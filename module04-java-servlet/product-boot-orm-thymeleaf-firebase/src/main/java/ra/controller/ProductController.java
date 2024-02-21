package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.dto.AddOrEditProductDto;
import ra.model.Category;
import ra.model.Product;
import ra.service.CategoryService;
import ra.service.ProductService;
import ra.service.UploadService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UploadService uploadService;

    // Hiển thị danh sách tất cả sản phẩm
    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProductsSortedByDate();
        model.addAttribute("products", products);
        return "product/list-product";
    }

    //show or hide
    @GetMapping("/status/{id}")
    public String toggleProductStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.productStatus(id);
            redirectAttributes.addFlashAttribute("successMessage", "Product status updated successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating product status.");
        }
        return "redirect:/products";
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "product/add-product";
    }

    // Xử lý thêm sản phẩm
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") AddOrEditProductDto addProductDto, RedirectAttributes redirectAttributes) {
        try {
            // Chuyển đổi từ DTO sang Entity
            Product product = convertDtoToEntity(addProductDto, null); // Gửi null vì đây là thêm mới
            productService.addProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "Product added successfully.");
            return "redirect:/products";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding product: " + e.getMessage());
            return "redirect:/products";
        }
    }

    //Form edit product
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            // Xử lý trường hợp sản phẩm không tìm thấy
            return "redirect:/products";
        }
        AddOrEditProductDto editProductDto = new AddOrEditProductDto();
        // Chuyển đổi từ Product sang EditProductDto
        editProductDto.setProductId(product.getId());
        editProductDto.setCategoryId(product.getCategory().getId());
        editProductDto.setProductName(product.getProductName());
        editProductDto.setPrice(product.getPrice());
        editProductDto.setDescription(product.getDescription());
        editProductDto.setQuantity(product.getQuantity());
        editProductDto.setClassification(product.getClassification());
        editProductDto.setStatus(product.getStatus());
        model.addAttribute("editProductDto", editProductDto);

        // Lấy danh sách các danh mục và thêm vào model
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "product/edit-product";
    }


    //Xử lí edit
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute("editProductDto") AddOrEditProductDto editProductDto, RedirectAttributes redirectAttributes) throws Exception {
        Product productToUpdate = convertDtoToEntity(editProductDto, id);
        productService.editProduct(productToUpdate);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully.");
        return "redirect:/products";
    }


    //search
    @GetMapping("/search")
    public String searchProduct(@RequestParam(name = "keyword") String keyword, Model model) {
        List<Product> searchResult = productService.searchProduct(keyword);
        model.addAttribute("products", searchResult);
        return "product/list-product";
    }

    // Xóa sản phẩm dựa trên ID
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(productId);
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully.");
        } catch (Exception e) {
            // Xử lý lỗi nếu sản phẩm không tìm thấy hoặc không thể xóa
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting product.");
        }
        return "redirect:/products";
    }

    //Convert Dto to Entity
    private Product convertDtoToEntity(AddOrEditProductDto addProductDto, Long productId) {
        Product product;
        if (productId != null) {
            //chỉnh sửa sản phẩm
            product = productService.getProductById(productId);
        } else {
            //thêm mới sản phẩm
            product = new Product();
        }

        Category category = categoryService.getCategoryById(addProductDto.getCategoryId());
        product.setCategory(category);
        product.setProductName(addProductDto.getProductName());
        product.setPrice(addProductDto.getPrice());
        product.setDescription(addProductDto.getDescription());
        product.setQuantity(addProductDto.getQuantity());
        product.setClassification(addProductDto.getClassification());
        product.setStatus(true);

        // Chỉ thay đổi imgUrl nếu có file mới được cung cấp
        if (addProductDto.getFile() != null && !addProductDto.getFile().isEmpty()) {
            product.setImgUrl(uploadService.upload(addProductDto.getFile()));
        }

        return product;
    }
}


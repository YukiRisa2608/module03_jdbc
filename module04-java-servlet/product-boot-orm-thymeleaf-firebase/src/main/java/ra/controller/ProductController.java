package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.dto.AddProductDto;
import ra.dto.EditProductDto;
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
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/list-product";
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
    public String addProduct(@ModelAttribute("product") AddProductDto addProductDto) {
        Product product = new Product();
        Category category = categoryService.getCategoryById(addProductDto.getCategoryId());
        product.setCategory(category);
        product.setProductName(addProductDto.getProductName());
        product.setPrice(addProductDto.getPrice());
        product.setDescription(addProductDto.getDescription());
        product.setQuantity(addProductDto.getQuantity());
        product.setClassification(addProductDto.getClassification());
        product.setStatus(true);
        product.setImgUrl(uploadService.upload(addProductDto.getFile()));
        productService.addProduct(product);
        return "redirect:/products"; // Điều hướng đến trang danh sách sản phẩm sau khi thêm
    }

    //Form edit product
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            // Xử lý trường hợp sản phẩm không tìm thấy
            return "redirect:/products";
        }
        EditProductDto editProductDto = new EditProductDto();
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
    @PostMapping("/edit")
    public String editProduct(@ModelAttribute("editProductDto") EditProductDto editProductDto) {
        Product product = productService.getProductById(editProductDto.getProductId());
        Category category = categoryService.getCategoryById(editProductDto.getCategoryId());
        // Cập nhật thông tin sản phẩm
        product.setCategory(category);
        product.setProductName(editProductDto.getProductName());
        product.setPrice(editProductDto.getPrice());
        product.setDescription(editProductDto.getDescription());
        product.setQuantity(editProductDto.getQuantity());
        product.setClassification(editProductDto.getClassification());
        product.setStatus(editProductDto.getStatus());
        //Nếu người dùng có chọn file mới thì mới thực hiện set file mới
        if (!editProductDto.getFile().isEmpty()) {
            product.setImgUrl(uploadService.upload(editProductDto.getFile()));
        }
        productService.updateProduct(product);
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
}


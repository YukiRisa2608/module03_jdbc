package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.dto.AddPProductDto;
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
        return "product/list-product"; // Điều hướng đến trang hiển thị danh sách sản phẩm (product/list-product.html)
    }
    // cái phương thức search dau chi trong home controller ak

    // Hiển thị form thêm sản phẩm
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "product/add-product"; // Điều hướng đến trang thêm sản phẩm (product/add.html)
    }

    // Xử lý thêm sản phẩm
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") AddPProductDto addPProductDto) {
        Product product = new Product();
        Category category = categoryService.getCategoryById(addPProductDto.getCategoryId());
        product.setCategory(category);
        product.setProductName(addPProductDto.getProductName());
        product.setPrice(addPProductDto.getPrice());
        product.setDescription(addPProductDto.getDescription());
        product.setQuantity(addPProductDto.getQuantity());
        product.setClassification(addPProductDto.getClassification());
        product.setStatus(true);
        product.setImgUrl(uploadService.upload(addPProductDto.getFile()));
        productService.addProduct(product);
        return "redirect:/products"; // Điều hướng đến trang danh sách sản phẩm sau khi thêm
    }

    //search
    @GetMapping("/search")
    public String searchProduct(@RequestParam(name = "keyword") String keyword, Model model) {
        List<Product> searchResult = productService.searchProduct(keyword);
        model.addAttribute("products", searchResult);
        return "product/list-product";
    }
}


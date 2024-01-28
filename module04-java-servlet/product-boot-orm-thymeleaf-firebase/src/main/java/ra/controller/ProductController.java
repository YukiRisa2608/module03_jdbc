package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.Product;
import ra.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Hiển thị danh sách tất cả sản phẩm
    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "list-product"; // Điều hướng đến trang hiển thị danh sách sản phẩm (product/list-product.html)
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/add"; // Điều hướng đến trang thêm sản phẩm (product/add.html)
    }

    // Xử lý thêm sản phẩm
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/products"; // Điều hướng đến trang danh sách sản phẩm sau khi thêm
    }

    // Hiển thị form cập nhật sản phẩm
    @GetMapping("/update/{id}")
    public String showUpdateProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/update"; // Điều hướng đến trang cập nhật sản phẩm (product/update.html)
    }

    // Xử lý cập nhật sản phẩm
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @ModelAttribute("product") Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/products"; // Điều hướng đến trang danh sách sản phẩm sau khi cập nhật
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products"; // Điều hướng đến trang danh sách sản phẩm sau khi xóa
    }
}


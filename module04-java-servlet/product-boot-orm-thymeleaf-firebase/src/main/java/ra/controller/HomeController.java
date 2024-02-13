package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.Product;
import ra.service.ProductService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;

    @GetMapping("/home")
    public String getHome(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "home"; // Điều hướng đến trang chủ (home.html)
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam(name = "keyword") String keyword, Model model) {
        List<Product> searchResult = productService.searchProduct(keyword);
        model.addAttribute("products", searchResult);
        return "home";
    }
}


package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ra.model.Category;
import ra.model.Product;
import ra.service.CategoryService;
import ra.service.ProductService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/home")
    public String getHome(Model model) {

        List<Product> products = productService.findAllActiveProducts();
        List<Category> categories = categoryService.findAllActiveCategories();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "home/home";
    }

    //search
    @GetMapping("/search")
    public String searchProduct(@RequestParam(name = "keyword") String keyword, Model model) {
        List<Product> searchResult = productService.searchProduct(keyword);
        model.addAttribute("products", searchResult);
        return "home/home";
    }

    //view detail
    @GetMapping("/detail/{id}")
    public String showProductDetail(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "Product not found";
        }
        model.addAttribute("product", product);
        return "home/product-detail";
    }

    //Order by
    @GetMapping("/sort")
    public String getSortedProducts(@RequestParam(required = false) String sort, Model model) {
        List<Product> products;
        if ("asc".equals(sort)) {
            products = productService.findAllByOrderByPriceAsc();
        } else if ("desc".equals(sort)) {
            products = productService.findAllByOrderByPriceDesc();
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("products", products);
        return "home/home";
    }

    //Find product by category
    @GetMapping("/filter")
    public String filterProductsByCategory(@RequestParam(required = false) Long categoryId, Model model) {
        List<Product> products;
        if (categoryId != null) {
            products = productService.findByCategoryId(categoryId);
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("products", products);
        return "home/home";
    }
}

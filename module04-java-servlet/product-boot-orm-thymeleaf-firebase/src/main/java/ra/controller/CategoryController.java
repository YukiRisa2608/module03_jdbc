package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.Category;
import ra.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Hiển thị danh sách tất cả danh mục
    @GetMapping
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/list"; // Điều hướng đến trang hiển thị danh sách danh mục (category/list-product.html)
    }

    // Hiển thị form thêm danh mục
    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add"; // Điều hướng đến trang thêm danh mục (category/add.html)
    }

    // Xử lý thêm danh mục
    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories"; // Điều hướng đến trang danh sách danh mục sau khi thêm
    }

    // Hiển thị form cập nhật danh mục
    @GetMapping("/update/{id}")
    public String showUpdateCategoryForm(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category/update"; // Điều hướng đến trang cập nhật danh mục (category/update.html)
    }

    // Xử lý cập nhật danh mục
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") Integer id, @ModelAttribute("category") Category category) {
        category.setId(id);
        categoryService.updateCategory(category);
        return "redirect:/categories"; // Điều hướng đến trang danh sách danh mục sau khi cập nhật
    }
}

package ra.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.model.Category;
import ra.model.Product;
import ra.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public String categoryManagement(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/list-categories";
    }

    // Hiển thị form thêm danh mục
    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add-category";
    }

    // Xử lý thêm danh mục
    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    //Delete category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (categoryService.deleteCategoryIfNoProducts(id)) {
            redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete category cause contains products.");
        }
        return "redirect:/categories";
    }

    //Display Form Edit category
    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category/edit-category";
    }

    //Edit category
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        category.setId(id);
        categoryService.updateCategory(category);
        redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully!");
        return "redirect:/categories";
    }

    //Display or not
    @GetMapping("/status/{id}")
    public String showOrHideCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.showOrHideCategory(id);
            redirectAttributes.addFlashAttribute("successMessage", "Category visibility toggled successfully!");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Category not found");
        }
        return "redirect:/categories";
    }

    //search
    @GetMapping("/search")
    public String searchCategory(@RequestParam(name = "keyword") String keyword, Model model) {
        List<Category> searchResult = categoryService.searchCategory(keyword);
        model.addAttribute("categories", searchResult);
        return "category/list-categories";
    }
}

package ra.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.model.Category;
import ra.model.CategoryProductCount;
import ra.model.Product;
import ra.service.CategoryService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public String categoryManagement(Model model) {
            List<Category> categories = categoryService.getAllCategories();
            Map<Long, Long> categoryCounts = categoryService.getCategoryCounts();
            model.addAttribute("categories", categories);
            model.addAttribute("categoryCounts", categoryCounts);
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
    public String createCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        try {
            Category savedCategory = categoryService.addCategory(category);
            redirectAttributes.addFlashAttribute("successMessage", "Category '" + savedCategory.getCategoryName() + "' added successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/categories";
    }


    //Delete category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
            try {
                categoryService.deleteCategory(id);
                redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully.");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            }
            return "redirect:/categories";
    }

    //Display Form Edit category
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        if (categoryService.hasProducts(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot edit category because it has products.");
            return "redirect:/categories";
        }
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "category/edit-category";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Category not found.");
            return "redirect:/categories";
        }
    }

    //Edit category
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        if (categoryService.updateCategory(id, category)) {
            redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot edit category because it has products or does not exist.");
        }
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


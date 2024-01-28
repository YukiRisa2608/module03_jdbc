package ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.CategoryRepostory;
import ra.model.Category;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepostory categoryRepository;

    @Autowired
    public CategoryService(CategoryRepostory categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Thêm danh mục
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Lấy danh sách tất cả danh mục
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lấy danh mục theo ID
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Cập nhật danh mục
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Xóa danh mục
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}


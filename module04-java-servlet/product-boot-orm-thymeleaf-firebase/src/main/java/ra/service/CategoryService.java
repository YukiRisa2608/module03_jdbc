package ra.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.CategoryRepostory;
import ra.dao.ProductReponsitory;
import ra.model.Category;
import ra.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepostory categoryRepository;

    @Autowired
    private ProductReponsitory productRepository;

    // Thêm danh mục
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Lấy danh sách tất cả danh mục
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lấy danh mục theo ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    //Tìm kiếm category theo keyword
    public List<Category> searchCategory(String keyword){
        return categoryRepository.findAllByCategoryNameContainingIgnoreCase(keyword);
    }

    // Cập nhật danh mục
    public void updateCategory(Category updatedCategory) {
        Category category = categoryRepository.findById(updatedCategory.getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + updatedCategory.getId()));

        // Cập nhật thông tin danh mục cũ
        category.setCategoryName(updatedCategory.getCategoryName());

        categoryRepository.save(category);
    }

    // Xóa danh mục
    public boolean deleteCategoryIfNoProducts(Long categoryId) {
        // Kiểm tra xem có sản phẩm nào thuộc về danh mục này không
        if (productRepository.existsByCategoryId(categoryId)) {
            return false;
        }
        categoryRepository.deleteById(categoryId);
        return true;
    }

    public void showOrHideCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        Boolean currentStatus = category.getStatus();
        if(currentStatus == null) {
            // Nếu status là null, mặc định là true
            category.setStatus(Boolean.TRUE);
        } else {
            // Đảo giá trị của status
            category.setStatus(!currentStatus);
        }
        categoryRepository.save(category);
    }


}


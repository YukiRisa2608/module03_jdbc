package ra.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.CategoryRepository;
import ra.dao.ProductReponsitory;
import ra.model.Category;
import ra.model.CategoryProductCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductReponsitory productRepository;

    // Thêm danh mục
    @Transactional
    public Category addCategory(Category category) throws Exception {
        // Kiểm tra xem tên danh mục đã tồn tại chưa
        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new Exception("Error!!! Category name '" + category.getCategoryName() + "' already exists.");
        }
        return categoryRepository.save(category);
    }

    // Cập nhật danh mục
    public boolean updateCategory(Long id, Category categoryDetails) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            // Kiểm tra số lượng sản phẩm trong danh mục
            if (hasProducts(id)) {
                return false; // Không cho phép chỉnh sửa nếu có sản phẩm
            }
            category.setCategoryName(categoryDetails.getCategoryName());
            categoryRepository.save(category);
            return true; // Chỉnh sửa thành công
        }
        return false; // Danh mục không tồn tại
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

    // Check category has product
    public boolean hasProducts(Long categoryId) {
        return categoryRepository.countProductsByCategoryId(categoryId) > 0;
    }

    //Delete category
    public void deleteCategory(Long categoryId) throws Exception {
        if (hasProducts(categoryId)) {
            // Ném ra một ngoại lệ hoặc xử lý logic không cho phép xóa
            throw new Exception("Cannot delete category if it has products.");
        } else {
            // Nếu không có sản phẩm, thực hiện xóa danh mục
            categoryRepository.deleteById(categoryId);
        }
    }

    //lấy tất cả categories có status là true
    public List<Category> findAllActiveCategories() {
        return categoryRepository.findAllByStatusTrue();
    }

    //Oder by create date
    public List<Category> getAllCategoriesSortedByDate() {
        return categoryRepository.findAllByOrderByCreatedDateDesc();
    }

    //Show or Hide
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

    //Count product in category
    public Map<Long, Long> getCategoryCounts() {
        // gọi đến categoryRepository findProductCountsByCategory().
        // trả về một danh sách các đối tượng CategoryProductCount,
        // mỗi đối tượng chứa ID của một danh mục (categoryId) và số lượng sản phẩm tương ứng (productCount) trong danh mục.
        List<CategoryProductCount> counts = categoryRepository.findProductCountsByCategory();
        //khởi tạo một HashMap mới categoryCounts để lưu trữ kết quả.
        // Map ánh xạ từ Long (ID của danh mục) sang Long (số lượng sản phẩm trong danh mục đó).
        Map<Long, Long> categoryCounts = new HashMap<>();
        //duyệt qua danh sách counts nhận được từ truy vấn.
        // Với mỗi đối tượng CategoryProductCount trong danh sách,lấy categoryId làm khóa và productCount làm giá trị, sau đó lưu chúng vào Map categoryCounts.
        for (CategoryProductCount count : counts) {
            categoryCounts.put(count.getCategoryId(), count.getProductCount());
        }
        return categoryCounts;
    }

}


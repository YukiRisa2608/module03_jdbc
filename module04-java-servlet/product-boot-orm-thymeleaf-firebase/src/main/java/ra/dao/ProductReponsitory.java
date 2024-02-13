package ra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.Product;

import java.util.List;
@Repository
public interface ProductReponsitory extends JpaRepository <Product, Long>{
    List<Product> findAllByProductNameContainingIgnoreCase(String keyword);
    // Kiểm tra sự tồn tại của sản phẩm trong danh mục
    boolean existsByCategoryId(Long categoryId);
}

package ra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.Category;
import ra.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductReponsitory extends JpaRepository <Product, Long>{
    //search
    List<Product> findAllByProductNameContainingIgnoreCase(String keyword);
    //find by name
    Optional<Product> findByProductName(String productName);
    boolean existsByProductName(String productName);
    //Find product by category
    List<Product> findByCategoryId(Long categoryId);
    // Kiểm tra sự tồn tại của sản phẩm trong danh mục
    boolean existsByCategoryId(Long categoryId);
    // Order by
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();

    // Tìm tất cả product có status là true
    List<Product> findAllByStatusTrue();

    //order by create date
    List<Product> findAllByOrderByCreatedDateDesc();

}

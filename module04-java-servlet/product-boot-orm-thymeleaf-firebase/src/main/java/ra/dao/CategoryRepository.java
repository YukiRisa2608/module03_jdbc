package ra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.model.Category;
import ra.model.CategoryProductCount;

import java.util.List;

public interface CategoryRepository extends JpaRepository <Category, Long> {
    //Check if exist name for add
    boolean existsByCategoryName(String categoryName);
    //Check if exist name and ID for update
    boolean existsByCategoryNameAndIdNot(String categoryName, Long id);
    //Search category by keyword
    List<Category> findAllByCategoryNameContainingIgnoreCase(String keyword);
    //Count product in all category
    @Query("SELECT new ra.model.CategoryProductCount(c.id, c.categoryName, COUNT(p)) FROM Category c JOIN c.products p GROUP BY c.id")
    List<CategoryProductCount> findProductCountsByCategory();
    //Count product by category ID
    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId")
    Long countProductsByCategoryId(@Param("categoryId") Long categoryId);
    // Tìm tất cả categories có status là true
    List<Category> findAllByStatusTrue();
    //order by create date
    List<Category> findAllByOrderByCreatedDateDesc();
}

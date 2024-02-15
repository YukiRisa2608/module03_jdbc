package ra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.Category;
import ra.model.Product;

import java.util.List;

public interface CategoryRepostory extends JpaRepository <Category, Long> {
    List<Category> findAllByCategoryNameContainingIgnoreCase(String keyword);

}

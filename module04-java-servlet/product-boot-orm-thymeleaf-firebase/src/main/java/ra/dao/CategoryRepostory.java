package ra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.Category;

public interface CategoryRepostory extends JpaRepository <Category, Integer> {
}

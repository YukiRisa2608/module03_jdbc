package ra.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.Product;

public interface ProductReponsitory extends JpaRepository <Product, Integer>{
}

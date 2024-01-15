package service.interf;

import dto.ProductRequest;
import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(Long id);

    void add(ProductRequest product);
    void update(Product product);

    void deleteById(Long id);
}

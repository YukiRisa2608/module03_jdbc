package ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.ProductReponsitory;
import ra.model.Product;

import java.util.List;

@Service
public class ProductService {
    private final ProductReponsitory productRepository;

    @Autowired
    public ProductService(ProductReponsitory productRepository) {
        this.productRepository = productRepository;
    }

    // Thêm sản phẩm
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Tìm kiếm phẩm theo ID
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    // Cập nhật sản phẩm
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Xóa sản phẩm
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}


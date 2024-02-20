package ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.ProductReponsitory;
import ra.model.Category;
import ra.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductReponsitory productRepository;

    @Autowired
    public ProductService(ProductReponsitory productRepository) {
        this.productRepository = productRepository;
    }

    // Thêm sản phẩm
    public Product addProduct(Product product) throws Exception {
        // Kiểm tra xem sản phẩm với tên đã cho đã tồn tại chưa
        if (productRepository.existsByProductName(product.getProductName())) {
            // Nếu sản phẩm đã tồn tại, ném ra một ngoại lệ
            throw new Exception("Product with name '" + product.getProductName() + "' already exists.");
        }
        // Nếu không, lưu sản phẩm mới vào cơ sở dữ liệu
        return productRepository.save(product);
    }

    //Edit
    public Product editProduct(Product product) throws Exception {
        return productRepository.save(product);
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //find by name
    public Optional<Product> findProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    //Tìm kiếm sản phẩm theo keyword
    public List<Product> searchProduct(String keyword){
        return productRepository.findAllByProductNameContainingIgnoreCase(keyword);
    }

    // Tìm kiếm phẩm theo ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Xóa sản phẩm
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    //Order by
    public List<Product> findAllByOrderByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<Product> findAllByOrderByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }
    //find product by category
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public void productStatus(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(!product.getStatus());
        productRepository.save(product);
    }

    //lấy tất cả product có status là true
    public List<Product> findAllActiveProducts() {
        return productRepository.findAllByStatusTrue();
    }
}


package service.impl;

import dao.IProductDao;
import dao.impl.ProductDaoImpl;
import dto.ProductRequest;
import model.Product;
import service.interf.IProductService;
import java.util.List;

public class ProductServiceImpl implements IProductService {

    private static IProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public void add(ProductRequest productDto) {
        // nhận vào 1 Productdto nhưng lại lưu 1 Product
        // chuyển đổi (mapper) từ dto -> model
        Product pro = new Product(null,productDto.getProductName(),productDto.getPrice(),productDto.getDescription(),true, productDto.getStock());
        productDao.save(pro);
    }

    @Override
    public void update(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }
}

package dao.impl;

import dao.IProductDao;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDaoImpl implements IProductDao {
    private static List<Product> productList = new ArrayList<>();
    static {
        Product p1 = new Product(1L,"Quần kaki nam",100D,"Quần áo cao câ nhập khẩu",true, 99);
        Product p2 = new Product(2L,"Áo sơ mi dài tay",150D,"Quần áo cao câ nhập khẩu",true, 999);
        Product p3 = new Product(3L,"Áo da nam",200D,"Quần áo cao câ nhập khẩu",true, 50);
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
    }
    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(Long id) {
        return productList.stream().filter(pro-> Objects.equals(pro.getId(), id)).findAny().orElse(null);
    }

    @Override
    public void save(Product product) {
        if(product.getId()==null){
            // thêm mới
            product.setId(getNewId());
            productList.add(product);
        }else {
            // cập nhật
            productList.set(productList.indexOf(findById(product.getId())), product);
        }
    }

    @Override
    public void deleteById(Long id) {
        productList.remove(findById(id));
    }

    public Long getNewId(){
        long idMax = 0;
        for (int i = 0; i< productList.size();i++){
            if(idMax<productList.get(i).getId()){
                idMax = productList.get(i).getId();
            }
        };
        return idMax+1;
    }
}

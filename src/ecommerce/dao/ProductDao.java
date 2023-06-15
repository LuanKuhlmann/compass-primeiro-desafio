package ecommerce.dao;

import ecommerce.entities.Product;

import java.util.List;

public interface ProductDao {

    void insert(Product obj);
    void update(Product obj);
    void deleteById(Integer id);
    void updateProductQuantity(Integer id, Integer quantity);
    Product findById(Integer id);
    List<Product> findAll();

}

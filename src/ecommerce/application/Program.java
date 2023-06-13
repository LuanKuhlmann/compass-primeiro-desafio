package ecommerce.application;

import ecommerce.dao.DaoFactory;
import ecommerce.dao.ProductDao;
import ecommerce.dao.impl.ProductDaoJDBC;
import ecommerce.entities.Product;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;

        ProductDao productDao = DaoFactory.createProductDao();

        Product prod = productDao.findById(2);

        //System.out.println("TESTE insert");
        //Product newProduct = new Product(1, "PC Gamer", 5000.0, 5);
        //Product newProduct2 = new Product(2, "Smartphone", 2550.0, 7);
        //productDao.insert(newProduct);
        //productDao.insert(newProduct2);
        //System.out.println("Inserted! New id = " + newProduct.getId());

        //System.out.println("TESTE findById");
        //System.out.println(prod);

        System.out.println("TESTE delete");
        productDao.deleteById(1);
    }
}
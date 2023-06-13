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

        System.out.println("TESTE");
        Product newProduct = new Product(1, "PC Gamer", 5000.0, 5);
        productDao.insert(newProduct);
        System.out.println("Inserted! New id = " + newProduct.getId());

    }
}

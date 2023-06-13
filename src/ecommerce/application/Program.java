package ecommerce.application;

import ecommerce.dao.DaoFactory;
import ecommerce.dao.ProductDao;
import ecommerce.dao.impl.ProductDaoJDBC;
import ecommerce.entities.Product;

import java.sql.Connection;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;

        ProductDao productDao = DaoFactory.createProductDao();

        Product prod = productDao.findById(2);

        //System.out.println("TESTE insert");
        //Product newProduct = new Product(1, "PC Gamer", 5000.0, 5);
        //Product newProduct2 = new Product(2, "Smartphone", 2550.0, 7);
        //Product newProduct3 = new Product(3, "Monitor", 2000.0, 3);
        //Product newProduct4 = new Product(4, "Gaming Chair", 900.9, 11);
        //Product newProduct5 = new Product(5, "Playstation 5", 5000.0, 3);
        //productDao.insert(newProduct);
        //productDao.insert(newProduct2);
        //productDao.insert(newProduct3);
        //productDao.insert(newProduct4);
        //productDao.insert(newProduct5);
        //System.out.println("Inserted! New id = " + newProduct.getId());

        //System.out.println("TESTE findById");
        //System.out.println(prod);

        //System.out.println("TESTE delete");
        //productDao.deleteById(1);

        System.out.println("TESTE findALL");
        List<Product> list = productDao.findAll();
        for (Product obj : list) {
            System.out.println(obj);
        }
    }
}
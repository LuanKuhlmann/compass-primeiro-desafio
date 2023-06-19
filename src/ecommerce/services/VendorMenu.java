package ecommerce.services;

import ecommerce.dao.DaoFactory;
import ecommerce.dao.ProductDao;
import ecommerce.entities.Product;

import java.util.List;
import java.util.Scanner;

public class VendorMenu {

    private static ProductDao productDao = DaoFactory.createProductDao();

    public static void productList() {
        List<Product> list = productDao.findAll();
        for (Product products : list) {
            System.out.println(products);
        }
        System.out.println("-------------------------------");
    }
    public static void createProduct(Scanner sc) {
        System.out.println("Inform DATA");
        System.out.print("Inform Product ID: ");
        int id = sc.nextInt();
        if (productDao.findById(id) != null) {
            System.out.println("ID " + id + " already in use.");
            System.out.println("-------------------------------");
            return;
        }
        System.out.print("Inform Product name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Inform Product price: ");
        double price = sc.nextDouble();
        System.out.print("Inform quantity: ");
        int quantity = sc.nextInt();
        Product newProduct = new Product(id, name, price, quantity);
        productDao.insert(newProduct);
        System.out.println("Product created.");
        System.out.println(newProduct);
        System.out.println("-------------------------------");
    }

    public static void removeProduct(Scanner sc) {
        System.out.print("Inform Product ID: ");
        int id = sc.nextInt();
        productDao.deleteById(id);
        System.out.println("Product removed.");
        System.out.println("-------------------------------");
    }

    public static void updateProduct(Scanner sc) {
        System.out.print("Inform Product ID: ");
        int id = sc.nextInt();
        Product product = productDao.findById(id);
        System.out.println(product);
        System.out.println("Inform which data to update:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Price");
        System.out.println("4. Quantity");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int opUpdate = sc.nextInt();
        System.out.println("-------------------------------");

        switch (opUpdate) {
            case 1 -> {
                System.out.print("Inform new product ID: ");
                int idToUpdate = sc.nextInt();
                product.setId(idToUpdate);
                productDao.update(product);
                System.out.println("Product Updated.");
                System.out.println("New Product DATA");
                System.out.println(product);
                System.out.println("-------------------------------");
            }
            case 2 -> {
                System.out.print("Inform new product name: ");
                sc.nextLine();
                String name = sc.nextLine();
                product.setName(name);
                productDao.update(product);
                System.out.println("Product Updated.");
                System.out.println("New Product DATA");
                System.out.println(product);
                System.out.println("-------------------------------");
            }
            case 3 -> {
                System.out.print("Inform new product price: ");
                double price = sc.nextDouble();
                product.setPrice(price);
                productDao.update(product);
                System.out.println("Product Updated.");
                System.out.println("New Product DATA");
                System.out.println(product);
                System.out.println("-------------------------------");
            }
            case 4 -> {
                System.out.print("Inform new product quantity: ");
                int quantity = sc.nextInt();
                product.setQuantity(quantity);
                productDao.update(product);
                System.out.println("Product Updated.");
                System.out.println("New Product DATA");
                System.out.println(product);
                System.out.println("-------------------------------");
            }
            default -> {
                System.out.println("Exiting update chart.");
                System.out.println("-------------------------------");
            }
        }
    }
}

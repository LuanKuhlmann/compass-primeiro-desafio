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
        for (Product obj : list) {
            System.out.println(obj);
        }
        System.out.println("-------------------------------");
    }
    public static void createProduct(Scanner sc) {
        System.out.println("Inform DATA");
        System.out.print("Inform Product ID: ");
        int id = sc.nextInt();
        if (productDao.findById(id) != null) {
            System.out.println("Product with ID " + id + " already exists. Cannot create another product with the same ID.");
            return;
        }
        System.out.print("Inform Product name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Inform Product price: ");
        double price = sc.nextDouble();
        System.out.print("Inform quantity: ");
        int quantity = sc.nextInt();
        System.out.println();
        Product newProduct = new Product(id, name, price, quantity);
        productDao.insert(newProduct);
        System.out.println("Product created!");
        System.out.println(newProduct);
        System.out.println("-------------------------------");
    }

    public static void removeProduct(Scanner sc) {
        System.out.print("Inform Product ID: ");
        int idRemove = sc.nextInt();
        productDao.deleteById(idRemove);
        System.out.println("-------------------------------");
    }

    public static void updateProduct(Scanner sc) {
        System.out.print("Inform Product ID: ");
        int idUpdate = sc.nextInt();
        Product product = productDao.findById(idUpdate);
        System.out.println(product);
        System.out.println("What do you want to updade?");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Price");
        System.out.println("4. Quantity");
        System.out.println("5. Exit");
        int opUpdate = sc.nextInt();

        switch (opUpdate) {
            case 1:
                System.out.print("Inform new product ID: ");
                int idToUpdate = sc.nextInt();
                product.setId(idToUpdate);
                productDao.update(product);
                System.out.println("New Product DATA");
                System.out.println(product);
                System.out.println();
                break;
            case 2:
                System.out.print("Inform new product name: ");
                sc.nextLine();
                String nameUpdate = sc.nextLine();
                product.setName(nameUpdate);
                productDao.update(product);
                System.out.println("New Product DATA");
                System.out.println(product);
                System.out.println();
                break;
            case 3:
                System.out.print("Inform new product price: ");
                double priceUpdate = sc.nextDouble();
                product.setPrice(priceUpdate);
                productDao.update(product);
                System.out.println("New Product DATA");
                System.out.println(product);
                System.out.println();
                break;
            case 4:
                System.out.print("Inform new product quantity: ");
                int quantityUpdate = sc.nextInt();
                product.setQuantity(quantityUpdate);
                productDao.update(product);
                System.out.println("New Product DATA");
                System.out.println(product);
                System.out.println();
                break;
            case 5:
                break;
        }
    }

}

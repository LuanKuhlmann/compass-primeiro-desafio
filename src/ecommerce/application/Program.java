package ecommerce.application;

import ecommerce.dao.DaoFactory;
import ecommerce.dao.ProductDao;
import ecommerce.dao.impl.ProductDaoJDBC;
import ecommerce.entities.Product;
import ecommerce.entities.ShoppingCart;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;

        Scanner sc = new Scanner(System.in);

        boolean confirmation = false;

        ProductDao productDao = DaoFactory.createProductDao();
        ShoppingCart cart = new ShoppingCart();
        List<Product> products = productDao.findAll();

        System.out.println("1. Vendor");
        System.out.println("2. Client");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int op = sc.nextInt();

        while (!confirmation) {

            if (op == 1) {

                boolean confirmationVendor = false;
                while (!confirmationVendor) {
                    System.out.println("1. Add Product");
                    System.out.println("2. Remove Product");
                    System.out.println("3. Update Product");
                    System.out.println("4. Show Product List");
                    System.out.println("5. Exit");
                    int opVendor = sc.nextInt();

                    switch (opVendor) {
                        case 1:
                            System.out.println("Inform DATA");
                            System.out.print("Inform Product ID: ");
                            int id = sc.nextInt();
                            System.out.print("Inform Product name: ");
                            sc.nextLine();
                            String name = sc.nextLine();
                            System.out.print("Inform Product price: ");
                            double price = sc.nextDouble();
                            System.out.print("Inform quantity: ");
                            int quantity = sc.nextInt();
                            Product newProduct = new Product(id, name, price, quantity);
                            productDao.insert(newProduct);
                            break;
                        case 2:
                            System.out.print("Inform Product ID: ");
                            int idDelete = sc.nextInt();
                            productDao.deleteById(idDelete);
                            break;
                        case 3:
                            System.out.print("Inform Product ID: ");
                            break;
                        case 4:
                            List<Product> list = productDao.findAll();
                            for (Product obj : list) {
                                System.out.println(obj);
                            }
                            break;
                        case 5:
                            confirmationVendor = true;
                            break;
                    }
                }
                op = 0;
            } else if (op == 2) {
                boolean checkout = false;
                while (!checkout) {
                    System.out.println("1. Show Product List");
                    System.out.println("2. Add Product");
                    System.out.println("3. Remove Product");
                    System.out.println("4. Update Product");
                    System.out.println("5. Show Products in Cart and Total");
                    System.out.println("6. Checkout");
                    int opClient = sc.nextInt();

                    switch (opClient) {
                        case 1:
                            List<Product> list = productDao.findAll();
                            for (Product obj : list) {
                                System.out.println(obj);
                            }
                            break;
                        case 2:
                            System.out.print("Inform product ID: ");
                            int num = sc.nextInt();
                            cart.addProduct(products.get(num));
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            double total = cart.calculateTotal();
                            System.out.println("Products in cart:");
                            for (Product product : cart.getProducts()) {
                                System.out.println(product.getName());
                            }
                            System.out.println("Total value: $" + total);
                            break;
                        case 6:
                            checkout = true;
                            break;
                    }
                }
                op = 0;
            } else {
                System.out.println("Exiting program");
                confirmation = true;
            }
        }
    }

}

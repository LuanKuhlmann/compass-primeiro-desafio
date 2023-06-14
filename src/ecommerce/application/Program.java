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
                            int idRemove = sc.nextInt();
                            productDao.deleteById(idRemove);
                            break;
                        case 3:
                            System.out.print("Inform Product ID: ");
                            int idUpdate = sc.nextInt();
                            Product product = productDao.findById(idUpdate);
                            System.out.println(product);
                            System.out.println("What do you want to updade?");
                            System.out.println("1. ID");
                            System.out.println("2. Name");
                            System.out.println("3. Price");
                            System.out.println("4. Quantity");
                            int opUpdate = sc.nextInt();

                            switch (opUpdate) {
                                case 1:
                                    System.out.print("Inform new product ID: ");
                                    int idToUpdate = sc.nextInt();
                                    product.setId(idToUpdate);
                                    productDao.update(product);
                                    System.out.println("New Product DATA");
                                    System.out.println(product);
                                    break;
                                case 2:
                                    System.out.print("Inform new product name: ");
                                    sc.nextLine();
                                    String nameUpdate = sc.nextLine();
                                    product.setName(nameUpdate);
                                    productDao.update(product);
                                    System.out.println("New Product DATA");
                                    System.out.println(product);
                                    break;
                                case 3:
                                    System.out.print("Inform new product price: ");
                                    double priceUpdate = sc.nextDouble();
                                    product.setPrice(priceUpdate);
                                    productDao.update(product);
                                    System.out.println("New Product DATA");
                                    System.out.println(product);
                                    break;
                                case 4:
                                    System.out.print("Inform new product quantity: ");
                                    int quantityUpdate = sc.nextInt();
                                    product.setQuantity(quantityUpdate);
                                    productDao.update(product);
                                    System.out.println("New Product DATA");
                                    System.out.println(product);
                                    break;
                            }

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
                    System.out.println("4. Show Products in Cart and Total");
                    System.out.println("5. Checkout");
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
                            int id = sc.nextInt();
                            cart.addProduct(products.get(id));
                            break;
                        case 3:
                            System.out.print("Inform product ID: ");
                            int idRemove = sc.nextInt();
                            cart.removeProduct(products.get(idRemove));
                            break;
                        case 4:
                            double total = cart.calculateTotal();
                            System.out.println("Products in cart:");
                            for (Product product : cart.getProducts()) {
                                System.out.printf("Product: %s Price: %.2f ", product.getName(), product.getPrice());
                                System.out.println();
                            }
                            System.out.println("Total value: $" + total);
                            break;
                        case 5:
                            double totalFinal = cart.calculateTotal();
                            System.out.println("Products in cart:");
                            for (Product product : cart.getProducts()) {
                                System.out.println(product);
                            }
                            System.out.println("The total value of products in the shopping cart is: $" + totalFinal);
                            System.out.println();
                            System.out.println("Confirm transaction? 1. Yes / 2. No");
                            int num = sc.nextInt();
                            if(num == 1) {
                                System.out.println("Completed transaction, thank you!");
                                checkout = true;
                            }
                            break;
                    }
                }
                op = 0;
            } else {
                System.out.println();
                System.out.println("Exiting program");
                confirmation = true;
            }
        }
    }

}

package ecommerce.services;

import ecommerce.dao.DaoFactory;
import ecommerce.dao.ProductDao;
import ecommerce.entities.Product;
import ecommerce.entities.ShoppingCart;

import java.util.List;
import java.util.Scanner;

public class ShoppingCartMenu {
    private static ProductDao productDao = DaoFactory.createProductDao();
    private static ShoppingCart shoppingCart = new ShoppingCart();

    public static void addItemToCart(Scanner sc) {
        List<Product> products = productDao.findAll();
        System.out.println("Products in store:");

        for (Product product : products) {
            System.out.println("ID: " + product.getId() + ". " + "Name: " + product.getName() + " - $" + product.getPrice());
        }
        System.out.print("Inform the product ID you want to add to cart: ");
        int id = sc.nextInt();
        System.out.print("Inform the quantity: ");
        int quantity = sc.nextInt();

        for (Product product : products) {
            if (product.getId() == id && product.getQuantity() > 0) {
                shoppingCart.addItem(product, quantity);
                System.out.println("Product added to cart.");
                return;
            }
        }
        System.out.println("Invalid product ID or not enough items in stock.");
    }

    public static void removeItemFromCart(Scanner sc) {
        List<Product> items = shoppingCart.getItems();
        System.out.println("Items in cart:");

        for (Product item : items) {
            System.out.println(item.getId() + ". " + item.getName() + " - Quantity: " + item.getQuantity());
        }
        System.out.print("Inform the product ID you want to remove from cart: ");
        int id = sc.nextInt();
        System.out.print("Enter the quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        for (Product item : items) {
            if (item.getId() == id) {
                shoppingCart.removeItem(item, quantity);
                System.out.println("Product removed from cart.");
                return;
            }
        }

        System.out.println("Invalid product ID or quantity.");
    }

    public static void totalValueInCart() {
        List<Product> products = shoppingCart.getItems();
        System.out.println("Items in cart: ");

        for (Product product : products) {
            System.out.println(product.getId() + ". " + product.getName() + " - Quantity: " + product.getQuantity());
        }
        System.out.println("Total: $" + shoppingCart.getTotal());
    }

    public static Product getProductById(Integer id) {
        List<Product> products = productDao.findAll();
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public static void checkout(Scanner sc) {
        totalValueInCart();
        System.out.println("Confirm purchase? 1. Yes/ 2. No):");
        int op = sc.nextInt();
        if (op == 1) {
            List<Product> items = shoppingCart.getItems();
            for (Product item : items) {
                Product product = getProductById(item.getId());
                if (product != null) {
                    product.setQuantity(product.getQuantity() - item.getQuantity());
                    productDao.updateProductQuantity(product.getId(), product.getQuantity());
                }
            }
            System.out.println("Purchase confirmed.");
        } else {
            System.out.println("Purchase canceled.");
        }
    }
}

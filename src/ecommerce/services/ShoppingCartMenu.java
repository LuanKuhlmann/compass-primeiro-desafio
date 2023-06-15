package ecommerce.services;

import ecommerce.dao.DaoFactory;
import ecommerce.dao.ProductDao;
import ecommerce.entities.Product;
import ecommerce.entities.ShoppingCart;

import java.util.List;
import java.util.Scanner;

public class ShoppingCartMenu {
    private static ProductDao productDao = DaoFactory.createProductDao();
    private static ShoppingCart cart = new ShoppingCart();

    public static void addItemToCart(Scanner sc) {
        List<Product> products = productDao.findAll();
        System.out.println("Products in store: ");
        for (Product product : products) {
            System.out.println("ID: " + product.getId() + ". " + "Name: " + product.getName() + " - $" + product.getPrice());
        }
        System.out.print("Enter the ID of the product to add to cart: ");
        int productId = sc.nextInt();
        System.out.print("Enter the quantity: ");
        int quantity = sc.nextInt();
        for (Product product : products) {
            if (product.getId() == productId) {
                cart.addItem(product, quantity);
                System.out.println("Product added to cart.");
                return;
            }
        }
        System.out.println("Invalid product ID.");
    }

    public static void removeItemFromCart(Scanner sc) {
        List<Product> items = cart.getItems();
        System.out.println("Items in cart:");
        for (Product item : items) {
            System.out.println(item.getId() + ". " + item.getName() + " - Quantity: " + item.getQuantity());
        }
        System.out.print("Enter the ID of the product to remove from cart: ");
        int productId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        for (Product item : items) {
            if (item.getId() == productId) {
                cart.removeItem(item, quantity);
                System.out.println("Product removed from cart.");
                return;
            }
        }
        System.out.println("Invalid product ID or quantity.");
    }

    public static void totalValueInCart() {
        List<Product> items = cart.getItems();
        System.out.println("Items in cart: ");
        for (Product item : items) {
            System.out.println(item.getId() + ". " + item.getName() + " - Quantity: " + item.getQuantity());
        }
        System.out.println("Total: $" + cart.getTotal());
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
        int choice = sc.nextInt();
        if (choice == 1) {
            List<Product> items = cart.getItems();
            for (Product item : items) {
                Product product = getProductById(item.getId());
                if (product != null) {
                    product.setQuantity(product.getQuantity() - item.getQuantity());
                    productDao.updateProductQuantity(product.getId(), product.getQuantity());
                }
            }
            System.out.println("Sale confirmed.");
        } else {
            System.out.println("Sale canceled.");
        }
    }
}

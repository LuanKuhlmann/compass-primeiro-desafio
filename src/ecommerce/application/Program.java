package ecommerce.application;

import ecommerce.services.ShoppingCartMenu;
import ecommerce.services.VendorMenu;

import java.sql.Connection;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;

        Scanner sc = new Scanner(System.in);

        boolean confirmation = false;

        System.out.println("1. Vendor");
        System.out.println("2. Client");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int op = sc.nextInt();
        System.out.println("-------------------------------");


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
                    System.out.println("-------------------------------");

                    switch (opVendor) {
                        case 1:
                            VendorMenu.createProduct(sc);
                            break;
                        case 2:
                            VendorMenu.removeProduct(sc);
                            break;
                        case 3:
                            VendorMenu.updateProduct(sc);
                            break;
                        case 4:
                            VendorMenu.productList();
                            break;
                        case 5:
                            confirmationVendor = true;
                            break;
                        default:
                            System.out.println("Inform a valid option!");
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
                    System.out.println("4. Checkout");
                    int opClient = sc.nextInt();
                    System.out.println("-------------------------------");

                    switch (opClient) {
                        case 1:
                            VendorMenu.productList();
                            break;
                        case 2:
                            ShoppingCartMenu.addItemToCart(sc);
                            break;
                        case 3:
                            ShoppingCartMenu.removeItemFromCart(sc);
                            break;
                        case 4:
                            ShoppingCartMenu.checkout(sc);
                            checkout = true;
                            break;
                        default:
                            System.out.println("Inform a valid option!");
                            break;
                    }
                    op = 0;
                }
            } else {
                System.out.println();
                System.out.println("Exiting program");
                confirmation = true;
            }
        }

        sc.close();
    }
}
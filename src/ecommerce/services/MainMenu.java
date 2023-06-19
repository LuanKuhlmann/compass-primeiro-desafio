package ecommerce.services;

import java.util.Scanner;

public class MainMenu {

    public static void mainMenuApplication() {
        Scanner sc = new Scanner(System.in);

        boolean confirmation = false;

        System.out.println("1. Vendor");
        System.out.println("2. Client");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int op = sc.nextInt();
        System.out.println("-------------------------------");


        while (!confirmation) {

            switch (op) {
                case 1 -> {
                    boolean confirmationVendor = false;
                    while (!confirmationVendor) {
                        System.out.println("Choose an option based on the number: ");
                        System.out.println("1. Add Product");
                        System.out.println("2. Remove Product");
                        System.out.println("3. Update Product");
                        System.out.println("4. Show Product List");
                        System.out.println("5. Exit");
                        System.out.print("Enter your choice: ");
                        int opVendor = sc.nextInt();
                        System.out.println("-------------------------------");

                        switch (opVendor) {
                            case 1 -> {
                                VendorMenu.createProduct(sc);
                                System.out.println("-------------------------------");
                            }
                            case 2 -> {
                                VendorMenu.removeProduct(sc);
                                System.out.println("-------------------------------");
                            }
                            case 3 -> {
                                VendorMenu.updateProduct(sc);
                                System.out.println("-------------------------------");
                            }
                            case 4 -> {
                                VendorMenu.productList();
                                System.out.println("-------------------------------");
                            }
                            case 5 -> {
                                System.out.println("-------------------------------");
                                confirmationVendor = true;
                            }
                            default -> {
                                System.out.println("Inform a valid option.");
                                System.out.println("-------------------------------");
                            }
                        }
                    }
                    confirmation = true;
                }
                case 2 -> {
                    boolean checkout = false;
                    while (!checkout) {
                        System.out.println("Choose an option based on the number: ");
                        System.out.println("1. Show Product List");
                        System.out.println("2. Add Product to Cart");
                        System.out.println("3. Remove Product from Cart ");
                        System.out.println("4. Checkout");
                        System.out.print("Enter your choice: ");
                        int opClient = sc.nextInt();
                        System.out.println("-------------------------------");

                        switch (opClient) {
                            case 1 -> {
                                VendorMenu.productList();
                                System.out.println("-------------------------------");
                            }
                            case 2 -> {
                                ShoppingCartMenu.addItemToCart(sc);
                                System.out.println("-------------------------------");
                            }
                            case 3 -> {
                                ShoppingCartMenu.removeItemFromCart(sc);
                                System.out.println("-------------------------------");
                            }
                            case 4 -> {
                                ShoppingCartMenu.checkout(sc);
                                System.out.println("-------------------------------");
                                checkout = true;
                            }
                            default -> {
                                System.out.println("Inform a valid option.");
                                System.out.println("-------------------------------");
                            }
                        }
                    }
                    confirmation = true;
                }
                case 3 -> {
                    System.out.println("Exiting program.");
                    confirmation = true;
                }
                default -> {System.out.println("Inform a valid option.");
                            System.out.println("-------------------------------");
                            System.out.println("1. Vendor");
                            System.out.println("2. Client");
                            System.out.println("3. Exit");
                            System.out.print("Enter your choice: ");
                            op = sc.nextInt();}

            }


        }

        sc.close();
    }
}

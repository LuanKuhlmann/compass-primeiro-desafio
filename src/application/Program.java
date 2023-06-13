package application;

import entities.Product;

public class Program {
    public static void main(String[] args) {

        Product obj = new Product(1, "Computer", 2000.0, 2);
        System.out.println(obj);
    }
}

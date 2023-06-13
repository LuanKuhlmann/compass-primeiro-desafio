package application;

import db.DB;
import entities.Product;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {

        //Product obj = new Product(1, "Computer", 2000.0, 2);
        //System.out.println(obj);

        Connection conn = DB.getConnection();
        DB.closeConnection();
    }
}

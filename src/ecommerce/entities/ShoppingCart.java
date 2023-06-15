package ecommerce.entities;

import ecommerce.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        for (Product item : items) {
            if (item.getId() == product.getId()) {
                int newQuantity = item.getQuantity() + quantity;
                item.setQuantity(newQuantity);
                return;
            }
        }
        Product newProduct = new Product(product.getId(), product.getName(), product.getPrice(), quantity);
        items.add(newProduct);
    }

    public void removeItem(Product product, int quantity) {
        for (Product item : items) {
            if (item.getId() == product.getId()) {
                int newQuantity = item.getQuantity() - quantity;
                if (newQuantity > 0) {
                    item.setQuantity(newQuantity);
                } else {
                    items.remove(item);
                }
                return;
            }
        }
    }

    public double getTotal() {
            double total = 0;
            for (Product item : items) {
                total += item.getPrice() * item.getQuantity();
            }
            return total;
    }

    public List<Product> getItems() {
        return items;
    }
}
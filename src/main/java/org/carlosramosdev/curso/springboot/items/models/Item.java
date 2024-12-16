package org.carlosramosdev.curso.springboot.items.models;

public class Item {
    private Product product;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Item() {
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal(){
        return product.getPrice()*quantity;
    }
}

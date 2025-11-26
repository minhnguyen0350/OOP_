package model;

public class CartItem {
    private Banh banh;
    private int quantity;

    public CartItem(Banh banh, int quantity) {
        this.banh = banh;
        this.quantity = quantity;
    }

    public Banh getBanh() {
        return banh;
    }

    public void setBanh(Banh banh) {
        this.banh = banh;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public float getSubtotal() {
        if (banh == null) return 0;
        float priceAfterSale = banh.getFinalPrice();
        return priceAfterSale * quantity;
    }
}



package model;

import java.util.Date;

public class Booking {
    private int id;             // bookId
    private Customer customer;  // customerId -> đối tượng Customer
    private Banh banh;          // banhId -> đối tượng Banh
    private int quantity;       // số lượng mua
    private Date bookingDate;   // ngày đặt
    private String note;        // ghi chú

    // Constructor đầy đủ
    public Booking(int id, Customer customer, Banh banh, int quantity, Date bookingDate, String note) {
        this.id = id;
        this.customer = customer;
        this.banh = banh;
        this.quantity = quantity;
        this.bookingDate = bookingDate;
        this.note = note;
    }

    // Constructor khi thêm mới (chưa có id, chưa có ngày đặt)
    public Booking(Customer customer, Banh banh, int quantity, String note) {
        this.customer = customer;
        this.banh = banh;
        this.quantity = quantity;
        this.note = note;
    }

    // Getter - Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Banh getBanh() {
        return banh;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Booking: ID=" + id +
               ", Customer=" + customer.getName() +
               ", Banh=" + banh.getName() +
               ", Quantity=" + quantity +
               ", Date=" + bookingDate +
               ", Note=" + note;
    }
}

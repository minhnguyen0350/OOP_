package model;

public class Bill {
    private int id;             // billId
    private Booking booking;    // booking liên kết
    private float totalPrice;   // tổng tiền

    // Constructor đầy đủ
    public Bill(int id, Booking booking, float totalPrice) {
        this.id = id;
        this.booking = booking;
        this.totalPrice = totalPrice;
    }

    // Constructor khi thêm mới (chưa có id)
    public Bill(Booking booking, float totalPrice) {
        this.booking = booking;
        this.totalPrice = totalPrice;
    }

    // Getter - Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Hiển thị thông tin hóa đơn
    @Override
    public String toString() {
        return "Bill ID=" + id +
               ", BookingID=" + (booking != null ? booking.getId() : "null") +
               ", TotalPrice=" + totalPrice;
    }
}

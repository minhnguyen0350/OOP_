package dao;

import model.Booking;
import model.Banh;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class BookingDAO extends DAO {

    public BookingDAO() {
        super();
    }

    // Thêm booking mới, trả về id của booking
    public int insertBooking(int customerId, int banhId, int quantity, String note) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO booking (customerId, banhId, quantity, note) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customerId);
            ps.setInt(2, banhId);
            ps.setInt(3, quantity);
            ps.setString(4, note);

            int cnt = ps.executeUpdate();
            if (cnt > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);  // trả về bookId
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    // Lấy danh sách booking theo khách hàng
    public ArrayList<Booking> getBookingByCustomer(int customerId) {
        ArrayList<Booking> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT b.bookId, b.quantity, b.bookingDate, b.note, " +
                         "banh.banhId, banh.banhName, banh.price, banh.sale, banh.rate, banh.description, banh.detail " +
                         "FROM booking b " +
                         "INNER JOIN banhs banh ON b.banhId = banh.banhId " +
                         "WHERE b.customerId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Banh banh = new Banh(
                        rs.getInt("banhId"),
                        rs.getString("banhName"),
                        rs.getFloat("price"),
                        rs.getFloat("sale"),
                        null, // hình ảnh lấy sau từ BanhDAO
                        rs.getInt("rate"),
                        rs.getString("description"),
                        rs.getString("detail")
                );

                Customer cus = new Customer("", "", "", "");
                cus.setId(customerId);

                Booking bk = new Booking(
                        rs.getInt("bookId"),
                        cus,
                        banh,
                        rs.getInt("quantity"),
                        rs.getTimestamp("bookingDate"),
                        rs.getString("note")
                );
                list.add(bk);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}

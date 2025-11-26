package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Customer;

public class CustomerDAO extends DAO {

    public CustomerDAO() {
        super();
    }

    // Thêm khách hàng mới, trả về id của khách hàng vừa thêm
    public int insertCustomer(Customer cus) {
        try {
            String sql = "INSERT INTO customers (customerName, email, tel, address) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cus.getName());
            ps.setString(2, cus.getEmail());
            ps.setString(3, cus.getTel());
            ps.setString(4, cus.getAddress());

            int cnt = ps.executeUpdate();
            if (cnt > 0) {
                // Lấy ID vừa insert
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1; // nếu thất bại
    }

    // Lấy khách hàng theo id
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE customerId = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer cus = new Customer(
                        rs.getString("customerName"),
                        rs.getString("email"),
                        rs.getString("tel"),
                        rs.getString("address")
                );
                cus.setId(rs.getInt("customerId"));
                return cus;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

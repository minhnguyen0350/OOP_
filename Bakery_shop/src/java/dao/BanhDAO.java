package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Banh;

public class BanhDAO extends DAO {

    public BanhDAO() {
        super();
    }

    // ================================
    // LẤY TẤT CẢ BÁNH + ẢNH ĐẠI DIỆN
    // ================================
    public ArrayList<Banh> getAllBanh() {
        String sql = """
            SELECT b.*, bi.imgURL
            FROM banhs b
            LEFT JOIN (
                SELECT banhId, MIN(imgURL) AS imgURL
                FROM banhImages
                GROUP BY banhId
            ) bi ON b.banhId = bi.banhId
            """;

        ArrayList<Banh> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ArrayList<String> imgs = new ArrayList<>();
                imgs.add(rs.getString("imgURL")); // ảnh đại diện

                Banh b = new Banh(
                        rs.getInt("banhId"),
                        rs.getString("banhName"),
                        rs.getFloat("price"),
                        rs.getFloat("sale"),
                        imgs,
                        rs.getInt("rate"),
                        rs.getString("description"),
                        rs.getString("detail")
                );
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================================
    // LẤY CHI TIẾT 1 BÁNH THEO ID
    // ================================
    public Banh getBanhById(int id) {
        String sql = "SELECT * FROM banhs WHERE banhId = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ArrayList<String> imgs = getImages(rs.getInt("banhId"));
                    return new Banh(
                            rs.getInt("banhId"),
                            rs.getString("banhName"),
                            rs.getFloat("price"),
                            rs.getFloat("sale"),
                            imgs,
                            rs.getInt("rate"),
                            rs.getString("description"),
                            rs.getString("detail")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================================
    // LẤY CHI TIẾT 1 BÁNH THEO TÊN
    // ================================
    public Banh getBanhByName(String name) {
        String sql = "SELECT * FROM banhs WHERE banhName = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ArrayList<String> imgs = getImages(rs.getInt("banhId"));
                    return new Banh(
                            rs.getInt("banhId"),
                            rs.getString("banhName"),
                            rs.getFloat("price"),
                            rs.getFloat("sale"),
                            imgs,
                            rs.getInt("rate"),
                            rs.getString("description"),
                            rs.getString("detail")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================================
    // LẤY DANH SÁCH ẢNH THEO ID
    // ================================
    public ArrayList<String> getImages(int banhId) {
        String sql = "SELECT imgURL FROM banhImages WHERE banhId = ?";
        ArrayList<String> imgs = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, banhId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    imgs.add(rs.getString("imgURL"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgs;
    }
}

package DAO;

import DTO.TaiKhoanDTO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import DBConnect.Connector;

public class QuanLyTaiKhoanDAO {

    private Connection conn;

    public QuanLyTaiKhoanDAO() {
        conn = Connector.getConnection();
    }

    public ArrayList<TaiKhoanDTO> readDB() {
        ArrayList<TaiKhoanDTO> dstk = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet r = stmt.executeQuery()) {

            while (r.next()) {
                String ten = r.getString("TenTK");
                String pass = r.getString("MatKhau");
                String manv = r.getString("MaNV");
                String maquyen = r.getString("MaQuyen");

                dstk.add(new TaiKhoanDTO(ten, pass, manv, maquyen));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng tài khoản");
            ex.printStackTrace();
        }

        return dstk;
    }

    public boolean add(TaiKhoanDTO tk) {
        String sql = "INSERT INTO taikhoan (TenTK, MatKhau, MaNV, MaQuyen) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tk.getUsername());
            stmt.setString(2, tk.getPassword());
            stmt.setString(3, tk.getMaNV());
            stmt.setString(4, tk.getMaQuyen());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể thêm tài khoản."+ ex.getMessage());
            ex.printStackTrace();
        }

        return false;
    }

    public boolean delete(String username) {
        String sql = "DELETE FROM taikhoan WHERE TenTK = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể xóa tài khoản.");
            ex.printStackTrace();
        }

        return false;
    }

    public boolean update(String username, String pass, String maNV, String maQuyen) {
        String sql = "UPDATE taikhoan SET MatKhau = ?, MaNV = ?, MaQuyen = ? WHERE TenTK = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pass);
            stmt.setString(2, maNV);
            stmt.setString(3, maQuyen);
            stmt.setString(4, username);

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể cập nhật tài khoản.");
            ex.printStackTrace();
        }

        return false;
    }

    public void close() {
        Connector.closeConnection();
    }
}

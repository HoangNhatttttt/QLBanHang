/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.LoginDTO;
/**
 *
 * @author 0388153585
 */
import DBConnect.Connector;
import java.sql.*;

public class LoginDAO {

    public String checkLogin(String tenTK, String matKhau) {
        String query = "SELECT MaQuyen, Ho, Ten FROM taikhoan "
                + "JOIN nhanvien ON taikhoan.MaNV=nhanvien.MaNV "
                + " WHERE taikhoan.TenTK = ? AND taikhoan.MatKhau = ?";
        try (Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, tenTK);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String maQuyen = rs.getString("MaQuyen");
                String ho = rs.getString("Ho");
                String ten = rs.getString("Ten");
                return maQuyen + "-" + ho + " " + ten;  // Trả về dạng: 1-Nguyễn Văn A
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""; 
    }
}


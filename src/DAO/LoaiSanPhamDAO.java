/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

import DBConnect.Connector;
import DTO.LoaiSanPhamDTO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class LoaiSanPhamDAO {
    private Connection conn;

    public LoaiSanPhamDAO() {
        // Kết nối DB – sửa thông tin theo cấu hình thật của bạn
        conn = Connector.getConnection();
    }

    public ArrayList<LoaiSanPhamDTO> getAll() {
        ArrayList<LoaiSanPhamDTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM loaisp";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ds.add(new LoaiSanPhamDTO(
                        rs.getString("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getString("MoTa")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean insert(LoaiSanPhamDTO lsp) {
        String sql = "INSERT INTO loaisp (MaLoai, TenLoai, MoTa) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, lsp.getMaLoai());
            pst.setString(2, lsp.getTenLoai());
            pst.setString(3, lsp.getMoTa());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean update(LoaiSanPhamDTO lsp) {
        String sql = "UPDATE loaisp SET TenLoai = ?, MoTa = ? WHERE MaLoai = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, lsp.getTenLoai());
            pst.setString(2, lsp.getMoTa());
            pst.setString(3, lsp.getMaLoai());
            return pst.executeUpdate() > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(String maLoai) {
        String sql = "DELETE FROM loaisp WHERE MaLoai = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maLoai);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
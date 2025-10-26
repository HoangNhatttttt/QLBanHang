package DAO;

import DBConnect.Connector;
import DTO.SanPhamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BanHangDAO {
    
    public BanHangDAO() {
    }
    
    // Cập nhật số lượng sản phẩm sau khi bán
    public boolean capNhatSoLuongSanPham(String maSP, int soLuongMua) {
        try {
            Connection conn = Connector.getConnection();
            
            // Lấy số lượng hiện tại
            String querySL = "SELECT SoLuong FROM sanpham WHERE MaSP = ?";
            PreparedStatement psSL = conn.prepareStatement(querySL);
            psSL.setString(1, maSP);
            ResultSet rs = psSL.executeQuery();
            
            if (rs.next()) {
                int soLuongHienTai = rs.getInt("SoLuong");
                int soLuongMoi = soLuongHienTai - soLuongMua;
                
                if (soLuongMoi >= 0) {
                    // Cập nhật số lượng mới
                    String queryUpdate = "UPDATE sanpham SET SoLuong = ? WHERE MaSP = ?";
                    PreparedStatement psUpdate = conn.prepareStatement(queryUpdate);
                    psUpdate.setInt(1, soLuongMoi);
                    psUpdate.setString(2, maSP);
                    int kq = psUpdate.executeUpdate();
                    
                    return kq > 0;
                }
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật số lượng: " + e.getMessage());
            return false;
        }
    }
    
    // Tạo mã hóa đơn mới
    public String taoMaHoaDonMoi() {
        String maHD = "";
        try {
            Connection conn = Connector.getConnection();
            
            // Lấy mã hóa đơn cuối cùng
            String query = "SELECT MAX(MaHD) AS MaHDCuoi FROM hoadon";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                String maHDCuoi = rs.getString("MaHDCuoi");
                if (maHDCuoi == null) {
                    maHD = "HD001";
                } else {
                    // Tạo mã HD mới tăng 1 đơn vị
                    int soHD = Integer.parseInt(maHDCuoi.substring(2)) + 1;
                    maHD = String.format("HD%03d", soHD);
                }
            } else {
                maHD = "HD001";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi tạo mã hóa đơn: " + e.getMessage());
        }
        
        return maHD;
    }
} 
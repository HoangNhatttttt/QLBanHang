package DAO;

import DTO.KhachHangDTO;
import java.sql.*;
import java.util.ArrayList;

public class KhachHangDAO {
    Connection conn;

    public KhachHangDAO() {
        conn = Connector.getConnection();
    }
    public boolean updateKhachHang(KhachHangDTO kh) {
        
            String sql = "UPDATE khachhang SET Ho = ?, Ten = ?, DiaChi = ?, SDT = ? WHERE MaKH = ?";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, kh.getHo());
                stmt.setString(2, kh.getTen());
                stmt.setString(3, kh.getDiaChi());
                stmt.setString(4, kh.getSDT());
                stmt.setString(5, kh.getMaKH());
                return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //lấy tất cả khách hàng và trả lại dạng DTO của Khách
    public ArrayList<KhachHangDTO> getAllKhachHang() {
        ArrayList<KhachHangDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM khachhang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangDTO kh = new KhachHangDTO(
                    rs.getString("MaKH"),
                    rs.getString("Ho"),
                    rs.getString("Ten"),
                    rs.getString("DiaChi"),
                    rs.getString("SDT")
                );
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // kiếm bởi ID và trả lại dạng DTO của khách
    public KhachHangDTO getKhachHangByID(int MaKH) {
        KhachHangDTO kh = null;
        String sql = "SELECT * FROM khachhang WHERE MaKH = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, MaKH);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kh = new KhachHangDTO(
                    rs.getString("MaKH"),
                    rs.getString("Ho"),
                    rs.getString("Ten"),
                    rs.getString("DiaChi"),
                    rs.getString("SDT")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }
    ////nhận vào DTO KhachHang và nhập vô DB với ID mới là +1 của id trước đó
    public String insertKhachHang(KhachHangDTO kh) {
        String getMaxIdSQL = "SELECT MAX(CAST(MaKH AS UNSIGNED)) FROM khachhang";
        String insertSQL = "INSERT INTO khachhang (MaKH, Ho, Ten, Diachi, SDT) VALUES (?, ?, ?, ?, ?)";

        try {
            int newId = 1;
            PreparedStatement psMax = conn.prepareStatement(getMaxIdSQL);
            ResultSet rs = psMax.executeQuery();
            if (rs.next()) {
                newId = rs.getInt(1) + 1;
            }

            String generatedMaKH = String.valueOf(newId);
            PreparedStatement psInsert = conn.prepareStatement(insertSQL);
            psInsert.setString(1, generatedMaKH);
            psInsert.setString(2, kh.getHo());
            psInsert.setString(3, kh.getTen());
            psInsert.setString(4, kh.getDiaChi());
            psInsert.setString(5, kh.getSDT());

            int rows = psInsert.executeUpdate();
            return rows > 0 ? generatedMaKH : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Delete an employee by MaNV
    public boolean deleteKhachHang(String MaKH) {
        String sql = "DELETE FROM khachhang WHERE MaKH = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, MaKH);  // Use setString for MaKH

            int row = ps.executeUpdate();
            return row > 0;  // Return true if deletion is successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if no rows were deleted or an error occurred
    }

}

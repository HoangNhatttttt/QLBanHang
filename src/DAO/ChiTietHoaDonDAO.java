package DAO;

import DTO.ChiTietHoaDonDTO;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietHoaDonDAO {
    private Connection conn;

    public ChiTietHoaDonDAO() {
        conn = Connector.getConnection();
    }

    public ArrayList<ChiTietHoaDonDTO> getAllChiTietHoaDon() {
        ArrayList<ChiTietHoaDonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitiethoadon";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ChiTietHoaDonDTO ct = new ChiTietHoaDonDTO(
                    rs.getString("MaHD"),
                    rs.getString("MaSP"),
                    rs.getInt("SoLuong"),
                    rs.getDouble("DonGia"),
                    rs.getDouble("ThanhTien")
                );
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertChiTietHoaDon(ChiTietHoaDonDTO ct) {
        String sql = "INSERT INTO chitiethoadon (MaHD, MaSP, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ct.getMaHD());
            ps.setString(2, ct.getMaSP());
            ps.setInt(3, ct.getSoLuong());
            ps.setDouble(4, ct.getDonGia());
            ps.setDouble(5, ct.getThanhTien());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteChiTietHoaDon(String MaHD, String MaSP) {
        String sql = "DELETE FROM chitiethoadon WHERE MaHD = ? AND MaSP = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, MaHD);
            ps.setString(2, MaSP);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateChiTietHoaDon(ChiTietHoaDonDTO ct) {
        String sql = "UPDATE chitiethoadon SET SoLuong = ?, DonGia = ?, ThanhTien = ? WHERE MaHD = ? AND MaSP = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ct.getSoLuong());
            ps.setDouble(2, ct.getDonGia());
            ps.setDouble(3, ct.getThanhTien());
            ps.setString(4, ct.getMaHD());
            ps.setString(5, ct.getMaSP());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

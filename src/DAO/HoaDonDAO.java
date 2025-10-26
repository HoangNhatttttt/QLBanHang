package DAO;

import DTO.HoaDonDTO;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HoaDonDAO {
    private Connection conn;

    public HoaDonDAO() {
        conn = Connector.getConnection();
    }

    // Insert a new HoaDon and return its MaHD
    public String insertHoaDon(HoaDonDTO hd) {
        String getMaxIdSQL = "SELECT MAX(CAST(MaHD AS UNSIGNED)) FROM hoadon";
        String insertSQL = "INSERT INTO hoadon (MaHD, MaKH, MaNV, NgayLapHD, TongTien) VALUES (?, ?, ?, ?, ?)";

        try {
            int newId = 1;
            PreparedStatement psMax = conn.prepareStatement(getMaxIdSQL);
            ResultSet rs = psMax.executeQuery();
            if (rs.next()) {
                newId = rs.getInt(1) + 1;
            }

            String generatedMaHD = String.valueOf(newId);
            PreparedStatement psInsert = conn.prepareStatement(insertSQL);
            psInsert.setString(1, generatedMaHD);
            psInsert.setString(2, hd.getMaKH());
            psInsert.setString(3, hd.getMaNV());
            psInsert.setTimestamp(4, Timestamp.valueOf(hd.getNgayLapHD()));
            psInsert.setBigDecimal(5, hd.getTongTien());

            int rows = psInsert.executeUpdate();
            return rows > 0 ? generatedMaHD : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all HoaDon
    public ArrayList<HoaDonDTO> getAllHoaDon() {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadon";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO(
                    rs.getString("MaHD"),
                    rs.getString("MaKH"),
                    rs.getString("MaNV"),
                    rs.getTimestamp("NgayLapHD").toLocalDateTime(),
                    rs.getBigDecimal("TongTien")
                );
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get HoaDon by ID
    public HoaDonDTO getHoaDonByID(String MaHD) {
        String sql = "SELECT * FROM hoadon WHERE MaHD = ?";
        HoaDonDTO hd = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, MaHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hd = new HoaDonDTO(
                    rs.getString("MaHD"),
                    rs.getString("MaKH"),
                    rs.getString("MaNV"),
                    rs.getTimestamp("NgayLapHD").toLocalDateTime(),
                    rs.getBigDecimal("TongTien")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hd;
    }

    // Update an existing HoaDon
    public boolean updateHoaDon(HoaDonDTO hd) {
        String sql = "UPDATE hoadon SET MaKH = ?, MaNV = ?, NgayLapHD = ?, TongTien = ? WHERE MaHD = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hd.getMaKH());
            ps.setString(2, hd.getMaNV());
            ps.setTimestamp(3, Timestamp.valueOf(hd.getNgayLapHD()));
            ps.setBigDecimal(4, hd.getTongTien());
            ps.setString(5, hd.getMaHD());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete HoaDon by MaHD
    public boolean deleteHoaDon(String MaHD) {
        String sql = "DELETE FROM hoadon WHERE MaHD = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, MaHD);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

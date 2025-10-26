package DAO;

import DTO.NhanVienDTO;
import java.sql.*;
import java.util.ArrayList;

public class NhanVienDAO {
    private Connection conn;

    public NhanVienDAO() {
        conn = Connector.getConnection();
    }
    public boolean updateNhanVien(NhanVienDTO nv) {
        
            String sql = "UPDATE nhanvien SET Ho = ?, Ten = ?, LuongThang = ?, TrangThai = ? WHERE MaNV = ?";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, nv.getHo());
                stmt.setString(2, nv.getTen());
                stmt.setDouble(3, nv.getLuongThang());
                stmt.setInt(4, nv.getTrangThai());
                stmt.setString(5, nv.getMaNV());
                return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Get all employees
    public ArrayList<NhanVienDTO> getAllNhanVien() {
        ArrayList<NhanVienDTO> nvList = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(
                    rs.getString("MaNV"),
                    rs.getString("Ho"),
                    rs.getString("Ten"),
                    rs.getDouble("LuongThang"),
                    rs.getInt("TrangThai")
                );
                nvList.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nvList;
    }

    // Get employee by MaNV
    public NhanVienDTO getNhanVienByID(String MaNV) {  
        String sql = "SELECT * FROM nhanvien WHERE MaNV = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, MaNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new NhanVienDTO(
                    rs.getString("MaNV"),
                    rs.getString("Ho"),
                    rs.getString("Ten"),
                    rs.getDouble("LuongThang"),
                    rs.getInt("TrangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Insert a new employee
    public String insertNhanVien(NhanVienDTO nv) {
        String getMaxIdSQL = "SELECT MAX(CAST(MaNV AS UNSIGNED)) FROM nhanvien";
        String insertSQL = "INSERT INTO nhanvien (MaNV, Ho, Ten, LuongThang, TrangThai) VALUES (?, ?, ?, ?, ?)";

        try {
            int newId = 1;
            PreparedStatement psMax = conn.prepareStatement(getMaxIdSQL);
            ResultSet rs = psMax.executeQuery();
            if (rs.next()) {
                newId = rs.getInt(1) + 1;
            }

            String generatedMaNV = String.valueOf(newId);
            PreparedStatement psInsert = conn.prepareStatement(insertSQL);
            psInsert.setString(1, generatedMaNV);
            psInsert.setString(2, nv.getHo());
            psInsert.setString(3, nv.getTen());
            psInsert.setDouble(4, nv.getLuongThang());
            psInsert.setInt(5, nv.getTrangThai());

            int rows = psInsert.executeUpdate();
            return rows > 0 ? generatedMaNV : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    // Delete an employee by MaNV
    public boolean deleteNhanVien(String MaNV) {
        String sql = "DELETE FROM nhanvien WHERE MaNV = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, MaNV);  // Use setString for MaNV

            int row = ps.executeUpdate();
            return row > 0;  // Return true if deletion is successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if no rows were deleted or an error occurred
    }

    // Test ground to check functionality
    public static void main(String[] args) {
        NhanVienDAO nv = new NhanVienDAO();
        NhanVienDTO bro = nv.getNhanVienByID("1");  
        System.out.println(bro);
    }
}

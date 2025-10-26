

package DAO;

import DBConnect.Connector;
import DTO.SanPhammDTO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class SanPhammDAO {
    private Connection conn;

    public SanPhammDAO(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<SanPhammDTO> getDanhSachSanPham() {
        ArrayList<SanPhammDTO> ds = new ArrayList<>();
        String sql = "SELECT sanpham.*, loaisp.TenLoai FROM sanpham " +
                     "JOIN loaisp ON sanpham.MaLoai = loaisp.MaLoai";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                SanPhammDTO sp = new SanPhammDTO();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setDonGia(rs.getDouble("DonGia"));
                sp.setDonVi(rs.getString("DonVi"));
                sp.setMaLoai(rs.getString("MaLoai"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                sp.setTenLoai(rs.getString("TenLoai"));

                ds.add(sp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn sản phẩm: " + e.getMessage());
        }

        return ds;
    }

    public boolean insertSanPham(SanPhammDTO sp) throws SQLException {
        String sql = "INSERT INTO sanpham (MaSP, TenSP, SoLuong, DonGia, DonVi, MaLoai, HinhAnh) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, sp.getMaSP());
        ps.setString(2, sp.getTenSP());
        ps.setInt(3, sp.getSoLuong());
        ps.setDouble(4, sp.getDonGia());
        ps.setString(5, sp.getDonVi());
        ps.setString(6, sp.getMaLoai());
        ps.setString(7, sp.getHinhAnh());

        return ps.executeUpdate() > 0;
    }

    public boolean updateSanPham(SanPhammDTO sp) throws SQLException {
        String sql = "UPDATE sanpham SET TenSP=?, SoLuong=?, DonGia=?, DonVi=?, MaLoai=?, HinhAnh=? WHERE MaSP=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, sp.getTenSP());
        ps.setInt(2, sp.getSoLuong());
        ps.setDouble(3, sp.getDonGia());
        ps.setString(4, sp.getDonVi());
        ps.setString(5, sp.getMaLoai());
        ps.setString(6, sp.getHinhAnh());
        ps.setString(7, sp.getMaSP());

        return ps.executeUpdate() > 0;
    }

    public boolean deleteSanPham(String maSP) throws SQLException {
        String sql = "DELETE FROM sanpham WHERE MaSP=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maSP);
        return ps.executeUpdate() > 0;
    }
}

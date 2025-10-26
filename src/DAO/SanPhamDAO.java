

package DAO;

import DBConnect.Connector;
import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SanPhamDAO {

    public ArrayList<SanPhamDTO> docDS_SP() {
        ArrayList<SanPhamDTO> dsSP = new ArrayList<>();

        try {
            String qry = "SELECT * FROM sanpham";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.MaSP = rs.getInt("MaSP");
                sp.TenSP = rs.getString("TenSP");
                sp.SoLuong = rs.getInt("SoLuong");
                sp.DonGia = rs.getDouble("DonGia");
                sp.DonVi = rs.getString("DonVi");
                sp.MaLoai = rs.getInt("MaLoai");
                
                dsSP.add(sp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return dsSP;
    }

    public void themSP(SanPhamDTO sp) {
        try {
            String qry = "INSERT INTO sanpham (MaSP, TenSP, SoLuong, DonGia, DonVi, MaLoai) VALUES (?, ?, ?, ?, ?, ?)";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setInt(1, sp.MaSP);            // MaSP là int
            stmt.setString(2, sp.TenSP);        // TenSP là String
            stmt.setInt(3, sp.SoLuong);         // SoLuong là int
            stmt.setDouble(4, sp.DonGia);       // DonGia là double
            stmt.setString(5, sp.DonVi);        // DonVi là String
            stmt.setInt(6, sp.MaLoai);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm sản phẩm: " + ex.getMessage());
        }
    }

    public void suaSP(SanPhamDTO sp) {
        try {
            String qry = "UPDATE sanpham SET TenSP=?, SoLuong=?, DonGia=?, DonVi=?, MaLoai=? WHERE MaSP=?";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setInt(1, sp.MaSP);            // MaSP là int
            stmt.setString(2, sp.TenSP);        // TenSP là String
            stmt.setInt(3, sp.SoLuong);         // SoLuong là int
            stmt.setDouble(4, sp.DonGia);       // DonGia là double
            stmt.setString(5, sp.DonVi);        // DonVi là String
            stmt.setInt(6, sp.MaLoai);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật sản phẩm: " + ex.getMessage());
        }
    }

    public void xoaSP(String maSP) {
        try {
            String qry = "DELETE FROM sanpham WHERE MaSP=?";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, maSP);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xoá sản phẩm: " + ex.getMessage());
        }
    }
    public ArrayList docChiTietSP(String maSP){
        ArrayList dsCTSP= new ArrayList<ChiTietSanPhamDTO>();
        try{
            String qry="SELECT MaSP, TenSP, TenLoai, DonGia, HinhAnh FROM sanpham " +
            "JOIN loaisp ON loaisp.MaLoai=sanpham.MaLoai " +
            "WHERE MaSP= ?";
            Connection conn= Connector.getConnection();
            PreparedStatement stmt=conn.prepareStatement(qry);
            stmt.setString(1, maSP);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                ChiTietSanPhamDTO ctsp = new ChiTietSanPhamDTO();
                ctsp.maSP=rs.getString(1);
                ctsp.tenSP=rs.getString(2);
                ctsp.tenLoaiSP=rs.getString(3);
                ctsp.donGia=rs.getString(4);
                ctsp.hinhAnh=rs.getString(5);
                dsCTSP.add(ctsp);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return dsCTSP;
    }
    public String getMaSpByTen(String tenSP){
        String maSP="";
        try{
            String qry="SELECT MaSP FROM sanpham WHERE TenSP = ? ";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, tenSP);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                maSP=rs.getString(1);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return maSP;
    }
}

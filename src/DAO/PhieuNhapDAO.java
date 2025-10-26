/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.*;
import DBConnect.Connector;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author 0388153585
 */
public class PhieuNhapDAO {

    public PhieuNhapDAO() {
    }
    public ArrayList docDS_PN(){
        ArrayList dsPN = new ArrayList<PhieuNhapDTO>();
        
        try {
            String qry="SELECT * FROM phieunhaphang";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt= conn.prepareStatement(qry);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                PhieuNhapDTO pn= new PhieuNhapDTO();
                pn.maPN=rs.getString(1);
                pn.maNV=rs.getString(2);
                pn.maNCC=rs.getString(3);
                pn.ngayNhap=rs.getString(4);
                pn.gioNhap=rs.getString(5);
                pn.tongTien=rs.getString(6);
                dsPN.add(pn);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return dsPN;
    }
    public ArrayList docChiTiet_PN(String maPN){
        ArrayList dsCTPN = new ArrayList<ChiTietPhieuNhapDTO>();
        
        try {
            String qry="SELECT ctpn.MaPN, nv.Ho, nv.Ten, ncc.TenNCC, sp.TenSP, ctpn.SoLuong, ctpn.DonGia, ctpn.ThanhTien " +
                     "FROM chitietphieunhap ctpn " +
                     "JOIN phieunhaphang pn ON ctpn.MaPN = pn.MaPN " +
                     "JOIN nhanvien nv ON pn.MaNV = nv.MaNV " +
                     "JOIN nhacungcap ncc ON pn.MaNCC = ncc.MaNCC " +
                     "JOIN sanpham sp ON ctpn.MaSP = sp.MaSP " +
                     "WHERE ctpn.MaPN = ?";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt= conn.prepareStatement(qry);
            stmt.setString(1, maPN);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                ChiTietPhieuNhapDTO pn= new ChiTietPhieuNhapDTO();
                pn.maPN=rs.getString(1);
                pn.ho=rs.getString(2);
                pn.ten=rs.getString(3);
                pn.tenNCC=rs.getString(4);
                pn.tenSP=rs.getString(5);
                pn.soLuong=rs.getString(6);
                pn.donGia=rs.getString(7);
                pn.thanhTien=rs.getString(8);
                dsCTPN.add(pn);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return dsCTPN;
    }
    public String getLastMaPN(){
        String lastMaPN="";
        try {
            String qry="SELECT MaPN from phieunhaphang "
                + "ORDER BY CAST(SUBSTRING(MaPN, 3) AS UNSIGNED) DESC "
                + "LIMIT 1";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt= conn.prepareStatement(qry);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                lastMaPN=rs.getString("MaPN");
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return lastMaPN;
    }
    
    //hàm lấy mã nhà cung cấp từ tên
    public String getMaNCCByName(String tenNCC) {
        String maNCC = null;
        try {
            String qry = "SELECT MaNCC FROM nhacungcap WHERE TenNCC = ?";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, tenNCC);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                maNCC = rs.getString("MaNCC");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return maNCC;
    }

    //hàm lấy mã nhân viên từ tên nhân viên
    public String getMaNVByHoTen(String ho, String ten) {
        String maNV = null;
        try {
            Connection conn = Connector.getConnection();
            String sql = "SELECT MaNV FROM nhanvien WHERE Ho = ? AND Ten = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ho);
            stmt.setString(2, ten);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maNV = rs.getString("MaNV");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maNV;
    }
    
    public void themPhieuNhap(PhieuNhapDTO phieuNhap) {
        try {
            String qry = "INSERT INTO phieunhaphang (MaPN, MaNV, MaNCC, NgayNhap, GioNhap, TongTien) VALUES (?, ?, ?, ?, ?, ?)";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, phieuNhap.maPN);
            stmt.setString(2, phieuNhap.maNV);
            stmt.setString(3, phieuNhap.maNCC);
            stmt.setString(4, phieuNhap.ngayNhap);
            stmt.setString(5, phieuNhap.gioNhap);
            stmt.setString(6, phieuNhap.tongTien);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
    }
    
    public void themChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTiet) {
        try {
            String qry = "INSERT INTO chitietphieunhap (MaPN, MaSP, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, chiTiet.maPN);
            stmt.setString(2, chiTiet.maSP);
            stmt.setString(3, chiTiet.soLuong);
            stmt.setString(4, chiTiet.donGia);
            stmt.setString(5, chiTiet.thanhTien);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
    }
    public void capNhatSoLuongSanPham(String maSP, int soLuong) {
        try {
            String qry = "UPDATE sanpham SET SoLuong = SoLuong + ? WHERE MaSP = ?";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setInt(1, soLuong);
            stmt.setString(2, maSP);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
    }
}

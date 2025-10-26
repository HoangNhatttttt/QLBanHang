/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnect.Connector;
import DTO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author 0388153585
 */
public class KhuyenMaiDAO {

    public KhuyenMaiDAO() {
    }
    
    public ArrayList<KhuyenMaiDTO> doc_DSKM(){
        ArrayList dsKM = new ArrayList<KhuyenMaiDTO>();
        try {
            String qry="SELECT * FROM chtrinhkhuyenmai";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt= conn.prepareStatement(qry);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                KhuyenMaiDTO km= new KhuyenMaiDTO();
                km.maKM=rs.getString(1);
                km.tenKM=rs.getString(2);
                km.ngayBD=rs.getString(3);
                km.ngayKT=rs.getString(4);
                
                dsKM.add(km);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return dsKM;
    }
    public ArrayList<ChiTietKMDTO> doc_CTKM(String maKM){
        ArrayList dsCTKM = new ArrayList<ChiTietKMDTO>();
        try {
            String qry="SELECT chtrinhkhuyenmai.MaCTKM, TenKhuyenMai, TenSP, PhanTramGiamGia FROM chtrinhkhuyenmai "
                    + "JOIN chitietkhuyenmai ON chtrinhkhuyenmai.MaCTKM = chitietkhuyenmai.MaCTKM "
                    + "JOIN sanpham ON sanpham.MaSP = chitietkhuyenmai.MaSP WHERE chtrinhkhuyenmai.MaCTKM = ?";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt= conn.prepareStatement(qry);
            stmt.setString(1, maKM);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                ChiTietKMDTO ct= new ChiTietKMDTO();
                ct.maKM=rs.getString(1);
                ct.tenKM=rs.getString(2);
                ct.tenSP=rs.getString(3);
                ct.phanTram=rs.getString(4);
                dsCTKM.add(ct);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return dsCTKM;
    }
    public void themKhuyenMai(KhuyenMaiDTO km) {
        try {
            String qry = "INSERT INTO chtrinhkhuyenmai (MaCTKM, TenKhuyenMai, NgayBatDau, NgayKetThuc) VALUES (?, ?, ?, ?)";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, km.maKM);
            stmt.setString(2, km.tenKM);
            stmt.setString(3, km.ngayBD);
            stmt.setString(4, km.ngayKT);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm khuyến mãi: " + e.getMessage());
        }
    }
    
    public void suaKhuyenMai(KhuyenMaiDTO km) {
        try {
            String qry = "UPDATE chtrinhkhuyenmai SET TenKhuyenMai = ?, NgayBatDau = ?, NgayKetThuc = ? WHERE MaCTKM = ?";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, km.tenKM);
            stmt.setString(2, km.ngayBD);
            stmt.setString(3, km.ngayKT);
            stmt.setString(4, km.maKM);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa khuyến mãi: " + e.getMessage());
        }
    }
    public void xoaKhuyenMai(String maKM) {
        try {
            // Xóa chi tiết khuyến mãi trước
            String qryCT = "DELETE FROM chitietkhuyenmai WHERE MaCTKM = ?";
            Connection conn = Connector.getConnection();
            PreparedStatement stmtCT = conn.prepareStatement(qryCT);
            stmtCT.setString(1, maKM);
            stmtCT.executeUpdate();

            // Xóa chương trình khuyến mãi sau
            String qryKM = "DELETE FROM chtrinhkhuyenmai WHERE MaCTKM = ?";
            PreparedStatement stmtKM = conn.prepareStatement(qryKM);
            stmtKM.setString(1, maKM);
            stmtKM.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật xoá khuyến mãi!");
        }
    }
    public void xoaChiTietKhuyenMai(String maKM, String maSP){
        try {
            String qry = "DELETE FROM chitietkhuyenmai WHERE MaCTKM = ? AND MaSP = ? ";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, maKM);
            stmt.setString(2, maSP);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xoá chi tiết khuyến mãi!");
        }
    }
    public void themChiTietKhuyenMai(ChiTietKMDTO ct) {
        try {
            String qry = "INSERT INTO chitietkhuyenmai (MaCTKM, MaSP, PhanTramGiamGia) VALUES (?, ?, ?)";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, ct.getMaKM());
            stmt.setString(2, ct.getMaSP());
            stmt.setString(3, ct.getPhanTram());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm chi tiết khuyến mãi!");
        }
    }
    public void suaChiTietKhuyenMai(ChiTietKMDTO ct) {
        try {
            String qry = "UPDATE chitietkhuyenmai SET MaSP = ?, PhanTramGiamGia = ? WHERE MaCTKM = ?";
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, ct.getMaSP());
            stmt.setString(2, ct.getPhanTram());
            stmt.setString(3, ct.getMaKM());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa chi tiết khuyến mãi!");
        }
    }   
}

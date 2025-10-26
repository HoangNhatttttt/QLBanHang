/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author 0388153585
 */
import DTO.NhaCungCapDTO;
import DBConnect.Connector;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class NhaCungCapDAO {

    public NhaCungCapDAO() {
    }
    
    public ArrayList docDS_NCC(){
        ArrayList dsNCC = new ArrayList<NhaCungCapDTO>();
        
        try {
            String qry="SELECT * FROM nhacungcap";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt= conn.prepareStatement(qry);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                NhaCungCapDTO ncc= new NhaCungCapDTO();
                ncc.maNCC=rs.getString(1);
                ncc.tenNCC=rs.getString(2);
                ncc.diaChi=rs.getString(3);
                ncc.soDT=rs.getString(4);
                dsNCC.add(ncc);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage());
        }
        return dsNCC;
    }
    public void themNCC (NhaCungCapDTO ncc){
        try{
            String qry="INSERT INTO nhacungcap(MaNCC, TenNCC, DiaChi, SDT) VALUES (?, ?, ?, ?)";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt= conn.prepareStatement(qry);
            stmt.setString(1, ncc.maNCC);
            stmt.setString(2, ncc.tenNCC);
            stmt.setString(3, ncc.diaChi);
            stmt.setString(4, ncc.soDT);
            stmt.executeUpdate();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Lỗi thêm nhà cung cấp!");
        }
    }
    public void suaNCC(NhaCungCapDTO ncc){
        try{
            String qry="UPDATE nhacungcap SET TenNCC=?, DiaChi=?, SDT=? WHERE MaNCC=?";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt= conn.prepareStatement(qry);
            stmt.setString(1, ncc.tenNCC);
            stmt.setString(2, ncc.diaChi);
            stmt.setString(3, ncc.soDT);
            stmt.setString(4, ncc.maNCC);
            stmt.executeUpdate();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật nhà cung cấp!");
        }
    }
    public void xoaNCC(String maNCC){
        try{
            String qry="DELETE FROM nhacungcap WHERE MaNCC=?";
            Connection conn=Connector.getConnection();
            PreparedStatement stmt= conn.prepareStatement(qry);
            stmt.setString(1, maNCC);
            stmt.executeUpdate();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật xoá nhà cung cấp!");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //xoa
    public void xoaNhaCungCap(String maNhaCungCap){
        String qry = "DELETE FROM nhacungcap WHERE MaNCC = ? ";
        try{
            Connection conn = Connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(qry);
            stmt.setString(1, maNhaCungCap);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi xoá nhà cung cấp " + e.getMessage());
        }
    }
    
}

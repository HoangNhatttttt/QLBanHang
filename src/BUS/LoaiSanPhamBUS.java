/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BUS;

import DAO.*;
import DBConnect.Connector;
import DTO.LoaiSanPhamDTO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DBConnect.Connector;

public class LoaiSanPhamBUS {
    private ArrayList<LoaiSanPhamDTO> dsLSP;
    private LoaiSanPhamDAO loaiSanPhamDAO;

    public LoaiSanPhamBUS() {
        loaiSanPhamDAO = new LoaiSanPhamDAO();
        dsLSP = loaiSanPhamDAO.getAll();
    }

    public ArrayList<LoaiSanPhamDTO> getDsLSP() {
        return dsLSP;
    }


    public boolean themLoai(LoaiSanPhamDTO lsp) {
        LoaiSanPhamDAO dao = new LoaiSanPhamDAO();
        return dao.insert(lsp); // bạn phải có hàm insert trong DAO
    }

    public boolean suaLoai(LoaiSanPhamDTO lsp) {
        boolean result = loaiSanPhamDAO.update(lsp);
        if (result) {
            for (int i = 0; i < dsLSP.size(); i++) {
                if (dsLSP.get(i).getMaLoai().equals(lsp.getMaLoai())) {
                    dsLSP.set(i, lsp);
                    break;
                }
            }
        }
        return result;
    }

    
    public boolean xoaLoai(String maLoai) {
        boolean result = loaiSanPhamDAO.delete(maLoai);
        if (result) {
            dsLSP.removeIf(l -> l.getMaLoai().equals(maLoai)); // Xóa trong danh sách nếu thành công
        }
    return result;
    }
    
    public static String getTenLoaiByMa(String maLoai) {
        String tenLoai = "Không xác định";
        String sql = "SELECT TenLoai FROM loaisp WHERE MaLoai = ?";
        
        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, maLoai);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                tenLoai = rs.getString("tenloai");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tenLoai;
    }
}

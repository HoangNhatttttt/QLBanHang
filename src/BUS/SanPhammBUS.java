/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DTO.SanPhammDTO;
import DAO.SanPhammDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SanPhammBUS {
    private SanPhammDAO sanPhammDAO;

    public SanPhammBUS(Connection conn) {
        this.sanPhammDAO = new SanPhammDAO(conn);
    }

    public ArrayList<SanPhammDTO> layDSSanPham() {
        return sanPhammDAO.getDanhSachSanPham();
    }

    public boolean insertSanPham(SanPhammDTO sp) throws SQLException {
        return sanPhammDAO.insertSanPham(sp);
    }

    public boolean updateSanPham(SanPhammDTO sp) throws SQLException {
        return sanPhammDAO.updateSanPham(sp);
    }

    public boolean deleteSanPham(String maSP) throws SQLException {
        return sanPhammDAO.deleteSanPham(maSP);
    }
}
    
    

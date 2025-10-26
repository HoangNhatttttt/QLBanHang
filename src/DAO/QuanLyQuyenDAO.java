/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;



import DTO.QuyenDTO;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import DBConnect.Connector;
public class QuanLyQuyenDAO {

    public QuanLyQuyenDAO() {
    }

    public ArrayList<QuyenDTO> readDB() {
        ArrayList<QuyenDTO> dsq = new ArrayList<>();
        String qry = "SELECT * FROM phanquyen";
        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(qry);
             ResultSet r = ps.executeQuery()) {

            while (r.next()) {
                String maq = r.getString("MaQuyen");
                String tenq = r.getString("TenQuyen");
                String chitietq = r.getString("ChiTietQuyen");
                dsq.add(new QuyenDTO(maq, tenq, chitietq));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng phân quyền");
            ex.printStackTrace();
        }

        return listSort(dsq);
    }

    public ArrayList<QuyenDTO> search(String columnName, String value) {
        ArrayList<QuyenDTO> dsq = new ArrayList<>();
        String qry = "SELECT * FROM phanquyen WHERE " + columnName + " LIKE ?";
        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(qry)) {

            ps.setString(1, "%" + value + "%");
            try (ResultSet r = ps.executeQuery()) {
                while (r.next()) {
                    String maq = r.getString("MaQuyen");
                    String tenq = r.getString("TenQuyen");
                    String chitietq = r.getString("ChiTietQuyen");
                    dsq.add(new QuyenDTO(maq, tenq, chitietq));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng phân quyền");
            ex.printStackTrace();
        }

        return dsq;
    }

    public Boolean add(QuyenDTO q) {
        String qry = "INSERT INTO phanquyen (MaQuyen, TenQuyen, ChiTietQuyen) VALUES (?, ?, ?)";
        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(qry)) {

            ps.setString(1, q.getMaQuyen());
            ps.setString(2, q.getTenQuyen());
            ps.setString(3, q.getChiTietQuyen());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể thêm quyền mới");
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(String maq) {
        String qry = "DELETE FROM phanquyen WHERE MaQuyen = ?";
        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(qry)) {

            ps.setString(1, maq);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể xóa quyền");
            e.printStackTrace();
            return false;
        }
    }

    public Boolean update(String maq, String tenquyen, String chitietquyen) {
        String qry = "UPDATE phanquyen SET TenQuyen = ?, ChiTietQuyen = ? WHERE MaQuyen = ?";
        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(qry)) {

            ps.setString(1, tenquyen);
            ps.setString(2, chitietquyen);
            ps.setString(3, maq);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể cập nhật quyền");
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<QuyenDTO> listSort(ArrayList<QuyenDTO> list) {
        Collections.sort(list, (ob1, ob2) -> {
            int ma1 = Integer.parseInt(ob1.getMaQuyen().replaceAll("\\D", ""));
            int ma2 = Integer.parseInt(ob2.getMaQuyen().replaceAll("\\D", ""));
            return Integer.compare(ma1, ma2);
        });
        return list;
    }

    public void close() {
        Connector.closeConnection();
    }
}

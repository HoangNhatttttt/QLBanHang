/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.QuyenDTO;
import DAO.QuanLyQuyenDAO;
import java.util.ArrayList;

public class QuanLyQuyenBUS {
   
    private ArrayList<QuyenDTO> dsq;
    private QuanLyQuyenDAO qlqDAO ;

    public QuanLyQuyenBUS() {
        qlqDAO = new QuanLyQuyenDAO();
        dsq = qlqDAO.readDB();
    }

    public void readDB() {
        dsq = qlqDAO.readDB();
    }

    public ArrayList<QuyenDTO> getDsq() {
        return dsq;
    }

    public QuyenDTO getQuyen(String maquyen) {
        for (QuyenDTO q : dsq) {
            if (q.getMaQuyen().equalsIgnoreCase(maquyen)) {
                return q;
            }
        }
        return null;
    }

    public String[] getHeaders() {
        return new String[]{"Mã quyền", "Tên quyền", "Chi tiết quyền"};
    }

    public void showConsole() {
        for (QuyenDTO q : dsq) {
            System.out.println(q.getMaQuyen() + " - " + q.getTenQuyen() + " - " + q.getChiTietQuyen());
        }
    }

    public String getNextID() {
        int max = 0;
        for (QuyenDTO q : dsq) {
            try {
                int num = Integer.parseInt(q.getMaQuyen().replaceAll("\\D", ""));
                if (num > max) max = num;
            } catch (NumberFormatException ignored) {}
        }
        return "Q" + (max + 1);
    }

    public boolean add(QuyenDTO q) {
        boolean ok = qlqDAO.add(q);
        if (ok) dsq.add(q);
        return ok;
    }

    public boolean add(String maq, String ten, String chitiet) {
        return add(new QuyenDTO(maq, ten, chitiet));
    }

    public boolean delete(String maq) {
        boolean ok = qlqDAO.delete(maq);
        if (ok) {
            dsq.removeIf(q -> q.getMaQuyen().equalsIgnoreCase(maq));
        }
        return ok;
    }

    public boolean update(String maq, String ten, String chitietquyen) {
        boolean ok = qlqDAO.update(maq, ten, chitietquyen);
        if (ok) {
            for (QuyenDTO q : dsq) {
                if (q.getMaQuyen().equalsIgnoreCase(maq)) {
                    q.setTenQuyen(ten);
                    q.setChiTietQuyen(chitietquyen);
                    break;
                }
            }
        }
        return ok;
    }

    public ArrayList<QuyenDTO> search(String value, String type) {
        ArrayList<QuyenDTO> result = new ArrayList<>();
        value = value.toLowerCase();

        for (QuyenDTO q : dsq) {
            switch (type) {
                case "Tất cả":
                    if (q.getMaQuyen().toLowerCase().contains(value)
                            || q.getTenQuyen().toLowerCase().contains(value)
                            || q.getChiTietQuyen().toLowerCase().contains(value)) {
                        result.add(q);
                    }
                    break;
                case "Mã quyền":
                    if (q.getMaQuyen().toLowerCase().contains(value)) result.add(q);
                    break;
                case "Tên quyền":
                    if (q.getTenQuyen().toLowerCase().contains(value)) result.add(q);
                    break;
                case "Chi tiết quyền":
                    if (q.getChiTietQuyen().toLowerCase().contains(value)) result.add(q);
                    break;
            }
        }

        return result;
    }


}

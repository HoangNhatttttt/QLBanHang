package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;
import java.util.ArrayList;

public class ChiTietHoaDonBUS {
    private ArrayList<ChiTietHoaDonDTO> listChiTiet;
    private ChiTietHoaDonDAO dao;

    public ChiTietHoaDonBUS() {
        dao = new ChiTietHoaDonDAO();
        listChiTiet = dao.getAllChiTietHoaDon();
    }

    public ArrayList<ChiTietHoaDonDTO> getAllChiTietHoaDon() {
        return listChiTiet;
    }

    public boolean addChiTietHoaDon(ChiTietHoaDonDTO ct) {
        boolean success = dao.insertChiTietHoaDon(ct);
        if (success) {
            listChiTiet.add(ct);
        }
        return success;
    }

    public boolean deleteChiTietHoaDon(String MaHD, String MaSP) {
        boolean success = dao.deleteChiTietHoaDon(MaHD, MaSP);
        if (success) {
            listChiTiet.removeIf(ct -> ct.getMaHD().equals(MaHD) && ct.getMaSP().equals(MaSP));
        }
        return success;
    }

    public boolean updateChiTietHoaDon(ChiTietHoaDonDTO updatedCT) {
        boolean success = dao.updateChiTietHoaDon(updatedCT);
        if (success) {
            for (int i = 0; i < listChiTiet.size(); i++) {
                ChiTietHoaDonDTO ct = listChiTiet.get(i);
                if (ct.getMaHD().equals(updatedCT.getMaHD()) && ct.getMaSP().equals(updatedCT.getMaSP())) {
                    listChiTiet.set(i, updatedCT);
                    break;
                }
            }
        }
        return success;
    }

    public void reload() {
        listChiTiet = dao.getAllChiTietHoaDon();
    }
}

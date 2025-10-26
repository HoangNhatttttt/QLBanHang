package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

import java.util.ArrayList;

public class HoaDonBUS {
    private ArrayList<HoaDonDTO> listHoaDon;
    private HoaDonDAO dao;

    public HoaDonBUS() {
        dao = new HoaDonDAO();
        listHoaDon = dao.getAllHoaDon();  // load only once
    }

    // Getter
    public ArrayList<HoaDonDTO> getAllHoaDon() {
        return listHoaDon;
    }

    // Search by MaHD, MaKH, MaNV
    public ArrayList<HoaDonDTO> search(String keyword, String searchOption) {
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        switch (searchOption) {
            case "Mã HĐ":
                for (HoaDonDTO hd : listHoaDon) {
                    if (hd.getMaHD().equalsIgnoreCase(keyword)) {
                        result.add(hd);
                        return result;  // return early
                    }
                }
                break;
            case "Mã KH":
                for (HoaDonDTO hd : listHoaDon) {
                    if (hd.getMaKH().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                }
                break;
            case "Mã NV":
                for (HoaDonDTO hd : listHoaDon) {
                    if (hd.getMaNV().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                }
                break;
        }
        return result;
    }

    // Refresh from DB
    public void reload() {
        listHoaDon = dao.getAllHoaDon();
    }

    // Get by MaHD
    public HoaDonDTO getHoaDonByID(String MaHD) {
        for (HoaDonDTO hd : listHoaDon) {
            if (hd.getMaHD().equals(MaHD)) {
                return hd;
            }
        }
        return null;
    }

    // Add new HoaDon
    public boolean addHoaDon(HoaDonDTO newHD) {
        // ID should not be duplicate unless it's manually set
        if (!newHD.getMaHD().equals("0")) {
            for (HoaDonDTO hd : listHoaDon) {
                if (hd.getMaHD().equals(newHD.getMaHD())) {
                    System.out.println("Hóa đơn với mã " + newHD.getMaHD() + " đã tồn tại.");
                    return false;
                }
            }
        }

        String actualMaHD = dao.insertHoaDon(newHD);
        if (actualMaHD != null) {
            newHD.setMaHD(actualMaHD);
            listHoaDon.add(newHD);
            return true;
        }
        return false;
    }

    // Update
    public boolean updateHoaDon(HoaDonDTO updatedHD) {
        boolean success = dao.updateHoaDon(updatedHD);
        if (success) {
            for (int i = 0; i < listHoaDon.size(); i++) {
                if (listHoaDon.get(i).getMaHD().equals(updatedHD.getMaHD())) {
                    listHoaDon.set(i, updatedHD);
                    break;
                }
            }
        }
        return success;
    }

    // Delete
    public boolean deleteHoaDon(String MaHD) {
        boolean success = dao.deleteHoaDon(MaHD);
        if (success) {
            listHoaDon.removeIf(hd -> hd.getMaHD().equals(MaHD));
        }
        return success;
    }
}

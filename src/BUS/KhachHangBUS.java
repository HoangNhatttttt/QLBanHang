package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

public class KhachHangBUS {
    private ArrayList<KhachHangDTO> listKhachHang;
    private KhachHangDAO dao;

    public KhachHangBUS() {
        dao = new KhachHangDAO();
        listKhachHang = dao.getAllKhachHang();  // load chỉ một lần
    }

    // Getter
    public ArrayList<KhachHangDTO> getAllKhachHang() {
        return listKhachHang;
    }

    // Search by MaNV, Ten, Ho, diachi, or SDT
    public ArrayList<KhachHangDTO> search(String keyword, String searchOption) {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        switch (searchOption) {
            case "Mã":
                for (KhachHangDTO nv : listKhachHang) {
                    if (nv.getMaKH().equalsIgnoreCase(keyword)) { // MaNV is now a String
                        result.add(nv);
                        return result;  // Return immediately if a match is found
                    }
                }
                break;
            case "Tên":
                for (KhachHangDTO nv : listKhachHang) {
                    if (nv.getTen().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(nv);
                    }
                }
                break;
            case "Họ":
                for (KhachHangDTO nv : listKhachHang) {
                    if (nv.getHo().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(nv);
                    }
                }
                break;
            case "Địa chỉ":
                    for (KhachHangDTO nv : listKhachHang) {
                        if (nv.getDiaChi().toLowerCase().contains(keyword.toLowerCase())) {
                            result.add(nv);
                        }
                    }
                break;
            case "Số điện thoại":
                    for (KhachHangDTO nv : listKhachHang) {
                        if (nv.getSDT().toLowerCase().contains(keyword.toLowerCase())) {
                            result.add(nv);
                        }
                    }
                break;
        }
        return (result != null) ? result : new ArrayList<>();  // Return empty list if no result found
    }
    // kiểm tra nếu sđt tồn tại
    private boolean isSDTExist(String sdt) {
        for (KhachHangDTO kh : listKhachHang) {
            if (kh.getSDT() != null && kh.getSDT().equals(sdt)) {
                return true;
            }
        }
        return false;
    }
    public boolean updateKhachHang(KhachHangDTO updatedKH) {
        boolean success = dao.updateKhachHang(updatedKH);
        if (success) {
            // Update the local list
            for (int i = 0; i < listKhachHang.size(); i++) {
                if (listKhachHang.get(i).getMaKH().equals(updatedKH.getMaKH())) {
                    listKhachHang.set(i, updatedKH);
                    break;
                }
            }
        }
        return success;
    }
 

    // refresh list ở local
    public void reload() {
        listKhachHang = dao.getAllKhachHang();
    }

    //tìm bằng ID
    public KhachHangDTO getKhachHangByID(String MaKH) {
        for (KhachHangDTO kh : listKhachHang) {
            if (kh.getMaKH().equals(MaKH)) {
                return kh;
            }
        }
        return null;
    }
        public boolean addKhachHang(KhachHangDTO newKH) {
        //duplicate check
        if (!newKH.getMaKH().equals("0")) {
            for (KhachHangDTO kh : listKhachHang) {
                if (kh.getMaKH().equals(newKH.getMaKH())) {
                    System.out.println("Khách hàng với mã " + newKH.getMaKH() + " đã tồn tại.");
                    return false;
                }
            }
        }

        // Let DAO insert and get the real MaKH
        String actualMaKH = dao.insertKhachHang(newKH);
        if (actualMaKH != null) {
            newKH.setMaKH(actualMaKH); // Set the generated MaKH
            listKhachHang.add(newKH);   // Add to the local list with correct ID
            return true;
        }
        return false;
    }


    // Delete an employee by MaKH
    public boolean deleteKhachHang(String MaKH) {  // MaKH is now a String
        boolean success = dao.deleteKhachHang(MaKH);
        if (success) {
            // Find and remove the employee from the local list
            listKhachHang.removeIf(nv -> nv.getMaKH().equals(MaKH)); // Use .equals for String comparison

        }
        return success;
    }
}

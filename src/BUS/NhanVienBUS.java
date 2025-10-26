package BUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;

public class NhanVienBUS {
    private ArrayList<NhanVienDTO> listNhanVien;
    private NhanVienDAO dao;

    public NhanVienBUS() {
        dao = new NhanVienDAO();
        listNhanVien = dao.getAllNhanVien();  // Load only once
    }

    // Getter
    public ArrayList<NhanVienDTO> getAllNhanVien() {
        return listNhanVien;
    }

    // Search by MaNV, Ten, Ho, LuongThang, or TrangThai
    public ArrayList<NhanVienDTO> search(String keyword, String searchOption) {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        switch (searchOption) {
            case "Mã":
                for (NhanVienDTO nv : listNhanVien) {
                    if (nv.getMaNV().equalsIgnoreCase(keyword)) { // MaNV is now a String
                        result.add(nv);
                        return result;  // Return immediately if a match is found
                    }
                }
                break;
            case "Tên":
                for (NhanVienDTO nv : listNhanVien) {
                    if (nv.getTen().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(nv);
                    }
                }
                break;
            case "Họ":
                for (NhanVienDTO nv : listNhanVien) {
                    if (nv.getHo().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(nv);
                    }
                }
                break;
            case "Lương tháng":
                try {
                    double luong = Double.parseDouble(keyword);  // Convert keyword to double for comparison
                    for (NhanVienDTO nv : listNhanVien) {
                        if (nv.getLuongThang() == luong) {
                            result.add(nv);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lương tháng phải là số hợp lệ.");
                }
                break;
            case "Trạng thái":
                try {
                    int trangThai = Integer.parseInt(keyword);  // Convert keyword to int for comparison
                    for (NhanVienDTO nv : listNhanVien) {
                        if (nv.getTrangThai() == trangThai) {
                            result.add(nv);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Trạng thái phải là số hợp lệ.");
                }
                break;
        }
        return (result != null) ? result : new ArrayList<>();  // Return empty list if no result found 
    }
    public boolean updateNhanVien(NhanVienDTO updatedNV) {
        boolean success = dao.updateNhanVien(updatedNV);
        if (success) {
            // Update the local list
            for (int i = 0; i < listNhanVien.size(); i++) {
                if (listNhanVien.get(i).getMaNV().equals(updatedNV.getMaNV())) {
                    listNhanVien.set(i, updatedNV);
                    break;
                }
            }
        }
        return success;
    }

    // Refresh the local list of employees
    public void reload() {
        listNhanVien = dao.getAllNhanVien();
    }
    public NhanVienDTO getNhanVienByID(String MaNV) {
        // Iterate over the local list and find the matching employee by MaNV
        for (NhanVienDTO nv : listNhanVien) {
            if (nv.getMaNV().equals(MaNV)) {
                return nv;  // Return the employee if a match is found
            }
        }
        // Return null if no employee with the given MaNV is found
        return null;
    }
    // Add a new employee
    public boolean addNhanVien(NhanVienDTO newNV) {
        //duplicate check
        if (!newNV.getMaNV().equals("0")) {
            for (NhanVienDTO nv : listNhanVien) {
                if (nv.getMaNV().equals(newNV.getMaNV())) {
                    System.out.println("Nhân viên với mã " + newNV.getMaNV() + " đã tồn tại.");
                    return false;
                }
            }
        }

        // Let DAO insert and get the real MaNV
        String actualMaNV = dao.insertNhanVien(newNV);
        if (actualMaNV != null) {
            newNV.setMaNV(actualMaNV); // Set the generated MaNV
            listNhanVien.add(newNV);   // Add to the local list with correct ID
            return true;
        }
        return false;
    }


    // Delete an employee by MaNV
    public boolean deleteNhanVien(String MaNV) {  // MaNV is now a String
        boolean success = dao.deleteNhanVien(MaNV);
        if (success) {
            // Find and remove the employee from the local list
            listNhanVien.removeIf(nv -> nv.getMaNV().equals(MaNV)); // Use .equals for String comparison
        }
        return success;
    }
}

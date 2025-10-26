package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKeDTO;

import java.util.ArrayList;

public class ThongKeBUS {
    private ArrayList<ThongKeDTO> dsThongKe;
    private ThongKeDAO dao;

    public ThongKeBUS() {
        dao = new ThongKeDAO();
        dsThongKe = dao.getAllThongKe();  // Load all data once
    }

    public ArrayList<ThongKeDTO> getAllThongKe() {
        return dsThongKe;
    }
    // Method to filter the data by date range
    public ArrayList<ThongKeDTO> filterByDate(ArrayList<ThongKeDTO> data, String fromDate, String toDate) {
        ArrayList<ThongKeDTO> filteredData = new ArrayList<>();
        
        for (ThongKeDTO dto : data) {
            String ngayLapHD = dto.getHoadon().getNgayLapHD().toLocalDate().toString();
            
            // Check if the date falls within the selected range
            if (ngayLapHD.compareTo(fromDate) >= 0 && ngayLapHD.compareTo(toDate) <= 0) {
                filteredData.add(dto);
            }
        }
        
        return filteredData;
    }


    public void reload() {
        dsThongKe = dao.getAllThongKe();
    }
    
    public ArrayList<ThongKeDTO> searchByMaHD(String maHD) {
        ArrayList<ThongKeDTO> result = new ArrayList<>();
        for (ThongKeDTO tk : dsThongKe) {
            if (tk.getHoadon().getMaHD().equalsIgnoreCase(maHD)) {
                result.add(tk);
            }
        }
        return result;
    }
}
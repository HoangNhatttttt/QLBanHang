
package BUS;
import DTO.*;
import DAO.SanPhamDAO;

import java.util.ArrayList;

public class SanPhamBUS {    
    public static ArrayList<SanPhamDTO> dsSP;
    public static ArrayList<ChiTietSanPhamDTO> dsCTSP;
    
    public SanPhamBUS() {
    }
    public void docDS_SP(){
        SanPhamDAO sp=new SanPhamDAO();    
        dsSP = sp.docDS_SP(); // Luôn đọc lại, không cần kiểm tra null

    }
    public void themSP(SanPhamDTO sp){
        SanPhamDAO sp1=new SanPhamDAO();
        sp1.themSP(sp);
        dsSP.add(sp);
    }
    public void xoaSP(String maSP){
        SanPhamDAO sp2=new SanPhamDAO();
        sp2.xoaSP(maSP);
    }
    public void suaSP(SanPhamDTO sp){
        SanPhamDAO sp3=new SanPhamDAO();
        sp3.suaSP(sp);
    }
    public ArrayList<ChiTietSanPhamDTO> docChiTiet_SP(String maSP){
        SanPhamDAO dao = new SanPhamDAO();
        dsCTSP=dao.docChiTietSP(maSP);
        return dsCTSP;
    }
    public ArrayList<SanPhamDTO> timKiem(String tuKhoa) {
        ArrayList<SanPhamDTO> ketQua = new ArrayList<>();
        if(dsSP==null){
            docDS_SP();
        }
        
        tuKhoa = tuKhoa.toLowerCase().trim();

        for (SanPhamDTO sp : dsSP) {
            if (String.valueOf(sp.getMaSP()).toLowerCase().contains(tuKhoa) ||
                String.valueOf(sp.getMaLoai()).toLowerCase().contains(tuKhoa) ||
                sp.getTenSP().toLowerCase().contains(tuKhoa) ||
                sp.getDonVi().toLowerCase().contains(tuKhoa) ||
                String.valueOf(sp.getSoLuong()).contains(tuKhoa) ||
                String.valueOf(sp.getDonGia()).contains(tuKhoa)) {
                ketQua.add(sp);
            }
        }

        return ketQua;
    }
    public String getMaSpByTen(String tenSP){
        SanPhamDAO dao = new SanPhamDAO();
        String maSP=dao.getMaSpByTen(tenSP);
        return maSP;
    }
}
    

    
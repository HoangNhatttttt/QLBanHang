/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author 0388153585
 */
import DTO.NhaCungCapDTO;
import DAO.NhaCungCapDAO;
import java.util.ArrayList;
public class NhaCungCapBUS {
    public static ArrayList<NhaCungCapDTO> dsNCC; 

    public NhaCungCapBUS() {
    }
    public void docDS_NCC(){
        NhaCungCapDAO ncc=new NhaCungCapDAO();
        if(dsNCC==null) dsNCC = new ArrayList<NhaCungCapDTO>();
        dsNCC=ncc.docDS_NCC();
    }
    public void themNCC(NhaCungCapDTO ncc){
        NhaCungCapDAO ncc1=new NhaCungCapDAO();
        ncc1.themNCC(ncc);
        dsNCC.add(ncc);
    }
    public void xoaNCC(String maNCC){
        NhaCungCapDAO ncc2=new NhaCungCapDAO();
        ncc2.xoaNCC(maNCC);
    }
    public void suaNCC(NhaCungCapDTO ncc){
        NhaCungCapDAO ncc3=new NhaCungCapDAO();
        ncc3.suaNCC(ncc);
    }
    public ArrayList<NhaCungCapDTO> timKiem(String tieuChi, String tuKhoa) {
        ArrayList<NhaCungCapDTO> dsKetQua = new ArrayList<>();

        if (dsNCC == null) return dsKetQua;

        tuKhoa = tuKhoa.toLowerCase();

        for (NhaCungCapDTO ncc : dsNCC) {
            if (tieuChi.equals("Tất cả")) {
                if (ncc.getMaNCC().toLowerCase().contains(tuKhoa) ||
                    ncc.getTenNCC().toLowerCase().contains(tuKhoa) ||
                    ncc.getDiaChi().toLowerCase().contains(tuKhoa) ||
                    ncc.getSoDT().toLowerCase().contains(tuKhoa)) {
                    dsKetQua.add(ncc);
                }
            } else if (tieuChi.equals("Mã NCC") && ncc.getMaNCC().toLowerCase().contains(tuKhoa)) {
                dsKetQua.add(ncc);
            } else if (tieuChi.equals("Tên NCC") && ncc.getTenNCC().toLowerCase().contains(tuKhoa)) {
                dsKetQua.add(ncc);
            } else if (tieuChi.equals("Địa chỉ") && ncc.getDiaChi().toLowerCase().contains(tuKhoa)) {
                dsKetQua.add(ncc);
            } else if (tieuChi.equals("Số điện thoại") && ncc.getSoDT().toLowerCase().contains(tuKhoa)) {
                dsKetQua.add(ncc);
            }
        }

        return dsKetQua;
    }


}

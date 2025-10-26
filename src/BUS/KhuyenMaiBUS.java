/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;
import DTO.*;
import DAO.*;

/**
 *
 * @author 0388153585
 */
public class KhuyenMaiBUS {
    public static ArrayList<KhuyenMaiDTO> dsKM;
    public static ArrayList<ChiTietKMDTO> dsCTKM;

    public KhuyenMaiBUS() {
    }
    public void doc_DSKM(){
        KhuyenMaiDAO dao = new KhuyenMaiDAO();
        dsKM= dao.doc_DSKM();
    }
    public ArrayList<ChiTietKMDTO> doc_CTKM(String maKM){
        KhuyenMaiDAO dao = new KhuyenMaiDAO();
        dsCTKM=dao.doc_CTKM(maKM);
        return dsCTKM;
    }
    public ArrayList<KhuyenMaiDTO> timKiemDSKM(String keyword, String tieuChi) {
        ArrayList<KhuyenMaiDTO> ketQua = new ArrayList<>();

        if (dsKM == null) {
            doc_DSKM(); // Đọc dữ liệu nếu chưa có
        }

        keyword = keyword.toLowerCase();

        for (KhuyenMaiDTO km : dsKM) {
            switch (tieuChi) {
                case "Mã khuyến mãi":
                    if (km.maKM.toLowerCase().contains(keyword)) ketQua.add(km);
                    break;
                case "Tên khuyến mãi":
                    if (km.tenKM.toLowerCase().contains(keyword)) ketQua.add(km);
                    break;
                case "Ngày bắt đầu":
                    if (km.ngayBD.toLowerCase().contains(keyword)) ketQua.add(km);
                    break;
                case "Ngày kết thúc":
                    if (km.ngayKT.toLowerCase().contains(keyword)) ketQua.add(km);
                    break;
                case "Tất cả":
                    if (km.maKM.toLowerCase().contains(keyword) ||
                        km.tenKM.toLowerCase().contains(keyword) ||
                        km.ngayBD.toLowerCase().contains(keyword) ||
                        km.ngayKT.toLowerCase().contains(keyword)) {
                        ketQua.add(km);
                    }
                    break;
            }
        }

        return ketQua;
    }
    public ArrayList<ChiTietKMDTO> timKiemChiTietKM(String keyword, String tieuChi) {
        ArrayList<ChiTietKMDTO> ketQua = new ArrayList<>();

        if (dsCTKM == null) {
            return ketQua;
        }

        keyword = keyword.toLowerCase();

        for (ChiTietKMDTO ct : dsCTKM) {
            switch (tieuChi) {
                case "Tất cả":
                    if (ct.getTenSP().toLowerCase().contains(keyword) ||
                        ct.getPhanTram().toLowerCase().contains(keyword)) {
                        ketQua.add(ct);
                    }
                    break;
                case "Tên sản phẩm":
                    if (ct.getTenSP().toLowerCase().contains(keyword)) {
                        ketQua.add(ct);
                    }
                    break;
                case "Phần trăm":
                    if (ct.getPhanTram().toLowerCase().contains(keyword)) {
                        ketQua.add(ct);
                    }
                    break;
            }
        }

        return ketQua;
    }
    public void themKhuyenMai(KhuyenMaiDTO km){
        KhuyenMaiDAO dao = new KhuyenMaiDAO();
        dao.themKhuyenMai(km);
        dsKM.add(km);
    }
    public void themChiTietKhuyenMai(ChiTietKMDTO ct){
        KhuyenMaiDAO dao = new KhuyenMaiDAO();
        dao.themChiTietKhuyenMai(ct);
    }
    public void suaKhuyenMai(KhuyenMaiDTO km){
        KhuyenMaiDAO dao = new KhuyenMaiDAO();
        dao.suaKhuyenMai(km);
    }
    public void suaChiTietKhuyenMai(ChiTietKMDTO ct){
        KhuyenMaiDAO dao = new KhuyenMaiDAO();
        dao.suaChiTietKhuyenMai(ct);
    }
    public void xoaKhuyenMai(String maKM){
        KhuyenMaiDAO dao = new KhuyenMaiDAO();
        dao.xoaKhuyenMai(maKM);
    }
    public void xoaChiTietKhuyenMai(String maKM, String maSP){
        KhuyenMaiDAO dao = new KhuyenMaiDAO();
        dao.xoaChiTietKhuyenMai(maKM, maSP);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author 0388153585
 */
import DTO.*;
import DAO.*;
import java.util.ArrayList;
public class PhieuNhapBUS {
    public static ArrayList<PhieuNhapDTO> dsPN;
    public static ArrayList<ChiTietPhieuNhapDTO> dsCTPN;

    public PhieuNhapBUS() {
    }
    
    public void docDS_PN(){
        PhieuNhapDAO pn=new PhieuNhapDAO();
        if(dsPN==null) dsPN = new ArrayList<PhieuNhapDTO>();
        dsPN=pn.docDS_PN();
    }
    public ArrayList<ChiTietPhieuNhapDTO> docChiTiet_PN(String maPN){
        PhieuNhapDAO dao = new PhieuNhapDAO();
        dsCTPN = dao.docChiTiet_PN(maPN);
        return dsCTPN;
    }
    public ArrayList<PhieuNhapDTO> timKiem(String keyword, String tieuChi) {
        ArrayList<PhieuNhapDTO> ketQua = new ArrayList<>();
        if (dsPN == null) {
            docDS_PN(); // đọc nếu chưa có
        }

        keyword=keyword.toLowerCase();
        for (PhieuNhapDTO pn : dsPN) {
            switch (tieuChi) {
                case "Mã phiếu nhập":
                    if (pn.maPN.toLowerCase().contains(keyword)) ketQua.add(pn); // Kiểm tra Mã phiếu nhập
                    break;
                case "Mã nhân viên":
                    if (pn.maNV.toLowerCase().contains(keyword)) ketQua.add(pn); // Kiểm tra Mã nhân viên
                    break;
                case "Mã nhà cung cấp":
                    if (pn.maNCC.toLowerCase().contains(keyword)) ketQua.add(pn); // Kiểm tra Mã nhà cung cấp
                    break;
                case "Ngày nhập":
                    if (pn.ngayNhap.toLowerCase().contains(keyword)) ketQua.add(pn); // Kiểm tra Ngày nhập
                    break;
                case "Giờ nhập":
                    if (pn.gioNhap.toLowerCase().contains(keyword)) ketQua.add(pn); // Kiểm tra Giờ nhập
                    break;
                case "Tổng tiền":
                    if (pn.tongTien.toLowerCase().contains(keyword)) ketQua.add(pn); // Kiểm tra Tổng tiền
                    break;
                case "Tất cả":
                    // Kiểm tra tất cả các trường thông tin của phiếu nhập
                    if (pn.maPN.toLowerCase().contains(keyword) ||
                        pn.maNV.toLowerCase().contains(keyword) ||
                        pn.maNCC.toLowerCase().contains(keyword) ||
                        pn.ngayNhap.toLowerCase().contains(keyword) ||
                        pn.gioNhap.toLowerCase().contains(keyword) ||
                        pn.tongTien.toLowerCase().contains(keyword)) {
                        ketQua.add(pn);
                    }
                    break;
            }
        }
        return ketQua;
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> timKiemChiTiet(String keyword, String tieuChi) {
        ArrayList<ChiTietPhieuNhapDTO> ketQua = new ArrayList<>();
        if (dsCTPN == null) return ketQua;

        keyword = keyword.toLowerCase();

        for (ChiTietPhieuNhapDTO ct : dsCTPN) {
            switch (tieuChi) {
                case "Mã phiếu nhập":
                    if (ct.maPN.toLowerCase().contains(keyword)) ketQua.add(ct);
                    break;
                case "Tên nhân viên":
                    if ((ct.ho + " " + ct.ten).toLowerCase().contains(keyword)) ketQua.add(ct);
                    break;
                case "Tên nhà cung cấp":
                    if (ct.tenNCC.toLowerCase().contains(keyword)) ketQua.add(ct);
                    break;
                case "Tên sản phẩm":
                    if (ct.tenSP.toLowerCase().contains(keyword)) ketQua.add(ct);
                    break;
                case "Số lượng":
                    if (ct.soLuong.toLowerCase().contains(keyword)) ketQua.add(ct);
                    break;
                case "Đơn giá":
                    if (ct.donGia.toLowerCase().contains(keyword)) ketQua.add(ct);
                    break;
                case "Thành tiền":
                    if (ct.thanhTien.toLowerCase().contains(keyword)) ketQua.add(ct);
                    break;
                case "Tất cả":
                    if (ct.maPN.toLowerCase().contains(keyword) ||
                        (ct.ho + " " + ct.ten).toLowerCase().contains(keyword) ||
                        ct.tenNCC.toLowerCase().contains(keyword) ||
                        ct.tenSP.toLowerCase().contains(keyword) ||
                        ct.soLuong.toLowerCase().contains(keyword) ||
                        ct.donGia.toLowerCase().contains(keyword)) {
                        ketQua.add(ct);
                    }
                    break;
            }
        }
        return ketQua;
    }


}

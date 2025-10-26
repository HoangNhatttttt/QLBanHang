/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import java.util.ArrayList;
/**
 *
 * @author drunkenpipe
 */
public class ThongKeDTO {
    private HoaDonDTO hoadon;
    private NhanVienDTO nhanvien;
    private KhachHangDTO khachhang;
     private ArrayList<ChiTietHoaDonDTO> dsChiTiet;
    private ArrayList<SanPhamDTO> dsSanPham;
    // Getters and setters

    public HoaDonDTO getHoadon() {
        return hoadon;
    }

    public void setHoadon(HoaDonDTO hoadon) {
        this.hoadon = hoadon;
    }

    public NhanVienDTO getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(NhanVienDTO nhanvien) {
        this.nhanvien = nhanvien;
    }

    public KhachHangDTO getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(KhachHangDTO khachhang) {
        this.khachhang = khachhang;
    }

    public ArrayList<ChiTietHoaDonDTO> getDsChiTiet() {
        return dsChiTiet;
    }

    public void setDsChiTiet(ArrayList<ChiTietHoaDonDTO> dsChiTiet) {
        this.dsChiTiet = dsChiTiet;
    }

    public ArrayList<SanPhamDTO> getDsSanPham() {
        return dsSanPham;
    }

    public void setDsSanPham(ArrayList<SanPhamDTO> dsSanPham) {
        this.dsSanPham = dsSanPham;
    }
    @Override
    public String toString(){
        return hoadon + "-" + khachhang + "-" + nhanvien + "-" + dsChiTiet;
    }
}


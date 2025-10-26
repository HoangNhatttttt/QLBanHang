/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author 0388153585
 */
public class PhieuNhapDTO {
    public String maPN;
    public String maNV;
    public String maNCC;
    public String ngayNhap;
    public String gioNhap;
    public String tongTien;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(String maPN, String maNV, String maNCC, String ngayNhap, String gioNhap, String tongTien) {
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.ngayNhap = ngayNhap;
        this.gioNhap = gioNhap;
        this.tongTien = tongTien;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getGioNhap() {
        return gioNhap;
    }

    public void setGioNhap(String gioNhap) {
        this.gioNhap = gioNhap;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    

  
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author 0388153585
 */
public class ChiTietSanPhamDTO {
    public String maSP;
    public String tenSP;
    public String tenLoaiSP;
    public String donGia;
    public String hinhAnh;

    public ChiTietSanPhamDTO() {
    }

    public ChiTietSanPhamDTO(String maSP, String tenSP, String tenLoaiSP, String donGia, String hinhAnh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.tenLoaiSP = tenLoaiSP;
        this.donGia = donGia;
        this.hinhAnh = hinhAnh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
    
}

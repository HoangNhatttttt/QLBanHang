
package DTO;

import BUS.LoaiSanPhamBUS;

public class SanPhammDTO {
    private String maSP;
    private String tenSP;
    private int soLuong;
    private double donGia;
    private String donVi;
    private String maLoai;
    private String hinhAnh;
    private String TenLoai;

    public SanPhammDTO() {}

    public SanPhammDTO(String maSP, String tenSP, int soLuong, double donGia, String donVi, String maLoai, String hinhAnh, String tenLoai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.donVi = donVi;
        this.maLoai = maLoai;
        this.hinhAnh = hinhAnh;
        this.TenLoai = LoaiSanPhamBUS.getTenLoaiByMa(maLoai);
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }
//    public void setMaLoai(String maLoai) {
//        this.maLoai = maLoai;
//        this.TenLoai = LoaiSanPhamBUS.getTenLoaiByMa(maLoai); // Tự động suy ra tên loại
//    }
    // Getters and Setters
    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public String getDonVi() { return donVi; }
    public void setDonVi(String donVi) { this.donVi = donVi; }

    public String getMaLoai() { return maLoai; }
    public void setMaLoai(String maLoai) { this.maLoai = maLoai; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
}


package DTO;

public class SanPhamDTO {
    public int MaSP;
    public String TenSP;
    public int SoLuong;
    public double DonGia;
    public String DonVi;
    public int MaLoai;

    public SanPhamDTO() {
    }
    
    
    public SanPhamDTO(int maSP, String tenSP, int soLuong, double donGia, String donVi, int maLoai) {
        MaSP = maSP;
        TenSP = tenSP;
        SoLuong = soLuong;
        DonGia = donGia;
        DonVi = donVi;
        MaLoai = maLoai;
    }

    // Getter và Setter
    public int getMaSP() { return MaSP; }
    public void setMaSP(int maSP) { MaSP = maSP; }

    public String getTenSP() { return TenSP; }
    public void setTenSP(String tenSP) { TenSP = tenSP; }

    public int getSoLuong() { return SoLuong; }
    public void setSoLuong(int soLuong) { SoLuong = soLuong; }

    public double getDonGia() { return DonGia; }
    public void setDonGia(double donGia) { DonGia = donGia; }

    public String getDonVi() { return DonVi; }
    public void setDonVi(String donVi) { DonVi = donVi; }

    public int getMaLoai() { return MaLoai; }
    public void setMaLoai(int maLoai) { MaLoai = maLoai; }

    @Override
    public String toString() {
        return MaSP + " - " + TenSP;
    }
}

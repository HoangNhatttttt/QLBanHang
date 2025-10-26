package DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HoaDonDTO {
    private String maHD;
    private String maKH;
    private String maNV;
    private LocalDateTime ngayLapHD;
    private BigDecimal tongTien;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String maHD, String maKH, String maNV, LocalDateTime ngayLapHD, BigDecimal tongTien) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayLapHD = ngayLapHD;
        this.tongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public LocalDateTime getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(LocalDateTime ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "HoaDonDTO{" +
                "maHD='" + maHD + '\'' +
                ", maKH='" + maKH + '\'' +
                ", maNV='" + maNV + '\'' +
                ", ngayLapHD=" + ngayLapHD +
                ", tongTien=" + tongTien +
                '}';
    }
}

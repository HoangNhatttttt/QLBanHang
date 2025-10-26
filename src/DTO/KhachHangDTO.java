package DTO;

public class KhachHangDTO {
    private String MaKH;
    private String Ho;
    private String Ten;
    private String DiaChi;
    private String SDT;

    public KhachHangDTO() {}

    public KhachHangDTO(String MaKH, String Ho, String Ten, String DiaChi, String SDT) {
        this.MaKH = MaKH;
        this.Ho = Ho;
        this.Ten = Ten;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return "KhachHangDTO{" +
               "MaKH=" + MaKH +
               ", Ho='" + Ho + '\'' +
               ", Ten='" + Ten + '\'' +
               ", DiaChi='" + DiaChi + '\'' +
               ", SDT='" + SDT + '\'' +
               '}';
    }
}

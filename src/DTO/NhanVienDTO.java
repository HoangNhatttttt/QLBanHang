package DTO;

public class NhanVienDTO {
    private String MaNV;
    private String Ho;
    private String Ten;
    private double LuongThang;
    private int TrangThai;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String MaNV, String Ho, String Ten, double LuongThang, int TrangThai) {
        this.MaNV = MaNV;
        this.Ho = Ho;
        this.Ten = Ten;
        this.LuongThang = LuongThang;
        this.TrangThai = TrangThai;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
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

    public double getLuongThang() {
        return LuongThang;
    }

    public void setLuongThang(double LuongThang) {
        this.LuongThang = LuongThang;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    @Override   //khi in raw trong System.out.println(...)
    public String toString() {
        return "NhanVienDTO {"
                + "MaNV=" + MaNV
                + ", Ho='" + Ho + '\''
                + ", Ten='" + Ten + '\''
                + ", LuongThang=" + LuongThang
                + ", TrangThai='" + TrangThai + '\''
                + '}';
    }
}
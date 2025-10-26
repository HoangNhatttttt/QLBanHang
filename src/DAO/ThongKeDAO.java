package DAO;

import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class ThongKeDAO {
    private Connection conn;

    public ThongKeDAO() {
        // Initialize your DB connection
        conn = Connector.getConnection();
    }

    public ArrayList<ThongKeDTO> getAllThongKe() {
        ArrayList<ThongKeDTO> result = new ArrayList<>();

        try {
            String sql = "SELECT * FROM hoadon hd " +
                         "JOIN khachhang kh ON hd.MaKH = kh.MaKH " +
                         "JOIN nhanvien nv ON hd.MaNV = nv.MaNV";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ThongKeDTO thongke = new ThongKeDTO();

                // HoaDon
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaKH(rs.getString("MaKH"));
                hd.setMaNV(rs.getString("MaNV"));
                Timestamp timestamp = rs.getTimestamp("NgayLapHD");
                hd.setNgayLapHD(timestamp != null ? timestamp.toLocalDateTime() : null);
                BigDecimal tongTien = rs.getBigDecimal("TongTien");
                hd.setTongTien(tongTien);
                thongke.setHoadon(hd);

                // KhachHang
                KhachHangDTO kh = new KhachHangDTO();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setHo(rs.getString("Ho"));
                kh.setTen(rs.getString("Ten"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSDT(rs.getString("SDT"));
                thongke.setKhachhang(kh);

                // NhanVien
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setHo(rs.getString("nv.Ho"));
                nv.setTen(rs.getString("nv.Ten"));
                nv.setLuongThang(rs.getDouble("LuongThang"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                thongke.setNhanvien(nv);

                // Get CTHD + SanPham
                ArrayList<ChiTietHoaDonDTO> dsChiTiet = new ArrayList<>();
                ArrayList<SanPhamDTO> dsSanPham = new ArrayList<>();

                String sqlCT = "SELECT * FROM chitiethoadon cthd " +
                               "JOIN sanpham sp ON cthd.MaSP = sp.MaSP " +
                               "WHERE cthd.MaHD = ?";

                PreparedStatement stmtCT = conn.prepareStatement(sqlCT);
                stmtCT.setString(1, hd.getMaHD());
                ResultSet rsCT = stmtCT.executeQuery();

                while (rsCT.next()) {
                    ChiTietHoaDonDTO ct = new ChiTietHoaDonDTO();
                    ct.setMaHD(rsCT.getString("MaHD"));
                    ct.setMaSP(rsCT.getString("MaSP"));
                    ct.setSoLuong(rsCT.getInt("SoLuong"));
                    ct.setDonGia(rsCT.getDouble("DonGia"));
                    ct.setThanhTien(rsCT.getDouble("ThanhTien"));
                    dsChiTiet.add(ct);

                    SanPhamDTO sp = new SanPhamDTO();
                    sp.setMaSP(rsCT.getInt("MaSP"));    //WARNING: NOT SAME TYPE AS DATABASE!
                    sp.setTenSP(rsCT.getString("TenSP"));
                    sp.setSoLuong(rsCT.getInt("sp.SoLuong"));
                    sp.setDonGia(rsCT.getDouble("sp.DonGia"));
                    sp.setDonVi(rsCT.getString("DonVi"));
                    sp.setMaLoai(rsCT.getInt("MaLoai")); //WARNING: NOT SAME TYPE AS DATABASE!
                    dsSanPham.add(sp);
                }

                thongke.setDsChiTiet(dsChiTiet);
                thongke.setDsSanPham(dsSanPham);
                result.add(thongke);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
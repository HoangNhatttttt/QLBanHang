package BUS;

import DAO.BanHangDAO;
import DAO.SanPhamDAO;
import DTO.ChiTietSanPhamDTO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class BanHangBUS {
    private SanPhamDAO spDAO;
    private BanHangDAO bhDAO;
    
    public BanHangBUS() {
        spDAO = new SanPhamDAO();
        bhDAO = new BanHangDAO();
    }
    
    // Lấy danh sách sản phẩm
    public ArrayList<SanPhamDTO> getListSanPham() {
        return spDAO.docDS_SP();
    }
    
    // Lấy thông tin chi tiết sản phẩm theo mã
    public SanPhamDTO getSanPham(String maSP) {
        ArrayList<SanPhamDTO> listSP = spDAO.docDS_SP();
        for (SanPhamDTO sp : listSP) {
            if (String.valueOf(sp.MaSP).equals(maSP)) {
                return sp;
            }
        }
        return null;
    }
    
    // Lấy chi tiết sản phẩm bao gồm hình ảnh
    public ChiTietSanPhamDTO getChiTietSP(String maSP) {
        ArrayList<ChiTietSanPhamDTO> dsCTSP = spDAO.docChiTietSP(maSP);
        if (!dsCTSP.isEmpty()) {
            return dsCTSP.get(0);
        }
        return null;
    }
    
    // Tìm kiếm sản phẩm theo từ khóa
    public ArrayList<SanPhamDTO> searchSanPham(String keyword) {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        ArrayList<SanPhamDTO> listSP = spDAO.docDS_SP();
        keyword = keyword.toLowerCase().trim();
        
        for (SanPhamDTO sp : listSP) {
            String tenSP = sp.TenSP.toLowerCase();
            String maSPStr = String.valueOf(sp.MaSP).toLowerCase();
            String maLoaiStr = String.valueOf(sp.MaLoai).toLowerCase();
            
            if (tenSP.contains(keyword) || maSPStr.contains(keyword) || maLoaiStr.contains(keyword)) {
                result.add(sp);
            }
        }
        
        return result;
    }
    
    // Kiểm tra số lượng tồn kho
    public boolean kiemTraSoLuong(String maSP, int soLuongMua) {
        SanPhamDTO sp = getSanPham(maSP);
        if (sp != null) {
            return sp.SoLuong >= soLuongMua;
        }
        return false;
    }
    
    // Cập nhật số lượng sản phẩm sau khi bán
    public boolean capNhatSoLuong(String maSP, int soLuongMua) {
        return bhDAO.capNhatSoLuongSanPham(maSP, soLuongMua);
    }
    
    // Tạo mã hóa đơn mới
    public String taoMaHoaDon() {
        return bhDAO.taoMaHoaDonMoi();
    }
} 
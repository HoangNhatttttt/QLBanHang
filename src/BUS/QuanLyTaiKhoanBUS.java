package BUS;

import DAO.QuanLyTaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;
import java.util.Collections;

public class QuanLyTaiKhoanBUS {

    private ArrayList<TaiKhoanDTO> dstk;
    private QuanLyTaiKhoanDAO qltkDAO;

    public QuanLyTaiKhoanBUS() {
        qltkDAO = new QuanLyTaiKhoanDAO();
        dstk = qltkDAO.readDB();
    }

    public void showConsole() {
        for (TaiKhoanDTO tk : dstk) {
            System.out.print(tk.getUsername() + " ");
            System.out.print(tk.getPassword() + " ");
            System.out.print(tk.getMaNV() + " ");
            System.out.println(tk.getMaQuyen());
        }
    }

    public String[] getHeaders() {
        return new String[]{"Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền"};
    }

    public void readDB() {
        dstk = qltkDAO.readDB();
    }

    public TaiKhoanDTO getTaiKhoan(String username) {
        for (TaiKhoanDTO tk : dstk) {
            if (tk.getUsername().equals(username)) {
                return tk;
            }
        }
        return null;
    }

    public ArrayList<TaiKhoanDTO> search(String keyword, String type) {
        ArrayList<TaiKhoanDTO> ketQua = new ArrayList<>();
        ArrayList<TaiKhoanDTO> dstk = getDstk();
        
        // Tìm kiếm dựa trên type
        for (TaiKhoanDTO tk : dstk) {
            if (type.equals("all")) {
                if (tk.getUsername().toLowerCase().contains(keyword.toLowerCase()) ||
                    tk.getPassword().toLowerCase().contains(keyword.toLowerCase()) ||
                    tk.getMaNV().toLowerCase().contains(keyword.toLowerCase()) ||
                    tk.getMaQuyen().toLowerCase().contains(keyword.toLowerCase())) {
                    ketQua.add(tk);
                }
            } else if (type.equals("username")) {
                if (tk.getUsername().toLowerCase().contains(keyword.toLowerCase())) {
                    ketQua.add(tk);
                }
            } else if (type.equals("password")) {
                if (tk.getPassword().toLowerCase().contains(keyword.toLowerCase())) {
                    ketQua.add(tk);
                }
            } else if (type.equals("maNV")) {
                if (tk.getMaNV().toLowerCase().contains(keyword.toLowerCase())) {
                    ketQua.add(tk);
                }
            } else if (type.equals("maQuyen")) {
                if (tk.getMaQuyen().toLowerCase().contains(keyword.toLowerCase())) {
                    ketQua.add(tk);
                }
            }
        }
        
        return ketQua;
    }

    public Boolean add(TaiKhoanDTO tk) {
        boolean ok = qltkDAO.add(tk);

        if (ok) {
            dstk.add(tk);
        }
        return ok;
    }

    public Boolean add(String username, String pass, String maNV, String maQuyen) {
        TaiKhoanDTO tk = new TaiKhoanDTO(username, pass, maNV, maQuyen);
        return add(tk);
    }

    public Boolean delete(String username) {
        boolean ok = qltkDAO.delete(username);

        if (ok) {
            dstk.removeIf(tk -> tk.getUsername().equals(username));
        }
        return ok;
    }

    public Boolean update(String username, String pass, String maNV, String maQuyen) {
        boolean ok = qltkDAO.update(username, pass, maNV, maQuyen);

        if (ok) {
            for (TaiKhoanDTO tk : dstk) {
                if (tk.getUsername().equals(username)) {
                    tk.setPassword(pass);
                    tk.setMaNV(maNV);
                    tk.setMaQuyen(maQuyen);
                }
            }
        }

        return ok;
    }

    public ArrayList<TaiKhoanDTO> getDstk() {
        return dstk;
    }

    public boolean isTaiKhoanTonTai(String username) {
        // Kiểm tra xem tài khoản đã tồn tại trong cơ sở dữ liệu chưa
        ArrayList<TaiKhoanDTO> danhSach = getDstk();
        for (TaiKhoanDTO tk : danhSach) {
            if (tk.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean themTaiKhoan(TaiKhoanDTO taiKhoan) {
        return add(taiKhoan); // Sử dụng phương thức add() có sẵn trong class
    }

    public ArrayList<TaiKhoanDTO> getDstkSorted() {
        // Sắp xếp theo username hoặc tiêu chí khác
        ArrayList<TaiKhoanDTO> sortedList = new ArrayList<>(dstk);
        Collections.sort(sortedList, (tk1, tk2) -> tk1.getUsername().compareTo(tk2.getUsername()));
        return sortedList;
    }
}

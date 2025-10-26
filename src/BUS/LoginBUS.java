/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.LoginDAO;
/**
 *
 * @author 0388153585
 */
public class LoginBUS {
    public String dangNhap(String tenDangNhap, String matKhau) {
        LoginDAO dao = new LoginDAO();
        return dao.checkLogin(tenDangNhap, matKhau);
    }
}

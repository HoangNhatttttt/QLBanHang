/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author 0388153585
 */
public class ChiTietKMDTO {
    public String maKM;
    public String tenKM;
    public String maSP;
    public String tenSP;
    public String phanTram;

    public ChiTietKMDTO() {
    }

    public ChiTietKMDTO(String maKM, String tenKM, String maSP, String tenSP, String phanTram) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.phanTram = phanTram;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(String phanTram) {
        this.phanTram = phanTram;
    }

    
    
}

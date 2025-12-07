/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import GUI.MainPanel.QuanLyPhieuNhapForm;
import GUI.MainPanel.Quyen;
import GUI.MainPanel.TaiKhoan;
import javax.swing.*;
import java.awt.event.ActionListener;
import static GUI.LoginGUI.nhanVienLogin;
import static GUI.LoginGUI.quyenLogin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import GUI.MainPanel.*;


public class MainFrame extends javax.swing.JFrame {
private JButton btnLogout; //e
    private JButton currentButton;
    private Map<String, JPanel> panelMap = new HashMap<>();
//   private DTO.NhanVienDTO nvDangNhap = LoginGUI.nhanVienLogin;
 //   private DTO.QuyenDTO quyenDangNhap = LoginGUI.quyenLogin;
    public MainFrame() {
        initComponents();
        setLocation(35, 35);
        setTitle("Dien Tu Store");
        setSize(1250, 650);
        hienThiMenuTheoPhanQuyen();
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        panelContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.BorderLayout());

        panelMenu.setBackground(new java.awt.Color(25, 118, 210));
        panelMenu.setPreferredSize(new java.awt.Dimension(200, 600));
        panelMenu.setLayout(new javax.swing.BoxLayout(panelMenu, javax.swing.BoxLayout.Y_AXIS));

        panelContent.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hienThiMenuTheoPhanQuyen() {
    String chiTietQuyen = quyenLogin.getChiTietQuyen();
// TODO: THÊM CHỨC NĂNG TRANG Ở NGAY ĐÂY, new [tênfile]
    themChucNang("Kho hàng", "qlKhoHang", new KhoHangPanel());
    themChucNang("Bán hàng", "qlBanHang", new FormBanHang());
      themChucNang("Nhập hàng", "qlNhapHang", new NhapHangForm());
    themChucNang("Sản phẩm", "qlSanPham", new SanPhamGUI());
    themChucNang("Loại sản phẩm", "qlLoaiSanPham", new LoaiSanPhamGUI());
      themChucNang("Hóa đơn", "qlHoaDon", new HoaDonPanel());
      themChucNang("Phiếu nhập", "qlPhieuNhap", new QuanLyPhieuNhapForm());
      themChucNang("Khuyến mãi", "qlKhuyenMai", new KhuyenMaiGUI());
      themChucNang("Nhân viên", "qlNhanVien", new NhanVienPanel());
      themChucNang("Khách hàng", "qlKhachHang", new KhachHangPanel());
      themChucNang("Nhà cung cấp", "qlNhaCungCap", new QuanLyNhaCungCapForm());
      themChucNang("Tài khoản", "qlTaiKhoan", new TaiKhoan());
      themChucNang("Quyền", "qlQuyen", new Quyen());
    themChucNang("Thống kê", "qlThongKe", new ThongKePanel());

    panelMenu.add(Box.createVerticalGlue());   // push it to the bottom
    btnLogout = new JButton("Đăng xuất");
    btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnLogout.setMaximumSize(new Dimension(200, 40));
    btnLogout.setBackground(new Color(220, 53, 69));    // a red tone
    btnLogout.setForeground(Color.WHITE);
    btnLogout.setFocusPainted(false);
    btnLogout.addActionListener(e -> {
        // close this window
        this.dispose();
        // show login screen
        new LoginGUI().setVisible(true);
    });
    panelMenu.add(btnLogout);
    // Trang chào mừng mặc định
        String msg = "Xin Chào " +nhanVienLogin.getHo()+" "
        + nhanVienLogin.getTen() 
        + " - " 
        + nhanVienLogin.getMaNV();
    JOptionPane.showMessageDialog(
        this,            // or `null` if you don't have a containing frame reference
        msg,
        "Chào mừng!",
        JOptionPane.INFORMATION_MESSAGE
    );
}
    private void themChucNang(String tenChucNang, String maQuyen, JPanel panel) {
    if (quyenLogin.getChiTietQuyen().contains(maQuyen)) {
        JButton btn = new JButton(tenChucNang);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setFocusPainted(false);
        
        // Màu mặc định
        btn.setBackground(new Color(255, 255, 255));
        btn.setForeground(new Color(33, 33, 33));

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentButton != null) {
                    // ⚪️ Trả lại nút trước đó về màu ban đầu
                    currentButton.setBackground(new Color(255, 255, 255));
                    currentButton.setForeground(new Color(33, 33, 33));
                }
                btn.setBackground(new Color(13, 71, 161));
                btn.setForeground(new Color(255, 255, 255));
                currentButton = btn;

                panelContent.removeAll();
                panelContent.add(panel, BorderLayout.CENTER);  // Add to center explicitly
                panelContent.revalidate();
                panelContent.repaint();
                panelContent.revalidate();
                panelContent.repaint();
            }
        });

        panelMenu.add(Box.createRigidArea(new Dimension(0, 10))); // khoảng cách
        panelMenu.add(btn);
        panelMap.put(tenChucNang, panel);
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}

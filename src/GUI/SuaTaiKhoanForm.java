package GUI;

import BUS.QuanLyTaiKhoanBUS;
import javax.swing.JOptionPane;
import GUI.MainPanel.JPanelTaiKhoan;
import javax.swing.JTextField;
import DTO.TaiKhoanDTO;

public class SuaTaiKhoanForm extends javax.swing.JFrame {
    
    private JPanelTaiKhoan parentPanel;
    private String originalUsername;
    QuanLyTaiKhoanBUS qltkBUS = new QuanLyTaiKhoanBUS();
    
    public SuaTaiKhoanForm(JPanelTaiKhoan parent, String username, String password, String maNV, String maQuyen) {
        initComponents();
        this.parentPanel = parent;
        this.originalUsername = username;
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        txtTaiKhoan.setText(username);
        txtMatKhau.setText(password);
        txtMaNV.setText(maNV);
        txtMaQuyen.setText(maQuyen);
        
        txtMaNV.setEditable(false);
        txtMaQuyen.setEditable(false);
        
        setupButtonHandlers();
    }

    private void setupButtonHandlers() {
        btnChonMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FormChonNV formChonNV = new FormChonNV(SuaTaiKhoanForm.this);
                formChonNV.setLocationRelativeTo(null);
                formChonNV.setVisible(true);
            }
        });
        
        btnChonMaQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromChonQuyen formChonQuyen = new FromChonQuyen(SuaTaiKhoanForm.this);
                formChonQuyen.setLocationRelativeTo(null);
                formChonQuyen.setVisible(true);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
    }

    public void setMaNV(String maNV) {
        txtMaNV.setText(maNV);
    }

    public void setMaQuyen(String maQuyen) {
        txtMaQuyen.setText(maQuyen);
    }

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {
        // Kiểm tra các trường thông tin
        if (!checkEmpty()) {
            return;
        }
        
        String username = txtTaiKhoan.getText().trim();
        String password = txtMatKhau.getText().trim();
        String maNV = txtMaNV.getText().trim();
        String maQuyen = txtMaQuyen.getText().trim();
        
        boolean ketQua = false;
        
        if (!username.equals(originalUsername)) {
            // Kiểm tra xem username mới đã tồn tại chưa
            if (qltkBUS.isTaiKhoanTonTai(username)) {
                JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Tạo mới qltkBUS để đảm bảo kết nối mới
            QuanLyTaiKhoanBUS busInstance = new QuanLyTaiKhoanBUS();
            
            // Thêm tài khoản mới
            boolean addResult = busInstance.add(new TaiKhoanDTO(username, password, maNV, maQuyen));
            if (!addResult) {
                JOptionPane.showMessageDialog(this, "Không thể thêm tài khoản mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Tạo kết nối mới và xóa tài khoản cũ
            busInstance = new QuanLyTaiKhoanBUS();
            boolean deleteResult = busInstance.delete(originalUsername);
            if (!deleteResult) {
                JOptionPane.showMessageDialog(this, "Đã thêm tài khoản mới nhưng không thể xóa tài khoản cũ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
            
            ketQua = true;
        } else {
            // Tạo mới kết nối
            QuanLyTaiKhoanBUS busInstance = new QuanLyTaiKhoanBUS();
            ketQua = busInstance.update(originalUsername, password, maNV, maQuyen);
        }
        
        if (ketQua) {
            JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thành công!");
            parentPanel.loadTable();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Boolean checkEmpty() {
        String username = txtTaiKhoan.getText();
        String pass = txtMatKhau.getText();
        String manv = txtMaNV.getText();
        String maquyen = txtMaQuyen.getText();

        if (username.trim().equals("")) {
            return showErrorTx(txtTaiKhoan, "Tên đăng nhập không được để trống");
        } else if (pass.equals("")) {
            return showErrorTx(txtMatKhau, "Mật khẩu không được để trống");
        } else if (manv.trim().equals("")) {
            return showErrorTx(txtMaNV, "Mã nhân viên không được để trống");
        } else if (maquyen.trim().equals("")) {
            return showErrorTx(txtMaQuyen, "Mã quyền không được để trống");
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        txtMaQuyen = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        btnChonMaNV = new javax.swing.JButton();
        btnChonMaQuyen = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Sửa tài khoản");

        jLabel1.setText("Tên tài khoản:");

        jLabel3.setText("Mật khẩu :");

        jLabel4.setText("Mã nhân viên:");

        jLabel5.setText("Mã quyền:");

        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });

        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });

        txtMaQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaQuyenActionPerformed(evt);
            }
        });

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        btnChonMaNV.setText("...");
        btnChonMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonMaNVActionPerformed(evt);
            }
        });

        btnChonMaQuyen.setText("...");

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jButton3.setText("Hủy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMaQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnChonMaQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnChonMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel2)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLuu)
                .addGap(130, 130, 130))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnChonMaNV)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonMaQuyen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu)
                    .addComponent(jButton3))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void txtMaQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaQuyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaQuyenActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void btnChonMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChonMaNVActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonMaNV;
    private javax.swing.JButton btnChonMaQuyen;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaQuyen;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables

    private Boolean showErrorTx(JTextField field, String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
        field.requestFocus();
        return false;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.MainPanel;
import DTO.HoaDonDTO;
import DTO.ChiTietHoaDonDTO;
import BUS.HoaDonBUS;
import BUS.ChiTietHoaDonBUS;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
/**
 *
 * @author drunkenpipe
 */
public class HoaDonPanel extends javax.swing.JPanel {
    private HoaDonBUS hdBus = new HoaDonBUS();
    private ChiTietHoaDonBUS chdBus = new ChiTietHoaDonBUS();
    JPanel dateFromPanel;
    JPanel dateToPanel;
    //️ Create date pickers
        JDateChooser from = new JDateChooser();
        JDateChooser to = new JDateChooser();
    /**
     * Creates new form HoaDonPanel
     */
    public HoaDonPanel() {
        initComponents();
        setLayout(new BorderLayout());
        addComponentsToLayout();
        customizeTable();
        updateTable();
        listener();
        setSize(1000, 600);
    }
    private void listener(){
            bangHD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = bangHD.getSelectedRow();
            if (selectedRow != -1) {
                String maHD = (String) bangHD.getValueAt(selectedRow, 0);  // Assuming MaHD is in the first column
                loadChiTietHoaDon(maHD);  // Load the corresponding ChiTietHoaDon data
            }
        }
    });
    }
    private void updateTable() {
        ArrayList<HoaDonDTO> list = hdBus.getAllHoaDon();
        DefaultTableModel model = (DefaultTableModel) bangHD.getModel();
        model.setRowCount(0);
        for (HoaDonDTO hd : list) {
            model.addRow(new Object[]{hd.getMaHD(), hd.getMaKH(), hd.getMaNV(), hd.getNgayLapHD(), hd.getTongTien()});
        }
    }
    private void loadChiTietHoaDon(String maHD) {
    // Retrieve the ChiTietHoaDon data from the BUS layer
    ArrayList<ChiTietHoaDonDTO> chiTietList = new ArrayList<>();
    for (ChiTietHoaDonDTO ct : chdBus.getAllChiTietHoaDon()) {
        if (ct.getMaHD().equals(maHD)) {
            chiTietList.add(ct);
        }
    }
    
    // Update the bangCHD table with the retrieved data
    updateBangCHD(chiTietList);
}
    private void updateBangCHD(ArrayList<ChiTietHoaDonDTO> chiTietList) {
        // Define the table model
        DefaultTableModel model = (DefaultTableModel) bangCHD.getModel();

        // Clear the existing rows
        model.setRowCount(0);

        // Define the number of decimal places you want
        int decimalPlaces = 2;

        // Add rows to the table model based on the chiTietList
        for (ChiTietHoaDonDTO ct : chiTietList) {
            // Format DonGia and ThanhTien to prevent scientific notation
            BigDecimal donGia = new BigDecimal(ct.getDonGia()).setScale(decimalPlaces, RoundingMode.HALF_UP);
            BigDecimal thanhTien = new BigDecimal(ct.getThanhTien()).setScale(decimalPlaces, RoundingMode.HALF_UP);


            Object[] rowData = {
                ct.getMaHD(),  // MaHD
                ct.getMaSP(),  // MaSP
                ct.getSoLuong(),  // SoLuong
                donGia.toString(),  // DonGia (formatted)
                thanhTien.toString()  // ThanhTien (formatted)
            };
            model.addRow(rowData);
        }
    }
    
    private boolean matchesSingle(HoaDonDTO hd, String rawFilter) {
        rawFilter = rawFilter.trim();

        // — Numeric filters: mahd/makh/manv/tongtien
        Pattern pNum = Pattern.compile(
            "(?i)\\s*(mahd|makh|manv|tongtien)\\s*:?\\s*(=|!=|<=|>=|<|>)\\s*(\\d+(?:\\.\\d+)?)\\s*"
        );
        Matcher mNum = pNum.matcher(rawFilter);
        if (mNum.matches()) {
            String field = mNum.group(1).toLowerCase();
            String op    = mNum.group(2);
            BigDecimal value = new BigDecimal(mNum.group(3));
            BigDecimal col;

            switch (field) {
                case "mahd":      col = new BigDecimal(hd.getMaHD());        break;
                case "makh":      col = new BigDecimal(hd.getMaKH());        break;
                case "manv":      col = new BigDecimal(hd.getMaNV());        break;
                default /*tongtien*/ : col = hd.getTongTien();              break;
            }

            int cmp = col.compareTo(value);
            return switch (op) {
                case "="  -> cmp == 0;
                case "!=" -> cmp != 0;
                case ">"  -> cmp >  0;
                case "<"  -> cmp <  0;
                case ">=" -> cmp >= 0;
                case "<=" -> cmp <= 0;
                default   -> false;
            };
        }

        // — Fallback substring on mahd/makh/manv
        String lit = rawFilter.toLowerCase();
        return hd.getMaHD().toLowerCase().contains(lit)
            || hd.getMaKH().toLowerCase().contains(lit)
            || hd.getMaNV().toLowerCase().contains(lit);
    }
    private void addComponentsToLayout() {
        setLayout(new BorderLayout(10, 10));
          
        // Panel for "Từ ngày"
        dateFromPanel = new JPanel();
        dateFromPanel.setBorder(BorderFactory.createTitledBorder("Từ ngày"));
        from.setPreferredSize(new Dimension(120, 25));
        dateFromPanel.add(from);
        
        // Panel for "Đến ngày"
        dateToPanel = new JPanel();
        dateToPanel.setBorder(BorderFactory.createTitledBorder("Đến ngày"));
        to.setPreferredSize(new Dimension(120, 25));
        dateToPanel.add(to);
        // Top title only
        this.add(title, BorderLayout.NORTH);

       // === Left panel (Hoa Đơn) ===
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));

        // Title + separator
        JPanel hdHeader = new JPanel(new BorderLayout());
        hdHeader.add(hdtitle, BorderLayout.NORTH);
        hdHeader.add(jSeparator1, BorderLayout.CENTER);

        // === Control buttons (Row 1) ===
        JPanel buttonPanelRow1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //buttonPanelRow1.add(add);
        //buttonPanelRow1.add(remove);
        //buttonPanelRow1.add(edit);

        // === Control buttons (Row 2 - date filters + clear) ===
        JPanel buttonPanelRow2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanelRow2.add(search);
        buttonPanelRow2.add(searchButton);
        buttonPanelRow2.add(help);
        buttonPanelRow2.add(dateFromPanel);
        buttonPanelRow2.add(dateToPanel);
        buttonPanelRow2.add(clearButton);
        // === Combine both rows into one vertical container ===
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(buttonPanelRow1);
        buttonPanel.add(buttonPanelRow2);
        // Combine header + controls
        JPanel topLeft = new JPanel(new BorderLayout());
        topLeft.add(hdHeader, BorderLayout.NORTH);
        topLeft.add(buttonPanel, BorderLayout.CENTER);

        // Add to left panel
        leftPanel.add(topLeft, BorderLayout.NORTH);
        leftPanel.add(jScrollPane1, BorderLayout.CENTER); // This will expand to fill remaining space

        // === Right panel (Chi tiết Hóa Đơn) ===
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        JPanel chdHeader = new JPanel(new BorderLayout());
        chdHeader.add(chdtitle, BorderLayout.NORTH);
        chdHeader.add(jSeparator2, BorderLayout.CENTER);

        rightPanel.add(chdHeader, BorderLayout.NORTH);
        rightPanel.add(jScrollPane2, BorderLayout.CENTER); // bangCHD table

        // === Split pane for both tables ===
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.5); // Equal space initially
        splitPane.setOneTouchExpandable(true);

        this.add(splitPane, BorderLayout.CENTER);
    }


    private void customizeTable() {
        // Change the background color of the column headers
        JTableHeader header = bangHD.getTableHeader();
        header.setBackground(new java.awt.Color(44, 62, 80)); // dark blue background
        header.setForeground(new java.awt.Color(0,0,0)); // light text color

        // Optionally set custom fonts for column headers
        header.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangHD = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        bangCHD = new javax.swing.JTable();
        edit = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        add = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        hdtitle = new javax.swing.JLabel();
        chdtitle = new javax.swing.JLabel();
        help = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1330, 750));

        title.setAlignment(java.awt.Label.CENTER);
        title.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        title.setName(""); // NOI18N
        title.setText("Quản lí hóa đơn");

        bangHD.setBackground(new java.awt.Color(255, 255, 153));
        bangHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã khách hàng", "Mã nhân viên", "Ngày lập", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(bangHD);

        bangCHD.setBackground(new java.awt.Color(255, 255, 153));
        bangCHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(bangCHD);

        edit.setBackground(new java.awt.Color(255, 255, 255));
        edit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        edit.setForeground(new java.awt.Color(0, 0, 0));
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Image/edit.png"))); // NOI18N
        edit.setText("Chỉnh sửa");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        remove.setBackground(new java.awt.Color(255, 255, 255));
        remove.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        remove.setForeground(new java.awt.Color(0, 0, 0));
        remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Image/delete.png"))); // NOI18N
        remove.setText("Xóa");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        add.setBackground(new java.awt.Color(255, 255, 255));
        add.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        add.setForeground(new java.awt.Color(0, 0, 0));
        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Image/plus.png"))); // NOI18N
        add.setText("Thêm");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        search.setBackground(new java.awt.Color(255, 255, 255));
        search.setForeground(new java.awt.Color(0, 0, 0));
        search.setToolTipText("mahd, makh, manv, tongtien đi kèm với =, !=, >, <, >=, <= và sau đó là một con só\nvd: manv>2, tongtien<=15000.50\n\nngaylap sài  >,<,>=,<=,= dạng ngày là yyyy[/MM[/dd]]\nvd: ngaylap>=2025/05, ngaylap<2025\n\ncó thể search dạng giờ với dạng HH:mm:SS\nvd: ngaylap=20:00:00\n\nngaylap: nếu muốn search với giá trị \"sao cũng được\", sài xx và với dấu : thay vì comparitor\nvd: ngaylap:2025/xx/05-20:xx:xx\n\ncó thể sài AND, OR, NOT để tìm kiếm hợp (in hoa)\nvd: mahd=1 AND makh=2 sẽ trả về kết quả có mã hóa đơn là 1 và mã khách hàng là 2\n     makh=1 AND NOT mahd=2 OR manv=2 sẽ đồng với (makh=1 AND NOT mahd=2) OR manv=2 \n\nnếu không sài format trên thì nó sẽ tìm tất cả\n");
        search.setPreferredSize(new java.awt.Dimension(200, 30));
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(255, 255, 255));
        searchButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        searchButton.setForeground(new java.awt.Color(0, 0, 0));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Image/icons8-search-24.png"))); // NOI18N
        searchButton.setText("tìm");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        hdtitle.setBackground(new java.awt.Color(255, 255, 255));
        hdtitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        hdtitle.setForeground(new java.awt.Color(0, 0, 0));
        hdtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hdtitle.setText("Hóa đơn");

        chdtitle.setBackground(new java.awt.Color(255, 255, 255));
        chdtitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chdtitle.setForeground(new java.awt.Color(0, 0, 0));
        chdtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chdtitle.setText("Chi tiết hóa đơn");

        help.setBackground(new java.awt.Color(255, 255, 255));
        help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Image/icons8-help-24.png"))); // NOI18N
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(255, 255, 255));
        clearButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        clearButton.setForeground(new java.awt.Color(0, 0, 0));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 453, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit)
                        .addGap(35, 35, 35)
                        .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2)
                            .addComponent(chdtitle, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(hdtitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hdtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(remove)
                                    .addComponent(add)
                                    .addComponent(edit))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(help, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                .addGap(9, 9, 9)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(searchButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(chdtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        int selectedRow = bangHD.getSelectedRow();
        if (selectedRow != -1) {
            String maHD = (String) bangHD.getValueAt(selectedRow, 0);
            HoaDonDTO existing = hdBus.getHoaDonByID(maHD);
            if (existing != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                JTextField tfMaKH = new JTextField(existing.getMaKH());
                JTextField tfMaNV = new JTextField(existing.getMaNV());
                JTextField tfNgayLap = new JTextField(existing.getNgayLapHD().format(formatter));
                JTextField tfTongTien = new JTextField(String.valueOf(existing.getTongTien()));

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Mã Hóa Đơn (không thể sửa):"));
                panel.add(new JTextField(maHD) {{ setEnabled(false); }});
                panel.add(new JLabel("Mã Khách Hàng:"));
                panel.add(tfMaKH);
                panel.add(new JLabel("Mã Nhân Viên:"));
                panel.add(tfMaNV);
                panel.add(new JLabel("Ngày Lập (yyyy-MM-dd HH:mm:ss):"));
                panel.add(tfNgayLap);
                panel.add(new JLabel("Tổng Tiền:"));
                panel.add(tfTongTien);

                int result = JOptionPane.showConfirmDialog(this, panel, "Chỉnh sửa hóa đơn",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        BigDecimal tongTien = new BigDecimal(tfTongTien.getText().trim());
                        LocalDateTime ngayLap = LocalDateTime.parse(tfNgayLap.getText().trim(), formatter);
                        HoaDonDTO updated = new HoaDonDTO(maHD, tfMaKH.getText().trim(), tfMaNV.getText().trim(), ngayLap, tongTien);
                        boolean success = hdBus.updateHoaDon(updated);
                        if (success) {
                            JOptionPane.showMessageDialog(this, "Đã cập nhật hóa đơn.");
                            updateTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "Cập nhật thất bại.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Tổng tiền không hợp lệ.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Ngày lập không hợp lệ. Định dạng đúng: yyyy-MM-dd HH:mm:ss");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để chỉnh sửa.");
        }
    }//GEN-LAST:event_editActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        int selectedRow = bangHD.getSelectedRow();
        if (selectedRow != -1) {
            String maHD = (String) bangHD.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa hóa đơn mã: " + maHD + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = hdBus.deleteHoaDon(maHD);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công.");
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để xóa.");
        }
    }//GEN-LAST:event_removeActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        JTextField tfMaHD = new JTextField("0");
        JTextField tfMaKH = new JTextField();
        JTextField tfMaNV = new JTextField();
        JTextField tfNgayLap = new JTextField();  // For date input in string format
        JTextField tfTongTien = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Mã Hóa Đơn (0 để tự tạo):"));
        panel.add(tfMaHD);
        panel.add(new JLabel("Mã Khách Hàng:"));
        panel.add(tfMaKH);
        panel.add(new JLabel("Mã Nhân Viên:"));
        panel.add(tfMaNV);
        panel.add(new JLabel("Ngày Lập (yyyy-MM-dd HH:mm:ss):"));
        panel.add(tfNgayLap);
        panel.add(new JLabel("Tổng Tiền:"));
        panel.add(tfTongTien);

        int result = JOptionPane.showConfirmDialog(this, panel, "Thêm Hóa Đơn mới",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String maHD = tfMaHD.getText().trim();
            String maKH = tfMaKH.getText().trim();
            String maNV = tfMaNV.getText().trim();
            String ngayLap = tfNgayLap.getText().trim();
            String tongTienStr = tfTongTien.getText().trim();

            if (maKH.isEmpty() || maNV.isEmpty() || ngayLap.isEmpty() || tongTienStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
                return;
            }

            try {
                BigDecimal tongTien = new BigDecimal(tongTienStr);
                // Parse ngayLap to LocalDateTime
                LocalDateTime ngayLapDT = LocalDateTime.parse(ngayLap, formatter);
                
                HoaDonDTO newHD = new HoaDonDTO(maHD, maKH, maNV, ngayLapDT, tongTien);
                boolean success = hdBus.addHoaDon(newHD);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Đã thêm Hóa Đơn thành công.");
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại. Có thể Mã HD đã tồn tại.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tổng tiền không hợp lệ.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ngày lập không hợp lệ. Định dạng đúng: yyyy-MM-dd HH:mm:ss");
            }
        }
    }//GEN-LAST:event_addActionPerformed

    
    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        searchButtonActionPerformed(new java.awt.event.ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }
    }//GEN-LAST:event_searchKeyPressed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
         // 1) Read your raw text
        String raw = search.getText().trim();

        // 2) Read the date pickers
        Date dateFrom = from.getDate();
        Date dateTo   = to.getDate();
        LocalDate ldFrom = dateFrom == null
            ? null
            : dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ldTo   = dateTo   == null
            ? null
            : dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // 3) Prepare your lists
        ArrayList<HoaDonDTO> all = hdBus.getAllHoaDon();
        ArrayList<HoaDonDTO> filtered = new ArrayList<>();

        // 4) Loop, applying both date and text filters
        for (HoaDonDTO hd : all) {
            // --- date filter ---
            LocalDate invDate = hd.getNgayLapHD().toLocalDate();
            boolean dateOk = true;
            if (ldFrom != null && invDate.isBefore(ldFrom)) dateOk = false;
            if (ldTo   != null && invDate.isAfter(ldTo))   dateOk = false;
            if (!dateOk) continue;

            // --- text filter ---
            boolean textOk = raw.isEmpty();
            if (!textOk) {
                // your OR/AND logic
                String[] orClauses = raw.split("(?i)\\s+OR\\s+");
                outer: for (String orC : orClauses) {
                    String[] andClauses = orC.split("(?i)\\s+AND\\s+");
                    boolean andAll = true;
                    for (String term : andClauses) {
                        term = term.trim();
                        boolean negate = term.startsWith("NOT ");
                        String core = negate ? term.substring(4).trim() : term;
                        boolean m = matchesSingle(hd, core);
                        if (negate) m = !m;
                        if (!m) {
                            andAll = false;
                            break;
                        }
                    }
                    if (andAll) {
                        textOk = true;
                        break outer;
                    }
                }
            }

            // --- add if both pass ---
            if (textOk) {
                filtered.add(hd);
            }
        }

        // 5) Refresh table
        DefaultTableModel model = (DefaultTableModel) bangHD.getModel();
        model.setRowCount(0);
        for (HoaDonDTO hd : filtered) {
            model.addRow(new Object[]{
                hd.getMaHD(), hd.getMaKH(), hd.getMaNV(),
                hd.getNgayLapHD(), hd.getTongTien()
            });
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        String helpText = """
        Bạn có thể tìm kiếm với cú pháp sau:

        - Các trường: mahd, makh, manv, tongtien dùng với các toán tử =, !=, >, <, >=, <=
          VD: manv>2, tongtien<=15000.50

        - Có thể kết hợp nhiều điều kiện bằng:
            AND, OR, NOT 
            VD: mahd=1 AND makh=2
                makh=1 AND NOT mahd=2 OR manv=2 
                (tương đương với: (makh=1 AND NOT mahd=2) OR manv=2)

        - Nếu không nhập đúng định dạng trên, tất cả kết quả của hóa đơn, khách hàng, nhân viên sẽ được hiển thị.
        """;

    JOptionPane.showMessageDialog(this, helpText, "Hướng Dẫn Tìm Kiếm", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_helpActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        from.setDate(null);
        to.setDate(null);
        updateTable();
    }//GEN-LAST:event_clearButtonActionPerformed
    
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            javax.swing.JFrame frame = new javax.swing.JFrame("Test HoaDonPanel");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new HoaDonPanel());
            frame.pack(); // Optional: sizes the frame to fit preferred size of contents
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true); // Show the frame
        }
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTable bangCHD;
    private javax.swing.JTable bangHD;
    private javax.swing.JLabel chdtitle;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton edit;
    private javax.swing.JLabel hdtitle;
    private javax.swing.JButton help;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton remove;
    private javax.swing.JTextField search;
    private javax.swing.JButton searchButton;
    private java.awt.Label title;
    // End of variables declaration//GEN-END:variables
}

package GUI.MainPanel;

import BUS.SanPhammBUS;
import DTO.SanPhammDTO;
import DBConnect.Connector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Simple read-only panel that lists products from `sanpham`.
 * Shows columns: MaSP, TenSP, SoLuong, DonGia
 */
public class KhoHangPanel extends JPanel {
    private SanPhammBUS spBus;
    private JTable table;
    private DefaultTableModel model;

    public KhoHangPanel() {
        setLayout(new BorderLayout(8,8));
        initBUS();
        initComponents();
        loadData();
    }

    private void initBUS() {
        try {
            Connection conn = Connector.getConnection(); // assumes Connector.getConnection() exists
            spBus = new SanPhammBUS(conn);
        } catch (Exception e) {
            // fallback: show error but allow UI to initialize
            spBus = null;
            System.err.println("Không thể kết nối DB trong KhoHangPanel: " + e.getMessage());
        }
    }

    private void initComponents() {
        JLabel title = new JLabel("Kho hàng - Danh sách sản phẩm", JLabel.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[] { "Mã SP", "Tên SP", "Số lượng", "Đơn giá" }, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setBackground(new Color(255,255,240));

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(44,62,80));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Dialog", Font.BOLD, 13));

        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        setPreferredSize(new Dimension(900, 600));
    }

    private void loadData() {
        model.setRowCount(0);
        if (spBus == null) return;
        try {
            ArrayList<SanPhammDTO> list = spBus.layDSSanPham();
            for (SanPhammDTO sp : list) {
                Object[] row = new Object[] {
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getSoLuong(),
                    sp.getDonGia()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu sản phẩm:\n" + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
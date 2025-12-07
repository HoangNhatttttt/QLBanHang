package GUI.MainPanel;

import BUS.SanPhammBUS;
import DTO.SanPhammDTO;
import DBConnect.Connector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple read-only panel that lists products from `sanpham`.
 * Shows columns: MaSP, TenSP, SoLuong, DonVi
 */
public class KhoHangPanel extends JPanel {
    private SanPhammBUS spBus;
    private JTable table;
    private DefaultTableModel model;
    private List<SanPhammDTO> spList = new ArrayList<>(); // cached list for lookup
    private JLabel imageLabel;

    public KhoHangPanel() {
        // keep a clean BorderLayout and small gaps like other panels
        setLayout(new BorderLayout(8, 8));
        setBackground(Color.WHITE);
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
        // Title area (styled consistently with other panels)
        JLabel title = new JLabel("Kho hàng - Danh sách sản phẩm", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(44, 62, 80));
        title.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        add(title, BorderLayout.NORTH);

        // Table model - read only
        model = new DefaultTableModel(new Object[]{"Mã SP", "Tên SP", "Số lượng", "Đơn vị"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        // Table
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(36);
        table.setBackground(new Color(255, 255, 240));
        table.setSelectionBackground(new Color(187, 222, 251));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Header styling - match other panels (dark header, readable text)
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(new Color(0, 0, 0));
        header.setFont(new Font("Dialog", Font.BOLD, 14));

        // Scroll pane (left)
        JScrollPane leftScroll = new JScrollPane(table);
        leftScroll.getViewport().setBackground(Color.WHITE);

        // Image preview (right)
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(340, 340));
        imageLabel.setBorder(BorderFactory.createTitledBorder("Hình ảnh"));
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(imageLabel, BorderLayout.NORTH);

        // Split pane so panel looks consistent with other panels
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll, rightPanel);
        split.setResizeWeight(0.75);
        split.setOneTouchExpandable(true);
        add(split, BorderLayout.CENTER);

        setPreferredSize(new Dimension(900, 600));

        // Click handler: load image for selected product
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) return;
                String maSP = String.valueOf(model.getValueAt(row, 0));
                // find DTO
                SanPhammDTO found = null;
                for (SanPhammDTO s : spList) {
                    if (s != null && maSP.equals(String.valueOf(s.getMaSP()))) {
                        found = s;
                        break;
                    }
                }
                if (found == null) {
                    imageLabel.setIcon(null);
                    return;
                }
                String path = found.getHinhAnh();
                if (path == null || path.trim().isEmpty()) {
                    imageLabel.setIcon(null);
                    return;
                }
                // try multiple ways to load image (absolute, relative, resource)
                BufferedImage img = null;
                try {
                    File f = new File(path);
                    if (f.exists()) {
                        img = ImageIO.read(f);
                    } else {
                        // try relative to working dir
                        File f2 = new File(System.getProperty("user.dir"), path);
                        if (f2.exists()) img = ImageIO.read(f2);
                        else {
                            // try as resource on classpath
                            java.net.URL url = getClass().getClassLoader().getResource(path.replace('\\','/'));
                            if (url != null) img = ImageIO.read(url);
                        }
                    }
                } catch (Exception ex) {
                    img = null;
                }
                if (img == null) {
                    imageLabel.setIcon(null);
                    return;
                }
                // scale image preserving aspect ratio
                int w = imageLabel.getPreferredSize().width - 12;
                int h = imageLabel.getPreferredSize().height - 12;
                Image scaled = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaled));
            }
        });
    }

    private void loadData() {
        model.setRowCount(0);
        spList.clear();
        if (spBus == null) return;
        try {
            ArrayList<SanPhammDTO> list = spBus.layDSSanPham();
            if (list != null) {
                spList.addAll(list);
                for (SanPhammDTO sp : list) {
                    Object[] row = new Object[]{
                        sp.getMaSP(),
                        sp.getTenSP(),
                        sp.getSoLuong(),
                        sp.getDonVi()
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu sản phẩm:\n" + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
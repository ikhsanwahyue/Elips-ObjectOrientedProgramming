package pElips.gui;

import pElips.model.BendaGeometri;
import pElips.model.Bola;
import pElips.model.Cincin;
import pElips.model.Elips;
import pElips.model.Juring;
import pElips.model.KerucutDenganAlasElips;
import pElips.model.KerucutTerpancungDenganAlasElips;
import pElips.model.Tabung;
import pElips.model.Tembereng;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class MainFrame extends JFrame {
    private static final int DATA_PER_BENDA = 100000;
    private static final String[] NAMA_KATEGORI = {
            "Elips", "Bola", "Tabung", "Kerucut Alas Elips",
            "Kerucut Terpancung", "Cincin", "Juring", "Tembereng"
    };
    private static final String[] ASSET_KATEGORI = {
            "elips.png", "bola.png", "tabung.png", "kerucut.png",
            "kerucut_terpancung.png", "cincin.png", "juring.png", "tembereng.png"
    };
    private static final int JUMLAH_KATEGORI = NAMA_KATEGORI.length;
    private static final int TOTAL_DATA_DEMO = DATA_PER_BENDA * JUMLAH_KATEGORI;

    private static final Color NAVY = new Color(3, 28, 69);
    private static final Color NAVY_DARK = new Color(2, 18, 47);
    private static final Color BLUE = new Color(20, 103, 236);
    private static final Color BG = new Color(242, 247, 253);
    private static final Color BORDER = new Color(210, 223, 241);
    private static final Color TEXT = new Color(7, 28, 72);

    private JComboBox<String> cmbJenisBenda;
    private JTextField txtInput1;
    private JTextField txtInput2;
    private JTextField txtInput3;
    private JTextField txtInput4;
    private JTextField txtInput5;
    private JLabel lblInput1;
    private JLabel lblInput2;
    private JLabel lblInput3;
    private JLabel lblInput4;
    private JLabel lblInput5;
    private JTextArea txtOutput;
    private JButton btnHitung;
    private JButton btnDemoThread;
    private JTable tblData;
    private GeometriTableModel tableModel;
    private BarRacePanel barRacePanel;
    private JLabel lblStatusThread;
    private JLabel lblStatusData;
    private JLabel lblStatusWaktu;

    public MainFrame() {
        setTitle("Aplikasi Geometri Elips - OOP dan Multithreading");
        setSize(1500, 860);
        setMinimumSize(new Dimension(1180, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        handleEvent();
        sesuaikanInput();
    }

    private void initComponents() {
        UIManager.put("ComboBox.selectionBackground", new Color(226, 237, 255));
        UIManager.put("ComboBox.selectionForeground", TEXT);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        setContentPane(root);

        JPanel mainArea = new JPanel(new BorderLayout(12, 0));
        mainArea.setBackground(BG);
        mainArea.setBorder(new EmptyBorder(12, 12, 12, 12));

        mainArea.add(buatSidebar(), BorderLayout.WEST);
        mainArea.add(buatKontenUtama(), BorderLayout.CENTER);

        root.add(mainArea, BorderLayout.CENTER);
        root.add(buatStatusBar(), BorderLayout.SOUTH);
    }

    private JPanel buatSidebar() {
        RoundedPanel sidebar = new RoundedPanel(new BorderLayout(12, 12), 12, Color.WHITE, BORDER);
        sidebar.setPreferredSize(new Dimension(340, 0));
        sidebar.setBorder(new EmptyBorder(18, 18, 16, 18));

        JPanel header = new JPanel(new BorderLayout(12, 0));
        header.setOpaque(false);
        header.add(new HeaderIcon(HeaderIcon.CUBE), BorderLayout.WEST);

        JPanel headerText = new JPanel(new GridLayout(2, 1, 0, 0));
        headerText.setOpaque(false);
        JLabel title = new JLabel("Form Input Geometri");
        title.setFont(new Font("SansSerif", Font.BOLD, 17));
        title.setForeground(TEXT);
        JLabel subtitle = new JLabel("Konfigurasi parameter benda geometri");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setForeground(new Color(76, 95, 138));
        headerText.add(title);
        headerText.add(subtitle);
        header.add(headerText, BorderLayout.CENTER);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setOpaque(false);
        tambahFormComponent(form, buatLabel("Jenis Benda"), 20);
        cmbJenisBenda = new JComboBox<>(new String[] {
                "Elips (2D)", "Bola", "Tabung", "Kerucut Alas Elips",
                "Kerucut Terpancung", "Cincin", "Juring", "Tembereng"
        });
        cmbJenisBenda.setFont(new Font("SansSerif", Font.BOLD, 13));
        cmbJenisBenda.setForeground(TEXT);
        cmbJenisBenda.setBackground(Color.WHITE);
        cmbJenisBenda.setBorder(BorderFactory.createLineBorder(BORDER));
        tambahFormComponent(form, cmbJenisBenda, 40);

        lblInput1 = buatLabel("Input 1");
        txtInput1 = buatTextField();
        lblInput2 = buatLabel("Input 2");
        txtInput2 = buatTextField();
        lblInput3 = buatLabel("Input 3");
        txtInput3 = buatTextField();
        lblInput4 = buatLabel("Input 4");
        txtInput4 = buatTextField();
        lblInput5 = buatLabel("Input 5");
        txtInput5 = buatTextField();

        tambahFormComponent(form, lblInput1, 20);
        tambahFormComponent(form, txtInput1, 40);
        tambahFormComponent(form, lblInput2, 20);
        tambahFormComponent(form, txtInput2, 40);
        tambahFormComponent(form, lblInput3, 20);
        tambahFormComponent(form, txtInput3, 40);
        tambahFormComponent(form, lblInput4, 20);
        tambahFormComponent(form, txtInput4, 40);
        tambahFormComponent(form, lblInput5, 20);
        tambahFormComponent(form, txtInput5, 40);

        JPanel preview = buatPreviewPanel();

        JPanel top = new JPanel(new BorderLayout(0, 18));
        top.setOpaque(false);
        top.add(header, BorderLayout.NORTH);
        top.add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(2, 1, 0, 10));
        buttons.setOpaque(false);
        btnHitung = new RoundedButton("Hitung dan Tampilkan", BLUE, Color.WHITE, true);
        btnDemoThread = new RoundedButton("Demo Multithreading\n100.000 Data per Benda", Color.WHITE, BLUE, false);
        buttons.add(btnHitung);
        buttons.add(btnDemoThread);

        sidebar.add(top, BorderLayout.NORTH);
        sidebar.add(preview, BorderLayout.CENTER);
        sidebar.add(buttons, BorderLayout.SOUTH);
        return sidebar;
    }

    private JLabel buatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        return label;
    }

    private JTextField buatTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setForeground(TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(8, 10, 8, 10)));
        return field;
    }

    private void tambahFormComponent(JPanel form, JComponent component, int tinggi) {
        component.setAlignmentX(Component.LEFT_ALIGNMENT);
        component.setMaximumSize(new Dimension(Integer.MAX_VALUE, tinggi));
        form.add(component);
        form.add(Box.createVerticalStrut(8));
    }

    private JPanel buatPreviewPanel() {
        RoundedPanel preview = new RoundedPanel(new BorderLayout(8, 10), 9, Color.WHITE, BORDER);
        preview.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel titlePanel = new JPanel(new BorderLayout(8, 0));
        titlePanel.setOpaque(false);
        titlePanel.add(new SmallOutlineIcon(SmallOutlineIcon.CUBE), BorderLayout.WEST);
        JLabel label = new JLabel("Preview Benda Geometri");
        label.setForeground(BLUE);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        titlePanel.add(label, BorderLayout.CENTER);

        JPanel grid = new JPanel(new GridLayout(0, 2, 8, 8));
        grid.setOpaque(false);
        for (int i = 0; i < JUMLAH_KATEGORI; i++) {
            grid.add(new PreviewCard(i));
        }

        JScrollPane scroll = new JScrollPane(grid);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(12);

        preview.add(titlePanel, BorderLayout.NORTH);
        preview.add(scroll, BorderLayout.CENTER);
        return preview;
    }

    private JPanel buatKontenUtama() {
        JPanel content = new JPanel(new BorderLayout(12, 12));
        content.setOpaque(false);

        RoundedPanel chartCard = new RoundedPanel(new BorderLayout(), 12, Color.WHITE, BORDER);
        chartCard.setBorder(new EmptyBorder(8, 14, 8, 14));
        barRacePanel = new BarRacePanel();
        barRacePanel.setPreferredSize(new Dimension(900, 430));
        chartCard.add(barRacePanel, BorderLayout.CENTER);

        JSplitPane bottomSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buatLogCard(), buatTableCard());
        bottomSplit.setResizeWeight(0.28);
        bottomSplit.setBorder(BorderFactory.createEmptyBorder());
        bottomSplit.setDividerSize(10);
        bottomSplit.setOpaque(false);

        content.add(chartCard, BorderLayout.CENTER);
        content.add(bottomSplit, BorderLayout.SOUTH);
        bottomSplit.setPreferredSize(new Dimension(900, 310));
        return content;
    }

    private JPanel buatLogCard() {
        RoundedPanel card = new RoundedPanel(new BorderLayout(8, 10), 12, Color.WHITE, BORDER);
        card.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel header = new JPanel(new BorderLayout(8, 0));
        header.setOpaque(false);
        header.add(new SmallSolidIcon(SmallSolidIcon.TERMINAL), BorderLayout.WEST);
        JLabel title = new JLabel("Hasil Perhitungan / Log Sistem");
        title.setFont(new Font("SansSerif", Font.BOLD, 13));
        title.setForeground(TEXT);
        header.add(title, BorderLayout.CENTER);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setLineWrap(false);
        txtOutput.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtOutput.setForeground(new Color(132, 255, 154));
        txtOutput.setBackground(new Color(0, 23, 54));
        txtOutput.setCaretColor(Color.WHITE);
        txtOutput.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(txtOutput);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(10, 42, 86)));
        scroll.getViewport().setBackground(new Color(0, 23, 54));

        card.add(header, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);
        return card;
    }

    private JPanel buatTableCard() {
        RoundedPanel card = new RoundedPanel(new BorderLayout(8, 10), 12, Color.WHITE, BORDER);
        card.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel header = new JPanel(new BorderLayout(10, 0));
        header.setOpaque(false);

        JPanel titlePanel = new JPanel(new BorderLayout(8, 0));
        titlePanel.setOpaque(false);
        titlePanel.add(new SmallSolidIcon(SmallSolidIcon.DATA), BorderLayout.WEST);
        JLabel title = new JLabel("Data Massal 800.000 Objek");
        title.setFont(new Font("SansSerif", Font.BOLD, 13));
        title.setForeground(TEXT);
        titlePanel.add(title, BorderLayout.CENTER);

        JPanel tools = new JPanel(new BorderLayout(8, 0));
        tools.setOpaque(false);
        JTextField search = buatSearchField();
        JButton filter = new IconOnlyButton();
        tools.add(search, BorderLayout.CENTER);
        tools.add(filter, BorderLayout.EAST);

        header.add(titlePanel, BorderLayout.WEST);
        header.add(tools, BorderLayout.EAST);

        tableModel = new GeometriTableModel();
        tblData = new JTable(tableModel);
        tblData.setRowHeight(28);
        tblData.setShowGrid(true);
        tblData.setGridColor(new Color(225, 234, 246));
        tblData.setForeground(TEXT);
        tblData.setBackground(Color.WHITE);
        tblData.setSelectionBackground(new Color(222, 236, 255));
        tblData.setSelectionForeground(TEXT);
        tblData.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tblData.setFillsViewportHeight(true);

        JTableHeader tableHeader = tblData.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(0, 30));
        tableHeader.setBackground(new Color(245, 249, 254));
        tableHeader.setForeground(TEXT);
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 12));

        DefaultTableCellRenderer normalRenderer = new DefaultTableCellRenderer();
        normalRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblData.setDefaultRenderer(Object.class, new SoftTableRenderer());
        tblData.getColumnModel().getColumn(0).setPreferredWidth(46);
        tblData.getColumnModel().getColumn(1).setPreferredWidth(96);
        tblData.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblData.getColumnModel().getColumn(6).setPreferredWidth(160);
        tblData.getColumnModel().getColumn(6).setCellRenderer(new ProgressRenderer());
        tblData.getColumnModel().getColumn(7).setPreferredWidth(110);
        tblData.getColumnModel().getColumn(7).setCellRenderer(new StatusRenderer());

        JScrollPane scroll = new JScrollPane(tblData);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 234, 246)));
        JScrollBar bar = scroll.getVerticalScrollBar();
        bar.setUnitIncrement(18);

        card.add(header, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);
        return card;
    }

    private JTextField buatSearchField() {
        JTextField field = new JTextField("Cari data...");
        field.setPreferredSize(new Dimension(210, 28));
        field.setForeground(new Color(136, 151, 182));
        field.setFont(new Font("SansSerif", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(4, 10, 4, 10)));
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if ("Cari data...".equals(field.getText())) {
                    field.setText("");
                    field.setForeground(TEXT);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().trim().isEmpty()) {
                    field.setText("Cari data...");
                    field.setForeground(new Color(136, 151, 182));
                }
            }
        });
        return field;
    }

    private JPanel buatStatusBar() {
        JPanel status = new JPanel(new BorderLayout());
        status.setBackground(NAVY_DARK);
        status.setPreferredSize(new Dimension(0, 52));
        status.setBorder(new EmptyBorder(0, 16, 0, 18));

        JPanel left = new JPanel(new BorderLayout(12, 0));
        left.setOpaque(false);
        left.add(new StatusOkIcon(), BorderLayout.WEST);
        lblStatusThread = new JLabel("Status: belum berjalan");
        lblStatusThread.setForeground(Color.WHITE);
        lblStatusThread.setFont(new Font("SansSerif", Font.BOLD, 13));
        left.add(lblStatusThread, BorderLayout.CENTER);

        JPanel right = new JPanel(new GridLayout(1, 3, 26, 0));
        right.setOpaque(false);
        JLabel threads = statusItem("Threads: Optimal");
        lblStatusData = statusItem("Data: 0");
        lblStatusWaktu = statusItem("Waktu: 0 ms");
        right.add(threads);
        right.add(lblStatusData);
        right.add(lblStatusWaktu);

        status.add(left, BorderLayout.WEST);
        status.add(right, BorderLayout.EAST);
        return status;
    }

    private JLabel statusItem(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(225, 235, 255));
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return label;
    }

    private void sesuaikanInput() {
        int dipilih = cmbJenisBenda.getSelectedIndex();
        tampilkanField(1);

        switch (dipilih) {
            case 0:
                lblInput1.setText("Sumbu A");
                lblInput2.setText("Sumbu B");
                tampilkanField(2);
                break;
            case 1:
                lblInput1.setText("Jari-jari (r)");
                break;
            case 2:
                lblInput1.setText("Jari-jari Alas (r)");
                lblInput2.setText("Tinggi");
                tampilkanField(2);
                break;
            case 3:
                lblInput1.setText("Sumbu A");
                lblInput2.setText("Sumbu B");
                lblInput3.setText("Tinggi");
                tampilkanField(3);
                break;
            case 4:
                lblInput1.setText("Sumbu a1");
                lblInput2.setText("Sumbu b1");
                lblInput3.setText("Sumbu a2");
                lblInput4.setText("Sumbu b2");
                lblInput5.setText("Tinggi");
                tampilkanField(5);
                break;
            case 5:
                lblInput1.setText("Jari-jari Mayor (R)");
                lblInput2.setText("Jari-jari Minor (r)");
                tampilkanField(2);
                break;
            case 6:
            case 7:
                lblInput1.setText("Jari-jari Bola (r)");
                lblInput2.setText("Tinggi Topi (h)");
                tampilkanField(2);
                break;
            default:
                break;
        }
        revalidate();
        repaint();
    }

    private void tampilkanField(int jumlah) {
        JLabel[] labels = { lblInput1, lblInput2, lblInput3, lblInput4, lblInput5 };
        JTextField[] fields = { txtInput1, txtInput2, txtInput3, txtInput4, txtInput5 };
        for (int i = 0; i < labels.length; i++) {
            boolean tampil = i < jumlah;
            labels[i].setVisible(tampil);
            fields[i].setVisible(tampil);
            if (!tampil) {
                fields[i].setText("");
            }
        }
    }

    private void handleEvent() {
        cmbJenisBenda.addActionListener(e -> sesuaikanInput());

        btnHitung.addActionListener(e -> {
            try {
                BendaGeometri benda = dapatkanObjekBenda();
                benda.hitungSemua();
                tampilkanHasilManual(benda);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDemoThread.addActionListener(e -> jalankanDemoMultithreading());
    }

    private BendaGeometri dapatkanObjekBenda() {
        int jenis = cmbJenisBenda.getSelectedIndex();

        switch (jenis) {
            case 0:
                return new Elips("GUI Elips", bacaNilai(txtInput1, "Sumbu A"), bacaNilai(txtInput2, "Sumbu B"));
            case 1:
                return new Bola("GUI Bola", bacaNilai(txtInput1, "Jari-jari"));
            case 2:
                return new Tabung("GUI Tabung", bacaNilai(txtInput1, "Jari-jari alas"),
                        bacaNilai(txtInput2, "Tinggi"));
            case 3:
                return new KerucutDenganAlasElips("GUI Kerucut Alas Elips", bacaNilai(txtInput1, "Sumbu A"),
                        bacaNilai(txtInput2, "Sumbu B"), bacaNilai(txtInput3, "Tinggi"));
            case 4:
                return new KerucutTerpancungDenganAlasElips("GUI Kerucut Terpancung",
                        bacaNilai(txtInput1, "Sumbu A bawah"), bacaNilai(txtInput2, "Sumbu B bawah"),
                        bacaNilai(txtInput3, "Sumbu A atas"), bacaNilai(txtInput4, "Sumbu B atas"),
                        bacaNilai(txtInput5, "Tinggi"));
            case 5:
                return new Cincin("GUI Cincin", bacaNilai(txtInput1, "Jari-jari mayor"),
                        bacaNilai(txtInput2, "Jari-jari minor"));
            case 6:
                return new Juring("GUI Juring", bacaNilai(txtInput1, "Jari-jari bola"),
                        bacaNilai(txtInput2, "Tinggi topi"));
            case 7:
                return new Tembereng("GUI Tembereng", bacaNilai(txtInput1, "Jari-jari bola"),
                        bacaNilai(txtInput2, "Tinggi topi"));
            default:
                throw new IllegalArgumentException("Jenis benda tidak valid.");
        }
    }

    private double bacaNilai(JTextField field, String namaInput) {
        String teks = field.getText().trim();
        if (teks.isEmpty()) {
            throw new IllegalArgumentException(namaInput + " harus diisi.");
        }

        try {
            return Double.parseDouble(teks);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(namaInput + " harus berupa angka.");
        }
    }

    private void tampilkanHasilManual(BendaGeometri benda) {
        txtOutput.setText("=== HASIL PERHITUNGAN ===\n");
        txtOutput.append("Nama Objek : " + benda.getNamaBenda() + "\n");
        txtOutput.append("Jenis      : " + benda.getClass().getSimpleName() + "\n");
        txtOutput.append(detailDimensi(benda));
        txtOutput.append("Luas       : " + format(benda.getLuas()) + "\n");
        txtOutput.append("Keliling   : " + format(benda.getKeliling()) + "\n");
        txtOutput.append("Volume     : " + format(benda.getVolume()) + "\n");
        txtOutput.append("Status     : " + benda.getStatusProses() + "\n");
    }

    private String detailDimensi(BendaGeometri benda) {
        StringBuilder detail = new StringBuilder();

        if (benda instanceof KerucutTerpancungDenganAlasElips) {
            KerucutTerpancungDenganAlasElips k = (KerucutTerpancungDenganAlasElips) benda;
            detail.append("a1 / b1    : ").append(format(k.getSumbuA())).append(" / ")
                    .append(format(k.getSumbuB())).append("\n");
            detail.append("a2 / b2    : ").append(format(k.getSumbuA2())).append(" / ")
                    .append(format(k.getSumbuB2())).append("\n");
            detail.append("Tinggi     : ").append(format(k.getTinggi())).append("\n");
            detail.append("Selimut    : ").append(format(k.getLuasSelimutTerpancung())).append("\n");
        } else if (benda instanceof KerucutDenganAlasElips) {
            KerucutDenganAlasElips k = (KerucutDenganAlasElips) benda;
            detail.append("Sumbu A/B  : ").append(format(k.getSumbuA())).append(" / ")
                    .append(format(k.getSumbuB())).append("\n");
            detail.append("Tinggi     : ").append(format(k.getTinggi())).append("\n");
            detail.append("Selimut    : ").append(format(k.getLuasSelimut())).append("\n");
        } else if (benda instanceof Elips) {
            Elips e = (Elips) benda;
            detail.append("Sumbu A/B  : ").append(format(e.getSumbuA())).append(" / ")
                    .append(format(e.getSumbuB())).append("\n");
        } else if (benda instanceof Tabung) {
            Tabung t = (Tabung) benda;
            detail.append("Jari-jari  : ").append(format(t.getJariJari())).append("\n");
            detail.append("Tinggi     : ").append(format(t.getTinggi())).append("\n");
            detail.append("Luas Alas  : ").append(format(t.getLuasAlas())).append("\n");
            detail.append("Selimut    : ").append(format(t.getLuasSelimut())).append("\n");
        } else if (benda instanceof Cincin) {
            Cincin c = (Cincin) benda;
            detail.append("R / r      : ").append(format(c.getJariJariMayor())).append(" / ")
                    .append(format(c.getJariJariMinor())).append("\n");
        } else if (benda instanceof Juring) {
            Juring j = (Juring) benda;
            detail.append("Jari-jari  : ").append(format(j.getJariJari())).append("\n");
            detail.append("Tinggi h   : ").append(format(j.getTinggiTopi())).append("\n");
            detail.append("Radius alas: ").append(format(j.getJariJariAlas())).append("\n");
        } else if (benda instanceof Tembereng) {
            Tembereng t = (Tembereng) benda;
            detail.append("Jari-jari  : ").append(format(t.getJariJari())).append("\n");
            detail.append("Tinggi h   : ").append(format(t.getTinggiTopi())).append("\n");
            detail.append("Radius alas: ").append(format(t.getJariJariAlas())).append("\n");
        } else if (benda instanceof Bola) {
            Bola b = (Bola) benda;
            detail.append("Jari-jari  : ").append(format(b.getJariJari())).append("\n");
            detail.append("Diameter   : ").append(format(b.getDiameter())).append("\n");
        }

        return detail.toString();
    }

    private void jalankanDemoMultithreading() {
        btnDemoThread.setEnabled(false);
        resetVisualisasi();
        tableModel.setData(new ArrayList<>());
        txtOutput.setText("=== MEMULAI DEMO MULTITHREADING ===\n");
        txtOutput.append("Membuat " + formatInteger(DATA_PER_BENDA) + " data untuk tiap benda geometri...\n");
        txtOutput.append("Total data yang dibuat: " + formatInteger(TOTAL_DATA_DEMO) + " objek.\n");

        SwingWorker<DemoResult, Void> worker = new SwingWorker<DemoResult, Void>() {
            @Override
            protected DemoResult doInBackground() throws Exception {
                List<BendaGeometri> dataMassal = buatDataMassalPerBenda();
                SwingUtilities.invokeLater(() -> {
                    tableModel.setData(dataMassal);
                    txtOutput.append("Tabel sudah berisi " + formatInteger(dataMassal.size())
                            + " data. Setiap benda selesai dalam batch kategorinya.\n");
                });

                AtomicIntegerArray kategoriDone = new AtomicIntegerArray(JUMLAH_KATEGORI);
                AtomicInteger totalDone = new AtomicInteger();
                CountDownLatch latch = new CountDownLatch(JUMLAH_KATEGORI);
                ExecutorService executor = Executors.newFixedThreadPool(JUMLAH_KATEGORI);

                long mulai = System.currentTimeMillis();
                for (int i = 0; i < JUMLAH_KATEGORI; i++) {
                    final int kategoriIndex = i;
                    final int fromIndex = kategoriIndex * DATA_PER_BENDA;
                    final int toIndex = fromIndex + DATA_PER_BENDA;

                    executor.execute(() -> {
                        try {
                            for (int j = fromIndex; j < toIndex; j++) {
                                dataMassal.get(j).run();
                                kategoriDone.incrementAndGet(kategoriIndex);
                                totalDone.incrementAndGet();
                            }
                        } finally {
                            latch.countDown();
                        }
                    });
                }

                try {
                    while (!latch.await(120, TimeUnit.MILLISECONDS)) {
                        jadwalkanUpdateVisual(totalDone, kategoriDone);
                    }
                } catch (InterruptedException ex) {
                    executor.shutdownNow();
                    Thread.currentThread().interrupt();
                    throw ex;
                } finally {
                    executor.shutdown();
                }

                executor.awaitTermination(1, TimeUnit.MINUTES);
                jadwalkanUpdateVisual(totalDone, kategoriDone);
                return new DemoResult(dataMassal.size(), System.currentTimeMillis() - mulai);
            }

            @Override
            protected void done() {
                try {
                    DemoResult result = get();
                    int[] selesai = new int[JUMLAH_KATEGORI];
                    Arrays.fill(selesai, DATA_PER_BENDA);
                    barRacePanel.setData(selesai, TOTAL_DATA_DEMO, result.durasiMs, false);
                    lblStatusThread.setText("Status: selesai memproses " + formatInteger(result.jumlahData)
                            + " data dalam " + result.durasiMs + " ms");
                    lblStatusData.setText("Data: " + formatInteger(result.jumlahData));
                    lblStatusWaktu.setText("Waktu: " + result.durasiMs + " ms");
                    tableModel.refreshAllData();
                    txtOutput.append("Selesai dalam " + result.durasiMs + " ms.\n");
                    txtOutput.append("Total " + formatInteger(result.jumlahData)
                            + " data, dengan " + formatInteger(DATA_PER_BENDA) + " data per benda.\n");
                    txtOutput.append("Bar visual menunjukkan jumlah data selesai.\n");
                    txtOutput.append("\n=== DEMO SELESAI ===\n");
                } catch (Exception ex) {
                    txtOutput.append("Proses gagal: " + ex.getMessage() + "\n");
                    lblStatusThread.setText("Status: gagal");
                } finally {
                    btnDemoThread.setEnabled(true);
                }
            }
        };

        worker.execute();
    }

    private List<BendaGeometri> buatDataMassalPerBenda() {
        List<BendaGeometri> data = new ArrayList<>(TOTAL_DATA_DEMO);
        Random rand = new Random();

        for (int kategori = 0; kategori < JUMLAH_KATEGORI; kategori++) {
            for (int nomor = 1; nomor <= DATA_PER_BENDA; nomor++) {
                data.add(buatObjekAcak(kategori, nomor, rand));
            }
        }

        return data;
    }

    private BendaGeometri buatObjekAcak(int kategori, int nomor, Random rand) {
        double a = acak(rand, 8, 60);
        double b = acak(rand, 4, 35);
        double r = acak(rand, 4, 45);
        double tinggi = acak(rand, 5, 70);
        String nama = NAMA_KATEGORI[kategori] + "-" + nomor;

        switch (kategori) {
            case 0:
                return new Elips(nama, a, b);
            case 1:
                return new Bola(nama, r);
            case 2:
                return new Tabung(nama, r, tinggi);
            case 3:
                return new KerucutDenganAlasElips(nama, a, b, tinggi);
            case 4:
                return new KerucutTerpancungDenganAlasElips(nama, a, b,
                        a * acak(rand, 0.25, 0.75), b * acak(rand, 0.25, 0.75), tinggi);
            case 5:
                double minor = acak(rand, 2, 12);
                return new Cincin(nama, minor + acak(rand, 5, 30), minor);
            case 6:
                return new Juring(nama, r, acak(rand, 0.5, 2 * r));
            case 7:
                return new Tembereng(nama, r, acak(rand, 0.5, 2 * r));
            default:
                return new Bola(nama, r);
        }
    }

    private double acak(Random rand, double min, double max) {
        return min + rand.nextDouble() * (max - min);
    }

    private void jadwalkanUpdateVisual(AtomicInteger totalDone, AtomicIntegerArray kategoriDone) {
        int totalSnapshot = totalDone.get();
        int[] kategoriSnapshot = new int[JUMLAH_KATEGORI];
        for (int i = 0; i < JUMLAH_KATEGORI; i++) {
            kategoriSnapshot[i] = kategoriDone.get(i);
        }

        SwingUtilities.invokeLater(() -> perbaruiVisual(totalSnapshot, kategoriSnapshot));
    }

    private void perbaruiVisual(int totalDone, int[] kategoriDone) {
        barRacePanel.setData(kategoriDone, totalDone, 0L, true);
        lblStatusThread.setText("Status: " + formatInteger(totalDone) + " dari "
                + formatInteger(TOTAL_DATA_DEMO) + " data selesai");
        lblStatusData.setText("Data: " + formatInteger(totalDone));
        tableModel.refreshData();
    }

    private void resetVisualisasi() {
        barRacePanel.reset();
        lblStatusThread.setText("Status: menyiapkan 800.000 data");
        lblStatusData.setText("Data: 0");
        lblStatusWaktu.setText("Waktu: 0 ms");
    }

    private static String format(double nilai) {
        return String.format("%.4f", nilai);
    }

    private static String formatInteger(int nilai) {
        return String.format("%,d", nilai).replace(',', '.');
    }

    private static BufferedImage loadAsset(String assetName) {
        try {
            java.net.URL url = MainFrame.class.getResource("/pElips/assets/" + assetName);
            if (url != null) {
                return ImageIO.read(url);
            }

            File sourceAsset = new File("src/pElips/assets/" + assetName);
            if (sourceAsset.exists()) {
                return ImageIO.read(sourceAsset);
            }

            File buildAsset = new File("build/classes/pElips/assets/" + assetName);
            if (buildAsset.exists()) {
                return ImageIO.read(buildAsset);
            }
        } catch (IOException | IllegalArgumentException ex) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

    private static class DemoResult {
        private final int jumlahData;
        private final long durasiMs;

        private DemoResult(int jumlahData, long durasiMs) {
            this.jumlahData = jumlahData;
            this.durasiMs = durasiMs;
        }
    }

    private class PreviewCard extends RoundedPanel {
        private final BufferedImage image;

        PreviewCard(int index) {
            super(new BorderLayout(4, 4), 9, Color.WHITE, BORDER);
            this.image = loadAsset(ASSET_KATEGORI[index]);
            setBorder(new EmptyBorder(7, 7, 7, 7));
            setPreferredSize(new Dimension(124, 104));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            ImagePreview preview = new ImagePreview(image);
            JLabel label = new JLabel(toPreviewLabel(NAMA_KATEGORI[index]), SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 12));
            label.setForeground(TEXT);
            add(preview, BorderLayout.CENTER);
            add(label, BorderLayout.SOUTH);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cmbJenisBenda.setSelectedIndex(index);
                }
            });
        }
    }

    private static String toPreviewLabel(String label) {
        if ("Kerucut Alas Elips".equals(label)) {
            return "<html><center>Kerucut<br>Alas Elips</center></html>";
        }
        if ("Kerucut Terpancung".equals(label)) {
            return "<html><center>Kerucut<br>Terpancung</center></html>";
        }
        if ("Tembereng".equals(label)) {
            return "<html><center>Tembereng</center></html>";
        }
        if ("Juring".equals(label)) {
            return "<html><center>Juring</center></html>";
        }
        return label;
    }

    private static class ImagePreview extends JComponent {
        private final BufferedImage image;

        ImagePreview(BufferedImage image) {
            this.image = image;
            setPreferredSize(new Dimension(94, 62));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (image != null) {
                int pad = 4;
                int w = getWidth() - pad * 2;
                int h = getHeight() - pad * 2;
                double scale = Math.min(w / (double) image.getWidth(), h / (double) image.getHeight());
                int drawW = (int) (image.getWidth() * scale);
                int drawH = (int) (image.getHeight() * scale);
                int x = (getWidth() - drawW) / 2;
                int y = (getHeight() - drawH) / 2;
                g.drawImage(image.getScaledInstance(drawW, drawH, Image.SCALE_SMOOTH), x, y, null);
            } else {
                g.setColor(new Color(226, 237, 255));
                g.fillOval(20, 8, getWidth() - 40, getHeight() - 16);
                g.setColor(BLUE);
                g.drawOval(20, 8, getWidth() - 40, getHeight() - 16);
            }
            g.dispose();
        }
    }

    private static class GeometriTableModel extends AbstractTableModel {
        private final String[] kolom = {
                "No", "Nama", "Jenis", "Luas", "Keliling", "Volume", "Progress", "Status"
        };
        private List<BendaGeometri> data = new ArrayList<>();

        public void setData(List<BendaGeometri> data) {
            this.data = data;
            fireTableDataChanged();
        }

        public void refreshData() {
            if (!data.isEmpty()) {
                fireTableRowsUpdated(0, Math.min(data.size() - 1, 1500));
            }
        }

        public void refreshAllData() {
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return kolom.length;
        }

        @Override
        public String getColumnName(int column) {
            return kolom[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            BendaGeometri benda = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return rowIndex + 1;
                case 1:
                    return benda.getNamaBenda();
                case 2:
                    return benda.getClass().getSimpleName();
                case 3:
                    return format(benda.getLuas());
                case 4:
                    return format(benda.getKeliling());
                case 5:
                    return format(benda.getVolume());
                case 6:
                    return benda.getProgress() + "%";
                case 7:
                    return benda.getStatusProses();
                default:
                    return "";
            }
        }
    }

    private static class BarRacePanel extends JPanel {
        private final Color[] warnaBar = {
                new Color(22, 100, 235), new Color(58, 180, 75), new Color(255, 133, 16),
                new Color(118, 64, 216), new Color(25, 190, 200), new Color(238, 58, 83),
                new Color(35, 160, 218), new Color(161, 99, 45)
        };
        private int[] nilai = new int[JUMLAH_KATEGORI];
        private int totalSelesai;
        private long durasiMs;
        private boolean berjalan;

        public BarRacePanel() {
            setOpaque(false);
            setBackground(Color.WHITE);
        }

        public void reset() {
            this.nilai = new int[JUMLAH_KATEGORI];
            this.totalSelesai = 0;
            this.durasiMs = 0L;
            this.berjalan = true;
            repaint();
        }

        public void setData(int[] nilaiBaru, int totalSelesai, long durasiMs, boolean berjalan) {
            this.nilai = Arrays.copyOf(nilaiBaru, nilaiBaru.length);
            this.totalSelesai = totalSelesai;
            if (durasiMs > 0) {
                this.durasiMs = durasiMs;
            }
            this.berjalan = berjalan;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            gambarHeader(g, width);
            gambarGrid(g, width, height);
            gambarBar(g, width, height);
            gambarTimeline(g, width, height);

            g.dispose();
        }

        private void gambarHeader(Graphics2D g, int width) {
            HeaderIcon chart = new HeaderIcon(HeaderIcon.CHART);
            Graphics2D iconGraphics = (Graphics2D) g.create(20, 12, 58, 58);
            chart.setSize(58, 58);
            chart.paint(iconGraphics);
            iconGraphics.dispose();

            g.setColor(TEXT);
            g.setFont(new Font("SansSerif", Font.BOLD, 36));
            g.drawString("Visualisasi Bar Chart", 92, 48);
            g.setColor(new Color(80, 96, 132));
            g.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g.drawString("100.000 data untuk setiap benda geometri", 94, 72);

            int badgeW = 158;
            int badgeX = width - badgeW - 16;
            g.setColor(new Color(245, 249, 255));
            g.fillRoundRect(badgeX, 20, badgeW, 34, 9, 9);
            g.setColor(BORDER);
            g.drawRoundRect(badgeX, 20, badgeW, 34, 9, 9);
            g.setColor(BLUE);
            g.setFont(new Font("SansSerif", Font.BOLD, 13));
            g.drawString("800.000 data total", badgeX + 44, 42);
            drawDatabaseIcon(g, badgeX + 18, 28, BLUE);
        }

        private void gambarGrid(Graphics2D g, int width, int height) {
            int xAwal = 240;
            int xAkhir = Math.max(xAwal + 260, width - 110);
            int yAwal = 114;
            int yTimeline = height - 64;
            int yAkhir = Math.max(yAwal + 210, yTimeline - 34);

            g.setColor(new Color(232, 238, 248));
            float[] dash = { 5f, 8f };
            g.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1f, dash, 0f));
            for (int i = 1; i <= 4; i++) {
                int x = xAwal + ((xAkhir - xAwal) * i / 4);
                g.drawLine(x, yAwal, x, yAkhir);
            }
            g.setStroke(new BasicStroke(1f));
        }

        private void gambarBar(Graphics2D g, int width, int height) {
            Integer[] urutan = new Integer[JUMLAH_KATEGORI];
            for (int i = 0; i < JUMLAH_KATEGORI; i++) {
                urutan[i] = i;
            }
            Arrays.sort(urutan, Comparator
                    .comparingInt((Integer i) -> nilai[i])
                    .reversed()
                    .thenComparingInt(Integer::intValue));

            int xNomor = 44;
            int xLabel = 78;
            int xAwal = 240;
            int xAkhir = Math.max(xAwal + 260, width - 110);
            int lebarMaks = xAkhir - xAwal;
            int yAwal = 98;
            int yTimeline = height - 64;
            int ruangVertikal = Math.max(210, yTimeline - yAwal - 42);
            int gap = 8;
            int tinggiBar = Math.max(18, Math.min(26,
                    (ruangVertikal - (JUMLAH_KATEGORI - 1) * gap) / JUMLAH_KATEGORI));
            tinggiBar = Math.min(tinggiBar, 22);

            for (int posisi = 0; posisi < urutan.length; posisi++) {
                int index = urutan[posisi];
                int y = yAwal + posisi * (tinggiBar + gap);
                int lebar = (int) (lebarMaks * (nilai[index] / (double) DATA_PER_BENDA));

                gambarNomor(g, xNomor, y + tinggiBar / 2, posisi + 1, warnaBar[index]);

                g.setColor(TEXT);
                g.setFont(new Font("SansSerif", Font.BOLD, 14));
                g.drawString(NAMA_KATEGORI[index], xLabel, y + 18);

                gambarIsiBar(g, xAwal, y, lebar, tinggiBar, warnaBar[index]);
                gambarNilaiBar(g, xAwal + lebar, y, nilai[index], warnaBar[index]);
            }
        }

        private void gambarNomor(Graphics2D g, int x, int y, int nomor, Color warna) {
            g.setColor(warna);
            g.fillOval(x - 13, y - 13, 26, 26);
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 14));
            String teks = String.valueOf(nomor);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(teks, x - fm.stringWidth(teks) / 2, y + fm.getAscent() / 2 - 3);
        }

        private void gambarIsiBar(Graphics2D g, int x, int y, int lebar, int tinggi, Color warna) {
            if (lebar <= 0) {
                return;
            }
            GradientPaint paint = new GradientPaint(x, y, warna.brighter(), x + Math.max(lebar, 1), y,
                    warna.darker());
            g.setPaint(paint);
            g.fill(new RoundRectangle2D.Double(x, y, lebar, tinggi, 5, 5));
        }

        private void gambarNilaiBar(Graphics2D g, int xUjung, int y, int value, Color warna) {
            int xTeks = xUjung + 24;
            g.setColor(warna.darker());
            g.setFont(new Font("SansSerif", Font.BOLD, 16));
            g.drawString(formatInteger(value), xTeks, y + 18);
        }

        private void gambarTimeline(Graphics2D g, int width, int height) {
            int y = height - 64;
            int xAwal = 220;
            int xAkhir = Math.max(xAwal + 220, width - 54);
            int progressWidth = xAkhir - xAwal;
            double rasio = TOTAL_DATA_DEMO == 0 ? 0 : totalSelesai / (double) TOTAL_DATA_DEMO;
            int xAktif = xAwal + (int) (progressWidth * rasio);

            g.setColor(NAVY);
            g.fillRoundRect(20, y - 16, 158, 32, 8, 8);
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 12));
            g.drawString("WAKTU BERJALAN", 58, y + 4);
            g.drawOval(36, y - 9, 18, 18);
            g.drawLine(45, y, 45, y - 6);
            g.drawLine(45, y, 51, y + 3);

            g.setColor(new Color(184, 194, 214));
            g.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(xAwal, y, xAkhir, y);
            g.setColor(BLUE);
            g.drawLine(xAwal, y, xAktif, y);
            g.setStroke(new BasicStroke(1f));

            String[] tick = { "0%", "25%", "50%", "75%", "100%" };
            g.setFont(new Font("SansSerif", Font.BOLD, 11));
            for (int i = 0; i < tick.length; i++) {
                int x = xAwal + (progressWidth * i / 4);
                g.setColor(i / 4.0 <= rasio ? BLUE : new Color(168, 178, 198));
                g.fillOval(x - 7, y - 7, 14, 14);
                g.setColor(TEXT);
                drawCentered(g, tick[i], x, y + 28);
            }

            g.setColor(new Color(232, 239, 255));
            g.fillRoundRect(width / 2 - 160, height - 28, 320, 22, 14, 14);
            g.setColor(new Color(45, 84, 160));
            g.setFont(new Font("SansSerif", Font.PLAIN, 12));
            String status = berjalan
                    ? "Data berubah saat worker menyelesaikan kategori"
                    : "Selesai dalam " + durasiMs + " ms";
            drawCentered(g, status, width / 2, height - 12);
        }

        private void drawDatabaseIcon(Graphics2D g, int x, int y, Color color) {
            g.setColor(color);
            g.drawOval(x, y, 18, 8);
            g.drawLine(x, y + 4, x, y + 20);
            g.drawLine(x + 18, y + 4, x + 18, y + 20);
            g.drawArc(x, y + 8, 18, 8, 180, 180);
            g.drawArc(x, y + 16, 18, 8, 180, 180);
        }

        private void drawCentered(Graphics2D g, String text, int centerX, int baselineY) {
            FontMetrics fm = g.getFontMetrics();
            g.drawString(text, centerX - fm.stringWidth(text) / 2, baselineY);
        }
    }

    private static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color background;
        private final Color border;

        RoundedPanel(BorderLayout layout, int radius, Color background, Color border) {
            super(layout);
            this.radius = radius;
            this.background = background;
            this.border = border;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(background);
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius * 2, radius * 2);
            g.setColor(border);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius * 2, radius * 2);
            g.dispose();
            super.paintComponent(graphics);
        }
    }

    private static class RoundedButton extends JButton {
        private final Color background;
        private final boolean filled;

        RoundedButton(String text, Color background, Color foreground, boolean filled) {
            super("<html><center>" + text.replace("\n", "<br>") + "</center></html>");
            this.background = background;
            this.filled = filled;
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setForeground(foreground);
            setFont(new Font("SansSerif", Font.BOLD, 14));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setMargin(new Insets(10, 12, 10, 12));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color paintColor = getModel().isPressed() ? background.darker() : background;
            if (filled) {
                g.setColor(paintColor);
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            } else {
                g.setColor(Color.WHITE);
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                g.setColor(background);
                g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            }
            g.dispose();
            super.paintComponent(graphics);
        }
    }

    private static class HeaderIcon extends JComponent {
        static final int CUBE = 1;
        static final int CHART = 2;

        private final int type;

        HeaderIcon(int type) {
            this.type = type;
            setPreferredSize(new Dimension(48, 48));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(BLUE);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            if (type == CHART) {
                g.drawLine(14, 35, 14, 14);
                g.drawLine(14, 35, 38, 35);
                g.drawLine(18, 29, 25, 22);
                g.drawLine(25, 22, 31, 26);
                g.drawLine(31, 26, 38, 16);
                g.fillOval(36, 14, 5, 5);
            } else {
                Polygon top = new Polygon();
                top.addPoint(24, 10);
                top.addPoint(36, 17);
                top.addPoint(24, 24);
                top.addPoint(12, 17);
                g.drawPolygon(top);
                g.drawLine(12, 17, 12, 31);
                g.drawLine(36, 17, 36, 31);
                g.drawLine(24, 24, 24, 39);
                g.drawLine(12, 31, 24, 39);
                g.drawLine(36, 31, 24, 39);
            }
            g.dispose();
        }
    }

    private static class SmallOutlineIcon extends JComponent {
        static final int CUBE = 1;
        private final int type;

        SmallOutlineIcon(int type) {
            this.type = type;
            setPreferredSize(new Dimension(22, 22));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(BLUE);
            g.setStroke(new BasicStroke(1.8f));
            if (type == CUBE) {
                Polygon top = new Polygon();
                top.addPoint(11, 3);
                top.addPoint(19, 7);
                top.addPoint(11, 12);
                top.addPoint(3, 7);
                g.drawPolygon(top);
                g.drawLine(3, 7, 3, 15);
                g.drawLine(19, 7, 19, 15);
                g.drawLine(11, 12, 11, 20);
                g.drawLine(3, 15, 11, 20);
                g.drawLine(19, 15, 11, 20);
            }
            g.dispose();
        }
    }

    private static class SmallSolidIcon extends JComponent {
        static final int TERMINAL = 1;
        static final int DATA = 2;
        private final int type;

        SmallSolidIcon(int type) {
            this.type = type;
            setPreferredSize(new Dimension(28, 28));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(NAVY);
            g.fillRoundRect(0, 0, 28, 28, 6, 6);
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            if (type == TERMINAL) {
                g.drawLine(8, 9, 13, 14);
                g.drawLine(13, 14, 8, 19);
                g.drawLine(16, 19, 22, 19);
            } else {
                g.drawOval(8, 7, 12, 5);
                g.drawLine(8, 9, 8, 20);
                g.drawLine(20, 9, 20, 20);
                g.drawArc(8, 12, 12, 5, 180, 180);
                g.drawArc(8, 17, 12, 5, 180, 180);
            }
            g.dispose();
        }
    }

    private static class IconOnlyButton extends JButton {
        IconOnlyButton() {
            setPreferredSize(new Dimension(34, 28));
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
            g.setColor(BORDER);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
            g.setColor(BLUE);
            g.setStroke(new BasicStroke(2f));
            g.drawLine(10, 8, 24, 8);
            g.drawLine(13, 14, 21, 14);
            g.drawLine(16, 20, 18, 20);
            g.dispose();
        }
    }

    private static class StatusOkIcon extends JComponent {
        StatusOkIcon() {
            setPreferredSize(new Dimension(30, 30));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(44, 194, 101));
            g.fillOval(2, 2, 26, 26);
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(10, 16, 14, 20);
            g.drawLine(14, 20, 21, 11);
            g.dispose();
        }
    }

    private static class SoftTableRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(new EmptyBorder(0, 8, 0, 8));
            setHorizontalAlignment(column == 1 || column == 2 ? SwingConstants.LEFT : SwingConstants.CENTER);
            if (isSelected) {
                component.setBackground(new Color(222, 236, 255));
            } else {
                component.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 251, 255));
            }
            component.setForeground(TEXT);
            return component;
        }
    }

    private static class ProgressRenderer extends JPanel implements TableCellRenderer {
        private int progress;

        ProgressRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            String text = value == null ? "0%" : value.toString().replace("%", "");
            try {
                progress = Integer.parseInt(text);
            } catch (NumberFormatException ex) {
                progress = 0;
            }
            setBackground(isSelected ? new Color(222, 236, 255)
                    : row % 2 == 0 ? Color.WHITE : new Color(248, 251, 255));
            return this;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int barX = 14;
            int barY = getHeight() / 2 - 3;
            int barW = Math.max(20, getWidth() - 58);
            g.setColor(new Color(224, 235, 251));
            g.fillRoundRect(barX, barY, barW, 6, 6, 6);
            g.setColor(BLUE);
            g.fillRoundRect(barX, barY, (int) (barW * progress / 100.0), 6, 6, 6);
            g.setColor(BLUE);
            g.setFont(new Font("SansSerif", Font.BOLD, 11));
            g.drawString(progress + "%", barX + barW + 10, barY + 8);
            g.dispose();
        }
    }

    private static class StatusRenderer extends JPanel implements TableCellRenderer {
        private String status = "";

        StatusRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            status = value == null ? "" : value.toString();
            setBackground(isSelected ? new Color(222, 236, 255)
                    : row % 2 == 0 ? Color.WHITE : new Color(248, 251, 255));
            return this;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color color = "Selesai".equals(status) ? new Color(35, 184, 94) : BLUE;
            g.setColor(color);
            g.fillOval(12, getHeight() / 2 - 6, 12, 12);
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(15, getHeight() / 2, 18, getHeight() / 2 + 3);
            g.drawLine(18, getHeight() / 2 + 3, 22, getHeight() / 2 - 4);
            g.setColor(TEXT);
            g.setFont(new Font("SansSerif", Font.BOLD, 11));
            g.drawString(status, 32, getHeight() / 2 + 4);
            g.dispose();
        }
    }
}

package pElips.gui;

import pElips.model.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * GUI utama untuk Simulasi Hitung Benda Geometri Elips.
 * Menggunakan Java Swing dengan desain modern dark theme.
 * 
 * @author LENOVO
 */
public class GeometriGUI extends JFrame {

    // Warna tema
    private static final Color BG_DARK = new Color(18, 18, 30);
    private static final Color BG_CARD = new Color(30, 30, 50);
    private static final Color BG_INPUT = new Color(40, 40, 65);
    private static final Color ACCENT_BLUE = new Color(80, 140, 255);
    private static final Color ACCENT_PURPLE = new Color(140, 80, 255);
    private static final Color ACCENT_CYAN = new Color(0, 210, 230);
    private static final Color TEXT_PRIMARY = new Color(230, 230, 245);
    private static final Color TEXT_SECONDARY = new Color(150, 150, 180);
    private static final Color SUCCESS_GREEN = new Color(50, 205, 100);
    private static final Color BORDER_COLOR = new Color(60, 60, 90);

    // Komponen
    private JComboBox<String> comboBenda;
    private JPanel panelInput;
    private JPanel panelHasil;
    private JTextField[] inputFields;
    private JLabel[] inputLabels;
    private JLabel lblLuas, lblKeliling, lblVolume;
    private JLabel lblNamaBenda;
    private JButton btnHitung, btnReset;

    // Daftar benda
    private final String[] daftarBenda = {
        "Pilih Benda Geometri...",
        "Elips",
        "Bola",
        "Tabung",
        "Kerucut Dengan Alas Elips",
        "Kerucut Terpancung Dengan Alas Elips",
        "Cincin (Torus)",
        "Juring Bola",
        "Tembereng Bola"
    };

    public GeometriGUI() {
        setTitle("Simulasi Hitung Benda Geometri Elips - PBO 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 680);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, BG_DARK, getWidth(), getHeight(), new Color(25, 20, 45));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(mainPanel);

        // Header
        mainPanel.add(createHeader(), BorderLayout.NORTH);

        // Content
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(15, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 8, 0, 8);

        // Left: Input
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5; gbc.weighty = 1.0;
        contentPanel.add(createInputPanel(), gbc);

        // Right: Hasil
        gbc.gridx = 1;
        contentPanel.add(createHasilPanel(), gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, ACCENT_BLUE, getWidth(), 0, ACCENT_PURPLE);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(0, 70));
        header.setBorder(new EmptyBorder(12, 25, 12, 25));

        JLabel title = new JLabel("◆  Simulasi Hitung Benda Geometri Elips");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);

        JLabel subtitle = new JLabel("Projek Akhir PBO 2  ");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(220, 220, 255));
        subtitle.setHorizontalAlignment(SwingConstants.RIGHT);
        header.add(subtitle, BorderLayout.EAST);

        return header;
    }

    private JPanel createInputPanel() {
        JPanel card = createCard("⬡  Input Parameter");

        JPanel inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setOpaque(false);

        // Combo benda
        JPanel comboPanel = new JPanel(new BorderLayout(0, 6));
        comboPanel.setOpaque(false);
        comboPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel lbl = createLabel("Jenis Benda Geometri:");
        comboPanel.add(lbl, BorderLayout.NORTH);

        comboBenda = new JComboBox<>(daftarBenda);
        comboBenda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBenda.setBackground(BG_INPUT);
        comboBenda.setForeground(TEXT_PRIMARY);
        comboBenda.setPreferredSize(new Dimension(0, 38));
        comboBenda.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        comboBenda.addActionListener(e -> updateInputFields());
        comboPanel.add(comboBenda, BorderLayout.CENTER);
        inner.add(comboPanel);
        inner.add(Box.createVerticalStrut(12));

        // Input fields panel
        panelInput = new JPanel();
        panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.Y_AXIS));
        panelInput.setOpaque(false);

        inputLabels = new JLabel[6];
        inputFields = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            JPanel row = new JPanel(new BorderLayout(0, 4));
            row.setOpaque(false);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));

            inputLabels[i] = createLabel("");
            inputLabels[i].setVisible(false);
            row.add(inputLabels[i], BorderLayout.NORTH);

            inputFields[i] = new JTextField();
            inputFields[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
            inputFields[i].setBackground(BG_INPUT);
            inputFields[i].setForeground(TEXT_PRIMARY);
            inputFields[i].setCaretColor(ACCENT_CYAN);
            inputFields[i].setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                new EmptyBorder(8, 10, 8, 10)
            ));
            inputFields[i].setPreferredSize(new Dimension(0, 36));
            inputFields[i].setVisible(false);
            row.add(inputFields[i], BorderLayout.CENTER);

            panelInput.add(row);
            panelInput.add(Box.createVerticalStrut(6));
        }
        inner.add(panelInput);
        inner.add(Box.createVerticalGlue());

        // Buttons
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        btnPanel.setOpaque(false);
        btnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        btnHitung = createButton("Hitung", ACCENT_BLUE);
        btnHitung.addActionListener(e -> hitungHasil());
        btnPanel.add(btnHitung);

        btnReset = createButton("Reset", new Color(100, 100, 130));
        btnReset.addActionListener(e -> resetForm());
        btnPanel.add(btnReset);

        inner.add(btnPanel);
        card.add(inner, BorderLayout.CENTER);
        return card;
    }

    private JPanel createHasilPanel() {
        JPanel card = createCard("◈  Hasil Perhitungan");

        JPanel inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setOpaque(false);

        // Nama benda
        lblNamaBenda = new JLabel("Belum ada benda dipilih");
        lblNamaBenda.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNamaBenda.setForeground(ACCENT_CYAN);
        lblNamaBenda.setAlignmentX(Component.LEFT_ALIGNMENT);
        inner.add(lblNamaBenda);
        inner.add(Box.createVerticalStrut(6));

        // Separator
        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER_COLOR);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        inner.add(sep);
        inner.add(Box.createVerticalStrut(18));

        // Hasil panel
        panelHasil = new JPanel();
        panelHasil.setLayout(new BoxLayout(panelHasil, BoxLayout.Y_AXIS));
        panelHasil.setOpaque(false);

        lblLuas = createHasilLabel("Luas Permukaan", "—");
        lblKeliling = createHasilLabel("Keliling", "—");
        lblVolume = createHasilLabel("Volume", "—");

        inner.add(panelHasil);
        inner.add(Box.createVerticalGlue());

        // Info pilar
        JPanel infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(40, 40, 70));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            }
        };
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(12, 14, 12, 14));
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

        JLabel infoTitle = new JLabel("5 Pilar PBO yang Diterapkan:");
        infoTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        infoTitle.setForeground(ACCENT_PURPLE);
        infoPanel.add(infoTitle);
        infoPanel.add(Box.createVerticalStrut(4));

        String[] pilars = {"① Encapsulation", "② Inheritance", "③ Overloading", "④ Overriding & Polymorphism", "⑤ Multithreading"};
        for (String p : pilars) {
            JLabel l = new JLabel(p);
            l.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            l.setForeground(TEXT_SECONDARY);
            infoPanel.add(l);
        }
        inner.add(infoPanel);

        card.add(inner, BorderLayout.CENTER);
        return card;
    }

    private JLabel createHasilLabel(String title, String value) {
        JPanel row = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(35, 35, 60));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            }
        };
        row.setOpaque(false);
        row.setBorder(new EmptyBorder(12, 16, 12, 16));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTitle.setForeground(TEXT_SECONDARY);
        row.add(lblTitle, BorderLayout.NORTH);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblValue.setForeground(SUCCESS_GREEN);
        row.add(lblValue, BorderLayout.CENTER);

        panelHasil.add(row);
        panelHasil.add(Box.createVerticalStrut(10));
        return lblValue;
    }

    private JPanel createCard(String title) {
        JPanel card = new JPanel(new BorderLayout(0, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(BORDER_COLOR);
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
            }
        };
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(18, 20, 18, 20));

        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbl.setForeground(ACCENT_BLUE);
        card.add(lbl, BorderLayout.NORTH);

        return card;
    }

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        l.setForeground(TEXT_SECONDARY);
        return l;
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(color.brighter());
                } else {
                    g2.setColor(color);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 42));
        return btn;
    }

    private void updateInputFields() {
        int idx = comboBenda.getSelectedIndex();
        // Hide all
        for (int i = 0; i < 6; i++) {
            inputLabels[i].setVisible(false);
            inputFields[i].setVisible(false);
            inputFields[i].setText("");
        }

        switch (idx) {
            case 1: // Elips
                showField(0, "Sumbu Semi-Mayor (a):");
                showField(1, "Sumbu Semi-Minor (b):");
                break;
            case 2: // Bola
                showField(0, "Jari-jari (r):");
                break;
            case 3: // Tabung
                showField(0, "Jari-jari (r):");
                showField(1, "Tinggi (t):");
                break;
            case 4: // Kerucut Alas Elips
                showField(0, "Sumbu Semi-Mayor (a):");
                showField(1, "Sumbu Semi-Minor (b):");
                showField(2, "Tinggi (t):");
                break;
            case 5: // Kerucut Terpancung
                showField(0, "Sumbu A Bawah (a1):");
                showField(1, "Sumbu B Bawah (b1):");
                showField(2, "Sumbu A Atas (a2):");
                showField(3, "Sumbu B Atas (b2):");
                showField(4, "Tinggi (t):");
                break;
            case 6: // Cincin
                showField(0, "Jari-jari Mayor / R (pusat torus ke pusat tabung):");
                showField(1, "Jari-jari Minor / r (radius tabung):");
                break;
            case 7: // Juring
                showField(0, "Jari-jari Bola (r):");
                showField(1, "Tinggi Topi (h):");
                break;
            case 8: // Tembereng
                showField(0, "Jari-jari Bola (r):");
                showField(1, "Tinggi Topi (h):");
                break;
        }
        panelInput.revalidate();
        panelInput.repaint();
    }

    private void showField(int index, String label) {
        inputLabels[index].setText(label);
        inputLabels[index].setVisible(true);
        inputFields[index].setVisible(true);
    }

    private void hitungHasil() {
        int idx = comboBenda.getSelectedIndex();
        if (idx == 0) {
            JOptionPane.showMessageDialog(this, "Pilih benda geometri terlebih dahulu!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double luas = 0, keliling = 0, volume = 0;
            String nama = "";

            switch (idx) {
                case 1: { // Elips
                    double a = Double.parseDouble(inputFields[0].getText());
                    double b = Double.parseDouble(inputFields[1].getText());
                    Elips e = new Elips("Elips", a, b);
                    luas = e.hitungLuas(); keliling = e.hitungKeliling(); volume = e.hitungVolume();
                    nama = "Elips";
                    break;
                }
                case 2: { // Bola
                    double r = Double.parseDouble(inputFields[0].getText());
                    Bola b = new Bola("Bola", r);
                    luas = b.hitungLuas(); keliling = b.hitungKeliling(); volume = b.hitungVolume();
                    nama = "Bola";
                    break;
                }
                case 3: { // Tabung
                    double r = Double.parseDouble(inputFields[0].getText());
                    double t = Double.parseDouble(inputFields[1].getText());
                    Tabung tb = new Tabung();
                    tb.setJariJari(r); tb.setTinggi(t);
                    luas = tb.hitungLuas(); keliling = tb.hitungKeliling(); volume = tb.hitungVolume();
                    nama = "Tabung";
                    break;
                }
                case 4: { // Kerucut Alas Elips
                    double a = Double.parseDouble(inputFields[0].getText());
                    double b = Double.parseDouble(inputFields[1].getText());
                    double t = Double.parseDouble(inputFields[2].getText());
                    KerucutDenganAlasElips k = new KerucutDenganAlasElips("Kerucut Alas Elips", a, b, t);
                    luas = k.hitungLuas(); keliling = k.hitungKeliling(); volume = k.hitungVolume();
                    nama = "Kerucut Dengan Alas Elips";
                    break;
                }
                case 5: { // Kerucut Terpancung
                    double a1 = Double.parseDouble(inputFields[0].getText());
                    double b1 = Double.parseDouble(inputFields[1].getText());
                    double a2 = Double.parseDouble(inputFields[2].getText());
                    double b2 = Double.parseDouble(inputFields[3].getText());
                    double t = Double.parseDouble(inputFields[4].getText());
                    KerucutTerpancungDenganAlasElips kt = new KerucutTerpancungDenganAlasElips(
                            "Kerucut Terpancung", a1, b1, a2, b2, t);
                    luas = kt.hitungLuas(); keliling = kt.hitungKeliling(); volume = kt.hitungVolume();
                    nama = "Kerucut Terpancung Alas Elips";
                    break;
                }
                case 6: { // Cincin
                    double R = Double.parseDouble(inputFields[0].getText());
                    double r = Double.parseDouble(inputFields[1].getText());
                    Cincin c = new Cincin("Cincin (Torus)", R, r);
                    luas = c.hitungLuas(); keliling = c.hitungKeliling(); volume = c.hitungVolume();
                    nama = "Cincin Elips (Torus)";
                    break;
                }
                case 7: { // Juring
                    double r = Double.parseDouble(inputFields[0].getText());
                    double h = Double.parseDouble(inputFields[1].getText());
                    Juring j = new Juring("Juring Bola", r, h);
                    luas = j.hitungLuas(); keliling = j.hitungKeliling(); volume = j.hitungVolume();
                    nama = "Juring Bola";
                    break;
                }
                case 8: { // Tembereng
                    double r = Double.parseDouble(inputFields[0].getText());
                    double h = Double.parseDouble(inputFields[1].getText());
                    Tembereng t = new Tembereng("Tembereng Bola", r, h);
                    luas = t.hitungLuas(); keliling = t.hitungKeliling(); volume = t.hitungVolume();
                    nama = "Tembereng Bola";
                    break;
                }
            }

            lblNamaBenda.setText("◆  " + nama);
            lblLuas.setText(String.format("%.4f", luas));
            lblKeliling.setText(String.format("%.4f", keliling));
            lblVolume.setText(String.format("%.4f", volume));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Masukkan angka yang valid pada semua field input!",
                    "Error Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Terjadi error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        comboBenda.setSelectedIndex(0);
        for (int i = 0; i < 6; i++) {
            inputFields[i].setText("");
            inputFields[i].setVisible(false);
            inputLabels[i].setVisible(false);
        }
        lblNamaBenda.setText("Belum ada benda dipilih");
        lblLuas.setText("—");
        lblKeliling.setText("—");
        lblVolume.setText("—");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            GeometriGUI gui = new GeometriGUI();
            gui.setVisible(true);
        });
    }
}

package pElips.gui;

import pElips.model.*;
import pElips.thread.KalkulasiThread;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainFrame extends JFrame {
    private JComboBox<String> cmbJenisBenda;
    private JTextField txtInput1, txtInput2, txtInput3, txtInput4, txtInput5;
    private JLabel lblInput1, lblInput2, lblInput3, lblInput4, lblInput5;
    private JTextArea txtOutput;
    private JButton btnHitung, btnDemoThread;

    public MainFrame() {
        setTitle("Aplikasi Geometri Elips & Bola - OOP & Multithreading");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        handleEvent();
        sesuaikanInput(); 
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // --- PANEL KIRI: FORM INPUT ---
        JPanel pnlInput = new JPanel(new GridLayout(7, 2, 5, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder(" Form Input Geometri "));
        
        pnlInput.add(new JLabel(" Jenis Benda:"));
        cmbJenisBenda = new JComboBox<>(new String[]{
            "Elips (2D)", "Bola", "Tabung", "Kerucut Alas Elips", 
            "Kerucut Terpancung", "Cincin", "Juring Bola", "Tembereng Bola"
        });
        pnlInput.add(cmbJenisBenda);

        lblInput1 = new JLabel(" Input 1:"); txtInput1 = new JTextField();
        lblInput2 = new JLabel(" Input 2:"); txtInput2 = new JTextField();
        lblInput3 = new JLabel(" Input 3:"); txtInput3 = new JTextField();
        lblInput4 = new JLabel(" Input 4:"); txtInput4 = new JTextField();
        lblInput5 = new JLabel(" Input 5:"); txtInput5 = new JTextField();

        pnlInput.add(lblInput1); pnlInput.add(txtInput1);
        pnlInput.add(lblInput2); pnlInput.add(txtInput2);
        pnlInput.add(lblInput3); pnlInput.add(txtInput3);
        pnlInput.add(lblInput4); pnlInput.add(txtInput4);
        pnlInput.add(lblInput5); pnlInput.add(txtInput5);

        // --- PANEL BAWAH: TOMBOL AKSI ---
        JPanel pnlTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnHitung = new JButton("Hitung & Tampilkan");
        btnDemoThread = new JButton("Run Demo Multithreading (100k Data)");
        btnDemoThread.setBackground(new Color(0, 128, 0));
        btnDemoThread.setForeground(Color.WHITE);
        
        pnlTombol.add(btnHitung);
        pnlTombol.add(btnDemoThread);

        // --- PANEL KANAN/TENGAH: HASIL OUTPUT ---
        JPanel pnlOutput = new JPanel(new BorderLayout());
        pnlOutput.setBorder(BorderFactory.createTitledBorder(" Hasil Perhitungan / Log Sistem "));
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        pnlOutput.add(scrollPane, BorderLayout.CENTER);

        // Gabungkan Komponen ke Frame Utama
        add(pnlInput, BorderLayout.WEST);
        add(pnlOutput, BorderLayout.CENTER);
        add(pnlTombol, BorderLayout.SOUTH);
    }

    private void sesuaikanInput() {
        int dipilih = cmbJenisBenda.getSelectedIndex();
        // Reset semua status textfield menjadi tidak aktif dulu
        txtInput1.setVisible(true); lblInput1.setVisible(true);
        txtInput2.setVisible(false); lblInput2.setVisible(false);
        txtInput3.setVisible(false); lblInput3.setVisible(false);
        txtInput4.setVisible(false); lblInput4.setVisible(false);
        txtInput5.setVisible(false); lblInput5.setVisible(false);

        switch (dipilih) {
            case 0: // Elips
                lblInput1.setText(" Sumbu A:"); lblInput2.setText(" Sumbu B:");
                txtInput2.setVisible(true); lblInput2.setVisible(true);
                break;
            case 1: // Bola
                lblInput1.setText(" Jari-jari (r):");
                break;
            case 2: // Tabung
                lblInput1.setText(" Sumbu A:"); lblInput2.setText(" Sumbu B:"); lblInput3.setText(" Tinggi:");
                txtInput2.setVisible(true); lblInput2.setVisible(true);
                txtInput3.setVisible(true); lblInput3.setVisible(true);
                break;
            case 3: // Kerucut Alas Elips
                lblInput1.setText(" Sumbu A:"); lblInput2.setText(" Sumbu B:"); lblInput3.setText(" Tinggi:");
                txtInput2.setVisible(true); lblInput2.setVisible(true);
                txtInput3.setVisible(true); lblInput3.setVisible(true);
                break;
            case 4: // Kerucut Terpancung
                lblInput1.setText(" Sumbu a1:"); lblInput2.setText(" Sumbu b1:"); 
                lblInput3.setText(" Sumbu a2:"); lblInput4.setText(" Sumbu b2:"); lblInput5.setText(" Tinggi:");
                txtInput2.setVisible(true); lblInput2.setVisible(true);
                txtInput3.setVisible(true); lblInput3.setVisible(true);
                txtInput4.setVisible(true); lblInput4.setVisible(true);
                txtInput5.setVisible(true); lblInput5.setVisible(true);
                break;
            case 5: // Cincin
                lblInput1.setText(" Jari-Jari Mayor (R):"); lblInput2.setText(" Jari-Jari Minor (r):");
                txtInput2.setVisible(true); lblInput2.setVisible(true);
                break;
            case 6: // Juring Bola
            case 7: // Tembereng Bola
                lblInput1.setText(" Jari-jari Bola (r):"); lblInput2.setText(" Tinggi Topi (h):");
                txtInput2.setVisible(true); lblInput2.setVisible(true);
                break;
        }
        revalidate();
        repaint();
    }

    private void handleEvent() {
        cmbJenisBenda.addActionListener(e -> sesuaikanInput());

        btnHitung.addActionListener(e -> {
            try {
                BendaGeometri benda = dapatkanObjekBenda();
                if (benda != null) {
                    txtOutput.setText("=== BERHASIL MENGHITUNG OBJEK ===\n");
                    txtOutput.append("Nama Objek : " + benda.getNamaBenda() + "\n");
                    txtOutput.append("Luas       : " + String.format("%.4f", benda.hitungLuas()) + "\n");
                    txtOutput.append("Keliling   : " + String.format("%.4f", benda.hitungKeliling()) + "\n");
                    txtOutput.append("Volume     : " + String.format("%.4f", benda.hitungVolume()) + "\n");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Mohon masukkan angka nilai input yang valid!", "Error Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDemoThread.addActionListener(e -> jalankanDemoMultithreading());
    }

    private BendaGeometri dapatkanObjekBenda() throws NumberFormatException {
        int jenis = cmbJenisBenda.getSelectedIndex();
        double v1 = txtInput1.getText().isEmpty() ? 0 : Double.parseDouble(txtInput1.getText().trim());
        double v2 = txtInput2.getText().isEmpty() ? 0 : Double.parseDouble(txtInput2.getText().trim());
        double v3 = txtInput3.getText().isEmpty() ? 0 : Double.parseDouble(txtInput3.getText().trim());
        double v4 = txtInput4.getText().isEmpty() ? 0 : Double.parseDouble(txtInput4.getText().trim());
        double v5 = txtInput5.getText().isEmpty() ? 0 : Double.parseDouble(txtInput5.getText().trim());

        switch (jenis) {
            case 0: return new Elips("GUI Elips", v1, v2);
            case 1: return new Bola("GUI Bola", v1);
            case 2: return new Tabung("GUI Tabung", v1, v2, v3);
            case 3: return new KerucutDenganAlasElips("GUI Kerucut Alas Elips", v1, v2, v3);
            case 4: return new KerucutTerpancungDenganAlasElips("GUI Kerucut Terpancung", v1, v2, v3, v4, v5);
            case 5: return new Cincin("GUI Cincin", v1, v2);
            case 6: return new Juring("GUI Juring Bola", v1, v2);
            case 7: return new Tembereng("GUI Tembereng Bola", v1, v2);
            default: return null;
        }
    }

    private void jalankanDemoMultithreading() {
        btnDemoThread.setEnabled(false);
        txtOutput.setText("=== MEMULAI DEMO PARALEL KOMPUTASI (100.000 DATA) ===\n");
        txtOutput.append("Sedang membuat objek acak di memori RAM, mohon tunggu...\n");

        // Optimasi Penting: Dijalankan di background thread baru agar GUI tidak "Not Responding"
        new Thread(() -> {
            int jumlahData = 100000;
            List<BendaGeometri> dataMassal = new ArrayList<>();
            Random rand = new Random();

            for (int i = 0; i < jumlahData; i++) {
                int tipe = rand.nextInt(4);
                double val1 = 10 + rand.nextDouble() * 50;
                double val2 = 5 + rand.nextDouble() * 30;
                
                if (tipe == 0) dataMassal.add(new Bola("Bulk-Bola-" + i, val1));
                else if (tipe == 1) dataMassal.add(new Tabung("Bulk-Tabung-" + i, val1, val2, val1));
                else if (tipe == 2) dataMassal.add(new Juring("Bulk-Juring-" + i, val1, val2));
                else dataMassal.add(new KerucutDenganAlasElips("Bulk-Kerucut-" + i, val1, val2, val1));
            }

            // 1. Sekuensial
            long startSequential = System.currentTimeMillis();
            for (BendaGeometri bg : dataMassal) {
                for (int i = 0; i < 50; i++) { 
                    bg.hitungVolume();
                    bg.hitungLuas();
                }
            }
            long endSequential = System.currentTimeMillis();
            long waktuSequential = endSequential - startSequential;

            // 2. Multithread
            int jumlahThread = 4;
            int chunkSize = jumlahData / jumlahThread;
            List<KalkulasiThread> threads = new ArrayList<>();
            
            long startParallel = System.currentTimeMillis();
            for (int i = 0; i < jumlahThread; i++) {
                int fromIndex = i * chunkSize;
                int toIndex = (i == jumlahThread - 1) ? jumlahData : (i + 1) * chunkSize;
                List<BendaGeometri> subList = dataMassal.subList(fromIndex, toIndex);
                
                KalkulasiThread thread = new KalkulasiThread(i + 1, subList);
                threads.add(thread);
                thread.start();
            }

            for (KalkulasiThread t : threads) {
                try { t.join(); } catch (InterruptedException e) { }
            }
            long endParallel = System.currentTimeMillis();
            long waktuParallel = endParallel - startParallel;

            // Kembalikan output ke UI Utama secara aman
            SwingUtilities.invokeLater(() -> {
                txtOutput.append("\n[✓] Semua proses kalkulasi selesai masal!\n");
                txtOutput.append("--------------------------------------------------\n");
                txtOutput.append("Waktu eksekusi SINGLE THREAD : " + waktuSequential + " ms\n");
                txtOutput.append("Waktu eksekusi MULTITHREADING: " + waktuParallel + " ms\n");
                txtOutput.append("--------------------------------------------------\n");
                if (waktuParallel > 0) {
                    double rasio = (double) waktuSequential / waktuParallel;
                    txtOutput.append("KESIMPULAN: Multithreading " + String.format("%.2f", rasio) + "x lipat LEBIH CEPAT!\n");
                }
                btnDemoThread.setEnabled(true);
            });
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
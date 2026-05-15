/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.proses;

import pElips.model.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class SimulasiHitung - Main class untuk menjalankan semua simulasi
 * perhitungan
 * benda geometri berbasis Elips secara dinamis dengan input dari user.
 * 
 * Mendemonstrasikan 5 Pilar PBO:
 * 1. Encapsulation - penggunaan getter/setter
 * 2. Inheritance - hierarki class geometri
 * 3. Overloading - constructor dengan parameter berbeda
 * 4. Overriding & Polymorphism - method yang di-override di setiap subclass
 * 5. Multithreading - penggunaan KalkulasiThread
 *
 * @author LENOVO
 */
public class SimulasiHitung {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<BendaGeometri> daftarBenda = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            tampilkanMenu();
            System.out.print("Pilih menu [1-5]: ");
            int pilihan = bacaInt();

            switch (pilihan) {
                case 1:
                    buatBendaGeometri();
                    break;
                case 2:
                    lihatSemuaBenda();
                    break;
                case 3:
                    demoPolymorphism();
                    break;
                case 4:
                    demoMultithreading();
                    break;
                case 5:
                    running = false;
                    System.out.println("\n╔══════════════════════════════════════════════════╗");
                    System.out.println("║   Terima kasih telah menggunakan program ini!   ║");
                    System.out.println("╚══════════════════════════════════════════════════╝");
                    break;
                default:
                    System.out.println("[!] Pilihan tidak valid. Silakan coba lagi.\n");
            }
        }
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║     SIMULASI HITUNG BENDA GEOMETRI ELIPS       ║");
        System.out.println("║         Projek Akhir - PBO 2                   ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  [1] Buat Benda Geometri Baru                  ║");
        System.out.println("║  [2] Lihat Semua Benda & Hasil Perhitungan     ║");
        System.out.println("║  [3] Demonstrasi Polymorphism                  ║");
        System.out.println("║  [4] Demonstrasi Multithreading                ║");
        System.out.println("║  [5] Keluar                                    ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static void buatBendaGeometri() {
        System.out.println("\n--- Pilih Jenis Benda Geometri ---");
        System.out.println("[1] Elips (2D)");
        System.out.println("[2] Bola");
        System.out.println("[3] Tabung");
        System.out.println("[4] Kerucut Dengan Alas Elips");
        System.out.println("[5] Kerucut Terpancung Dengan Alas Elips");
        System.out.println("[6] Cincin (Torus)");
        System.out.println("[7] Juring Bola");
        System.out.println("[8] Tembereng Bola");
        System.out.print("Pilih [1-8]: ");
        int jenis = bacaInt();

        BendaGeometri benda = null;

        switch (jenis) {
            case 1:
                benda = inputElips();
                break;
            case 2:
                benda = inputBola();
                break;
            case 3:
                benda = inputTabung();
                break;
            case 4:
                benda = inputKerucut();
                break;
            case 5:
                benda = inputKerucutTerpancung();
                break;
            case 6:
                benda = inputCincin();
                break;
            case 7:
                benda = inputJuring();
                break;
            case 8:
                benda = inputTembereng();
                break;
            default:
                System.out.println("[!] Pilihan tidak valid.");
                return;
        }

        if (benda != null) {
            daftarBenda.add(benda);
            System.out.println("\n[OK] Benda berhasil dibuat! Hasil perhitungan:");
            System.out.println();
            benda.cetakInfo();
        }
    }

    // ========== INPUT METHODS ==========

    private static Elips inputElips() {
        System.out.println("\n--- Input Data Elips ---");
        System.out.print("Masukkan Sumbu Semi-Mayor (a): ");
        double a = bacaDouble();
        System.out.print("Masukkan Sumbu Semi-Minor (b): ");
        double b = bacaDouble();
        return new Elips("Elips", a, b);
    }

    private static Bola inputBola() {
        System.out.println("\n--- Input Data Bola ---");
        System.out.print("Masukkan Jari-jari (r): ");
        double r = bacaDouble();
        return new Bola("Bola", r);
    }

    private static Tabung inputTabung() {
        System.out.println("\n--- Input Data Tabung ---");
        System.out.print("Masukkan Jari-jari (r): ");
        double r = bacaDouble();
        System.out.print("Masukkan Tinggi (t): ");
        double t = bacaDouble();
        Tabung tb = new Tabung();
        tb.setJariJari(r);
        tb.setTinggi(t);
        return tb;
    }

    private static KerucutDenganAlasElips inputKerucut() {
        System.out.println("\n--- Input Data Kerucut Dengan Alas Elips ---");
        System.out.print("Masukkan Sumbu Semi-Mayor (a): ");
        double a = bacaDouble();
        System.out.print("Masukkan Sumbu Semi-Minor (b): ");
        double b = bacaDouble();
        System.out.print("Masukkan Tinggi (t): ");
        double t = bacaDouble();
        return new KerucutDenganAlasElips("Kerucut Alas Elips", a, b, t);
    }

    private static KerucutTerpancungDenganAlasElips inputKerucutTerpancung() {
        System.out.println("\n--- Input Data Kerucut Terpancung Dengan Alas Elips ---");
        System.out.print("Masukkan Sumbu A Bawah (a1): ");
        double a1 = bacaDouble();
        System.out.print("Masukkan Sumbu B Bawah (b1): ");
        double b1 = bacaDouble();
        System.out.print("Masukkan Sumbu A Atas  (a2): ");
        double a2 = bacaDouble();
        System.out.print("Masukkan Sumbu B Atas  (b2): ");
        double b2 = bacaDouble();
        System.out.print("Masukkan Tinggi (t): ");
        double t = bacaDouble();
        return new KerucutTerpancungDenganAlasElips("Kerucut Terpancung Alas Elips", a1, b1, a2, b2, t);
    }

    private static Cincin inputCincin() {
        System.out.println("\n--- Input Data Cincin (Torus) ---");
        System.out.print("Masukkan Jari-jari Mayor / R (pusat torus ke pusat tabung): ");
        double R = bacaDouble();
        System.out.print("Masukkan Jari-jari Minor / r (radius tabung): ");
        double r = bacaDouble();
        return new Cincin("Cincin Elips (Torus)", R, r);
    }

    private static Juring inputJuring() {
        System.out.println("\n--- Input Data Juring Bola ---");
        System.out.print("Masukkan Jari-jari Bola (r): ");
        double r = bacaDouble();
        System.out.print("Masukkan Tinggi Topi (h): ");
        double h = bacaDouble();
        return new Juring("Juring Bola", r, h);
    }

    private static Tembereng inputTembereng() {
        System.out.println("\n--- Input Data Tembereng Bola ---");
        System.out.print("Masukkan Jari-jari Bola (r): ");
        double r = bacaDouble();
        System.out.print("Masukkan Tinggi Topi (h): ");
        double h = bacaDouble();
        return new Tembereng("Tembereng Bola", r, h);
    }

    // ========== FITUR DEMO ==========

    private static void lihatSemuaBenda() {
        if (daftarBenda.isEmpty()) {
            System.out.println("\n[!] Belum ada benda geometri. Silakan buat benda terlebih dahulu (menu 1).");
            return;
        }
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║        DAFTAR SEMUA BENDA GEOMETRI              ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        int no = 1;
        for (BendaGeometri benda : daftarBenda) {
            System.out.println("\n[Benda #" + no + "]");
            benda.cetakInfo();
            no++;
        }
    }

    private static void demoPolymorphism() {
        if (daftarBenda.isEmpty()) {
            System.out.println("\n[!] Belum ada benda geometri. Silakan buat benda terlebih dahulu (menu 1).");
            return;
        }
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║          DEMONSTRASI POLYMORPHISM               ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
        System.out.println("Menggunakan array BendaGeometri[] untuk memanggil");
        System.out.println("method yang sama pada objek berbeda (Polymorphism):\n");

        for (BendaGeometri benda : daftarBenda) {
            if (benda instanceof KalkulasiGeometri) {
                KalkulasiGeometri kg = (KalkulasiGeometri) benda;
                System.out.println("[" + benda.getNamaBenda() + "]");
                System.out.println("  Luas     = " + String.format("%.2f", kg.hitungLuas()));
                System.out.println("  Keliling = " + String.format("%.2f", kg.hitungKeliling()));
                System.out.println("  Volume   = " + String.format("%.2f", kg.hitungVolume()));
                System.out.println();
            }
        }
    }

    private static void demoMultithreading() {
        if (daftarBenda.isEmpty()) {
            System.out.println("\n[!] Belum ada benda geometri. Silakan buat benda terlebih dahulu (menu 1).");
            return;
        }
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║          DEMONSTRASI MULTITHREADING             ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
        System.out.println("Menjalankan kalkulasi setiap benda di thread terpisah...\n");

        ArrayList<KalkulasiThread> threads = new ArrayList<>();
        for (BendaGeometri benda : daftarBenda) {
            KalkulasiThread t = new KalkulasiThread(benda);
            threads.add(t);
            t.start();
        }

        // Menunggu semua thread selesai
        for (KalkulasiThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println("Thread utama terganggu: " + e.getMessage());
            }
        }

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║     SEMUA THREAD SELESAI DIJALANKAN!            ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    // ========== UTILITY METHODS ==========

    private static double bacaDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Input tidak valid. Masukkan angka: ");
            }
        }
    }

    private static int bacaInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Input tidak valid. Masukkan angka: ");
            }
        }
    }
}

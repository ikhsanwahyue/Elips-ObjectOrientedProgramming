package pElips.proses;

import pElips.model.*;
import pElips.thread.KalkulasiThread;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

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
                case 1: buatBendaGeometri(); break;
                case 2: lihatSemuaBenda(); break;
                case 3: demoPolymorphism(); break;
                case 4: demoMultithreading(); break;
                case 5:
                    running = false;
                    System.out.println("\n|| Terima kasih telah menggunakan program ini! ||");
                    break;
                default:
                    System.out.println("[!] Pilihan tidak valid.\n");
            }
        }
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n=======================================================");
        System.out.println(" || SIMULASI HITUNG BENDA GEOMETRI ELIPS & BOLA || ");
        System.out.println(" || Projek Akhir - Tugas Kelompok PBO               || ");
        System.out.println("=======================================================");
        System.out.println(" [1] Buat Benda Geometri Baru (Manual)");
        System.out.println(" [2] Lihat Semua Benda & Hasil Perhitungan");
        System.out.println(" [3] Demonstrasi Polimorfisme Murni");
        System.out.println(" [4] DEMONSTRASI MULTITHREADING (100.000 DATA)");
        System.out.println(" [5] Keluar");
        System.out.println("=======================================================");
    }

    private static void buatBendaGeometri() {
        System.out.println("\n--- Pilih Jenis Benda Geometri ---");
        System.out.println("[1] Elips (2D)\n[2] Bola\n[3] Tabung\n[4] Kerucut Alas Elips");
        System.out.println("[5] Kerucut Terpancung Alas Elips\n[6] Cincin\n[7] Juring Bola\n[8] Tembereng Bola");
        System.out.print("Pilih [1-8]: ");
        int jenis = bacaInt();
        BendaGeometri benda = null;
        
        switch (jenis) {
            case 1:
                System.out.print("Sumbu a & b (pisahkan dengan enter): ");
                benda = new Elips("Elips Manual", bacaDouble(), bacaDouble());
                break;
            case 2:
                System.out.print("Jari-jari: ");
                benda = new Bola("Bola Manual", bacaDouble());
                break;
            case 3:
                System.out.print("Sumbu a, b, & tinggi (pisahkan dengan enter): ");
                benda = new Tabung("Tabung Alas Elips Manual", bacaDouble(), bacaDouble(), bacaDouble());
                break;
            case 4:
                System.out.print("Sumbu a, b, & tinggi (pisahkan dengan enter): ");
                benda = new KerucutDenganAlasElips("Kerucut Alas Elips Manual", bacaDouble(), bacaDouble(), bacaDouble());
                break;
            case 5:
                System.out.print("Sumbu a1, b1, a2, b2, & tinggi (pisahkan dengan enter): ");
                benda = new KerucutTerpancungDenganAlasElips("Kerucut Terpancung Manual", bacaDouble(), bacaDouble(), bacaDouble(), bacaDouble(), bacaDouble());
                break;
            case 6:
                System.out.print("R Mayor & r Minor (pisahkan dengan enter): ");
                benda = new Cincin("Cincin Bola Manual", bacaDouble(), bacaDouble());
                break;
            case 7:
                System.out.print("Jari-jari & tinggi topi (pisahkan dengan enter): ");
                benda = new Juring("Juring Bola Manual", bacaDouble(), bacaDouble());
                break;
            case 8:
                System.out.print("Jari-jari & tinggi topi (pisahkan dengan enter): ");
                benda = new Tembereng("Tembereng Bola Manual", bacaDouble(), bacaDouble());
                break;
            default:
                System.out.println("[!] Pilihan tidak valid.");
                return;
        }
        
        daftarBenda.add(benda);
        System.out.println("\n[OK] Berhasil dibuat!");
        benda.cetakInfo();
    }

    private static void lihatSemuaBenda() {
        if (daftarBenda.isEmpty()) {
            System.out.println("\n[!] Daftar kosong.");
            return;
        }
        for (int i = 0; i < daftarBenda.size(); i++) {
            System.out.println("\n[Benda #" + (i + 1) + "]");
            daftarBenda.get(i).cetakInfo();
        }
    }

    private static void demoPolymorphism() {
        if (daftarBenda.isEmpty()) {
            System.out.println("\n[!] Masukkan data manual terlebih dahulu di menu [1].");
            return;
        }
        System.out.println("\n--- DEMONSTRASI POLIMORFISME MURNI ---");
        for (BendaGeometri benda : daftarBenda) {
            System.out.println("Nama Objek : " + benda.getNamaBenda());
            System.out.println("-> Luas    : " + String.format("%.2f", benda.hitungLuas()));
            System.out.println("-> Volume  : " + String.format("%.2f", benda.hitungVolume()));
            System.out.println("----------------------------------------");
        }
    }

    private static void demoMultithreading() {
        int jumlahData = 100000;
        System.out.println("\n--- DEMONSTRASI MULTITHREADING DENGAN " + jumlahData + " DATA ---");
        System.out.println("Membuat " + jumlahData + " data geometri otomatis di memory...");
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

        long startSequential = System.currentTimeMillis();
        for (BendaGeometri bg : dataMassal) {
            for (int i = 0; i < 50; i++) { 
                bg.hitungVolume();
                bg.hitungLuas();
            }
        }
        long endSequential = System.currentTimeMillis();
        long waktuSequential = endSequential - startSequential;
        System.out.println("\n[1] Waktu eksekusi SINGLE THREAD (Sequential): " + waktuSequential + " ms");

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
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread Utama Terganggu.");
            }
        }
        long endParallel = System.currentTimeMillis();
        long waktuParallel = endParallel - startParallel;

        System.out.println("[2] Waktu eksekusi MULTITHREADING (" + jumlahThread + " Threads): " + waktuParallel + " ms");
        if (waktuParallel > 0) {
            System.out.println("\nKESIMPULAN: Multithreading lebih cepat " + String.format("%.2f", (double) waktuSequential / waktuParallel) + "x lipat!");
        }
    }

    private static double bacaDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Input angka tidak valid, masukkan ulang: ");
            }
        }
    }

    private static int bacaInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("[!] Input angka tidak valid, masukkan ulang: ");
            }
        }
    }
}
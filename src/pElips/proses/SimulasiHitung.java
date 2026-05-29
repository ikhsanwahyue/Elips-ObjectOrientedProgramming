package pElips.proses;

import pElips.model.BendaGeometri;
import pElips.model.Bola;
import pElips.model.Cincin;
import pElips.model.Elips;
import pElips.model.Juring;
import pElips.model.KerucutDenganAlasElips;
import pElips.model.KerucutTerpancungDenganAlasElips;
import pElips.model.Tabung;
import pElips.model.Tembereng;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulasiHitung {
    private static final int DATA_PER_BENDA = 100000;
    private static final String[] NAMA_KATEGORI = {
            "Elips", "Bola", "Tabung", "Kerucut Alas Elips",
            "Kerucut Terpancung", "Cincin", "Juring Bola", "Tembereng Bola"
    };
    private static final int JUMLAH_KATEGORI = NAMA_KATEGORI.length;
    private static final int TOTAL_DATA_DEMO = DATA_PER_BENDA * JUMLAH_KATEGORI;

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<BendaGeometri> daftarBenda = new ArrayList<>();

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
                    System.out.println("\nTerima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("[!] Pilihan tidak valid.\n");
                    break;
            }
        }
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n=======================================================");
        System.out.println(" SIMULASI HITUNG BENDA GEOMETRI ELIPS DAN BOLA");
        System.out.println(" Projek Akhir - Tugas Kelompok PBO");
        System.out.println("=======================================================");
        System.out.println(" [1] Buat Benda Geometri Baru (Manual)");
        System.out.println(" [2] Lihat Semua Benda dan Hasil Perhitungan");
        System.out.println(" [3] Demonstrasi Polimorfisme");
        System.out.println(" [4] Demonstrasi Multithreading (100.000 Data per Benda)");
        System.out.println(" [5] Keluar");
        System.out.println("=======================================================");
    }

    private static void buatBendaGeometri() {
        System.out.println("\n--- Pilih Jenis Benda Geometri ---");
        System.out.println("[1] Elips (2D)");
        System.out.println("[2] Bola");
        System.out.println("[3] Tabung");
        System.out.println("[4] Kerucut Alas Elips");
        System.out.println("[5] Kerucut Terpancung Alas Elips");
        System.out.println("[6] Cincin");
        System.out.println("[7] Juring Bola");
        System.out.println("[8] Tembereng Bola");
        System.out.print("Pilih [1-8]: ");
        int jenis = bacaInt();

        try {
            BendaGeometri benda;
            switch (jenis) {
                case 1:
                    System.out.print("Sumbu a dan b (pisahkan dengan enter): ");
                    benda = new Elips("Elips Manual", bacaDouble(), bacaDouble());
                    break;
                case 2:
                    System.out.print("Jari-jari: ");
                    benda = new Bola("Bola Manual", bacaDouble());
                    break;
                case 3:
                    System.out.print("Jari-jari alas dan tinggi (pisahkan dengan enter): ");
                    benda = new Tabung("Tabung Manual", bacaDouble(), bacaDouble());
                    break;
                case 4:
                    System.out.print("Sumbu a, b, dan tinggi (pisahkan dengan enter): ");
                    benda = new KerucutDenganAlasElips("Kerucut Alas Elips Manual", bacaDouble(), bacaDouble(),
                            bacaDouble());
                    break;
                case 5:
                    System.out.print("Sumbu a1, b1, a2, b2, dan tinggi (pisahkan dengan enter): ");
                    benda = new KerucutTerpancungDenganAlasElips("Kerucut Terpancung Manual", bacaDouble(),
                            bacaDouble(), bacaDouble(), bacaDouble(), bacaDouble());
                    break;
                case 6:
                    System.out.print("R mayor dan r minor (pisahkan dengan enter): ");
                    benda = new Cincin("Cincin Manual", bacaDouble(), bacaDouble());
                    break;
                case 7:
                    System.out.print("Jari-jari dan tinggi topi (pisahkan dengan enter): ");
                    benda = new Juring("Juring Bola Manual", bacaDouble(), bacaDouble());
                    break;
                case 8:
                    System.out.print("Jari-jari dan tinggi topi (pisahkan dengan enter): ");
                    benda = new Tembereng("Tembereng Bola Manual", bacaDouble(), bacaDouble());
                    break;
                default:
                    System.out.println("[!] Pilihan tidak valid.");
                    return;
            }

            daftarBenda.add(benda);
            System.out.println("\n[OK] Berhasil dibuat.");
            benda.cetakInfo();
        } catch (IllegalArgumentException ex) {
            System.out.println("[!] " + ex.getMessage());
        }
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

        System.out.println("\n--- DEMONSTRASI POLIMORFISME ---");
        for (BendaGeometri benda : daftarBenda) {
            benda.hitungSemua();
            System.out.println("Nama Objek : " + benda.getNamaBenda());
            System.out.println("Jenis      : " + benda.getClass().getSimpleName());
            System.out.println("Luas       : " + String.format("%.4f", benda.getLuas()));
            System.out.println("Keliling   : " + String.format("%.4f", benda.getKeliling()));
            System.out.println("Volume     : " + String.format("%.4f", benda.getVolume()));
            System.out.println("----------------------------------------");
        }
    }

    private static void demoMultithreading() {
        System.out.println("\n--- DEMONSTRASI MULTITHREADING 100.000 DATA PER BENDA ---");
        System.out.println("Membuat " + formatInteger(DATA_PER_BENDA) + " data untuk tiap jenis benda.");
        System.out.println("Total data: " + formatInteger(TOTAL_DATA_DEMO) + " objek.");
        List<BendaGeometri> dataMassal = buatDataMassalPerBenda();

        AtomicInteger totalSelesai = new AtomicInteger();
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
                        totalSelesai.incrementAndGet();
                    }
                    System.out.println("\nWorker " + (kategoriIndex + 1) + " - " + NAMA_KATEGORI[kategoriIndex]
                            + " selesai memproses " + formatInteger(toIndex - fromIndex) + " data.");
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            while (!latch.await(500, TimeUnit.MILLISECONDS)) {
                tampilkanProgressConsole(totalSelesai.get(), TOTAL_DATA_DEMO);
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("\n[!] Proses multithreading terganggu.");
            return;
        } finally {
            executor.shutdown();
        }

        tampilkanProgressConsole(TOTAL_DATA_DEMO, TOTAL_DATA_DEMO);
        System.out.println("\nSelesai dalam " + (System.currentTimeMillis() - mulai) + " ms.");
        System.out.println("Catatan: visualisasi batang berjalan lengkap tersedia di GUI utama.");
    }

    private static List<BendaGeometri> buatDataMassalPerBenda() {
        List<BendaGeometri> data = new ArrayList<>(TOTAL_DATA_DEMO);
        Random rand = new Random();

        for (int kategori = 0; kategori < JUMLAH_KATEGORI; kategori++) {
            for (int nomor = 1; nomor <= DATA_PER_BENDA; nomor++) {
                data.add(buatObjekAcak(kategori, nomor, rand));
            }
        }

        return data;
    }

    private static BendaGeometri buatObjekAcak(int kategori, int nomor, Random rand) {
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
            default:
                return new Tembereng(nama, r, acak(rand, 0.5, 2 * r));
        }
    }

    private static double acak(Random rand, double min, double max) {
        return min + rand.nextDouble() * (max - min);
    }

    private static void tampilkanProgressConsole(int selesai, int total) {
        int persen = (int) ((selesai * 100L) / total);
        int panjangBar = 30;
        int terisi = (int) ((persen / 100.0) * panjangBar);
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < panjangBar; i++) {
            bar.append(i < terisi ? '#' : '-');
        }
        System.out.print("\rProgress [" + bar + "] " + persen + "% (" + selesai + "/" + total + ")");
    }

    private static String formatInteger(int nilai) {
        return String.format("%,d", nilai).replace(',', '.');
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

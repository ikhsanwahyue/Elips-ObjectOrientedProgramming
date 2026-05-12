/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 * Class KerucutTerpancungDenganAlasElips - Benda 3D Limas Terpancung (Frustum)
 * dengan alas dan penampang atas berbentuk Elips.
 * 
 * Pilar OOP yang diterapkan:
 * 1. Encapsulation & Information Hiding - atribut private untuk sumbu atas
 * 2. Inheritance - extends KerucutDenganALasElips (mewarisi sumbuA, sumbuB, tinggi)
 * 3. Overloading - dua constructor dengan parameter berbeda
 * 4. Overriding & Polymorphism - override hitungLuas(), hitungVolume(), cetakInfo()
 * 5. Multithreading - kompatibel dengan KalkulasiThread
 *
 * Keterangan:
 * - Alas bawah (elips besar): sumbuA, sumbuB (diwarisi dari Elips)
 * - Alas atas (elips kecil) : sumbuA2, sumbuB2 (atribut baru)
 * - Tinggi kerucut terpancung: tinggi (diwarisi dari KerucutDenganALasElips)
 *
 * Rumus:
 * - Volume = (1/3) * π * t * (a1*b1 + a2*b2 + √(a1*b1*a2*b2))
 * - Luas Alas Bawah = π * a1 * b1
 * - Luas Alas Atas  = π * a2 * b2
 * - Luas Selimut ≈ (1/2) * (K_bawah + K_atas) * s  (pendekatan)
 *   dimana s = √(t² + ((a1-a2+b1-b2)/2)²) sebagai garis pelukis rata-rata
 * - Luas Total = Luas Alas Bawah + Luas Alas Atas + Luas Selimut
 *
 * @author Lahar
 */
public class KerucutTerpancungDenganAlasElips extends KerucutDenganALasElips {

    // Encapsulation: atribut private untuk dimensi elips bagian atas
    private double sumbuA2; // Sumbu semi-mayor alas atas
    private double sumbuB2; // Sumbu semi-minor alas atas

    /**
     * Pilar: OVERLOADING (Constructor 1)
     * Constructor default jika dimensi atas belum diketahui.
     */
    public KerucutTerpancungDenganAlasElips(String nama) {
        super(nama);
        this.sumbuA2 = 0;
        this.sumbuB2 = 0;
    }

    /**
     * Pilar: OVERLOADING (Constructor 2)
     * Constructor lengkap dengan parameter alas bawah, alas atas, dan tinggi.
     * @param nama Nama benda geometri
     * @param a1 Sumbu semi-mayor alas bawah (elips besar)
     * @param b1 Sumbu semi-minor alas bawah (elips besar)
     * @param a2 Sumbu semi-mayor alas atas (elips kecil)
     * @param b2 Sumbu semi-minor alas atas (elips kecil)
     * @param tinggi Tinggi kerucut terpancung
     */
    public KerucutTerpancungDenganAlasElips(String nama, double a1, double b1,
                                             double a2, double b2, double tinggi) {
        super(nama, a1, b1, tinggi);
        this.sumbuA2 = a2;
        this.sumbuB2 = b2;
    }

    // --- Implementasi Pilar ENCAPSULATION (Getter & Setter) ---
    public double getSumbuA2() {
        return sumbuA2;
    }

    public void setSumbuA2(double sumbuA2) {
        this.sumbuA2 = sumbuA2;
    }

    public double getSumbuB2() {
        return sumbuB2;
    }

    public void setSumbuB2(double sumbuB2) {
        this.sumbuB2 = sumbuB2;
    }

    // --- Implementasi Pilar OVERRIDING & POLYMORPHISM ---

    /**
     * Menghitung keliling elips menggunakan rumus pendekatan Ramanujan.
     * Digunakan untuk menghitung keliling alas bawah dan alas atas.
     */
    private double hitungKelilingElips(double a, double b) {
        return Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
    }

    /**
     * Menghitung garis pelukis rata-rata (slant height) kerucut terpancung.
     * s = √(t² + ((a1-a2)² + (b1-b2)²) / 2)
     */
    private double hitungSlantHeight() {
        double diffA = sumbuA - sumbuA2;
        double diffB = sumbuB - sumbuB2;
        return Math.sqrt(getTinggi() * getTinggi() + (diffA * diffA + diffB * diffB) / 2.0);
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung luas permukaan total kerucut terpancung dengan alas elips.
     * Luas Total = Luas Alas Bawah + Luas Alas Atas + Luas Selimut
     */
    @Override
    public double hitungLuas() {
        double luasAlasBawah = Math.PI * sumbuA * sumbuB;
        double luasAlasAtas = Math.PI * sumbuA2 * sumbuB2;

        // Keliling alas bawah dan atas menggunakan pendekatan Ramanujan
        double kelilingBawah = hitungKelilingElips(sumbuA, sumbuB);
        double kelilingAtas = hitungKelilingElips(sumbuA2, sumbuB2);

        // Garis pelukis rata-rata
        double s = hitungSlantHeight();

        // Luas selimut ≈ (1/2) * (K_bawah + K_atas) * s
        double luasSelimut = 0.5 * (kelilingBawah + kelilingAtas) * s;

        return luasAlasBawah + luasAlasAtas + luasSelimut;
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung keliling gabungan (keliling alas bawah + keliling alas atas).
     */
    @Override
    public double hitungKeliling() {
        double kelilingBawah = hitungKelilingElips(sumbuA, sumbuB);
        double kelilingAtas = hitungKelilingElips(sumbuA2, sumbuB2);
        return kelilingBawah + kelilingAtas;
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung volume kerucut terpancung dengan alas elips.
     * V = (1/3) * π * t * (a1*b1 + a2*b2 + √(a1*b1*a2*b2))
     */
    @Override
    public double hitungVolume() {
        double ab1 = sumbuA * sumbuB;     // produk sumbu alas bawah
        double ab2 = sumbuA2 * sumbuB2;   // produk sumbu alas atas
        return (1.0 / 3.0) * Math.PI * getTinggi() * (ab1 + ab2 + Math.sqrt(ab1 * ab2));
    }

    /**
     * Pilar: OVERRIDING
     * Mencetak informasi lengkap kerucut terpancung dengan alas elips.
     */
    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("=== Alas Bawah (Elips Besar) ===");
        System.out.println("Sumbu Semi-Mayor (A1) : " + sumbuA);
        System.out.println("Sumbu Semi-Minor (B1) : " + sumbuB);
        System.out.println("Luas Alas Bawah       : " + String.format("%.2f", Math.PI * sumbuA * sumbuB));
        System.out.println("Keliling Alas Bawah   : " + String.format("%.2f", hitungKelilingElips(sumbuA, sumbuB)));
        System.out.println("=== Alas Atas (Elips Kecil) ===");
        System.out.println("Sumbu Semi-Mayor (A2) : " + sumbuA2);
        System.out.println("Sumbu Semi-Minor (B2) : " + sumbuB2);
        System.out.println("Luas Alas Atas        : " + String.format("%.2f", Math.PI * sumbuA2 * sumbuB2));
        System.out.println("Keliling Alas Atas    : " + String.format("%.2f", hitungKelilingElips(sumbuA2, sumbuB2)));
        System.out.println("=== Dimensi Kerucut Terpancung ===");
        System.out.println("Tinggi (t)            : " + getTinggi());
        System.out.println("Garis Pelukis (s)     : " + String.format("%.2f", hitungSlantHeight()));
        System.out.println("Luas Permukaan Total  : " + String.format("%.2f", hitungLuas()));
        System.out.println("Volume                : " + String.format("%.2f", hitungVolume()));
    }
}

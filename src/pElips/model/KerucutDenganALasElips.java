/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 * Class KerucutDenganAlasElips - Benda 3D Limas dengan alas berbentuk Elips.
 * 
 * Pilar OOP yang diterapkan:
 * 1. Encapsulation & Information Hiding - atribut tinggi bersifat private
 * 2. Inheritance - extends Elips (mewarisi sumbuA, sumbuB)
 * 3. Overloading - dua constructor dengan parameter berbeda
 * 4. Overriding & Polymorphism - override hitungLuas(), hitungVolume(),
 * hitungKeliling(), cetakInfo()
 * 5. Multithreading - kompatibel dengan KalkulasiThread
 *
 * Rumus:
 * - Volume = (1/3) * π * a * b * t
 * - Luas Selimut ≈ π * a * s1 + π * b * s2 (pendekatan untuk alas elips)
 * dimana s1 = √(b² + t²), s2 = √(a² + t²)
 * - Luas Alas = π * a * b (luas elips)
 * - Luas Total = Luas Selimut + Luas Alas
 * - Keliling Alas menggunakan rumus pendekatan Ramanujan (diwarisi dari Elips)
 *
 * @author Lahar 2
 */
public class KerucutDenganAlasElips extends Elips {

    // Encapsulation: atribut private untuk keamanan data
    private double tinggi;

    /**
     * Pilar: OVERLOADING (Constructor 1)
     * Constructor default jika tinggi belum diketahui.
     */
    public KerucutDenganAlasElips(String nama) {
        super(nama);
        this.tinggi = 0;
    }

    /**
     * Pilar: OVERLOADING (Constructor 2)
     * Constructor lengkap dengan parameter sumbu A, sumbu B, dan tinggi.
     */
    public KerucutDenganAlasElips(String nama, double a, double b, double tinggi) {
        super(nama, a, b);
        this.tinggi = tinggi;
    }

    // --- Implementasi Pilar ENCAPSULATION (Getter & Setter) ---
    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }

    // --- Implementasi Pilar OVERRIDING & POLYMORPHISM ---

    /**
     * Menghitung garis pelukis (slant height) terhadap sumbu semi-minor (b).
     * s1 = √(b² + t²)
     */
    private double hitungSlantHeight1() {
        return Math.sqrt(sumbuB * sumbuB + tinggi * tinggi);
    }

    /**
     * Menghitung garis pelukis (slant height) terhadap sumbu semi-mayor (a).
     * s2 = √(a² + t²)
     */
    private double hitungSlantHeight2() {
        return Math.sqrt(sumbuA * sumbuA + tinggi * tinggi);
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung luas permukaan total kerucut dengan alas elips.
     * Luas Total = Luas Alas + Luas Selimut
     * Luas Alas = π * a * b
     * Luas Selimut ≈ π * a * s1 + π * b * s2 (pendekatan)
     * dimana s1 = √(b² + t²), s2 = √(a² + t²)
     */
    @Override
    public double hitungLuas() {
        double luasAlas = Math.PI * sumbuA * sumbuB;
        double s1 = hitungSlantHeight1();
        double s2 = hitungSlantHeight2();
        // Pendekatan luas selimut kerucut elips menggunakan rata-rata
        double luasSelimut = Math.PI * (sumbuA * s1 + sumbuB * s2) / 2.0;
        return luasAlas + luasSelimut;
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung keliling alas kerucut (keliling elips).
     * Menggunakan rumus pendekatan Ramanujan yang diwarisi dari class Elips.
     */
    @Override
    public double hitungKeliling() {
        // Keliling alas = keliling elips (menggunakan method induk)
        return super.hitungKeliling();
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung volume kerucut dengan alas elips.
     * V = (1/3) * π * a * b * t
     */
    @Override
    public double hitungVolume() {
        return (1.0 / 3.0) * Math.PI * sumbuA * sumbuB * tinggi;
    }

    /**
     * Pilar: OVERRIDING
     * Mencetak informasi lengkap kerucut dengan alas elips.
     */
    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Sumbu Semi-Mayor (A) : " + sumbuA);
        System.out.println("Sumbu Semi-Minor (B) : " + sumbuB);
        System.out.println("Tinggi (t)           : " + tinggi);
        System.out.println("Garis Pelukis s1     : " + String.format("%.2f", hitungSlantHeight1()));
        System.out.println("Garis Pelukis s2     : " + String.format("%.2f", hitungSlantHeight2()));
        System.out.println("Luas Alas (Elips)    : " + String.format("%.2f", Math.PI * sumbuA * sumbuB));
        System.out.println("Luas Permukaan Total : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling Alas        : " + String.format("%.2f", hitungKeliling()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 * Class Cincin - Benda 3D berbentuk Torus (Donat) berbasis Elips.
 * 
 * Pilar OOP yang diterapkan:
 * 1. Encapsulation & Information Hiding - atribut jariJariMayor bersifat private
 * 2. Inheritance - extends Bola (mewarisi jariJari sebagai radius tabung)
 * 3. Overloading - dua constructor dengan parameter berbeda
 * 4. Overriding & Polymorphism - override hitungLuas(), hitungVolume(),
 *    hitungKeliling(), cetakInfo()
 * 5. Multithreading - kompatibel dengan KalkulasiThread
 *
 * Keterangan:
 * - jariJari (r) = jari-jari tabung / penampang (diwarisi dari Bola)
 * - jariJariMayor (R) = jarak dari pusat torus ke pusat tabung
 *
 * Rumus:
 * - Volume = 2π²Rr²
 * - Luas Permukaan = 4π²Rr
 * - Keliling Luar = 2π(R + r)
 *
 * @author LENOVO
 */
public class Cincin extends Bola {

    // Encapsulation: atribut private untuk keamanan data
    private double jariJariMayor; // R: jarak pusat torus ke pusat tabung

    /**
     * Pilar: OVERLOADING (Constructor 1)
     * Constructor default jika jari-jari mayor belum diketahui.
     */
    public Cincin(String nama) {
        super(nama, 0);
        this.jariJariMayor = 0;
    }

    /**
     * Pilar: OVERLOADING (Constructor 2)
     * Constructor lengkap dengan parameter.
     * 
     * @param nama  Nama benda geometri
     * @param R     Jari-jari Mayor (jarak pusat torus ke pusat tabung)
     * @param r     Jari-jari Minor / tabung (radius penampang)
     */
    public Cincin(String nama, double R, double r) {
        super(nama, r);
        this.jariJariMayor = R;
    }

    // --- Implementasi Pilar ENCAPSULATION (Getter & Setter) ---
    public double getJariJariMayor() {
        return jariJariMayor;
    }

    public void setJariJariMayor(double R) {
        this.jariJariMayor = R;
    }

    // --- Implementasi Pilar OVERRIDING & POLYMORPHISM ---

    /**
     * Pilar: OVERRIDING
     * Menghitung luas permukaan torus.
     * Luas = 4π²Rr
     */
    @Override
    public double hitungLuas() {
        return 4 * Math.PI * Math.PI * jariJariMayor * jariJari;
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung keliling luar torus.
     * Keliling = 2π(R + r)
     */
    @Override
    public double hitungKeliling() {
        return 2 * Math.PI * (jariJariMayor + jariJari);
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung volume torus.
     * Volume = 2π²Rr²
     */
    @Override
    public double hitungVolume() {
        return 2 * Math.PI * Math.PI * jariJariMayor * Math.pow(jariJari, 2);
    }

    /**
     * Pilar: OVERRIDING
     * Mencetak informasi lengkap cincin (torus).
     */
    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Mayor (R)  : " + jariJariMayor);
        System.out.println("Jari-jari Minor (r)  : " + jariJari);
        System.out.println("Luas Permukaan       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling Luar        : " + String.format("%.2f", hitungKeliling()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 * Class Juring - Benda 3D Juring Bola (Spherical Sector) berbasis Elips.
 * 
 * Pilar OOP yang diterapkan:
 * 1. Encapsulation & Information Hiding - atribut tinggiTopi bersifat private
 * 2. Inheritance - extends Bola (mewarisi jariJari)
 * 3. Overloading - dua constructor dengan parameter berbeda
 * 4. Overriding & Polymorphism - override hitungLuas(), hitungVolume(),
 *    hitungKeliling(), cetakInfo()
 * 5. Multithreading - kompatibel dengan KalkulasiThread
 *
 * Keterangan:
 * - jariJari (r) = jari-jari bola (diwarisi dari Bola)
 * - tinggiTopi (h) = tinggi topi bola (spherical cap height)
 * - a = √(h(2r - h)) = jari-jari alas kerucut
 *
 * Rumus:
 * - Volume = (2/3)πr²h
 * - Luas Permukaan = πr(2h + a)
 * - Keliling Alas = 2πa
 *
 * @author LENOVO
 */
public class Juring extends Bola {

    // Encapsulation: atribut private untuk keamanan data
    private double tinggiTopi; // h: tinggi topi bola

    /**
     * Pilar: OVERLOADING (Constructor 1)
     * Constructor default jika tinggi topi belum diketahui.
     */
    public Juring(String nama) {
        super(nama, 0);
        this.tinggiTopi = 0;
    }

    /**
     * Pilar: OVERLOADING (Constructor 2)
     * Constructor lengkap dengan parameter.
     * 
     * @param nama  Nama benda geometri
     * @param r     Jari-jari bola
     * @param h     Tinggi topi bola (spherical cap height)
     */
    public Juring(String nama, double r, double h) {
        super(nama, r);
        this.tinggiTopi = h;
    }

    // --- Implementasi Pilar ENCAPSULATION (Getter & Setter) ---
    public double getTinggiTopi() {
        return tinggiTopi;
    }

    public void setTinggiTopi(double h) {
        this.tinggiTopi = h;
    }

    // --- Helper Method ---

    /**
     * Menghitung jari-jari alas kerucut (base radius).
     * a = √(h(2r - h))
     */
    private double hitungJariJariAlas() {
        return Math.sqrt(tinggiTopi * (2 * jariJari - tinggiTopi));
    }

    // --- Implementasi Pilar OVERRIDING & POLYMORPHISM ---

    /**
     * Pilar: OVERRIDING
     * Menghitung luas permukaan juring bola.
     * Luas = πr(2h + a)
     * dimana a = √(h(2r - h))
     */
    @Override
    public double hitungLuas() {
        double a = hitungJariJariAlas();
        return Math.PI * jariJari * (2 * tinggiTopi + a);
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung keliling alas kerucut pada juring bola.
     * Keliling = 2πa
     */
    @Override
    public double hitungKeliling() {
        double a = hitungJariJariAlas();
        return 2 * Math.PI * a;
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung volume juring bola.
     * Volume = (2/3)πr²h
     */
    @Override
    public double hitungVolume() {
        return (2.0 / 3.0) * Math.PI * Math.pow(jariJari, 2) * tinggiTopi;
    }

    /**
     * Pilar: OVERRIDING
     * Mencetak informasi lengkap juring bola.
     */
    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Bola (r)   : " + jariJari);
        System.out.println("Tinggi Topi (h)      : " + tinggiTopi);
        System.out.println("Jari-jari Alas (a)   : " + String.format("%.2f", hitungJariJariAlas()));
        System.out.println("Luas Permukaan       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling Alas        : " + String.format("%.2f", hitungKeliling()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}

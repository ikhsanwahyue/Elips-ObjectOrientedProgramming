/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 * Class Tembereng - Benda 3D Tembereng Bola (Spherical Cap) berbasis Elips.
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
 * - tinggiTopi (h) = tinggi tembereng / cap height
 * - a = √(h(2r - h)) = jari-jari alas tembereng
 *
 * Rumus:
 * - Volume = (πh²/3)(3r - h)
 * - Luas Permukaan Total = 2πrh + πa² (selimut + alas)
 * - Keliling Alas = 2πa
 *
 * @author LENOVO
 */
public class Tembereng extends Bola {

    // Encapsulation: atribut private untuk keamanan data
    private double tinggiTopi; // h: tinggi tembereng

    /**
     * Pilar: OVERLOADING (Constructor 1)
     * Constructor default jika tinggi topi belum diketahui.
     */
    public Tembereng(String nama) {
        super(nama, 0);
        this.tinggiTopi = 0;
    }

    /**
     * Pilar: OVERLOADING (Constructor 2)
     * Constructor lengkap dengan parameter.
     * 
     * @param nama  Nama benda geometri
     * @param r     Jari-jari bola
     * @param h     Tinggi tembereng (cap height)
     */
    public Tembereng(String nama, double r, double h) {
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
     * Menghitung jari-jari alas tembereng (base radius).
     * a = √(h(2r - h))
     */
    private double hitungJariJariAlas() {
        return Math.sqrt(tinggiTopi * (2 * jariJari - tinggiTopi));
    }

    // --- Implementasi Pilar OVERRIDING & POLYMORPHISM ---

    /**
     * Pilar: OVERRIDING
     * Menghitung luas permukaan total tembereng bola.
     * Luas = Luas Selimut + Luas Alas
     *      = 2πrh + πa²
     */
    @Override
    public double hitungLuas() {
        double a = hitungJariJariAlas();
        double luasSelimut = 2 * Math.PI * jariJari * tinggiTopi;
        double luasAlas = Math.PI * a * a;
        return luasSelimut + luasAlas;
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung keliling alas tembereng bola.
     * Keliling = 2πa
     */
    @Override
    public double hitungKeliling() {
        double a = hitungJariJariAlas();
        return 2 * Math.PI * a;
    }

    /**
     * Pilar: OVERRIDING
     * Menghitung volume tembereng bola.
     * Volume = (πh²/3)(3r - h)
     */
    @Override
    public double hitungVolume() {
        return (Math.PI * Math.pow(tinggiTopi, 2) / 3.0) * (3 * jariJari - tinggiTopi);
    }

    /**
     * Pilar: OVERRIDING
     * Mencetak informasi lengkap tembereng bola.
     */
    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Bola (r)   : " + jariJari);
        System.out.println("Tinggi Topi (h)      : " + tinggiTopi);
        System.out.println("Jari-jari Alas (a)   : " + String.format("%.2f", hitungJariJariAlas()));
        System.out.println("Luas Selimut         : " + String.format("%.2f", 2 * Math.PI * jariJari * tinggiTopi));
        System.out.println("Luas Alas            : " + String.format("%.2f", Math.PI * Math.pow(hitungJariJariAlas(), 2)));
        System.out.println("Luas Permukaan Total : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling Alas        : " + String.format("%.2f", hitungKeliling()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}

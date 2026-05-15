/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 *
 * @author LENOVO
 */
public class Bola extends BendaGeometri implements KalkulasiGeometri {
    protected double jariJari;

    /**
     * Pilar: OVERLOADING (Constructor 1)
     * Constructor default tanpa parameter jari-jari.
     */
    public Bola() {
        super("Bola");
    }

    /**
     * Pilar: OVERLOADING (Constructor 2)
     * Constructor lengkap dengan parameter nama dan jari-jari.
     */
    public Bola(String nama, double r) {
        super(nama);
        this.jariJari = r;
    }

    // --- Implementasi Pilar ENCAPSULATION (Getter & Setter) ---
    public double getJariJari() {
        return jariJari;
    }

    public void setJariJari(double r) {
        this.jariJari = r;
    }

    // --- Implementasi Pilar OVERRIDING & POLYMORPHISM ---

    @Override
    public double hitungLuas() {
        // Luas Permukaan Bola = 4 * π * r²
        return 4 * Math.PI * Math.pow(jariJari, 2);
    }

    @Override
    public double hitungKeliling() {
        // Keliling Lingkaran Besar Bola = 2 * π * r
        return 2 * Math.PI * jariJari;
    }

    @Override
    public double hitungVolume() {
        // Volume Bola = (4/3) * π * r³
        return (4.0 / 3.0) * Math.PI * Math.pow(jariJari, 3);
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari (r)        : " + jariJari);
        System.out.println("Luas Permukaan       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling             : " + String.format("%.2f", hitungKeliling()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}
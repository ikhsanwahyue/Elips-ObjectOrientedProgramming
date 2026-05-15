/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 *
 * @author LENOVO
 */
public class Elips extends BendaGeometri implements KalkulasiGeometri {
    // Encapsulation: menggunakan protected agar bisa diwariskan ke benda 3D (seperti Bola/Spheroid)
    protected double sumbuA;
    protected double sumbuB;

    /**
     * Pilar: OVERLOADING (Constructor 1)
     * Constructor default jika sumbu belum diketahui.
     */
    public Elips(String nama) {
        super(nama);
        this.sumbuA = 0;
        this.sumbuB = 0;
    }

    /**
     * Pilar: OVERLOADING (Constructor 2)
     * Constructor lengkap dengan parameter.
     */
    public Elips(String nama, double a, double b) {
        super(nama);
        this.sumbuA = a;
        this.sumbuB = b;
    }

    // --- Implementasi Pilar ENCAPSULATION (Getter & Setter) ---
    public double getSumbuA() { return sumbuA; }
    public void setSumbuA(double a) { this.sumbuA = a; }
    public double getSumbuB() { return sumbuB; }
    public void setSumbuB(double b) { this.sumbuB = b; }

    // --- Implementasi Pilar OVERRIDING & POLYMORPHISM ---
    
    @Override
    public double hitungLuas() {
        return Math.PI * sumbuA * sumbuB;
    }

    @Override
    public double hitungKeliling() {
        // Menggunakan rumus pendekatan Ramanujan (lebih akademis)
        return Math.PI * (3 * (sumbuA + sumbuB) - Math.sqrt((3 * sumbuA + sumbuB) * (sumbuA + 3 * sumbuB)));
    }

    @Override
    public double hitungVolume() {
        // Elips adalah benda 2D, volumenya selalu 0
        return 0.0;
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Sumbu Semi-Mayor (A) : " + sumbuA);
        System.out.println("Sumbu Semi-Minor (B) : " + sumbuB);
        System.out.println("Luas                 : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling             : " + String.format("%.2f", hitungKeliling()));
    }
}
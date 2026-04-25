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
    
    // Protected agar bisa diakses langsung oleh class benda 3D berbasis elips (Bola, Kerucut, dll..)
    protected double sumbuA;
    protected double sumbuB;
    
    public Elips(String nama, double a, double b) {
        super(nama);
        this.sumbuA = a;
        this.sumbuB = b;
    }
    
    // Implementasi Pilar Encapsulation
    public double getSumbuA() {
        return sumbuA;
    }
    
    public void setSumbuA(double sumbuA) {
        this.sumbuA = sumbuA;
    }
    
    public double sumbuB() {
        return sumbuB;
    }
    
    public void setSumbuB(double sumbuB) {
        this.sumbuB = sumbuB;
    }
    
    // Implementasi Pilar Override & Kalkulasi
    @Override
    public double hitungLuas() {
        return Math.PI * sumbuA * sumbuB;
    }
    
    @Override
    public double hitungKeliling() {
        return Math.PI * (3 * (sumbuA + sumbuB) - Math.sqrt((3 * sumbuA + sumbuB) * (sumbuA + 3 * sumbuB)));
    }
    
    @Override
    public double hitungVolume() {
        
        // Elips adalah benda 2D, jadi volumenya nol
        return 0.0;
    }
    
    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Sumbu A (Mayor) : " + sumbuA);
        System.out.println("Sumbu B (Minor) : " + sumbuB);
        System.out.println("Luas            : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling        : " + String.format("%.2f", hitungKeliling()));
    }
}

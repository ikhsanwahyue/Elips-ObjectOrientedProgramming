package pElips.model;

public class KerucutTerpancungDenganAlasElips extends KerucutDenganAlasElips {

    private double sumbuA2; 
    private double sumbuB2; 

    public KerucutTerpancungDenganAlasElips(String nama) {
        super(nama);
        this.sumbuA2 = 0;
        this.sumbuB2 = 0;
    }

    public KerucutTerpancungDenganAlasElips(String nama, double a1, double b1, double a2, double b2, double tinggi) {
        super(nama, a1, b1, tinggi);
        this.sumbuA2 = a2;
        this.sumbuB2 = b2;
    }

    public double getSumbuA2() { return sumbuA2; }
    public void setSumbuA2(double sumbuA2) { this.sumbuA2 = sumbuA2; }

    public double getSumbuB2() { return sumbuB2; }
    public void setSumbuB2(double sumbuB2) { this.sumbuB2 = sumbuB2; }

    private double hitungKelilingElips(double a, double b) {
        return Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
    }

    private double hitungSlantHeight() {
        double diffA = sumbuA - sumbuA2;
        double diffB = sumbuB - sumbuB2;
        return Math.sqrt(getTinggi() * getTinggi() + (diffA * diffA + diffB * diffB) / 2.0);
    }

    @Override
    public double hitungLuas() {
        double luasAlasBawah = Math.PI * sumbuA * sumbuB;
        double luasAlasAtas = Math.PI * sumbuA2 * sumbuB2;
        
        double kelilingBawah = hitungKelilingElips(sumbuA, sumbuB);
        double kelilingAtas = hitungKelilingElips(sumbuA2, sumbuB2);
        
        double s = hitungSlantHeight();
        double luasSelimut = 0.5 * (kelilingBawah + kelilingAtas) * s;

        return luasAlasBawah + luasAlasAtas + luasSelimut;
    }

    @Override
    public double hitungKeliling() {
        double kelilingBawah = hitungKelilingElips(sumbuA, sumbuB);
        double kelilingAtas = hitungKelilingElips(sumbuA2, sumbuB2);
        return kelilingBawah + kelilingAtas;
    }

    @Override
    public double hitungVolume() {
        double ab1 = sumbuA * sumbuB; 
        double ab2 = sumbuA2 * sumbuB2; 
        return (1.0 / 3.0) * Math.PI * getTinggi() * (ab1 + ab2 + Math.sqrt(ab1 * ab2));
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("=== Alas Bawah (Besar) ===");
        System.out.println("Sumbu (A1/B1)        : " + sumbuA + " / " + sumbuB);
        System.out.println("=== Alas Atas (Kecil) ===");
        System.out.println("Sumbu (A2/B2)        : " + sumbuA2 + " / " + sumbuB2);
        System.out.println("=== Dimensi Total ===");
        System.out.println("Tinggi (t)           : " + getTinggi());
        System.out.println("Luas Permukaan       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}
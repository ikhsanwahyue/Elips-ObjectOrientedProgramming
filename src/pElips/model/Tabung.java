package pElips.model;

public class Tabung extends Elips {
    private double tinggi;

    public Tabung(String nama, double a, double b, double tinggi) {
        super(nama, a, b);
        this.tinggi = tinggi;
    }

    @Override
    public double hitungLuas() {
        double luasAlas = super.hitungLuas();
        double kelilingAlas = super.hitungKeliling();
        return (2 * luasAlas) + (kelilingAlas * tinggi);
    }

    @Override
    public double hitungVolume() {
        return super.hitungLuas() * tinggi;
    }

    @Override
    public void cetakInfo() {
        super.cetakInfo();
        System.out.println("Tinggi Tabung        : " + tinggi);
        System.out.println("Volume Tabung        : " + String.format("%.2f", hitungVolume()));
    }
}
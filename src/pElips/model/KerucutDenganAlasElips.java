package pElips.model;

public class KerucutDenganAlasElips extends Elips {
    private double tinggi;

    public KerucutDenganAlasElips(String nama, double a, double b, double tinggi) {
        super(nama, a, b);
        this.tinggi = tinggi;
    }

    public double getTinggi() { return tinggi; }

    @Override
    public double hitungLuas() {
        double luasAlas = super.hitungLuas();
        double s1 = akarKuadrat(sumbuB * sumbuB + tinggi * tinggi);
        double s2 = akarKuadrat(sumbuA * sumbuA + tinggi * tinggi);
        double luasSelimut = PI * (sumbuA * s1 + sumbuB * s2) / 2.0;
        return luasAlas + luasSelimut;
    }

    @Override
    public double hitungVolume() {
        return (1.0 / 3.0) * super.hitungLuas() * tinggi;
    }

    @Override
    public void cetakInfo() {
        super.cetakInfo();
        System.out.println("Tinggi (t)           : " + tinggi);
        System.out.println("Volume Kerucut       : " + String.format("%.2f", hitungVolume()));
    }
}
package pElips.model;

public class KerucutDenganAlasElips extends Elips {

    private double tinggi;

    public KerucutDenganAlasElips(String nama) {
        super(nama);
        this.tinggi = 0;
    }

    public KerucutDenganAlasElips(String nama, double a, double b, double tinggi) {
        super(nama, a, b);
        this.tinggi = tinggi;
    }

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }

    private double hitungSlantHeight1() {
        return Math.sqrt(sumbuB * sumbuB + tinggi * tinggi);
    }

    private double hitungSlantHeight2() {
        return Math.sqrt(sumbuA * sumbuA + tinggi * tinggi);
    }

    @Override
    public double hitungLuas() {
        double luasAlas = Math.PI * sumbuA * sumbuB;
        double s1 = hitungSlantHeight1();
        double s2 = hitungSlantHeight2();
        double luasSelimut = Math.PI * (sumbuA * s1 + sumbuB * s2) / 2.0;
        return luasAlas + luasSelimut;
    }

    @Override
    public double hitungKeliling() {
        return super.hitungKeliling();
    }

    @Override
    public double hitungVolume() {
        return (1.0 / 3.0) * Math.PI * sumbuA * sumbuB * tinggi;
    }

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

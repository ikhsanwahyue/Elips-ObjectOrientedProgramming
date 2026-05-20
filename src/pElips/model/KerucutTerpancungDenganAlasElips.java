package pElips.model;

public class KerucutTerpancungDenganAlasElips extends KerucutDenganAlasElips {
    private double sumbuA2;
    private double sumbuB2;

    public KerucutTerpancungDenganAlasElips(String nama, double a1, double b1, double a2, double b2, double tinggi) {
        super(nama, a1, b1, tinggi);
        this.sumbuA2 = a2;
        this.sumbuB2 = b2;
    }

    @Override
    public double hitungLuas() {
        double luasAlasBawah = PI * sumbuA * sumbuB;
        double luasAlasAtas = PI * sumbuA2 * sumbuB2;
        double kelilingBawah = super.hitungKeliling();
        double kelilingAtas = PI * (3 * (sumbuA2 + sumbuB2) - akarKuadrat((3 * sumbuA2 + sumbuB2) * (sumbuA2 + 3 * sumbuB2)));
        double diffA = sumbuA - sumbuA2;
        double diffB = sumbuB - sumbuB2;
        double s = akarKuadrat(getTinggi() * getTinggi() + (diffA * diffA + diffB * diffB) / 2.0);
        double luasSelimut = 0.5 * (kelilingBawah + kelilingAtas) * s;
        return luasAlasBawah + luasAlasAtas + luasSelimut;
    }

    @Override
    public double hitungVolume() {
        double ab1 = sumbuA * sumbuB;
        double ab2 = sumbuA2 * sumbuB2;
        return (1.0 / 3.0) * PI * getTinggi() * (ab1 + ab2 + akarKuadrat(ab1 * ab2));
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Sumbu Bawah (a1/b1)  : " + sumbuA + "/" + sumbuB);
        System.out.println("Sumbu Atas (a2/b2)   : " + sumbuA2 + "/" + sumbuB2);
        System.out.println("Tinggi               : " + getTinggi());
        System.out.println("Luas Permukaan       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}
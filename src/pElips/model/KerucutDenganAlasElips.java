package pElips.model;

public class KerucutDenganAlasElips extends Elips {
    public double tinggi;
    public double garisPelukisA;
    public double garisPelukisB;
    public double luasAlas;
    public double luasSelimut;

    public KerucutDenganAlasElips() {
        this("Kerucut Dengan Alas Elips", 1, 1, 1);
    }

    public KerucutDenganAlasElips(String nama, double a, double b, double tinggi) {
        super(nama, a, b);
        this.tinggi = wajibPositif("Tinggi", tinggi);
    }

    @Override
    public double hitungLuas() {
        garisPelukisA = akarKuadrat(sumbuA * sumbuA + tinggi * tinggi);
        garisPelukisB = akarKuadrat(sumbuB * sumbuB + tinggi * tinggi);
        luasSelimut = PI * (sumbuA * garisPelukisB + sumbuB * garisPelukisA) / 2.0;
        luas = hitungLuas(sumbuA, sumbuB, tinggi);
        return luas;
    }

    public double hitungLuas(double a, double b, double tinggi) {
        sumbuA = wajibPositif("Sumbu A", a);
        sumbuB = wajibPositif("Sumbu B", b);
        this.tinggi = wajibPositif("Tinggi", tinggi);
        luas = super.hitungLuasElips(sumbuA, sumbuB);
        luasAlas = super.luas;
        garisPelukisA = akarKuadrat(sumbuA * sumbuA + this.tinggi * this.tinggi);
        garisPelukisB = akarKuadrat(sumbuB * sumbuB + this.tinggi * this.tinggi);
        luasSelimut = PI * (sumbuA * garisPelukisB + sumbuB * garisPelukisA) / 2.0;
        luas2 = super.luas + luasSelimut;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        keliling = hitungKeliling(sumbuA, sumbuB);
        return keliling;
    }

    public double hitungKeliling(double a, double b) {
        sumbuA = wajibPositif("Sumbu A", a);
        sumbuB = wajibPositif("Sumbu B", b);
        keliling2 = super.hitungKelilingElips(sumbuA, sumbuB);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        luasAlas = super.hitungLuasElips(sumbuA, sumbuB);
        volume = hitungVolume(sumbuA, sumbuB, tinggi);
        return volume;
    }

    public double hitungVolume(double a, double b, double tinggi) {
        sumbuA = wajibPositif("Sumbu A", a);
        sumbuB = wajibPositif("Sumbu B", b);
        this.tinggi = wajibPositif("Tinggi", tinggi);
        luasAlas = super.hitungLuasElips(sumbuA, sumbuB);
        volume2 = (1.0 / 3.0) * luasAlas * this.tinggi;
        return volume2;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + namaBenda + " ---");
        System.out.println("Sumbu Alas (a/b)     : " + sumbuA + "/" + sumbuB);
        System.out.println("Tinggi (t)           : " + tinggi);
        System.out.println("Garis Pelukis A      : " + formatAngka(garisPelukisA));
        System.out.println("Garis Pelukis B      : " + formatAngka(garisPelukisB));
        System.out.println("Luas Alas            : " + formatAngka(luasAlas));
        System.out.println("Luas Selimut         : " + formatAngka(luasSelimut));
        System.out.println("Luas Permukaan       : " + formatAngka(luas));
        System.out.println("Volume Kerucut       : " + formatAngka(volume));
    }
}

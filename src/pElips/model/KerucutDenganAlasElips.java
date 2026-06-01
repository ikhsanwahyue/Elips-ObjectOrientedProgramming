package pElips.model;

public class KerucutDenganAlasElips extends Elips {
    private double tinggi;
    private double garisPelukisA;
    private double garisPelukisB;
    private double luasAlas;
    private double luasSelimut;

    public KerucutDenganAlasElips(String nama, double a, double b, double tinggi) {
        super(nama, a, b);
        setTinggi(tinggi);
    }

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = wajibPositif("Tinggi", tinggi);
    }

    @Override
    public double hitungLuas() {
        luasAlas = hitungLuasElips(sumbuA, sumbuB);
        garisPelukisA = akarKuadrat(sumbuA * sumbuA + tinggi * tinggi);
        garisPelukisB = akarKuadrat(sumbuB * sumbuB + tinggi * tinggi);
        luasSelimut = PI * (sumbuA * garisPelukisB + sumbuB * garisPelukisA) / 2.0;
        luas = hitungLuas(sumbuA, sumbuB, tinggi);
        return luas;
    }

    public double hitungLuas(double a, double b, double tinggi) {
        double sumbuAHitung = wajibPositif("Sumbu A", a);
        double sumbuBHitung = wajibPositif("Sumbu B", b);
        double tinggiHitung = wajibPositif("Tinggi", tinggi);
        double luasAlasHitung = hitungLuasElips(sumbuAHitung, sumbuBHitung);
        double garisPelukisAHitung = akarKuadrat(sumbuAHitung * sumbuAHitung + tinggiHitung * tinggiHitung);
        double garisPelukisBHitung = akarKuadrat(sumbuBHitung * sumbuBHitung + tinggiHitung * tinggiHitung);
        double luasSelimutHitung = PI * (sumbuAHitung * garisPelukisBHitung
                + sumbuBHitung * garisPelukisAHitung) / 2.0;
        luas2 = luasAlasHitung + luasSelimutHitung;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        keliling = hitungKeliling(sumbuA, sumbuB);
        return keliling;
    }

    public double hitungKeliling(double a, double b) {
        keliling2 = super.hitungKeliling(a, b);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        luasAlas = hitungLuasElips(sumbuA, sumbuB);
        volume = hitungVolume(sumbuA, sumbuB, tinggi);
        return volume;
    }

    public double hitungVolume(double a, double b, double tinggi) {
        double sumbuAHitung = wajibPositif("Sumbu A", a);
        double sumbuBHitung = wajibPositif("Sumbu B", b);
        double tinggiHitung = wajibPositif("Tinggi", tinggi);
        volume2 = (1.0 / 3.0) * hitungLuasElips(sumbuAHitung, sumbuBHitung) * tinggiHitung;
        return volume2;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Sumbu Alas (a/b)     : " + sumbuA + "/" + sumbuB);
        System.out.println("Tinggi (t)           : " + tinggi);
        System.out.println("Garis Pelukis A      : " + formatAngka(garisPelukisA));
        System.out.println("Garis Pelukis B      : " + formatAngka(garisPelukisB));
        System.out.println("Luas Alas            : " + formatAngka(luasAlas));
        System.out.println("Luas Selimut         : " + formatAngka(luasSelimut));
        System.out.println("Luas Permukaan       : " + formatAngka(luas));
        System.out.println("Volume Kerucut       : " + formatAngka(volume));
    }

    public double getGarisPelukisA() {
        return garisPelukisA;
    }

    public double getGarisPelukisB() {
        return garisPelukisB;
    }

    public double getLuasAlas() {
        return luasAlas;
    }

    public double getLuasSelimut() {
        return luasSelimut;
    }
}

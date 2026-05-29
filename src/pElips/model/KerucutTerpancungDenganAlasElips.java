package pElips.model;

public class KerucutTerpancungDenganAlasElips extends KerucutDenganAlasElips {
    private double sumbuA2;
    private double sumbuB2;
    private double luasAlasBawah;
    private double luasAlasAtas;
    private double luasSelimut;
    private double garisPelukis;

    public KerucutTerpancungDenganAlasElips(String nama, double a1, double b1, double a2, double b2, double tinggi) {
        super(nama, a1, b1, tinggi);
        setSumbuA2(a2);
        setSumbuB2(b2);
    }

    @Override
    public double hitungLuas() {
        luasAlasBawah = hitungLuasElips(sumbuA, sumbuB);
        luasAlasAtas = hitungLuasElips(sumbuA2, sumbuB2);
        double kelilingBawah = super.hitungKeliling();
        double kelilingAtas = hitungKelilingElips(sumbuA2, sumbuB2);
        double diffA = sumbuA - sumbuA2;
        double diffB = sumbuB - sumbuB2;
        garisPelukis = akarKuadrat(getTinggi() * getTinggi() + (diffA * diffA + diffB * diffB) / 2.0);
        luasSelimut = 0.5 * (kelilingBawah + kelilingAtas) * garisPelukis;
        luas = luasAlasBawah + luasAlasAtas + luasSelimut;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        keliling = hitungKelilingElips(sumbuA, sumbuB) + hitungKelilingElips(sumbuA2, sumbuB2);
        return keliling;
    }

    @Override
    public double hitungVolume() {
        double ab1 = sumbuA * sumbuB;
        double ab2 = sumbuA2 * sumbuB2;
        volume = (1.0 / 3.0) * PI * getTinggi() * (ab1 + ab2 + akarKuadrat(ab1 * ab2));
        return volume;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Sumbu Bawah (a1/b1)  : " + sumbuA + "/" + sumbuB);
        System.out.println("Sumbu Atas (a2/b2)   : " + sumbuA2 + "/" + sumbuB2);
        System.out.println("Tinggi               : " + getTinggi());
        System.out.println("Garis Pelukis        : " + formatAngka(garisPelukis));
        System.out.println("Luas Alas Bawah      : " + formatAngka(luasAlasBawah));
        System.out.println("Luas Alas Atas       : " + formatAngka(luasAlasAtas));
        System.out.println("Luas Selimut         : " + formatAngka(luasSelimut));
        System.out.println("Luas Permukaan       : " + formatAngka(luas));
        System.out.println("Volume               : " + formatAngka(volume));
    }

    public double getSumbuA2() {
        return sumbuA2;
    }

    public void setSumbuA2(double sumbuA2) {
        this.sumbuA2 = wajibPositif("Sumbu A atas", sumbuA2);
        validasi(this.sumbuA2 < sumbuA, "Sumbu A atas harus lebih kecil dari sumbu A bawah.");
    }

    public double getSumbuB2() {
        return sumbuB2;
    }

    public void setSumbuB2(double sumbuB2) {
        this.sumbuB2 = wajibPositif("Sumbu B atas", sumbuB2);
        validasi(this.sumbuB2 < sumbuB, "Sumbu B atas harus lebih kecil dari sumbu B bawah.");
    }

    public double getLuasAlasBawah() {
        return luasAlasBawah;
    }

    public double getLuasAlasAtas() {
        return luasAlasAtas;
    }

    public double getLuasSelimutTerpancung() {
        return luasSelimut;
    }

    public double getGarisPelukis() {
        return garisPelukis;
    }
}

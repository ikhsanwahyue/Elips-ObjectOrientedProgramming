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
        luasAlasBawah = super.hitungLuas(sumbuA, sumbuB);
        luasAlasAtas = super.hitungLuas(sumbuA2, sumbuB2);
        double kelilingBawah = super.hitungKeliling(sumbuA, sumbuB);
        double kelilingAtas = super.hitungKeliling(sumbuA2, sumbuB2);
        double diffA = sumbuA - sumbuA2;
        double diffB = sumbuB - sumbuB2;
        garisPelukis = akarKuadrat(getTinggi() * getTinggi() + (diffA * diffA + diffB * diffB) / 2.0);
        luasSelimut = 0.5 * (kelilingBawah + kelilingAtas) * garisPelukis;
        luas = hitungLuas(sumbuA, sumbuB, sumbuA2, sumbuB2, getTinggi());
        return luas;
    }

    public double hitungLuas(double a1, double b1, double a2, double b2, double tinggi) {
        double bawahA = wajibPositif("Sumbu A bawah", a1);
        double bawahB = wajibPositif("Sumbu B bawah", b1);
        double atasA = wajibPositif("Sumbu A atas", a2);
        double atasB = wajibPositif("Sumbu B atas", b2);
        double tinggiHitung = wajibPositif("Tinggi", tinggi);
        validasi(atasA < bawahA, "Sumbu A atas harus lebih kecil dari sumbu A bawah.");
        validasi(atasB < bawahB, "Sumbu B atas harus lebih kecil dari sumbu B bawah.");

        double alasBawah = super.hitungLuas(bawahA, bawahB);
        double alasAtas = super.hitungLuas(atasA, atasB);
        double kelilingBawah = super.hitungKeliling(bawahA, bawahB);
        double kelilingAtas = super.hitungKeliling(atasA, atasB);
        double diffA = bawahA - atasA;
        double diffB = bawahB - atasB;
        double garisPelukisHitung = akarKuadrat(tinggiHitung * tinggiHitung + (diffA * diffA + diffB * diffB) / 2.0);
        luas2 = alasBawah + alasAtas + 0.5 * (kelilingBawah + kelilingAtas) * garisPelukisHitung;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        keliling = hitungKeliling(sumbuA, sumbuB, sumbuA2, sumbuB2);
        return keliling;
    }

    public double hitungKeliling(double a1, double b1, double a2, double b2) {
        double bawahA = wajibPositif("Sumbu A bawah", a1);
        double bawahB = wajibPositif("Sumbu B bawah", b1);
        double atasA = wajibPositif("Sumbu A atas", a2);
        double atasB = wajibPositif("Sumbu B atas", b2);
        validasi(atasA < bawahA, "Sumbu A atas harus lebih kecil dari sumbu A bawah.");
        validasi(atasB < bawahB, "Sumbu B atas harus lebih kecil dari sumbu B bawah.");
        keliling2 = super.hitungKeliling(bawahA, bawahB) + super.hitungKeliling(atasA, atasB);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        volume = hitungVolume(sumbuA, sumbuB, sumbuA2, sumbuB2, getTinggi());
        return volume;
    }

    public double hitungVolume(double a1, double b1, double a2, double b2, double tinggi) {
        double bawahA = wajibPositif("Sumbu A bawah", a1);
        double bawahB = wajibPositif("Sumbu B bawah", b1);
        double atasA = wajibPositif("Sumbu A atas", a2);
        double atasB = wajibPositif("Sumbu B atas", b2);
        double tinggiHitung = wajibPositif("Tinggi", tinggi);
        validasi(atasA < bawahA, "Sumbu A atas harus lebih kecil dari sumbu A bawah.");
        validasi(atasB < bawahB, "Sumbu B atas harus lebih kecil dari sumbu B bawah.");
        double alasBawah = super.hitungLuas(bawahA, bawahB);
        double alasAtas = super.hitungLuas(atasA, atasB);
        volume2 = (tinggiHitung / 3.0) * (alasBawah + alasAtas + akarKuadrat(alasBawah * alasAtas));
        return volume2;
    }

    @Override
    public void run() {
        super.run();
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

package pElips.model;

public class KerucutTerpancungDenganAlasElips extends Elips {
    public double sumbuA2;
    public double sumbuB2;
    public double tinggi;
    public double luasAlasBawah;
    public double luasAlasAtas;
    public double luasSelimut;
    public double garisPelukis;
    public double kelilingBawah;
    public double kelilingAtas;
    public double diffA;
    public double diffB;

    public KerucutTerpancungDenganAlasElips() {
        this("Kerucut Terpancung Dengan Alas Elips", 2, 1, 1, 0.5, 1);
    }

    public KerucutTerpancungDenganAlasElips(String nama, double a1, double b1, double a2, double b2, double tinggi) {
        super(nama, a1, b1);
        this.tinggi = wajibPositif("Tinggi", tinggi);
        this.sumbuA2 = wajibPositif("Sumbu A atas", a2);
        validasi(this.sumbuA2 < sumbuA, "Sumbu A atas harus lebih kecil dari sumbu A bawah.");
        this.sumbuB2 = wajibPositif("Sumbu B atas", b2);
        validasi(this.sumbuB2 < sumbuB, "Sumbu B atas harus lebih kecil dari sumbu B bawah.");
    }

    @Override
    public double hitungLuas() {
        luas = hitungLuas(sumbuA, sumbuB, sumbuA2, sumbuB2, tinggi);
        return luas;
    }

    public double hitungLuas(double a1, double b1, double a2, double b2, double tinggi) {
        sumbuA = wajibPositif("Sumbu A bawah", a1);
        sumbuB = wajibPositif("Sumbu B bawah", b1);
        sumbuA2 = wajibPositif("Sumbu A atas", a2);
        sumbuB2 = wajibPositif("Sumbu B atas", b2);
        this.tinggi = wajibPositif("Tinggi", tinggi);
        validasi(sumbuA2 < sumbuA, "Sumbu A atas harus lebih kecil dari sumbu A bawah.");
        validasi(sumbuB2 < sumbuB, "Sumbu B atas harus lebih kecil dari sumbu B bawah.");

        luas = super.hitungLuasElips(sumbuA, sumbuB);
        luasAlasBawah = super.luas;
        luasAlasAtas = super.hitungLuasElips(sumbuA2, sumbuB2);
        kelilingBawah = super.hitungKelilingElips(sumbuA, sumbuB);
        kelilingAtas = super.hitungKelilingElips(sumbuA2, sumbuB2);
        diffA = sumbuA - sumbuA2;
        diffB = sumbuB - sumbuB2;
        garisPelukis = akarKuadrat(this.tinggi * this.tinggi + (diffA * diffA + diffB * diffB) / 2.0);
        luasSelimut = 0.5 * (kelilingBawah + kelilingAtas) * garisPelukis;
        luas2 = super.luas + luasAlasAtas + luasSelimut;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        keliling = hitungKeliling(sumbuA, sumbuB, sumbuA2, sumbuB2);
        return keliling;
    }

    public double hitungKeliling(double a1, double b1, double a2, double b2) {
        sumbuA = wajibPositif("Sumbu A bawah", a1);
        sumbuB = wajibPositif("Sumbu B bawah", b1);
        sumbuA2 = wajibPositif("Sumbu A atas", a2);
        sumbuB2 = wajibPositif("Sumbu B atas", b2);
        validasi(sumbuA2 < sumbuA, "Sumbu A atas harus lebih kecil dari sumbu A bawah.");
        validasi(sumbuB2 < sumbuB, "Sumbu B atas harus lebih kecil dari sumbu B bawah.");
        kelilingBawah = super.hitungKelilingElips(sumbuA, sumbuB);
        kelilingAtas = super.hitungKelilingElips(sumbuA2, sumbuB2);
        keliling2 = kelilingBawah + kelilingAtas;
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        volume = hitungVolume(sumbuA, sumbuB, sumbuA2, sumbuB2, tinggi);
        return volume;
    }

    public double hitungVolume(double a1, double b1, double a2, double b2, double tinggi) {
        sumbuA = wajibPositif("Sumbu A bawah", a1);
        sumbuB = wajibPositif("Sumbu B bawah", b1);
        sumbuA2 = wajibPositif("Sumbu A atas", a2);
        sumbuB2 = wajibPositif("Sumbu B atas", b2);
        this.tinggi = wajibPositif("Tinggi", tinggi);
        validasi(sumbuA2 < sumbuA, "Sumbu A atas harus lebih kecil dari sumbu A bawah.");
        validasi(sumbuB2 < sumbuB, "Sumbu B atas harus lebih kecil dari sumbu B bawah.");
        luasAlasBawah = super.hitungLuasElips(sumbuA, sumbuB);
        luasAlasAtas = super.hitungLuasElips(sumbuA2, sumbuB2);
        volume2 = (this.tinggi / 3.0)
                * (luasAlasBawah + luasAlasAtas + akarKuadrat(luasAlasBawah * luasAlasAtas));
        return volume2;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + namaBenda + " ---");
        System.out.println("Sumbu Bawah (a1/b1)  : " + sumbuA + "/" + sumbuB);
        System.out.println("Sumbu Atas (a2/b2)   : " + sumbuA2 + "/" + sumbuB2);
        System.out.println("Tinggi               : " + tinggi);
        System.out.println("Garis Pelukis        : " + formatAngka(garisPelukis));
        System.out.println("Luas Alas Bawah      : " + formatAngka(luasAlasBawah));
        System.out.println("Luas Alas Atas       : " + formatAngka(luasAlasAtas));
        System.out.println("Luas Selimut         : " + formatAngka(luasSelimut));
        System.out.println("Luas Permukaan       : " + formatAngka(luas));
        System.out.println("Volume               : " + formatAngka(volume));
    }
}

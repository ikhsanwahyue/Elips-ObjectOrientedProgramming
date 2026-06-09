package pElips.model;

public class Tabung extends Bola {
    private double tinggi;
    private double luasAlas;
    private double luasSelimut;

    public Tabung() {
        this("Tabung", 1, 1);
    }

    public Tabung(String nama, double r, double tinggi) {
        super(nama, r);
        setTinggi(tinggi);
    }

    @Override
    public double hitungLuas() {
        luasAlas = super.hitungLuasLingkaran(jariJari);
        luasSelimut = super.hitungKelilingLingkaran(jariJari) * tinggi;
        hitungLuas(jariJari, tinggi);
        luas = super.luas2;
        return luas;
    }

    public double hitungLuas(double r, double tinggi) {
        jariJari = wajibPositif("Jari-jari alas", r);
        diameter = 2 * jariJari;
        this.tinggi = wajibPositif("Tinggi", tinggi);
        luasAlas = super.hitungLuasLingkaran(jariJari);
        luasSelimut = super.hitungKelilingLingkaran(jariJari) * this.tinggi;
        luas2 = (2 * luasAlas) + luasSelimut;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        hitungKeliling(jariJari);
        kelilingLingkaranBesar = super.keliling2;
        keliling = super.keliling2;
        return keliling;
    }

    public double hitungKeliling(double r) {
        jariJari = wajibPositif("Jari-jari alas", r);
        diameter = 2 * jariJari;
        keliling2 = super.hitungKelilingLingkaran(jariJari);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        luasAlas = super.hitungLuasLingkaran(jariJari);
        hitungVolume(jariJari, tinggi);
        volume = super.volume2;
        return volume;
    }

    public double hitungVolume(double r, double tinggi) {
        jariJari = wajibPositif("Jari-jari alas", r);
        diameter = 2 * jariJari;
        this.tinggi = wajibPositif("Tinggi", tinggi);
        luasAlas = super.hitungLuasLingkaran(jariJari);
        volume2 = luasAlas * this.tinggi;
        return volume2;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + namaBenda + " ---");
        System.out.println("Jari-jari Alas (r)   : " + jariJari);
        System.out.println("Tinggi Tabung        : " + tinggi);
        System.out.println("Luas Alas            : " + formatAngka(luasAlas));
        System.out.println("Luas Selimut         : " + formatAngka(luasSelimut));
        System.out.println("Luas Permukaan       : " + formatAngka(luas));
        System.out.println("Volume Tabung        : " + formatAngka(volume));
    }

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = wajibPositif("Tinggi", tinggi);
    }

    public double getLuasAlas() {
        return luasAlas;
    }

    public double getLuasSelimut() {
        return luasSelimut;
    }
}

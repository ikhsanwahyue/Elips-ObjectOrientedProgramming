package pElips.model;

public class Tabung extends Bola {
    private double tinggi;
    private double luasAlas;
    private double luasSelimut;

    public Tabung(String nama, double r, double tinggi) {
        super(nama, r);
        setTinggi(tinggi);
    }

    @Override
    public double hitungLuas() {
        luasAlas = PI * pangkat(jariJari, 2);
        luasSelimut = 2 * PI * jariJari * tinggi;
        luas = (2 * luasAlas) + luasSelimut;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        kelilingLingkaranBesar = 2 * PI * jariJari;
        keliling = kelilingLingkaranBesar;
        return keliling;
    }

    @Override
    public double hitungVolume() {
        luasAlas = PI * pangkat(jariJari, 2);
        volume = luasAlas * tinggi;
        return volume;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
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

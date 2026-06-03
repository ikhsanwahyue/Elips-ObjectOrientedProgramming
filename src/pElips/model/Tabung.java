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
        luas = hitungLuas(jariJari, tinggi);
        return luas;
    }

    public double hitungLuas(double r, double tinggi) {
        double radius = wajibPositif("Jari-jari alas", r);
        double tinggiHitung = wajibPositif("Tinggi", tinggi);
        double luasAlasHitung = PI * pangkat(radius, 2);
        double luasSelimutHitung = 2 * PI * radius * tinggiHitung;
        luas2 = (2 * luasAlasHitung) + luasSelimutHitung;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        kelilingLingkaranBesar = hitungKeliling(jariJari);
        keliling = kelilingLingkaranBesar;
        return keliling;
    }

    public double hitungKeliling(double r) {
        keliling2 = super.hitungKeliling(r);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        luasAlas = PI * pangkat(jariJari, 2);
        volume = hitungVolume(jariJari, tinggi);
        return volume;
    }

    public double hitungVolume(double r, double tinggi) {
        double radius = wajibPositif("Jari-jari alas", r);
        double tinggiHitung = wajibPositif("Tinggi", tinggi);
        volume2 = PI * pangkat(radius, 2) * tinggiHitung;
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

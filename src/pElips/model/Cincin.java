package pElips.model;

public class Cincin extends Bola {
    public double jariJariMayor;
    public double jariJariMinor;

    public Cincin() {
        this("Cincin", 2, 1);
    }

    public Cincin(String nama, double R, double r) {
        super(nama, r);
        this.jariJariMinor = wajibPositif("Jari-jari minor", r);
        this.jariJariMayor = wajibPositif("Jari-jari mayor", R);
        validasi(this.jariJariMayor > this.jariJariMinor,
                "Jari-jari mayor harus lebih besar dari jari-jari minor.");
    }

    @Override
    public double hitungLuas() {
        hitungLuas(jariJariMayor, jariJariMinor);
        luas = super.luas2;
        return luas;
    }

    public double hitungLuas(double R, double r) {
        jariJariMayor = wajibPositif("Jari-jari mayor", R);
        jariJariMinor = wajibPositif("Jari-jari minor", r);
        jariJari = jariJariMinor;
        diameter = 2 * jariJari;
        validasi(jariJariMayor > jariJariMinor, "Jari-jari mayor harus lebih besar dari jari-jari minor.");
        luas2 = 4 * pangkat(PI, 2) * jariJariMayor * jariJariMinor;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        hitungKeliling(jariJariMayor);
        keliling = super.keliling2;
        return keliling;
    }

    public double hitungKeliling(double R) {
        jariJariMayor = wajibPositif("Jari-jari mayor", R);
        validasi(jariJariMayor > jariJariMinor, "Jari-jari mayor harus lebih besar dari jari-jari minor.");
        keliling2 = super.hitungKelilingLingkaran(jariJariMayor);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        hitungVolume(jariJariMayor, jariJariMinor);
        volume = super.volume2;
        return volume;
    }

    public double hitungVolume(double R, double r) {
        jariJariMayor = wajibPositif("Jari-jari mayor", R);
        jariJariMinor = wajibPositif("Jari-jari minor", r);
        jariJari = jariJariMinor;
        diameter = 2 * jariJari;
        validasi(jariJariMayor > jariJariMinor, "Jari-jari mayor harus lebih besar dari jari-jari minor.");
        volume2 = 2 * pangkat(PI, 2) * jariJariMayor * pangkat(jariJariMinor, 2);
        return volume2;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + namaBenda + " ---");
        System.out.println("Jari-jari Mayor (R)  : " + jariJariMayor);
        System.out.println("Jari-jari Minor (r)  : " + jariJariMinor);
        System.out.println("Keliling Mayor       : " + formatAngka(keliling));
        System.out.println("Luas Cincin          : " + formatAngka(luas));
        System.out.println("Volume Cincin        : " + formatAngka(volume));
    }
}

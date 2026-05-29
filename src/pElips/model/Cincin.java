package pElips.model;

public class Cincin extends Bola {
    private double jariJariMayor;
    private double jariJariMinor;

    public Cincin(String nama, double R, double r) {
        super(nama, r);
        this.jariJariMinor = r;
        setJariJariMayor(R);
    }

    @Override
    public double hitungLuas() {
        luas = 4 * pangkat(PI, 2) * jariJariMayor * jariJariMinor;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        keliling = 2 * PI * jariJariMayor;
        return keliling;
    }

    @Override
    public double hitungVolume() {
        volume = 2 * pangkat(PI, 2) * jariJariMayor * pangkat(jariJariMinor, 2);
        return volume;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Mayor (R)  : " + jariJariMayor);
        System.out.println("Jari-jari Minor (r)  : " + jariJariMinor);
        System.out.println("Keliling Mayor       : " + formatAngka(keliling));
        System.out.println("Luas Cincin          : " + formatAngka(luas));
        System.out.println("Volume Cincin        : " + formatAngka(volume));
    }

    public double getJariJariMayor() {
        return jariJariMayor;
    }

    public void setJariJariMayor(double R) {
        this.jariJariMayor = wajibPositif("Jari-jari mayor", R);
        validasi(this.jariJariMayor > jariJariMinor || jariJariMinor == 0,
                "Jari-jari mayor harus lebih besar dari jari-jari minor.");
    }

    public double getJariJariMinor() {
        return jariJariMinor;
    }

    public void setJariJariMinor(double r) {
        super.setJariJari(r);
        this.jariJariMinor = r;
        validasi(jariJariMayor == 0 || jariJariMayor > this.jariJariMinor,
                "Jari-jari mayor harus lebih besar dari jari-jari minor.");
    }
}

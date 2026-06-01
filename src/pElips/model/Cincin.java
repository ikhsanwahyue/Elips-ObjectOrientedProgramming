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
        luas = hitungLuas(jariJariMayor, jariJariMinor);
        return luas;
    }

    public double hitungLuas(double R, double r) {
        double mayor = wajibPositif("Jari-jari mayor", R);
        double minor = wajibPositif("Jari-jari minor", r);
        validasi(mayor > minor, "Jari-jari mayor harus lebih besar dari jari-jari minor.");
        luas2 = 4 * pangkat(PI, 2) * mayor * minor;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        keliling = hitungKeliling(jariJariMayor);
        return keliling;
    }

    public double hitungKeliling(double R) {
        double mayor = wajibPositif("Jari-jari mayor", R);
        keliling2 = 2 * PI * mayor;
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        volume = hitungVolume(jariJariMayor, jariJariMinor);
        return volume;
    }

    public double hitungVolume(double R, double r) {
        double mayor = wajibPositif("Jari-jari mayor", R);
        double minor = wajibPositif("Jari-jari minor", r);
        validasi(mayor > minor, "Jari-jari mayor harus lebih besar dari jari-jari minor.");
        volume2 = 2 * pangkat(PI, 2) * mayor * pangkat(minor, 2);
        return volume2;
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

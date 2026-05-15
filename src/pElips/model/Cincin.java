package pElips.model;

public class Cincin extends Bola {

    private double jariJariMayor;

    public Cincin(String nama) {
        super(nama, 0);
        this.jariJariMayor = 0;
    }

    public Cincin(String nama, double R, double r) {
        super(nama, r);
        this.jariJariMayor = R;
    }

    public double getJariJariMayor() {
        return jariJariMayor;
    }

    public void setJariJariMayor(double R) {
        this.jariJariMayor = R;
    }

    @Override
    public double hitungLuas() {
        return 4 * Math.PI * Math.PI * jariJariMayor * jariJari;
    }

    @Override
    public double hitungKeliling() {
        return 2 * Math.PI * (jariJariMayor + jariJari);
    }

    @Override
    public double hitungVolume() {
        return 2 * Math.PI * Math.PI * jariJariMayor * Math.pow(jariJari, 2);
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Mayor (R)  : " + jariJariMayor);
        System.out.println("Jari-jari Minor (r)  : " + jariJari);
        System.out.println("Luas Permukaan       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling Luar        : " + String.format("%.2f", hitungKeliling()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}

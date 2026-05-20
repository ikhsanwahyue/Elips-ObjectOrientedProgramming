package pElips.model;

public class Cincin extends Bola {
    private double jariJariMayor;

    public Cincin(String nama, double R, double r) {
        super(nama, r);
        this.jariJariMayor = R;
    }

    @Override
    public double hitungLuas() {
        return 4 * pangkat(PI, 2) * jariJariMayor * jariJari;
    }

    @Override
    public double hitungVolume() {
        return 2 * pangkat(PI, 2) * jariJariMayor * pangkat(jariJari, 2);
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Mayor (R)  : " + jariJariMayor);
        System.out.println("Jari-jari Minor (r)  : " + jariJari);
        System.out.println("Luas Cincin          : " + String.format("%.2f", hitungLuas()));
        System.out.println("Volume Cincin        : " + String.format("%.2f", hitungVolume()));
    }
}
package pElips.model;

public class Bola extends BendaGeometri implements KalkulasiGeometri {
    protected double jariJari;

    public Bola() {
        super("Bola");
    }

    public Bola(String nama, double r) {
        super(nama);
        this.jariJari = r;
    }

    public double getJariJari() {
        return jariJari;
    }

    public void setJariJari(double r) {
        this.jariJari = r;
    }

    @Override
    public double hitungLuas() {
        return 4 * Math.PI * Math.pow(jariJari, 2);
    }

    @Override
    public double hitungKeliling() {
        return 2 * Math.PI * jariJari;
    }

    @Override
    public double hitungVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(jariJari, 3);
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari (r)        : " + jariJari);
        System.out.println("Luas Permukaan       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling             : " + String.format("%.2f", hitungKeliling()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}
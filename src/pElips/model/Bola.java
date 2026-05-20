package pElips.model;

public class Bola extends BendaGeometri {
    protected double jariJari;

    public Bola(String nama, double r) {
        super(nama);
        this.jariJari = r;
    }

    @Override
    public double hitungLuas() {
        return 4 * PI * pangkat(jariJari, 2);
    }

    @Override
    public double hitungKeliling() {
        return 2 * PI * jariJari;
    }

    @Override
    public double hitungVolume() {
        return (4.0 / 3.0) * PI * pangkat(jariJari, 3);
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari (r)        : " + jariJari);
        System.out.println("Luas Permukaan       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }

    public double getJariJari() { return jariJari; }
}
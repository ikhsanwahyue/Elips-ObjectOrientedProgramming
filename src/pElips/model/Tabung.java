package pElips.model;

public class Tabung extends BendaGeometri implements KalkulasiGeometri {
    private double jariJari;
    private double tinggi;

    public Tabung() {
        super("Tabung");
        setNamaBenda("Tabung");
    }

    public void setJariJari(double r) {
        this.jariJari = r;
    }

    public void setTinggi(double t) {
        this.tinggi = t;
    }

    @Override
    public double hitungLuas() {
        return 2 * Math.PI * jariJari * (jariJari + tinggi);
    }

    @Override
    public double hitungKeliling() {
        return 2 * Math.PI * jariJari;
    }

    @Override
    public double hitungVolume() {
        return Math.PI * Math.pow(jariJari, 2) * tinggi;
    }

    @Override
    public void cetakInfo() {
        System.out.println("Nama Benda : " + getNamaBenda());
        System.out.println("Jari-jari  : " + jariJari);
        System.out.println("Tinggi     : " + tinggi);
        System.out.println("Volume     : " + hitungVolume());
        System.out.println("Luas Perm. : " + hitungLuas());
    }
}
package pElips.model;

public class Bola extends BendaGeometri {
    protected double jariJari;
    protected double diameter;
    protected double kelilingLingkaranBesar;

    public Bola(String nama) {
        this(nama, 1.0);
    }

    public Bola(String nama, double r) {
        super(nama);
        setJariJari(r);
    }

    @Override
    public double hitungLuas() {
        luas = 4 * PI * pangkat(jariJari, 2);
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
        volume = (4.0 / 3.0) * PI * pangkat(jariJari, 3);
        return volume;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari (r)        : " + jariJari);
        System.out.println("Diameter             : " + diameter);
        System.out.println("Keliling Lingkaran   : " + formatAngka(kelilingLingkaranBesar));
        System.out.println("Luas Permukaan       : " + formatAngka(luas));
        System.out.println("Volume               : " + formatAngka(volume));
    }

    public double getJariJari() {
        return jariJari;
    }

    public void setJariJari(double r) {
        this.jariJari = wajibPositif("Jari-jari", r);
        this.diameter = 2 * this.jariJari;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getKelilingLingkaranBesar() {
        return kelilingLingkaranBesar;
    }
}

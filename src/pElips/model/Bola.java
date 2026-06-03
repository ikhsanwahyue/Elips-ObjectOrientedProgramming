package pElips.model;

public class Bola extends BendaGeometri {
    public double jariJari;
    public double diameter;
    public double kelilingLingkaranBesar;

    public Bola(String nama, double r) {
        super(nama);
        setJariJari(r);
    }

    @Override
    public double hitungLuas() {
        luas = hitungLuas(jariJari);
        return luas;
    }

    public double hitungLuas(double r) {
        double radius = wajibPositif("Jari-jari", r);
        luas2 = 4 * PI * radius * radius;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        kelilingLingkaranBesar = hitungKeliling(jariJari);
        keliling = kelilingLingkaranBesar;
        return keliling;
    }

    public double hitungKeliling(double r) {
        double radius = wajibPositif("Jari-jari", r);
        keliling2 = 2 * PI * radius;
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        volume = hitungVolume(jariJari);
        return volume;
    }

    public double hitungVolume(double r) {
        double radius = wajibPositif("Jari-jari", r);
        volume2 = (4.0 / 3.0) * PI * pangkat(radius, 3);
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

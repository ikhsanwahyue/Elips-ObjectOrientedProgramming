package pElips.model;

public class Bola extends BendaGeometri {
    public double jariJari;
    public double diameter;
    public double kelilingLingkaranBesar;

    public Bola() {
        this("Bola", 1);
    }

    public Bola(String nama, double r) {
        super(nama);
        this.jariJari = wajibPositif("Jari-jari", r);
        this.diameter = 2 * this.jariJari;
    }

    @Override
    public double hitungLuas() {
        hitungLuas(jariJari);
        luas = super.luas2;
        return luas;
    }

    public double hitungLuas(double r) {
        jariJari = wajibPositif("Jari-jari", r);
        diameter = 2 * jariJari;
        luas2 = hitungLuasBola(jariJari);
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        kelilingLingkaranBesar = hitungKeliling(jariJari);
        keliling = super.keliling2;
        return keliling;
    }

    public double hitungKeliling(double r) {
        jariJari = wajibPositif("Jari-jari", r);
        diameter = 2 * jariJari;
        keliling2 = hitungKelilingLingkaran(jariJari);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        hitungVolume(jariJari);
        volume = super.volume2;
        return volume;
    }

    public double hitungVolume(double r) {
        jariJari = wajibPositif("Jari-jari", r);
        diameter = 2 * jariJari;
        volume2 = hitungVolumeBola(jariJari);
        return volume2;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + namaBenda + " ---");
        System.out.println("Jari-jari (r)        : " + jariJari);
        System.out.println("Diameter             : " + diameter);
        System.out.println("Keliling Lingkaran   : " + formatAngka(kelilingLingkaranBesar));
        System.out.println("Luas Permukaan       : " + formatAngka(luas));
        System.out.println("Volume               : " + formatAngka(volume));
    }

    public double hitungLuasBola(double r) {
        return 4 * PI * r * r;
    }

    public double hitungVolumeBola(double r) {
        return (4.0 / 3.0) * PI * pangkat(r, 3);
    }

    public double hitungLuasLingkaran(double r) {
        return PI * pangkat(r, 2);
    }

    public double hitungKelilingLingkaran(double r) {
        return 2 * PI * r;
    }
}

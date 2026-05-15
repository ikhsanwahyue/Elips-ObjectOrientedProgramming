package pElips.model;

public class Juring extends Bola {

    private double tinggiTopi;

    public Juring(String nama) {
        super(nama, 0);
        this.tinggiTopi = 0;
    }

    public Juring(String nama, double r, double h) {
        super(nama, r);
        this.tinggiTopi = h;
    }

    public double getTinggiTopi() {
        return tinggiTopi;
    }

    public void setTinggiTopi(double h) {
        this.tinggiTopi = h;
    }

    private double hitungJariJariAlas() {
        return Math.sqrt(tinggiTopi * (2 * jariJari - tinggiTopi));
    }

    @Override
    public double hitungLuas() {
        double a = hitungJariJariAlas();
        return Math.PI * jariJari * (2 * tinggiTopi + a);
    }

    @Override
    public double hitungKeliling() {
        double a = hitungJariJariAlas();
        return 2 * Math.PI * a;
    }

    @Override
    public double hitungVolume() {
        return (2.0 / 3.0) * Math.PI * Math.pow(jariJari, 2) * tinggiTopi;
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Bola (r)   : " + jariJari);
        System.out.println("Tinggi Topi (h)      : " + tinggiTopi);
        System.out.println("Jari-jari Alas (a)   : " + String.format("%.2f", hitungJariJariAlas()));
        System.out.println("Luas Permukaan       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling Alas        : " + String.format("%.2f", hitungKeliling()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}

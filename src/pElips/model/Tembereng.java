package pElips.model;

public class Tembereng extends Bola {

    private double tinggiTopi;

    public Tembereng(String nama) {
        super(nama, 0);
        this.tinggiTopi = 0;
    }

    public Tembereng(String nama, double r, double h) {
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
        double luasSelimut = 2 * Math.PI * jariJari * tinggiTopi;
        double luasAlas = Math.PI * a * a;
        return luasSelimut + luasAlas;
    }

    @Override
    public double hitungKeliling() {
        double a = hitungJariJariAlas();
        return 2 * Math.PI * a;
    }

    @Override
    public double hitungVolume() {
        return (Math.PI * Math.pow(tinggiTopi, 2) / 3.0) * (3 * jariJari - tinggiTopi);
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Bola (r)   : " + jariJari);
        System.out.println("Tinggi Topi (h)      : " + tinggiTopi);
        System.out.println("Jari-jari Alas (a)   : " + String.format("%.2f", hitungJariJariAlas()));
        System.out.println("Luas Selimut         : " + String.format("%.2f", 2 * Math.PI * jariJari * tinggiTopi));
        System.out.println("Luas Alas            : " + String.format("%.2f", Math.PI * Math.pow(hitungJariJariAlas(), 2)));
        System.out.println("Luas Permukaan Total : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling Alas        : " + String.format("%.2f", hitungKeliling()));
        System.out.println("Volume               : " + String.format("%.2f", hitungVolume()));
    }
}

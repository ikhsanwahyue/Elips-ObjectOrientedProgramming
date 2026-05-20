package pElips.model;

public class Tembereng extends Bola {
    private double tinggiTopi;

    public Tembereng(String nama, double r, double h) {
        super(nama, r);
        this.tinggiTopi = h;
    }

    @Override
    public double hitungLuas() {
        double a = akarKuadrat(tinggiTopi * (2 * jariJari - tinggiTopi));
        double luasSelimut = 2 * PI * jariJari * tinggiTopi;
        double luasAlas = PI * a * a;
        return luasSelimut + luasAlas;
    }

    @Override
    public double hitungVolume() {
        return (PI * pangkat(tinggiTopi, 2) / 3.0) * (3 * jariJari - tinggiTopi);
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Bola (r)   : " + jariJari);
        System.out.println("Tinggi Topi (h)      : " + tinggiTopi);
        System.out.println("Luas Tembereng       : " + String.format("%.2f", hitungLuas()));
        System.out.println("Volume Tembereng     : " + String.format("%.2f", hitungVolume()));
    }
}
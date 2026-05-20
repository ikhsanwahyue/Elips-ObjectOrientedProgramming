package pElips.model;

public class Juring extends Bola {
    private double tinggiTopi;

    public Juring(String nama, double r, double h) {
        super(nama, r);
        this.tinggiTopi = h;
    }

    @Override
    public double hitungLuas() {
        double a = akarKuadrat(tinggiTopi * (2 * jariJari - tinggiTopi));
        return PI * jariJari * (2 * tinggiTopi + a);
    }

    @Override
    public double hitungVolume() {
        return (2.0 / 3.0) * PI * pangkat(jariJari, 2) * tinggiTopi;
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Bola (r)   : " + jariJari);
        System.out.println("Tinggi Topi (h)      : " + tinggiTopi);
        System.out.println("Luas Juring          : " + String.format("%.2f", hitungLuas()));
        System.out.println("Volume Juring        : " + String.format("%.2f", hitungVolume()));
    }
}
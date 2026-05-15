package pElips.model;

public class Elips extends BendaGeometri implements KalkulasiGeometri {
    
    protected double sumbuA;
    protected double sumbuB;

    public Elips(String nama) {
        super(nama);
        this.sumbuA = 0;
        this.sumbuB = 0;
    }

    public Elips(String nama, double a, double b) {
        super(nama);
        this.sumbuA = a;
        this.sumbuB = b;
    }

    public double getSumbuA() { return sumbuA; }
    public void setSumbuA(double a) { this.sumbuA = a; }
    
    public double getSumbuB() { return sumbuB; }
    public void setSumbuB(double b) { this.sumbuB = b; }

    @Override
    public double hitungLuas() {
        return Math.PI * sumbuA * sumbuB;
    }

    @Override
    public double hitungKeliling() {
        return Math.PI * (3 * (sumbuA + sumbuB) - Math.sqrt((3 * sumbuA + sumbuB) * (sumbuA + 3 * sumbuB)));
    }

    @Override
    public double hitungVolume() {
        return 0.0;
    }

    @Override
    public void cetakInfo() {
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Sumbu Semi-Mayor (A) : " + sumbuA);
        System.out.println("Sumbu Semi-Minor (B) : " + sumbuB);
        System.out.println("Luas                 : " + String.format("%.2f", hitungLuas()));
        System.out.println("Keliling             : " + String.format("%.2f", hitungKeliling()));
    }
}
package pElips.model;

public class Elips extends BendaGeometri {
    protected double sumbuA;
    protected double sumbuB;

    public Elips(String nama) {
        this(nama, 1.0, 1.0);
    }

    public Elips(String nama, double a, double b) {
        super(nama);
        setSumbuA(a);
        setSumbuB(b);
    }

    @Override
    public double hitungLuas() {
        luas = hitungLuasElips(sumbuA, sumbuB);
        return luas;
    }

    @Override
    public double hitungKeliling() {
        keliling = hitungKelilingElips(sumbuA, sumbuB);
        return keliling;
    }

    @Override
    public double hitungVolume() {
        volume = 0.0;
        return volume;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Sumbu Semi-Mayor (A) : " + sumbuA);
        System.out.println("Sumbu Semi-Minor (B) : " + sumbuB);
        System.out.println("Luas                 : " + formatAngka(luas));
        System.out.println("Keliling             : " + formatAngka(keliling));
    }

    protected double hitungLuasElips(double a, double b) {
        return PI * a * b;
    }

    protected double hitungKelilingElips(double a, double b) {
        return PI * (3 * (a + b) - akarKuadrat((3 * a + b) * (a + 3 * b)));
    }

    public double getSumbuA() {
        return sumbuA;
    }

    public void setSumbuA(double a) {
        this.sumbuA = wajibPositif("Sumbu A", a);
    }

    public double getSumbuB() {
        return sumbuB;
    }

    public void setSumbuB(double b) {
        this.sumbuB = wajibPositif("Sumbu B", b);
    }
}

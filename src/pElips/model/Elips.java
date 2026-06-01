package pElips.model;

public class Elips extends BendaGeometri {
    protected double sumbuA;
    protected double sumbuB;

    public Elips(String nama, double a, double b) {
        super(nama);
        setSumbuA(a);
        setSumbuB(b);
    }

    @Override
    public double hitungLuas() {
        luas = hitungLuas(sumbuA, sumbuB);
        return luas;
    }

    public double hitungLuas(double a, double b) {
        double sumbuAHitung = wajibPositif("Sumbu A", a);
        double sumbuBHitung = wajibPositif("Sumbu B", b);
        return hitungLuasElips(sumbuAHitung, sumbuBHitung);
    }

    @Override
    public double hitungKeliling() {
        keliling = hitungKeliling(sumbuA, sumbuB);
        return keliling;
    }

    public double hitungKeliling(double a, double b) {
        double sumbuAHitung = wajibPositif("Sumbu A", a);
        double sumbuBHitung = wajibPositif("Sumbu B", b);
        keliling2 = hitungKelilingElips(sumbuAHitung, sumbuBHitung);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        volume = hitungVolume(sumbuA, sumbuB);
        return volume;
    }

    public double hitungVolume(double a, double b) {
        wajibPositif("Sumbu A", a);
        wajibPositif("Sumbu B", b);
        volume2 = 0;
        return volume2;
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

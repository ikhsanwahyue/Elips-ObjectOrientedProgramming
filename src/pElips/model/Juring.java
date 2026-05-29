package pElips.model;

public class Juring extends Bola {
    private double tinggiTopi;
    private double jariJariAlas;

    public Juring(String nama, double r, double h) {
        super(nama, r);
        setTinggiTopi(h);
    }

    @Override
    public double hitungLuas() {
        jariJariAlas = hitungJariJariAlas();
        luas = PI * jariJari * (2 * tinggiTopi + jariJariAlas);
        return luas;
    }

    @Override
    public double hitungKeliling() {
        jariJariAlas = hitungJariJariAlas();
        keliling = 2 * PI * jariJariAlas;
        return keliling;
    }

    @Override
    public double hitungVolume() {
        volume = (2.0 / 3.0) * PI * pangkat(jariJari, 2) * tinggiTopi;
        return volume;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + getNamaBenda() + " ---");
        System.out.println("Jari-jari Bola (r)   : " + jariJari);
        System.out.println("Tinggi Topi (h)      : " + tinggiTopi);
        System.out.println("Jari-jari Alas       : " + formatAngka(jariJariAlas));
        System.out.println("Keliling Alas        : " + formatAngka(keliling));
        System.out.println("Luas Juring          : " + formatAngka(luas));
        System.out.println("Volume Juring        : " + formatAngka(volume));
    }

    private double hitungJariJariAlas() {
        return akarKuadrat(tinggiTopi * (2 * jariJari - tinggiTopi));
    }

    public double getTinggiTopi() {
        return tinggiTopi;
    }

    public void setTinggiTopi(double h) {
        this.tinggiTopi = wajibPositif("Tinggi topi", h);
        validasi(this.tinggiTopi <= 2 * jariJari, "Tinggi topi maksimal 2 x jari-jari bola.");
    }

    public double getJariJariAlas() {
        return jariJariAlas;
    }
}

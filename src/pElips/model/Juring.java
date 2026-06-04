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
        luas = hitungLuas(jariJari, tinggiTopi);
        return luas;
    }

    public double hitungLuas(double r, double h) {
        double radius = wajibPositif("Jari-jari bola", r);
        double tinggi = wajibPositif("Tinggi topi", h);
        validasi(tinggi <= 2 * radius, "Tinggi topi maksimal 2 x jari-jari bola.");
        double alas = hitungJariJariAlas(radius, tinggi);
        luas2 = PI * pangkat(alas, 2) + PI * radius * tinggi;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        jariJariAlas = hitungJariJariAlas();
        keliling = hitungKeliling(jariJari, tinggiTopi);
        return keliling;
    }

    public double hitungKeliling(double r, double h) {
        double radius = wajibPositif("Jari-jari bola", r);
        double tinggi = wajibPositif("Tinggi topi", h);
        validasi(tinggi <= 2 * radius, "Tinggi topi maksimal 2 x jari-jari bola.");
        double alas = hitungJariJariAlas(radius, tinggi);
        keliling2 = alas == 0 ? 0 : super.hitungKeliling(alas);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        volume = hitungVolume(jariJari, tinggiTopi);
        return volume;
    }

    public double hitungVolume(double r, double h) {
        double radius = wajibPositif("Jari-jari bola", r);
        double tinggi = wajibPositif("Tinggi topi", h);
        validasi(tinggi <= 2 * radius, "Tinggi topi maksimal 2 x jari-jari bola.");
        volume2 = (2.0 / 3.0) * PI * pangkat(radius, 2) * tinggi;
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

    private double hitungJariJariAlas(double r, double h) {
        return akarKuadrat(h * (2 * r - h));
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

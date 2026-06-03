package pElips.model;

public class Tembereng extends Bola {
    private double tinggiTopi;
    private double jariJariAlas;
    private double luasSelimut;
    private double luasAlas;

    public Tembereng(String nama, double r, double h) {
        super(nama, r);
        setTinggiTopi(h);
    }

    @Override
    public double hitungLuas() {
        jariJariAlas = hitungJariJariAlas();
        luasSelimut = 2 * PI * jariJari * tinggiTopi;
        luasAlas = PI * jariJariAlas * jariJariAlas;
        luas = hitungLuas(jariJari, tinggiTopi);
        return luas;
    }

    public double hitungLuas(double r, double h) {
        double radius = wajibPositif("Jari-jari bola", r);
        double tinggi = wajibPositif("Tinggi topi", h);
        validasi(tinggi <= 2 * radius, "Tinggi topi maksimal 2 x jari-jari bola.");
        double alas = hitungJariJariAlas(radius, tinggi);
        luas2 = (2 * PI * radius * tinggi) + (PI * alas * alas);
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
        keliling2 = 2 * PI * hitungJariJariAlas(radius, tinggi);
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
        volume2 = (PI * pangkat(tinggi, 2) / 3.0) * (3 * radius - tinggi);
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
        System.out.println("Luas Selimut         : " + formatAngka(luasSelimut));
        System.out.println("Luas Alas            : " + formatAngka(luasAlas));
        System.out.println("Luas Tembereng       : " + formatAngka(luas));
        System.out.println("Volume Tembereng     : " + formatAngka(volume));
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

    public double getLuasSelimut() {
        return luasSelimut;
    }

    public double getLuasAlas() {
        return luasAlas;
    }
}

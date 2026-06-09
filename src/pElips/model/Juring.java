package pElips.model;

public class Juring extends Bola {
    public double tinggiTopi;
    public double jariJariAlas;
    public double luasAlas;

    public Juring() {
        this("Juring", 1, 1);
    }

    public Juring(String nama, double r, double h) {
        super(nama, r);
        this.tinggiTopi = wajibPositif("Tinggi topi", h);
        validasi(this.tinggiTopi <= 2 * jariJari, "Tinggi topi maksimal 2 x jari-jari bola.");
    }

    @Override
    public double hitungLuas() {
        jariJariAlas = hitungJariJariAlas();
        hitungLuas(jariJari, tinggiTopi);
        luas = super.luas2;
        return luas;
    }

    public double hitungLuas(double r, double h) {
        jariJari = wajibPositif("Jari-jari bola", r);
        diameter = 2 * jariJari;
        tinggiTopi = wajibPositif("Tinggi topi", h);
        validasi(tinggiTopi <= 2 * jariJari, "Tinggi topi maksimal 2 x jari-jari bola.");
        jariJariAlas = hitungJariJariAlas(jariJari, tinggiTopi);
        luasAlas = super.hitungLuasLingkaran(jariJariAlas);
        luas2 = luasAlas + PI * jariJari * tinggiTopi;
        return luas2;
    }

    @Override
    public double hitungKeliling() {
        jariJariAlas = hitungJariJariAlas();
        hitungKeliling(jariJari, tinggiTopi);
        keliling = super.keliling2;
        return keliling;
    }

    public double hitungKeliling(double r, double h) {
        jariJari = wajibPositif("Jari-jari bola", r);
        diameter = 2 * jariJari;
        tinggiTopi = wajibPositif("Tinggi topi", h);
        validasi(tinggiTopi <= 2 * jariJari, "Tinggi topi maksimal 2 x jari-jari bola.");
        jariJariAlas = hitungJariJariAlas(jariJari, tinggiTopi);
        keliling2 = jariJariAlas == 0 ? 0 : super.hitungKelilingLingkaran(jariJariAlas);
        return keliling2;
    }

    @Override
    public double hitungVolume() {
        hitungVolume(jariJari, tinggiTopi);
        volume = super.volume2;
        return volume;
    }

    public double hitungVolume(double r, double h) {
        jariJari = wajibPositif("Jari-jari bola", r);
        diameter = 2 * jariJari;
        tinggiTopi = wajibPositif("Tinggi topi", h);
        validasi(tinggiTopi <= 2 * jariJari, "Tinggi topi maksimal 2 x jari-jari bola.");
        volume2 = (2.0 / 3.0) * PI * pangkat(jariJari, 2) * tinggiTopi;
        return volume2;
    }

    @Override
    public void cetakInfo() {
        hitungSemua();
        System.out.println("--- Data Geometri: " + namaBenda + " ---");
        System.out.println("Jari-jari Bola (r)   : " + jariJari);
        System.out.println("Tinggi Topi (h)      : " + tinggiTopi);
        System.out.println("Jari-jari Alas       : " + formatAngka(jariJariAlas));
        System.out.println("Keliling Alas        : " + formatAngka(keliling));
        System.out.println("Luas Juring          : " + formatAngka(luas));
        System.out.println("Volume Juring        : " + formatAngka(volume));
    }

    public double hitungJariJariAlas() {
        return akarKuadrat(tinggiTopi * (2 * jariJari - tinggiTopi));
    }

    public double hitungJariJariAlas(double r, double h) {
        jariJari = wajibPositif("Jari-jari bola", r);
        diameter = 2 * jariJari;
        tinggiTopi = wajibPositif("Tinggi topi", h);
        validasi(tinggiTopi <= 2 * jariJari, "Tinggi topi maksimal 2 x jari-jari bola.");
        jariJariAlas = akarKuadrat(tinggiTopi * (2 * jariJari - tinggiTopi));
        return jariJariAlas;
    }
}

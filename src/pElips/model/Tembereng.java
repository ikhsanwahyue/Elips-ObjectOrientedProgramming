package pElips.model;

public class Tembereng extends Bola {
    public double tinggiTopi;
    public double jariJariAlas;
    public double luasSelimut;
    public double luasAlas;

    public Tembereng() {
        this("Tembereng", 1, 1);
    }

    public Tembereng(String nama, double r, double h) {
        super(nama, r);
        this.tinggiTopi = wajibPositif("Tinggi topi", h);
        validasi(this.tinggiTopi <= 2 * jariJari, "Tinggi topi maksimal 2 x jari-jari bola.");
    }

    @Override
    public double hitungLuas() {
        jariJariAlas = hitungJariJariAlas();
        luasSelimut = 2 * PI * jariJari * tinggiTopi;
        luasAlas = super.hitungLuasLingkaran(jariJariAlas);
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
        luasSelimut = 2 * PI * jariJari * tinggiTopi;
        luasAlas = super.hitungLuasLingkaran(jariJariAlas);
        luas2 = luasSelimut + luasAlas;
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
        volume2 = (PI * pangkat(tinggiTopi, 2) / 3.0) * (3 * jariJari - tinggiTopi);
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
        System.out.println("Luas Selimut         : " + formatAngka(luasSelimut));
        System.out.println("Luas Alas            : " + formatAngka(luasAlas));
        System.out.println("Luas Tembereng       : " + formatAngka(luas));
        System.out.println("Volume Tembereng     : " + formatAngka(volume));
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

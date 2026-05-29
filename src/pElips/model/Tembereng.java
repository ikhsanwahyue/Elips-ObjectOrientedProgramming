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
        luas = luasSelimut + luasAlas;
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
        volume = (PI * pangkat(tinggiTopi, 2) / 3.0) * (3 * jariJari - tinggiTopi);
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
        System.out.println("Luas Selimut         : " + formatAngka(luasSelimut));
        System.out.println("Luas Alas            : " + formatAngka(luasAlas));
        System.out.println("Luas Tembereng       : " + formatAngka(luas));
        System.out.println("Volume Tembereng     : " + formatAngka(volume));
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

    public double getLuasSelimut() {
        return luasSelimut;
    }

    public double getLuasAlas() {
        return luasAlas;
    }
}

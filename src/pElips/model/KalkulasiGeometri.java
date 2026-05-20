package pElips.model;

public interface KalkulasiGeometri {
    double PI = 3.141592653589793;

    double hitungLuas();
    double hitungKeliling();
    double hitungVolume();

    default double pangkat(double angka, int eksponen) {
        double hasil = 1.0;
        for (int i = 0; i < eksponen; i++) {
            hasil *= angka;
        }
        return hasil;
    }

    default double akarKuadrat(double angka) {
        if (angka < 0) return 0;
        double x = angka;
        double y = 1.0;
        double e = 0.000000001;
        while (x - y > e) {
            x = (x + y) / 2;
            y = angka / x;
        }
        return x;
    }
}
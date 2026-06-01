package pElips.model;

public abstract class BendaGeometri implements Runnable {
    public static final double PI = Math.PI;
    private static final int JUMLAH_ITERASI_THREAD = 1000;

    private String namaBenda;
    protected volatile double luas;
    protected volatile double luas2;
    protected volatile double keliling;
    protected volatile double keliling2;
    protected volatile double volume;
    protected volatile double volume2;
    private volatile int progress;
    private volatile String statusProses;

    public BendaGeometri(String namaBenda) {
        setNamaBenda(namaBenda);
        this.statusProses = "Belum diproses";
    }

    public String getNamaBenda() {
        return namaBenda;
    }

    public void setNamaBenda(String namaBenda) {
        if (namaBenda == null || namaBenda.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama benda tidak boleh kosong.");
        }
        this.namaBenda = namaBenda;
    }

    public double getLuas() {
        return luas;
    }

    public double getKeliling() {
        return keliling;
    }

    public double getVolume() {
        return volume;
    }

    public int getProgress() {
        return progress;
    }

    public String getStatusProses() {
        return statusProses;
    }

    protected void setStatusProses(String statusProses) {
        this.statusProses = statusProses;
    }

    public abstract double hitungLuas();

    public abstract double hitungKeliling();

    public abstract double hitungVolume();

    public void hitungSemua() {
        hitungLuas();
        hitungKeliling();
        hitungVolume();
        if (!"Berjalan".equals(statusProses)) {
            statusProses = "Selesai dihitung";
        }
    }

    @Override
    public void run() {
        statusProses = "Berjalan";
        progress = 0;

        for (int i = 1; i <= JUMLAH_ITERASI_THREAD; i++) {
            if (Thread.currentThread().isInterrupted()) {
                statusProses = "Dibatalkan";
                return;
            }

            hitungSemua();
            progress = (i * 100) / JUMLAH_ITERASI_THREAD;

            if (i % 50 == 0) {
                Thread.yield();
            }
        }

        progress = 100;
        statusProses = "Selesai";
    }

    public abstract void cetakInfo();

    protected double pangkat(double angka, int eksponen) {
        double hasil = 1.0;
        for (int i = 0; i < eksponen; i++) {
            hasil *= angka;
        }
        return hasil;
    }

    protected double akarKuadrat(double angka) {
        if (angka < 0 && angka > -0.000000001) {
            angka = 0;
        }
        if (angka < 0) {
            throw new IllegalArgumentException("Nilai akar kuadrat tidak boleh negatif.");
        }
        return Math.sqrt(angka);
    }

    protected double wajibPositif(String namaAtribut, double nilai) {
        if (nilai <= 0) {
            throw new IllegalArgumentException(namaAtribut + " harus lebih dari 0.");
        }
        return nilai;
    }

    protected void validasi(boolean kondisi, String pesan) {
        if (!kondisi) {
            throw new IllegalArgumentException(pesan);
        }
    }

    protected String formatAngka(double nilai) {
        return String.format("%.4f", nilai);
    }
}

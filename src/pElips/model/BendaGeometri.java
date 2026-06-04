package pElips.model;

/**
 * [Konsep OOP: Abstract Class & Interface]
 * Class BendaGeometri dibuat sebagai 'abstract class' karena merupakan konsep umum yang tidak bisa diinstansiasi langsung.
 * Class ini mengimplementasikan interface 'Runnable' agar objek turunannya nanti bisa dijalankan sebagai Thread (Multithreading).
 */
public abstract class BendaGeometri implements Runnable {
    // Konstanta matematika PI untuk perhitungan rumus lingkaran/elips
    public static final double PI = Math.PI;
    
    // Konstanta untuk menentukan seberapa banyak perulangan (simulasi beban komputasi) di dalam Thread
    public static final int JUMLAH_ITERASI_THREAD = 1000;

    // Atribut untuk menyimpan nama dari benda geometri
    public String namaBenda;
    
    /**
     * [Konsep Multithreading: Keyword volatile]
     * Keyword 'volatile' memastikan bahwa nilai variabel ini selalu dibaca dan ditulis langsung dari/ke Memori Utama (Main Memory),
     * bukan dari cache CPU masing-masing thread. Hal ini mencegah terjadinya inkonsistensi data antar-thread (Thread-Safe visibility).
     */
    public volatile double luas;
    public volatile double luas2;
    public volatile double keliling;
    public volatile double keliling2;
    public volatile double volume;
    public volatile double volume2;
    public volatile int progress; // Untuk mencatat persentase kemajuan thread (0-100%)
    public volatile String statusProses; // Untuk mencatat status thread (misal: "Berjalan", "Selesai", "Dibatalkan")
    
    // Objek Thread yang bertugas mengontrol eksekusi proses di latar belakang
    public Thread thread;

    // Constructor untuk menginisialisasi nama benda dan mengatur status awal proses
    public BendaGeometri(String namaBenda) {
        setNamaBenda(namaBenda); // Memanggil setter untuk memvalidasi input nama
        this.statusProses = "Belum diproses"; // Mengatur status default objek saat pertama kali dibuat
    }

    // Getter untuk mengambil nama benda
    public String getNamaBenda() {
        return namaBenda;
    }

    // Setter untuk mengubah nama benda disertai dengan validasi data input
    public void setNamaBenda(String namaBenda) {
        // Validasi: Nama benda tidak boleh null, kosong, atau hanya berisi spasi
        if (namaBenda == null || namaBenda.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama benda tidak boleh kosong.");
        }
        this.namaBenda = namaBenda;
    }

    // Getter untuk mengambil nilai luas
    public double getLuas() {
        return luas;
    }

    // Getter untuk mengambil nilai keliling
    public double getKeliling() {
        return keliling;
    }

    // Getter untuk mengambil nilai volume
    public double getVolume() {
        return volume;
    }

    // Getter untuk memantau perkembangan/progress komputasi
    public int getProgress() {
        return progress;
    }

    // Getter untuk memantau status proses saat ini
    public String getStatusProses() {
        return statusProses;
    }

    // Setter dengan hak akses protected untuk mengubah status proses secara internal/oleh class turunan
    protected void setStatusProses(String statusProses) {
        this.statusProses = statusProses;
    }

    // Method untuk membuat objek Thread baru dan memberikan nama thread sesuai nama benda geometri
    public Thread buatThread() {
        thread = new Thread(this, getNamaBenda()); // 'this' merujuk pada objek Runnable saat ini
        return thread;
    }

    /**
     * [Konsep OOP: Abstract Method]
     * Tiga method di bawah ini tidak memiliki body (implementasi) karena rumus kalkulasi setiap benda geometri berbeda-beda.
     * Class anak (seperti Elips) WAJIB mengimplementasikan (override) ketiga method ini.
     */
    public abstract double hitungLuas();

    public abstract double hitungKeliling();

    public abstract double hitungVolume();

    // Method sekuensial untuk mengeksekusi semua perhitungan geometri sekaligus
    public void hitungSemua() {
        hitungLuas();     // Memicu perhitungan luas
        hitungKeliling(); // Memicu perhitungan keliling
        hitungVolume();   // Memicu perhitungan volume
        
        // Jika method ini dipanggil di luar Thread utama (bukan mode berjalan), set status menjadi selesai dihitung
        if (!"Berjalan".equals(statusProses)) {
            statusProses = "Selesai dihitung";
        }
    }

    /**
     * [Konsep Multithreading: Overriding method run()]
     * Method ini adalah jantung dari proses Runnable. Ketika thread.start() dipanggil, kode di dalam run() akan dieksekusi.
     */
    @Override
    public void run() {
        statusProses = "Berjalan"; // Mengubah status menjadi sedang berjalan
        progress = 0; // Menginisialisasi progress dari 0%

        // Melakukan perulangan komputasi sebanyak 1000 kali (JUMLAH_ITERASI_THREAD)
        for (int i = 1; i <= JUMLAH_ITERASI_THREAD; i++) {
            
            // Mekanisme Interupsi: Memeriksa apakah thread dipaksa berhenti dari luar
            if (Thread.currentThread().isInterrupted()) {
                statusProses = "Dibatalkan"; // Mengubah status jika dibatalkan
                return; // Langsung keluar dari method run() untuk menghentikan Thread
            }

            hitungSemua(); // Melakukan kalkulasi rumus di setiap iterasi loop
            
            // Menghitung persentase kemajuan (0 sampai 100)
            progress = (i * 100) / JUMLAH_ITERASI_THREAD;

            // Setiap kelipatan 50 iterasi, Thread akan memberikan kesempatan kepada thread lain untuk menggunakan CPU
            if (i % 50 == 0) {
                Thread.yield(); // Menghimbau scheduler agar merelakan core CPU secara sukarela (kooperatif)
            }
        }

        progress = 100; // Memastikan progress bernilai genap 100% saat selesai
        statusProses = "Selesai"; // Mengubah status akhir menjadi selesai
    }

    // Abstract method untuk mencetak data/informasi geometri (wajib di-override class anak)
    public abstract void cetakInfo();

    // Method pembantu (utility) untuk menghitung perpangkatan bilangan
    protected double pangkat(double angka, int eksponen) {
        double hasil = 1.0;
        for (int i = 0; i < eksponen; i++) {
            hasil *= angka;
        }
        return hasil;
    }

    // Method pembantu untuk menghitung akar kuadrat dengan toleransi presisi bilangan negatif kecil
    protected double akarKuadrat(double angka) {
        // Toleransi floating-point jika angka bernilai sedikit di bawah 0 karena error pembulatan komputer
        if (angka < 0 && angka > -0.000000001) {
            angka = 0;
        }
        // Validasi: Bilangan riil negatif tidak memiliki nilai akar kuadrat
        if (angka < 0) {
            throw new IllegalArgumentException("Nilai akar kuadrat tidak boleh negatif.");
        }
        return Math.sqrt(angka); // Memanfaatkan fungsi bawaan Java Math.sqrt
    }

    // Method pembantu untuk memvalidasi bahwa nilai dimensi (panjang/jari-jari/sumbu) harus lebih besar dari 0
    protected double wajibPositif(String namaAtribut, double nilai) {
        if (nilai <= 0) {
            throw new IllegalArgumentException(namaAtribut + " harus lebih dari 0.");
        }
        return nilai;
    }

    // Method validasi umum berbasis kondisi boolean
    protected void validasi(boolean kondisi, String pesan) {
        if (!kondisi) { // Catatan: Ada sedikit typo bawaan code Anda 'kondisi'/'conditions', tetapi logika tetap dipertahankan sesuai file asli Anda
            throw new IllegalArgumentException(pesan);
        }
    }

    // Method pembantu untuk memformat bilangan desimal agar menampilkan 4 angka di belakang koma (.4f)
    protected String formatAngka(double nilai) {
        return String.format("%.4f", nilai);
    }
}
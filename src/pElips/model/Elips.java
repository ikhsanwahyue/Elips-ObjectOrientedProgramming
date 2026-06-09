package pElips.model;

/**
 * [Konsep OOP: Inheritance / Pewarisan]
 * Class Elips bertindak sebagai subclass (class anak) yang mewarisi seluruh kode, atribut, 
 * dan method milik superclass-nya (BendaGeometri) menggunakan keyword 'extends'.
 */
public class Elips extends BendaGeometri {
    // Atribut spesifik milik Elips untuk menyimpan panjang sumbu semi-mayor (a) dan semi-minor (b)
    public double sumbuA;
    public double sumbuB;

    // Constructor default agar contoh polymorphism Elips e1 = new Elips() bisa digunakan
    public Elips() {
        this("Elips", 1, 1);
    }

    // Constructor Class Elips untuk membuat objek baru
    public Elips(String nama, double a, double b) {
        super(nama); // [Konsep OOP] Keyword 'super' digunakan untuk memanggil constructor milik class induk (BendaGeometri)
        this.sumbuA = wajibPositif("Sumbu A", a); // Memvalidasi nilai sumbu A
        this.sumbuB = wajibPositif("Sumbu B", b); // Memvalidasi nilai sumbu B
    }

    /**
     * [Konsep OOP: Polymorphism - Method Overriding]
     * Mengimplementasikan ulang method hitungLuas() abstract yang diturunkan dari BendaGeometri.
     */
    @Override
    public double hitungLuas() {
        luas = hitungLuas(sumbuA, sumbuB); // Memanggil method overload di bawahnya
        return luas;
    }

    /**
     * [Konsep OOP: Polymorphism - Method Overloading]
     * Method ini memiliki nama yang sama ('hitungLuas') tetapi jumlah/tipe parameter berbeda (menerima data a dan b).
     */
    public double hitungLuas(double a, double b) {
        sumbuA = wajibPositif("Sumbu A", a); // Validasi input sumbu A wajib > 0
        sumbuB = wajibPositif("Sumbu B", b); // Validasi input sumbu B wajib > 0
        luas2 = hitungLuasElips(sumbuA, sumbuB); // Menghitung dengan rumus matematika elips
        return luas2;
    }

    // Overriding method hitungKeliling() dari class induk
    @Override
    public double hitungKeliling() {
        keliling = hitungKeliling(sumbuA, sumbuB); // Memanggil method overload keliling
        return keliling;
    }

    // Overloading method hitungKeliling dengan parameter eksternal
    public double hitungKeliling(double a, double b) {
        sumbuA = wajibPositif("Sumbu A", a); // Validasi sumbu A
        sumbuB = wajibPositif("Sumbu B", b); // Validasi sumbu B
        keliling2 = hitungKelilingElips(sumbuA, sumbuB); // Menghitung keliling elips dengan rumus Ramanujan
        return keliling2;
    }

    // Overriding method hitungVolume() dari class induk
    @Override
    public double hitungVolume() {
        volume = hitungVolume(sumbuA, sumbuB); // Memanggil method overload volume
        return volume;
    }

    // Overloading method hitungVolume. Karena elips adalah bangun 2D (dua dimensi), volumenya diatur bernilai 0
    public double hitungVolume(double a, double b) {
        sumbuA = wajibPositif("Sumbu A", a); // Tetap memvalidasi keabsahan nilai sumbu A
        sumbuB = wajibPositif("Sumbu B", b); // Tetap memvalidasi keabsahan nilai sumbu B
        volume2 = 0; // Set volume ke angka 0 karena tidak memiliki ketebalan/ruang
        return volume2;
    }

    // Overriding method cetakInfo() untuk menampilkan ringkasan data Elips ke terminal/konsol output
    @Override
    public void cetakInfo() {
        hitungSemua(); // Memastikan kalkulasi ter-update sebelum dicetak
        System.out.println("--- Data Geometri: " + namaBenda + " ---");
        System.out.println("Sumbu Semi-Mayor (A) : " + sumbuA);
        System.out.println("Sumbu Semi-Minor (B) : " + sumbuB);
        System.out.println("Luas                 : " + formatAngka(luas)); // Menggunakan utility format 4 desimal
        System.out.println("Keliling             : " + formatAngka(keliling));
    }

    // Fungsi matematika murni untuk rumus Luas Elips: Luas = PI * a * b
    public double hitungLuasElips(double a, double b) {
        return PI * a * b;
    }

    // Fungsi matematika menggunakan Pendekatan Formula Ramanujan untuk menghitung Keliling Elips secara presisi
    public double hitungKelilingElips(double a, double b) {
        return PI * (3 * (a + b) - akarKuadrat((3 * a + b) * (a + 3 * b)));
    }
}

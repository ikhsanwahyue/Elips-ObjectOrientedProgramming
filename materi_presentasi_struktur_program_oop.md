# Materi Presentasi Struktur Program OOP

## Ringkasan

Bagian ini menjelaskan inti struktur program dalam durasi sekitar 3-5 menit. Urutan yang disarankan:

1. Arsitektur program.
2. Konsep OOP yang digunakan.
3. Struktur class.
4. Method utama dan alur kerja program.

Fokus penjelasan ada pada inti logika program, terutama package `model`, bukan pada detail desain tampilan GUI.

## Poin Slide

### 1. Arsitektur Program

- Program dibagi menjadi tiga bagian utama:
  - `Main` sebagai entry point aplikasi.
  - `MainFrame` sebagai tampilan GUI dan penghubung input user.
  - Package `model` sebagai inti logika geometri.
- `Main.main()` membuka GUI utama menggunakan `SwingUtilities.invokeLater()`.
- `MainFrame` menerima input user, membuat objek geometri, memanggil perhitungan, lalu menampilkan hasil.

### 2. Konsep OOP yang Digunakan

- Abstraction:
  - `BendaGeometri` adalah abstract class utama.
  - Class ini mendefinisikan method wajib: `hitungLuas()`, `hitungKeliling()`, `hitungVolume()`, dan `cetakInfo()`.
- Inheritance:
  - `Elips` menjadi parent untuk `KerucutDenganAlasElips`.
  - `KerucutDenganAlasElips` menjadi parent untuk `KerucutTerpancungDenganAlasElips`.
  - `Bola` menjadi parent untuk `Tabung`, `Cincin`, `Juring`, dan `Tembereng`.
- Encapsulation:
  - Atribut penting dijaga dengan getter dan setter.
  - Validasi input dilakukan melalui setter dan helper seperti `wajibPositif()`.
- Polymorphism:
  - Banyak objek berbeda tetap bisa diproses menggunakan tipe parent `BendaGeometri`.
  - Saat `hitungSemua()` dipanggil, method yang berjalan menyesuaikan class asli objek.
- Overriding:
  - Class turunan menulis ulang method dari parent atau abstract class.
  - Contohnya `Elips`, `Bola`, `Tabung`, dan class lainnya memiliki versi sendiri untuk `hitungLuas()`, `hitungKeliling()`, `hitungVolume()`, dan `cetakInfo()`.
  - Di kode, overriding ditandai dengan anotasi `@Override`.
- Overloading:
  - Beberapa constructor memiliki nama yang sama, tetapi parameter berbeda.
  - Contohnya `Elips(String nama)` dan `Elips(String nama, double a, double b)`.
  - Contoh lain adalah `Bola(String nama)` dan `Bola(String nama, double r)`.
- Multithreading:
  - `BendaGeometri implements Runnable`.
  - Setiap objek geometri dapat menjalankan proses hitung melalui method `run()`.

### 3. Struktur Class

- Hirarki berbasis elips:
  - `BendaGeometri` -> `Elips`
  - `Elips` -> `KerucutDenganAlasElips`
  - `KerucutDenganAlasElips` -> `KerucutTerpancungDenganAlasElips`
- Hirarki berbasis bola:
  - `BendaGeometri` -> `Bola`
  - `Bola` -> `Tabung`
  - `Bola` -> `Cincin`
  - `Bola` -> `Juring`
  - `Bola` -> `Tembereng`
- Class GUI:
  - `MainFrame` membuat objek sesuai pilihan user.
  - Setelah objek dibuat, GUI memanggil method perhitungan dari model.

### 4. Method Utama

- `Main.main()`
  - Entry point utama program.
  - Membuka `MainFrame`.
- `sesuaikanInput()`
  - Menyesuaikan label dan jumlah field input berdasarkan jenis benda yang dipilih.
- `handleEvent()`
  - Mengatur aksi tombol hitung dan tombol demo multithreading.
- `dapatkanObjekBenda()`
  - Membaca input user.
  - Membuat objek seperti `Elips`, `Bola`, `Tabung`, `Cincin`, dan class lainnya.
- `hitungSemua()`
  - Memanggil `hitungLuas()`, `hitungKeliling()`, dan `hitungVolume()`.
- `run()`
  - Menjalankan proses hitung berulang.
  - Memperbarui `progress` dan `statusProses`.
- `jalankanDemoMultithreading()`
  - Membuat 100.000 data untuk setiap jenis benda.
  - Total data demo adalah 800.000 objek.
  - Memproses data menggunakan `ExecutorService`, `CountDownLatch`, dan counter atomic.

## Naskah Bicara 3-5 Menit

"Pada bagian struktur program, inti logika aplikasi ini ada di package `model`. Class paling atas adalah `BendaGeometri`, yaitu abstract class yang menjadi dasar untuk semua benda geometri. Di class ini terdapat atribut umum seperti nama benda, luas, keliling, volume, progress, dan status proses. Karena class ini abstract, rumus luas, keliling, volume, dan cara mencetak informasi tidak langsung ditentukan di sini, tetapi dibuat oleh class turunannya.

Konsep OOP pertama yang digunakan adalah abstraction. `BendaGeometri` hanya menentukan method, misalnya `hitungLuas()`, `hitungKeliling()`, `hitungVolume()`, dan `cetakInfo()`. Setiap bentuk geometri punya rumus berbeda, jadi implementasinya dibuat di class masing-masing.

Konsep berikutnya adalah inheritance. Misalnya `Elips` menjadi dasar untuk `KerucutDenganAlasElips`, lalu class itu diturunkan lagi menjadi `KerucutTerpancungDenganAlasElips`. Untuk bentuk berbasis lingkaran atau bola, ada class `Bola`, lalu turunannya adalah `Tabung`, `Cincin`, `Juring`, dan `Tembereng`. Dengan inheritance, class turunan bisa memakai atribut dan method bantu dari parent, seperti method `pangkat()`, `akarKuadrat()`, dan validasi input.

Konsep berikutnya adalah overriding. Overriding terjadi ketika class turunan menulis ulang method dari parent. Di program ini, contohnya ada pada method `hitungLuas()`, `hitungKeliling()`, `hitungVolume()`, dan `cetakInfo()`. Method tersebut sudah didefinisikan sebagai method wajib di `BendaGeometri`, lalu setiap class seperti `Elips`, `Bola`, `Tabung`, dan `Cincin` membuat implementasi rumusnya sendiri. Di kode Java, overriding ini ditandai dengan anotasi `@Override`.

Program ini juga memakai overloading, terutama pada constructor. Overloading berarti ada method atau constructor dengan nama yang sama, tetapi daftar parameternya berbeda. Contohnya pada class `Elips`, ada constructor `Elips(String nama)` untuk nilai default, dan ada `Elips(String nama, double a, double b)` untuk memasukkan ukuran sumbu secara langsung. Hal yang sama juga ada pada class `Bola`, yaitu `Bola(String nama)` dan `Bola(String nama, double r)`.

Konsep berikutnya adalah encapsulation. Data tidak semuanya diakses bebas dari luar. Contohnya nama benda disimpan private, lalu diakses lewat getter dan setter. Untuk nilai dimensi seperti jari-jari, sumbu, dan tinggi, setter juga melakukan validasi agar nilainya harus positif. Jadi validasi diletakkan dekat dengan data yang dijaga.

Konsep berikutnya adalah polymorphism. Di GUI, objek disimpan dengan tipe `BendaGeometri`, tetapi isi sebenarnya bisa berupa `Elips`, `Bola`, `Tabung`, atau class lainnya. Saat method `hitungSemua()` dipanggil, Java otomatis menjalankan versi `hitungLuas()`, `hitungKeliling()`, dan `hitungVolume()` sesuai class asli objek tersebut.

Untuk alur program, `Main.main()` adalah entry point utama. Method ini membuka `MainFrame`, yaitu tampilan GUI. Di dalam `MainFrame`, method `sesuaikanInput()` mengubah field input sesuai jenis benda yang dipilih. Setelah user menekan tombol hitung, method `handleEvent()` memanggil method `dapatkanObjekBenda()` untuk membuat objek yang sesuai, lalu memanggil method `hitungSemua()` dan menampilkan hasilnya.

Selain hitung manual, program juga memiliki demo multithreading. Karena `BendaGeometri implements Runnable`, setiap objek geometri punya method `run()`. Method ini menjalankan perhitungan berulang, memperbarui progress, dan mengubah status dari berjalan menjadi selesai. Di GUI, `jalankanDemoMultithreading()` membuat 100.000 data untuk setiap jenis benda, total 800.000 objek, lalu memprosesnya menggunakan `ExecutorService`, `CountDownLatch`, dan counter atomic agar progress bisa ditampilkan di tabel dan bar chart.

Jadi kesimpulannya, inti program ini bukan hanya menghitung rumus geometri, tetapi juga menunjukkan penerapan OOP: abstract class untuk kontrak umum, inheritance untuk hubungan antar bentuk, encapsulation untuk keamanan data, polymorphism untuk memproses banyak objek dengan satu tipe parent, overriding untuk menyesuaikan rumus di setiap class turunan, overloading untuk menyediakan beberapa cara membuat objek, dan multithreading untuk menjalankan perhitungan massal."

## Catatan Singkat Saat Presentasi

- Buka dari `Main.java` untuk menunjukkan entry point.
- Lanjutkan ke `BendaGeometri.java` untuk menjelaskan abstract class dan `Runnable`.
- Tunjukkan satu contoh turunan sederhana, misalnya `Elips.java` atau `Bola.java`.
- Tunjukkan satu contoh turunan lanjutan, misalnya `Tabung.java` atau `KerucutTerpancungDenganAlasElips.java`.
- Akhiri di `MainFrame.java`, terutama alur `handleEvent()`, `dapatkanObjekBenda()`, dan `jalankanDemoMultithreading()`.

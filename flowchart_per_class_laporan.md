# Flowchart Per Class untuk Laporan

Dokumen ini dibuat per class agar mudah dimasukkan ke laporan. Setiap bagian dapat dijadikan satu halaman tersendiri. Flowchart hanya memuat alur yang penting: constructor, validasi, perhitungan utama, proses `run()`, GUI, dan multithreading.

---

## Page 1 - `BendaGeometri` (Abstract Class)

Fokus: alur umum semua benda geometri dan proses `Runnable`.

```mermaid
flowchart TD
    A([Objek BendaGeometri dibuat]) --> B["Terima namaBenda"]
    B --> C{"namaBenda valid?"}
    C -->|"Tidak"| D["Throw IllegalArgumentException"]
    C -->|"Ya"| E["Simpan namaBenda"]
    E --> F["statusProses = Belum diproses"]
    F --> G([Objek siap digunakan])

    H([hitungSemua dipanggil]) --> I["hitungLuas()"]
    I --> J["hitungKeliling()"]
    J --> K["hitungVolume()"]
    K --> L{"statusProses sedang Berjalan?"}
    L -->|"Tidak"| M["statusProses = Selesai dihitung"]
    L -->|"Ya"| N["Status tetap Berjalan"]
    M --> O([Selesai])
    N --> O

    P([run dipanggil oleh thread]) --> Q["statusProses = Berjalan"]
    Q --> R["progress = 0"]
    R --> S{"Iterasi <= 1000?"}
    S -->|"Ya"| T{"Thread interrupted?"}
    T -->|"Ya"| U["statusProses = Dibatalkan"]
    U --> V([Stop])
    T -->|"Tidak"| W["Panggil hitungSemua()"]
    W --> X["Update progress"]
    X --> S
    S -->|"Tidak"| Y["progress = 100"]
    Y --> Z["statusProses = Selesai"]
    Z --> AA([Thread selesai])
```

<div style="page-break-after: always;"></div>

---

## Page 2 - `Elips`

Fokus: validasi sumbu dan perhitungan benda 2D.

```mermaid
flowchart TD
    A([Constructor Elips]) --> B["Terima nama, sumbuA, sumbuB"]
    B --> C["Panggil constructor BendaGeometri"]
    C --> D{"sumbuA > 0?"}
    D -->|"Tidak"| E["Throw error"]
    D -->|"Ya"| F["Simpan sumbuA"]
    F --> G{"sumbuB > 0?"}
    G -->|"Tidak"| E
    G -->|"Ya"| H["Simpan sumbuB"]
    H --> I([Elips siap dihitung])

    J([hitungLuas]) --> K["luas = PI * sumbuA * sumbuB"]
    K --> L["return luas"]

    M([hitungKeliling]) --> N["Hitung keliling dengan rumus pendekatan Ramanujan"]
    N --> O["keliling = hasil rumus"]
    O --> P["return keliling"]

    Q([hitungVolume]) --> R["volume = 0 karena Elips adalah 2D"]
    R --> S["return volume"]
```

<div style="page-break-after: always;"></div>

---

## Page 3 - `KerucutDenganAlasElips`

Fokus: turunan `Elips`, menghitung luas permukaan dan volume kerucut alas elips.

```mermaid
flowchart TD
    A([Constructor KerucutDenganAlasElips]) --> B["Terima nama, a, b, tinggi"]
    B --> C["Panggil constructor Elips"]
    C --> D{"tinggi > 0?"}
    D -->|"Tidak"| E["Throw error"]
    D -->|"Ya"| F["Simpan tinggi"]
    F --> G([Objek siap dihitung])

    H([hitungLuas]) --> I["luasAlas = luas elips"]
    I --> J["garisPelukisA = akar(sumbuA^2 + tinggi^2)"]
    J --> K["garisPelukisB = akar(sumbuB^2 + tinggi^2)"]
    K --> L["luasSelimut = PI * (a*sB + b*sA) / 2"]
    L --> M["luas = luasAlas + luasSelimut"]
    M --> N["return luas"]

    O([hitungKeliling]) --> P["keliling = keliling alas elips"]
    P --> Q["return keliling"]

    R([hitungVolume]) --> S["luasAlas = luas elips"]
    S --> T["volume = 1/3 * luasAlas * tinggi"]
    T --> U["return volume"]
```

<div style="page-break-after: always;"></div>

---

## Page 4 - `KerucutTerpancungDenganAlasElips`

Fokus: validasi alas atas lebih kecil, menghitung frustum alas elips.

```mermaid
flowchart TD
    A([Constructor KerucutTerpancung]) --> B["Terima a1, b1, a2, b2, tinggi"]
    B --> C["Panggil constructor KerucutDenganAlasElips"]
    C --> D{"a2 > 0 dan a2 < a1?"}
    D -->|"Tidak"| E["Throw error"]
    D -->|"Ya"| F["Simpan sumbuA2"]
    F --> G{"b2 > 0 dan b2 < b1?"}
    G -->|"Tidak"| E
    G -->|"Ya"| H["Simpan sumbuB2"]
    H --> I([Objek siap dihitung])

    J([hitungLuas]) --> K["luasAlasBawah = luas elips a1,b1"]
    K --> L["luasAlasAtas = luas elips a2,b2"]
    L --> M["kelilingBawah = keliling elips bawah"]
    M --> N["kelilingAtas = keliling elips atas"]
    N --> O["Hitung selisih sumbu: diffA, diffB"]
    O --> P["garisPelukis = akar(t^2 + rata-rata diff^2)"]
    P --> Q["luasSelimut = 0.5 * (kelilingBawah + kelilingAtas) * garisPelukis"]
    Q --> R["luas = luasAlasBawah + luasAlasAtas + luasSelimut"]
    R --> S["return luas"]

    T([hitungVolume]) --> U["ab1 = a1*b1, ab2 = a2*b2"]
    U --> V["volume = 1/3 * PI * tinggi * (ab1 + ab2 + akar(ab1*ab2))"]
    V --> W["return volume"]
```

<div style="page-break-after: always;"></div>

---

## Page 5 - `Bola`

Fokus: parent untuk juring, tembereng, cincin, dan tabung.

```mermaid
flowchart TD
    A([Constructor Bola]) --> B["Terima nama dan jariJari"]
    B --> C["Panggil constructor BendaGeometri"]
    C --> D{"jariJari > 0?"}
    D -->|"Tidak"| E["Throw error"]
    D -->|"Ya"| F["Simpan jariJari"]
    F --> G["diameter = 2 * jariJari"]
    G --> H([Bola siap dihitung])

    I([hitungLuas]) --> J["luas = 4 * PI * r^2"]
    J --> K["return luas"]

    L([hitungKeliling]) --> M["kelilingLingkaranBesar = 2 * PI * r"]
    M --> N["keliling = kelilingLingkaranBesar"]
    N --> O["return keliling"]

    P([hitungVolume]) --> Q["volume = 4/3 * PI * r^3"]
    Q --> R["return volume"]
```

<div style="page-break-after: always;"></div>

---

## Page 6 - `Tabung`

Fokus: turunan `Bola`, memakai `jariJari` sebagai alas tabung.

```mermaid
flowchart TD
    A([Constructor Tabung]) --> B["Terima nama, r, tinggi"]
    B --> C["Panggil constructor Bola"]
    C --> D{"tinggi > 0?"}
    D -->|"Tidak"| E["Throw error"]
    D -->|"Ya"| F["Simpan tinggi"]
    F --> G([Tabung siap dihitung])

    H([hitungLuas]) --> I["luasAlas = PI * r^2"]
    I --> J["luasSelimut = 2 * PI * r * tinggi"]
    J --> K["luas = 2*luasAlas + luasSelimut"]
    K --> L["return luas"]

    M([hitungKeliling]) --> N["keliling = 2 * PI * r"]
    N --> O["return keliling"]

    P([hitungVolume]) --> Q["luasAlas = PI * r^2"]
    Q --> R["volume = luasAlas * tinggi"]
    R --> S["return volume"]
```

<div style="page-break-after: always;"></div>

---

## Page 7 - `Cincin`

Fokus: validasi radius mayor dan minor.

```mermaid
flowchart TD
    A([Constructor Cincin]) --> B["Terima nama, R, r"]
    B --> C["Panggil constructor Bola dengan r"]
    C --> D["Simpan jariJariMinor"]
    D --> E{"R > 0 dan R > r?"}
    E -->|"Tidak"| F["Throw error"]
    E -->|"Ya"| G["Simpan jariJariMayor"]
    G --> H([Cincin siap dihitung])

    I([hitungLuas]) --> J["luas = 4 * PI^2 * R * r"]
    J --> K["return luas"]

    L([hitungKeliling]) --> M["keliling = 2 * PI * R"]
    M --> N["return keliling"]

    O([hitungVolume]) --> P["volume = 2 * PI^2 * R * r^2"]
    P --> Q["return volume"]
```

<div style="page-break-after: always;"></div>

---

## Page 8 - `Juring`

Fokus: validasi tinggi topi dan perhitungan juring bola.

```mermaid
flowchart TD
    A([Constructor Juring]) --> B["Terima nama, r, tinggiTopi"]
    B --> C["Panggil constructor Bola"]
    C --> D{"0 < tinggiTopi <= 2*r?"}
    D -->|"Tidak"| E["Throw error"]
    D -->|"Ya"| F["Simpan tinggiTopi"]
    F --> G([Juring siap dihitung])

    H([hitungJariJariAlas]) --> I["jariJariAlas = akar(h * (2*r - h))"]
    I --> J["return jariJariAlas"]

    K([hitungLuas]) --> L["Hitung jariJariAlas"]
    L --> M["luas = PI * r * (2*h + jariJariAlas)"]
    M --> N["return luas"]

    O([hitungKeliling]) --> P["Hitung jariJariAlas"]
    P --> Q["keliling = 2 * PI * jariJariAlas"]
    Q --> R["return keliling"]

    S([hitungVolume]) --> T["volume = 2/3 * PI * r^2 * h"]
    T --> U["return volume"]
```

<div style="page-break-after: always;"></div>

---

## Page 9 - `Tembereng`

Fokus: validasi tinggi topi dan perhitungan tembereng bola.

```mermaid
flowchart TD
    A([Constructor Tembereng]) --> B["Terima nama, r, tinggiTopi"]
    B --> C["Panggil constructor Bola"]
    C --> D{"0 < tinggiTopi <= 2*r?"}
    D -->|"Tidak"| E["Throw error"]
    D -->|"Ya"| F["Simpan tinggiTopi"]
    F --> G([Tembereng siap dihitung])

    H([hitungJariJariAlas]) --> I["jariJariAlas = akar(h * (2*r - h))"]
    I --> J["return jariJariAlas"]

    K([hitungLuas]) --> L["Hitung jariJariAlas"]
    L --> M["luasSelimut = 2 * PI * r * h"]
    M --> N["luasAlas = PI * jariJariAlas^2"]
    N --> O["luas = luasSelimut + luasAlas"]
    O --> P["return luas"]

    Q([hitungKeliling]) --> R["Hitung jariJariAlas"]
    R --> S["keliling = 2 * PI * jariJariAlas"]
    S --> T["return keliling"]

    U([hitungVolume]) --> V["volume = PI*h^2/3 * (3*r - h)"]
    V --> W["return volume"]
```

<div style="page-break-after: always;"></div>

---

## Page 10 - `Main`

Fokus: class utama sebagai dirijen aplikasi.

```mermaid
flowchart TD
    A([Program dijalankan]) --> B["Main.main()"]
    B --> C["Tampilkan pesan bahwa Main adalah dirijen"]
    C --> D["SwingUtilities.invokeLater()"]
    D --> E["Buat objek MainFrame"]
    E --> F["setVisible(true)"]
    F --> G([GUI utama terbuka])
```

<div style="page-break-after: always;"></div>

---

## Page 11 - `MainFrame`

Fokus: alur GUI untuk input manual dan demo multithreading.

```mermaid
flowchart TD
    A([MainFrame dibuat]) --> B["Atur title, ukuran, close operation"]
    B --> C["initComponents()"]
    C --> D["handleEvent()"]
    D --> E["sesuaikanInput()"]
    E --> F([GUI siap dipakai])

    G([User klik Hitung dan Tampilkan]) --> H["Baca jenis benda dari combo box"]
    H --> I["Baca input field"]
    I --> J{"Input valid?"}
    J -->|"Tidak"| K["Tampilkan JOptionPane error"]
    J -->|"Ya"| L["Buat objek BendaGeometri sesuai pilihan"]
    L --> M["Panggil hitungSemua()"]
    M --> N["Tampilkan hasil ke log sistem"]

    O([User klik Demo Multithreading]) --> P["Reset visualisasi dan tabel"]
    P --> Q["Buat SwingWorker"]
    Q --> R["Generate 100.000 data per kategori"]
    R --> S["Total data = 800.000 objek"]
    S --> T["Buat ExecutorService 8 worker"]
    T --> U["Setiap worker memproses satu kategori"]
    U --> V["Setiap objek menjalankan run()"]
    V --> W["Update kategoriDone dan totalDone"]
    W --> X["Update visual bar dan tabel"]
    X --> Y{"Semua worker selesai?"}
    Y -->|"Belum"| W
    Y -->|"Ya"| Z["Tampilkan durasi, status selesai, dan data akhir"]
```

<div style="page-break-after: always;"></div>

---

## Page 12 - `SimulasiHitung`

Fokus: alur console/CLI sebagai alternatif demonstrasi.

```mermaid
flowchart TD
    A([SimulasiHitung.main]) --> B["Tampilkan menu"]
    B --> C{"Pilih menu"}
    C -->|"1 Buat Benda"| D["Pilih jenis benda"]
    D --> E["Baca input sesuai jenis"]
    E --> F{"Input valid?"}
    F -->|"Tidak"| G["Tampilkan pesan error"]
    F -->|"Ya"| H["Tambahkan ke daftarBenda"]
    H --> I["cetakInfo()"]
    I --> B

    C -->|"2 Lihat Semua"| J{"daftarBenda kosong?"}
    J -->|"Ya"| K["Tampilkan daftar kosong"]
    J -->|"Tidak"| L["Loop cetakInfo setiap benda"]
    K --> B
    L --> B

    C -->|"3 Polimorfisme"| M["Loop daftarBenda sebagai BendaGeometri"]
    M --> N["Panggil hitungSemua()"]
    N --> O["Tampilkan luas, keliling, volume"]
    O --> B

    C -->|"4 Multithreading"| P["Generate 800.000 data"]
    P --> Q["Bagi 8 worker per kategori"]
    Q --> R["Setiap objek menjalankan run()"]
    R --> S["Tampilkan progress console"]
    S --> B

    C -->|"5 Keluar"| T([Program selesai])
```


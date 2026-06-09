# Flowchart Model Geometri

File ini berisi flowchart detail berdasarkan kode pada folder `src/pElips/model`.
Format diagram menggunakan Mermaid agar mudah ditempel ke Markdown, dokumentasi, atau editor yang mendukung Mermaid.

## Relasi Pewarisan Class

```mermaid
flowchart TD
    A["BendaGeometri<br/>abstract class, implements Runnable"]
    B["Elips"]
    C["KerucutDenganAlasElips"]
    D["KerucutTerpancungDenganAlasElips"]
    E["Bola"]
    F["Cincin"]
    G["Juring"]
    H["Tabung"]
    I["Tembereng"]

    A --> B
    B --> C
    C --> D
    A --> E
    E --> F
    E --> G
    E --> H
    E --> I
```

Catatan:
- Semua class turunan memakai alur thread dari `BendaGeometri.run()`.
- Method `run()` pada class turunan hanya memanggil `super.run()`.
- Method `hitungSemua()` selalu memanggil `hitungLuas()`, `hitungKeliling()`, lalu `hitungVolume()` sesuai implementasi class masing-masing.

---

## 1. BendaGeometri.java

### Alur Constructor dan Validasi Nama

```mermaid
flowchart TD
    A([Mulai membuat objek turunan BendaGeometri])
    B["Constructor BendaGeometri(namaBenda)"]
    C["Panggil setNamaBenda(namaBenda)"]
    D{"namaBenda == null<br/>atau kosong setelah trim?"}
    E["Throw IllegalArgumentException:<br/>Nama benda tidak boleh kosong"]
    F["Simpan this.namaBenda = namaBenda"]
    G["Set statusProses = Belum diproses"]
    H([Objek dasar selesai dibuat])

    A --> B --> C --> D
    D -- Ya --> E
    D -- Tidak --> F --> G --> H
```

### Alur hitungSemua()

```mermaid
flowchart TD
    A([Mulai hitungSemua])
    B["Panggil hitungLuas()<br/>di class turunan"]
    C["Panggil hitungKeliling()<br/>di class turunan"]
    D["Panggil hitungVolume()<br/>di class turunan"]
    E{"statusProses bukan Berjalan?"}
    F["statusProses = Selesai dihitung"]
    G["Status tetap Berjalan"]
    H([Selesai hitungSemua])

    A --> B --> C --> D --> E
    E -- Ya --> F --> H
    E -- Tidak --> G --> H
```

### Alur run() untuk Multithreading

```mermaid
flowchart TD
    A([Thread.start memanggil run])
    B["statusProses = Berjalan"]
    C["progress = 0"]
    D["i = 1"]
    E{"i <= JUMLAH_ITERASI_THREAD<br/>(1000)?"}
    F{"Thread sedang interrupted?"}
    G["statusProses = Dibatalkan"]
    H([Keluar dari run])
    I["Panggil hitungSemua()"]
    J["progress = i * 100 / 1000"]
    K{"i kelipatan 50?"}
    L["Thread.yield()"]
    M["i++"]
    N["progress = 100"]
    O["statusProses = Selesai"]
    P([Thread selesai])

    A --> B --> C --> D --> E
    E -- Tidak --> N --> O --> P
    E -- Ya --> F
    F -- Ya --> G --> H
    F -- Tidak --> I --> J --> K
    K -- Ya --> L --> M --> E
    K -- Tidak --> M --> E
```

### Alur Utility Method

```mermaid
flowchart TD
    A([Mulai utility method])

    A --> B["pangkat(angka, eksponen)"]
    B --> C["hasil = 1"]
    C --> D{"i < eksponen?"}
    D -- Ya --> E["hasil = hasil * angka"]
    E --> F["i++"]
    F --> D
    D -- Tidak --> G["return hasil"]

    A --> H["akarKuadrat(angka)"]
    H --> I{"angka < 0 dan angka > -0.000000001?"}
    I -- Ya --> J["angka = 0"]
    I -- Tidak --> K{"angka < 0?"}
    J --> K
    K -- Ya --> L["Throw IllegalArgumentException:<br/>akar kuadrat tidak boleh negatif"]
    K -- Tidak --> M["return Math.sqrt(angka)"]

    A --> N["wajibPositif(namaAtribut, nilai)"]
    N --> O{"nilai <= 0?"}
    O -- Ya --> P["Throw IllegalArgumentException:<br/>atribut harus lebih dari 0"]
    O -- Tidak --> Q["return nilai"]

    A --> R["validasi(kondisi, pesan)"]
    R --> S{"kondisi bernilai false?"}
    S -- Ya --> T["Throw IllegalArgumentException(pesan)"]
    S -- Tidak --> U["Lanjut"]

    A --> V["formatAngka(nilai)"]
    V --> W["return String.format(%.4f, nilai)"]
```

---

## 2. Elips.java

### Alur Constructor

```mermaid
flowchart TD
    A([Mulai new Elips])
    B["Input: nama, a, b"]
    C["Panggil super(nama)<br/>validasi nama di BendaGeometri"]
    D["setSumbuA(a)"]
    E{"a <= 0?"}
    F["Throw IllegalArgumentException:<br/>Sumbu A harus lebih dari 0"]
    G["sumbuA = a"]
    H["setSumbuB(b)"]
    I{"b <= 0?"}
    J["Throw IllegalArgumentException:<br/>Sumbu B harus lebih dari 0"]
    K["sumbuB = b"]
    L([Objek Elips siap])

    A --> B --> C --> D --> E
    E -- Ya --> F
    E -- Tidak --> G --> H --> I
    I -- Ya --> J
    I -- Tidak --> K --> L
```

### Alur Perhitungan Elips

```mermaid
flowchart TD
    A([Mulai perhitungan Elips])

    A --> B["hitungLuas()"]
    B --> C["Panggil hitungLuas(sumbuA, sumbuB)"]
    C --> D{"sumbuA > 0 dan sumbuB > 0?"}
    D -- Tidak --> E["Throw IllegalArgumentException"]
    D -- Ya --> F["luas2 = PI * a * b"]
    F --> G["luas = luas2"]

    G --> H["hitungKeliling()"]
    H --> I["Panggil hitungKeliling(sumbuA, sumbuB)"]
    I --> J{"sumbuA > 0 dan sumbuB > 0?"}
    J -- Tidak --> K["Throw IllegalArgumentException"]
    J -- Ya --> L["keliling2 = PI * (3 * (a + b)<br/>- sqrt((3a + b) * (a + 3b)))"]
    L --> M["keliling = keliling2"]

    M --> N["hitungVolume()"]
    N --> O["Panggil hitungVolume(sumbuA, sumbuB)"]
    O --> P{"sumbuA > 0 dan sumbuB > 0?"}
    P -- Tidak --> Q["Throw IllegalArgumentException"]
    P -- Ya --> R["volume2 = 0<br/>karena elips adalah bangun 2D"]
    R --> S["volume = volume2"]
    S --> T([Perhitungan selesai])
```

### Alur cetakInfo()

```mermaid
flowchart TD
    A([Mulai cetakInfo Elips])
    B["Panggil hitungSemua()"]
    C["Cetak nama benda"]
    D["Cetak sumbuA dan sumbuB"]
    E["Cetak luas dengan format 4 desimal"]
    F["Cetak keliling dengan format 4 desimal"]
    G([Selesai])

    A --> B --> C --> D --> E --> F --> G
```

---

## 3. KerucutDenganAlasElips.java

### Alur Constructor

```mermaid
flowchart TD
    A([Mulai new KerucutDenganAlasElips])
    B["Input: nama, a, b, tinggi"]
    C["Panggil super(nama, a, b)<br/>membuat Elips dan validasi sumbu"]
    D["setTinggi(tinggi)"]
    E{"tinggi <= 0?"}
    F["Throw IllegalArgumentException:<br/>Tinggi harus lebih dari 0"]
    G["this.tinggi = tinggi"]
    H([Objek KerucutDenganAlasElips siap])

    A --> B --> C --> D --> E
    E -- Ya --> F
    E -- Tidak --> G --> H
```

### Alur hitungLuas()

```mermaid
flowchart TD
    A([Mulai hitungLuas kerucut alas elips])
    B["luasAlas = super.hitungLuas(sumbuA, sumbuB)"]
    C["garisPelukisA = sqrt(sumbuA^2 + tinggi^2)"]
    D["garisPelukisB = sqrt(sumbuB^2 + tinggi^2)"]
    E["luasSelimut = PI * (sumbuA * garisPelukisB<br/>+ sumbuB * garisPelukisA) / 2"]
    F["Panggil hitungLuas(sumbuA, sumbuB, tinggi)"]
    G{"a, b, tinggi semuanya > 0?"}
    H["Throw IllegalArgumentException"]
    I["luasAlasHitung = PI * a * b"]
    J["garisPelukisAHitung = sqrt(a^2 + tinggi^2)"]
    K["garisPelukisBHitung = sqrt(b^2 + tinggi^2)"]
    L["luasSelimutHitung = PI * (a * garisPelukisBHitung<br/>+ b * garisPelukisAHitung) / 2"]
    M["luas2 = luasAlasHitung + luasSelimutHitung"]
    N["luas = luas2"]
    O([Selesai])

    A --> B --> C --> D --> E --> F --> G
    G -- Tidak --> H
    G -- Ya --> I --> J --> K --> L --> M --> N --> O
```

### Alur hitungKeliling() dan hitungVolume()

```mermaid
flowchart TD
    A([Mulai hitungKeliling])
    B["Panggil hitungKeliling(sumbuA, sumbuB)"]
    C{"sumbuA > 0 dan sumbuB > 0?"}
    D["Throw IllegalArgumentException"]
    E["keliling2 = keliling elips alas"]
    F["keliling = keliling2"]
    G([Selesai hitungKeliling])

    A --> B --> C
    C -- Tidak --> D
    C -- Ya --> E --> F --> G

    H([Mulai hitungVolume])
    I["luasAlas = super.hitungLuas(sumbuA, sumbuB)"]
    J["Panggil hitungVolume(sumbuA, sumbuB, tinggi)"]
    K{"sumbuA, sumbuB, tinggi semuanya > 0?"}
    L["Throw IllegalArgumentException"]
    M["volume2 = (1 / 3) * luasAlas * tinggi"]
    N["volume = volume2"]
    O([Selesai hitungVolume])

    H --> I --> J --> K
    K -- Tidak --> L
    K -- Ya --> M --> N --> O
```

### Alur cetakInfo()

```mermaid
flowchart TD
    A([Mulai cetakInfo kerucut alas elips])
    B["Panggil hitungSemua()"]
    C["Cetak nama benda"]
    D["Cetak sumbu alas a/b"]
    E["Cetak tinggi"]
    F["Cetak garisPelukisA dan garisPelukisB"]
    G["Cetak luasAlas dan luasSelimut"]
    H["Cetak luas permukaan"]
    I["Cetak volume kerucut"]
    J([Selesai])

    A --> B --> C --> D --> E --> F --> G --> H --> I --> J
```

---

## 4. KerucutTerpancungDenganAlasElips.java

### Alur Constructor

```mermaid
flowchart TD
    A([Mulai new KerucutTerpancungDenganAlasElips])
    B["Input: nama, a1, b1, a2, b2, tinggi"]
    C["Panggil super(nama, a1, b1, tinggi)<br/>validasi alas bawah dan tinggi"]
    D["setSumbuA2(a2)"]
    E{"a2 <= 0?"}
    F["Throw IllegalArgumentException:<br/>Sumbu A atas harus lebih dari 0"]
    G{"sumbuA2 < sumbuA bawah?"}
    H["Throw IllegalArgumentException:<br/>Sumbu A atas harus lebih kecil"]
    I["sumbuA2 = a2"]
    J["setSumbuB2(b2)"]
    K{"b2 <= 0?"}
    L["Throw IllegalArgumentException:<br/>Sumbu B atas harus lebih dari 0"]
    M{"sumbuB2 < sumbuB bawah?"}
    N["Throw IllegalArgumentException:<br/>Sumbu B atas harus lebih kecil"]
    O["sumbuB2 = b2"]
    P([Objek kerucut terpancung siap])

    A --> B --> C --> D --> E
    E -- Ya --> F
    E -- Tidak --> I --> G
    G -- Tidak --> H
    G -- Ya --> J --> K
    K -- Ya --> L
    K -- Tidak --> O --> M
    M -- Tidak --> N
    M -- Ya --> P
```

### Alur hitungLuas()

```mermaid
flowchart TD
    A([Mulai hitungLuas kerucut terpancung])
    B["luasAlasBawah = super.hitungLuas(sumbuA, sumbuB)"]
    C["luasAlasAtas = super.hitungLuas(sumbuA2, sumbuB2)"]
    D["kelilingBawah = super.hitungKeliling(sumbuA, sumbuB)"]
    E["kelilingAtas = super.hitungKeliling(sumbuA2, sumbuB2)"]
    F["diffA = sumbuA - sumbuA2"]
    G["diffB = sumbuB - sumbuB2"]
    H["garisPelukis = sqrt(tinggi^2 + (diffA^2 + diffB^2) / 2)"]
    I["luasSelimut = 0.5 * (kelilingBawah + kelilingAtas) * garisPelukis"]
    J["Panggil hitungLuas(a1, b1, a2, b2, tinggi)"]
    K{"Semua dimensi > 0<br/>dan a2 < a1<br/>dan b2 < b1?"}
    L["Throw IllegalArgumentException"]
    M["alasBawah = PI * a1 * b1"]
    N["alasAtas = PI * a2 * b2"]
    O["Hitung kelilingBawah dan kelilingAtas"]
    P["garisPelukisHitung = sqrt(tinggi^2 + (diffA^2 + diffB^2) / 2)"]
    Q["luas2 = alasBawah + alasAtas<br/>+ 0.5 * (kelilingBawah + kelilingAtas) * garisPelukisHitung"]
    R["luas = luas2"]
    S([Selesai])

    A --> B --> C --> D --> E --> F --> G --> H --> I --> J --> K
    K -- Tidak --> L
    K -- Ya --> M --> N --> O --> P --> Q --> R --> S
```

### Alur hitungKeliling() dan hitungVolume()

```mermaid
flowchart TD
    A([Mulai hitungKeliling])
    B["Panggil hitungKeliling(a1, b1, a2, b2)"]
    C{"Semua sumbu > 0<br/>dan a2 < a1<br/>dan b2 < b1?"}
    D["Throw IllegalArgumentException"]
    E["keliling2 = keliling elips bawah + keliling elips atas"]
    F["keliling = keliling2"]
    G([Selesai hitungKeliling])

    A --> B --> C
    C -- Tidak --> D
    C -- Ya --> E --> F --> G

    H([Mulai hitungVolume])
    I["Panggil hitungVolume(a1, b1, a2, b2, tinggi)"]
    J{"Semua dimensi > 0<br/>dan a2 < a1<br/>dan b2 < b1?"}
    K["Throw IllegalArgumentException"]
    L["alasBawah = PI * a1 * b1"]
    M["alasAtas = PI * a2 * b2"]
    N["volume2 = (tinggi / 3) * (alasBawah + alasAtas<br/>+ sqrt(alasBawah * alasAtas))"]
    O["volume = volume2"]
    P([Selesai hitungVolume])

    H --> I --> J
    J -- Tidak --> K
    J -- Ya --> L --> M --> N --> O --> P
```

### Alur cetakInfo()

```mermaid
flowchart TD
    A([Mulai cetakInfo kerucut terpancung])
    B["Panggil hitungSemua()"]
    C["Cetak nama benda"]
    D["Cetak sumbu bawah a1/b1"]
    E["Cetak sumbu atas a2/b2"]
    F["Cetak tinggi"]
    G["Cetak garisPelukis"]
    H["Cetak luasAlasBawah dan luasAlasAtas"]
    I["Cetak luasSelimut"]
    J["Cetak luas permukaan"]
    K["Cetak volume"]
    L([Selesai])

    A --> B --> C --> D --> E --> F --> G --> H --> I --> J --> K --> L
```

---

## 5. Bola.java

### Alur Constructor

```mermaid
flowchart TD
    A([Mulai new Bola])
    B["Input: nama, r"]
    C["Panggil super(nama)<br/>validasi nama"]
    D["setJariJari(r)"]
    E{"r <= 0?"}
    F["Throw IllegalArgumentException:<br/>Jari-jari harus lebih dari 0"]
    G["jariJari = r"]
    H["diameter = 2 * jariJari"]
    I([Objek Bola siap])

    A --> B --> C --> D --> E
    E -- Ya --> F
    E -- Tidak --> G --> H --> I
```

### Alur Perhitungan Bola

```mermaid
flowchart TD
    A([Mulai perhitungan Bola])

    A --> B["hitungLuas()"]
    B --> C["Panggil hitungLuas(jariJari)"]
    C --> D{"r > 0?"}
    D -- Tidak --> E["Throw IllegalArgumentException"]
    D -- Ya --> F["luas2 = 4 * PI * r^2"]
    F --> G["luas = luas2"]

    G --> H["hitungKeliling()"]
    H --> I["Panggil hitungKeliling(jariJari)"]
    I --> J{"r > 0?"}
    J -- Tidak --> K["Throw IllegalArgumentException"]
    J -- Ya --> L["keliling2 = 2 * PI * r"]
    L --> M["kelilingLingkaranBesar = keliling2"]
    M --> N["keliling = kelilingLingkaranBesar"]

    N --> O["hitungVolume()"]
    O --> P["Panggil hitungVolume(jariJari)"]
    P --> Q{"r > 0?"}
    Q -- Tidak --> R["Throw IllegalArgumentException"]
    Q -- Ya --> S["volume2 = (4 / 3) * PI * r^3"]
    S --> T["volume = volume2"]
    T --> U([Perhitungan selesai])
```

### Alur cetakInfo()

```mermaid
flowchart TD
    A([Mulai cetakInfo Bola])
    B["Panggil hitungSemua()"]
    C["Cetak nama benda"]
    D["Cetak jariJari dan diameter"]
    E["Cetak kelilingLingkaranBesar"]
    F["Cetak luas permukaan"]
    G["Cetak volume"]
    H([Selesai])

    A --> B --> C --> D --> E --> F --> G --> H
```

---

## 6. Cincin.java

### Alur Constructor

```mermaid
flowchart TD
    A([Mulai new Cincin])
    B["Input: nama, R, r"]
    C["Panggil super(nama, r)<br/>validasi r sebagai jari-jari Bola"]
    D["jariJariMinor = r"]
    E["setJariJariMayor(R)"]
    F{"R <= 0?"}
    G["Throw IllegalArgumentException:<br/>Jari-jari mayor harus lebih dari 0"]
    H{"jariJariMayor > jariJariMinor<br/>atau jariJariMinor == 0?"}
    I["Throw IllegalArgumentException:<br/>R harus lebih besar dari r"]
    J["jariJariMayor = R"]
    K([Objek Cincin siap])

    A --> B --> C --> D --> E --> F
    F -- Ya --> G
    F -- Tidak --> J --> H
    H -- Tidak --> I
    H -- Ya --> K
```

### Alur Perhitungan Cincin

```mermaid
flowchart TD
    A([Mulai perhitungan Cincin])

    A --> B["hitungLuas()"]
    B --> C["Panggil hitungLuas(R, r)"]
    C --> D{"R > 0 dan r > 0<br/>dan R > r?"}
    D -- Tidak --> E["Throw IllegalArgumentException"]
    D -- Ya --> F["luas2 = 4 * PI^2 * R * r"]
    F --> G["luas = luas2"]

    G --> H["hitungKeliling()"]
    H --> I["Panggil hitungKeliling(R)"]
    I --> J{"R > 0?"}
    J -- Tidak --> K["Throw IllegalArgumentException"]
    J -- Ya --> L["keliling2 = 2 * PI * R<br/>menggunakan Bola.hitungKeliling"]
    L --> M["keliling = keliling2"]

    M --> N["hitungVolume()"]
    N --> O["Panggil hitungVolume(R, r)"]
    O --> P{"R > 0 dan r > 0<br/>dan R > r?"}
    P -- Tidak --> Q["Throw IllegalArgumentException"]
    P -- Ya --> R["volume2 = 2 * PI^2 * R * r^2"]
    R --> S["volume = volume2"]
    S --> T([Perhitungan selesai])
```

### Alur Setter Minor dan cetakInfo()

```mermaid
flowchart TD
    A([Mulai setJariJariMinor(r)])
    B["Panggil super.setJariJari(r)"]
    C{"r <= 0?"}
    D["Throw IllegalArgumentException"]
    E["jariJariMinor = r"]
    F{"jariJariMayor == 0<br/>atau jariJariMayor > jariJariMinor?"}
    G["Throw IllegalArgumentException:<br/>R harus lebih besar dari r"]
    H([Setter selesai])

    A --> B --> C
    C -- Ya --> D
    C -- Tidak --> E --> F
    F -- Tidak --> G
    F -- Ya --> H

    I([Mulai cetakInfo Cincin])
    J["Panggil hitungSemua()"]
    K["Cetak nama benda"]
    L["Cetak jariJariMayor dan jariJariMinor"]
    M["Cetak keliling mayor"]
    N["Cetak luas cincin"]
    O["Cetak volume cincin"]
    P([Selesai])

    I --> J --> K --> L --> M --> N --> O --> P
```

---

## 7. Juring.java

### Alur Constructor

```mermaid
flowchart TD
    A([Mulai new Juring])
    B["Input: nama, r, h"]
    C["Panggil super(nama, r)<br/>validasi jari-jari bola"]
    D["setTinggiTopi(h)"]
    E{"h <= 0?"}
    F["Throw IllegalArgumentException:<br/>Tinggi topi harus lebih dari 0"]
    G{"tinggiTopi <= 2 * jariJari?"}
    H["Throw IllegalArgumentException:<br/>Tinggi topi maksimal 2 x jari-jari"]
    I["tinggiTopi = h"]
    J([Objek Juring siap])

    A --> B --> C --> D --> E
    E -- Ya --> F
    E -- Tidak --> I --> G
    G -- Tidak --> H
    G -- Ya --> J
```

### Alur Perhitungan Juring

```mermaid
flowchart TD
    A([Mulai perhitungan Juring])

    A --> B["hitungLuas()"]
    B --> C["jariJariAlas = sqrt(h * (2r - h))"]
    C --> D["Panggil hitungLuas(r, h)"]
    D --> E{"r > 0, h > 0,<br/>dan h <= 2r?"}
    E -- Tidak --> F["Throw IllegalArgumentException"]
    E -- Ya --> G["alas = sqrt(h * (2r - h))"]
    G --> H["luas2 = PI * alas^2 + PI * r * h"]
    H --> I["luas = luas2"]

    I --> J["hitungKeliling()"]
    J --> K["jariJariAlas = sqrt(h * (2r - h))"]
    K --> L["Panggil hitungKeliling(r, h)"]
    L --> M{"r > 0, h > 0,<br/>dan h <= 2r?"}
    M -- Tidak --> N["Throw IllegalArgumentException"]
    M -- Ya --> O["alas = sqrt(h * (2r - h))"]
    O --> P{"alas == 0?"}
    P -- Ya --> Q["keliling2 = 0"]
    P -- Tidak --> R["keliling2 = 2 * PI * alas"]
    Q --> S["keliling = keliling2"]
    R --> S

    S --> T["hitungVolume()"]
    T --> U["Panggil hitungVolume(r, h)"]
    U --> V{"r > 0, h > 0,<br/>dan h <= 2r?"}
    V -- Tidak --> W["Throw IllegalArgumentException"]
    V -- Ya --> X["volume2 = (2 / 3) * PI * r^2 * h"]
    X --> Y["volume = volume2"]
    Y --> Z([Perhitungan selesai])
```

### Alur cetakInfo()

```mermaid
flowchart TD
    A([Mulai cetakInfo Juring])
    B["Panggil hitungSemua()"]
    C["Cetak nama benda"]
    D["Cetak jariJari dan tinggiTopi"]
    E["Cetak jariJariAlas"]
    F["Cetak keliling alas"]
    G["Cetak luas juring"]
    H["Cetak volume juring"]
    I([Selesai])

    A --> B --> C --> D --> E --> F --> G --> H --> I
```

---

## 8. Tabung.java

### Alur Constructor

```mermaid
flowchart TD
    A([Mulai new Tabung])
    B["Input: nama, r, tinggi"]
    C["Panggil super(nama, r)<br/>validasi jari-jari alas"]
    D["setTinggi(tinggi)"]
    E{"tinggi <= 0?"}
    F["Throw IllegalArgumentException:<br/>Tinggi harus lebih dari 0"]
    G["this.tinggi = tinggi"]
    H([Objek Tabung siap])

    A --> B --> C --> D --> E
    E -- Ya --> F
    E -- Tidak --> G --> H
```

### Alur Perhitungan Tabung

```mermaid
flowchart TD
    A([Mulai perhitungan Tabung])

    A --> B["hitungLuas()"]
    B --> C["luasAlas = PI * r^2"]
    C --> D["luasSelimut = 2 * PI * r * tinggi"]
    D --> E["Panggil hitungLuas(r, tinggi)"]
    E --> F{"r > 0 dan tinggi > 0?"}
    F -- Tidak --> G["Throw IllegalArgumentException"]
    F -- Ya --> H["luasAlasHitung = PI * r^2"]
    H --> I["luasSelimutHitung = 2 * PI * r * tinggi"]
    I --> J["luas2 = 2 * luasAlasHitung + luasSelimutHitung"]
    J --> K["luas = luas2"]

    K --> L["hitungKeliling()"]
    L --> M["Panggil hitungKeliling(r)"]
    M --> N{"r > 0?"}
    N -- Tidak --> O["Throw IllegalArgumentException"]
    N -- Ya --> P["keliling2 = 2 * PI * r"]
    P --> Q["kelilingLingkaranBesar = keliling2"]
    Q --> R["keliling = kelilingLingkaranBesar"]

    R --> S["hitungVolume()"]
    S --> T["luasAlas = PI * r^2"]
    T --> U["Panggil hitungVolume(r, tinggi)"]
    U --> V{"r > 0 dan tinggi > 0?"}
    V -- Tidak --> W["Throw IllegalArgumentException"]
    V -- Ya --> X["volume2 = PI * r^2 * tinggi"]
    X --> Y["volume = volume2"]
    Y --> Z([Perhitungan selesai])
```

### Alur cetakInfo()

```mermaid
flowchart TD
    A([Mulai cetakInfo Tabung])
    B["Panggil hitungSemua()"]
    C["Cetak nama benda"]
    D["Cetak jari-jari alas dan tinggi"]
    E["Cetak luasAlas dan luasSelimut"]
    F["Cetak luas permukaan"]
    G["Cetak volume tabung"]
    H([Selesai])

    A --> B --> C --> D --> E --> F --> G --> H
```

---

## 9. Tembereng.java

### Alur Constructor

```mermaid
flowchart TD
    A([Mulai new Tembereng])
    B["Input: nama, r, h"]
    C["Panggil super(nama, r)<br/>validasi jari-jari bola"]
    D["setTinggiTopi(h)"]
    E{"h <= 0?"}
    F["Throw IllegalArgumentException:<br/>Tinggi topi harus lebih dari 0"]
    G{"tinggiTopi <= 2 * jariJari?"}
    H["Throw IllegalArgumentException:<br/>Tinggi topi maksimal 2 x jari-jari"]
    I["tinggiTopi = h"]
    J([Objek Tembereng siap])

    A --> B --> C --> D --> E
    E -- Ya --> F
    E -- Tidak --> I --> G
    G -- Tidak --> H
    G -- Ya --> J
```

### Alur Perhitungan Tembereng

```mermaid
flowchart TD
    A([Mulai perhitungan Tembereng])

    A --> B["hitungLuas()"]
    B --> C["jariJariAlas = sqrt(h * (2r - h))"]
    C --> D["luasSelimut = 2 * PI * r * h"]
    D --> E["luasAlas = PI * jariJariAlas^2"]
    E --> F["Panggil hitungLuas(r, h)"]
    F --> G{"r > 0, h > 0,<br/>dan h <= 2r?"}
    G -- Tidak --> H["Throw IllegalArgumentException"]
    G -- Ya --> I["alas = sqrt(h * (2r - h))"]
    I --> J["luas2 = 2 * PI * r * h + PI * alas^2"]
    J --> K["luas = luas2"]

    K --> L["hitungKeliling()"]
    L --> M["jariJariAlas = sqrt(h * (2r - h))"]
    M --> N["Panggil hitungKeliling(r, h)"]
    N --> O{"r > 0, h > 0,<br/>dan h <= 2r?"}
    O -- Tidak --> P["Throw IllegalArgumentException"]
    O -- Ya --> Q["alas = sqrt(h * (2r - h))"]
    Q --> R{"alas == 0?"}
    R -- Ya --> S["keliling2 = 0"]
    R -- Tidak --> T["keliling2 = 2 * PI * alas"]
    S --> U["keliling = keliling2"]
    T --> U

    U --> V["hitungVolume()"]
    V --> W["Panggil hitungVolume(r, h)"]
    W --> X{"r > 0, h > 0,<br/>dan h <= 2r?"}
    X -- Tidak --> Y["Throw IllegalArgumentException"]
    X -- Ya --> Z["volume2 = (PI * h^2 / 3) * (3r - h)"]
    Z --> AA["volume = volume2"]
    AA --> AB([Perhitungan selesai])
```

### Alur cetakInfo()

```mermaid
flowchart TD
    A([Mulai cetakInfo Tembereng])
    B["Panggil hitungSemua()"]
    C["Cetak nama benda"]
    D["Cetak jari-jari bola dan tinggiTopi"]
    E["Cetak jariJariAlas"]
    F["Cetak keliling alas"]
    G["Cetak luasSelimut dan luasAlas"]
    H["Cetak luas tembereng"]
    I["Cetak volume tembereng"]
    J([Selesai])

    A --> B --> C --> D --> E --> F --> G --> H --> I --> J
```

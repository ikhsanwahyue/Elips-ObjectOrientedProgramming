# Flowchart & UML Class Diagram
## Projek Akhir PBO 2 — Simulasi Hitung Benda Geometri Elips

---

## 1. UML Class Diagram

Diagram berikut menggambarkan seluruh hierarki class, interface, atribut, method, dan relasi antar class dalam project.

```mermaid
classDiagram
    direction TB

    class BendaGeometri {
        <<abstract>>
        -String namaBenda
        +BendaGeometri(String nama)
        +getNamaBenda() String
        +setNamaBenda(String namaBenda) void
        +cetakInfo()* void
    }

    class KalkulasiGeometri {
        <<interface>>
        +hitungLuas()* double
        +hitungKeliling()* double
        +hitungVolume()* double
    }

    class Elips {
        #double sumbuA
        #double sumbuB
        +Elips(String nama)
        +Elips(String nama, double a, double b)
        +getSumbuA() double
        +setSumbuA(double a) void
        +getSumbuB() double
        +setSumbuB(double b) void
        +hitungLuas() double
        +hitungKeliling() double
        +hitungVolume() double
        +cetakInfo() void
    }

    class Bola {
        #double jariJari
        +Bola()
        +Bola(String nama, double r)
        +getJariJari() double
        +setJariJari(double r) void
        +hitungLuas() double
        +hitungKeliling() double
        +hitungVolume() double
        +cetakInfo() void
    }

    class Tabung {
        -double jariJari
        -double tinggi
        +Tabung()
        +setJariJari(double r) void
        +setTinggi(double t) void
        +hitungLuas() double
        +hitungKeliling() double
        +hitungVolume() double
        +cetakInfo() void
    }

    class KerucutDenganAlasElips {
        -double tinggi
        +KerucutDenganAlasElips(String nama)
        +KerucutDenganAlasElips(String nama, double a, double b, double tinggi)
        +getTinggi() double
        +setTinggi(double tinggi) void
        -hitungSlantHeight1() double
        -hitungSlantHeight2() double
        +hitungLuas() double
        +hitungKeliling() double
        +hitungVolume() double
        +cetakInfo() void
    }

    class KerucutTerpancungDenganAlasElips {
        -double sumbuA2
        -double sumbuB2
        +KerucutTerpancungDenganAlasElips(String nama)
        +KerucutTerpancungDenganAlasElips(String nama, double a1, double b1, double a2, double b2, double tinggi)
        +getSumbuA2() double
        +setSumbuA2(double sumbuA2) void
        +getSumbuB2() double
        +setSumbuB2(double sumbuB2) void
        -hitungKelilingElips(double a, double b) double
        -hitungSlantHeight() double
        +hitungLuas() double
        +hitungKeliling() double
        +hitungVolume() double
        +cetakInfo() void
    }

    class Cincin {
        -double jariJariMayor
        +Cincin(String nama)
        +Cincin(String nama, double R, double r)
        +getJariJariMayor() double
        +setJariJariMayor(double R) void
        +hitungLuas() double
        +hitungKeliling() double
        +hitungVolume() double
        +cetakInfo() void
    }

    class Juring {
        -double tinggiTopi
        +Juring(String nama)
        +Juring(String nama, double r, double h)
        +getTinggiTopi() double
        +setTinggiTopi(double h) void
        -hitungJariJariAlas() double
        +hitungLuas() double
        +hitungKeliling() double
        +hitungVolume() double
        +cetakInfo() void
    }

    class Tembereng {
        -double tinggiTopi
        +Tembereng(String nama)
        +Tembereng(String nama, double r, double h)
        +getTinggiTopi() double
        +setTinggiTopi(double h) void
        -hitungJariJariAlas() double
        +hitungLuas() double
        +hitungKeliling() double
        +hitungVolume() double
        +cetakInfo() void
    }

    class KalkulasiThread {
        -BendaGeometri objekGeometri
        +KalkulasiThread(BendaGeometri bg)
        +run() void
    }

    class SimulasiHitung {
        -Scanner scanner$
        -ArrayList~BendaGeometri~ daftarBenda$
        +main(String[] args)$ void
        -tampilkanMenu()$ void
        -buatBendaGeometri()$ void
        -inputElips()$ Elips
        -inputBola()$ Bola
        -inputTabung()$ Tabung
        -inputKerucut()$ KerucutDenganAlasElips
        -inputKerucutTerpancung()$ KerucutTerpancungDenganAlasElips
        -inputCincin()$ Cincin
        -inputJuring()$ Juring
        -inputTembereng()$ Tembereng
        -lihatSemuaBenda()$ void
        -demoPolymorphism()$ void
        -demoMultithreading()$ void
        -bacaDouble()$ double
        -bacaInt()$ int
    }

    class GeometriGUI {
        -JComboBox~String~ comboBenda
        -JPanel panelInput
        -JPanel panelHasil
        -JTextField[] inputFields
        -JLabel[] inputLabels
        -JLabel lblLuas
        -JLabel lblKeliling
        -JLabel lblVolume
        -JButton btnHitung
        -JButton btnReset
        +GeometriGUI()
        -createHeader() JPanel
        -createInputPanel() JPanel
        -createHasilPanel() JPanel
        -updateInputFields() void
        -hitungHasil() void
        -resetForm() void
        +main(String[] args) void
    }

    %% === RELASI INHERITANCE ===
    BendaGeometri <|-- Elips : extends
    BendaGeometri <|-- Bola : extends
    BendaGeometri <|-- Tabung : extends
    Elips <|-- KerucutDenganAlasElips : extends
    KerucutDenganAlasElips <|-- KerucutTerpancungDenganAlasElips : extends
    Bola <|-- Cincin : extends
    Bola <|-- Juring : extends
    Bola <|-- Tembereng : extends
    Thread <|-- KalkulasiThread : extends
    JFrame <|-- GeometriGUI : extends

    %% === RELASI IMPLEMENTS ===
    KalkulasiGeometri <|.. Elips : implements
    KalkulasiGeometri <|.. Bola : implements
    KalkulasiGeometri <|.. Tabung : implements

    %% === RELASI DEPENDENCY / USES ===
    KalkulasiThread --> BendaGeometri : uses
    SimulasiHitung ..> BendaGeometri : creates
    SimulasiHitung ..> KalkulasiThread : creates
    SimulasiHitung ..> KalkulasiGeometri : uses
    GeometriGUI ..> Elips : creates
    GeometriGUI ..> Bola : creates
    GeometriGUI ..> Tabung : creates
    GeometriGUI ..> KerucutDenganAlasElips : creates
    GeometriGUI ..> KerucutTerpancungDenganAlasElips : creates
    GeometriGUI ..> Cincin : creates
    GeometriGUI ..> Juring : creates
    GeometriGUI ..> Tembereng : creates
```

### Keterangan Notasi UML

| Simbol | Arti |
|--------|------|
| `+` | `public` |
| `-` | `private` |
| `#` | `protected` |
| `$` | `static` |
| `*` | `abstract` |
| `<<abstract>>` | Abstract Class |
| `<<interface>>` | Interface |
| `<\|--` (garis penuh) | Inheritance (`extends`) |
| `<\|..` (garis putus) | Implementation (`implements`) |
| `-->` | Association / Uses |
| `..>` | Dependency / Creates |

### Ringkasan Hierarki Inheritance

| Level | Class | Parent |
|-------|-------|--------|
| 0 | `BendaGeometri` *(abstract)* | — |
| 1 | `Elips` | `BendaGeometri` |
| 1 | `Bola` | `BendaGeometri` |
| 1 | `Tabung` | `BendaGeometri` |
| 2 | `KerucutDenganAlasElips` | `Elips` |
| 2 | `Cincin` | `Bola` |
| 2 | `Juring` | `Bola` |
| 2 | `Tembereng` | `Bola` |
| 3 | `KerucutTerpancungDenganAlasElips` | `KerucutDenganAlasElips` |

---

## 2. Flowchart — Alur Program Utama (SimulasiHitung)

```mermaid
flowchart TD
    A([🟢 START]) --> B["Inisialisasi Scanner & ArrayList daftarBenda"]
    B --> C["Tampilkan Menu Utama"]
    C --> D{"Pilih Menu\n[1-5]"}

    D -->|"1"| E["Buat Benda Geometri Baru"]
    D -->|"2"| F["Lihat Semua Benda"]
    D -->|"3"| G["Demo Polymorphism"]
    D -->|"4"| H["Demo Multithreading"]
    D -->|"5"| I["Tampilkan Pesan Keluar"]

    %% === MENU 1: Buat Benda ===
    E --> E1{"Pilih Jenis\nBenda [1-8]"}
    E1 -->|"1"| E2["Input Sumbu A & B\n→ new Elips()"]
    E1 -->|"2"| E3["Input Jari-jari r\n→ new Bola()"]
    E1 -->|"3"| E4["Input r & t\n→ new Tabung()"]
    E1 -->|"4"| E5["Input a, b, t\n→ new KerucutDenganAlasElips()"]
    E1 -->|"5"| E6["Input a1, b1, a2, b2, t\n→ new KerucutTerpancungDgnAlasElips()"]
    E1 -->|"6"| E7["Input R & r\n→ new Cincin()"]
    E1 -->|"7"| E8["Input r & h\n→ new Juring()"]
    E1 -->|"8"| E9["Input r & h\n→ new Tembereng()"]
    E1 -->|"invalid"| E10["Tampilkan Error"]

    E2 --> EA["daftarBenda.add(benda)"]
    E3 --> EA
    E4 --> EA
    E5 --> EA
    E6 --> EA
    E7 --> EA
    E8 --> EA
    E9 --> EA
    EA --> EB["benda.cetakInfo()"]
    EB --> C
    E10 --> C

    %% === MENU 2: Lihat Semua ===
    F --> F1{"daftarBenda\nkosong?"}
    F1 -->|"Ya"| F2["Tampilkan Peringatan"]
    F1 -->|"Tidak"| F3["Loop: benda.cetakInfo()\nuntuk setiap benda"]
    F2 --> C
    F3 --> C

    %% === MENU 3: Polymorphism ===
    G --> G1{"daftarBenda\nkosong?"}
    G1 -->|"Ya"| G2["Tampilkan Peringatan"]
    G1 -->|"Tidak"| G3["Loop setiap benda:"]
    G3 --> G4{"instanceof\nKalkulasiGeometri?"}
    G4 -->|"Ya"| G5["Cast ke KalkulasiGeometri\nhitungLuas() / hitungKeliling() / hitungVolume()"]
    G4 -->|"Tidak"| G6["Skip benda ini"]
    G5 --> G7["Tampilkan Hasil"]
    G6 --> G7
    G7 --> C
    G2 --> C

    %% === MENU 4: Multithreading ===
    H --> H1{"daftarBenda\nkosong?"}
    H1 -->|"Ya"| H2["Tampilkan Peringatan"]
    H1 -->|"Tidak"| H3["Loop: new KalkulasiThread(benda).start()"]
    H3 --> H4["Tunggu semua thread selesai\nthread.join()"]
    H4 --> H5["Tampilkan 'Semua Thread Selesai'"]
    H5 --> C
    H2 --> C

    %% === MENU 5: Keluar ===
    I --> J["scanner.close()"]
    J --> K([🔴 END])

    %% Styling
    style A fill:#22c55e,stroke:#16a34a,color:#fff
    style K fill:#ef4444,stroke:#dc2626,color:#fff
    style D fill:#3b82f6,stroke:#2563eb,color:#fff
    style E1 fill:#8b5cf6,stroke:#7c3aed,color:#fff
    style F1 fill:#f59e0b,stroke:#d97706,color:#fff
    style G1 fill:#f59e0b,stroke:#d97706,color:#fff
    style G4 fill:#f59e0b,stroke:#d97706,color:#fff
    style H1 fill:#f59e0b,stroke:#d97706,color:#fff
```

---

## 3. Flowchart — Alur GUI (GeometriGUI)

```mermaid
flowchart TD
    A([🟢 START GUI]) --> B["Inisialisasi JFrame GeometriGUI\nSet Dark Theme"]
    B --> C["Render Header + Panel Input + Panel Hasil"]
    C --> D["User memilih benda dari JComboBox"]
    D --> E["updateInputFields()\nTampilkan field input sesuai jenis benda"]
    E --> F{"User klik\ntombol mana?"}

    F -->|"Hitung"| G{"Benda sudah\ndipilih?"}
    F -->|"Reset"| L["resetForm()\nKosongkan semua field & hasil"]

    G -->|"Belum"| H["Tampilkan Warning Dialog"]
    G -->|"Sudah"| I["Baca nilai dari inputFields"]

    I --> J{"Input\nvalid?"}
    J -->|"Ya"| K["Buat objek sesuai jenis benda\nhitungLuas() / hitungKeliling() / hitungVolume()"]
    J -->|"Tidak"| M["Tampilkan Error Dialog"]

    K --> N["Update Label Hasil:\nlblLuas, lblKeliling, lblVolume"]

    H --> D
    M --> D
    N --> D
    L --> D

    style A fill:#22c55e,stroke:#16a34a,color:#fff
    style G fill:#3b82f6,stroke:#2563eb,color:#fff
    style J fill:#f59e0b,stroke:#d97706,color:#fff
    style F fill:#8b5cf6,stroke:#7c3aed,color:#fff
```

---

## 4. Flowchart — Alur Multithreading (KalkulasiThread)

```mermaid
flowchart TD
    A([🟢 Thread.start]) --> B["run() dipanggil"]
    B --> C["Cetak: Memulai kalkulasi untuk objekGeometri.getNamaBenda()"]
    C --> D["Thread.sleep 1500ms\nSimulasi proses delay"]
    D --> E{"InterruptedException?"}
    E -->|"Tidak"| F["objekGeometri.cetakInfo()\nPolymorphism: method sesuai tipe objek"]
    E -->|"Ya"| G["Cetak Error: Thread terganggu"]
    F --> H["Cetak: Kalkulasi selesai"]
    H --> I([🔴 Thread Selesai])
    G --> I

    style A fill:#22c55e,stroke:#16a34a,color:#fff
    style I fill:#ef4444,stroke:#dc2626,color:#fff
    style E fill:#f59e0b,stroke:#d97706,color:#fff
```

---

## 5. Penerapan 5 Pilar PBO dalam Diagram

| Pilar PBO | Penjelasan di Diagram |
|-----------|----------------------|
| **Encapsulation** | Atribut `private` (`-`) dan `protected` (`#`) dengan getter/setter `public` (`+`) di setiap class |
| **Inheritance** | Garis `extends`: BendaGeometri → Elips → KerucutDenganAlasElips → KerucutTerpancungDgnAlasElips, dan BendaGeometri → Bola → Cincin/Juring/Tembereng |
| **Overloading** | Setiap subclass memiliki 2 constructor: default (1 parameter) dan lengkap (parameter penuh) |
| **Overriding & Polymorphism** | Method `hitungLuas()`, `hitungKeliling()`, `hitungVolume()`, `cetakInfo()` di-override di setiap subclass. Di flowchart, terlihat pada loop Polymorphism yang memanggil method yang sama untuk objek berbeda |
| **Multithreading** | `KalkulasiThread extends Thread` menjalankan `cetakInfo()` secara paralel dengan `Thread.sleep()` di flowchart multithreading |

---

## 6. Package Diagram

```mermaid
graph LR
    subgraph "pElips.model"
        BG["BendaGeometri\n(abstract)"]
        KG["KalkulasiGeometri\n(interface)"]
        EL[Elips]
        BO[Bola]
        TB[Tabung]
        KE[KerucutDenganAlasElips]
        KT[KerucutTerpancungDenganAlasElips]
        CI[Cincin]
        JU[Juring]
        TE[Tembereng]
        KTH[KalkulasiThread]
    end

    subgraph "pElips.proses"
        SH[SimulasiHitung]
    end

    subgraph "pElips.gui"
        GUI[GeometriGUI]
    end

    SH -->|"uses"| BG
    SH -->|"uses"| KTH
    GUI -->|"uses"| EL
    GUI -->|"uses"| BO
    GUI -->|"uses"| TB

    style BG fill:#3b82f6,color:#fff
    style KG fill:#8b5cf6,color:#fff
    style SH fill:#22c55e,color:#fff
    style GUI fill:#f59e0b,color:#fff
```

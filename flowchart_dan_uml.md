# Flowchart dan UML Class Diagram

## UML Class Diagram

```mermaid
classDiagram
    direction TB

    class Runnable {
        <<interface>>
        +run() void
    }

    class BendaGeometri {
        <<abstract>>
        -String namaBenda
        #double luas
        #double keliling
        #double volume
        -int progress
        -String statusProses
        +hitungLuas()* double
        +hitungKeliling()* double
        +hitungVolume()* double
        +hitungSemua() void
        +run() void
        +cetakInfo()* void
    }

    class Elips {
        #double sumbuA
        #double sumbuB
    }

    class KerucutDenganAlasElips {
        -double tinggi
        -double garisPelukisA
        -double garisPelukisB
        -double luasAlas
        -double luasSelimut
    }

    class KerucutTerpancungDenganAlasElips {
        -double sumbuA2
        -double sumbuB2
        -double luasAlasBawah
        -double luasAlasAtas
        -double luasSelimut
        -double garisPelukis
    }

    class Bola {
        #double jariJari
        #double diameter
        #double kelilingLingkaranBesar
    }

    class Juring {
        -double tinggiTopi
        -double jariJariAlas
    }

    class Tembereng {
        -double tinggiTopi
        -double jariJariAlas
        -double luasSelimut
        -double luasAlas
    }

    class Cincin {
        -double jariJariMayor
        -double jariJariMinor
    }

    class Tabung {
        -double tinggi
        -double luasAlas
        -double luasSelimut
    }

    class Main {
        +main(String[] args)$ void
    }

    class MainFrame {
        -JTable tblData
        -BarRacePanel barRacePanel
        +main(String[] args)$ void
    }

    class BarRacePanel {
        -int[] nilai
        -int[] nilaiSebelumnya
        -int totalSelesai
        +setData(int[] nilaiBaru, int totalSelesai, long durasiMs, boolean berjalan) void
    }

    Runnable <|.. BendaGeometri : implements
    BendaGeometri <|-- Elips : extends
    Elips <|-- KerucutDenganAlasElips : extends
    KerucutDenganAlasElips <|-- KerucutTerpancungDenganAlasElips : extends
    BendaGeometri <|-- Bola : extends
    Bola <|-- Juring : extends
    Bola <|-- Tembereng : extends
    Bola <|-- Cincin : extends
    Bola <|-- Tabung : extends
    JFrame <|-- MainFrame : extends
    JPanel <|-- BarRacePanel : extends
    Main ..> MainFrame : opens
    MainFrame *-- BarRacePanel : visualizes
    MainFrame ..> BendaGeometri : creates and runs
```

## Flowchart Program Utama

```mermaid
flowchart TD
    A([START]) --> B["pElips.Main sebagai dirijen"]
    B --> C["Buka MainFrame di Event Dispatch Thread"]
    C --> D{"User memilih aksi"}
    D -->|"Hitung manual"| E["Baca input dan validasi dimensi"]
    E --> F["Buat objek BendaGeometri sesuai pilihan"]
    F --> G["Panggil hitungSemua()"]
    G --> H["Tampilkan luas, keliling, volume, dan atribut penting"]
    H --> D
    D -->|"Demo multithreading"| I["Buat 100.000 objek untuk setiap jenis benda"]
    I --> J["Total 800.000 data ditampilkan di JTable"]
    J --> K["Bagi data ke 8 worker berdasarkan kategori benda"]
    K --> L["Setiap worker memanggil run() pada objek geometri kategorinya"]
    L --> M["Update visualisasi batang berjalan per kategori"]
    M --> N{"Semua data selesai?"}
    N -->|"Belum"| M
    N -->|"Ya"| O["Tampilkan durasi dan status selesai"]
    O --> D
```

## Catatan Revisi

- Interface kalkulasi terpisah dihapus; method hitung berada di abstract class `BendaGeometri`.
- Class thread terpisah dihapus; proses paralel memakai objek geometri sebagai `Runnable`.
- Multithreading memakai `Runnable` dari setiap objek geometri.
- `Tabung` sekarang mengikuti struktur revisi: `BendaGeometri -> Bola -> Tabung`.
- GUI utama berada di package `pElips.gui`.

# Elips - Object Oriented Programming Project

Project ini mendemonstrasikan penerapan konsep OOP Java pada benda geometri:

- `Elips` sebagai benda 2D.
- `KerucutDenganAlasElips` dan `KerucutTerpancungDenganAlasElips` sebagai turunan dari `Elips`.
- `Bola` sebagai parent untuk `Juring`, `Tembereng`, `Cincin`, dan `Tabung`.
- `BendaGeometri` sebagai abstract class utama yang `implements Runnable`.

## Struktur Utama

- Tidak ada lagi interface kalkulasi terpisah.
- Tidak ada lagi class thread terpisah.
- Setiap objek geometri menyimpan atribut hasil seperti `luas`, `keliling`, `volume`, `progress`, dan `statusProses`.
- Multithreading menjalankan objek geometri langsung melalui method `run()` dari `BendaGeometri`.
- Entry point project adalah `pElips.Main`, yang membuka GUI utama `pElips.gui.MainFrame`.

## Fitur

- Hitung manual untuk semua benda geometri.
- Validasi input angka positif dan relasi dimensi penting.
- Demo polymorphism melalui referensi `BendaGeometri`.
- Demo multithreading 100.000 data per benda geometri, total 800.000 data, dengan tabel dan visualisasi batang berjalan.

## Cara Menjalankan

Di NetBeans, jalankan project langsung karena `main.class` sudah diarahkan ke:

```text
pElips.Main
```

Atau compile manual lewat PowerShell:

```powershell
$out = Join-Path $env:TEMP "elips-oop-compile"
New-Item -ItemType Directory -Force -Path $out | Out-Null
javac -encoding UTF-8 -d $out (Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })
java -cp $out pElips.Main
```

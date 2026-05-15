/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 * Class KalkulasiThread - Menjalankan kalkulasi geometri dalam thread terpisah.
 * 
 * Pilar: MULTITHREADING
 * Menggunakan Thread untuk simulasi proses kalkulasi secara paralel.
 * Menerima semua subclass BendaGeometri (Polymorphism).
 *
 * @author LENOVO
 */
public class KalkulasiThread extends Thread {
    private BendaGeometri objekGeometri;

    public KalkulasiThread(BendaGeometri bg) {
        this.objekGeometri = bg;
    }

    @Override
    public void run() {
        try {
            System.out.println("\n[Thread] Memulai kalkulasi untuk: " + objekGeometri.getNamaBenda());
            // Simulasi proses delay selama 1.5 detik
            Thread.sleep(1500); 
            objekGeometri.cetakInfo();
            System.out.println("[Thread] Kalkulasi selesai untuk: " + objekGeometri.getNamaBenda() + "\n");
        } catch (InterruptedException ex) {
            System.err.println("Thread terganggu: " + ex.getMessage());
        }
    }
}

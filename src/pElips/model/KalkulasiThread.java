/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 *
 * @author LENOVO
 */
public class KalkulasiThread extends Thread {
    private Elips objekElips;

    public KalkulasiThread(Elips e) {
        this.objekElips = e;
    }

    @Override
    public void run() {
        try {
            System.out.println("\n[System] Memulai kalkulasi thread untuk: " + objekElips.getNamaBenda());
            // Simulasi proses delay selama 1.5 detik
            Thread.sleep(1500); 
            objekElips.cetakInfo();
            System.out.println("[System] Kalkulasi selesai.\n");
        } catch (InterruptedException ex) {
            System.err.println("Thread terganggu: " + ex.getMessage());
        }
    }
}

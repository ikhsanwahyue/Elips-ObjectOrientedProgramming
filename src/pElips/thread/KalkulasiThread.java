package pElips.thread;

import pElips.model.BendaGeometri;
import java.util.List;

public class KalkulasiThread extends Thread {
    private List<BendaGeometri> subDaftar;
    private int idThread;

    public KalkulasiThread(int idThread, List<BendaGeometri> subDaftar) {
        this.idThread = idThread;
        this.subDaftar = subDaftar;
    }

    @Override
    public void run() {
        for (BendaGeometri benda : subDaftar) {
            for (int i = 0; i < 50; i++) {
                benda.hitungVolume();
                benda.hitungLuas();
            }
        }
        System.out.println("[Thread-" + idThread + "] Selesai memproses " + subDaftar.size() + " data.");
    }
}
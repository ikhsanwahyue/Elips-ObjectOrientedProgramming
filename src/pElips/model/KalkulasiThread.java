package pElips.model;

public class KalkulasiThread extends Thread {
    private BendaGeometri objekGeometri;

    public KalkulasiThread(BendaGeometri bg) {
        this.objekGeometri = bg;
    }

    @Override
    public void run() {
        try {
            System.out.println("\n[Thread] Memulai kalkulasi untuk: " + objekGeometri.getNamaBenda());
            Thread.sleep(1500); 
            objekGeometri.cetakInfo();
            System.out.println("[Thread] Kalkulasi selesai untuk: " + objekGeometri.getNamaBenda() + "\n");
        } catch (InterruptedException ex) {
            System.err.println("Thread terganggu: " + ex.getMessage());
        }
    }
}

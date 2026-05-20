package pElips.model;

public abstract class BendaGeometri implements KalkulasiGeometri {
    private String namaBenda;

    public BendaGeometri(String namaBenda) {
        this.namaBenda = namaBenda;
    }

    public String getNamaBenda() {
        return namaBenda;
    }

    public void setNamaBenda(String namaBenda) {
        this.namaBenda = namaBenda;
    }

    public abstract void cetakInfo();
}
package pElips.model;

public abstract class BendaGeometri {
    
    private String namaBenda;

    public BendaGeometri(String nama) {
        this.namaBenda = nama;
    }

    public String getNamaBenda() {
        return namaBenda;
    }

    public void setNamaBenda(String namaBenda) {
        this.namaBenda = namaBenda;
    }

    public abstract void cetakInfo();
}
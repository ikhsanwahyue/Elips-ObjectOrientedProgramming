/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 *
 * @author LENOVO
 */
public abstract class BendaGeometri {
    // Encapsulation: Attribute private agar tidak bisa diakses langsung dari luar package
    private String namaBenda;

    // Constructor
    public BendaGeometri(String nama) {
        this.namaBenda = nama;
    }

    // Getter untuk akses data (Information Hiding)
    public String getNamaBenda() {
        return namaBenda;
    }

    // Setter untuk mengubah data
    public void setNamaBenda(String namaBenda) {
        this.namaBenda = namaBenda;
    }

    // Method abstract yang akan diatur perilakunya oleh subclass
    public abstract void cetakInfo();
}

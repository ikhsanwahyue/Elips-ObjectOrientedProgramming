/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.model;

/**
 *
 * @author LENOVO
 */
public class Bola extends BendaGeometri implements KalkulasiGeometri {
    protected double jariJari;

    public Bola() {
        // setNamaBenda("Bola");
        super("Bola");
    }

    public void setJariJari(double r) {
        this.jariJari = r;
    }

    @Override
    public double hitungLuas() {
        return 4 * Math.PI * Math.pow(jariJari, 2);
    }

    @Override
    public double hitungKeliling() {
        return 0;
    }

    @Override
    public double hitungVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(jariJari, 3);
    }

    @Override
    public void cetakInfo() {
        System.out.println("Nama: " + getNamaBenda());
        System.out.println("Volume: " + hitungVolume());
    }
}
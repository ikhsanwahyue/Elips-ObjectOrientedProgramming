package pElips;

import pElips.gui.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main bertugas sebagai dirijen: menyiapkan aplikasi lalu membuka GUI utama.");
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
package pElips;

import javax.swing.SwingUtilities;
import pElips.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main bertugas sebagai dirijen: menyiapkan aplikasi lalu membuka GUI utama.");
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

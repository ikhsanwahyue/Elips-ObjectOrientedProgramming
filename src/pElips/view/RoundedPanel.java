/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pElips.view;

/**
 *
 * @author LENOVO
 */
public class RoundedPanel extends JPanel {
    private int cornerRadius = 30; // Tingkat kelengkungan sudut
    private Color backgroundColor = new Color(230, 230, 230); // Warna abu-abu terang

    public RoundedPanel() {
        setOpaque(false); // Agar area di luar lengkungan jadi transparan
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Agar gambar halus (anti-aliasing)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Menggambar background melengkung
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        
        // Menggambar garis pinggir (Border) hitam
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2)); // Ketebalan garis 2px
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    }
}
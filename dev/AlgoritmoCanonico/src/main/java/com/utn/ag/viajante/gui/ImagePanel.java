package com.utn.ag.viajante.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import com.utn.ag.viajante.model.Vector2D;
import javax.swing.ImageIcon;

class ImagePanel extends JPanel {

    private final int radioContadores = 5;

    private static final String IMG_PATH_ARGENTINA = "src/main/java/com/utn/ag/viajante/gui/mapa1Argentina.jpg";
    public static final Image IMG_ARGENTINA = new ImageIcon(IMG_PATH_ARGENTINA).getImage();
    private static final String IMG_PATH_STA_FE = "src/main/java/com/utn/ag/viajante/gui/mapaStaFe.jpg";
    public static final Image IMG_STA_FE = new ImageIcon(IMG_PATH_STA_FE).getImage();

    private int[] ciudades;
    private Vector2D[] coordenadasCiudades;
    private Image img;
    private Dimension size;
    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 100;
    private int y2 = 100;

    public int temporal = 1;

    public ImagePanel(Image img, Vector2D[] coordenadasCiudades) {
        this.img = img;
        this.coordenadasCiudades = coordenadasCiudades;
        init();
    }

    private void init() {
        size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void dibujarRecorrido(int[] ciudades) {
        this.ciudades = ciudades;
        repaint();
    }

    public void clearMap() {
        this.ciudades = null;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        paintCiudades(g);
    }

    private void paintCiudades(Graphics g) {
        if (ciudades == null || ciudades.length < 1) {
            return;
        }
        g.setColor(Color.black);

        for (int i = 1; i < ciudades.length; i++) {
            x1 = coordenadasCiudades[i].getX();
            y1 = coordenadasCiudades[i].getY();
            x2 = coordenadasCiudades[i - 1].getX();
            y2 = coordenadasCiudades[i - 1].getY();
            g.drawLine(x1, y1, x2, y2);
        }

        g.drawLine(coordenadasCiudades[ciudades.length - 1].getX(), coordenadasCiudades[ciudades.length - 1].getY(),
                coordenadasCiudades[ciudades[0]].getX(), coordenadasCiudades[ciudades[0]].getY());

        g.setColor(Color.green);
        for (int c : ciudades) {
            g.fillOval(coordenadasCiudades[c].getX() - 2, coordenadasCiudades[c].getY() - 2, radioContadores, radioContadores);
        }

        g.setColor(Color.red);
        g.fillOval(coordenadasCiudades[ciudades[0]].getX() - 2, coordenadasCiudades[ciudades[0]].getY() - 2, radioContadores, radioContadores);
    }

    public static BufferedImage imageToBufferedImage(Image im) {
        BufferedImage bi = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }

}

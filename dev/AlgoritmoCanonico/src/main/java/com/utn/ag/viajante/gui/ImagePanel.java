package com.utn.ag.viajante.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import com.utn.ag.viajante.model.Constants;
import javax.swing.ImageIcon;

class ImagePanel extends JPanel {
    
    private final int radioContadores = 5;
    private int[] ciudades;
    private static final String IMG_PATH = "src/main/java/com/utn/ag/viajante/gui/mapa1Argentina.jpg";
    private final Image img = new ImageIcon(IMG_PATH).getImage();
    private final Dimension size;
    private int height = 691;
    private int width = 317;
    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 100;
    private int y2 = 100;

    public int temporal = 1;

    public void dibujarRecorrido(int[] ciudades) {
        this.ciudades = ciudades;
        repaint();
    }

    public void clearMap() {
        this.ciudades = null;
        repaint();
    }

    public ImagePanel() {
        size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, width, height);
        g.drawImage(img, 0, 0, width, height, x1, y1, x2, y2, null);
        g.drawImage(img, 0, 0, width, height, 0, 0, size.width, size.height, null);
        paintCiudades(g);
    }

    private void paintCiudades(Graphics g) {
        if (ciudades == null || ciudades.length < 1) {
            return;
        }
        g.setColor(Color.black);

        for (int i = 1; i < ciudades.length; i++) {
            x1 = Constants.COORENADAS_CIUDADES[i].getX();
            y1 = Constants.COORENADAS_CIUDADES[i].getY();
            x2 = Constants.COORENADAS_CIUDADES[i - 1].getX();
            y2 = Constants.COORENADAS_CIUDADES[i - 1].getY();
            g.drawLine(x1, y1, x2, y2);
        }

        g.drawLine(Constants.COORENADAS_CIUDADES[ciudades.length - 1].getX(), Constants.COORENADAS_CIUDADES[ciudades.length - 1].getY(),
                Constants.COORENADAS_CIUDADES[ciudades[0]].getX(), Constants.COORENADAS_CIUDADES[ciudades[0]].getY());

        g.setColor(Color.green);
        for (int c : ciudades) {
            g.fillOval(Constants.COORENADAS_CIUDADES[c].getX() - 2, Constants.COORENADAS_CIUDADES[c].getY() - 2, radioContadores, radioContadores);
        }

        g.setColor(Color.red);
        g.fillOval(Constants.COORENADAS_CIUDADES[ciudades[0]].getX() - 2, Constants.COORENADAS_CIUDADES[ciudades[0]].getY() - 2, radioContadores, radioContadores);
    }

    public static BufferedImage imageToBufferedImage(Image im) {
        BufferedImage bi = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }

}

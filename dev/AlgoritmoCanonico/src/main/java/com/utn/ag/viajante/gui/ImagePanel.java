package com.utn.ag.viajante.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class ImagePanel extends JPanel {

	int radiocontadores = 16;
	
static Image snapshot;	
	
  private Image img;
  Dimension size;
  
  int height = 691;
  int width= 317;
  int x1= 0;
  int y1= 0;
  int x2= 100;
  int y2= 100;

  
  public int temporal = 1;

  /*
  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }

 public void UpdateScale (int x){
//	 super.paintComponent(null);
	temporal = temporal + 50;
	//this.
	 this.repaint();
	 
 } */
 
 


 
 
  
  public ImagePanel(Image img) {
    this.img = img;
    size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
    
  }

  public void paintComponent(Graphics g) {
	  super.paintComponent(g);
	  
	  g.drawRect(0, 0, width, height);
    g.drawImage(img, 0, 0, width, height , x1,y1,x2,y2, null);
	  g.drawImage(img, 0, 0 ,width, height ,0, 0, size.width ,size.height, null); 
	 
	
	  
	  
	  snapshot = img.getScaledInstance(width,  height, Image.SCALE_SMOOTH);
	
	
	  
	    
		  Font font = new Font("Times New Roman", Font.BOLD , 12 );

		  
		 /* originalmentSi
		  for (int i = 0 ; i< 50 ; i++){
			  g.setColor( Tablero.paises[i].getOwner().getColor() );
			  g.fillOval(Tablero.paises[i].getArmyX() - radiocontadores/2, Tablero.paises[i].getArmyY() -radiocontadores/2  , radiocontadores ,radiocontadores );
			  
			  g.setColor(Color.gray);
			  g.setFont(font);
			  g.drawString(Integer.toString(Tablero.paises[i].getTrops()) ,Tablero.paises[i].getArmyX() -2    , Tablero.paises[i].getArmyY() + 4  );
			
		  }*/
	   
	    
	      
	  
	  
  }
  
  /*
  public void repaint (Graphics g){
	  super.repaint();
	  
  }*/
  


  

  
  /*
  public void refrescarDibujo(){
	  Graphics g = this.getGraphics();
	  g.setColor(Color.BLACK);
	  g.fillOval(50,50,20,20);
	  
  }*/
 
  
  

  public static BufferedImage imageToBufferedImage(Image im) {
	     BufferedImage bi = new BufferedImage
	        (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
	     Graphics bg = bi.getGraphics();
	     bg.drawImage(im, 0, 0, null);
	     bg.dispose();
	     return bi;
	  }
  
  public static int GetPixelColor (int x, int y){
	 
	 /* if( x >= Tablero.panel.getWidth() || x >= Tablero.panel.getHeight() ){
		  
		  return -1;
	  }*/
	  
	  
	 return ImagePanel.imageToBufferedImage(ImagePanel.snapshot).getRGB(x,y);
 
  
  }
  
 
  
}

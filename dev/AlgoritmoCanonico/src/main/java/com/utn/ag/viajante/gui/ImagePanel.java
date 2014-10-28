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

import com.utn.ag.viajante.model.Constants;

class ImagePanel extends JPanel {

	int radiocontadores = 5;
	
static Image snapshot;	

//int[] ciudades = new int[23];

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
 
 
public void dibujarRecorrido(int[] ciudades){
	pintarRecorrido(ciudades);
	//this.ciudades = ciudades;
	//this.repaint();
}

  
  
  
  public void pintarRecorrido(int[] ciudades){
	  
	  Graphics g = this.getGraphics();
      g.setColor(Color.black);
      
      
      
      for (int i = 1 ; i < ciudades.length  ; i++){
      
      x1= Constants.COORENADAS_CIUDADES[i].getX();
      y1= Constants.COORENADAS_CIUDADES[i].getY();
      
      x2= Constants.COORENADAS_CIUDADES[i-1].getX();
      y2= Constants.COORENADAS_CIUDADES[i-1].getY();
    	  
    	  
    	  
    	  
    	  
    	  g.drawLine(x1, y1, x2, y2);
      
      }
      
      
      g.drawLine(Constants.COORENADAS_CIUDADES[ciudades.length-1].getX(), Constants.COORENADAS_CIUDADES[ciudades.length-1].getY() ,
    		      
    		  Constants.COORENADAS_CIUDADES[ciudades[0]].getX(), Constants.COORENADAS_CIUDADES[ciudades[0]].getY() );
      
      
      
      g.setColor(Color.green);  
      
      for (int c :ciudades){
    	  
    	  g.fillOval(Constants.COORENADAS_CIUDADES[c].getX() - 2, Constants.COORENADAS_CIUDADES[c].getY() - 2, radiocontadores, radiocontadores);  
    	  
      }
	  
      g.setColor(Color.red);     
      g.fillOval(Constants.COORENADAS_CIUDADES[ciudades[0]].getX() - 2, Constants.COORENADAS_CIUDADES[ciudades[0]].getY() - 2, radiocontadores, radiocontadores);
	  
	  
  }
 
  
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
	//  pintarRecorrido(ciudades);
	  g.drawRect(0, 0, width, height);
    g.drawImage(img, 0, 0, width, height , x1,y1,x2,y2, null);
	  g.drawImage(img, 0, 0 ,width, height ,0, 0, size.width ,size.height, null); 
	 
	
	  
	  
	  //snapshot = img.getScaledInstance(width,  height, Image.SCALE_SMOOTH);
	
	
	  
	    
		//  Font font = new Font("Times New Roman", Font.BOLD , 12 );

	  
	  
		  
		  
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

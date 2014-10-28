package com.utn.ag.viajante.gui;




import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.utn.ag.viajante.impl.HeuristicaFacu;
import com.utn.ag.viajante.model.Constants;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class guiViajante {

	private static final String IMG_PATH = "src/main/java/com/utn/ag/viajante/gui/mapa1Argentina.jpg";
	static ImagePanel pnlMapa ;
	BufferedImage tableroBuffer = null;
	
	
	
	private JFrame frmGui;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiViajante window = new guiViajante();
					window.frmGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void dibujarResultado(int[] ciudades){
		
		
		
		
		
		
	}
	
	
	public void dibujarInicio(int[] ciudades){
		
		
		
	}
	
	
	
	/**
	 * Create the application.
	 */
	public guiViajante() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGui = new JFrame();
		frmGui.setResizable(false);
		frmGui.setTitle("guiTSP");
		frmGui.setBounds(100, 100, 346, 786);
		frmGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGui.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlInput = new JPanel();
		frmGui.getContentPane().add(pnlInput, BorderLayout.NORTH);
		pnlInput.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblCiclos = new JLabel("Ciclos:");
		pnlInput.add(lblCiclos);
		
		textField = new JTextField();
		textField.setText("200");
		pnlInput.add(textField);
		textField.setColumns(10);
		
		JLabel lblPoblacin = new JLabel("Población:");
		pnlInput.add(lblPoblacin);
		
		textField_1 = new JTextField();
		textField_1.setText("50");
		pnlInput.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel pnlBtnResult = new JPanel();
		frmGui.getContentPane().add(pnlBtnResult, BorderLayout.SOUTH);
		pnlBtnResult.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlResult = new JPanel();
		pnlBtnResult.add(pnlResult, BorderLayout.NORTH);
		
		JLabel lblResultado = new JLabel("");
		pnlResult.add(lblResultado);
		
		JPanel pnlBotones = new JPanel();
		pnlBtnResult.add(pnlBotones, BorderLayout.SOUTH);
		pnlBotones.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnEjecutarAlgGentico = new JButton("Ejecutar Alg. Genético");
		pnlBotones.add(btnEjecutarAlgGentico);
		
		JButton btnEjecutarAlgHeurstico = new JButton("Ejecutar Alg. Heurístico");
		btnEjecutarAlgHeurstico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int mejorCiudad = -1;
		         int mejorRecorrido = -1;
		         for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {
		             HeuristicaFacu hf = new HeuristicaFacu();
		             hf.recorreCiudades(i);
		             if (mejorRecorrido < 0 || mejorRecorrido > hf.getKmRecorridos()) {
		                 mejorRecorrido = hf.getKmRecorridos();
		                 mejorCiudad = i;
		             }
		             
		            // hf.printCiudadesRecorridas();
		         }
		         
		         HeuristicaFacu hf = new HeuristicaFacu();
	             hf.recorreCiudades(mejorCiudad);
		         
	             /*int indice =  hf.getRecorrido()[0];
	    	             
	    	             
	             
	             int x = Constants.COORENADAS_CIUDADES[indice].getX();
	             int y = Constants.COORENADAS_CIUDADES[indice].getY();
	             pnlMapa.pintarInicio(x, y);
		         */
				
	             pnlMapa.pintarRecorrido( hf.getRecorrido());
	             
	             
	             
	             
				
			}
		});
		pnlBotones.add(btnEjecutarAlgHeurstico);
		
		ImageIcon imagen = new ImageIcon(IMG_PATH);	
		pnlMapa = new ImagePanel(imagen.getImage());	
	/*	pnlMapa.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				try {
					
					System.out.println(arg0.getX()+ ":" + arg0.getY());
					
				}catch (Exception e){
					
					
					
				}
			}
		});
		
		*/
		
		
		
		
		
		
		
		
		frmGui.getContentPane().add(pnlMapa, BorderLayout.CENTER);
		
		
		


		
	}

}

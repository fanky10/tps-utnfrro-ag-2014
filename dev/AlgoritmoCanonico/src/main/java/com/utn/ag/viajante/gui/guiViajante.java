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

import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Label;



public class guiViajante {

	private static final String IMG_PATH = "src/main/java/com/utn/ag/viajante/gui/mapa1Argentina.jpg";
	
	
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
		frmGui.setTitle("guiTSP");
		frmGui.setResizable(false);
		frmGui.setBounds(100, 100, 487, 573);
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
		pnlBotones.add(btnEjecutarAlgHeurstico);
		
		JPanel pnlMapa = new JPanel();
		frmGui.getContentPane().add(pnlMapa, BorderLayout.CENTER);
		
		JLabel mapa = new JLabel("");
		
		ImageIcon icon = new ImageIcon(IMG_PATH); 
		
		mapa.setIcon(icon);
	
		
		pnlMapa.add(mapa);
		
	}

}

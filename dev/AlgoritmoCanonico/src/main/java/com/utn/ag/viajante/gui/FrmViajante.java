package com.utn.ag.viajante.gui;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.utn.ag.viajante.impl.GeneticoEst;
import com.utn.ag.viajante.impl.HeuristicaFacu;
import com.utn.ag.viajante.model.Constants;
import com.utn.ag.viajante.model.Cromosoma;
import com.utn.ag.viajante.model.Poblacion;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrmViajante extends JFrame {

    private static final String IMG_PATH = "src/main/java/com/utn/ag/viajante/gui/mapa1Argentina.jpg";
    static ImagePanel pnlMapa;
    BufferedImage tableroBuffer = null;

    private JTextField txtCantCiclos;
    private JTextField txtCantPoblacion;
    private JTextField txtIndiceCiudad;
    private JLabel lblResultado;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmViajante window = new FrmViajante();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FrmViajante() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("guiTSP");
        setBounds(100, 100, 346, 786);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel pnlInput = new JPanel();
        getContentPane().add(pnlInput, BorderLayout.NORTH);
        pnlInput.setLayout(new GridLayout(0, 4, 0, 0));

        JLabel lblCiclos = new JLabel("Ciclos:");
        pnlInput.add(lblCiclos);

        txtCantCiclos = new JTextField();
        txtCantCiclos.setText("200");
        pnlInput.add(txtCantCiclos);
        txtCantCiclos.setColumns(10);

        JLabel lblPoblacin = new JLabel("Población:");
        pnlInput.add(lblPoblacin);

        txtCantPoblacion = new JTextField();
        txtCantPoblacion.setText("50");
        pnlInput.add(txtCantPoblacion);
        txtCantPoblacion.setColumns(10);

        JLabel lblCiudad = new JLabel("Ciudad:");
        pnlInput.add(lblCiudad);

        txtIndiceCiudad = new JTextField();
        txtIndiceCiudad.setText("21");
        pnlInput.add(txtIndiceCiudad);
        txtIndiceCiudad.setColumns(5);

        JPanel pnlBtnResult = new JPanel();
        getContentPane().add(pnlBtnResult, BorderLayout.SOUTH);
        pnlBtnResult.setLayout(new BorderLayout(0, 0));

        JPanel pnlResult = new JPanel();
        pnlBtnResult.add(pnlResult, BorderLayout.NORTH);

        lblResultado = new JLabel("");
        pnlResult.add(lblResultado);

        JButton btnEjecutarAlgGentico = new JButton("Ejecutar Alg. Genético");
        btnEjecutarAlgGentico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ejecutaAlgoritmoGenetico();
            }
        });

        JButton btnEjecutarAlgHeurstico = new JButton(
                "Ejecutar Alg. Heurístico");
        btnEjecutarAlgHeurstico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ejecutaAlgoritmoHeuristico();
            }
        });

        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(1, 0, 0, 0));
        pnlBotones.add(btnEjecutarAlgGentico);
        pnlBotones.add(btnEjecutarAlgHeurstico);
        pnlBtnResult.add(pnlBotones, BorderLayout.SOUTH);
        
        pnlMapa = new ImagePanel();

        getContentPane().add(pnlMapa, BorderLayout.CENTER);

    }

    private void ejecutaAlgoritmoGenetico() {
        pnlMapa.clearMap();
        GeneticoEst.CANT_CICLOS = Integer.valueOf(txtCantCiclos.getText());
        GeneticoEst.CANT_POBLACION = Integer.valueOf(txtCantPoblacion.getText());

        GeneticoEst gen = new GeneticoEst();

        Poblacion p = null;
        for (int i = 0; i < GeneticoEst.CANT_CICLOS; i++) {
            if (i == 0) {
                p = gen.nuevaPoblacion();
            } else {
                p = gen.nuevaPoblacion(p);
            }
            p.printPoblacion();
        }

        p.processFitness();

        Cromosoma resultado = new Cromosoma();
        resultado.setFitness(0);
        for (Cromosoma c : p) {
            if (c.getFitness() > resultado.getFitness()) {
                resultado = c;
            }
        }
        pnlMapa.dibujarRecorrido(resultado.getCiudades());
        lblResultado.setText("Ciudad Inicial: " + Constants.NOMBRES_PROVINCIAS[resultado.getCiudades()[0]]
                + " i: " + resultado.getCiudades()[0] + " y recorrido de " + resultado.getDistanciaRecorrido() + "km");
    }

    private void ejecutaAlgoritmoHeuristico() {
        pnlMapa.clearMap();
        int mejorCiudad = -1;
        int mejorRecorrido = -1;
        for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {
            HeuristicaFacu hf = new HeuristicaFacu();
            hf.recorreCiudades(i);
            if (mejorRecorrido < 0
                    || mejorRecorrido > hf.getKmRecorridos()) {
                mejorRecorrido = hf.getKmRecorridos();
                mejorCiudad = i;
            }

        }

        HeuristicaFacu hf = new HeuristicaFacu();
        if (!txtIndiceCiudad.getText().isEmpty()) {
            mejorCiudad = Integer.parseInt(txtIndiceCiudad.getText());
        }
        hf.recorreCiudades(mejorCiudad);
        mejorRecorrido = hf.getKmRecorridos();
        pnlMapa.dibujarRecorrido(hf.getRecorrido());
        lblResultado.setText("Ciudad Inicial: " + Constants.NOMBRES_PROVINCIAS[mejorCiudad]
                + " i: " + mejorCiudad + " y recorrido de " + mejorRecorrido + "km");
    }

}

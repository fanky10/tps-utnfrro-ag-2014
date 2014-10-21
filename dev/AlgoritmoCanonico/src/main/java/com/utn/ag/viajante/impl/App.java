package com.utn.ag.viajante.impl;

import com.utn.ag.algoritmocanonico.AppConstants;
import com.utn.ag.algoritmocanonico.MockedLogger;
import com.utn.ag.algoritmocanonico.vo.InformeVO;
import com.utn.ag.viajante.model.Constants;
import com.utn.ag.viajante.model.Cromosoma;
import com.utn.ag.viajante.model.Poblacion;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        HeuristicaFacu.DEBUG = Boolean.FALSE;
       
        System.out.println("Algoritmo Heurístico:");
        
        int mejorCiudad = -1;
        int mejorRecorrido = -1;
        for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {
            HeuristicaFacu hf = new HeuristicaFacu();
            hf.recorreCiudades(i);
            if (mejorRecorrido < 0 || mejorRecorrido > hf.getKmRecorridos()) {
                mejorRecorrido = hf.getKmRecorridos();
                mejorCiudad = i;
            }
            
            hf.printCiudadesRecorridas();
        }
        System.out.println("Mejor ciudad: " + mejorCiudad + " - " + Constants.NOMBRES_PROVINCIAS[mejorCiudad] + " km recorridos: " + mejorRecorrido);
  
    
    
    
    System.out.println( "\n\nAlgoritmo Genético:");
    
   
    GeneticoEst gen = new GeneticoEst();
    
    
  
    Poblacion p = null;
	for (int i = 0; i < GeneticoEst.CANT_CICLOS; i++) {
		if (i == 0) {
			p = gen.nuevaPoblacion();
			
		
			
			
		} else {
			p = gen.nuevaPoblacion(p);
		}
		System.out.println("********CICLO " + (i+1));
		 p.printPoblacion();
    
  

}

    }}

package com.utn.ag.viajante.model;

import java.util.Random;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.ICombinatoricsVector;

import com.utn.ag.problemamochila.model.Item;
import com.utn.ag.viajante.impl.GeneticoEst;

public class Cromosoma {

	private int ciudades[] = new int[23];//RECORDAR que [0] coincide con el [24] que no aparece
	private Double fitness; // 1/distancia
	private int distancia = 0;
	
	public Cromosoma(){
		
generarGenoma();
		
	}
	
	
	public Cromosoma(int[] is) {
		// TODO Auto-generated constructor stub
		ciudades = is;
	}


	public int[] getCiudades(){
		return ciudades;
	}
	
	public Double getFitness(){
		return fitness;
	}
	
	public void generarGenoma(){
		int r = -1;
		for (int i = 0; i<22; i++){
		
			r = GeneticoEst.r.nextInt(23);
		while (ciudadYaIncluida(r)){
			r = GeneticoEst.r.nextInt(23);
		}
			ciudades[i] = r;
			
		}
	}
	
	public void setFitness (double i){
		
		fitness = i;
	}
	
	public int getDistancia(int i, int j) {

		return Constants.TABLA_DISTANCIAS[i][j];

	}
	
	public int getDistanciaRecorrido(){
		distancia = 0;
		
		for (int i = 1; i < ciudades.length; i++) {

			distancia = distancia
					+ getDistancia(ciudades[i - 1], ciudades[i]);

		

		}
		
		distancia = distancia + getDistancia(ciudades[ciudades.length-1], ciudades[0]);  //El último eslabón
		
		
		return distancia;
	}
	
	
	private boolean ciudadYaIncluida(int i){
		
		for (int c : ciudades){
		
			if (c == i) {
				return true;
			};
			
		}
		
		return false;
		
	}


	public void setGenoma(int[] nuevoRecorrido) {
		// TODO Auto-generated method stub
		ciudades = nuevoRecorrido;
	}
	
	
	
	
	
	
	
	
}

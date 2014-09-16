package com.utn.ag.viajante.impl;

import java.util.ArrayList;
import java.util.List;

import com.utn.ag.viajante.model.Constants;

public class HeuristicaFacu implements Heuristica {
	
	private int[] recorridoArray = new int [Constants.CANTIDAD_PROVINCIAS + 1];
	private List<Integer> recorridoList = new ArrayList<Integer>();
	public int[] getRecorridoCiudad(int idx) {
		//busca el indice de la ciudad mas cercana,
		//lo agrega al recorridoArray
		
		return recorridoArray;
	}
	
	private void addRecorridoCiudad(int indiceCiudad){
		int proximaCiudad = getClosestCityIndex(indiceCiudad);
		
	}
	/*
	 * devuelve el indice de la ciudad mas cercana
	 */
	private int getClosestCityIndex(int ciudadOrigen){
		int[] ciudadesDestino = Constants.TABLA_DISTANCIAS[ciudadOrigen];
		int bestRecorrido = -1;
		int indiceDestino = -1;
		for(int i=0;i<ciudadesDestino.length;i++){
			int distancia = ciudadesDestino[i];
			if(distancia>0 && !recorridoList.contains(Integer.valueOf(i)) && (distancia<bestRecorrido || bestRecorrido <0)){
				// encontre el mejor!
				// actualizo indice y el best
				bestRecorrido = distancia;
				indiceDestino = i;
			}
		}
		return indiceDestino;
	}

	public void printRecorrido() {
		// TODO Auto-generated method stub
		for(int i = 0; i< recorridoArray.length;i++){
			int ciudad = recorridoArray[i];
			System.out.println("Ciudad: "+Constants.NOMBRES_PROVINCIAS[ciudad]);
		}
	}

	public void resolverCiudades() {
		// TODO Auto-generated method stub
		// primer ciudad:
		getRecorridoCiudad(0);
		printRecorrido();
	}

	public static void main(String args[]){
		int ciudadDestino = 7;
		int ciudadOrigen = 0;
		HeuristicaFacu hf = new HeuristicaFacu();
		int posibleDestino = hf.getClosestCityIndex(ciudadOrigen);
		if(ciudadDestino == posibleDestino){
			System.out.println("todo bien!!");
		}
		
	}

}

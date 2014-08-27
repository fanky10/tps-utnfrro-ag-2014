package com.utn.ag.viajante.impl;

import com.utn.ag.viajante.model.Constants;

public class Heuristica {
//La Heuristica aplicada es "Desde cada ciudad ir a la ciudad más cercana no visitada"
	
	int[] recorrido = new int[Constants.CANTIDAD_PROVINCIAS];
	
	int ciudadOrigen = 0;
	
	
	public void calcularRecorrido(){
		
		System.out.println(getCiudadCercana(2) + " , " + getCiudadCercana(0));
	}
	
	
	

	
	
	public int getDistancia(int i, int j){

		return Constants.distanciasTabla[i][j];
		
	}
	
	public int getCiudadCercana (int origen){
		int res = 0;
		if (origen == 0) res = 1; 
		
		for(int i = 0; i<Constants.CANTIDAD_PROVINCIAS; i++){
			
			if(getDistancia(origen, res) > getDistancia(origen, i) & origen != i){
				
				res =i;
			}
			
			
			
		}
		
		
		
		return res;
	}
	
	
	
}

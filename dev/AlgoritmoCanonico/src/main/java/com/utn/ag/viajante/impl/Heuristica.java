package com.utn.ag.viajante.impl;

public interface Heuristica {
	/*
	 * retorna un arreglo con el recorrido
	 * a partir de la ciudad ingresada
	 */
	public int[] getRecorridoCiudad(int idx);
	
	public void printRecorrido();
	
	
	public void resolverCiudades();
}

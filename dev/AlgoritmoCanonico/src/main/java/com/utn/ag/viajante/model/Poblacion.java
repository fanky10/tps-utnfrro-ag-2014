package com.utn.ag.viajante.model;

import java.util.ArrayList;





public class Poblacion extends ArrayList<Cromosoma> {

	public Poblacion(int size) {
		super(size);
	}
	
	public Poblacion(ArrayList<Cromosoma> hijos) {
		
		for (Cromosoma c : hijos) {
			this.add(c);
		}
	}
	
	public Poblacion() {
		// TODO Auto-generated constructor stub
	}

	public void processFitness() {
		int distTotal = 0;
		for (Cromosoma c : this){
			distTotal = distTotal + c.getDistanciaRecorrido();
		}
				
		
		for (Cromosoma c : this) {
			c.setFitness(c.getDistanciaRecorrido()/distTotal);
		}
		
	}
	
	public void printPoblacion(){
		System.out.println("Recorridos Finales");
	for (Cromosoma c : this)	{
		
		c.printRecorrido();
		System.out.println(c.getDistanciaRecorrido());
		System.out.println(c.getFitness());
		
	}
		
		
	}	
	
}

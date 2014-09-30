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
		for (Cromosoma c : this) {
			c.setFitness(1 / c.getDistanciaRecorrido());
		}
	}
	
}

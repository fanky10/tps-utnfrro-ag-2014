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
		double distTotal = 0;
		// double sumacontrol = 0;
		for (Cromosoma c : this){
			distTotal = distTotal + c.getDistanciaRecorrido();
		}
				
		double fitnessTotal = 0;
		for (Cromosoma c : this) {
			//c.setFitness(c.getDistanciaRecorrido());
			c.setFitness(1-(c.getDistanciaRecorrido()/distTotal));
	//sumacontrol = sumacontrol + c.getFitness();
	

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

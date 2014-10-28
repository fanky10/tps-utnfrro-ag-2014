package com.utn.ag.viajante.model;

import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;
import java.util.Random;





public class Poblacion extends ArrayList<Cromosoma> {

	private static Random m_rand = new Random();
	
	
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
				
	
		
		
		
		//Resetear Fitness a 0;
				for (Cromosoma c : this){
					c.setFitness(0);
				}
				
		
		
		
		double distTotal = 0;
		double sumaTotal = 0;
		double controlFit = 0;
		
		for (Cromosoma c : this){
			distTotal = distTotal + c.getDistanciaRecorrido();
		}
		
		
		for (Cromosoma c : this){
		
			c.setFitness(distTotal - c.getDistanciaRecorrido());
			sumaTotal = sumaTotal + (distTotal - c.getDistanciaRecorrido());
			
		}
		
		for (Cromosoma c : this){
			
			double fitness = c.getFitness()/sumaTotal;
			
			c.setFitness(fitness);
			
			controlFit = controlFit + c.getFitness();
			
		}
		

		//System.out.println("Distancia TOTAL:" + distTotal + "| fitness TOT:" + sumaTotal + "|sumacontrol:" + controlFit);
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		double distTotal = 0;
		for (Cromosoma c : this){
			distTotal = distTotal + c.getDistanciaRecorrido();
		}
		double porcTotal = 0d;
		for (Cromosoma c : this){
			double invPorc = 1 / (c.getDistanciaRecorrido()/distTotal);
			c.setFitness(invPorc);
			porcTotal = porcTotal + invPorc;
		}
		for (Cromosoma c : this){
			double fitness = c.getFitness() / porcTotal;
			c.setFitness(fitness);
		}
		double fitnessTotal = 0;
		for (Cromosoma c : this) {
			//c.setFitness(c.getDistanciaRecorrido());
			c.setFitness(1-(c.getDistanciaRecorrido()/distTotal));
	//sumacontrol = sumacontrol + c.getFitness();
	

		}
		
	*/
		
		
		
		
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

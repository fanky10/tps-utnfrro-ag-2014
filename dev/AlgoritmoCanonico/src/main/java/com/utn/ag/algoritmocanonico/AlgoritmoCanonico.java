package com.utn.ag.algoritmocanonico;

import java.util.ArrayList;
import java.util.Random;

import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;

public class AlgoritmoCanonico {

	private static Double probmutacion;
	private static Double probcrossover;
	
	private int[][] ruleta;
	
	
	
	private void seleccionRuleta(Poblacion poblacion) {
		ruleta = new int[poblacion.size() / 2][2];
		Double sumaAcumulada = 0d;

		for (Cromosoma c : poblacion) {
			sumaAcumulada = sumaAcumulada + c.getFitness();
			c.setSumaAcumulada(sumaAcumulada);
		}

		for (int i = 0; i < poblacion.size() / 2; i++) {
			Double ran = Math.random();
			for (int j = 0; j < poblacion.size(); j++) {

				if (poblacion.get(j).getSumaAcumulada() > ran) {
					ruleta[i][0] = j;
					break;
				}
			}

			for (int j = 0; j < poblacion.size(); j++) {

				if (poblacion.get(j).getSumaAcumulada() > ran) {
					ruleta[i][1] = j;
					break;
				}
			}
		}
	}
	
	public void operadorMutacion(Cromosoma cromosoma) {

		if (Math.random() >= probmutacion) {
					cromosoma.mutarBit();	
		}

	}

	public void operadorCrossover(Cromosoma cromosoma1, Cromosoma cromosoma2) {
		Cromosoma cromosomaHijo1, cromosomaHijo2;

		if (Math.random() >= probcrossover) {
			// TODO crossovear

		} else {

		}

	}
	
	
	public AlgoritmoCanonico(Double probcrossover, Double promutacion){
		this.probcrossover = probcrossover;
		this.probmutacion = probcrossover;
		
	
	}
	
	
	public Poblacion siguienteGeneracion(Poblacion poblacion) {
		poblacion.processFitness();
		seleccionRuleta(poblacion);
		Poblacion hijos = new Poblacion();
		
		//TODO: Implementar Crossover y a cada hijo aplicarle operadorMutacion
		
	
		
		
		return hijos;
	}
	
}

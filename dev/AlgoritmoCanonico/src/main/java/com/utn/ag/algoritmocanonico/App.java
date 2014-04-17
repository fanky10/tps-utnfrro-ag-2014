package com.utn.ag.algoritmocanonico;

import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;

public class App {
	private static final Integer POBLACION = 20;
	private static final Double PROBMUTACION = 0.05;
	private static final Double PROBCROSSOVER = 0.75;
	private static final int ITERACIONES = 1000;

	public static void main(String args[]) {
		Poblacion p = generarPrimerPoblacion();
		AlgoritmoCanonico a = new AlgoritmoCanonico(PROBCROSSOVER, PROBMUTACION);
		p.processFitness();
		p.showInforme();

		for (int i = 0; i < ITERACIONES - 1; i++) {

			p = a.siguienteGeneracion(p);
			p.processFitness();
			p.showInforme();

		}

	}

	private static Poblacion generarPrimerPoblacion() {
		Poblacion poblacion = new Poblacion();
		for (int i = 0; i < POBLACION; i++) {
			Cromosoma c = new Cromosoma();
			debug(c.toString());
			poblacion.add(c);
		}
		return poblacion;
	}

	private static final Boolean DEBUG = Boolean.FALSE;

	private static void debug(String txt) {
		if (DEBUG) {
			System.out.println("DEBUG: " + txt);
		}
	}
}

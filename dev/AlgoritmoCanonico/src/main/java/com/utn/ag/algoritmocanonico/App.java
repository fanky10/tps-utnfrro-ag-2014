package com.utn.ag.algoritmocanonico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;

public class App {
	private static final Integer POBLACION = 10;
	private static final Double PROBMUTACION = 0.05;
	private static final Double PROBCROSSOVER = 0.75;
	private static final int ITERACIONES = 25;

	public static void main(String args[]) throws FileNotFoundException {
		cleanFile();
		Poblacion.showInformeHeaders();
		Poblacion p = generarPrimerPoblacion();
		AlgoritmoCanonico a = new AlgoritmoCanonico(PROBCROSSOVER, PROBMUTACION);
		p.processFitness();
		p.showInformeData();

		for (int i = 0; i < ITERACIONES - 1; i++) {

			p = a.siguienteGeneracion(p);
			p.processFitness();
			p.showInformeData();

		}

	}

	private static Poblacion generarPrimerPoblacion() {
		Poblacion poblacion = new Poblacion();
		for (int i = 0; i < POBLACION; i++) {
			Cromosoma c = new Cromosoma();
			MockedLogger.debug(c.toString());
			poblacion.add(c);
		}
		return poblacion;
	}
	
	private static void cleanFile(){
		File f = new File(AppConstants.FILE_INFORME);
		f.delete();
	}
}

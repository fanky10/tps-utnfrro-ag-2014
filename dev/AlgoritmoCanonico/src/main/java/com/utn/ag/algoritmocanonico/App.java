package com.utn.ag.algoritmocanonico;

import java.io.File;
import java.io.FileNotFoundException;

import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;
import com.utn.ag.algoritmocanonico.service.AlgoritmoCanonico;
import com.utn.ag.algoritmocanonico.service.impl.AlgoritmoCanonicoImpl;

public class App {

	public static void main(String args[]) throws FileNotFoundException {
		cleanFile();
		Poblacion.showInformeHeaders();
		Poblacion p = generarPrimerPoblacion();
		AlgoritmoCanonico a = new AlgoritmoCanonicoImpl();
		p.showInformeData();

		for (int i = 0; i < AppConstants.ITERACIONES - 1; i++) {
			p = a.nuevaPoblacion(p);
			p.showInformeData();
		}

	}

	private static Poblacion generarPrimerPoblacion() {
		Poblacion poblacion = new Poblacion();
		for (int i = 0; i < AppConstants.POBLACION; i++) {
			Cromosoma c = new Cromosoma();
			MockedLogger.debug(c.toString());
			poblacion.add(c);
		}
		return poblacion;
	}

	private static void cleanFile() {
		File f = new File(AppConstants.FILE_INFORME);
		f.delete();
	}
}

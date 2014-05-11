package com.utn.ag.algoritmocanonico.service.impl;

import java.util.Random;

import com.utn.ag.algoritmocanonico.AppConstants;
import com.utn.ag.algoritmocanonico.MockedLogger;
import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;
import com.utn.ag.algoritmocanonico.service.AlgoritmoCanonico;
import com.utn.ag.algoritmocanonico.util.RouletteWheelSelection;

public class AlgoritmoCanonicoImpl implements AlgoritmoCanonico {

	public Poblacion nuevaPoblacion() {
		Poblacion poblacion = new Poblacion();
		for (int i = 0; i < AppConstants.POBLACION; i++) {
			Cromosoma c = new Cromosoma();
			poblacion.add(c);
		}
		poblacion.processFitness();
		return poblacion;
	}

	/**
	 * la nueva poblacion es generada a partir de: crossover + mutacion
	 * 
	 * @param poblacionActual
	 * @return poblacionNueva
	 */
	public Poblacion nuevaPoblacion(Poblacion poblacionActual) {

		// ruleta n veces por cantidad de poblacion actual
		Poblacion poblacionNueva = RouletteWheelSelection
				.select(poblacionActual);
		debugPoblacion(poblacionNueva);
		// aplicamos crossover cada dos
		// aplicamos mutacion a cada cromosoma generado
		for (int i = 0; i < poblacionNueva.size(); i += 2) {
			Cromosoma c1 = poblacionNueva.get(i);
			Cromosoma c2 = poblacionNueva.get(i + 1);
			MockedLogger.debug("PADRES " + c1.getGenoma() + " | " + c2.getGenoma());
			aplicarCrossover(c1, c2);
			aplicarMutacion(c1);
			aplicarMutacion(c2);
			MockedLogger.debug("HIJOS! " + c1.getGenoma() + " | " + c2.getGenoma());
			MockedLogger.debug("----------------------------------------------------");
		}
		poblacionNueva.processFitness();
		return poblacionNueva;
	}

	private void debugPoblacion(Poblacion p) {
		MockedLogger.debug("Poblacion luego de la seleccion de ruleta");
		for (Cromosoma c : p) {
			MockedLogger.debug("genoma: " + c.getGenoma() + " fitness: "
					+ c.getFitness());
		}
	}

	/**
	 * si se debe aplicar crossover, entonces tirar l
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public void aplicarCrossover(Cromosoma c1, Cromosoma c2) {
		if (!aplicarCrossover()) {
			MockedLogger.debug("NOT aplicar crossover!!");
			return;
		}
		MockedLogger.debug("a aplicar crossover!!");
		if (c1.getGenoma().length() != c2.getGenoma().length()) {
			throw new IllegalArgumentException(
					"cromosomas con genomas de distintas longitudes");
		}
		// crossover de un corte
		// donde se corta
		Random ran = new Random();
		int indexCorte = ran.nextInt(c1.getGenoma().length());

		MockedLogger.debug("indexCorte " + indexCorte);
		// se intercambian
		StringBuilder genoma1 = new StringBuilder();
		StringBuilder genoma2 = new StringBuilder();
		genoma1.append(c1.getGenoma().substring(0, indexCorte));
		genoma1.append(c2.getGenoma().substring(indexCorte));

		genoma2.append(c2.getGenoma().substring(0, indexCorte));
		genoma2.append(c1.getGenoma().substring(indexCorte));
		// se sobreescribe
		c1.setGenoma(genoma1.toString());
		c2.setGenoma(genoma2.toString());

	}

	private boolean aplicarCrossover() {
		return (Math.random() <= PROBABILIDAD_CROSSOVER);
	}

	public void aplicarMutacion(Cromosoma c1) {
		// ver si debo mutar por probabilidad
		// si debo mutar, ver que inidice mutar.
		if (aplicarMutacion()) {
			MockedLogger.debug("Aplicar mutacion!! " + c1.getGenoma());
			c1.mutarBit();
			MockedLogger.debug("mutante!! " + c1.getGenoma());
		}
	}

	private boolean aplicarMutacion() {
		return Math.random() <= PROBABILIDAD_MUTACION;
	}

}
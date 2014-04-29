package com.utn.ag.algoritmocanonico.service.impl;

import java.util.Random;

import com.utn.ag.algoritmocanonico.MockedLogger;
import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;
import com.utn.ag.algoritmocanonico.service.AlgoritmoCanonico;
import com.utn.ag.algoritmocanonico.util.RouletteWheelSelection;

public class AlgoritmoCanonicoImpl implements AlgoritmoCanonico {

	/**
	 * la nueva poblacion es generada a partir de: crossover + mutacion
	 * 
	 * @param poblacionActual
	 * @return poblacionNueva
	 */
	public Poblacion nuevaPoblacion(Poblacion poblacionActual) {
		poblacionActual.processFitness();
		// ruleta n veces por cantidad de poblacion actual
		Poblacion poblacionNueva = RouletteWheelSelection
				.select(poblacionActual);
		verbosePoblacion(poblacionNueva,
				"Poblacion luego de la seleccion de ruleta");
		// aplicamos crossover cada dos
		// aplicamos mutacion a cada cromosoma generado
		for (int i = 0; i < poblacionNueva.size() / 2;i+=2) {
			Cromosoma c1 = poblacionNueva.get(i);
			Cromosoma c2 = poblacionNueva.get(i + 1);
			aplicarCrossover(c1, c2);
			aplicarMutacion(c1);
			aplicarMutacion(c2);
		}

		return poblacionNueva;
	}

	private void verbosePoblacion(Poblacion p, String mensaje) {
		for (Cromosoma c : p) {
			MockedLogger.verbose("genoma: " + c.getGenoma());
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
			return;
		}
		if (c1.getGenoma().length() != c2.getGenoma().length()) {
			throw new IllegalArgumentException(
					"cromosomas con genomas de distintas longitudes");
		}
		MockedLogger.verbose("PADRES " + c1.getGenoma() + " | "
				+ c2.getGenoma());
		// crossover de un corte
		// donde se corta
		Random ran = new Random();
		int indexCorte = ran.nextInt(c1.getGenoma().length());
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

		MockedLogger.verbose("HIJOS! " + c1.getGenoma() + " | "
				+ c2.getGenoma());
	}

	private boolean aplicarCrossover() {
		return (Math.random() <= PROBABILIDAD_CROSSOVER);
	}

	public void aplicarMutacion(Cromosoma c1) {
		// ver si debo mutar por probabilidad
		// si debo mutar, ver que inidice mutar.
		if (aplicarMutacion()) {
			c1.mutarBit();
		}
	}

	private boolean aplicarMutacion() {
		return Math.random() <= PROBABILIDAD_MUTACION;
	}

}
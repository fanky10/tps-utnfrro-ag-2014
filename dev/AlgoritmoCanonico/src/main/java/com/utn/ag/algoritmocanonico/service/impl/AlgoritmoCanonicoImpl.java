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
		Poblacion poblacionSeleccionada = RouletteWheelSelection
				.select(poblacionActual);
		debugPoblacion(poblacionSeleccionada);
		Poblacion nuevaPoblacion = new Poblacion();
		MockedLogger.debug("***************************************");
		// aplicamos crossover cada dos
		// aplicamos mutacion a cada cromosoma generado
		for (int i = 0; i < poblacionSeleccionada.size(); i += 2) {
			Cromosoma c1 = poblacionSeleccionada.get(i);
			Cromosoma c2 = poblacionSeleccionada.get(i + 1);
			debugCromosoma("Padre: 1 ", c1);
			debugCromosoma("Padre: 2 ", c2);
			
			aplicarCrossover(c1, c2);
			debugCromosoma("Hijo: 1 ", c1);
			debugCromosoma("Hijo: 2 ", c2);
			if (aplicarMutacion(c1)) {
				debugCromosoma("Hijo: 1 Mutado! ", c1);
			}
			if (aplicarMutacion(c2)) {
				debugCromosoma("Hijo: 2 Mutado! ", c2);
			}
			MockedLogger
					.debug("----------------------------------------------------");
			nuevaPoblacion.add(c1);
			nuevaPoblacion.add(c2);
		}
		nuevaPoblacion.processFitness();
		return nuevaPoblacion;
	}

	private void debugCromosoma(String msg, Cromosoma c) {
		MockedLogger.debug(msg + c.getGenoma() + " intVal: " + c.getIntValue()
				+ " f(x)" + c.getFunctionValue());
	}

	private void debugPoblacion(Poblacion p) {
		MockedLogger.debug("Poblacion luego de la seleccion de ruleta");
		for (Cromosoma c : p) {
			MockedLogger.debug("IntVal:" + c.getIntValue() + " genoma: "
					+ c.getGenoma() + " fitness: " + c.getFitness());
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

	public boolean aplicarMutacion(Cromosoma c1) {
		// ver si debo mutar por probabilidad
		// si debo mutar, ver que inidice mutar.
		boolean aplicarMutacion = aplicarMutacion();
		if (aplicarMutacion) {
			MockedLogger.debug("Aplicar mutacion!! ");
			String nuevoGenoma = mutarBit(c1.getGenoma());
			c1.setGenoma(nuevoGenoma);
		}
		return aplicarMutacion;
	}

	public String mutarBit(String genoma) {
		MockedLogger.debug("Genoma a Mutar: " + genoma);
		Random ran = new Random();
		int index = ran.nextInt(genoma.length());
		StringBuilder builder = new StringBuilder(genoma);
		if (genoma.charAt(index) == '0') {
			builder.setCharAt(index, '1');
		} else {
			builder.setCharAt(index, '0');
		}
		MockedLogger.debug("Cambio de genoma en: " + index);
		return builder.toString();
	}

	private boolean aplicarMutacion() {
		return Math.random() <= PROBABILIDAD_MUTACION;
	}

}
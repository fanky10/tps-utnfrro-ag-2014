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
			String genoma1 = poblacionSeleccionada.get(i).getGenoma();
			String genoma2 = poblacionSeleccionada.get(i + 1).getGenoma();
			debugCromosoma("Padre: 1 ", poblacionSeleccionada.get(i));
			debugCromosoma("Padre: 2 ", poblacionSeleccionada.get(i + 1));

			String nuevosGenomas[] = aplicarCrossover(genoma1, genoma2);
			Cromosoma c1 = new Cromosoma(nuevosGenomas[0]);
			Cromosoma c2 = new Cromosoma(nuevosGenomas[1]);
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
				+ " f(x) " + c.getFunctionValue());
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
	public String[] aplicarCrossover(String g1, String g2) {
		if (!aplicarCrossover()) {
			MockedLogger.debug("NOT aplicar crossover!!");
			return new String[]{g1,g2};
		}
		MockedLogger.debug("a aplicar crossover!!");
		if (g1.length() != g2.length()) {
			throw new IllegalArgumentException(
					"cromosomas con genomas de distintas longitudes");
		}
		// crossover de un corte
		// donde se corta
		Random ran = new Random();
		int indexCorte = ran.nextInt(g1.length());

		MockedLogger.debug("indexCorte " + indexCorte);
		// se intercambian
		StringBuilder genoma1 = new StringBuilder();
		StringBuilder genoma2 = new StringBuilder();
		genoma1.append(g1.substring(0, indexCorte));
		genoma1.append(g2.substring(indexCorte));

		genoma2.append(g2.substring(0, indexCorte));
		genoma2.append(g1.substring(indexCorte));
		// se sobreescribe
		return new String[]{genoma1.toString(), genoma2.toString()};

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
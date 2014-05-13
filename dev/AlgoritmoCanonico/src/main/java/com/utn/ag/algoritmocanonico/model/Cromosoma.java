package com.utn.ag.algoritmocanonico.model;

import java.util.Random;

public class Cromosoma {
	private static final Integer LONGITUD = 30;
	private static final Double COEFICIENTE = Math.pow(2, LONGITUD) - 1;
	private String genoma;
	private Double fitness;
	private Double sumaAcumulada;

	public Cromosoma() {
		genoma = generarGenoma();
	}

	public Cromosoma(String genoma) {
		this.genoma = genoma;
	}

	public String toString() {
		return String.format(
				"Genoma generado: %s - intValue %s - coef %s - f(x) %s",
				genoma, getIntValue(), COEFICIENTE, getFunctionValue());
	}

	private String generarGenoma() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < LONGITUD; i++) {
			sb.append(ranBin());
		}
		return sb.toString();
	}

	private Integer ranBin() {
		Double random = new Random().nextDouble();
		return random > 0.5d ? 1 : 0;
	}

	public Double getFunctionValue() {
		return Math.pow(getIntValue() / COEFICIENTE, 2);
	}

	public Integer getIntValue() {
		return Integer.parseInt(genoma, 2);
	}

	public void setFitness(Double fitness) { // Esteban
		this.fitness = fitness;
	}

	public Double getFitness() {
		return this.fitness;
	}

	public Double getSumaAcumulada() {
		return sumaAcumulada;
	}

	public void setSumaAcumulada(Double sumaAcumulada) {
		this.sumaAcumulada = sumaAcumulada;
	}

	public String getGenoma() {
		return genoma;
	}

	public void setGenoma(String genoma) {
		this.genoma = genoma;
	}

}

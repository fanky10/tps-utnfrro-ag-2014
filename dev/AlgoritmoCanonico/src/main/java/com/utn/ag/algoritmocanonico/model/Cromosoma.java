package com.utn.ag.algoritmocanonico.model;

public class Cromosoma {
	private static final Integer LONGITUD = 30;
	private static final Double COEFICIENTE = Math.pow(2, LONGITUD) - 1;
	private String cadena;

	public Cromosoma() {
		cadena = genCadena();
	}

	public Cromosoma(String cadena) {
		this.cadena = cadena;
	}

	public String toString() {
		return String.format("Cadena generada: %s - intValue %s - coef %s - f(x) %s", cadena,
				getIntValue(), COEFICIENTE, getFunctionValue());
	}

	private String genCadena() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < LONGITUD; i++) {
			sb.append(ranBin());
		}
		return sb.toString();
	}

	private Integer ranBin() {
		Double random = Math.random();
		return random > 0.5d ? 1 : 0;
	}

	public Double getFunctionValue() {
		return Math.pow(getIntValue() / COEFICIENTE, 2);
	}

	public Integer getIntValue() {
		return Integer.parseInt(cadena, 2);
	}

	public String getCadena() {
		return cadena;
	}
}

package com.utn.ag.algoritmocanonico.model;

import java.util.Random;

public class Cromosoma {
	private static final Integer LONGITUD = 30;
	private static final Double COEFICIENTE = Math.pow(2, LONGITUD) - 1;
	private String cadena;
	private Double fitness;   
	private Double sumaAcumulada;

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
	
	public void mutarBit() {
		Random ran = new Random();
		int index = ran.nextInt(cadena.length());
		StringBuilder builder = new StringBuilder(cadena);
		if (cadena.charAt(index) == '0') {
			builder.setCharAt(index, '1');
		} else {
			builder.setCharAt(index, '0');

		}

		cadena = builder.toString();

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
	
	public  void setFitness (Double fitness) {		//Esteban
		this.fitness = fitness;		
	}
	
	public Double getFitness (){
		return this.fitness;
		
		
	}

	public Double getSumaAcumulada() {
		return sumaAcumulada;
	}

	public void setSumaAcumulada(Double sumaAcumulada) {
		this.sumaAcumulada = sumaAcumulada;
	}
	
	
	
	
	
	
	
}

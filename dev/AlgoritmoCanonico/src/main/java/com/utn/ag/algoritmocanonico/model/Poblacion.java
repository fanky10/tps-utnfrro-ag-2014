package com.utn.ag.algoritmocanonico.model;

import java.util.ArrayList;

public class Poblacion extends ArrayList<Cromosoma> {

	private static Integer ID = 0;

	public Poblacion() {
		ID++; // increment id (:
	}

	private Double getSum() {
		Double sum = 0d;
		for (Cromosoma c : this) {
			sum += c.getFunctionValue();
		}
		return sum;
	}

	private Double getMax() {
		Double max = 0d;
		for (Cromosoma c : this) {
			if (max < c.getFunctionValue()) {
				max = c.getFunctionValue();
			}
		}
		return max;
	}

	public void showInforme() {
		Double sum = getSum();
		Double prom = getSum() / this.size();
		Double max = getMax();
		int i = 1;
		System.out.println("Poblacion " + ID);
		for (Cromosoma c : this) {
			System.out.println(i + ": " + c.toString() + " - fit: "
					+ c.getFunctionValue() / sum);
			i++;
		}
		System.out.println("Suma: " + sum);
		System.out.println("Promedio: " + prom);
		System.out.println("Maximo: " + max);

	}
}

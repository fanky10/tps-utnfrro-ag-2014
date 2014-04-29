package com.utn.ag.algoritmocanonico.service;

import com.utn.ag.algoritmocanonico.AppConstants;
import com.utn.ag.algoritmocanonico.model.Poblacion;

public interface AlgoritmoCanonico {
	public static final Double PROBABILIDAD_MUTACION = AppConstants.PROBCROSSOVER;
	public static final Double PROBABILIDAD_CROSSOVER = AppConstants.PROBCROSSOVER;

	public Poblacion nuevaPoblacion(Poblacion poblacion);
}

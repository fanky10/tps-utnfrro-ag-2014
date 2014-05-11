package com.utn.ag.algoritmocanonico.service;

import com.utn.ag.algoritmocanonico.AppConstants;
import com.utn.ag.algoritmocanonico.model.Poblacion;

public interface AlgoritmoCanonico {
	public static final Double PROBABILIDAD_MUTACION = AppConstants.PROBMUTACION;
	public static final Double PROBABILIDAD_CROSSOVER = AppConstants.PROBCROSSOVER;
	
	public Poblacion nuevaPoblacion();
	public Poblacion nuevaPoblacion(Poblacion poblacion);
}

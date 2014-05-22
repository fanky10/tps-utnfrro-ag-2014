package com.utn.ag.algoritmocanonico.util;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.utn.ag.algoritmocanonico.MockedLogger;
import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;

/*
 * esta clase no es estrictamente de test
 * pero la vamos a utilizar para mejorar el algoritmo de seleccion
 */
public class RouletteWheelSelectionTest{
	
	private Poblacion poblacion;
	
	@Before
	public void buildData(){
		MockedLogger.DEBUG = true;
		MockedLogger.VERBOSE = true;
		poblacion = new Poblacion();
		Cromosoma c = new Cromosoma();
		c.setFitness(0.8);
		poblacion.add(c);
		double sumFitnes = c.getFitness();
		while(sumFitnes<1){
			double fitness = 0.02d;
			c = new Cromosoma();
			c.setFitness(fitness);
			poblacion.add(c);
			sumFitnes += fitness;
		}
		poblacion.getInformeVO();
	}
	
	@Test
	public void checkData(){
		Poblacion newPoblacion = RouletteWheelSelection.select(poblacion,new Random());
		newPoblacion.getInformeVO();
		assert(true);
	}
	
}

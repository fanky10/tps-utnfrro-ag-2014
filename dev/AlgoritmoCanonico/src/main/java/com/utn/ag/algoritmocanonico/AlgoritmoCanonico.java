package com.utn.ag.algoritmocanonico;

import java.util.Random;

import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;

public class AlgoritmoCanonico {

	private static Double probmutacion;
	private static Double probcrossover;

	private int[][] ruleta;

	private void seleccionRuleta(Poblacion poblacion) {
		ruleta = new int[poblacion.size() / 2][2];
		Double sumaAcumulada = 0d;
		Random ran = new Random();

		for (Cromosoma c : poblacion) {
			sumaAcumulada = sumaAcumulada + c.getFitness();
			c.setSumaAcumulada(sumaAcumulada);

		}

		for (int i = 0; i < poblacion.size() / 2; i++) {

			for (int j = 0; j < poblacion.size(); j++) {

				if (poblacion.get(j).getSumaAcumulada() > ran.nextDouble()) {
					ruleta[i][0] = j;
					break;
				}
			}

			for (int j = 0; j < poblacion.size(); j++) {

				if (poblacion.get(j).getSumaAcumulada() > ran.nextDouble()) {
					ruleta[i][1] = j;
					break;
				}
			}

			if(App.VERBOSE) System.out.println("Seleccionado: " + (ruleta[i][0] + 1) + " y "
					+ (ruleta[i][1] + 1));

		}

	}

	private Cromosoma operadorMutacion(Cromosoma cromosoma) {

		if (Math.random() <= probmutacion) {
			cromosoma.mutarBit();
		}
		return cromosoma;

	}

	private Cromosoma[] operadorCrossover(Cromosoma[] cromosomas) {
		String tempA[] = new String[2];
		String tempB[] = new String[2];
		int largoGenoma = cromosomas[0].getGenoma().length();

		if (Math.random() <= probcrossover) {
			if(App.VERBOSE) System.out.println("PADRES " + cromosomas[0].getCadena() +" | "+ cromosomas[1].getCadena());	
			tempA[0] = cromosomas[0].getCadena().substring(0, largoGenoma / 2);
			tempA[1] = cromosomas[0].getCadena().substring(largoGenoma / 2);

			tempB[0] = cromosomas[1].getCadena().substring(0, largoGenoma / 2);
			tempB[1] = cromosomas[1].getCadena().substring(largoGenoma / 2);

			cromosomas[0] = new Cromosoma(tempA[0] + tempB[1]);
			cromosomas[1] = new Cromosoma(tempB[0] + tempA[1]);
		}

		cromosomas[0] = operadorMutacion(cromosomas[0]);
		cromosomas[1] = operadorMutacion(cromosomas[1]);
		
		if(App.VERBOSE) System.out.println("DESEND " + cromosomas[0].getCadena() +" | "+ cromosomas[1].getCadena());	

		return cromosomas;
	}

	public AlgoritmoCanonico(Double probcrossover, Double promutacion) {
		this.probcrossover = probcrossover;
		this.probmutacion = probcrossover;

	}

	public Poblacion siguienteGeneracion(Poblacion poblacion) {
		poblacion.processFitness();
		seleccionRuleta(poblacion);
		Poblacion hijos = new Poblacion();
		Cromosoma[] parCromosomas = new Cromosoma[2];

		for (int i = 0; i < poblacion.size() / 2; i++) {
			parCromosomas[0] = poblacion.get(ruleta[i][0]);
			parCromosomas[1] = poblacion.get(ruleta[i][1]);

			parCromosomas = operadorCrossover(parCromosomas);

			hijos.add(parCromosomas[0]);
			hijos.add(parCromosomas[1]);

		}

		// TODO: Implementar Crossover y a cada hijo aplicarle operadorMutacion

		return hijos;
	}

}

package com.utn.ag.viajante.impl;

import java.util.Random;









import com.utn.ag.algoritmocanonico.MockedLogger;
import com.utn.ag.algoritmocanonico.util.RouletteWheelSelection;
import com.utn.ag.viajante.model.Cromosoma;
import com.utn.ag.viajante.model.Poblacion;

public class GeneticoEst implements Geneticos{

	static int CANT_POBLACION = 50;
	static int CANT_CICLOS = 200;
	static double PROBABILIDAD_CROSSOVER = 0.1 ;
	static double  PROBABILIDAD_MUTACION = 0.05 ;
	public static Random r = new Random();
	
	
	

	public Poblacion nuevaPoblacion() {
		Poblacion poblacion = new Poblacion();
		for (int i = 0; i < CANT_POBLACION; i++) {
			Cromosoma c = new Cromosoma();
			poblacion.add(c);
		}
		poblacion.processFitness();
		return poblacion;
	}
	
	
	public Poblacion nuevaPoblacion(Poblacion poblacionActual) {
		Random rouletteRan = new Random();
		// ruleta n veces por cantidad de poblacion actual
		
		//TODO RULETA y SELECCION
		
		Poblacion nuevaPoblacion = new Poblacion();
		
		Poblacion poblacionSeleccionada = null; // TODO
		
		// aplicamos crossover cada dos
		// aplicamos mutacion a cada cromosoma generado
		for (int i = 0; i < poblacionSeleccionada.size(); i += 2) {
			int[] genoma1 = poblacionSeleccionada.get(i).getCiudades();
			int[] genoma2 = poblacionSeleccionada.get(i + 1).getCiudades();
			

			  int nuevosGenomas[][] = aplicarCrossover(genoma1, genoma2);
			Cromosoma c1 = new Cromosoma(nuevosGenomas[0]);
			Cromosoma c2 = new Cromosoma(nuevosGenomas[1]);
			/*debugCromosoma("Hijo: 1 ", c1);
			debugCromosoma("Hijo: 2 ", c2);*/
			if (aplicarMutacion(c1)) {
				
			}
			if (aplicarMutacion(c2)) {
				//debugCromosoma("Hijo: 2 Mutado! ", c2);
			}
		
			nuevaPoblacion.add(c1);
			nuevaPoblacion.add(c2);
		}
		nuevaPoblacion.processFitness();
		return nuevaPoblacion;
	}
	
	
	public int[][] aplicarCrossover(int[] g1, int[] g2) {
		
		int[][] temp = new int[2][23];
		
		if (!aplicarCrossover()) {
			MockedLogger.debug("NOT aplicar crossover!!");
			
			temp[0] = g1;
			temp[1] = g2;
			return temp;
		}
		MockedLogger.debug("a aplicar crossover!!");
		if (g1.length != g2.length) {
			throw new IllegalArgumentException(
					"cromosomas con genomas de distintas longitudes");
		}
		// crossover de un corte
		// donde se corta
		Random ran = new Random();
		int indexCorte = ran.nextInt(g1.length);

		MockedLogger.debug("indexCorte " + indexCorte);
		//TODO se intercambian
		/*StringBuilder genoma1 = new StringBuilder();
		StringBuilder genoma2 = new StringBuilder();
		genoma1.append(g1.substring(0, indexCorte));
		genoma1.append(g2.substring(indexCorte));

		genoma2.append(g2.substring(0, indexCorte));
		genoma2.append(g1.substring(indexCorte));*/
		// se sobreescribe
		//temp[0] = genoma1;
		//temp[1]  = genoma2;
		return temp;

	}


	public boolean aplicarMutacion(Cromosoma c1) {
		// ver si debo mutar por probabilidad
		// si debo mutar, ver que inidice mutar.
		boolean aplicarMutacion = aplicarMutacion();
		if (aplicarMutacion) {
			
			int nuevoRecorrido[] = mutarBit(c1.getCiudades());
			c1.setGenoma(nuevoRecorrido);
		}
		return aplicarMutacion;
	}

	public int[] mutarBit(int recorrido[]) {
		
		Random ran = new Random();
		
		int index1 = ran.nextInt(recorrido.length);
		int index2 = ran.nextInt(recorrido.length);
		
		int tmp = recorrido [index1];
		recorrido[index1] = recorrido[index2];
		recorrido[index2] = tmp;
		
		
		
		return recorrido;
	}

	private boolean aplicarMutacion() {
		return new Random().nextDouble() <= PROBABILIDAD_MUTACION;
	}
	
	private boolean aplicarCrossover() {
		return (new Random().nextDouble() <= PROBABILIDAD_CROSSOVER);
	}
	
}

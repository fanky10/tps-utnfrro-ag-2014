package com.utn.ag.viajante.impl;

import java.util.Random;

import com.utn.ag.viajante.model.Cromosoma;
import com.utn.ag.viajante.model.Poblacion;
import com.utn.ag.viajante.model.RouletteWheelSelection;

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
		
	
		
		Poblacion poblacionSeleccionada = RouletteWheelSelection
				.select(poblacionActual,rouletteRan);
		

		
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
			
			
			temp[0] = g1;
			temp[1] = g2;
			return temp;
		}
		
		if (g1.length != g2.length) {
			throw new IllegalArgumentException(
					"cromosomas con genomas de distintas longitudes");
		}
				
		Random ran = new Random();
		
		
		int j, k , n;
		
		int hijo1[] = new int[23];
		int hijo2[] = new int[23];
		
		for (int t : hijo1){
			t = -1;					
		}
		
		for (int t : hijo2){
			t = -1;					
		}
		
		n=0;		
		hijo1[n] = g1[n];
		boolean sigue = true;
		while(sigue){		
		
		for(int t : g1){
			if (t == g2[n]) {
				break;
			}
			
			n++;
		}		
		
		hijo1[n] = g1[n];
		
		for ( int t : hijo1){
			
			if (t == g2[n]){
				sigue = false;
			}
		
		}
		
		
		}
		
		
		
		
		
		
		
		n=0;		
		hijo2[n] = g2[n];
		 sigue = true;
		while(sigue){		
		
		for(int t : g2){
			if (t == g1[n]) {
				break;
			}
			
			n++;
		}		
		
		hijo2[n] = g2[n];
		
		for ( int t : hijo2){
			
			if (t == g1[n]){
				sigue = false;
			}
		
		}
		
		
		}
		
		
		
		temp[0] = hijo1;
		temp[1]  = hijo2;
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

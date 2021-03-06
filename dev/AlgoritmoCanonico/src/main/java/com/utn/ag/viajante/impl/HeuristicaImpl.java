package com.utn.ag.viajante.impl;

import com.utn.ag.viajante.model.Constants;

public class HeuristicaImpl implements Heuristica {
	// La Heuristica aplicada es
	// "Desde cada ciudad ir a la ciudad más cercana no visitada"

	int[] recorrido = new int[Constants.CANTIDAD_PROVINCIAS + 1];

	int ciudadOrigen;

	public void resolverHeuristica() {

		for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {
			calcularRecorrido(i);
			imprimirRecorridoNombre();
		}

	}
	
	

	public void calcularRecorrido(int ciudadInicial) {
		ciudadOrigen = ciudadInicial;
		recorrido = new int[Constants.CANTIDAD_PROVINCIAS + 1];
		// Inicializar TODO

		recorrido[0] = ciudadOrigen;

		for (int i = 1; i < Constants.CANTIDAD_PROVINCIAS; i++) {

			if (cantidadSinVisitar() > 0) {

				recorrido[i] = getCiudadCercana(recorrido[i - 1]);
			}

		}

		recorrido[Constants.CANTIDAD_PROVINCIAS] = ciudadOrigen;

		// imprimirRecorridoNumeros();
	}

	public int ultimaSinVisitar() {
		int res = 0;

		for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {

			res = res + i;

		}

		for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {

			res = res - recorrido[i];

		}

		return res;
	}

	public int cantidadSinVisitar() {
		int res = 0;

		for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {

			if (fueVisitada(i) == false) {
				res++;
			}

		}

		return res;
	}

	public boolean fueVisitada(int ciudad) {
		boolean res = false;

		for (int i : recorrido) {
			if (i == ciudad)
				res = true;
		}

		return res;

	}

	public int getDistancia(int i, int j) {

		return Constants.TABLA_DISTANCIAS[i][j];

	}

	public int getCiudadCercana(int origen) {
		int res = 2;
		// Encontrar
		// una
		// ciudad
		// para
		// referencia
		// que no
		// haya sido
		// visitada

		for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {
			if (fueVisitada(res))
				res++;

		}

		if (origen == 2)
			res = 1;

		for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {

			if (fueVisitada(i) == false & origen != i
					& getDistancia(origen, res) > getDistancia(origen, i)) {

				res = i;
			}

		}

		// String debug = Constants.nombresProvincias[origen] +
		// " tiene mas cerca a " + Constants.nombresProvincias[res] +
		// " estando a " + getDistancia(origen, res);
		// System.out.println(debug);

		return res;
	}

	public void imprimirRecorridoNombre() {

		String recorridoNombre = Constants.NOMBRES_PROVINCIAS[ciudadOrigen];

		for (int i = 1; i < Constants.CANTIDAD_PROVINCIAS + 1; i++) {

			recorridoNombre = recorridoNombre + ", "
					+ Constants.NOMBRES_PROVINCIAS[recorrido[i]];

		}

		System.out.println(recorridoNombre);

	}

	public void imprimirRecorridoNumeros() {

		String recorridoNombre = String.valueOf(ciudadOrigen);

		for (int i = 1; i < Constants.CANTIDAD_PROVINCIAS + 1; i++) {

			recorridoNombre = recorridoNombre + ", " + recorrido[i];

		}

		System.out.println(recorridoNombre);

	}

	public int getDistanciaRecorrido() {
		int distanciaRecorrida = 0;

		for (int i = 1; i < recorrido.length; i++) {

			distanciaRecorrida = distanciaRecorrida
					+ getDistancia(recorrido[i - 1], recorrido[i]);

			System.out.println(Constants.NOMBRES_PROVINCIAS[recorrido[i - 1]]
					+ " a " + Constants.NOMBRES_PROVINCIAS[recorrido[i]] + " "
					+ getDistancia(recorrido[i - 1], recorrido[i]));

		}

		return distanciaRecorrida;
	}



	public int[] getRecorridoCiudad(int idx) {
		// TODO Auto-generated method stub
		return null;
	}



	public void printRecorrido() {
		// TODO Auto-generated method stub
		
	}



	public void resolverCiudades() {
		// TODO Auto-generated method stub
		
	}

}

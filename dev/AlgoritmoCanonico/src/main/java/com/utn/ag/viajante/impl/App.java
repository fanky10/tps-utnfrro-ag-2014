package com.utn.ag.viajante.impl;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
		contarProvinciasYGenerarTablas();

		Heuristica heur = new HeuristicaImpl();

		heur.resolverCiudades();

	}

	public static void contarProvinciasYGenerarTablas() {

	}

}

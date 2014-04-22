package com.utn.ag.algoritmocanonico;

public class MockedLogger {
	// Mostrar selecciones y resultados de crossover
	public static final Boolean VERBOSE = true;
	private static final Boolean DEBUG = Boolean.FALSE;
	
	public static void verbose(String text) {
		if (VERBOSE) {
			System.out.println(text);
		}
	}

	public static void debug(String txt) {
		if (DEBUG) {
			System.out.println("DEBUG: " + txt);
		}
	}
}

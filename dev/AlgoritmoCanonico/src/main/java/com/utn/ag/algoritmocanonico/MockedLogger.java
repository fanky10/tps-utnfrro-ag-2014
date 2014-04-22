package com.utn.ag.algoritmocanonico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MockedLogger {
	// Mostrar selecciones y resultados de crossover
	public static final Boolean VERBOSE = Boolean.FALSE;
	private static final Boolean DEBUG = Boolean.FALSE;
	private static final Boolean WRITE_FILE = Boolean.TRUE;
	

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

	public static void informe(String txt) {
		System.out.println(txt);
		if (WRITE_FILE) {
			writeToFile(txt);
		}
	}

	private static void writeToFile(String text) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(AppConstants.FILE_INFORME, true)));
			out.println(text);
			out.close();
		} catch (IOException ex) {
			System.out.println("ERROR FILE NOT FOUND: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

}

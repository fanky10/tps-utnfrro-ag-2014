package com.utn.ag.algoritmocanonico;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.utn.ag.algoritmocanonico.vo.InformeVO;

public class MockedLogger {
	// Mostrar selecciones y resultados de crossover
	public static Boolean VERBOSE = Boolean.FALSE;
	public static Boolean DEBUG = Boolean.FALSE;
	public static Boolean WRITE_FILE = Boolean.FALSE;

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

	public static void writeInforme(InformeVO informeVO) {
		MockedLogger.informe(informeVO.getMax() + "," + informeVO.getMin()
				+ "," + informeVO.getProm());
	}

	public static void informe(String txt) {
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

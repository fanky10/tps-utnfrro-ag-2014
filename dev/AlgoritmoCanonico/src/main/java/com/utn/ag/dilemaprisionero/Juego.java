package com.utn.ag.dilemaprisionero;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.utn.ag.algoritmocanonico.MockedLogger;

public class Juego {

	public static final List<String> JUGADAS_POSIBLES = new ArrayList<String>();
	public static final Map<String, String> TIT_FOR_TAT = new LinkedHashMap<String, String>();
	public static final Map<String, String> PUNTAJE = new LinkedHashMap<String, String>();

	static {
		PermutationsWithRepetition genPares = new PermutationsWithRepetition(
				new String[] { "A", "N" });
		List<String> vParesPosibles = genPares.getVariations();
		PermutationsWithRepetition gen = new PermutationsWithRepetition(
				new String[] { "A", "N" }, 4);
		List<String> vEstrategias = gen.getVariations();
		for (String par : vParesPosibles) {
			for (String s : vEstrategias) {
				StringBuilder sb = new StringBuilder();
				sb.append(par).append(s);
				JUGADAS_POSIBLES.add(sb.toString());
			}
		}

		TIT_FOR_TAT.put("AA", "A");
		TIT_FOR_TAT.put("AN", "N");
		TIT_FOR_TAT.put("NA", "A");
		TIT_FOR_TAT.put("NN", "N");

		PUNTAJE.put("NN", "4,4");
		PUNTAJE.put("AN", "5,0");
		PUNTAJE.put("NA", "0,5");
		PUNTAJE.put("AA", "1,1");

	}
	private String parJugadorX;
	private String parJugadorY;
	private List<String> jugadasJugadorX = new ArrayList<String>();
	private List<String> jugadasJugadorY = new ArrayList<String>();
	private int puntosX;
	private int puntosY;

	public Juego() {
		this.parJugadorX = "AA";
		this.parJugadorY = "NN";
	}

	public Juego(String parJugadorX, String parJugadorY) {
		super();
		this.parJugadorX = parJugadorX;
		this.parJugadorY = parJugadorY;
	}

	private void seleccionaPares() {
		for (String jugada : JUGADAS_POSIBLES) {
			if (jugada.startsWith(parJugadorX)) {
				jugadasJugadorX.add(jugada);
			}
		}
		for (String jugada : JUGADAS_POSIBLES) {
			if (jugada.startsWith(parJugadorY)) {
				jugadasJugadorY.add(jugada);
			}
		}
	}

	private void juegaPares() {
		for (int i = 0; i < jugadasJugadorX.size(); i++) {
			String jugadasX = jugadasJugadorX.get(i);
			String jugadasY = jugadasJugadorY.get(i);
			// TODO: puntajes por cada uno
			// sumar todos y me quedo con la mejor tira de 6 y 6 
			int idx = 0;
			for(int j = 2;j<jugadasX.length();j+=2){
				String x = TIT_FOR_TAT.get(jugadasX.substring(idx,j));
				String y = TIT_FOR_TAT.get(jugadasY.substring(idx,j));
				jugada(x, y);
				idx = j;
			}
			
		}
	}

	private int jugada(String x, String y) {
		String puntaje = PUNTAJE.get(x + y);
		int puntajeX = Integer.parseInt(puntaje.split(",")[0]);
		int puntajeY = Integer.parseInt(puntaje.split(",")[1]);
		MockedLogger.debug("puntaje: [x,y] ["+puntajeX+" , "+puntajeY+"]");
		return puntajeX + puntajeY;
	}

	public int jugar() {
		//TODO: ver cual es el menor puntaje
		seleccionaPares();
		juegaPares();
		MockedLogger.debug("Puntos jugador x: " + puntosX);
		MockedLogger.debug("Puntos jugador y: " + puntosY);
		return puntosX + puntosY;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.viajante.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.utn.ag.viajante.model.Constants;

/**
 *
 * @author fanky
 */
public class Exhaustivo {
	private static final boolean DEBUG = true;
	private static final boolean OUTPUT_INTO_FILE = true;

	/**
	 * 
	 *
	 */
	public static void main(String args[]) {
		recorrer();
	}

	private static final int[][] DISTANCIAS = { { 0, 187, 164, 218, 503 },
			{ 187, 0, 346, 117, 316 }, { 164, 346, 0, 354, 662 },
			{ 218, 117, 354, 0, 382 }, { 503, 316, 662, 382, 0 }, };

	public static void recorrer() {
		int w = 0;
		List<Integer> recorridoInicial = new ArrayList<Integer>();
		List<Integer> distanciasInicial = new ArrayList<Integer>();
		for (int i = 0; i < DISTANCIAS.length; i++) {
			int toAdd = 0;
			if (i + 1 == DISTANCIAS.length) {
				toAdd = DISTANCIAS[i][0];
			} else {
				toAdd = DISTANCIAS[i][i + 1];

			}
			recorridoInicial.add(i);
			distanciasInicial.add(toAdd);
			w += toAdd;

		}
		debug("recorrido inicial: " + recorridoInicial);
		debug("dist inicial: " + distanciasInicial);
		debug("dist: " + w);
		debug("======== SEARCH =========");
		int ptoInicial = 0;
		Search s = new Search(ptoInicial);
		Search bestSoFar = new Search(recorridoInicial, distanciasInicial, w);
		search(s, bestSoFar, ptoInicial);
		System.out.println("Best: " + bestSoFar);
		System.out.println("Ciudades: ");
		for (int i : bestSoFar.recorrido) {
			System.out.println(Constants.NOMBRES_CIUDADES_SANTA_FE[i]);
		}
	}

	/**
	 * @param s
	 * @param bestSoFar
	 */
	private static void search(Search s, Search bestSoFar, int inicial) {
		List<Integer> searchRoute = s.recorrido;
		List<Integer> searchDist = s.distancias;
		int searchW = s.w;
		int wB = bestSoFar.w;
		int newW = 0;
		int lastDest = searchRoute.get(searchRoute.size() - 1);
		if (searchRoute.size() == bestSoFar.recorrido.size()) {
			newW = searchW + DISTANCIAS[lastDest][inicial];
			searchDist.add(DISTANCIAS[lastDest][inicial]);
			s.w = newW;
			if (newW < wB) {
				bestSoFar.recorrido = new ArrayList<Integer>(searchRoute);
				bestSoFar.w = newW;
			}
			debug("##----- END Line Posibility ----##");
			debug("##----- Current " + s + " ----##");
			debug("##----- Best " + bestSoFar + " ----##");
		} else {
			List<Integer> notIncluded = getNotIncluded(searchRoute);
			debug(">> not included: " + notIncluded);
			for (int j : notIncluded) {
				int toDist = DISTANCIAS[lastDest][j];
				debug(">> current S: " + searchRoute);
				debug(">> from: " + lastDest + " -> to: " + j + " dist: "
						+ toDist);

				newW = searchW + toDist;
				debug(">> newW: " + newW);
				if (newW < wB) {
					List<Integer> newRoute = new ArrayList<Integer>(searchRoute);
					newRoute.add(j);
					List<Integer> newDist = new ArrayList<Integer>(searchDist);
					newDist.add(toDist);
					s = new Search(newRoute, newDist, newW);
					search(s, bestSoFar, inicial);
				}
			}
		}
	}

	public static List<Integer> getNotIncluded(List<Integer> searchRoute) {
		List<Integer> notIncluded = new ArrayList<Integer>();
		for (int j = 0; j < DISTANCIAS.length; j++) {
			if (!isContained(j, searchRoute)) {
				notIncluded.add(j);
			}
		}
		return notIncluded;
	}

	public static boolean isContained(int key, List<Integer> values) {
		for (int i : values) {
			if (key == i) {
				return true;
			}
		}
		return false;
	}

	private static void debug(String text) {
		if (DEBUG) {
			System.out.println("DEBUG: " + text);
		}
		if (OUTPUT_INTO_FILE) {
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter("out.txt", true)));
				out.println("DEBUG: " + text);
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	static class Search {

		List<Integer> recorrido;
		List<Integer> distancias;
		int w;

		public Search(int inicial) {
			recorrido = new ArrayList<Integer>();
			recorrido.add(inicial);
			distancias = new ArrayList<Integer>();
			w = 0;
		}

		public Search(List<Integer> recorrido, List<Integer> distancias, int w) {
			this.recorrido = recorrido;
			this.distancias = distancias;
			this.w = w;
		}

		@Override
		public String toString() {
			return String.format("%s %s %s", recorrido, distancias, w);
		}

	}

}

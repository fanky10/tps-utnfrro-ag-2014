/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.viajante.impl;

import java.io.BufferedWriter;
import java.io.File;
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
	private static final boolean DEBUG = false;
	private static final boolean OUTPUT_INTO_FILE = false;
	private static final String OUTPUT_FILE_PATH = "out.txt";

	private int[][] distancias;
	private String[] nombres;
	private int puntoInicial;
	private List<Integer> mejorRecorrido;

	public Exhaustivo(int[][] distancias, String[] nombres, int puntoInicial) {
		super();
		this.distancias = distancias;
		this.nombres = nombres;
		this.puntoInicial = puntoInicial;
	}

	public List<Integer> getMejorRecorrido() {
		return mejorRecorrido;
	}

	/**
	 * 
	 *
	 */
	public static void main(String args[]) {
		initFile();
		new Exhaustivo(Constants.DISTANCIAS_CIUDADES_SANTA_FE,Constants.NOMBRES_CIUDADES_SANTA_FE,0).recorrer();;
	}

	public void recorrer() {
		int w = 0;
		List<Integer> recorridoInicial = new ArrayList<Integer>();
		List<Integer> distanciasInicial = new ArrayList<Integer>();
		for (int i = 0; i < distancias.length; i++) {
			int toAdd = 0;
			if (i + 1 == distancias.length) {
				toAdd = distancias[i][0];
			} else {
				toAdd = distancias[i][i + 1];

			}
			recorridoInicial.add(i);
			distanciasInicial.add(toAdd);
			w += toAdd;

		}
		debug("recorrido inicial: " + recorridoInicial);
		debug("dist inicial: " + distanciasInicial);
		debug("dist: " + w);
		debug("======== SEARCH =========");
		Search s = new Search(puntoInicial);
		Search bestSoFar = new Search(recorridoInicial, distanciasInicial, w);
		search(s, bestSoFar, puntoInicial);
		System.out.println("Best: " + bestSoFar);
		System.out.println("Ciudades: ");
		for (int i : bestSoFar.recorrido) {
			System.out.println(nombres[i]);
		}
	}

	/**
	 * @param s
	 * @param bestSoFar
	 */
	private void search(Search s, Search bestSoFar, int inicial) {
		List<Integer> searchRoute = s.recorrido;
		List<Integer> searchDist = s.distancias;
		int searchW = s.w;
		int wB = bestSoFar.w;
		int newW = 0;
		int lastDest = searchRoute.get(searchRoute.size() - 1);
		if (searchRoute.size() == bestSoFar.recorrido.size()) {
			newW = searchW + distancias[lastDest][inicial];
			searchDist.add(distancias[lastDest][inicial]);
			s.w = newW;
			if (newW < wB) {
				bestSoFar.recorrido = new ArrayList<Integer>(searchRoute);
				bestSoFar.distancias = new ArrayList<Integer>(searchDist);
				bestSoFar.w = newW;
				debug("##----- NEW BEST!! ----##");
			}
			debug("##----- END Line Posibility ----##");
			debug("##----- Current " + s + " ----##");
			debug("##----- Best " + bestSoFar + " ----##");
		} else {
			List<Integer> notIncluded = getNotIncluded(searchRoute);
			debug(">> not included: " + notIncluded);
			for (int j : notIncluded) {
				int toDist = distancias[lastDest][j];
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

	public List<Integer> getNotIncluded(List<Integer> searchRoute) {
		List<Integer> notIncluded = new ArrayList<Integer>();
		for (int j = 0; j < distancias.length; j++) {
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

	private static void initFile() {
		if (OUTPUT_INTO_FILE) {
			File f = new File(OUTPUT_FILE_PATH);
			if (f.exists()) {
				f.delete();
			}
		}
	}

	private static void debug(String text) {
		if (DEBUG) {
			System.out.println("DEBUG: " + text);
		}
		if (OUTPUT_INTO_FILE) {
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter(OUTPUT_FILE_PATH, true)));
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

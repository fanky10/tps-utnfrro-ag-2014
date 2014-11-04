/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.dilemaprisionero;

import com.utn.ag.algoritmocanonico.MockedLogger;
import java.util.List;

/**
 *
 * @author fanky
 */
public class App {
	public static void main(String args[]) {
		MockedLogger.DEBUG = true;
		new Juego().jugar();

//		MockedLogger.debug("--- PARES POSIBLES:");
//		PermutationsWithRepetition genPares = new PermutationsWithRepetition(
//				new String[] { "A", "N" });
//		List<String> vParesPosibles = genPares.getVariations();
//		int count = 1;
//		for (String s : vParesPosibles) {
//			MockedLogger.debug(count + " generated: " + s);
//			count++;
//		}
//		MockedLogger.debug("--- ESTRATEGIAS:");
//		PermutationsWithRepetition gen = new PermutationsWithRepetition(
//				new String[] { "A", "N" }, 4);
//		List<String> vEstrategias = gen.getVariations();
//		count = 1;
//		for (String s : vEstrategias) {
//			MockedLogger.debug(count + " generated: " + s);
//			count++;
//		}
//
//		MockedLogger.debug("--- ESTRATEGIAS COMPLETAS:");
//		count = 1;
//		for (String par : vParesPosibles) {
//			for (String s : vEstrategias) {
//				StringBuilder sb = new StringBuilder();
//				sb.append(par).append(s);
//				MockedLogger.debug(count + " generated: " + sb.toString());
//				count++;
//			}
//		}
	}
}

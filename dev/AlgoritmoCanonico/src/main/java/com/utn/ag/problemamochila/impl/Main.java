package com.utn.ag.problemamochila.impl;

import java.util.ArrayList;
import java.util.List;

import com.utn.ag.problemamochila.model.Bag;
import com.utn.ag.problemamochila.model.Constants;
import com.utn.ag.problemamochila.model.House;
import com.utn.ag.problemamochila.model.Item;

public class Main {
	public static void main(String[] args) {
		Bag mochila = new Bag(Constants.BAG_VOLUME);
		House casa = new House(Constants.ITEMS);

		System.out.println("\n\nImplementacion Algoritmo EXHAUSTIVO:");
		Exhaustive algExhaustivo = new Exhaustive();
		algExhaustivo.fillBag(mochila, casa);
		printBagInfo(mochila);
		// Limpia antes de empezar el GREEDY
		mochila.getItems().clear();

		System.out.println("\n\nImplementacion Algoritmo GREEDY:");
		Greedy algGreedy = new Greedy();
		algGreedy.sortProportion(casa.getItems());
		algGreedy.fillBag(mochila, casa);
		printBagInfo(mochila);
	}

	public static void printBagInfo(Bag mochila) {
		System.out.println("Mochila:");
		System.out
				.printf("Objeto\tValor($)\tVolumen(cm³)\tProporcion($/cm³)\n");
		float valueSum = 0;
		for (Item i : mochila.getItems()) {
			System.out.printf("%-1s\t %-10s\t %-15s %-10s\n", i.getName(),
					i.getValue(), i.getVolume(), i.getvalueVolumeProportion());
			valueSum += i.getValue();
		}
		System.out.println(" \n*Cantidad de objetos:" + mochila.getItems().size()
				+ " \n*Valor total de items en mochila: $" + valueSum
				+ " \n*Volumen Sobrante: " + mochila.getRemainingVolume());

	}
}

package com.utn.ag.problemamochila.impl;

import java.util.ArrayList;
import java.util.List;

import com.utn.ag.problemamochila.model.Bag;
import com.utn.ag.problemamochila.model.Constants;
import com.utn.ag.problemamochila.model.House;
import com.utn.ag.problemamochila.model.Item;

public class Main {
	public static List<Item> ITEMS = new ArrayList<Item>();
	static {
		ITEMS.add(new Item(String.valueOf(1), 150, 20));
		ITEMS.add(new Item(String.valueOf(2), 325, 40));
		ITEMS.add(new Item(String.valueOf(3), 600, 50));
		ITEMS.add(new Item(String.valueOf(4), 805, 36));
		ITEMS.add(new Item(String.valueOf(5), 430, 25));
		ITEMS.add(new Item(String.valueOf(6), 1200, 64));
		ITEMS.add(new Item(String.valueOf(7), 770, 54));
		ITEMS.add(new Item(String.valueOf(8), 60, 18));
		ITEMS.add(new Item(String.valueOf(9), 930, 46));
		ITEMS.add(new Item(String.valueOf(10), 353, 28));
	}
	public static void main(String[] args) {
		Bag mochila = new Bag(Constants.BAG_VOLUME);
		House casa = new House(ITEMS);

		System.out.println("\n\nImplementacion Algoritmo EXHAUSTIVO:");
		Exhaustive algExhaustivo = new Exhaustive();
		// COMIENZO Algoritmo Exhaustivo
		algExhaustivo.fillBag(mochila, casa);
		getBagInfo(mochila);
		mochila.getItems().clear(); // Limpia antes de empezar el GREEDY
		// FIN Algoritmo Exahustivo

		System.out.println("\n\nImplementacion Algoritmo GREEDY:");
		Greedy algGreedy = new Greedy();
		algGreedy.sortProportion(casa.getItems());
		algGreedy.fillBag(mochila, casa);
		getBagInfo(mochila);
	}

	public static void getBagInfo(Bag mochila) {
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

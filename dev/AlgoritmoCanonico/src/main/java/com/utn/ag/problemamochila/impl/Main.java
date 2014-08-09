package com.utn.ag.problemamochila.impl;

import com.utn.ag.problemamochila.model.Bag;
import com.utn.ag.problemamochila.model.Constants;
import com.utn.ag.problemamochila.model.House;
import com.utn.ag.problemamochila.model.Item;

public class Main {

	public static void main(String[] args) {

		Bag mochila = new Bag(Constants.BAG_VOLUME);
		House casa = new House();
		// Comienzo entrada manual TODO: persistencia, levantar items de archivo
		casa.items.add(new Item(String.valueOf(1), 150, 20));
		casa.items.add(new Item(String.valueOf(2), 325, 40));
		casa.items.add(new Item(String.valueOf(3), 600, 50));
		casa.items.add(new Item(String.valueOf(4), 805, 36));
		casa.items.add(new Item(String.valueOf(5), 430, 25));
		casa.items.add(new Item(String.valueOf(6), 1200, 64));
		casa.items.add(new Item(String.valueOf(7), 770, 54));
		casa.items.add(new Item(String.valueOf(8), 60, 18));
		casa.items.add(new Item(String.valueOf(9), 930, 46));
		casa.items.add(new Item(String.valueOf(10), 353, 28));
		// Fin entrada manual

		System.out.println("Implementacion Algoritmo EXAHUSTIVO:");
		Exhaustive algExhaustivo = new Exhaustive();
		//COMIENZO Algoritmo Exahustivo
		algExhaustivo.fillBag(mochila, casa);		
		
		getBagInfo(mochila);
		
		
		
		mochila.items.clear(); //Limpia antes de empezar el GREEDY
		//FIN Algoritmo Exahustivo

		System.out.println("Implementacion Algoritmo GREEDY:");

		Greedy algGreedy = new Greedy();
		algGreedy.processValueVolumeProp(casa.items);

		algGreedy.sortProportion(casa.items);

		algGreedy.fillBag(mochila, casa);

		getBagInfo(mochila);

	}
	
	public static void getBagInfo (Bag mochila){
		System.out.println("Mochila:");
		for (Item i : mochila.items) {
			System.out.println("Name:" + i.getName() + " Value:" + i.getValue()
					+ " Volume:" + i.getVolume() + " GetProportion:"
					+ i.getvalueVolumeProportion());

		}
		System.out.println("Items:" + mochila.items.size()
				+ " Volumen Sobrante: " + mochila.getRemainingVolume());
		
	}

}

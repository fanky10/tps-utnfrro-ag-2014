package com.utn.ag.problemamochila.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.utn.ag.problemamochila.model.Bag;
import com.utn.ag.problemamochila.model.House;
import com.utn.ag.problemamochila.model.Item;

public class Greedy {
	// Ordena de mayor a menor
	// segun el $i/Vi
	public void sortProportion(List<Item> items) {
		Collections.sort(items, new Comparator<Item>() {
			public int compare(Item p1, Item p2) {
				return Integer.valueOf(Float.compare(
						p1.getvalueVolumeProportion(),
						p2.getvalueVolumeProportion()));
			}
		});
		// Invierte la coleccion para que quede
		// ordenada de mayor a menor
		Collections.reverse(items);
	}

	public void fillBag(Bag mochila, House casa) {
		List<Item> houseItems = casa.getItems();
		for (int idx = 0; idx < houseItems.size(); idx++) {
			if (houseItems.get(idx).getVolume() <= mochila.getRemainingVolume()) {
				mochila.getItems().add(houseItems.get(idx));
			}
		}
	}
}

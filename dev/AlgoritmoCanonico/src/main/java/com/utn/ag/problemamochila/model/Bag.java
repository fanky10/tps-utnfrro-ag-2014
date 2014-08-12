package com.utn.ag.problemamochila.model;

import java.util.ArrayList;
import java.util.List;

public class Bag {
	private List<Item> items = new ArrayList<Item>();
	private float volume;

	public Bag(float volume) {
		this.volume = volume;
	}

	// Devuelve el volumen restante
	public float getRemainingVolume() {
		float remainingVolume = volume;
		for (Item i : items) {
			remainingVolume = remainingVolume - i.getVolume();
		}
		return remainingVolume;
	}

	public float getOriginalVolume() {
		return volume;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}

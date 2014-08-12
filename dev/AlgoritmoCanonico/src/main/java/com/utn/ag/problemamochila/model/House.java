package com.utn.ag.problemamochila.model;

import java.util.ArrayList;
import java.util.List;

public class House {
	public House() {

	}

	private List<Item> items = new ArrayList<Item>();

	public House(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return this.items;
	}

}

package com.utn.ag.problemamochila.model;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	public static float BAG_VOLUME = 4200;
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

}

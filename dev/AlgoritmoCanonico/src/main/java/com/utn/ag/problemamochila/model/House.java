package com.utn.ag.problemamochila.model;

import java.util.ArrayList;

public class House
{
	 public	ArrayList <Item> items = new ArrayList<Item>();
	 
	 public void loadValues()
	 {
		 this.items.add(new Item(String.valueOf(1), 150, 20));
		 this.items.add(new Item(String.valueOf(2), 325, 40));
		 this.items.add(new Item(String.valueOf(3), 600, 50));
		 this.items.add(new Item(String.valueOf(4), 805, 36));
		 this.items.add(new Item(String.valueOf(5), 430, 25));
		 this.items.add(new Item(String.valueOf(6), 1200, 64));
		 this.items.add(new Item(String.valueOf(7), 770, 54));
		 this.items.add(new Item(String.valueOf(8), 60, 18));
		 this.items.add(new Item(String.valueOf(9), 930, 46));
		 this.items.add(new Item(String.valueOf(10), 353, 28));
	 }
}

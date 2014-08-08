package com.utn.ag.problemamochila.impl;

import java.util.ArrayList;
import java.util.Collections;


import java.util.Comparator;

import com.utn.ag.problemamochila.model.Bag;
import com.utn.ag.problemamochila.model.Constants;
import com.utn.ag.problemamochila.model.House;
import com.utn.ag.problemamochila.model.Item;



public class Greedy {

	public void processValueVolumeProp (ArrayList <Item> items){
		
		for (Item i : items){
			i.setvalueVolumeProportion((float) i.getValue()/i.getVolume()); 
			} 
			}
	
	
	public void sortProportion (ArrayList <Item> items){ //Ordena de mayor a menor segun el $i/Vi


		Collections.sort(items, new Comparator<Item>() {
	        public int compare(Item p1, Item p2) {
	           return Integer.valueOf(Float.compare(p1.getvalueVolumeProportion(), p2.getvalueVolumeProportion()));
	        }
	});
		
		Collections.reverse(items); //Invierte la coleccion para que quede de mayor a menor
		
	}
	
	public void fillBag (Bag mochila, House casa){
		int index = 0;
		for (Item i : casa.items){
			if( casa.items.get(index).getVolume() <= mochila.getRemainingVolume() ){
				mochila.items.add(casa.items.get(index));
				//casa.items.remove(index);
				
			} 
			
			index++;
			
			
			
		}
		
		
		
	}
	 
	
	
	
	
	
	
}

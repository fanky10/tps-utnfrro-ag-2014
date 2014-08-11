package com.utn.ag.problemamochila.model;

import java.util.ArrayList;

public class Bag {
	public	ArrayList <Item> items = new ArrayList<Item>();
	private float volume;
	
	public Bag (float volume)
	{
		this.volume = volume;		
	}
	
	public float getRemainingVolume() //Devuelve el volumen restante
	{
		float remainingVolume = volume;
		for (Item i : items)
		{
			remainingVolume = remainingVolume - i.getVolume();	
		}
		return remainingVolume;
	}
	
	public float getOriginalVolume()
	{
		return volume;
	}
}

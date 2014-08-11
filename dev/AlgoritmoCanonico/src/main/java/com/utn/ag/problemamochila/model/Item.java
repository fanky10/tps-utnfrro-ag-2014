package com.utn.ag.problemamochila.model;

public class Item {

	float volume;
	float value;
	float valueVolumeProportion;
	String name;
	
	public Item (String name,float volume, float value)
	{
		this.name = name;
		this.value = value;
		this.volume = volume;
		this.valueVolumeProportion= value/volume;	
	}

	public String getName()
	{
		return name;
	}

	public float getVolume()
	{
		return volume;
	}

	public float getValue() 
	{
		return value;
	}
	
	public float getvalueVolumeProportion()
	{
		return valueVolumeProportion;
	}

	

	
	


	
}

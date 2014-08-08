package com.utn.ag.problemamochila.model;

public class Item {

	float volume;
	float value;
	float valueVolumeProportion;
	String name;
	
	
	
	public Item (String name,float volume, float value){
		this.value = value;
		this.volume = volume;
		this.name = name;
	}



	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public float getvalueVolumeProportion() {
		return valueVolumeProportion;
	}

	public void setvalueVolumeProportion(float valueVolumeProportion) {
		this.valueVolumeProportion = valueVolumeProportion;
	}
	

	
	


	
}

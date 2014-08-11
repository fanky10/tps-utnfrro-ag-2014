package com.utn.ag.problemamochila.impl;

import com.utn.ag.problemamochila.model.Bag;
import com.utn.ag.problemamochila.model.Constants;
import com.utn.ag.problemamochila.model.House;
import com.utn.ag.problemamochila.model.Item;

public class Main 
{

	public static void main(String[] args) 
	{

		Bag mochila = new Bag(Constants.BAG_VOLUME);
		House casa = new House();
		casa.loadValues();

		System.out.println("\n\nImplementacion Algoritmo EXAHUSTIVO:");
		Exhaustive algExhaustivo = new Exhaustive();
		//COMIENZO Algoritmo Exahustivo
		algExhaustivo.fillBag(mochila, casa);		
		
		getBagInfo(mochila);
		
		mochila.items.clear(); //Limpia antes de empezar el GREEDY
		//FIN Algoritmo Exahustivo

		System.out.println("\n\nImplementacion Algoritmo GREEDY:");

		Greedy algGreedy = new Greedy();
		algGreedy.sortProportion(casa.items);
		algGreedy.fillBag(mochila, casa);
		getBagInfo(mochila);
	}
	
	public static void getBagInfo (Bag mochila)
	{
		System.out.println("Mochila:");
		System.out.printf("Objeto\tValor($)\tVolumen(cm³)\tProporcion($/cm³)\n");
		float valueSum = 0;
		for (Item i : mochila.items) 
		{
			System.out.printf("%-1s\t %-10s\t %-15s %-10s\n",i.getName(), i.getValue(),
					i.getVolume(), i.getvalueVolumeProportion());
		valueSum = valueSum + i.getValue();
		}
		System.out.println("\n*Cantidad de objetos:" + mochila.items.size()+
				" \n*Valor total de items en mochila: $" + valueSum + 
				" \n*Volumen Sobrante: " + mochila.getRemainingVolume());
		
	}
}


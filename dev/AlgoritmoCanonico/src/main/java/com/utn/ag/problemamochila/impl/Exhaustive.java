package com.utn.ag.problemamochila.impl;

import java.util.ArrayList;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import com.utn.ag.problemamochila.model.Bag;
import com.utn.ag.problemamochila.model.House;
import com.utn.ag.problemamochila.model.Item;

public class Exhaustive
{

	// TODO: Implementar con esto: https://code.google.com/p/combinatoricslib/
	// BIEN FACIL... funca con Maven
	private ArrayList<ArrayList<Item>> CombinacionesDeItems = new ArrayList<ArrayList<Item>>();
	private ArrayList<ArrayList<Item>> CombinacionesRespuesta = new ArrayList<ArrayList<Item>>();

	public void fillBag(Bag mochila, House casa) 
	{
		/*
		 * Obtiene TODAS las posibles combinaciones de elementos descartando todas
		 * las que superan el volumen de la mochila 
		 * Calcula el valor$ de todas las combinaciones
		 * La combinacion/es de mayor valor es/son la/s
		 * respuesta/s
		 */
		ICombinatoricsVector<Item> initialVector = Factory.createVector(); // Crea el Vector Inicial (que luego sera combinado)

		for (Item i : casa.items)  // Agrega los Items que seran combinados al Vector 
		{
			initialVector.addValue(i);
		}
		  //Crea las combinaciones, verifica si pueden entrar en la mochila, y si es asi las va agregando a CombinacionesDeItems que es una Lista de Listas de Item
		
		
		for (int index = 1; index < casa.items.size(); index++)
		{ 
			Generator<Item> gen = Factory.createSimpleCombinationGenerator(initialVector, index);
			for (ICombinatoricsVector <Item> combination : gen)
			{
				if (fitsInBag((ArrayList<Item>)combination.getVector(), mochila))
				{ //Verifica que puedan entrar en la mochila antes de agregarlas a la combinacion
						CombinacionesDeItems.add((ArrayList<Item>) combination.getVector());
				}
			}
		}	
		//Va buscando el mayor valor entre todas las combinaciones. Tiene en cuenta si dos combinaciones comparten tener mayor valor
		
		for(ArrayList<Item> comb : CombinacionesDeItems)
		{
			float maxParcial = 0;
			float valTemp = 0;
			valTemp = getValueOfCombination(comb);
			if(valTemp == maxParcial) //Si son iguales es otra posible respuesta, lo agrega a las respuestas
			{
				CombinacionesRespuesta.add( comb);
			} 
			else if (valTemp > maxParcial)
			{
				CombinacionesRespuesta.clear();
				CombinacionesRespuesta.add( comb);
				maxParcial = valTemp;
			}			
		}	
		mochila.items = CombinacionesRespuesta.get(0); //SOLO se toma valida la primer respuesta. Se puede hacer uqe devuelva todas
		System.out.println("Con el EXAHUSTIVO se encontraron " + CombinacionesRespuesta.size() + " respuesta(s).");	
	}
	
	private boolean fitsInBag (ArrayList<Item> combinacion, Bag mochila)
	{
		float volume = 0;
		for (Item i : combinacion)
		{
			volume = volume + i.getVolume();
		}
		if(volume<= mochila.getOriginalVolume())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private float getValueOfCombination (ArrayList<Item> combinacion)
	{
		float value =0;
		
		for (Item i : combinacion)
		{
			value = value + i.getValue();
		}		
		return value;
	}
}

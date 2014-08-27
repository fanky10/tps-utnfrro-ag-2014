package com.utn.ag.viajante.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.commons.lang3.StringUtils;

import com.utn.ag.viajante.model.Constants;

public class App {
	
	

	public static void main(String[] args) throws IOException {
		
		contarProvinciasYGenerarTablas ();
	
		
		
		
		
	}
	
	
	
	
	
	public static void contarProvinciasYGenerarTablas (){
		
		Constants.CANTIDAD_PROVINCIAS= Constants.distanciasTexto.split("-").length;
		Constants.distanciasTabla = new int[Constants.CANTIDAD_PROVINCIAS][Constants.CANTIDAD_PROVINCIAS];

		String[] lineas = Constants.distanciasTexto.split("-");
		String[] tempDistancias = new String[Constants.CANTIDAD_PROVINCIAS]; 
		

		
		for (int i = 0; i<Constants.CANTIDAD_PROVINCIAS ; i++){
			tempDistancias = lineas[i].split("x");
			for (int j = 0; i< Constants.CANTIDAD_PROVINCIAS ; j++){
				
				Constants.distanciasTabla[i][j]= Integer.valueOf(tempDistancias[j]);
			
			}
			
			
			
		}
		
		
		
		System.out.println("Se cargaron " + Constants.CANTIDAD_PROVINCIAS + " provincias y " + (1 + StringUtils.countMatches(Constants.nombresProvincias, "," )) + " nombres de provincias.");
		
	
		
		
	}
	
	

	

	
	

}

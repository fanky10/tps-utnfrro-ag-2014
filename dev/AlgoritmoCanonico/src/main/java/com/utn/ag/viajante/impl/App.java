package com.utn.ag.viajante.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import com.utn.ag.viajante.model.Constants;

public class App {
	
	

	public static void main(String[] args) throws IOException {
		contarProvinciasYGenerarTablas ();
		
		Heuristica heur = new Heuristica();
		heur.calcularRecorrido();
		
		
		
		
		
		
	}
	
	public static void contarProvinciasYGenerarTablas (){
		Constants.CANTIDAD_PROVINCIAS = Constants.distanciasTabla[0].length;
		System.out.println("Se cargaron " + Constants.CANTIDAD_PROVINCIAS + " provincias y " + Constants.nombresProvincias.length + " nombres");
	}
	

	
	
	

}

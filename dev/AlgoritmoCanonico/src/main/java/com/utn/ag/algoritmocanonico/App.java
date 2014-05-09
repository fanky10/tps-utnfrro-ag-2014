package com.utn.ag.algoritmocanonico;

import com.utn.ag.algoritmocanonico.gui.PnlInformeChart;
import java.io.File;
import java.io.FileNotFoundException;

import com.utn.ag.algoritmocanonico.model.Cromosoma;
import com.utn.ag.algoritmocanonico.model.Poblacion;
import com.utn.ag.algoritmocanonico.service.AlgoritmoCanonico;
import com.utn.ag.algoritmocanonico.service.impl.AlgoritmoCanonicoImpl;
import com.utn.ag.algoritmocanonico.vo.InformeChart;

public class App {

	public static void main(String args[]) throws FileNotFoundException {
		PnlInformeChart.showApp();
	}

	public static InformeChart getInformeChart() {
		InformeChart informeVO = new InformeChart();
		Poblacion.showInformeHeaders();
		Poblacion p = generarPrimerPoblacion();
		p.processFitness();
		AlgoritmoCanonico a = new AlgoritmoCanonicoImpl();
		informeVO.add(p.getInformeVO());

		for (int i = 0; i < AppConstants.ITERACIONES - 1; i++) {
			p = a.nuevaPoblacion(p);
			informeVO.add(p.getInformeVO());
		}
		MockedLogger.debug("fin!");
		return informeVO;
	}

	private static Poblacion generarPrimerPoblacion() {
		Poblacion poblacion = new Poblacion();
		for (int i = 0; i < AppConstants.POBLACION; i++) {
			Cromosoma c = new Cromosoma();
			MockedLogger.debug(c.toString());
			poblacion.add(c);
		}
		return poblacion;
	}

	private static void cleanFile() {
		File f = new File(AppConstants.FILE_INFORME);
		f.delete();
	}
}

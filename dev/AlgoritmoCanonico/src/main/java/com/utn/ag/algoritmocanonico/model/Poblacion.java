package com.utn.ag.algoritmocanonico.model;

import java.util.ArrayList;

import com.utn.ag.algoritmocanonico.MockedLogger;
import com.utn.ag.algoritmocanonico.vo.InformeVO;

public class Poblacion extends ArrayList<Cromosoma> {

	public static Integer ID = 0;

	public Poblacion() {
		this(0);
	}
	
	public Poblacion(int size) {
		super(size);
		ID++;
		MockedLogger.debug("## Nueva Poblacion: " + Poblacion.ID);
	}

	public Poblacion(ArrayList<Cromosoma> hijos) {
		this();
		for (Cromosoma c : hijos) {
			this.add(c);
		}
	}

	private Double getSum() {
		Double sum = 0d;
		for (Cromosoma c : this) {
			sum += c.getFunctionValue();
		}
		return sum;
	}

	private Double getMax() {
		Double max = 0d;
		for (Cromosoma c : this) {
			if (max < c.getFunctionValue()) {
				max = c.getFunctionValue();
			}
		}
		return max;
	}
	
	private Double getMin() {
		Double min = 0d;
		for (Cromosoma c : this) {
			if (min == 0 || min > c.getFunctionValue()) {
				min = c.getFunctionValue();
			}
		}
		return min;
	}

	public void showInformeData() {
		Double prom = getSum() / this.size();
		Double max = getMax();
		Double min = getMin();

		int i = 1;
		for (Cromosoma c : this) {
			MockedLogger.debug(i + ": " + c.toString() + " - fit: "
					+ c.getFitness());
			i++;
		}
		// informe! max, min, prom
		MockedLogger.informe(max+","+min+","+prom);

	}
        
        public InformeVO getInformeVO(){
            Double prom = getSum() / this.size();
            Double max = getMax();
            Double min = getMin();

            int i = 1;
            for (Cromosoma c : this) {
                    MockedLogger.debug(i + ": " + c.toString() + " - fit: "
                                    + c.getFitness());
                    i++;
            }
            return new InformeVO(min, max, prom);
        }
	
	public static void showInformeHeaders(){
		// informe! max, min, prom
		MockedLogger.informe("max,min,prom");
	}

	public void processFitness() {
		for (Cromosoma c : this) {
			c.setFitness(c.getFunctionValue() / this.getSum());
		}
	}

}

package com.utn.ag.algoritmocanonico.model;

import java.util.ArrayList;

import com.utn.ag.algoritmocanonico.MockedLogger;
import com.utn.ag.algoritmocanonico.vo.InformeVO;

public class Poblacion extends ArrayList<Cromosoma> {

	public Poblacion() {
		this(0);
	}

	public Poblacion(int size) {
		super(size);
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

	public InformeVO getInformeVO() {
		Double sum = getSum();
		Double prom = sum / this.size();
		Double max = getMax();
		Double min = getMin();

		for (Cromosoma c : this) {
			MockedLogger
					.verbose(c.toString() + " - fitness: " + c.getFitness());
		}
		InformeVO informeVO = new InformeVO(min, max, prom);
		MockedLogger.verbose("Sum: " + sum);
		MockedLogger.verbose("Prom: " + prom);
		MockedLogger.verbose("Max: " + max);
		return informeVO;
	}

	public void processFitness() {
		for (Cromosoma c : this) {
			c.setFitness(c.getFunctionValue() / this.getSum());
		}
	}

}

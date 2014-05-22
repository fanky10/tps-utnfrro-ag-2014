/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.algoritmocanonico.vo;

import com.utn.ag.algoritmocanonico.model.Cromosoma;

/**
 *
 * @author fanky10
 */
public class InformeVO {
    private Cromosoma min;
    private Cromosoma max;
    private Double prom;

    public InformeVO(Cromosoma min, Cromosoma max, Double prom) {
        this.min = min;
        this.max = max;
        this.prom = prom;
    }

    public Cromosoma getMin() {
		return min;
	}

	public void setMin(Cromosoma min) {
		this.min = min;
	}

	public Cromosoma getMax() {
		return max;
	}

	public void setMax(Cromosoma max) {
		this.max = max;
	}

	public Double getProm() {
        return prom;
    }

    public void setProm(Double prom) {
        this.prom = prom;
    }
    
}

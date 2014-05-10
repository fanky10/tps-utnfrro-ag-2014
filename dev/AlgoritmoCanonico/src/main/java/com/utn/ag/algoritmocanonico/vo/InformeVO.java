/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.algoritmocanonico.vo;

/**
 *
 * @author fanky10
 */
public class InformeVO {
    private Double min;
    private Double max;
    private Double prom;

    public InformeVO(Double min, Double max, Double prom) {
        this.min = min;
        this.max = max;
        this.prom = prom;
    }
    
    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getProm() {
        return prom;
    }

    public void setProm(Double prom) {
        this.prom = prom;
    }
    
}

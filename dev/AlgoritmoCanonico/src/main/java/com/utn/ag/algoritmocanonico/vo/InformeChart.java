/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.algoritmocanonico.vo;

import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

/**
 *
 * @author fanky10
 */
public class InformeChart extends ArrayList<InformeVO>{
    protected XYSeriesCollection data;
    protected JFreeChart chart;
    
    public JFreeChart getChart(){
        createDataset();
        createChart();
        return chart;
    }
    
    protected void createDataset() {
        data = new XYSeriesCollection();
        XYSeries minSeries = new XYSeries("min");
        XYSeries maxSeries = new XYSeries("max");
        XYSeries promSeries = new XYSeries("prom");
        int i = 0;
        for(InformeVO informe: this){
            minSeries.add(i,informe.getMin());
            maxSeries.add(i,informe.getMax());
            promSeries.add(i,informe.getProm());
            i++;
        }
        data.addSeries(minSeries);
        data.addSeries(maxSeries);
        data.addSeries(promSeries);
    }

    protected void createChart() {
        chart = ChartFactory.createXYLineChart(
                "Grafico", // chart title
                "x", // domain axis label
                "Y(x)", // range axis label
                data, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
                );
        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);
        XYPlot plot = chart.getXYPlot();
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        plot.setRenderer(renderer);


    }

    
}

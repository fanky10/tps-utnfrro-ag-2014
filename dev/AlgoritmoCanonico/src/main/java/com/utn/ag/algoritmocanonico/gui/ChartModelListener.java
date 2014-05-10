/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.algoritmocanonico.gui;

import java.util.EventListener;

/**
 *
 * @author facundo
 */
public interface ChartModelListener extends EventListener{
    
    public void panelChanged(ChartModelEvent evt);
}

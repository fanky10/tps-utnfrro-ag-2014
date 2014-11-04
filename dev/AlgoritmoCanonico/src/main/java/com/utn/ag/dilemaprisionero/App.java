/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.dilemaprisionero;

import com.utn.ag.algoritmocanonico.MockedLogger;
import java.util.List;

/**
 *
 * @author fanky
 */
public class App {
    public static void main(String args[]){
        MockedLogger.DEBUG = true;
        PermutationsWithRepetition gen = new PermutationsWithRepetition(new String[]{"A","N"});
        List<String> v = gen.getVariations();
        for(String s: v){
            MockedLogger.debug("generated: "+s);
        }
    }
}

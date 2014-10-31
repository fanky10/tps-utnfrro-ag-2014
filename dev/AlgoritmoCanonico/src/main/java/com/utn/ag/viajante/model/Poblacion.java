package com.utn.ag.viajante.model;

import com.utn.ag.algoritmocanonico.MockedLogger;
import java.util.ArrayList;
import java.util.Random;

public class Poblacion extends ArrayList<Cromosoma> {

    private static Random m_rand = new Random();

    public Poblacion(int size) {
        super(size);
    }

    public Poblacion(ArrayList<Cromosoma> hijos) {

        for (Cromosoma c : hijos) {
            this.add(c);
        }
    }

    public Poblacion() {
    }

    public void processFitness() {
        //Resetear Fitness a 0;
        for (Cromosoma c : this) {
            c.setFitness(0);
        }

        double factor = 1;

        double distTotal = 0;
        double sumaTotal = 0;
        double controlFit = 0;

        for (Cromosoma c : this) {
            if (c.getDistanciaRecorrido() + factor > distTotal) {
                distTotal = c.getDistanciaRecorrido() + factor;
            }
        }

        for (Cromosoma c : this) {

            c.setFitness(distTotal - c.getDistanciaRecorrido());
            sumaTotal = sumaTotal + (distTotal - c.getDistanciaRecorrido());

        }

        for (Cromosoma c : this) {

            double fitness = c.getFitness() / sumaTotal;

            c.setFitness(fitness);

            controlFit = controlFit + c.getFitness();

        }

    }

    public void printPoblacion() {
        MockedLogger.debug("Recorridos Finales");
        for (Cromosoma c : this) {
            c.printRecorrido();
            MockedLogger.debug("dist: " + c.getDistanciaRecorrido());
            MockedLogger.debug("fit: " + c.getFitness());

        }

    }

}

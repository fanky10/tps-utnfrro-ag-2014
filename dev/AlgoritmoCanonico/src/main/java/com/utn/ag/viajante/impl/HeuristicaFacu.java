package com.utn.ag.viajante.impl;

import java.util.ArrayList;
import java.util.List;

import com.utn.ag.viajante.model.Constants;

public class HeuristicaFacu implements Heuristica {

    private class Movimiento {

        private int indiceCiudad;
        private int distanciaCiudadAnterior;

        public Movimiento(int indiceCiudad, int distanciaCiudadAnterior) {
            this.indiceCiudad = indiceCiudad;
            this.distanciaCiudadAnterior = distanciaCiudadAnterior;
        }

        public int getIndiceCiudad() {
            return indiceCiudad;
        }

        public int getDistanciaCiudadAnterior() {
            return distanciaCiudadAnterior;
        }

        public String toString() {
            return String.format("Ciudad idx: %s nombre: %s dist: %s", indiceCiudad, Constants.NOMBRES_PROVINCIAS[indiceCiudad], distanciaCiudadAnterior);
        }

    }

    public HeuristicaFacu() {
        init();
    }

    private void init() {
        // inicializa los indices de posibles ciudades
        for (int i = 0; i < Constants.CANTIDAD_PROVINCIAS; i++) {
            ciudadesList.add(i);
        }
    }

    private List<Integer> recorridoList = new ArrayList<Integer>();
    private List<Movimiento> movementList = new ArrayList<Movimiento>();
    private List<Integer> ciudadesList = new ArrayList<Integer>();
    private int kmRecorridos = 0;

    public void recorreCiudades(int indiceCiudad) {
        movementList.add(new Movimiento(indiceCiudad, 0));
        recorridoList.add(indiceCiudad);
        ciudadesList.remove(Integer.valueOf(indiceCiudad));
        recorreCiudades(indiceCiudad, 100);
        int ultimaCiudad = movementList.get(movementList.size() - 1).getIndiceCiudad();
        int[] ciudadesDestinoUltimaCiudad = Constants.TABLA_DISTANCIAS[ultimaCiudad];
        int distanciaUltimaCiudadDestino = ciudadesDestinoUltimaCiudad[indiceCiudad];
        movementList.add(new Movimiento(indiceCiudad, distanciaUltimaCiudadDestino));
        kmRecorridos += distanciaUltimaCiudadDestino;

    }

    /**
     *
     * @param indiceCiudad
     * @param maxCities
     * @return citiesLeft
     */
    private void recorreCiudades(int indiceCiudad, int maxCities) {
        Movimiento nextMove = getClosestMovimiento(indiceCiudad);
        if (nextMove.getIndiceCiudad() < 0) {
            return;
        }
        recorridoList.add(nextMove.getIndiceCiudad());
        movementList.add(nextMove);
        kmRecorridos += nextMove.getDistanciaCiudadAnterior();
        if (maxCities > 0) {
            // decrement
            recorreCiudades(nextMove.getIndiceCiudad(), maxCities - 1);
        } else if (maxCities < 0) {
            recorreCiudades(nextMove.getIndiceCiudad(), maxCities);
        }
        // count = 0;
        // termina cuando ya el proximo movimiento retorna negativo

    }

    private Movimiento getClosestMovimiento(int ciudadOrigen) {
        int[] ciudadesDestino = Constants.TABLA_DISTANCIAS[ciudadOrigen];
        int bestRecorrido = -1;
        int indiceDestino = -1;
        if (!ciudadesList.isEmpty()) {

            for (int i = 0; i < ciudadesDestino.length; i++) {
                int distancia = ciudadesDestino[i];
                if (distancia > 0 && ciudadesList.contains(i) && (distancia < bestRecorrido || bestRecorrido < 0)) {
                    // encontre el mejor!
                    // actualizo indice y el best
                    bestRecorrido = distancia;
                    indiceDestino = i;
                }
            }

            ciudadesList.remove(Integer.valueOf(indiceDestino));

        }
        return new Movimiento(indiceDestino, bestRecorrido);
    }
    
    public int getKmRecorridos(){
        return kmRecorridos;
    }
    
    public void printRecorrido() {
        debug("Inicio recorrido");
        int totalKm = 0;
        int totalCiudades = 0;
        for (Movimiento m : movementList) {
            debug("Ciudad movimiento: " + m);
            totalKm += m.getDistanciaCiudadAnterior();
            totalCiudades++;
        }
        debug("Fin recorrido");
        debug("Total ciudades: " + totalCiudades);
        debug("Total km: " + totalKm);
    }

    public static void main(String args[]) {
        int ciudadObjetivo = 7;
        int distObjetivo = 478;
        int ciudadOrigen = 0;
        HeuristicaFacu hf = new HeuristicaFacu();
        Movimiento nextMove = hf.getClosestMovimiento(ciudadOrigen);
        debug("next Move: " + nextMove);
        if (ciudadObjetivo == nextMove.getIndiceCiudad() && distObjetivo == nextMove.getDistanciaCiudadAnterior()) {
            debug("todo bien!!");
        }

        // nos movemos dos ciudades adelante:
        // la mejor de 0 -> 7 (dist: 478)
        // la mejor de 7 -> 10 (dist: 31)
        ciudadObjetivo = 10;
        distObjetivo = 31;
        nextMove = hf.getClosestMovimiento(nextMove.getIndiceCiudad());
        debug("next Move: " + nextMove);
        if (ciudadObjetivo == nextMove.getIndiceCiudad() && distObjetivo == nextMove.getDistanciaCiudadAnterior()) {
            debug("todo bien next!!");
        }

        // ahora lo hace automatico
        // hasta la 3er ciudad
        hf = new HeuristicaFacu();
        debug("ciudad origen: " + Constants.NOMBRES_PROVINCIAS[0]);
        hf.recorreCiudades(0);
        hf.printRecorrido();

        // ahora lo hace automatico
        // hasta que no encuentra mas ciudades que recorrer
        debug("ciudad origen: " + Constants.NOMBRES_PROVINCIAS[18]);
        hf = new HeuristicaFacu();
        hf.recorreCiudades(18);
        hf.printRecorrido();
        
        int mejorCiudad = -1;
        int mejorRecorrido = -1;
        for(int i=0;i<Constants.CANTIDAD_PROVINCIAS;i++){
            hf = new HeuristicaFacu();
            hf.recorreCiudades(i);
            hf.printRecorrido();
            if(mejorRecorrido<0 || mejorRecorrido>hf.kmRecorridos){
                mejorRecorrido = hf.kmRecorridos;
                mejorCiudad = i;
            }
        }
        info("mejor ciudad: "+mejorCiudad+" - "+Constants.NOMBRES_PROVINCIAS[mejorCiudad]+" km recorridos: "+mejorRecorrido);
    }
    
    public static Boolean DEBUG = Boolean.TRUE;
    private static void debug(String text){
        if(DEBUG){
            System.out.println("DEBUG: "+text);
        }
    }
    
    private static void info(String text){
        System.out.println("INFO: "+text);
    }

}

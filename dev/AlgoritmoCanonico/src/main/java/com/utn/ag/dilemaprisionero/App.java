/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.dilemaprisionero;

import com.utn.ag.algoritmocanonico.MockedLogger;
import static com.utn.ag.algoritmocanonico.MockedLogger.debug;
import java.util.List;
import java.util.Random;

/**
 *
 * @author fanky
 */
public class App {

    public static void main(String args[]) {
        MockedLogger.DEBUG = true;
        String parJugadorX = getPar();
        String parJugadorY = getPar();
        debug("Comienza el juego con pares:[x,y] [" + parJugadorX + " , " + parJugadorY + "]");
        Juego j = new Juego(parJugadorX, parJugadorY);
        j.jugar();
        j.getMejorJugadaX();
        j.getMejorJugadaY();

    }
    private static final Random random = new Random();

    private static String getPar() {
        List<String> posiblesPares = Juego.PARES_POSIBLES;
        int idx = random.nextInt(posiblesPares.size());
        return posiblesPares.get(idx);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.ag.dilemaprisionero;

import com.utn.ag.algoritmocanonico.MockedLogger;
import static com.utn.ag.algoritmocanonico.MockedLogger.debug;
import static com.utn.ag.algoritmocanonico.MockedLogger.info;
import java.util.List;
import java.util.Random;

/**
 *
 * @author fanky
 */
public class App {

    private static final int CANTIDAD_JUGADAS = 10;

    public static void main(String args[]) {
        MockedLogger.DEBUG = true;
        int mejorPuntaje = -1;
        String mejorJugadaX = "", mejorJugadaY = "";
        for (int i = 0; i < CANTIDAD_JUGADAS; i++) {
            String parJugadorX = getPar();
            String parJugadorY = getPar();
            info("Comienza el juego con pares:[x,y] [" + parJugadorX + " , " + parJugadorY + "]");
            Juego j = new Juego(parJugadorX, parJugadorY);
            j.jugar();

            if (mejorPuntaje < 0 || mejorPuntaje < j.getMejorPuntaje()) {
                mejorPuntaje = j.getMejorPuntaje();
                mejorJugadaX = j.getMejorJugadaX();
                mejorJugadaY = j.getMejorJugadaY();
            }
        }
        info("mejor jugada [x,y]: [" + mejorJugadaX + " , " + mejorJugadaY + "]");
        info("mejor puntaje: " + mejorPuntaje);

    }
    private static final Random random = new Random();

    private static String getPar() {
        List<String> posiblesPares = Juego.PARES_POSIBLES;
        int idx = random.nextInt(posiblesPares.size());
        return posiblesPares.get(idx);
    }
}

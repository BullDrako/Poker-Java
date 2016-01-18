/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.*;

/**
 *
 * @author edgar
 */
public class Poker {

    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private Baraja baraja = new Baraja();

    
    public Poker() {
        // crear jugadores
        this.jugadores();

        System.out.println(" ");
        System.out.println("---PARTICIPANTES---");
        this.imprimirJugadores();

        System.out.println("\n ---COMENZAR JUEGO POKER ---");
        System.out.println(" ");
        // CREAR BARAJA ORDENADA        
        this.baraja.BarajaPoker();

        // IMPRIMIR BARAJA ORDENADA   
        System.out.println("Baraja ORDENADA");
        this.imprimirBaraja();

        // MEZCLAR BARAJA
        this.baraja.barajar();

        System.out.println(" ");

        // IMPRIMIR BARAJA MEZCLADA   
        System.out.println("Baraja DESORDENADA");
        this.imprimirBaraja();

        System.out.println(" ");

        //REPARTIR
        this.repartir();
        System.out.println(" ");
        System.out.println("---CARTAS REPARTIDAS---");
        this.imprimirJugadores();

        System.out.println("\n ---DESCARTES---");
        this.descartar();

        // GANADOR
        this.winner();
    }

    // Metodos 
    // Crear jugador
    private void jugadores() {
        for (int i = 1; i <= 4; i++) {
            // añadir jugador
            this.jugadores.add(new Jugador("Jugador " + i));
        }
    }

    private void repartir() {
        // recorrer jugadores y cartas
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                //Coger 1 carta de la baraja
                // un jugador guarda la carta en su mano.
                this.jugadores.get(i).añadirCarta(this.baraja.cogerCarta());
            }
        }
    }

    private void descartar() {
        for (int i = 0; i < this.jugadores.size(); i++) {
  
            this.descartesDelJugador(i);

            System.out.println("\nCartas DESPUÉS de descartar (" + this.jugadores.get(i).verNombre() + ")");
            this.imprimirJugador(i);

        }
    }

    private void descartesDelJugador(int numJugador) {
        // cartas que va a descartar jugador
        ArrayList<String> descartar = this.jugadores.get(numJugador).descartar();

        for (int i = 0; i < this.jugadores.get(numJugador).verMano(); i++) {
            for (int j = 0; j < descartar.size(); j++) {
                // Si la carta i es igual a la j se descarta
                if (this.jugadores.get(numJugador).verCarta(i).verValor() == Integer.parseInt(descartar.get(j))) {
                    
                    // coger la carta i y la meterla en la baraja

                    this.baraja.añadirCarta(this.jugadores.get(numJugador).cogerCarta(i));

                    // Al guardar la carta el tamaño de la mano disminuye
                    i--;

                    j = descartar.size();
                }
            }
        }
        // Dar cartas nuevas al jugador
        for (int i = 0; i < descartar.size(); i++) {
            this.jugadores.get(numJugador).añadirCarta(this.baraja.cogerCarta());
        }
    }

    //Ganador
    private void winner() {
        // almacenar la info del primer jugador
        String ganador = this.jugadores.get(0).verNombre();
        int puntos = this.jugadores.get(0).verPuntos();

        //recorrer jugadores
        for (int i = 1; i < this.jugadores.size(); i++) {
            // Comprobar si el jugador actual tiene más puntos que el anterior
            if (this.jugadores.get(i).verPuntos() > puntos) {
                ganador = this.jugadores.get(i).verNombre();
                puntos = this.jugadores.get(i).verPuntos();
            }
        }

        System.out.println(" ");

        System.out.println("¡¡¡¡¡¡GANADOR!!!!!!");

        //si hay empate
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (this.jugadores.get(i).verPuntos() == puntos) {
                this.imprimirJugador(i);
            }
        }
    }

    private void imprimirJugadores() {
        System.out.println("---Jugadores---");
        for (int i = 0; i < this.jugadores.size(); i++) {
            System.out.print("\n");
            this.jugadores.get(i).imprimir();
        }
    }

    //Imprime solo 1 jugador
    private void imprimirJugador(int id) {
        System.out.print("\n");
        this.jugadores.get(id).imprimir();
    }

    // imprimir baraja
    private void imprimirBaraja() {
        System.out.println("---Baraja---");
        this.baraja.imprimir();
    }
}

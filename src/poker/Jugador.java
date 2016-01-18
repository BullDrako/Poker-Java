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
public class Jugador {

    private String nombre;
    private ArrayList<Carta> mano = new ArrayList<Carta>();
    private int puntos;

    //Constructor
    public Jugador(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public int verMano() {
        return this.mano.size();
    }

    public Carta verCarta(int posicion) {
        return this.mano.get(posicion);
    }

    public Carta cogerCarta(int posicion) {
        // copia de la carta
        Carta cartaCopia = this.mano.get(posicion);

        // eliminar carta
        this.mano.remove(posicion);

        //devolver carta.
        return cartaCopia;
    }

    public String verNombre() {
        return this.nombre;
    }

    public int verPuntos() {
        this.puntuacion();
        return this.puntos;
    }

    //Metodos
    // Guarda la carta en la mano
    public void añadirCarta(Carta cartaRecibida) {
        this.mano.add(cartaRecibida);

        // puntuar cuando este completa la mano
        if (this.mano.size() > 4) {
            this.puntuacion();
        }
    }

    public ArrayList<String> descartar() {

        String[] jugada = this.comprobarJugada();

        ArrayList<String> descartarCartas = new ArrayList<String>();

        // descartar cartas no repetidas
        if (jugada[0] == "trio" || jugada[0] == "pareja") {
            // recorrer la mano
            for (int i = 0; i < this.mano.size(); i++) {

                //Si el numero de la carta es diferente al numero de la jugada
                //no trío
                if (this.mano.get(i).verNumero() != jugada[1]) {
                    // Como no interesa la carta, añadir a descartes
                    descartarCartas.add(Integer.toString(this.mano.get(i).verValor()));
                }
            }
        } else if (jugada[0] == "doble pareja") {
            for (int i = 0; i < this.mano.size(); i++) {

                // comparar 2 cartas
                if (this.mano.get(i).verNumero() != jugada[1]
                        && this.mano.get(i).verNumero() != jugada[2]) {
                    
                    descartarCartas.add(Integer.toString(this.mano.get(i).verValor()));
                }
            }
        } else {
            // Como no hay ni trio, ni pareja ni doble --> descartar todas
            for (int i = 0; i < this.mano.size(); i++) {
                descartarCartas.add(Integer.toString(this.mano.get(i).verValor()));
            }
        }

        // devolver cartas que descartar.
        return descartarCartas;
    }

    private String[] comprobarJugada() {
        String almacenarJugada[] = new String[4];

        String comprobarJugada[] = this.hayTrio();

        if (comprobarJugada[0] == "trio") {

            // si la jugada es trio guardrs la info
            almacenarJugada[0] = comprobarJugada[0];	// Trio  almacenar jugada
            almacenarJugada[1] = comprobarJugada[1];	// Numero almacenar carta
            almacenarJugada[2] = "nada";                // almacenar carta
            almacenarJugada[3] = "300";		// Puntos almacenar puntos

        } else {
            // si la jugada es doble pareja guardar la info
            comprobarJugada = this.hayParejas();

            if (comprobarJugada[0] == "doble pareja") {
                
                almacenarJugada[0] = comprobarJugada[0];	//Doble pareja
                almacenarJugada[1] = comprobarJugada[1];	//Numero pareja 1
                almacenarJugada[2] = comprobarJugada[2];	//Numero pareja 2
                almacenarJugada[3] = "200";                 //Puntos 

            } else if (comprobarJugada[0] == "pareja") {

                almacenarJugada[0] = comprobarJugada[0];	//Pareja
                almacenarJugada[1] = comprobarJugada[1];	//Numero pareja
                almacenarJugada[2] = "nada";
                almacenarJugada[3] = "100";			//Puntos 

            } else {
                // No hay jugada

                almacenarJugada[0] = comprobarJugada[0];	// No hay jugada
                almacenarJugada[1] = comprobarJugada[1];	
                almacenarJugada[2] = "nada";
                almacenarJugada[3] = "0";			// No hay puntos
            }
        }
        return almacenarJugada;
    }

    private String[] hayTrio() {
        String trio[] = {"no hay jugada", "no hay jugada"};

        //comprobar 3 cartas ; 3 for.
        for (int i = 0; i < this.mano.size() - 2; i++) {
            for (int j = i + 1; j < this.mano.size() - 1; j++) {
                for (int k = j + 1; k < this.mano.size(); k++) {
                    // comparar 3 cartas por su valor

                    if (this.mano.get(i).verValor() == this.mano.get(j).verValor()
                            && this.mano.get(j).verValor() == this.mano.get(k).verValor()) {
                        // guardar jugada trio
                        trio[0] = "trio";
                        // guardar valor del trio
                        trio[1] = this.mano.get(i).verNumero();
                    }
                }
            }
        }
        // devolver el resultado
        return trio;
    }

    private String[] hayParejas() {
        String[] parejas = {"no hay jugada", "no hay jugada", "no hay jugada"};

        // 2 cartas; 2 for 
        for (int i = 0; i < this.mano.size() - 1; i++) {
            for (int j = i + 1; j < this.mano.size(); j++) {
                // Comprobar parejas
                if (this.mano.get(i).verValor() == this.mano.get(j).verValor()) {

                    // Primera pareja
                    if (parejas[0] == "no hay jugada") {
                        parejas[0] = "pareja";
                        parejas[1] = this.mano.get(i).verNumero();

                    } // Segunda pareja
                    else {

                        parejas[0] = "doble pareja";
                        parejas[2] = this.mano.get(i).verNumero();

                    }
                }
            }
        }

        return parejas;
    }

    public void imprimir() {
        System.out.println("Jugador: " + this.nombre + " ");

        String[] jugada = this.comprobarJugada();
        System.out.println("Jugada: " + jugada[0] + " (" + this.puntos + " PUNTOS)");

        // Cartas de la mano
        for (int i = 0; i < this.mano.size(); i++) {
            this.mano.get(i).imprimir();
        }
    }

    private void puntuacion() {
        this.puntos = 0;

        // recorrer cartas de la mano
        for (int i = 0; i < this.mano.size(); i++) {
            this.puntos = this.puntos + this.mano.get(i).verValor();
        }

        // añadir puntos de la jugada
        String[] jugada = this.comprobarJugada();

        this.puntos = this.puntos + Integer.parseInt(jugada[3]);

    }
}

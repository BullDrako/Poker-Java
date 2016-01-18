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
public class Baraja {

    private ArrayList<Carta> baraja = new ArrayList<Carta>();

    //Constructor 
    public void BarajaPoker() {

        this.baraja.clear();

        // palos y numeros
        String[] palos = {"Corazones", "Picas", "Diamantes", "Treboles"};
        String[] numeros = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // recorrer los numeros y palos	
        for (int i = 0; i < palos.length; i++) {
            for (int j = 0; j < numeros.length; j++) {
                // Añadir una nueva carta
                this.baraja.add(new Carta(numeros[j], palos[i]));
            }
        }
    }

    public Carta cogerCarta() {
        //copia de la carta
        Carta copiaCarta = this.baraja.get(0);

        // eliminar carta de la baraja
        this.baraja.remove(0);

        // devolver copia
        return copiaCarta;
    }

    //añadir carta
    public void añadirCarta(Carta nuevaCarta) {
        this.baraja.add(nuevaCarta);
    }

    //Metodos
    //Mezclar 
    public void barajar() {
        Collections.shuffle(this.baraja);
    }

    public void imprimir() {

        // imprimir cartas de la baraja
        for (int i = 0; i < this.baraja.size(); i++) {
            this.baraja.get(i).imprimir();
        }
    }

}

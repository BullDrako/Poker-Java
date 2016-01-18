/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author edgar
 */
public class Carta {

    private String numero;
    private String palo;
    private int valor;

    //Constructor
    public Carta(String nNumero, String nPalo) {
        this.numero = nNumero;
        this.palo = nPalo;

        this.valor = darValor(nNumero);
    }

    public String verNumero() {
        return this.numero;
    }

    public int verValor() {
        return this.valor;
    }

    //Metodos
    private int darValor(String numCarta) {
        // valor predeterminado de la carta es 0
        int valorCarta = 0;

        // cada carta tiene unos puntos
        String[][] arrayValores = {{"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"},
        {"14", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"}};

        for (int i = 0; i < 13; i++) {
            if (numCarta == arrayValores[0][i]) {
                valorCarta = Integer.parseInt(arrayValores[1][i]);
            }
        }
        return valorCarta;
    }

    public void imprimir() {
        System.out.println("Carta[ " + this.numero + "	de " + this.palo + ",	" + this.valor + "	]");
    }

}

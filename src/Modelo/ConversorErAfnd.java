/*
 * Parser de ER a Automata Finito No Determinista
 */
package Modelo;

import dk.brics.automaton.RegExp;
import dk.brics.automaton.Automaton;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ConversorErAfnd {

    private String estructura;

    public Automaton convertir(String text) {
        RegExp r = new RegExp(text.replace(".", ""));
        Automaton a = r.toAutomaton();
        return a;
    }

    public DefaultTableModel generarModelo(Object[] alfabetoLista) {
        DefaultTableModel modelo = null;
        String[] alfabeto = null; //Alfabeto - Nombre columnas
        alfabeto[0] = "E/A";
        Character[] estados = null; //Estados - Datos
        
        
        //Obtener un arreglo sin los espacios
        ArrayList<Character> listaEstruct = new ArrayList<Character>();
        for (int i = 0; i < estructura.length(); i++) {
            char currentCharacter = estructura.charAt(i);
            if (currentCharacter != ' ') {
                listaEstruct.add(currentCharacter);
            }
        }
        //ObtenerEstados
        
        for (int i = 0; i <= listaEstruct.size(); i++) {
            if (listaEstruct.get(i) == '(') {
                estados[i] = listaEstruct.get(i - 1);
            }
        }
        
        //prueba
        for (Character string : estados) {
            System.out.print("[" + string + "] ");
        }
        //Ejemplo de modelo
        /*Object[][] data = {
                {"Mary", "Campione", "Esquiar", new Integer(5), new Boolean(false)},
                {"Lhucas", "Huml", "Patinar", new Integer(3), new Boolean(true)},
                {"Kathya", "Walrath", "Escalar", new Integer(2), new Boolean(false)},
                {"Marcus", "Andrews", "Correr", new Integer(7), new Boolean(true)},
                {"Angela", "Lalth", "Nadar", new Integer(4), new Boolean(false)}
            };*/
        return modelo;
    }

    public String generarAutomata(String estructura) {
        
        estructura = estructura.replace("initial state", "estado inicial");
        estructura = estructura.replace("state", "estado");
        estructura = estructura.replace("reject", "rechazado");
        estructura = estructura.replace("accept", "aceptado");
        this.estructura = estructura;
        return estructura;
    }
}

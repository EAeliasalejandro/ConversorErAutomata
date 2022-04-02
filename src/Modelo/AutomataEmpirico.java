/*
 * Parser de ER a Automata Finito No Determinista
 */
package Modelo;

import dk.brics.automaton.RegExp;
import dk.brics.automaton.Automaton;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class AutomataEmpirico {

    private String estructura;

    public Automaton convertir(String text) {
        RegExp r = new RegExp(text.replace(".", ""));
        Automaton a = r.toAutomaton();
        return a;
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

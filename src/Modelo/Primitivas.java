/* 
 * modelos de las primitivas
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author EAeli
 */
public class Primitivas {

    public void or(ArrayList<String> transicion) {
        Estado q0 = new Estado();
        Estado q1 = new Estado();
        Estado q2 = new Estado();
        Estado q3 = new Estado();
        Estado q4 = new Estado();
        Estado q5 = new Estado();

        ArrayList<Estado> liga = new ArrayList();
        ArrayList<String> transiciones = new ArrayList();
        liga.add(q1);
        liga.add(q2);
        q0.setLiga(liga);
        transiciones.add("e");
        transiciones.add("e");
        q0.setTransisiones(transiciones);
        q0.setNumEstado("q0");
        liga.clear();
        transiciones.clear();

        liga.add(q3);
        q1.setLiga(liga);
        transiciones.add(transicion.get(0));
        q1.setTransisiones(transiciones);
        q1.setNumEstado("q1");
        liga.clear();
        transiciones.clear();

        liga.add(q4);
        q2.setLiga(liga);
        transiciones.add(transicion.get(0));
        q2.setTransisiones(transiciones);
        q2.setNumEstado("q2");
        liga.clear();
        transiciones.clear();

        liga.add(q5);
        q3.setLiga(liga);
        transiciones.add("e");
        q3.setTransisiones(transiciones);
        q3.setNumEstado("q3");
        liga.clear();
        transiciones.clear();

        liga.add(q5);
        q4.setLiga(liga);
        transiciones.add("e");
        q4.setTransisiones(transiciones);
        q4.setNumEstado("q4");
        liga.clear();
        transiciones.clear();
    }

    public void estrella(ArrayList<String> transicion) {
        Estado q0 = new Estado();
        Estado q1 = new Estado();
        Estado q2 = new Estado();
        Estado q3 = new Estado();

        ArrayList<Estado> liga = new ArrayList();
        ArrayList<String> transiciones = new ArrayList();
        liga.add(q1);
        liga.add(q3);
        q0.setLiga(liga);
        transiciones.add("e");
        transiciones.add("e");
        q0.setTransisiones(transiciones);
        q0.setNumEstado("q0");
        liga.clear();
        transiciones.clear();

        liga.add(q2);
        q1.setLiga(liga);
        transiciones.add(transicion.get(0));
        q1.setTransisiones(transiciones);
        q1.setNumEstado("q1");
        liga.clear();
        transiciones.clear();

        liga.add(q1);
        liga.add(q3);
        q2.setLiga(liga);
        transiciones.add("e");
        transiciones.add("e");
        q2.setTransisiones(transiciones);
        q2.setNumEstado("q2");
        liga.clear();
        transiciones.clear();
    }

    public void union(ArrayList<String> transicion) {
        Estado q0 = new Estado();
        Estado q1 = new Estado();
        Estado q2 = new Estado();

        ArrayList<Estado> liga = new ArrayList();
        ArrayList<String> transiciones = new ArrayList();
        liga.add(q1);
        q0.setLiga(liga);
        transiciones.add(transicion.get(0));
        q1.setTransisiones(transiciones);
        q0.setNumEstado("q0");
        liga.clear();
        transiciones.clear();

        liga.add(q2);
        q1.setLiga(liga);
        transiciones.add(transicion.get(0));
        q1.setTransisiones(transiciones);
        q1.setNumEstado("q1");
        liga.clear();
        transiciones.clear();
    }

    public ArrayList<Primitivas> generarPrimitivas(String resultado, ArrayList<String> transicion) {
        ArrayList<Primitivas> arregloUnido = new ArrayList();
        char[] chars = resultado.toCharArray();
        for (char ch : chars) {
            switch (ch) {
                case '|':
                    or(transicion);
                    arregloUnido.add(this);
                    break;
                case '*':
                    estrella(transicion);
                    arregloUnido.add(this);
                    break;
                case '.':
                    union(transicion);
                    arregloUnido.add(this);
                    break;
            }
        }
        return arregloUnido;
    }
}

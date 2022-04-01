/*
 * Generador del Analizador Lexico
 */
package Modelo;

import java.io.File;

public class AnalizadorLexico {

    public static void main(String[] args) {
        String ruta = "C:/Users/EAeli/Documents/NetBeansProjects/ConversoER-AFD-AFND/src/Modelo/Lexer.flex";
        generarLexer(ruta);
    }

    public static void generarLexer(String ruta) {
        File archivo = new File(ruta);
        JFlex.Main.generate(archivo);
    }
}

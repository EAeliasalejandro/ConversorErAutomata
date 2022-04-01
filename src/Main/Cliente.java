/*
 * Conversor de Expresión Regular - AFND - AFD
 */
package Main;

import Vista.VentanaPrincipal;

/**
 *
 * @author EAeli
 */
public class Cliente {

    public static void main(String[] args) {
        VentanaPrincipal menu = new VentanaPrincipal();
        // Para probar: 
        //a+.b.c.d.f*
        //0.(1|0).*0
        //a+.(c|v).a
        //(a)|((b)*.(c)).=.a|b*.c
        //(a|b)*
        
        menu.setVisible(true);
    }
    
}

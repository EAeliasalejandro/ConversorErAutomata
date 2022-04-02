/*
 * Interfaz Grafica de Usuario
 */
package Vista;

import Modelo.AutomataEmpirico;
import Modelo.Lexer;
import Modelo.Primitivas;
import Modelo.Tokens;
import Modelo.afd.AFD;
import Modelo.afnd.AFND;
import dk.brics.automaton.Automaton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EAeli
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private ArrayList<String> transicion;
    static ArrayList<Primitivas> arq;
    private ArrayList<String> alfabetoLista;
    private AFND afnd;
    Boolean Analizado = false;

    //private ArrayList ;
    //private ArrayList<> aFNDLista = new ArrayList<>;
    public VentanaPrincipal() {
        initComponents();
        lblImagen.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtEntrada = new javax.swing.JTextField();
        btnAnalizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaAFND = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnAFND = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaResultadoLexico = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAFD = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtaAFD = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Decker", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Conversor ER - AFD - AFND");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 1640, -1));

        txtEntrada.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        txtEntrada.setText("Escribe la expresión regular");
        txtEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEntradaMouseClicked(evt);
            }
        });
        getContentPane().add(txtEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 83, 385, 47));

        btnAnalizar.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 142, -1, 37));

        txtaAFND.setColumns(20);
        txtaAFND.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        txtaAFND.setRows(5);
        jScrollPane1.setViewportView(txtaAFND);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 454, 470, 390));

        jLabel2.setFont(new java.awt.Font("Decker", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Analizador Léxico");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 48, 435, -1));

        btnAFND.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        btnAFND.setText("Convertir a AFND");
        btnAFND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAFNDActionPerformed(evt);
            }
        });
        getContentPane().add(btnAFND, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, 37));

        txtaResultadoLexico.setColumns(20);
        txtaResultadoLexico.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        txtaResultadoLexico.setRows(5);
        jScrollPane2.setViewportView(txtaResultadoLexico);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 404, 310));

        jLabel3.setFont(new java.awt.Font("Decker", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Autómata Finito No Determinista con e");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 413, 470, 40));

        lblImagen.setText("imagenNoCargada");
        lblImagen.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 690, 310));

        jLabel5.setFont(new java.awt.Font("Decker", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Autómata Finito Determinista");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 423, 430, 30));

        jLabel6.setFont(new java.awt.Font("Decker", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Diagrama AFND empirico");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 660, -1));

        btnAFD.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        btnAFD.setText("Convertir a AFD");
        btnAFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAFDActionPerformed(evt);
            }
        });
        getContentPane().add(btnAFD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 360, -1, 37));

        txtaAFD.setColumns(20);
        txtaAFD.setFont(new java.awt.Font("Decker", 0, 14)); // NOI18N
        txtaAFD.setRows(5);
        jScrollPane5.setViewportView(txtaAFD);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 450, 430, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEntradaMouseClicked
        txtEntrada.setText("");
    }//GEN-LAST:event_txtEntradaMouseClicked

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        alfabetoLista = new ArrayList<String>();
        File archivo = new File("archivo.txt");
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(archivo);
            escribir.print(txtEntrada.getText());
            escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Reader lector = new BufferedReader(new FileReader("archivo.txt"));
            Lexer lexer = new Lexer(lector);
            String resultado = "";
            while (true) {
                Tokens tokens = lexer.yylex();
                if (tokens == null) {
                    resultado += "FIN";
                    txtaResultadoLexico.setText(resultado);
                    return;
                }
                switch (tokens) {
                    case ERROR:
                        resultado += "Simbolo no definido\n";
                        break;
                    case Numero:
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";
                        alfabetoLista.add(lexer.lexeme);
                        break;
                    case Elemento:
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";
                        alfabetoLista.add(lexer.lexeme);
                        break;
                    case UnoOmas:
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";

                        break;
                    case Or:
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";

                        break;
                    case Adicion:
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";

                        break;
                    case ParAb:
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";

                        break;
                    case ParCe:
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";

                        break;
                    case Cerradura:
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";

                        break;
                    default:
                        resultado += "Token " + tokens + "\n";

                        break;
                }
                if (Analizado == true) {
                    Primitivas prim = new Primitivas();
                    arq = prim.generarPrimitivas(resultado, transicion);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnAFNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAFNDActionPerformed
        //Diagrama AFND empirico
        AutomataEmpirico conversor = new AutomataEmpirico();
        Automaton a = conversor.convertir(txtEntrada.getText());
        String estrDise = a.toDot().replace("Automaton", "AutomataCompiladores");
        //String estrAuto = conversor.generarAutomata(a.toString());
        //txtaAFD.setText(estrAuto);

        File archivo = new File("EstructuraAutomata.dot");
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(archivo);
            escribir.print(estrDise);
            escribir.close();
            Process process = Runtime.getRuntime().exec("C:/Program Files/Graphviz/bin/dot.exe -Tpng EstructuraAutomata.dot -o  C:/Users/EAeli/Documents/NetBeansProjects/ConversoER-AFD-AFND/Automata.png");
            ImageIcon ImagenAutomata;

            ImagenAutomata = new ImageIcon("C:/Users/EAeli/Documents/NetBeansProjects/ConversoER-AFD-AFND/Automata.png");
            ImagenAutomata.getImage().flush();
            Thread.sleep(2000);
            lblImagen.setIcon(ImagenAutomata);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //AFND CON e
        afnd = new AFND(txtEntrada.getText().replace(".", ""));
        afnd.agregarAnidacion();
        afnd.postfix();
        afnd.erAafnd();
        afnd.print(this.txtaAFND);
    }//GEN-LAST:event_btnAFNDActionPerformed

    private void btnAFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAFDActionPerformed
        AFD afd = new AFD(afnd.getParesDatos(), afnd.getLetra());
        afd.crearAFD();
        afd.imprimirAFD(this.txtaAFD);
    }//GEN-LAST:event_btnAFDActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAFD;
    private javax.swing.JButton btnAFND;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTextField txtEntrada;
    private javax.swing.JTextArea txtaAFD;
    private javax.swing.JTextArea txtaAFND;
    private javax.swing.JTextArea txtaResultadoLexico;
    // End of variables declaration//GEN-END:variables
}

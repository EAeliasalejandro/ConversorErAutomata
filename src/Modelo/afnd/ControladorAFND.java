package Modelo.afnd;

import Modelo.imprimir.Celda;
import java.util.Stack;

import Modelo.imprimir.ParesDatos;

public class ControladorAFND {
    private final int NFA_MAX = 256; 
    private Celda[] ArregloEstadosAFND = null;
    private Stack<Celda> PilaAFND = null;
    private int siguienteAsignacion = 0; 
    private int estadosAFND = 0; 
    
    public ControladorAFND()  {
    	ArregloEstadosAFND = new Celda[NFA_MAX];
    	for (int i = 0; i < NFA_MAX; i++) {
    		ArregloEstadosAFND[i] = new Celda();
    	}
    	
    	PilaAFND = new Stack<Celda>();
    	
    }
    
    public Celda nuevoAFND()  {
    	Celda afnd = null;
    	if (PilaAFND.size() > 0) {
    		afnd = PilaAFND.pop();
    	}
    	else {
    		afnd = ArregloEstadosAFND[siguienteAsignacion];
    		siguienteAsignacion++;
    	}
    	
    	afnd.clearState();
    	afnd.setEstado(estadosAFND++);
    	afnd.setEdge(Celda.EPSILON);
    	
    	return afnd;
    }
    
    public void descartarAFND(Celda nfaDiscarded) {
    	--estadosAFND;
    	nfaDiscarded.clearState();
    	PilaAFND.push(nfaDiscarded);
    }
    
   
}

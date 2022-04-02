package Modelo.imprimir;

public class Celda {
	public static final int EPSILON = -1;
	public static final int EMPTY = -2; 

	private int edge;

	public int getEdge() {
		return edge;
	}

	public void setEdge(int type) {
		edge = type;
	}

	public Celda next; 
	public Celda next2; 
	private int estado;
	private boolean visitado = false;
	
	public void setVisitado() {
		visitado = true;
	}

	public void setNoVisitado() {
		visitado = false;
	}
	
	public boolean isVisitado() {
		return visitado;
	}

	public void setEstado(int num) {
		estado = num;
	}

	public int getEstado() {
		return estado;
	}


	public void clearState() {
    	next = next2 = null;
    	estado = -1;		
	}
	
	@Override
	public String toString() {
		return (char)edge+" "+estado+""+isVisitado();
	}
}

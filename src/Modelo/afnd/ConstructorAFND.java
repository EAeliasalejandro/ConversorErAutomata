package Modelo.afnd;

import Modelo.imprimir.ParesDatos;

public class ConstructorAFND {
	private ControladorAFND controladorAFND = null;

	public ConstructorAFND() {
		controladorAFND = new ControladorAFND();
	}

	public ParesDatos contruirEstrella(ParesDatos pairIn) {
		ParesDatos pairOut = new ParesDatos();
		pairOut.nodoInicio = controladorAFND.nuevoAFND();
		pairOut.nodoFinal = controladorAFND.nuevoAFND();

		pairOut.nodoInicio.next = pairIn.nodoInicio;
		pairIn.nodoFinal.next = pairOut.nodoFinal;

		pairOut.nodoInicio.next2 = pairOut.nodoFinal;
		pairIn.nodoFinal.next2 = pairIn.nodoInicio;

		pairIn.nodoInicio = pairOut.nodoInicio;
		pairIn.nodoFinal = pairOut.nodoFinal;

		return pairOut;
	}

	public ParesDatos construirUnoOmas(ParesDatos pairIn) {
		ParesDatos pairOut = new ParesDatos();

		pairOut.nodoInicio = controladorAFND.nuevoAFND();
		pairOut.nodoFinal = controladorAFND.nuevoAFND();

		pairOut.nodoInicio.next = pairIn.nodoInicio;
		pairIn.nodoFinal.next = pairOut.nodoFinal;

		pairIn.nodoFinal.next2 = pairOut.nodoInicio;

		pairIn.nodoInicio = pairOut.nodoInicio;
		pairIn.nodoFinal = pairOut.nodoFinal;

		return pairOut;
	}

	public ParesDatos construirAFNDcaracterSolo(char c) {

		ParesDatos pairOut = new ParesDatos();
		pairOut.nodoInicio = controladorAFND.nuevoAFND();
		pairOut.nodoFinal = controladorAFND.nuevoAFND();
		pairOut.nodoInicio.next = pairOut.nodoFinal;
		pairOut.nodoInicio.setEdge(c);

		return pairOut;
	}

	public ParesDatos construirOr(ParesDatos left, ParesDatos right) {
		ParesDatos pair = new ParesDatos();
		pair.nodoInicio = controladorAFND.nuevoAFND();
		pair.nodoFinal = controladorAFND.nuevoAFND();

		pair.nodoInicio.next = left.nodoInicio;
		pair.nodoInicio.next2 = right.nodoInicio;

		left.nodoFinal.next = pair.nodoFinal;
		right.nodoFinal.next = pair.nodoFinal;

		return pair;
	}

	public ParesDatos construirAnidacion(ParesDatos left, ParesDatos right) {
		ParesDatos pairOut = new ParesDatos();
		pairOut.nodoInicio = left.nodoInicio;
		pairOut.nodoFinal = right.nodoFinal;

		left.nodoFinal.next = right.nodoInicio;

		return pairOut;
	}
}

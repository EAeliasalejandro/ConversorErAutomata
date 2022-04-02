package Modelo.afd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import Modelo.imprimir.TablaValores;
import Modelo.imprimir.Celda;
import Modelo.imprimir.ParesDatos;
import javax.swing.JTextArea;

public class AFD {
	private ParesDatos pares;
	private String[] letra;
	private TablaValores tabla;
	private Map<Set<Integer>, Integer> map;
	private Set<Integer> conjuntoTemporal;
	private Queue<Integer> cola = new LinkedList<>();
	private List<Character[]> afd = new ArrayList<>();
	private List<Character> estadoFinal = new ArrayList<>();
	
	private int state = 'A';

	public AFD(ParesDatos pair, String[] letter) {
		this.pares = pair;
		this.letra = letter;
		tabla = new TablaValores(letter.length - 1, true);
		tabla.appendRow();
		for (int i = 0; i < letter.length - 1; i++) {
			tabla.appendColum(letter[i]);
		}
		map = new HashMap<>();
	}

	public List<Character[]> getAFD() {
		List<Character[]> redfa = new ArrayList<>();
		for (Character[] ch : afd) {
			if(getSet(ch[0])==null||getSet(ch[0]).isEmpty()) {
				continue;
			}
			else {
				Character[] nuevoChar = new Character[ch.length];
				for(int i=0;i<ch.length;i++) {
					if(ch[i]==null)
						continue;
					Set<Integer> set = getSet(ch[i]);
					if(set == null||set.isEmpty())
						nuevoChar[i] = null;
					else
						nuevoChar[i] = ch[i];
				}
				redfa.add(nuevoChar);
			}
		}
		return redfa;
	}
	
	public List<Character> getEstadoFInal() {
		return estadoFinal;
	}
	
	public String[] getLetter() {
		return letra;
	}
	
	public void imprimirAFD(JTextArea txtaAFD) {
		
		txtaAFD.append("--------AFD--------\n");
		txtaAFD.append(""+tabla);
		for (Entry<Set<Integer>, Integer> entry : map.entrySet()) { 
			if(entry.getValue()==-1)
				continue;
			txtaAFD.append((char)entry.getValue().intValue() +" = " + entry.getKey() + (isStart(entry.getKey())?" START \n":"\n") + (isEnd(entry.getKey())?" END \n":"\n") ); 
		}
		txtaAFD.append("--------AFD--------\n");
	}

	private boolean isStart(Set<Integer> set) {
		for (Integer integer : set) {
			if(integer == pares.nodoInicio.getEstado())
				return true;
		}
		return false;
	}
	
	private boolean isEnd(Set<Integer> set) {
		for (Integer integer : set) {
			if(integer == pares.nodoFinal.getEstado()) {
				estadoFinal.add(new Character((char)getCharacter(set).intValue()));	
				return true;
			}
		}
		return false;
	}
	
	public void crearAFD() {
		conjuntoTemporal = new HashSet<>();
		Set<Integer> inicio= move(pares.nodoInicio,-1);
		map.put(inicio, state);
		cola.add(state++);
		while(!cola.isEmpty()) {
			Character[] dfaline = new Character[letra.length-1];
			int character = cola.poll();
			tabla.appendRow();
			tabla.appendColum((char)character);
			dfaline[0] = (char)character;
			Set<Integer> set = getSet(character);
			for(int i=1;i<letra.length-1;i++) {
				conjuntoTemporal = new HashSet<>();
				Set<Integer> midset = new HashSet<>();
				for (Integer integer : set) {
					Celda cell = getCelda(pares.nodoInicio, integer);
					revisit();
					if(cell==null) {
						continue;
					}
					else if((char)cell.getEdge() == letra[i].charAt(0)) {
						midset.add(cell.next.getEstado());
					}
				}
				for (Integer integer : midset) {
					Celda cell = getCelda(pares.nodoInicio, integer);
					revisit();
					move(cell, -1);
				}
				Integer c = getCharacter(conjuntoTemporal);
				if(c==null) {
					if(conjuntoTemporal.isEmpty()) {
						map.put(conjuntoTemporal, -1);
						tabla.appendColum("null");
						dfaline[i] = null;
					}
					else {
						cola.add(state);
						tabla.appendColum((char)state);
						dfaline[i] = (char)state;
						map.put(conjuntoTemporal, state++);
					}
				}
				else {
					if(c==-1) {
						tabla.appendColum("null");
						dfaline[i] = null;
					}
					else {
						dfaline[i] = (char)c.intValue();
						tabla.appendColum((char)c.intValue());
					}
				}
			}
			afd.add(dfaline);
		}
	}

	private Set<Integer> move(Celda nodoInicio, int i) {
		connect(nodoInicio, i);
		revisit();
		return conjuntoTemporal;
	}

	private void connect(Celda cell, int i) {
		if(cell==null||cell.isVisitado())
			return;
		cell.setVisitado();
		conjuntoTemporal.add(cell.getEstado());
		if(cell.getEdge()==-1||cell.getEdge()==i) {
			connect(cell.next, i);
			connect(cell.next2, i);
		}
		else
			return;
	}
	
	private Celda getCelda(Celda cell, int startstate) {
		if (cell == null || cell.isVisitado())
			return null;
		cell.setVisitado();
		if (cell.getEstado() == startstate)
			return cell;
		if (cell.getEstado() > startstate)
			return null;
		Celda temp1 = getCelda(cell.next, startstate);
		Celda temp2 = getCelda(cell.next2, startstate);
		if (temp1 != null)
			return temp1;
		if (temp2 != null)
			return temp2;
		return null;
	}
	
	private Integer getCharacter(Set<Integer> set) {
		return map.get(set);
	}
	
	private Set<Integer> getSet(int character) {
		for (Entry<Set<Integer>, Integer> m :map.entrySet())  {
			if(m.getValue()==character)
				return m.getKey();
		}
		return null;
	}
	
	private void revisit(Celda cell) {
		if (cell == null || !cell.isVisitado()) {
			return;
		}
		cell.setNoVisitado();
		revisit(cell.next);
		revisit(cell.next2);
	}
	private void revisit() {
		pares.nodoInicio.setNoVisitado();
		revisit(pares.nodoInicio.next);
		revisit(pares.nodoInicio.next2);
	}
	
	@Override
	public String toString() {
		return conjuntoTemporal.toString();
	}
}

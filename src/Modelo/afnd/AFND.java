/*
 * Generador de un AFND
 */
package Modelo.afnd;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import Modelo.imprimir.TablaValores;
import Modelo.imprimir.Celda;
import Modelo.imprimir.ParesDatos;
import javax.swing.JTextArea;

/**
 * @author EAeli
 */
public class AFND {

	private int restate = 0;
	
	private String er;
	private String erAdicion;
	private String rePostfix;

	private String[] letra;
	private ParesDatos pares;
	
	private TablaValores table;

	public AFND(String re) {
		this.er = re;
		erAdicion = null;
		rePostfix = null;
		Set<Character> temp = new HashSet<>();
		for(int i=0;i<this.er.length();i++){
			if(is_letter(this.er.charAt(i))){
				temp.add(this.er.charAt(i));
			}
		}
		letra = new String[temp.size()+2];
		Object []tempObj = temp.toArray();
		int i=0;
		letra[i] = "";
		for (;i<tempObj.length;i++) {
			letra[i+1] = (char)tempObj[i]+"";
		}
		letra[i+1] = "EPSILON";
		table = new TablaValores(letra.length, true);
		table.appendRow();
		for (String string : letra) {
			table.appendColum(string);
		}
	}

	public ParesDatos getParesDatos() {
		return pares;
	}
	
	public String[] getLetra() {
		return letra;
	}

	public void setParesDatos(ParesDatos pair) {
		this.pares = pair;
	}

	public String agregarAnidacion() {
		int length = er.length();
		if(length==1) {
			//System.out.println("Agregar anidación:" + re);
			erAdicion = er;
			return er;
		}
		int tamanoCadena = 0;
		char[] cadenaResultado = new char[2 * length + 2];
		char first, second = '0';
		for (int i = 0; i < length - 1; i++) {
			first = er.charAt(i);
			second = er.charAt(i + 1);
			cadenaResultado[tamanoCadena++] = first;
			if (first != '(' && first != '|' && is_letter(second)) {
				cadenaResultado[tamanoCadena++] = '.';
			}
			else if (second == '(' && first != '|' && first != '(') {
				cadenaResultado[tamanoCadena++] = '.';
			}
		}
		cadenaResultado[tamanoCadena++] = second;
		String rString = new String(cadenaResultado, 0, tamanoCadena);
		//System.out.println("Agregar anidación:" + rString);
		System.out.println();
		erAdicion = rString;
		return rString;
	}

	private boolean is_letter(char check) {
		{
			if (check >= 'a' && check <= 'z' || check >= 'A' && check <= 'Z')
				return true;
			return false;
		}
	}

	public String postfix() {
		erAdicion = erAdicion + "#";

		Stack<Character> s = new Stack<>();
		char ch = '#', ch1, op;
		s.push(ch);
		String out_string = "";
		int read_location = 0;
		ch = erAdicion.charAt(read_location++);
		while (!s.empty()) {
			if (is_letter(ch)) {
				out_string = out_string + ch;
				ch = erAdicion.charAt(read_location++);
			} else {
				ch1 = s.peek();
				if (isp(ch1) < icp(ch)) {
					s.push(ch);
					ch = erAdicion.charAt(read_location++);
				} else if (isp(ch1) > icp(ch)) {
					op = s.pop();
					out_string = out_string + op;
				} else {
					op = s.pop();
					if (op == '(')
						ch = erAdicion.charAt(read_location++);
				}
			}
		}
		rePostfix = out_string;
		return out_string;
	}

	private int isp(char c) {
		switch (c) {
		case '#':
			return 0;
		case '(':
			return 1;
		case '*':
			return 7;
		case '+':
			return 7;
		case '.':
			return 5;
		case '|':
			return 3;
		case ')':
			return 8;
		}
		return -1;
	}

	private int icp(char c) {
		switch (c) {
		case '#':
			return 0;
		case '(':
			return 8;
		case '*':
			return 6;
		case '+':
			return 6;
		case '.':
			return 4;
		case '|':
			return 2;
		case ')':
			return 1;
		}
		return -1;
	}

	public void erAafnd() {
		pares = new ParesDatos();
		ParesDatos temp = new ParesDatos();
		ParesDatos right, left;
		ConstructorAFND constructor = new ConstructorAFND();
		char ch[] = rePostfix.toCharArray();
		Stack<ParesDatos> stack = new Stack<>();
		for (char c : ch) {
			switch (c) {
			case '|':
				right = stack.pop();
				left = stack.pop();
				pares = constructor.construirOr(left, right);
				stack.push(pares);
				break;
			case '*':
				temp = stack.pop();
				pares = constructor.contruirEstrella(temp);
				stack.push(pares);
				break;
			case '+':
				temp = stack.pop();
				pares = constructor.construirUnoOmas(temp);
				stack.push(pares);
				break;
			case '.':
				right = stack.pop();
				left = stack.pop();
				pares = constructor.construirAnidacion(left, right);
				stack.push(pares);
				break;
			default:
				pares = constructor.construirAFNDcaracterSolo(c);
				stack.push(pares);
				break;
			}
		}
	}

	public void print(JTextArea txtaAFND) {
		restate(this.pares.nodoInicio);
		revisit(this.pares.nodoInicio);
		txtaAFND.append("--------AFND--------\n");
		table.appendRow();
		printNfa(this.pares.nodoInicio);
		txtaAFND.append(""+table);
		revisit(this.pares.nodoInicio);
		txtaAFND.append("--------AFND--------\n");
		txtaAFND.append("Estado Inicial: " + (this.pares.nodoInicio.getEstado())+"\n");
		txtaAFND.append("Estado Final: " + (this.pares.nodoFinal.getEstado())+"\n");
	}

	private void restate(Celda inicioAFND) {
		if (inicioAFND == null || inicioAFND.isVisitado()) {
			return;
		}
		inicioAFND.setVisitado();
		inicioAFND.setEstado(restate++);
		restate(inicioAFND.next);
		restate(inicioAFND.next2);
	}
	private void revisit(Celda startNfa) {
		if (startNfa == null || !startNfa.isVisitado()) {
			return;
		}
		startNfa.setNoVisitado();
		revisit(startNfa.next);
		revisit(startNfa.next2);
	}

	private void printNfa(Celda startNfa) {
		if (startNfa == null || startNfa.isVisitado()) {
			return;
		}

		startNfa.setVisitado();

		printNfaNode(startNfa);
		if (startNfa.next != null) {
			table.appendRow();
		}
		printNfa(startNfa.next);
		printNfa(startNfa.next2);
	}

	private void printNfaNode(Celda node) {
		if (node.next != null) {
			table.appendColum(node.getEstado());
			if(node.getEdge()==-1) {
				for(int i=0;i<letra.length-2;i++) {
					table.appendColum(" ");
				}
				if (node.next2 != null)
					table.appendColum("{"+node.next.getEstado()+","+node.next2.getEstado()+"}");
				else
					table.appendColum("{"+node.next.getEstado()+"}");
				}
			else {
				int index = getindex(""+(char)node.getEdge());
				for(int i=0;i<letra.length-1;i++) {
					if(i!=index)
						table.appendColum(" ");
					else {
						if (node.next2 != null)
							table.appendColum("{"+node.next.getEstado()+","+node.next2.getEstado()+"}");
						else
							table.appendColum("{"+node.next.getEstado()+"}");
					}
				}
			}
		}
		else {
			table.appendColum(node.getEstado());
			table.appendColum(" ");
			table.appendColum(" ");
			table.appendRow();
		}
	}
	
	//“”,a,b,EPS
	private int getindex(String ch) {
		for (int i=0;i<letra.length;i++) {
			if(letra[i].equals(ch))
				return i-1;
		}
		return -1;
	}
	
}
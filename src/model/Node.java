package model;

import java.awt.Point;
import java.util.ArrayList; 
import java.util.List; 

public class Node<T> { 
	
	private T dato;   
	private List<Node<T>> hijos;   
	private Node<T> padre; 
	private Point locale;
	
	public Node(T dato) {     
		this.dato = dato;     
		this.hijos = new ArrayList<>();   
		}    
	
	public Node(Node<T> nodo) {     
		this.dato = (T) nodo.getDato();     
		hijos = new ArrayList<>();   
	}    
	
	public void agregarHijo(Node<T> hijo) {     
		hijo.setPadre(this);     
		hijos.add(hijo);   
	}    
	
	public void agregarHijoEn(int posicion, Node<T> hijo) {     
		hijo.setPadre(this);     
		this.hijos.add(posicion, hijo); 
	}
	
	public void setHijos(List<Node<T>> hijos) {     
		for (Node<T> hijo : hijos) {
			hijo.setPadre(this);      
			this.hijos = hijos;
		}
	}    
	
	public void eliminarHijos() {     
		this.hijos.clear();   
	}    
	
	public Node<T> eliminarHijoEn(int posicion) {     
		return hijos.remove(posicion);   
	}
	        
	public void eliminarHijo(Node<T> hijoABorrar)   {     
		List <Node<T>> list = getHijos();     
		list.remove(hijoABorrar);   
	}    
	
	public T getDato() {     
		return this.dato;   
	}    
	
	public void setDato(T dato) {     
		this.dato = dato; 	
	} 
	
	public void setPadre(Node<T> padre) {     
		this.padre = padre;   
	}    
	
	public List<Node<T>> getHijos() {     
		return this.hijos;   
	}    
	
	public Node<T> getHijoEn(int posicion) {     
		return hijos.get(posicion);   
	}
	
	@Override   
	public boolean equals(Object obj) {     
		if (null == obj)       
			return false;      
		if (obj instanceof Node) {       
			if (((Node<?>) obj).getDato().equals(this.dato))         
				return true;     
			}      
		return false;   
	} 

	@Override   
	public String toString() {     
		return this.dato.toString();   
	}

	public Point getLocale() {
		return locale;
	}

	public void setLocale(Point locale) {
		this.locale = locale;
	}
	
}


package test;

import model.NTree;
import model.Node;
import model.Person;

public class TestNTree<T> {
	
	public static void main(String[] args) {
		Node<Person> node = new Node<Person>(new Person("Jorge"));
		NTree<Person> arbol = new NTree<>(new Node<Person>(
				node
		));
		
		arbol.getRaiz().agregarHijo(new Node<Person>(new Person("checho")));
		
		Node<Person> nodoB= arbol.searchNodeForData("checho");
		
		nodoB.agregarHijo(new Node<Person>(new Person("Pepe1")));
		nodoB.agregarHijo(new Node<Person>(new Person("Pepe2")));
		nodoB.agregarHijo(new Node<Person>(new Person("Pepe3")));
		nodoB.agregarHijo(new Node<Person>(new Person("Pepe4")));
		
		int num = arbol.getNumeroDescendientes(nodoB);
		
		System.out.println("Numero de desendientes: " + num);
	}

}

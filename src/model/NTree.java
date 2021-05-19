package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NTree<T> {
	
	private Node<T> raiz;
	private int altura;
		 
	public NTree(Node<T> raiz) {
		this.raiz = raiz;
	}
	
	public NTree() {
		
	}
	
	public boolean vacio() {
		return raiz == null;
	}
	
	public Node<T> getRaiz() {
		return raiz;
	}
	
	public void setRaiz(Node<T> raiz) {
		 this.raiz = raiz;
	}
	
	public boolean existe(T clave) {
		return encontrar(raiz, clave);
	}
		 
	public int getNumeroNodos() {
		return getNumeroDescendientes(raiz) + 1;	 
	}
		 
	public int getNumeroDescendientes(Node<T> nodo) {
		 int n = nodo.getHijos().size();
		 for (Node<T> hijo : nodo.getHijos()) {
			 n += getNumeroDescendientes(hijo);
		 }
		 
		 return n;
	}
		 
	private boolean encontrar(Node<T> nodo, T nodoClave) {
		boolean res = false;
		if (nodo.getDato().equals(nodoClave))
			return true;
		else {
			for (Node<T> hijo : nodo.getHijos())
				if (encontrar(hijo, nodoClave))
					res = true;
		}
		return res;
	}

	public Node<T> encontrarNodo(Node<T> nodo, T nodoClave) {
		if(nodo == null)
			return null;
		if(nodo.getDato().equals(nodoClave))
			return nodo;
		else{
			Node<T> cnodo = null;
			for (Node<T> hijo : nodo.getHijos())
				if ((cnodo = encontrarNodo(hijo, nodoClave)) != null)
					return cnodo;
		}
		
		return null;
	}
	
	public ArrayList<Node<T>> getPreOrder() {
		ArrayList<Node<T>> preOrder = new ArrayList<Node<T>>();
		construirPreOrder(raiz, preOrder);
		return preOrder;
	}
		 
	public ArrayList<Node<T>> getPostOrder() {
		ArrayList<Node<T>> postOrder = new ArrayList<Node<T>>();
		construirPostOrder(raiz, postOrder);
		return postOrder;
	}
	
	private void construirPreOrder(Node<T> nodo, ArrayList<Node<T>> preOrder) {
		if (nodo != null) {
			preOrder.add(nodo);
			for (Node<T> hijo : nodo.getHijos()) {
				construirPreOrder(hijo, preOrder);
			}
		}
	}
	
	private void construirPostOrder(Node<T> nodo, ArrayList<Node<T>> postOrder) {
		for (Node<T> hijo : nodo.getHijos()) {
			construirPostOrder(hijo, postOrder);
		}
		
		postOrder.add(nodo);
	}

	public ArrayList<Node<T>> caminoMasLargo() {
		ArrayList<Node<T>> camino = null;
		int max = 0;
		for (ArrayList<Node<T>> ruta : getRamas()) {
			if (ruta.size() > max) {
				max = ruta.size();
				camino = ruta;
			}
		}
		 return camino;
	}
		 
	public int getCaminoMasLargo(){
		 return caminoMasLargo().size();
	}

	public ArrayList<ArrayList<Node<T>>> getRamas() {
		ArrayList<ArrayList<Node<T>>> rutas = new
		ArrayList<ArrayList<Node<T>>>();
		ArrayList<Node<T>> camino = new ArrayList<Node<T>>();
		getPath(raiz, camino, rutas);
		return rutas;
	}
	
	private void getPath(Node<T> nodo, ArrayList<Node<T>> camino,
		ArrayList<ArrayList<Node<T>>> rutas) {
		if (nodo != null) {
			if (camino == null)
				return;
			camino.add(nodo);
			if (nodo.getHijos().size() == 0) {
				rutas.add(clone(camino));
			}
			 
			for (Node<T> hijo : nodo.getHijos()) 
				getPath(hijo, camino, rutas);
			 	int index = camino.indexOf(nodo);
			
			 	
			for (int i = index; i < camino.size(); i++) {
			 camino.remove(index);
			}
		}
	}
	
	private ArrayList<Node<T>> clone(ArrayList<Node<T>> list) {
		ArrayList<Node<T>> lista = new ArrayList<Node<T>>();
		for (Node<T> nodo : list)
			lista.add(new Node<T>(nodo)); 
		 return lista;
	}
	
	public void recorrerNiveles() {
        // lista para guardar los valores de los nodos
        List<T> valores = new ArrayList<>();
        // agregar el valor de la raíz
        valores.add(raiz.getDato());

        // iterar desde 1 hasta altura - 1
        // altura - 1 porque si el arbol tiene altura 4 solo se 
        // necesitan 3 niveles porque la raiz ya está incluida
        for (int i = 1; i < getNumeroNodos(); i++) {
            // llamda recursiva para bajar un nivel
            recorrerNiveles(raiz, i - 1, valores);
        }
        System.out.println(valores);

    }

    private void recorrerNiveles(Node<T> nodo, int nivel, List<T> valores) {
       // iterar los hijos del nodo
        for (Node<T> n : nodo.getHijos()) {
            // si ya se llegó al nivel 0 agregar los valores a la lista
            if (nivel == 0)
                valores.add(n.getDato());
            else // seguir bajando de nivel hasta llegar al nivel cero
                recorrerNiveles(n, nivel - 1, valores);
        }
    }
    
    /**
     * Retorna el nivel de un nodo en especifico
     * @param node nodo referencia, siempre debe de ser la raiz
     * @param data dato a buscar
     * @param level nivel de busqueda, siempre debe ser 0
     * @return nivel del dato
     */
    public int getLevel(Node<Person> node, String data, int level) {
    	int temp = 0;
    	if (node == null) {
			return -1;
		}else if(node.getDato().getName().equals(data)) {
			return level;
		}else {
			List<Node<Person>> listTemp = node.getHijos();
			int size = node.getHijos().size();
			//si el padre es alguno de los hijos
			for (int i = 0; i < size; i++) {
				temp = getLevel((Node<Person>) listTemp.get(i), data, level+1);
				if (temp != -1) {
					return temp;
				}
			}
			return -1;
		}
    }
    
    /**
     * Devuelve la cantidad de nodos que se encuentran en un nivel
     * @param level nivel a buscar
     * @return cantidad de nodos que se encuentran en el nivel
     */
    public int getNumberOfLevel(int levelToSearch) {
    	int number = 0;
    	List<Node<T>> list = this.getPreOrder();
    	for (int i = 0; i < list.size(); i++) {
    		Node<Person> nod = (Node<Person>) list.get(i);
    		int aux = getLevel((Node<Person>) this.getRaiz(), nod.getDato().getName(), 0);
    		if (aux == levelToSearch) {
				number++;
			}
		}
    	return number;
    }
}

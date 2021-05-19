package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.NTree;
import model.Node;
import model.Person;
import resources.Constants;

public class FrameTree extends JFrame{
	
	private BackgroundPanel panel;
	private PanelTree panelTree;
	private PanelInfo panelInfo;
	private JScrollPane scroll;
	private JButton buttonAdd;
	private NTree<Person> tree;
	
	static NTree<Person> arbol;
	
	public FrameTree() {
		tree = new NTree<>();
		this.setSize(Constants.SIZE_FRAME);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(true);
		addElements();
		init();
	}
	
	public void addElements() { 
		//añadimos el panel que dibujará el árbol
		panelTree = new PanelTree(tree, new Dimension(((this.getWidth()/4)*3) - 10, ((this.getHeight()/4)*3)- 10) , new Point(5,5));
		
		//scollpane que tendrá el panel del árbol
		scroll = new JScrollPane(panelTree);
		scroll.getViewport().setOpaque(false);
		scroll.setBackground(new Color(0,0,0,0));
		this.getContentPane().add(scroll);
		scroll.setSize(panelTree.getSize());
		scroll.setLocation(panelTree.getLocation());
		
		//añadimos el panel que Mostrará la info
		panelInfo = new PanelInfo(new Dimension((this.getWidth()/4) - 20, ((this.getHeight()/4)*3)- 10) , new Point(panelTree.getWidth() + 10, 5));
		this.getContentPane().add(panelInfo);
		
		// añadimos el panel de imagen de fondo
		panel = new BackgroundPanel(Constants.IMAGE_FRAME_TREE, this.getSize(), new Point(0, 0));
		this.getContentPane().add(panel);
		
		buttonAdd = new JButton(Constants.MESSAGE_ADD_PERSON);
		buttonAdd.setSize(Constants.SIZE_BUTTON_ADD);
		buttonAdd.setLocation(new Point(panelTree.getX(), panelTree.getY() + panelTree.getHeight() + 20));
		this.add(buttonAdd);
		
		buttonAdd.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initFrameRegistry();
			}
		});
	}
	
	public void initFrameRegistry() {
		new FrameRegistry(this, tree).init();
	}
	
	public void setPanelTree(NTree panelTree) {
		this.setVisible(false);
		PanelTree panelAux = new PanelTree(panelTree, this.panelTree.getSize(), this.panelTree.getLocation());
		this.remove(this.panelTree);
		this.panelTree = panelAux;
		this.add(this.panelTree);
		this.setVisible(true);
	}
	
	
	
	public PanelTree getPanelTree() {
		return panelTree;
	}

	public void init() {
		this.setVisible(true);
	}
	
//	public static void main(String[] args) {
//		FrameTree frame = new FrameTree();
//		
//		Node<Person> raiz = new Node<Person>(new Person("Pepe", 123, null));
//		arbol = new NTree<>(
//				raiz
//		);
//		
//		arbol.getRaiz().agregarHijo(new Node<Person>(new Person("Jorge", 789, null)));
//		arbol.getRaiz().agregarHijo(new Node<Person>(new Person("Elsa", 789, null)));
//		arbol.getRaiz().agregarHijo(new Node<Person>(new Person("Nicolas", 789, null)));
//		Node<Person> nodo = searchPerson("Jorge");
//		Node<Person> nodo2 = searchPerson("Nicolas");
//		Node<Person> nodo3 = searchPerson("Elsa");
//		nodo.agregarHijo(new Node<Person>(new Person("Nicolas0", 1234, null)));
//		nodo2.agregarHijo(new Node<Person>(new Person("Nicolas1", WIDTH, null)));
//		nodo2.agregarHijo(new Node<Person>(new Person("Nicolas2", WIDTH, null)));
//		Node<Person> nodo4 = searchPerson("Nicolas1");
//		nodo4.agregarHijo(new Node<Person>(new Person("El pepas", WIDTH, null)));
//		
//		frame.getPanelTree().drawTree(arbol);
//	}
//	
//	public static Node<Person> searchPerson(String nameDad) {
//		ArrayList<Node<Person>> list = arbol.getPreOrder();
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).getDato().getName() == nameDad) {
//				return list.get(i);
//			}
//		}
//		return null;
//	}
}

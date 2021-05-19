package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Line;
import model.NTree;
import model.Node;
import model.Person;

public class PanelTree extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Line> lines;
	private Dimension size;
	private Point locale;
	private NTree<Person> tree;
	private int auxLocale = 0;
	private int widthTree = 0;
	private int widthNode;
	private int x;
	private int y;
	private int level;
	private Point init;
	private Point end;
	
	public PanelTree(NTree<Person> tree, Dimension size, Point locale) {
		this.tree = tree;
		lines = new ArrayList<Line>();
		this.size = size;
		this.locale = locale;
		init();
		repaint();
	}
	
	public void init() {
		this.setLayout(null);
		this.setSize(size);
		this.setLocation(locale);
		this.setBackground(new Color(0,0,0,80));
	}
	
	public void drawTree(Graphics2D g) {
		int size = getWidthTree(tree.getRaiz());
		
		if (size == 0) {
			size = 1;
		}
		
		widthNode = (this.getWidth()/size) - 10;
		
		if (widthNode > 40) {
			widthNode = 40;
		}
		
		Rectangle rect = new Rectangle(middlePoint(this.getWidth(), widthNode), 0, widthNode, 20);
		g.draw(rect);
		g.drawString(tree.getRaiz().getDato().getName(), (int) (rect.getX() + (rect.getWidth()/4)), (int) (rect.getCenterY() + rect.getHeight()/4));
		
		init = new Point((int) (rect.getX() + (rect.getWidth()/2)), (int) (rect.getY() + rect.getHeight()));
		level = 1;
		y += 30;
		createNames(g, tree.getRaiz());
		paintComponent(this.getGraphics());
	}
	
	public void createNames(Graphics2D g, Node<Person> node) {
		int aux;
		List<Node<Person>> list = node.getHijos();
		for (int i = 0; i < list.size(); i++) {
			
			aux = tree.getLevel(tree.getRaiz(), node.getDato().getName(), 0);
			// comprovamos si nos encontramos en otro nivel
			int numberOfNodes = tree.getNumberOfLevel(aux);
			
			if (level != (aux)) {
				level = aux; 
				x = 0;
			}
			
			Point localeLabel = new Point(x, 60 * tree.getLevel(tree.getRaiz(), list.get(i).getDato().getName(), 0));
			list.get(i).setLocale(localeLabel);
			Rectangle rect = new Rectangle((int) localeLabel.getX(), (int) localeLabel.getY(), widthNode, 20);
			g.draw(rect);
			g.drawString(list.get(i).getDato().getName(), (int) (rect.getX() + (rect.getWidth()/4)), (int) (rect.getCenterY() + rect.getHeight()/4));

			end = new Point((int) (rect.getX() + (rect.getWidth()/2)), (int) rect.getY());
			
			//creamos la linea para dibujarla
			lines.add(new Line(init, end));
			x += (widthNode + 10);
			auxLocale++;
		}
		
		for (int i = 0; i < list.size(); i++) {
			Point auxPoint = list.get(i).getLocale();
			init = new Point((int) auxPoint.getX() + widthNode/2, (int) auxPoint.getY() + 30);
			createNames(g, list.get(i));
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			Graphics2D g2d = (Graphics2D) g;
			drawTree(g2d);
			g2d.setColor(Color.BLUE);
			for (int i = 0; i < lines.size(); i++) {
				Point initLine = lines.get(i).getInit();
				Point endLine = lines.get(i).getEnd();
				g2d.drawLine((int) initLine.getX(), (int) initLine.getY(), (int) endLine.getX(), (int) endLine.getY());
			}
			
		} catch (Exception e) {
			System.out.println("no se pudo pintar");
		}
		
	}
	
	public int getWidthTree(Node node) {
		List<Node> list = node.getHijos();
		int aux1 = list.size();
		int aux = 0;
		for (int  i = 0; i < list.size(); i++) {
			aux += list.get(i).getHijos().size();
		}
		
		if (aux > aux1) {
			if (aux > widthTree) {
				widthTree = aux;
			}
		} else {
			if (aux1 > widthTree) {
				widthTree = aux1;
			}
		}
		
		for (int  i = 0; i < list.size(); i++) {
			getWidthTree(list.get(i));
		}
		
		return widthTree;
	}
	
	public int middlePoint(int size1, int size2) {
		return (size1/2) - (size2/2);
	}

}

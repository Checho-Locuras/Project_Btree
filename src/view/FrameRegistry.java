package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import model.NTree;
import model.Node;
import model.Person;
import resources.Constants;

public class FrameRegistry extends JFrame{
	
	private JPanel panelImage;
	private JPanel panelRegistry;
	private JButton buttonOk;
	private JButton buttonCancel;
	private JTextField textName;
	private JTextField textID;
	private JCalendar calendar;
	private JComboBox<String> comboBox;
	private JLabel labelChild;
	private FrameTree frame;
	private NTree<Person> tree;
	
	public FrameRegistry(FrameTree frame, NTree tree) {
		this.frame = frame;
		this.tree = tree;
		this.setUndecorated(true);
		this.setSize(Constants.SIZE_WINDOW_REGISTRY);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		addElements();
		createButtons();
		setActions();
	}
	
	private void addElements() {
		panelImage = new BackgroundPanel(Constants.IMAGE_FAMILY, new Dimension((this.getWidth()/3) * 2, this.getHeight()), new Point(0,0));
		this.getContentPane().add(panelImage);
		panelRegistry = new BackgroundPanel(Color.LIGHT_GRAY, new Dimension(((this.getWidth()/3) * 1) + 3, this.getHeight()), new Point(panelImage.getWidth(), 0));
		this.getContentPane().add(panelRegistry);
		
		//creamos los campos de texto
		textName = new JTextField();
		textName.setSize((panelRegistry.getWidth()/5)*4, Constants.HEIGTH_FIELD_NAME);
		textName.setLocation(new Point(middlePoint(panelRegistry.getWidth(), textName.getWidth()), 20));
		TextPrompt placeholder = new TextPrompt(Constants.MESSAGE_NAME, textName);
	    placeholder.changeAlpha(0.75f);
	    placeholder.changeStyle(Font.ITALIC);
		
		textID = new JTextField();
		textID.setSize(textName.getSize());
		textID.setLocation(new Point(middlePoint(panelRegistry.getWidth(), textID.getWidth()), 50 + textName.getY()));
		TextPrompt placeholder2 = new TextPrompt(Constants.MESSAGE_ID, textID);
	    placeholder2.changeAlpha(0.75f);
	    placeholder2.changeStyle(Font.ITALIC);
		
		panelRegistry.add(textName);
		panelRegistry.add(textID);
		
		//creamos el label de los hijos
		labelChild = new JLabel(Constants.MESSAGE_CHILD_OF);
		labelChild.setSize(textName.getWidth()/4, textName.getHeight());
		labelChild.setLocation(textID.getX(), textID.getY() + 50);
		panelRegistry.add(labelChild);
		
		//creamos el comboBox
		comboBox = new JComboBox();
		comboBox.setSize((textID.getWidth()/3)*2, textID.getHeight());
		comboBox.setLocation(labelChild.getX() + labelChild.getWidth() + 10, labelChild.getY());
		panelRegistry.add(comboBox);
		
		//creamos el calendario
		calendar = new JCalendar();
		calendar.setSize(textID.getWidth(),130);
		calendar.setLocation(middlePoint(panelRegistry.getWidth(), calendar.getWidth()), comboBox.getY() + 50);
		calendar.setWeekOfYearVisible(false);
		calendar.setMaxDayCharacters(3);
		panelRegistry.add(calendar);
		initCombo();
	}
	
	public void createButtons() {
		buttonOk = new JButton(Constants.MESSAGE_CREATE);
		buttonOk.setSize(new Dimension(panelRegistry.getWidth()/2, 30));
		buttonOk.setLocation(new Point(middlePoint(panelRegistry.getWidth(), buttonOk.getWidth()), panelRegistry.getHeight()-80));
		panelRegistry.add(buttonOk);
		
		buttonCancel = new JButton(Constants.MESSAGE_CANCEL);
		buttonCancel.setSize(new Dimension(panelRegistry.getWidth()/2, 30));
		buttonCancel.setLocation(new Point(middlePoint(panelRegistry.getWidth(), buttonCancel.getWidth()), panelRegistry.getHeight()-40));
		panelRegistry.add(buttonCancel);
	}
	
	/**
	 * Prueba para iniciar el comboBox
	 */
	public void initCombo() {
		ArrayList<Node<Person>> list = tree.getPreOrder();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				comboBox.addItem(list.get(i).getDato().getName());
			}
		}
	}
	
	public void setActions() {
		buttonOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameDad = (String) comboBox.getSelectedItem();
				String nameNewPerson = textName.getText();
				int id = Integer.parseInt(textID.getText());
				//Date date = (Date) calendar.getDate();
				Person person = new Person(nameNewPerson, id, null);
				if (tree.getRaiz() == null) {
					Node<Person> root = new Node<Person>(person);
					tree.setRaiz(root);
				}else {
					Node<Person> nodeToInsert = searchPerson(nameDad);
					nodeToInsert.agregarHijo(new Node<Person>(person));
				}
				
				frame.setPanelTree(tree);
				endThis();
			}
		});
	}
	
	public void endThis() {
		this.setVisible(false);
		this.dispose();
	}
	
	public Node<Person> searchPerson(String nameDad) {
		ArrayList<Node<Person>> list = tree.getPreOrder();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDato().getName() == nameDad) {
				return list.get(i);
			}
		}
		return null;
	}
	
	public int middlePoint(int size1, int size2) {
		return (size1/2) - (size2/2);
	}
	
	public void init() {
		this.setVisible(true);
	}

}

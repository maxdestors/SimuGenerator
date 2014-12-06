package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.eclipse.jface.viewers.ComboBoxCellEditor;

import com.google.inject.Singleton;

import fr.unice.deptinfo.maven_compiler.FileTool;
public final class FenetreChoix implements ActionListener {

	private static String choix;
	private static volatile FenetreChoix instance = null;
	private JComboBox combo = new JComboBox();
	
	
	public String getChoix(){
		return choix;
	}
	
	
	private FenetreChoix(){
		super();
	}
	
	public final static FenetreChoix getInstance() {
		if(FenetreChoix.instance == null){
			synchronized (Singleton.class) {
				if(FenetreChoix.instance == null){
					FenetreChoix.instance = new FenetreChoix();
				}
				
			}
		}
		return FenetreChoix.instance;
	}
	
	public void choixConf(){
		//String choix;
		File f = null;
		ArrayList<String> configs = new ArrayList<String>();
		f = new File("./config");
		configs = (ArrayList<String>) FileTool.listFiles(f, "");


		JFrame fenetre = new JFrame();
		JPanel container = new JPanel();
		
		//JComboBox combo = new JComboBox();
		JLabel label = new JLabel("Configuration");
		JButton buttonValider = new JButton("Valider");
		JButton buttonEditer = new JButton("Editer");
		
		buttonEditer.addActionListener(this);
		buttonValider.addActionListener(this);
		

		fenetre.add(container);
		fenetre.add(combo);
		fenetre.add(label); 

		fenetre.setTitle("Simu-generator");
		fenetre.setSize(250, 150);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());

		combo.setPreferredSize(new Dimension(100, 20));

		JPanel top = new JPanel();
		top.add(label);
		top.add(combo);

		JPanel down = new JPanel();
		down.add(buttonValider, BorderLayout.EAST);
		down.add(buttonEditer, BorderLayout.WEST);
		
		container.add(top, BorderLayout.NORTH);
		container.add(down, BorderLayout.SOUTH);
		
		fenetre.setContentPane(container);
		fenetre.setVisible(true);    

		//Ajout des propositions dans la comboBox
		for(String p:configs){
			combo.addItem(p);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// do something
		if(e.getActionCommand().equals("Valider")) {
			this.choix = combo.getSelectedItem().toString();
		}
		else if(e.getActionCommand().equals("Editer")) {
			System.out.println(choix);
		}
	}
}

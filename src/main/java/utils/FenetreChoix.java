package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.unice.deptinfo.maven_compiler.FileTool;
public class FenetreChoix {

	public static void choixConf(){

		File f = null;

		ArrayList<String> configs = new ArrayList<String>();
		f = new File("./config");
		configs = (ArrayList<String>) FileTool.listFiles(f, "");

		if(configs.isEmpty()){
			System.out.println("aucun fichier de configuration n'a été trouvé");
			//L'idée est d'y proposer d'en rentrer une a la main et de le mettre dans un string
		}
		else{
			//Création de la fenetre de choix des configurations
			JFrame fenetre = new JFrame();
			JPanel container = new JPanel();
			JComboBox combo = new JComboBox();
			JLabel label = new JLabel("Configuration");
			JButton button = new JButton("Valider");


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
			container.add(top, BorderLayout.NORTH);
			container.add(button, BorderLayout.SOUTH);
			fenetre.setContentPane(container);
			fenetre.setVisible(true);    

			//Ajout des propositions dans la comboBox
			for(String p:configs){
				combo.addItem(p);
			}
		}


	}
}

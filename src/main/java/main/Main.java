package main;

import feature.Features;
import generator.simulator.GenerateProjectSimulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main {
	
	 private static Logger logger = Logger.getLogger("main.Main");

	public static void main(String args[]) {
	    
		
		
		/*
		
		File f = null;
		File[] paths;
		Map<String,String> configs = new HashMap<String,String>();
		f = new File("./config");
		String fmName = "toto";
		String FM = fmName+" = FM(A: E F; E: (B|C|D); F: (X|Y)+;)";
		String configName = "config1";
		FileChannel in = null; // canal d'entrée
		FileChannel out = null; // canal de sortie

		//Chargement d'un fichier dans l'application
		paths = f.listFiles();

		for(File p:paths){
			System.out.println(p);
			configs.put(p.getName(), p.getAbsolutePath());
			System.out.println(p.getName());
		}

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
			for(File p:paths){
				combo.addItem(p.getName());
			}
		}
		
		
		
		
		*/
		
		
		
		
		Logger.getLogger("main").setLevel(Level.ALL);
		String pathProject = "D:\\Documents\\GitHub\\TP_GL";
		// Romain // C:\\Documents\\M1\\GL\\TP_GL
		// Max // D:\\Documents\\GitHub\\TP_GL
		// Pierre // 
		String pathProjectToBuild = "D:\\Documents\\GitHub\\TP_GL_new";
		// Romain // C:\\Documents\\GitHub\\TP_GL_new
		// Max // D:\\Documents\\GitHub\\TP_GL_new
		// Pierre // 
		
		/* ATTENTION A BIEN VERIFIER VOTRE CHEMIN AVANT DE DECOMMENTER !!!
		// supprime tout les fichier dans le nouveau projet (pas b'soin de l'faire a la main)
		emptyDirectory(new File(pathProjectToBuild));
		logger.info("Erase : "+pathProjectToBuild+"*");
		//*/
		
	    List<String> config = new ArrayList<String>();
		/*
		 * Lent, Sequentiel, Action, VitesseSimu, Moteur, SimuTechno, Rapide, Normal
		 */
	    config.add("SimuTechno");
	    config.add("Moteur");
	    config.add("Action");
	    config.add("VitesseSimu");
	    config.add("Sequentiel");
	    config.add("Rapide");
	    config.add("Normal");
	    config.add("Lent");
	    /*/
    	Features fs = new Features();
    	fs.askConfig();
    	config = new ArrayList<String>(fs.getConfig());
    	//*/
	    
	    GenerateProjectSimulator gps = new GenerateProjectSimulator(pathProject, pathProjectToBuild, config);
		gps.generate();

	}
	
	
	public static void emptyDirectory(File folder){
		for(File file : folder.listFiles()){
			if(file.isDirectory()){
				emptyDirectory(file);
			}
			file.delete();
		}
	}


	
}

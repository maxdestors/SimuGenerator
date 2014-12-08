package fr.unice.deptinfo.simu_generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.FenetreChoix;
//import utils.FenetreChoix;
import feature.Features;
import generator.simulator.GenerateProjectSimulator;

public class App 
{
	//variable utile pour l'interface graphique
	private static Logger logger = Logger.getLogger("main.Main");
	private static File pathProject = new File("./../TP_GL");
	private static File pathProjectToBuild = null;
    private static JFrame fenetre = new JFrame();
    
    
    public static void main(String args[]) {
    	
    	
    	//========= Création de l'interface graphique =========//
    	JPanel container = new JPanel();
    	
    	
		JButton buttonValider = new JButton("Valider");
		JButton buttonAnnuler = new JButton("Annuler");
		JButton buttonDest = new JButton("Destination");
		buttonAnnuler.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        System.exit(0);
		    }
		});
		
		buttonValider.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(pathProjectToBuild != null){
		    	fenetre.dispose();
		    	FenetreChoix.getInstance().choixConf();
		    	}
		    	else
		    	{
		    		javax.swing.JOptionPane.showMessageDialog(null,"Veuillez selectionner le repertoire "
		    														+ "source et le repertoire de destination"); 
		    	}
		    }
		});
		
		buttonDest.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	//Create a file chooser
		    	final JFileChooser fc = new JFileChooser();
		    	fc.setMultiSelectionEnabled(false);
		    	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    	
		    	//In response to a button click:
		    	fc.showOpenDialog(fenetre);
		    	pathProjectToBuild = fc.getSelectedFile();
		    }
		});
		fenetre.add(container);
		fenetre.setTitle("Simu-generator");
		fenetre.setSize(250, 150);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		
		JPanel down = new JPanel();
		JPanel top = new JPanel();
		
		
		down.add(buttonValider, BorderLayout.EAST);
		down.add(buttonAnnuler, BorderLayout.WEST);
		top.add(buttonDest, BorderLayout.CENTER);
		container.add(down, BorderLayout.SOUTH);
		container.add(top, BorderLayout.CENTER);
		
		fenetre.setContentPane(container);
		fenetre.setVisible(true);    
 
		
    	
		Logger.getLogger("main").setLevel(Level.ALL);
		
	    	    
	}
	
	
	
    public static void ChargerConfig(String name, String path)
    {
    	ArrayList<String> listConfigs = new ArrayList<String>();
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				listConfigs.add(ligne);
			}
			br.close();
			listConfigs.add("exit");
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		    	
    	Features fs = new Features(name.replaceAll(".fml", ""));
    	Collection<String> config = fs.askConfig(listConfigs);
    	
    	if(config != null) {
		    GenerateProjectSimulator gps = new GenerateProjectSimulator(pathProject.getAbsolutePath(), pathProjectToBuild.getAbsolutePath(), new ArrayList<String>(config));
			gps.generate();
    	}
    	else {
    		logger.info("La configuration est invalide le projet n'a pas pu etre compile");
    	}

    }

}

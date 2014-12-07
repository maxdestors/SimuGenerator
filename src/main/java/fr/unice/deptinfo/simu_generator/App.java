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
	private static String pathProject = null;
	private static String pathProjectToBuild = null;
    private static JFrame fenetre = new JFrame();
    
    
    public static void main(String args[]) {
    	
    	
    	//========= Création de l'interface graphique =========//
    	JPanel container = new JPanel();
    	
    	
		JButton buttonValider = new JButton("Valider");
		JButton buttonAnnuler = new JButton("Annuler");
		JButton buttonOrig = new JButton("Source");
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
		    	if(pathProject != null && pathProjectToBuild != null){
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
		
		buttonOrig.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	//Create a file chooser
		    	final JFileChooser fc = new JFileChooser();
		    	fc.setMultiSelectionEnabled(false);
		    	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    	
		    	//In response to a button click:
		    	fc.showOpenDialog(fenetre);
		    	pathProject = fc.getSelectedFile().toString();
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
		    	pathProjectToBuild = fc.getSelectedFile().toString();
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
		top.add(buttonOrig, BorderLayout.EAST);
		top.add(buttonDest, BorderLayout.WEST);
		container.add(down, BorderLayout.SOUTH);
		container.add(top, BorderLayout.CENTER);
		
		fenetre.setContentPane(container);
		fenetre.setVisible(true);    
 
		
    	
    	
    	
    	// COMMENTER LES 4 lignes ci dessous, juste pour vérifier l'exécution de l'appli par maven
    	/*JFrame jf = new JFrame();
		jf.setTitle("Rapports test");
		jf.setSize(430, 840);
		jf.setVisible(true);*/
    	
		Logger.getLogger("main").setLevel(Level.ALL);
		
		// Romain // C:\\Documents\\M1\\GL\\TP_GL
		// Max // D:\\Documents\\GitHub\\TP_GL
		// Pierre // C:\\Users\\Pierre\\Desktop\\GL\\TEST
		
		// Romain // C:\\Documents\\GitHub\\TP_GL_new
		// Max // D:\\Documents\\GitHub\\TP_GL_new
		// Pierre // C:\\Users\\Pierre\\Desktop\\GL\\TEST
		
		/* ATTENTION A BIEN VERIFIER VOTRE CHEMIN AVANT DE DECOMMENTER !!!
		// supprime tout les fichier dans le nouveau projet (pas b'soin de l'faire a la main)
		emptyDirectory(new File(pathProjectToBuild));
		logger.info("Erase : "+pathProjectToBuild+"*");
		//*/
		
	    //List<String> config = new ArrayList<String>();
		/* Lent, Sequentiel, Action, VitesseSimu, Moteur, SimuTechno, Rapide, Normal
	    config.add("SimuTechno");
	    config.add("Moteur");
	    config.add("Action");
	    config.add("VitesseSimu");
	    config.add("Sequentiel");
	    config.add("Rapide");
	    config.add("Normal");
	    config.add("Lent");
	    config.add("Visu");
	    config.add("Creature");
	    config.add("Movement");
	    config.add("Environnement");
	    config.add("Couleur");
	    config.add("Nombre");
	    config.add("Random");
	    config.add("Flock");
	    config.add("Bouncing");
	    config.add("Toric");
	    config.add("Circular");
	    config.add("Closed");
	    /*/
    	//Features fs = new Features();
    	//fs.askConfig();
    	//config = new ArrayList<String>(fs.getConfig());
    	//*/
	    
	    //GenerateProjectSimulator gps = new GenerateProjectSimulator(pathProject, pathProjectToBuild, config);
		//gps.generate();

	    	    
	}
	
  	public static void emptyDirectory(File folder){
		for(File file : folder.listFiles()){
			if(file.isDirectory()){
				emptyDirectory(file);
			}
			file.delete();
		}
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
		    GenerateProjectSimulator gps = new GenerateProjectSimulator(pathProject, pathProjectToBuild, new ArrayList<String>(config));
			gps.generate();
    	}
    	else {
    		logger.info("La configuration est invalide le projet n'a pas pu etre compile");
    	}

    }

}

/*
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

class EssaiChoixFichier {
    public static void main(String[] arg) throws IOException {
	JFileChooser dialogue = new JFileChooser(new File("."));
	PrintWriter sortie;
	File fichier;
	
	if (dialogue.showOpenDialog(null)== 
	    JFileChooser.APPROVE_OPTION) {
	    fichier = dialogue.getSelectedFile();
	    sortie = new PrintWriter
		(new FileWriter(fichier.getPath(), true));
	    sortie.println(arg[0]);
	    sortie.close();
	}
    }
}*/

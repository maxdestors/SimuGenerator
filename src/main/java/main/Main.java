package main;

import feature.Features;
import generator.simulator.GenerateProjectSimulator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
	
	 private static Logger logger = Logger.getLogger("main.Main");

	public static void main(String args[]) {
	    Logger.getLogger("main").setLevel(Level.ALL);
		String pathProject = "D:\\Documents\\GitHub\\TP_GL";
		// Romain // C:\\Documents\\M1\\GL\\TP_GL
		// Max // D:\\Documents\\GitHub\\TP_GL
		// Pierre // 
		String pathProjectToBuild = "D:\\Documents\\GitHub\\TP_GL_new";
		// Romain // C:\\Documents\\GitHub\\TP_GL_new
		// Max // D:\\Documents\\GitHub\\TP_GL_new
		// Pierre // 
		
		//* ATTENTION A BIEN VERIFIER VOTRE CHEMIN AVANT DE DECOMMENTER !!!
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

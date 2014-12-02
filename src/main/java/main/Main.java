package main;

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
		String pathProject = "C:\\Users\\Romain\\Documents\\M1\\GL\\TP_GL";
		// Romain // C:\\Documents\\M1\\GL\\TP_GL
		// Max // D:\\Documents\\GitHub\\TP_GL
		// Pierre // 
		String pathProjectToBuild = "C:\\Users\\Romain\\Documents\\GitHub\\TP_GL_new";
		// Romain // C:\\Documents\\GitHub\\TP_GL_new
		// Max // D:\\Documents\\GitHub\\TP_GL_new
		// Pierre // 
		
		/* ATTENTION A BIEN VERIFIER VOTRE CHEMIN AVANT DE DECOMMENTER !!!
		// supprime tout les fichier dans le nouveau projet (pas b'soin de l'faire a la main)
		emptyDirectory(new File(pathProjectToBuild));
		logger.info("Erase : "+pathProjectToBuild+"*");
		//*/
		
	    List<String> config = new ArrayList<String>();
	    config.add("apercu");
	    config.add("creature");
	    config.add("rapide");
	    
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

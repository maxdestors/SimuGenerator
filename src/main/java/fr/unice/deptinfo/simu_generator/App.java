package fr.unice.deptinfo.simu_generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



import javax.swing.JFrame;

import utils.FenetreChoix;
//import utils.FenetreChoix;
import feature.Features;

public class App 
{
	
	private static Logger logger = Logger.getLogger("main.Main");
	 
 
    
    public static void main(String args[]) {
    	
    	// COMMENTER LES 4 lignes ci dessous, juste pour vérifier l'exécution de l'appli par maven
    	/*JFrame jf = new JFrame();
		jf.setTitle("Rapports test");
		jf.setSize(430, 840);
		jf.setVisible(true);*/
    	
		Logger.getLogger("main").setLevel(Level.ALL);
		String pathProject = "C:\\Users\\Pierre\\Desktop\\GL\\TP_GL";
		// Romain // C:\\Documents\\M1\\GL\\TP_GL
		// Max // D:\\Documents\\GitHub\\TP_GL
		// Pierre // C:\\Users\\Pierre\\Desktop\\GL\\TEST
		String pathProjectToBuild = "C:\\Users\\Pierre\\Desktop\\GL\\TEST";
		// Romain // C:\\Documents\\GitHub\\TP_GL_new
		// Max // D:\\Documents\\GitHub\\TP_GL_new
		// Pierre // C:\\Users\\Pierre\\Desktop\\GL\\TEST
		
		/* ATTENTION A BIEN VERIFIER VOTRE CHEMIN AVANT DE DECOMMENTER !!!
		// supprime tout les fichier dans le nouveau projet (pas b'soin de l'faire a la main)
		emptyDirectory(new File(pathProjectToBuild));
		logger.info("Erase : "+pathProjectToBuild+"*");
		//*/
		
	    List<String> config = new ArrayList<String>();
		//* Lent, Sequentiel, Action, VitesseSimu, Moteur, SimuTechno, Rapide, Normal
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
    	Features fs = new Features();
    	fs.askConfig();
    	config = new ArrayList<String>(fs.getConfig());
    	//*/
	    
	    //GenerateProjectSimulator gps = new GenerateProjectSimulator(pathProject, pathProjectToBuild, config);
		//gps.generate();

	    FenetreChoix.getInstance().choixConf();	    
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
    	fs.askConfig(listConfigs);
    }

}

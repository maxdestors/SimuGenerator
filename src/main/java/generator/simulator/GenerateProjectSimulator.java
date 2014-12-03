package generator.simulator;

import generator.GenerateProject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

public class GenerateProjectSimulator extends GenerateProject {
	
	 private static Logger logger = Logger.getLogger("generator.simulator.GeneratorProjectSimulator");

	
	public GenerateProjectSimulator(String pathProject, String pathProjectToBuild, List<String> configuration) {
		super(pathProject, pathProjectToBuild, configuration);
	}
	
	public void generate() {
		
		logger.info("Copie des fichiers obligatoires :");
		
		copyFolder("src"+System.getProperty("file.separator")+"simulator", true);
		copyFolder("src"+System.getProperty("file.separator")+"commons", true);
		
		
		logger.info("Copie des fichiers Optionel :");
		//appel des config pour ajouter les parametres demand�s
		for (String node : config) {
			logger.severe("Appel de la fonction : "+node);
			Method method;
			try {
				method = this.getClass().getMethod(node);// .toLowerCase() ??
				method.invoke(this);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	public void visu() {
		logger.info("visu ");
		
		copyFolder("src"+System.getProperty("file.separator")+"visual", false);
		copyFolder("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"visual", false);

		/*List<String> fileToCpy = new ArrayList<String>();
		fileToCpy.add("src"+System.getProperty("file.separator")+"simulator"+System.getProperty("file.separator")+"IActionable.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"simulator"+System.getProperty("file.separator")+"ISimulationListener.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"simulator"+System.getProperty("file.separator")+"Simulator.java");
		
		createFolder("src"+System.getProperty ("file.separator")+"simulator");
		for(String pathFile : fileToCpy) {
			copyFile(pathFile);
		}*/
	}
	
	public void Normal() {
		logger.info("");
	}
	public void Rapide() {
		logger.info("");
	}
	public void Lent() {
		logger.info("");
	}
	public void Sequentiel() {
		logger.info("");
	}
	public void Action() {
		logger.info("");
	}
	public void VitesseSimu() {
		logger.info("");
	}
	public void Moteur() {
		logger.info("");
	}
	public void SimuTechno() {
		logger.info("");
	}
	public void Nombre() {
		logger.info("");
	}
	public void Toric() {
		logger.info("");
	}
	public void Creature() {
		logger.info("");
	}
	public void Groupe() {
		logger.info("");
	}
	public void Milliers() {
		logger.info("");
	}
	public void Random() {
		logger.info("");
	}
	public void Environnement() {
		logger.info("");
	}
	public void NAleatoire() {
		logger.info("");
	}
	public void Bouncing() {
		logger.info("");
	}
	public void Movement() {
		logger.info("");
	}
	public void Dizaine() {
		logger.info("");
	}
	public void Centaine() {
		logger.info("");
	}
	public void Circular() {
		logger.info("");
	}
	public void Couleur() {
		logger.info("");
	}
	public void Fixe() {
		logger.info("");
	}
	public void Cube() {
		logger.info("");
	}
	public void Closed() {
		logger.info("");
	}
	public void Unique() {
		logger.info("");
	}
	public void Flock() {
		logger.info("");
	}
	
}

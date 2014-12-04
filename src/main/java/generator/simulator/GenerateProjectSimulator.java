package generator.simulator;

import generator.GenerateProject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

import utils.FileTool;

public class GenerateProjectSimulator extends GenerateProject {
	
	 private static Logger logger = Logger.getLogger("generator.simulator.GeneratorProjectSimulator");

	
	public GenerateProjectSimulator(String pathProject, String pathProjectToBuild, List<String> configuration) {
		super(pathProject, pathProjectToBuild, configuration);
	}
	
	public void generate() {
		
		logger.info("Copie des fichiers :");
		//appel des config pour ajouter les parametres demand�s
		for (String node : config) {
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
		
		
		try {
			for(String pathDir : dirToCpy) {
				FileTool.copyFilesRecursively(new File(pathProject+System.getProperty("file.separator")+pathDir), new File(pathProject+System.getProperty("file.separator")), new File(pathProjectToBuild+System.getProperty("file.separator")));
			}
			
			for(String pathFile : fileToCpy) {
				FileTool.createDirectory(pathProjectToBuild+System.getProperty("file.separator")+pathFile.substring(0, pathFile.lastIndexOf('\\')));
				FileTool.copy(new File(pathProject+System.getProperty("file.separator")+pathFile), new File(pathProjectToBuild+System.getProperty("file.separator")+pathFile));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void Visu() {
		logger.info("Visu");
		
		/*
		 * src/visual/*
		 * src/creatures/visual/*
		 */
		
		dirToCpy.add("src"+System.getProperty("file.separator")+"visual");
		dirToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"visual");
		
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
		logger.info("Moteur");

		/*
		 * src/simulator/*
		 * src/commons/*
		 * src/plug/IPlugin.java
		 * src/plug/PluginLoader.java
		 */
		
		dirToCpy.add("src"+System.getProperty("file.separator")+"simulator");
		dirToCpy.add("src"+System.getProperty("file.separator")+"commons");
			
		fileToCpy.add("src"+System.getProperty("file.separator")+"plug"+System.getProperty("file.separator")+"IPlugin.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"plug"+System.getProperty("file.separator")+"PluginLoader.java");
			
	}
	public void SimuTechno() {
		logger.info("");
	}
	public void Nombre() {
		logger.info("");
	}
	public void Toric() {
		logger.info("Toric");
		/*
		 * src/worlds/WorldToric.java
		 * test/worlds/WorldToricTest.java
		 */

		fileToCpy.add("src"+System.getProperty("file.separator")+"worlds"+System.getProperty("file.separator")+"WorldToric.java");
		fileToCpy.add("test"+System.getProperty("file.separator")+"worlds"+System.getProperty("file.separator")+"WorldToricTest.java");
		
	}
	public void Creature() {
		logger.info("");
		/*
		 * src/plug/creatures/IPluginFactory.java
		 * src/plug/creatures/CreatureFactory.java
		 * src/creatures/AbstractCreature.java
		 * src/creatures/CreatureBuilder.java
		 * src/creatures/ICreature.java
		 * src/creatures/IEnvironment.java
		 * src/creatures/StandardCreature.java
		 * src/creatures/IColorStrategy.java
		 * src/creatures/visual/ColorCube.java
		 */

		fileToCpy.add("src"+System.getProperty("file.separator")+"plug"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"IPluginFactory.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"plug"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"CreatureFactory.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"AbstractCreature.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"CreatureBuilder.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"ICreature.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"IEnvironment.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"StandardCreature.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"IColorStrategy.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"visual"+System.getProperty("file.separator")+"ColorCube.java");
		
	}
	public void Groupe() {
		logger.info("");
	}
	public void Milliers() {
		logger.info("");
	}
	public void Random() {
		logger.info("Random");
		/*
		 * src/creatures/movement/MovementRandom.java
		 * test/creatures/movement/MovementRandomTest.java
		 */

		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"movement"+System.getProperty("file.separator")+"MovementRandom.java");
		fileToCpy.add("test"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"movement"+System.getProperty("file.separator")+"MovementRandomTest.java");

	}
	public void Environnement() {
		logger.info("Environnement");
		/*
		 * src/worlds/IWorld.java
		 * src/plug/creatures/WorldPluginFactory.java
		 * src/plug/creatures/PluginMenuItemBuilderWorld.java
		 */
		
		fileToCpy.add("src"+System.getProperty("file.separator")+"worlds"+System.getProperty("file.separator")+"IWorld.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"plug"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"WorldPluginFactory.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"plug"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"PluginMenuItemBuilderWorld.java");
			
	}
	public void NAleatoire() {
		logger.info("");
	}
	public void Bouncing() {
		logger.info("Bouncing");
		/*
		 * src/creatures/movement/MovementBouncing.java
		 * test/creatures/movement/MovementBouncingTest.java
		 */

		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"movement"+System.getProperty("file.separator")+"MovementBouncing.java");
		fileToCpy.add("test"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"movement"+System.getProperty("file.separator")+"MovementBouncingTest.java");

	}
	public void Movement() {
		logger.info("Movement");
		/*
		 * src/creatures/movement/IMovement.java
		 * src/plug/creatures/MovementPluginFactory.java
		 * src/plug/creatures/PluginMenuItemBuilderMovement.java
		 */
		
		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"movement"+System.getProperty("file.separator")+"IMovement.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"plug"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"MovementPluginFactory.java");
		fileToCpy.add("src"+System.getProperty("file.separator")+"plug"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"PluginMenuItemBuilderMovement.java");

	}
	public void Dizaine() {
		logger.info("");
	}
	public void Centaine() {
		logger.info("");
	}
	public void Circular() {
		logger.info("Circular");
		/*
		 * src/worlds/WorldCircular.java
		 * test/worlds/WorldCircularTest.java
		 */

		fileToCpy.add("src"+System.getProperty("file.separator")+"worlds"+System.getProperty("file.separator")+"WorldCircular.java");
		fileToCpy.add("test"+System.getProperty("file.separator")+"worlds"+System.getProperty("file.separator")+"WorldCircularTest.java");

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
		logger.info("Closed");
		/*
		 * src/worlds/WorldClosed.java
		 * test/worlds/WorldClosedTest.java
		 */

		fileToCpy.add("src"+System.getProperty("file.separator")+"worlds"+System.getProperty("file.separator")+"WorldClosed.java");
		fileToCpy.add("test"+System.getProperty("file.separator")+"worlds"+System.getProperty("file.separator")+"WorldClosedTest.java");

	}
	public void Unique() {
		logger.info("");
	}
	public void Flock() {
		logger.info("Flock");
		/*
		 * src/creatures/movement/MovementFlock.java
		 * test/creatures/movement/MovementFlockTest.java
		 */

		fileToCpy.add("src"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"movement"+System.getProperty("file.separator")+"MovementFlock.java");
		fileToCpy.add("test"+System.getProperty("file.separator")+"creatures"+System.getProperty("file.separator")+"movement"+System.getProperty("file.separator")+"MovementFlockTest.java");

	}
	
}

package generator.simulator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import utils.FileTool;
import utils.MavenCompiler;

public class GenerateProjectSimulator {
		
	protected String pathProject; // Projet 'temoin'
	
	protected String pathProjectToBuild; // chemin ou l'on va mettre le nouveau projet
	
	protected List<String> config; // String contenant les configurations de création du projet
	
	protected List<String> dirToCpy; // on ajoute les fichiers besoins
	
	protected List<String> fileToCpy; // on ajoute les dossier besoins

	
	private static Logger logger = Logger.getLogger("generator.simulator.GeneratorProjectSimulator");

	
	public GenerateProjectSimulator(String pathProject, String pathProjectToBuild, List<String> configuration) {
		this.pathProject = pathProject;
		this.pathProjectToBuild = pathProjectToBuild;
		this.config = configuration;
		this.dirToCpy = new ArrayList<String>();
		this.fileToCpy = new ArrayList<String>();
	}

	public void generate() {

		logger.info("Copie des fichiers :");
		// appel des config pour ajouter les parametres demand�s
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
			for (String pathDir : dirToCpy) {
				FileTool.copyFilesRecursively(
						new File(pathProject
								+ System.getProperty("file.separator")
								+ pathDir),
						new File(pathProject
								+ System.getProperty("file.separator")),
						new File(pathProjectToBuild
								+ System.getProperty("file.separator")));
			}

			for (String pathFile : fileToCpy) {
				FileTool.createDirectory(pathProjectToBuild
						+ System.getProperty("file.separator")
						+ pathFile.substring(0, pathFile.lastIndexOf('\\')));
				FileTool.copy(
						new File(pathProject
								+ System.getProperty("file.separator")
								+ pathFile), new File(pathProjectToBuild
								+ System.getProperty("file.separator")
								+ pathFile));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		// maven compiler
		
		MavenCompiler mc = new MavenCompiler();
		mc.compile(new File(pathProjectToBuild), new File(pathProjectToBuild+System.getProperty("file.separator")+"target"));
		
		System.out.println("success : "+mc.isLastCompilationSucessful());
		System.out.println(mc.getLastCompilationMessage());
		
		

	}

	public void Visu() {
		logger.info("Visu");

		/*
		 * src/visual/* src/creatures/visual/*
		 */

		dirToCpy.add("src" + System.getProperty("file.separator") + "visual");
		dirToCpy.add("src" + System.getProperty("file.separator") + "creatures"
				+ System.getProperty("file.separator") + "visual");

	}

	public void Normal() {
		logger.info("Normal");
		String fileContent = FileTool.loadFileIntoString(
				pathProject + System.getProperty("file.separator") + "src"
						+ System.getProperty("file.separator") + "main"
						+ System.getProperty("file.separator") + "Const.java",
				"UTF-8");
		fileContent = fileContent.replace("SIMULATOR_SPEED = 10;",
				"SIMULATOR_SPEED = 100;");
		try {
			FileTool.createDirectory(pathProjectToBuild
					+ System.getProperty("file.separator") + "src"
					+ System.getProperty("file.separator") + "main");
			FileTool.saveFile(
					pathProjectToBuild + System.getProperty("file.separator")
							+ "src" + System.getProperty("file.separator")
							+ "main" + System.getProperty("file.separator")
							+ "Const.java", fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Rapide() {
		String fileContent = FileTool.loadFileIntoString(
				pathProject + System.getProperty("file.separator") + "src"
						+ System.getProperty("file.separator") + "main"
						+ System.getProperty("file.separator") + "Const.java",
				"UTF-8");
		fileContent = fileContent.replace("SIMULATOR_SPEED = 10;",
				"SIMULATOR_SPEED = 300;");
		try {
			FileTool.createDirectory(pathProjectToBuild
					+ System.getProperty("file.separator") + "src"
					+ System.getProperty("file.separator") + "main");
			FileTool.saveFile(
					pathProjectToBuild + System.getProperty("file.separator")
							+ "src" + System.getProperty("file.separator")
							+ "main" + System.getProperty("file.separator")
							+ "Const.java", fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("");
	}

	public void Lent() {
		String fileContent = FileTool.loadFileIntoString(
				pathProject + System.getProperty("file.separator") + "src"
						+ System.getProperty("file.separator") + "main"
						+ System.getProperty("file.separator") + "Const.java",
				"UTF-8");
		fileContent = fileContent.replace("SIMULATOR_SPEED = 10;",
				"SIMULATOR_SPEED = 5;");
		try {
			FileTool.createDirectory(pathProjectToBuild
					+ System.getProperty("file.separator") + "src"
					+ System.getProperty("file.separator") + "main");
			FileTool.saveFile(
					pathProjectToBuild + System.getProperty("file.separator")
							+ "src" + System.getProperty("file.separator")
							+ "main" + System.getProperty("file.separator")
							+ "Const.java", fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		 * src/simulator/* src/commons/* src/plug/IPlugin.java
		 * src/plug/PluginLoader.java
		 */

		dirToCpy.add("src" + System.getProperty("file.separator") + "simulator");
		dirToCpy.add("src" + System.getProperty("file.separator") + "commons");

		fileToCpy.add("src" + System.getProperty("file.separator") + "plug"
				+ System.getProperty("file.separator") + "IPlugin.java");
		fileToCpy.add("src" + System.getProperty("file.separator") + "plug"
				+ System.getProperty("file.separator") + "PluginLoader.java");

	}

	public void SimuTechno() {
		logger.info("SimuTechno - main");
		/*
		 * src/main/Launcher.java
		 */

		fileToCpy.add("src" + System.getProperty("file.separator") + "main"
				+ System.getProperty("file.separator") + "Launcher.java");
		fileToCpy.add(System.getProperty("file.separator") + "pom.xml");
		
		dirToCpy.add("myplugins");

	}

	public void Nombre() {
		logger.info("");
	}

	public void Toric() {
		logger.info("Toric");
		/*
		 * src/worlds/WorldToric.java test/worlds/WorldToricTest.java
		 */

		fileToCpy.add("src" + System.getProperty("file.separator") + "worlds"
				+ System.getProperty("file.separator") + "WorldToric.java");
		fileToCpy.add("test" + System.getProperty("file.separator") + "worlds"
				+ System.getProperty("file.separator") + "WorldToricTest.java");

	}

	public void Creature() {
		logger.info("");
		/*
		 * src/plug/creatures/IPluginFactory.java
		 * src/plug/creatures/CreatureFactory.java
		 * src/creatures/AbstractCreature.java
		 * src/creatures/CreatureBuilder.java src/creatures/ICreature.java
		 * src/creatures/IEnvironment.java src/creatures/StandardCreature.java
		 * src/creatures/IColorStrategy.java src/creatures/visual/ColorCube.java
		 */

		fileToCpy.add("src" + System.getProperty("file.separator") + "plug"
				+ System.getProperty("file.separator") + "creatures"
				+ System.getProperty("file.separator") + "IPluginFactory.java");
		fileToCpy
				.add("src" + System.getProperty("file.separator") + "plug"
						+ System.getProperty("file.separator") + "creatures"
						+ System.getProperty("file.separator")
						+ "CreatureFactory.java");
		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "AbstractCreature.java");
		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "CreatureBuilder.java");
		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "ICreature.java");
		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "IEnvironment.java");
		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "StandardCreature.java");
		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "IColorStrategy.java");
		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator") + "visual"
				+ System.getProperty("file.separator") + "ColorCube.java");

	}

	public void Milliers() {
		String fileContent = FileTool.loadFileIntoString(
				pathProject + System.getProperty("file.separator") + "src"
						+ System.getProperty("file.separator") + "main"
						+ System.getProperty("file.separator") + "Const.java",
				"UTF-8");
		fileContent = fileContent.replace("CREATURE_COUNT = 10;",
				"CREATURE_COUNT = 1000;");
		try {
			FileTool.createDirectory(pathProjectToBuild
					+ System.getProperty("file.separator") + "src"
					+ System.getProperty("file.separator") + "main");
			FileTool.saveFile(
					pathProjectToBuild + System.getProperty("file.separator")
							+ "src" + System.getProperty("file.separator")
							+ "main" + System.getProperty("file.separator")
							+ "Const.java", fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("");
	}

	public void Random() {
		logger.info("Random");
		/*
		 * src/creatures/movement/MovementRandom.java
		 * test/creatures/movement/MovementRandomTest.java
		 */

		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "movement" + System.getProperty("file.separator")
				+ "MovementRandom.java");
		fileToCpy.add("test" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "movement" + System.getProperty("file.separator")
				+ "MovementRandomTest.java");

	}

	public void Environnement() {
		logger.info("Environnement");
		/*
		 * src/worlds/IWorld.java src/plug/creatures/WorldPluginFactory.java
		 * src/plug/creatures/PluginMenuItemBuilderWorld.java
		 */

		fileToCpy.add("src" + System.getProperty("file.separator") + "worlds"
				+ System.getProperty("file.separator") + "IWorld.java");
		fileToCpy.add("src" + System.getProperty("file.separator") + "plug"
				+ System.getProperty("file.separator") + "creatures"
				+ System.getProperty("file.separator")
				+ "WorldPluginFactory.java");
		fileToCpy.add("src" + System.getProperty("file.separator") + "plug"
				+ System.getProperty("file.separator") + "creatures"
				+ System.getProperty("file.separator")
				+ "PluginMenuItemBuilderWorld.java");

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

		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "movement" + System.getProperty("file.separator")
				+ "MovementBouncing.java");
		fileToCpy.add("test" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "movement" + System.getProperty("file.separator")
				+ "MovementBouncingTest.java");

	}

	public void Movement() {
		logger.info("Movement");
		/*
		 * src/creatures/movement/IMovement.java
		 * src/plug/creatures/MovementPluginFactory.java
		 * src/plug/creatures/PluginMenuItemBuilderMovement.java
		 */

		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "movement" + System.getProperty("file.separator")
				+ "IMovement.java");
		fileToCpy.add("src" + System.getProperty("file.separator") + "plug"
				+ System.getProperty("file.separator") + "creatures"
				+ System.getProperty("file.separator")
				+ "MovementPluginFactory.java");
		fileToCpy.add("src" + System.getProperty("file.separator") + "plug"
				+ System.getProperty("file.separator") + "creatures"
				+ System.getProperty("file.separator")
				+ "PluginMenuItemBuilderMovement.java");

	}

	public void Dizaine() {
		String fileContent = FileTool.loadFileIntoString(
				pathProject + System.getProperty("file.separator") + "src"
						+ System.getProperty("file.separator") + "main"
						+ System.getProperty("file.separator") + "Const.java",
				"UTF-8");
		fileContent = fileContent.replace("CREATURE_COUNT = 10;",
				"CREATURE_COUNT = 10;");
		try {
			FileTool.createDirectory(pathProjectToBuild
					+ System.getProperty("file.separator") + "src"
					+ System.getProperty("file.separator") + "main");
			FileTool.saveFile(
					pathProjectToBuild + System.getProperty("file.separator")
							+ "src" + System.getProperty("file.separator")
							+ "main" + System.getProperty("file.separator")
							+ "Const.java", fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("");
	}

	public void Centaine() {
		String fileContent = FileTool.loadFileIntoString(
				pathProject + System.getProperty("file.separator") + "src"
						+ System.getProperty("file.separator") + "main"
						+ System.getProperty("file.separator") + "Const.java",
				"UTF-8");
		fileContent = fileContent.replace("CREATURE_COUNT = 10;",
				"CREATURE_COUNT = 100;");
		try {
			FileTool.createDirectory(pathProjectToBuild
					+ System.getProperty("file.separator") + "src"
					+ System.getProperty("file.separator") + "main");
			FileTool.saveFile(
					pathProjectToBuild + System.getProperty("file.separator")
							+ "src" + System.getProperty("file.separator")
							+ "main" + System.getProperty("file.separator")
							+ "Const.java", fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("");
	}

	public void Circular() {
		logger.info("Circular");
		/*
		 * src/worlds/WorldCircular.java test/worlds/WorldCircularTest.java
		 */

		fileToCpy.add("src" + System.getProperty("file.separator") + "worlds"
				+ System.getProperty("file.separator") + "WorldCircular.java");
		fileToCpy.add("test" + System.getProperty("file.separator") + "worlds"
				+ System.getProperty("file.separator")
				+ "WorldCircularTest.java");

	}

	public void Couleur() {
		logger.info("");
	}

	public void Cube() {
		logger.info("");
	}

	public void Closed() {
		logger.info("Closed");
		/*
		 * src/worlds/WorldClosed.java test/worlds/WorldClosedTest.java
		 */

		fileToCpy.add("src" + System.getProperty("file.separator") + "worlds"
				+ System.getProperty("file.separator") + "WorldClosed.java");
		fileToCpy
				.add("test" + System.getProperty("file.separator") + "worlds"
						+ System.getProperty("file.separator")
						+ "WorldClosedTest.java");

	}

	public void Flock() {
		logger.info("Flock");
		/*
		 * src/creatures/movement/MovementFlock.java
		 * test/creatures/movement/MovementFlockTest.java
		 */

		fileToCpy.add("src" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "movement" + System.getProperty("file.separator")
				+ "MovementFlock.java");
		fileToCpy.add("test" + System.getProperty("file.separator")
				+ "creatures" + System.getProperty("file.separator")
				+ "movement" + System.getProperty("file.separator")
				+ "MovementFlockTest.java");

	}

}

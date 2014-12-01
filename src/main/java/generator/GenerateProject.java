package generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;

public abstract class GenerateProject {
	
	 private static Logger logger = Logger.getLogger("generator.GeneratorProject");
	
	protected String pathProject; // Projet 'temoin'
	
	protected String pathProjectToBuild; // chemin ou l'on va mettre le nouveau projet
	
	protected List<String> config; // String contenant les configurations de création du projet
	
	
	public GenerateProject(String pathProject, String pathProjectToBuild, List<String> configuration) {
		this.pathProject = pathProject;
		this.pathProjectToBuild = pathProjectToBuild;
		this.config = configuration;

	}
	
	public abstract void generate();
	

	public void copyFile(String pathFile) {
		File source = new File(pathProject+System.getProperty("file.separator")+pathFile);
		File dest = new File(pathProjectToBuild+System.getProperty("file.separator")+pathFile);
		
		logger.info(" - File : "+pathProject+System.getProperty("file.separator")+pathFile+"\n          To : "+pathProjectToBuild+System.getProperty("file.separator")+pathFile);

		try {
			Files.copy(source.toPath(), dest.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void copyFolder(String folderName, Boolean copySubFolder) {
		File folderToRead = new File(pathProject+System.getProperty("file.separator")+folderName);
		
		createFolder(folderName);
		
		for(File file : folderToRead.listFiles()){
			if(file.isDirectory() && copySubFolder){
				copyFolder(folderName+System.getProperty("file.separator")+file.getName(), copySubFolder);
			}
			else {
				copyFile(folderName+System.getProperty("file.separator")+file.getName());	
			}
		}
	}
	
	public void createFolder(String folderName) {
		
		logger.info("Folder : "+pathProjectToBuild+System.getProperty("file.separator")+folderName);

		File dir = new File(pathProjectToBuild+System.getProperty("file.separator")+folderName);
		dir.mkdirs();
	}
	
}

package feature;

import java.util.Scanner;

import fr.unice.deptinfo.familiar_interpreter.FMEngineException;
import fr.unice.deptinfo.familiar_interpreter.impl.FamiliarInterpreter;
import fr.unice.polytech.modalis.familiar.interpreter.VariableNotExistingException;
import fr.unice.polytech.modalis.familiar.parser.VariableAmbigousConflictException;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class Features {
	
	
	private String fmName;
	
	private String FM;
	
	private String configName;
	
	private FamiliarInterpreter fi;
	
	
	public Features() 
	{
		this.configName = "config1";
		this.fmName = "fmTechno";
		this.FM = this.fmName+" = FM(SimuTechno: [Creature] [Visu] Moteur; "
								+ "Creature: Movement Environnement Couleur Nombre; "
								+ "Movement: (Random|Flock|Bouncing)+; "
								+ "Couleur: (Cube|Groupe|Unique); "
								+ "Environnement: (Toric|Circular|Closed); "
								+ "Nombre: (NAleatoire|Fixe); "
								+ "NAleatoire: (Dizaine|Centaine|Milliers); "
								+ "Moteur: VitesseSimu Action; "
								+ "Action: Sequentiel; "
								+ "VitesseSimu: (Lent|Rapide|Normal);)";
		this.fi = FamiliarInterpreter.getInstance();
	}
	
	
	public void askConfig() {
		try {
			fi.eval(FM);
			FeatureModelVariable fmv = fi.getFMVariable(fmName);
	    	
	    	System.out.println("Instancied FM : "+fmv.getSyntacticalRepresentation());
	    	
	    	fi.eval(configName+" = configuration "+fmName);
	    	
	        Scanner scan = new Scanner(System.in);
	        String s = "";
	        String selectCmd = "select ";
	        
	        do {
	        	System.out.println("Enter the name of features you wish to select, or type exit to exit.");
	        	s = scan.nextLine();
	        	if (!s.equals("exit")) {
		        	fi.eval(selectCmd+s+" in "+configName);
		        	System.out.println("Selected features :"+fi.getSelectedFeature(configName));
		        	System.out.println("Deselected features :"+fi.getDeselectedFeature(configName));
		        	System.out.println("Unselected features :"+fi.getUnselectedFeature(configName));
		        	System.out.println("The configuration is complete : "+fi.getConfigurationVariable(configName).isComplete());
	        	}
	        } while (!s.equals("exit"));
		} catch (FMEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VariableNotExistingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VariableAmbigousConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
	

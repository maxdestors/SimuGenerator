package feature;

import java.util.ArrayList;
import java.util.Collection;

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


	public Features(String name) 
	{
		this.fmName = name;
		this.FM = this.fmName+" = FM(SimuTechno: Creature Visu Moteur; "
								+ "Creature: Movement Environnement Couleur Nombre; "
								+ "Movement: (Random|Flock|Bouncing)+; "
								+ "Couleur: Cube; "
								+ "Environnement: (Toric|Circular|Closed); "
								+ "Nombre: NAleatoire; "
								+ "NAleatoire: (Dizaine|Centaine|Milliers); "
								+ "Moteur: VitesseSimu Action; "
								+ "Action: Sequentiel; "
								+ "VitesseSimu: (Lent|Rapide|Normal);)";
		this.configName = "SimuGenerator";

		this.fi = FamiliarInterpreter.getInstance();

	}

	public Collection<String> askConfig(ArrayList<String> listConfig){
		try {
			fi.eval(FM);
			try {
				@SuppressWarnings("unused")
				FeatureModelVariable fmv = fi.getFMVariable(fmName);
			} catch (VariableNotExistingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (VariableAmbigousConflictException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			fi.eval(configName+" = configuration "+fmName);


			String s = "";
			String selectCmd = "select ";
			String deselectCmd = "deselect ";
			ArrayList<String> unselectList = new ArrayList<String>();
			
			//Va permettre d'iterer dans le ArrayList
			int index = 0;
			do {
				s = listConfig.get(index);
				System.out.println(s);
				if (!s.equals("exit")) {
					fi.eval(selectCmd+s+" in "+configName);
					
					index++;
				}
			} while (!s.equals("exit"));
			
			unselectList.addAll(fi.getUnselectedFeature(configName));
			for(String p:unselectList){
				fi.eval(deselectCmd+p+" in "+configName);
			}
			
			System.out.println("Selected features :"+fi.getSelectedFeature(configName));
			System.out.println("Deselected features :"+fi.getDeselectedFeature(configName));
			System.out.println("Unselected features :"+fi.getUnselectedFeature(configName));
			System.out.println("The configuration is complete : "+fi.getConfigurationVariable(configName).isComplete());

			fi.eval(configName+" = configuration "+fmName);


			/*if (!s.equals("exit")) {
				fi.eval(selectCmd+s+" in "+configName);
			}*/
			
			if(fi.getConfigurationVariable(configName).isComplete()) {
				return fi.getSelectedFeature(configName);
			}
			
			

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

		return null;

	}

}
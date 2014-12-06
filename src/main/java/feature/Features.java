package feature;

import java.util.ArrayList;
import java.util.Collection;
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


	public Features(String name) 
	{
		this.fmName = name;
		this.FM = this.fmName+" = FM(A: E F; E: (B|C|D); F: (X|Y)+;)";
		this.configName = "Simu-Generator";

		this.fi = FamiliarInterpreter.getInstance();

	}


	public void askConfig(String s) {
		try {
			fi.eval(FM);
			FeatureModelVariable fmv = fi.getFMVariable(fmName);

			System.out.println("Instancied FM : "+fmv.getSyntacticalRepresentation());

			fi.eval(configName+" = configuration "+fmName);

			String selectCmd = "select ";

			if (!s.equals("exit")) {
				fi.eval(selectCmd+s+" in "+configName);
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
	}

	public Collection<String> getConfig() {
		try {
			if(fi.getConfigurationVariable(configName).isComplete()){
				return fi.getSelectedFeature(configName);
			}
		} catch (VariableNotExistingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (VariableAmbigousConflictException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (FMEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}





}


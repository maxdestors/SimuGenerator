package feature;

import java.util.ArrayList;

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
		this.configName = "SimuGenerator";

		this.fi = FamiliarInterpreter.getInstance();

	}

	public void askConfig(ArrayList<String> listConfig){
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


			fi.eval(configName+" = configuration "+fmName);


			if (!s.equals("exit")) {
				fi.eval(selectCmd+s+" in "+configName);
			}

		} catch (FMEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
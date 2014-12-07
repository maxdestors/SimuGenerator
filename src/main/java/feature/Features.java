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
		this.configName = "SimuGenerator";

		this.fi = FamiliarInterpreter.getInstance();

	}

	/*
 public void askConfig(ArrayList<String> s) {
  try {

	  fi.eval(FM);
   FeatureModelVariable fmv = fi.getFMVariable(fmName);

   System.out.println("Instancied FM : "+fmv.getSyntacticalRepresentation());

   System.out.println("select "+s+" in "+configName);
   fi.eval(configName+" = configuration "+fmName);

   String selectCmd = "select ";

   System.out.println("##################");
   System.out.println(selectCmd+s+" in "+configName);
   System.out.println("##################");

   fi.eval(selectCmd+s+" in "+configName);

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
	 */
	public void askConfig(ArrayList<String> listConfig){
		try {
			fi.eval(FM);
			try {
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
package fr.unice.deptinfo.simu_generator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import feature.Features;

public class App 
{
    public static void ChargerConfig(String name, String path)
    {
    	String chaine="";
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+"\n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		//System.out.println(chaine);
    	
    	Features fs = new Features(name, chaine);
    	fs.askConfig();
    }
}
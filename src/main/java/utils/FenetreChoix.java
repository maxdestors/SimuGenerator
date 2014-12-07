package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.unice.deptinfo.simu_generator.App;

public final class FenetreChoix implements ActionListener {

	private String choix;
	private static volatile FenetreChoix instance = null;
	private JFrame fenetre = new JFrame();
	private JPanel container = new JPanel();
	private JComboBox<String> combo = new JComboBox<String>();
	private Map<String,String> configsPresentes = new HashMap<String,String>();
	
	public String getChoix(){
		return this.configsPresentes.get(choix);
	}
	
	
	private FenetreChoix(){
		super();
	}
	
	public final static FenetreChoix getInstance() {
		if(FenetreChoix.instance == null){
			synchronized (Singleton.class) {
				if(FenetreChoix.instance == null){
					FenetreChoix.instance = new FenetreChoix();
				}
				
			}
		}
		return FenetreChoix.instance;
	}
	
	public void choixConf(){
		File f = null;
		File[] configs;
		f = new File("./config");
		configs = f.listFiles();
	
	
		JLabel label = new JLabel("Configuration");
		JButton buttonValider = new JButton("Valider");
		JButton buttonEditer = new JButton("Editer");
		
		buttonEditer.addActionListener(this);
		buttonValider.addActionListener(this);
		

		fenetre.add(container);
		fenetre.add(combo);
		fenetre.add(label); 

		fenetre.setTitle("Simu-generator");
		fenetre.setSize(250, 150);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());

		combo.setPreferredSize(new Dimension(100, 20));

		JPanel top = new JPanel();
		top.add(label);
		top.add(combo);

		JPanel down = new JPanel();
		down.add(buttonValider, BorderLayout.EAST);
		down.add(buttonEditer, BorderLayout.WEST);
		
		container.add(top, BorderLayout.NORTH);
		container.add(down, BorderLayout.SOUTH);
		
		fenetre.setContentPane(container);
		fenetre.setVisible(true);    

		//Ajout des propositions dans la comboBox
		for(File p:configs){
			configsPresentes.put(p.getName(), p.getAbsolutePath());
			combo.addItem(p.getName());
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Valider")) {
			this.choix = combo.getSelectedItem().toString();
			this.fenetre.dispose();
			//App.ChargerConfig(choix,configsPresentes.get(choix));
		}
		else if(e.getActionCommand().equals("Editer")) {
			System.out.println(configsPresentes.get(choix));
		}
	}
}
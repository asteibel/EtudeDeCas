package src.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FenetreMenu extends JFrame{
	
	/**
	 * Layout du menu
	 * Centre : texte
	 * Haut : bouton WE
	 * Bas : bouton lancer nouveau tableau
	 */
	BorderLayout bl;
	/**
	 * Tableau affiché en ce moment
	 */
	TableauEvenement tabEv;
	
	public FenetreMenu(){
		super();
		
		setTitle("Menu");
		setPreferredSize(new Dimension(200,150));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bl = new BorderLayout();
		
		this.add(new JLabel("Calcul de la complémentarité"),bl.CENTER);
		
		boutonDemarrer();
		boutonWE();
		
		pack();
	}
	
	public void boutonDemarrer(){
		JButton bDemarrer = new JButton("Demarrer");
		bDemarrer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//Main main = new Main();
				fermerTabEv();
				TableauEvenement test = new TableauEvenement();
				tabEv = test.lancer();
				lancerFenetreCalcul(tabEv);
				
			}
		});
		
		this.add(bDemarrer,bl.SOUTH);
		this.setVisible(false);
		
	}
	
	public void boutonWE(){
		JButton bWE = new JButton("Lancer exemple WE");
		bWE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fermerTabEv();
				TableauEvenement test = new TableauEvenement();
				tabEv = test.lancerWE();
				lancerFenetreCalcul(tabEv);
			}
		});
		this.add(bWE,bl.NORTH);
		this.setVisible(false);
	}
	
	/**
	 * Ferme la fenêtre du tableau des évènements
	 */
	public void fermerTabEv(){
		if (tabEv!=null){
			tabEv.fermerTabEv();
		}
	}

	/**
	 * Ouvre la fenêtre de calcul
	 * @param tabEv Tableau sur lequel faire les calculs
	 */
	public void lancerFenetreCalcul(TableauEvenement tabEv){
		FenetreCalcul fCalcul = new FenetreCalcul(tabEv);
		fCalcul.setVisible(true);
	}
	
}

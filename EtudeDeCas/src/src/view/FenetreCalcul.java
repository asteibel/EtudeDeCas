package src.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import src.ec.CalculEntropie;

/**
 * Fenetre pour lancer le calcul
 *
 */
public class FenetreCalcul extends JFrame{
	
	GestionTableaux gestion;
	
	public FenetreCalcul(GestionTableaux gestion){
		super();
		
		this.gestion=gestion;
		
		setTitle("Fenetre de calcul");
		setPreferredSize(new Dimension(200,150));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		boutonCalcul();
		
		pack();
	}
	
	public void boutonCalcul(){
		JButton bCalcul = new JButton("Lancer le calcul");
		bCalcul.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Lancement du calcul!!");
				CalculEntropie calcul = new CalculEntropie(gestion.getTabUI().getTab());
				
			}
		});
		this.add(bCalcul);
	}
	
	

}

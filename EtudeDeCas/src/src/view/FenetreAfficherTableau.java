package src.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FenetreAfficherTableau extends JFrame{

	TableauEvenement tabEv;
	
	public FenetreAfficherTableau(TableauEvenement tabEv){
		super();
		this.tabEv=tabEv;
		setPreferredSize(new Dimension(200,150));
		boutonAfficher();
		pack();
		setVisible(true);
		
	}
	
	public void boutonAfficher(){
		JButton bouton = new JButton("Afficher le tableau");
		bouton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tabEv.afficherTableau();
				setVisible(false);
			}
		});
		this.add(bouton);
	}
}

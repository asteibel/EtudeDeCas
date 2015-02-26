package src.ec;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import src.view.TableauUI;

/**
 * Listener pour le remplissage du tableau 
 *
 */
public class TableauListener implements MouseListener{

	int i;
	int j;
	TableauUI tabUI;
	
	public TableauListener(int i, int j, TableauUI tableauUI){
		this.i=i;
		this.j=j;
		this.tabUI=tableauUI;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		tabUI.getTab().getPresence()[i-1][j-1]=!tabUI.getTab().getPresence()[i-1][j-1];
		tabUI.paint2();
		System.out.println(i+" "+j+" "+tabUI.getTab().getPresence()[i-1][j-1]);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

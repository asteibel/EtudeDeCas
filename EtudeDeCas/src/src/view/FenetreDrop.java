package src.view;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import src.ec.ReadCSV;

public class FenetreDrop extends JFrame{
	
	String path;;
	
	public FenetreDrop(final FenetreMenu menu){
		super();
		
		this.setSize(250, 150);
		JLabel myLabel = new JLabel("Déposez le fichier ici!", SwingConstants.CENTER);
	    new DropTarget(myLabel, new DropTargetListener() {
			
			@Override
			public void dropActionChanged(DropTargetDragEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void drop(DropTargetDropEvent event) {
				// TODO Auto-generated method stub
				// Accept copy drops
		        event.acceptDrop(DnDConstants.ACTION_COPY);
		        // Get the transfer which can provide the dropped item data
		        Transferable transferable = event.getTransferable();
		        // Get the data formats of the dropped item
		        DataFlavor[] flavors = transferable.getTransferDataFlavors();
		        
		        for (DataFlavor flavor : flavors) {

		            try {
		                // If the drop items are files
		                if (flavor.isFlavorJavaFileListType()) {

		                    // Get all of the dropped files
		                    List<File> files = (List<File>) transferable.getTransferData(flavor);
		                    // Loop them through
		                    for (File file : files) {
		                    	path=file.getPath();
		                    }
		                }

		            } catch (Exception e) {

		                // Print out the error stack
		                e.printStackTrace();

		            }
		            ReadCSV csv = new ReadCSV(path);
		            
		            menu.fermerTabEv();
		            TableauEvenement tabEv = new TableauEvenement();
		            tabEv=tabEv.lancerCSV(csv.run());
		            menu.lancerFenetreCalcul(tabEv);
		            FenetreAfficherTableau afficherTab = new FenetreAfficherTableau(tabEv);
		            
			}
			
			}
			@Override
			public void dragOver(DropTargetDragEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dragExit(DropTargetEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dragEnter(DropTargetDragEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	    
	    this.getContentPane().add(BorderLayout.CENTER, myLabel);
	    this.setVisible(true);
	    
	}

}

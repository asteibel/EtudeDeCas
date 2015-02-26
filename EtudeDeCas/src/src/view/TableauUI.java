package src.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.ec.Tableau;
import src.ec.TableauListener;

public class TableauUI extends JFrame{

	Tableau tab;
	/**
	 * individus
	 */
	int n;
	/**
	 * evenements
	 */
	int m;
	
	JPanel grille;
	
	public TableauUI(){
		super();
		setTitle("Tableau des Evenements");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tab = new Tableau();
		
		pack();
	
	}
	
	public void chargerCSV(int[][] tab){
		n=tab.length;
		m=tab[0].length;
		this.tab.chargerCSV(tab);
		
	}

	public void paint(){
		/**
		 * Creation du GridLayout + creation d'un tab de jPanel
		 */
		grille = new JPanel(new GridLayout(n+1,m+1));
		JPanel[][] tabPanel = new JPanel[n+1][m+1];
		for(int i =0;i<n+1;i++)
			for(int j=0;j<m+1;j++){
				tabPanel[i][j]=new JPanel();
				tabPanel[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				tabPanel[i][j].addMouseListener(new TableauListener(i, j, this));
			}
		
		/**
		 * Ajout des noms des évènements
		 */
		for(int i =1;i<m+1;i++){
			JPanel nvPanel = new JPanel();
			JLabel nvLab = new JLabel(tab.getListeEvenements()[i-1]);
			nvPanel.add(nvLab);
			tabPanel[0][i].add(nvPanel);
		}
		
		/**
		 * Ajout des noms des individus
		 */
		for(int i =1;i<n+1;i++){
			JPanel nvPanel = new JPanel();
			JLabel nvLab = new JLabel(tab.getListeIndividus()[i-1]);
			nvPanel.add(nvLab);
			tabPanel[i][0].add(nvPanel);
		}
		
		/**
		 * Ajout de la présence
		 */
		for(int i =1;i<n+1;i++)
			for(int j=1;j<m+1;j++){
				if (tab.getPresence()[i-1][j-1])
					tabPanel[i][j].setBackground(Color.WHITE);
				else
					tabPanel[i][j].setBackground(Color.DARK_GRAY);
			}
		
		/**
		 * Ajout des JPanel à la grille
		 */
		for(int i =0;i<n+1;i++){
			for(int j=0;j<m+1;j++){
				grille.add(tabPanel[i][j]);
			}
			
		}
		
		this.add(grille);
		pack();
		
	}
	
	public void paint2(){
		this.remove(grille);
		paint();
		this.pack();
		this.repaint();
	}
	
	public void chargerWE(){
		n=18;
		m=14;
		tab.chargerWE();
	}
	
	public Tableau getTab() {
		return tab;
	}

	public void setTab(Tableau tab) {
		this.tab = tab;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public JPanel getGrille() {
		return grille;
	}

	public void setGrille(JPanel grille) {
		this.grille = grille;
	}
	
	
}

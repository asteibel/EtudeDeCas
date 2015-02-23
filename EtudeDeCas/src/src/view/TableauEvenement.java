package src.view;

import java.util.Scanner;

public class TableauEvenement {

	static TableauUI tabUI = new TableauUI();


	static int n;
	static String[] listeIndividus;
	static int m;
	static String[] listeEvenements;
	

	public TableauEvenement lancer() {
		// TODO Auto-ge1erated method stub

		
		initialisation();
		
		
		tabUI.paint();
		tabUI.setVisible(true);
		return this;
		
	}
	
	public TableauEvenement lancerCSV(int[][] tab){
		tabUI.chargerCSV(tab);
		tabUI.paint();
		tabUI.setVisible(true);
		return this;
	}
	
	public TableauEvenement lancerWE(){
		tabUI.chargerWE();
		tabUI.paint();
		tabUI.setVisible(true);
		return this;
	}
	
	public static void initialisation(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Combien y-a-t'il d'individus?");
		n=sc.nextInt();
		listeIndividus = new String[n];
		for(int i =0;i<listeIndividus.length;i++){
			System.out.println("Entrez l'individu n°"+(i+1));
			listeIndividus[i]=sc.next();
		}
		tabUI.setN(n);
		
		System.out.println("Combien y-a-t'il d'évènements?");
		m=sc.nextInt();
		tabUI.setM(m);
		listeEvenements = new String[m];
		for(int i =0;i<listeEvenements.length;i++){
			System.out.println("Entrez l'évènement n°"+(i+1));
			listeEvenements[i]=sc.next();
		}
		
		tabUI.getTab().initialisation(n, m);
		tabUI.getTab().setListeEvenements(listeEvenements);
		tabUI.getTab().setListeIndividus(listeIndividus);
	}
	
	public void fermerTabEv(){
		tabUI.dispose();
		tabUI = new TableauUI();
	}

	public static TableauUI getTabUI() {
		return tabUI;
	}

	public static void setTabUI(TableauUI tabUI) {
		TableauEvenement.tabUI = tabUI;
	}

	public static int getN() {
		return n;
	}

	public static void setN(int n) {
		TableauEvenement.n = n;
	}

	public static String[] getListeIndividus() {
		return listeIndividus;
	}

	public static void setListeIndividus(String[] listeIndividus) {
		TableauEvenement.listeIndividus = listeIndividus;
	}

	public static int getM() {
		return m;
	}

	public static void setM(int m) {
		TableauEvenement.m = m;
	}

	public static String[] getListeEvenements() {
		return listeEvenements;
	}

	public static void setListeEvenements(String[] listeEvenements) {
		TableauEvenement.listeEvenements = listeEvenements;
	}
	
}

	


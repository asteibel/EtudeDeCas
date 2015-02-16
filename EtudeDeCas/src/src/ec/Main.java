package src.ec;

import java.util.Scanner;

import src.view.TableauUI;

public class Main {

	static TableauUI tabUI = new TableauUI();
	static int n;
	static String[] listeIndividus;
	static int m;
	static String[] listeEvenements;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("Utiliser women events? (o/n)");
		Scanner sc = new Scanner(System.in);
		String rep = sc.next();
		if(rep.equals("o"))
			tabUI.chargerWE();
		else
			initialisation();
		
		
		tabUI.paint();
		tabUI.setVisible(true);
		
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

}

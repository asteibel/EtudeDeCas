package src.ec;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CalculEntropie {

	/**
	 * Tableau sur lequel on travaille
	 */
	Tableau tableau;
	/**
	 * nombre de personnes
	 */
	int n;
	/**
	 * nombre d'évènements
	 */
	int m;
	/**
	 * Pour chaque individu, son nombre de présence aux évènements
	 */
	int[] nombreDePresenceLigne;
	/**
	 * Total de présence de tous les individus 
	 */
	int nombreTotalDePresence;
	
	/**
	 * Pour chaque ev, combien de personne sont venus
	 */
	int[] nombreDePresenceColonne;
	/**
	 * Entropie du système
	 */
	double SSysteme = 0.0;
	
	/**
	 * Tableau de présence en int
	 */
	private int[][] mint;
	
	/**
	 * 
	 * @param tableau Le tableau sur lequel calculer l'entropie
	 */
	public CalculEntropie(Tableau tableau){

		this.tableau = tableau;
		this.m=tableau.getM();
		this.n=tableau.getN();
		mint=new int[n][m];
		
		for(int i =0;i<n;i++)
			for(int j =0;j<m;j++)
				if(tableau.getPresence()[i][j])
					mint[i][j]=1;
				else
					mint[i][j]=0;
		
		System.out.println(afficherMatriceInt(mint));
		
		System.out.println("Calcul du nombre de présence");
		
		nombreDePresenceLigne=new int[n];
		nombreDePresenceColonne =new int[m];
		nombreTotalDePresence=0;
		for(int i =0;i<n;i++){
			int c = 0;
			for(int j=0;j<m;j++)
				if(tableau.getPresence()[i][j])
					c++;
			nombreDePresenceLigne[i]=c;
			
			nombreTotalDePresence+=nombreDePresenceLigne[i];
			
		}
		for(int i =0;i<m;i++){
			int c = 0;
			for(int j=0;j<n;j++)
				if(tableau.getPresence()[j][i])
					c++;
			nombreDePresenceColonne[i]=c;
			
			
		}
		System.out.println(afficherTableauInt(nombreDePresenceColonne));
		System.out.println(afficherTableauInt(nombreDePresenceLigne));
		System.out.println(nombreTotalDePresence +" <= nombre total de présence");
		
		calculEntropieColonneSysteme();
		calculGainInfoMax();
			
			
	}
	
	public void calculEntropieLigneSysteme(){
		double[] tabEntropie = new double[nombreDePresenceLigne.length];
		System.out.println("Calcul entropie personnes");
		for(int i =0;i<tabEntropie.length;i++){
			double p = (double)nombreDePresenceLigne[i];
			double r = ((double)nombreDePresenceLigne[i])/nombreTotalDePresence;
			double logr = Math.log10(r);
			double plogr = p*logr/nombreTotalDePresence;
			double logp = Math.log10(nombreDePresenceLigne.length);
			tabEntropie[i]=-plogr/logp;
			}
		System.out.println("Tableau de l'entropie des individus");
		System.out.println(afficherTableauDouble(tabEntropie));
		
		SSysteme=0.0;
		for(int i =0;i<tabEntropie.length;i++)
			SSysteme+=tabEntropie[i];
		System.out.println("Entropie système "+SSysteme);
	}
	
	public void calculEntropieColonneSysteme(){
		double[] tabEntropie = new double[nombreDePresenceColonne.length];
		System.out.println("Calcul entropie personnes");
		for(int i =0;i<m;i++){
			double p = (double)nombreDePresenceColonne[i];
			double r = ((double)nombreDePresenceColonne[i])/nombreTotalDePresence;
			double logr = Math.log10(r);
			double plogr = p*logr/nombreTotalDePresence;
			double logp = Math.log10(nombreDePresenceColonne.length);
			tabEntropie[i]=-plogr/logp;
			}
		System.out.println("Tableau de l'entropie des évènements");
		System.out.println(afficherTableauDouble(tabEntropie));
		
		SSysteme=0.0;
		for(int i =0;i<tabEntropie.length;i++)
			SSysteme+=tabEntropie[i];
		System.out.println("Entropie système "+SSysteme);
	}
	
	public void calculGainInfoMax(){
		double[][] matGainP = new double[n][m];
		double[][] matGainN = new double[n][m];
		
		for(int i =0;i<n;i++)
			for(int j=0;j<m;j++){
				if (mint[i][j]==0){
					double c =(double) 1/(m-nombreDePresenceLigne[i]);
					matGainN[i][j]=c*Math.log(c);
				}
					
				else{
					double a = (double)mint[i][j];
					double b=Math.log(((double)mint[i][j])/((double)nombreDePresenceLigne[i]));
					matGainP[i][j]=a*b/((double)nombreDePresenceLigne[i]);
					
				}
						}
		System.out.println("Matrice des gains d'informations");
		System.out.println(afficherMatriceDouble(matGainP));
		System.out.println(afficherMatriceDouble(matGainN));
		
		double[] tabGainSommeP = new double[n];
		double[] tabGainSommeN = new double[n];
		for(int i =0;i<n;i++){
			tabGainSommeP[i]=0;
			tabGainSommeN[i]=0;
			for(int j=0;j<m;j++){
				tabGainSommeP[i]+=((double)nombreDePresenceLigne[i])/((double)nombreTotalDePresence)*matGainP[i][j]/Math.log(m);
				tabGainSommeN[i]+=((double)(nombreTotalDePresence-nombreDePresenceLigne[i]))/((double)nombreTotalDePresence)*matGainN[i][j]/Math.log(m);
			}
				
		}
		double[] tabGain =new double[n];
		for(int i =0;i<n;i++)
			tabGain[i]=SSysteme+tabGainSommeP[i]+tabGainSommeN[i];
		System.out.println("Tableau des gain d'information");
		System.out.println(afficherTableauDouble(tabGain));
		double gainMax=0;
		int indice=0;
		for(int i =0;i<n;i++){
			if(tabGain[i]>gainMax){
				gainMax=tabGain[i];
				indice=i+1;
			}
		}
		System.out.println("Le Gain max est de "+gainMax+" pour la personne n°"+indice);
	}
	
	/**
	public void matriceEntropie(){

		// Initialisation de la matrice
		double S=0.0;
		double T=0.0;
		for(int i=1;i<=n;i++){
			if(mbool[i][1]==0)
				S=0.0;
			else{
				double q = prob.get(i)/nb_occ;
				double x= (double)prob.get(i);
				double y = mbool[i][1] * Math.log(mbool[i][1]/x);
				double r = y/x;
				S = q * r ;
		}
			if(prob.get(i)-mbool[i][1]==0)
				T=0.0;
			else{
				double x = nb_occ - prob.get(i);
				double z = x/nb_occ;
				double q = (double)prob.get(i)-mbool[i][1];
				double r = q * Math.log(q/prob.get(i));
				double y = r/prob.get(i);
				T = z * y;
	}
			entp[i][1]= S + T;
		
	}
		// Construction  des autres colonnes de la matrice qui dependent des valeurs contenues dans entp[i-1][j-1]
		
		for(int j=2;j<=p;j++)
			for(int i=1;i<=n;i++){
				double Z=0;
				double W=0;
					if(mbool[i][j]==0)
						W=0;
					else{
						double q = entp[i][j-1]/nb_occ;
						double x= (double) prob.get(i);
						double a = mbool[i][j] * Math.log(mbool[i][j]/x);
						double y = a/x;
						W = q * y;
				}
					if(prob.get(i)-mbool[i][j]==0)
						Z=0;
					else{
						double x = nb_occ - entp[i][j-1];
						double y = x/nb_occ;
						double z = prob.get(i)-mbool[i][j];
						double r = z * Math.log(z/prob.get(i));
						double q = r/prob.get(i);
						Z = y * q;
			}
					entp[i][j]= W + Z;
				
			}
		
	}

	public void entropieListePersonne(){
		for(int i=1;i<=this.n;i++){
			double somme=0.0;
			for(int j=1;j<=this.p;j++)
				somme = somme + this.entp[i][j];
			double l= (-1)*somme/Math.log(this.p);
			double e = l/2;
			this.dictionnaire.put(i, e);
		}			
				
	}
	
	// Affichage de la matrice Ã  varibable booleene dans une fichier
	public void afficherMatriceBooleene(){
		Scanner entree = new Scanner(System.in);
		System.out.println("Donner le repertoire oÃ¹ enregistrer le fichier contenant la matrice Ã  variable booleenne ");
		String destination = entree.next();
		entree.close();
		try{
			PrintWriter fichier = new PrintWriter(new FileWriter(destination));
			fichier.println("******** Matrice Ã  variablebooleenne *************");
			for(int i=1;i<=this.n;i++){
				for(int j=1;j<=this.p;j++)
					fichier.print(mbool[i][j]+"  ");
			fichier.println();
				
			}
			fichier.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	// Affichage de la matrice dans fichier.txt
	public void afficherMatriceEnropie(){
		Scanner entree = new Scanner(System.in);
		System.out.println("Donner le repertoire oÃ¹ enregistrer le fichier contenant la matrice ");
		String destination = entree.next();
		entree.close();
		try{
			PrintWriter fichier = new PrintWriter(new FileWriter(destination));
			fichier.println("****************** Matrice contenant l'entropie de chaque personne pour chaque variable j ****************");
			for(int i=1;i<=this.n;i++){
				for(int j=1;j<=this.p;j++)
					fichier.print(entp[i][j]+"  ");	
				fichier.println();
		}
			fichier.close();
		
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// Creation d'un fichier puis ecriture dans le fichier de la liste des personnes et l'entropie correspondant
	// sur chaque ligne nous aurons la personne p(i) et l'entropie e(i).
	
	public void afficherEntropiePersonne(String destination){
		try{
			PrintWriter fichier = new PrintWriter(new FileWriter(destination));
			fichier.println("*********************************Entropie d'une liste de personne***********************************");
			for(int i=1;i<=this.n;i++)
				fichier.println("p"+i+"   "+dictionnaire.get(i));
			fichier.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}*/
	
	public String afficherMatriceInt(int[][] mat){
		String s = "";
		for(int i =0;i<mat.length;i++){
			for(int j=0;j<mat[0].length;j++)
				s+=mat[i][j]+" ";
			s+="\n";
		}
		return s;
			
	}
	
	public String afficherMatriceDouble(double[][] mat){
		String s = "";
		for(int i =0;i<mat.length;i++){
			for(int j=0;j<mat[0].length;j++)
				s+=mat[i][j]+" ";
			s+="\n";
		}
		return s;
			
	}
	
	public String afficherTableauInt(int[] tab){
		String s="";
		for(int i =0;i<tab.length;i++)
			s+=tab[i]+" ";
		return s;
	}
	public String afficherTableauDouble(double[] tab){
		String s="";
		for(int i =0;i<tab.length;i++)
			s+=tab[i]+" ";
		return s;
	}
	
}

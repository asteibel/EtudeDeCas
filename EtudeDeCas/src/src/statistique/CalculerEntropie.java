package src.statistique;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;


public class CalculerEntropie {
	
	private double nb_occ=0 ;
	public Map<Integer,Double> dictionnaire = new HashMap<>();
	private int n;
	private int p;
	private int [][] mbool;
	private double [][] entp;
	public Map <Integer,Integer> prob = new TreeMap<>();
	
	//Constructeur de la classe CalculerEntropie qui recoit en parametre une matrice à variable booleenne 
	// de n lignes(nombre de personne) et de p colonne(variables ou critere d'evaluation)
	
	public CalculerEntropie(int [][] matrice,int n,int p){
		this.n=n;
		this.p=p;
		entp = new double [n+1][p+1]; 
		mbool=new int [n+1][p+1];
		for(int i=1;i<=n;i++)
			for(int j=1;j<=p;j++)
				mbool[i][j] = matrice[i][j];
		
		/** Construction du dictionnaire associant à chaque personne son occurence(nonmbre de fois où il y a
		   un 1 dans ligne de la personne concernée */
		
		for(int i=1;i<=n;i++){
			int nb=0;
			for(int j=1;j<=p;j++){
				if(matrice[i][j]==1)
					nb=nb+1;
			}
			prob.put(i, nb);
			nb_occ=nb_occ+nb;
		}			
			
	}
	
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
	
	// Affichage de la matrice à varibable booleene dans une fichier
	public void afficherMatriceBooleene(){
		Scanner entree = new Scanner(System.in);
		System.out.println("Donner le repertoire où enregistrer le fichier contenant la matrice à variable booleenne ");
		String destination = entree.next();
		entree.close();
		try{
			PrintWriter fichier = new PrintWriter(new FileWriter(destination));
			fichier.println("******** Matrice à variablebooleenne *************");
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
		System.out.println("Donner le repertoire où enregistrer le fichier contenant la matrice ");
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
		
	}
}


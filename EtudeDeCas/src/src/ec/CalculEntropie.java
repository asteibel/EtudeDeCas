package src.ec;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	 * nombre d'�v�nements
	 */
	int m;
	/**
	 * Pour chaque individu, son nombre de pr�sence aux �v�nements
	 */
	int[] nombreDePresenceLigne;
	/**
	 * Total de pr�sence de tous les individus 
	 */
	int nombreTotalDePresence;
	
	/**
	 * Pour chaque ev, combien de personne sont venus
	 */
	int[] nombreDePresenceColonne;
	/**
	 * Entropie du syst�me
	 */
	double SSysteme = 0.0;
	
	/**
	 * Tableau de pr�sence en int
	 */
	private int[][] mint;
	
	/**
	 * Tableau des indices des personnes s�lectionn�es
	 */
	ArrayList<Integer> indices = new ArrayList<Integer>();
	
	/**
	 * Tableau des compl�mentarit�s
	 */
	double[] tabComp;
	
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
		
		System.out.println("Calcul du nombre de pr�sence");
		
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
		System.out.println(nombreTotalDePresence +" <= nombre total de pr�sence");
		
		//On calcul l'entropie du syst�me
		calculEntropieColonneSysteme();
		//On d�termine la personne avec le plus de gain d'information
		calculGainInfo();
		//On trouve la personne avec le plus de compl�mentarit� avec la personne au gain max
		complementarite();
		
		//A ce niveau, on a deux personnes s�lectionn�es.
		//On fait alors le calcul de compl�mentarit� qui rajoute une personne tant qu'aucune des 
		//conditions d'arr�t n'est v�rifi�e
		for(int i =1;i<n;i++)
			if(!arret()&&!arretCouvertureTotale())
				complementarite();
			else{
				System.out.println("Condition d'arr�t v�rifi�e!");
				break;
			}
				
		//On affiche les personnes s�lectionn�es
		System.out.println("Personnes s�lectionn�es");
		for(int i=0;i<indices.size();i++)
			System.out.println(indices.get(i)+1);
			
			
	}
	/**
	 * Calcul de l'entropie du syst�me en ligne
	 */
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
		System.out.println("Entropie syst�me "+SSysteme);
	}
	/**
	 * Calcul de l'entropie du syst�me en colonne
	 */
	public void calculEntropieColonneSysteme(){
		double[] tabEntropie = new double[nombreDePresenceColonne.length];
		System.out.println("Calcul entropie �v�nements");
		for(int i =0;i<m;i++){
			if(nombreDePresenceColonne[i]==0)
				tabEntropie[i]=0;
			else
			{

				double p = (double)nombreDePresenceColonne[i];
				double r = ((double)nombreDePresenceColonne[i])/nombreTotalDePresence;
				double logr = Math.log10(r);
				double plogr = p*logr/nombreTotalDePresence;
				double logp = Math.log10(nombreDePresenceColonne.length);

				tabEntropie[i]=-plogr/logp;
			}
			}
		System.out.println("Tableau de l'entropie des �v�nements");
		System.out.println(afficherTableauDouble(tabEntropie));
		
		SSysteme=0.0;
		for(int i =0;i<tabEntropie.length;i++)
			SSysteme+=tabEntropie[i];
		System.out.println("Entropie syst�me "+SSysteme);
	}
	
	/**
	 * Calcul du gain d'information
	 */
	public void calculGainInfo(){
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
		trouverMax(tabGain);
	}
	
	/**
	 * D�termine la personne avec le gain d'info max/la plus grande compl�mentarit� et l'ajoute � indices, qui repr�sente les personnes s�lectionn�es
	 * @param tab
	 */
	public void trouverMax(double[] tab){
		double max=0;
		int indice=0;
		for(int i =0;i<n;i++){
			if(tab[i]>max){
				max=tab[i];
				indice=i;
			}
		}
		System.out.println("Le Gain max est de "+max+" pour la personne n�"+(indice+1));
		indices.add(indice);
		
	}
	
	/**
	 * Calcul de la compl�mentarit�
	 */
	public void complementarite(){
		System.out.println("Calcul de la compl�mentarit� avec "+indices.size()+" personnes s�lectionn�es");
		
		//Les 8 matrices et tableaux n�cessaires aux calculs
		double[][] mat1 = new double[n][m];//p1 et pi
		double[][] mat2 = new double[n][m];//non (pi et p1)
		double[][] mat3 = new double[n][m];
		double[][] mat4 = new double[n][m];
		double[][] mat5 = new double[n][m];
		double[][] mat6 = new double[n][m];
		double[][] mat7 = new double[n][m];
		double[][] mat8 = new double[n][m];
		double[] tab1 = new double[n];
		double[] tab2 = new double[n];
		double[] tab3 = new double[n];
		double[] tab4 = new double[n];
		double[] tab5 = new double[n];
		double[] tab6 = new double[n];
		double[] tab7 = new double[n];
		double[] tab8 = new double[n];
		
		//On initialise un nouveau tableau dans lequel mettre la compl�mentarit�
		tabComp = new double[n];
		
		for(int i =0;i<n;i++){
			//On consid�re uniquement les personnes non s�lectionn�es
			if(!indices.contains(i))
				for(int j=0;j<m;j++){
					//D�finition des facteurs pr�sents dans les calculs
					double a=(double)mint[i][j];
					double b=(double)nombreDePresenceLigne[i];
					double c=(double)1-mint[i][j];
					double d=(double)-nombreDePresenceLigne[i];
					double e=(double)mint[i][j];
					double f=(double)nombreDePresenceLigne[i];
					double g=(double)1-mint[i][j];
					double h=(double)m-nombreDePresenceLigne[i];
					for(int k=0;k<indices.size();k++){
						a+=(double)mint[indices.get(k)][j];
						b+=(double)nombreDePresenceLigne[indices.get(k)];
						c+=(double)mint[indices.get(k)][j];
						d+=(double)nombreDePresenceLigne[indices.get(k)];
						e+=(double)1-mint[indices.get(k)][j];
						f+=(double)m-nombreDePresenceLigne[indices.get(k)];
						g+=(double)1-mint[indices.get(k)][j];
						h+=(double)m-nombreDePresenceLigne[indices.get(k)];
					}
					//Si les personnes s�lectionn�es sont pr�sentes � j et la personne i aussi
					if(presence(indices,j)&&mint[i][j]==1){
						mat1[i][j]=a*Math.log(a/b)/b;
						mat2[i][j]=0;
					}
					else{
						mat1[i][j]=0;
						mat2[i][j]=(1-a/b)*Math.log(1-a/b);
					}
					//Si les personnes s�lectionn�es sont pr�sentes � j mais pas la personne i 
					if(presence(indices,j)&&mint[i][j]==0){
						mat3[i][j]=c/(m+d)*Math.log(c/(m+d));
						mat4[i][j]=0;
					}
					else{
						mat3[i][j]=0;
						mat4[i][j]=(1-c/(m+d))*Math.log(1-c/(m+d));
					}
					//Si les personnes s�lectionn�es ne sont pas pr�sentes � j mais la personne i oui
					if(mint[i][j]==1&&!presence(indices,j)){
						mat5[i][j]=e/f*Math.log(e/f);
						mat6[i][j]=0;
					}
					else{
						mat5[i][j]=0;
						mat6[i][j]=(1-e/f)*Math.log(1-e/f);						
					}
					//Si les personnes s�lectionn�es ne sont pas pr�sentes � j et la personne i non plus
					if(mint[i][j]==0&&!presence(indices,j)){
						mat7[i][j]=(g/h)*Math.log(g/h);
						mat8[i][j]=0;
					}
					else{
						mat7[i][j]=0;
						mat8[i][j]=(1-g/h)*Math.log((1-g/h));
					}
						
					tabComp[i]=0;
			
					}
						
			for(int j=0;j<m;j++){
				
				double a=(double)nombreDePresenceLigne[i];
				double b=(double)m-nombreDePresenceLigne[i];
				double c=(double)nombreDePresenceLigne[i];
				double d=(double)m-nombreDePresenceLigne[i];
				for(int k=0;k<indices.size();k++){
						a+=(double)nombreDePresenceLigne[indices.get(k)];
						b+=(double)nombreDePresenceLigne[indices.get(k)];
						c+=(double)m-nombreDePresenceLigne[indices.get(k)];
						d+=(double)m-nombreDePresenceLigne[indices.get(k)];
				}
				a=a/((double)nombreTotalDePresence);
				b=b/((double)nombreTotalDePresence);
				c=c/((double)nombreTotalDePresence);
				d=d/((double)nombreTotalDePresence);
				
				tab1[i]+=a*mat1[i][j]/Math.log(m);
				tab2[i]+=(1-a)*mat2[i][j]/Math.log(m);
				tab3[i]+=b*mat3[i][j]/Math.log(m);
				tab4[i]+=(1-b)*mat4[i][j]/Math.log(m);
				tab5[i]+=c*mat5[i][j]/Math.log(m);
				tab6[i]+=(1-c)*mat6[i][j]/Math.log(m);
				tab7[i]+=d*mat7[i][j]/Math.log(m);
				tab8[i]+=(1-d)*mat8[i][j]/Math.log(m);
			}
			//On somme les 8 tableaux pour trouver les compl�mentarit�s
			tabComp[i]=tab3[i]+tab4[i]+tab5[i]+tab6[i]-tab1[i]-tab2[i]-tab7[i]-tab8[i];
			
			}
		
		
		System.out.println(afficherTableauDouble(tabComp));
		trouverMax(tabComp);
	}
	
	/**
	 * Retourne true que si toutes les personnes s�lectionn�es sont pr�sentes � l'�v�nement j
	 * @param indices Les personnes s�lectionn�es
	 * @param j l'�v�nement � consid�rer
	 * @return
	 */
	public boolean presence(ArrayList<Integer> indices,int j){
		for(int i=0;i<indices.size();i++){
				if(mint[indices.get(i)][j]==0)
					return false;
		}
		return true;
	}
	
	/**
	 * D�termine si la condition d'arr�t toutes les compl�mentarit�s n�gatives ou nulles est v�rifi�
	 * @return
	 */
	public boolean arret(){
		for(int i =0;i<n;i++)
			if(tabComp[i]>0)
				return false;
		return true;
	}
	
	/**
	 * D�termine si la condition d'arr�t converture totale est v�rifi�e
	 */
	public boolean arretCouvertureTotale(){
		int[] presence = new int[m];
		int somme=0;
		
		//Pour toutes les personnes s�lectionn�es, on met les �v�nements couverts dans presence
		for(int i =0;i<indices.size();i++)
			for(int j=0;j<m;j++)
				if(mint[indices.get(i)][j]==1)
					presence[j]=1;
		//On fait la somme des �v�nements couverts
		for(int i=0;i<m;i++)
			somme+=presence[i];
		//Si tous les �v�nements sont coverts, la somme vaut m 
		if(somme==m){
			System.out.println("Tous les �v�nements sont couverts");
			return true;
		}
			
		else
			return false;
					
						
	}
	
	//__________ M�thodes pour afficher des tableaux/matrices dans la console_____________
	
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

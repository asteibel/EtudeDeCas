package statistique;

import java.util.Scanner;

public class Entropie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Pour tester notre algorithme nous allons utiliser la matrice donner dans le fichier joint au mail
		int [][] m = new int[19][15];
		
		m[1][1]=1;	m[4][1]=1;	m[7][1]=0;	
		m[1][2]=1;	m[4][2]=0;	m[7][2]=0;		
		m[1][3]=1;	m[4][3]=1;	m[7][3]=0;	
		m[1][4]=1;	m[4][4]=1;	m[7][4]=0;	
		m[1][5]=1;	m[4][5]=1;	m[7][5]=1;	
		m[1][6]=1;  m[4][6]=1;	m[7][6]=1;	
		m[1][7]=0;				m[7][7]=1;	
		m[1][8]=1;	m[4][7]=1;	m[7][8]=1;	
		m[1][9]=1;	m[4][8]=1;	m[7][9]=0;	
		m[1][10]=0;	m[4][9]=0;	m[7][10]=0;	
		m[1][11]=0;	m[4][10]=0;	m[7][11]=0;	
		m[1][12]=0;	m[4][11]=0;	m[7][12]=0;	
		m[1][13]=0;	m[4][12]=0;	m[7][13]=0;	
		m[1][14]=0;	m[4][13]=0;	m[7][14]=0;	
		m[2][1]=1;	m[4][14]=0;	m[8][1]=0;	
		m[2][2]=1;	m[5][1]=0;	m[8][2]=0;		
		m[2][3]=1;	m[5][2]=0;	m[8][3]=0;	
		m[2][4]=0;	m[5][3]=1;	m[8][4]=0;	
		m[2][5]=1;	m[5][4]=1;	m[8][5]=0;	
		m[2][6]=1;  m[5][5]=1;	m[8][6]=1;	
		m[2][7]=1;	m[5][6]=0;	m[8][7]=0;	
		m[2][8]=1;	m[5][7]=1;	m[8][8]=1;	
		m[2][9]=0;	m[5][8]=0;	m[8][9]=1;	
		m[2][10]=0;	m[5][9]=0;	m[8][10]=0;	
		m[2][11]=0;	m[5][10]=0;	m[8][11]=0;	
		m[2][12]=0;	m[5][11]=0;	m[8][12]=0;	
		m[2][13]=0;	m[5][12]=0;	m[8][13]=0;	
		m[2][14]=0;	m[5][13]=0;	m[8][14]=0;	
		m[3][1]=0;	m[5][14]=0;	m[9][1]=0;	
		m[3][2]=1;	m[6][1]=0;	m[9][2]=0;	
		m[3][3]=1;	m[6][2]=0;	m[9][3]=0;	
		m[3][4]=1;	m[6][3]=1;	m[9][4]=0;	
		m[3][5]=1;	m[6][4]=0;	m[9][5]=1;	
		m[3][6]=1;	m[6][5]=1;	m[9][6]=0;	
		m[3][7]=1;	m[6][6]=1;	m[9][7]=1;	
		m[3][8]=1;	m[6][7]=0;	m[9][8]=1;	
		m[3][9]=1;	m[6][8]=1; 	m[9][9]=1;	
		m[3][10]=0;	m[6][9]=0;	m[9][10]=0;	
		m[3][11]=0;	m[6][10]=0;	m[9][11]=0;	
		m[3][12]=0;	m[6][11]=0;	m[9][12]=0;	
		m[3][13]=0;	m[6][12]=0;	m[9][13]=0;	
		m[3][14]=0;	m[6][13]=0;	m[9][14]=0;	
					m[6][14]=0;

					
		m[10][1]=0;	   m[13][1]=0;	m[16][1]=0;	
		m[10][2]=0;	   m[13][2]=0;	m[16][2]=0;	
		m[10][3]=0;    m[13][3]=0;	m[16][3]=0;	
		m[10][4]=0;	   m[13][4]=0;	m[16][4]=0;	
		m[10][5]=0;	   m[13][5]=0;	m[16][5]=0;	
		m[10][6]=0;    m[13][6]=0;	m[16][6]=0;	
		m[10][7]=1;				    m[16][7]=0;	
		m[10][8]=1;	   m[13][7]=1;	m[16][8]=1;	
		m[10][9]=1;	   m[13][8]=1;	m[16][9]=1;	
		m[10][10]=0;   m[13][9]=1;	m[16][10]=0;	
		m[10][11]=0;   m[13][10]=1;	m[16][11]=0;	
		m[10][12]=1;   m[13][11]=0;	m[16][12]=0;	
		m[10][13]=0;   m[13][12]=1;	m[16][13]=0;	
		m[10][14]=0;   m[13][13]=1;	m[16][14]=0;	
		m[11][1]=0;    m[13][14]=1;	m[17][1]=0;	
		m[11][2]=0;	   m[14][1]=0;	m[17][2]=0;		
		m[11][3]=0;    m[14][2]=0;	m[17][3]=0;	
		m[11][4]=0;	   m[14][3]=0;	m[17][4]=0;	
		m[11][5]=0;	   m[14][4]=0;	m[17][5]=0;	
		m[11][6]=0;    m[14][5]=0;	m[17][6]=0;	
		m[11][7]=0;	   m[14][6]=1;	m[17][7]=0;	
		m[11][8]=1;	   m[14][7]=1;	m[17][8]=0;	
		m[11][9]=1;    m[14][8]=0;	m[17][9]=1;	
		m[11][10]=1;   m[14][9]=1;	m[17][10]=0;	
		m[11][11]=0;   m[14][10]=1;	m[17][11]=1;	
		m[11][12]=1;   m[14][11]=1;	m[17][12]=0;	
		m[11][13]=0;   m[14][12]=1;	m[17][13]=0;	
		m[11][14]=0;   m[14][13]=1;	m[17][14]=0;	
		m[12][1]=0;	   m[14][14]=1;	m[18][1]=0;	
		m[12][2]=0;    m[15][1]=0;	m[18][2]=0;	
		m[12][3]=0;	   m[15][2]=0;	m[18][3]=0;	
		m[12][4]=0;	   m[15][3]=0;	m[18][4]=0;	
		m[12][5]=0;	   m[15][4]=0;	m[18][5]=0;	
		m[12][6]=0;	   m[15][5]=0;	m[18][6]=0;	
		m[12][7]=0;	   m[15][6]=0;	m[18][7]=0;	
		m[12][8]=1;	   m[15][7]=1;	m[18][8]=0;	
		m[12][9]=1;	   m[15][8]=1; 	m[18][9]=1;	
		m[12][10]=1;   m[15][9]=0;	m[18][10]=0;	
		m[12][11]=0;   m[15][10]=1;	m[18][11]=1;	
		m[12][12]=1;   m[15][11]=1;	m[18][12]=0;	
		m[12][13]=1;   m[15][12]=1;	m[18][13]=0;	
		m[12][14]=1;   m[15][13]=0;	m[18][14]=0;	
				       m[15][14]=0;	
	
		CalculerEntropie entropie= new CalculerEntropie(m,18,14);
		entropie.matriceEntropie();
		//entropie.afficherMatriceEnropie();
		//entropie.afficherMatriceBooleene();
		entropie.entropieListePersonne();
		Scanner entree = new Scanner(System.in);
		System.out.println("Donner le repertoire et le nom ou enregistrer le fichier");
		String destination = entree.next();
		entree.close();
		entropie.afficherEntropiePersonne(destination);
		
	}

}

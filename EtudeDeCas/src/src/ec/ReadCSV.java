package src.ec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class ReadCSV {

	int n;
	int m;
	
	public ReadCSV(int n, int m){
		this.m=m;
		this.n=n;
	}
	
	public int[][] run(){
		String csvFile ="C:/Users/Audric/Desktop/data.csv";
		BufferedReader br = null;
		String line ="";
		String split=";";
		
		int[][] tab = new int[n][m];
		
		try{
			br = new BufferedReader(new FileReader(csvFile));
			int compteur = 0;
			while((line = br.readLine())!=null){
				String[] personne = line.split(split);
				for(int i =0;i<m;i++){
					tab[compteur][i]=Integer.parseInt(personne[i]);
				}
				compteur++;				
			}
			/**
			String s = "";
			for(int i =0;i<tab.length;i++){
				for(int j=0;j<tab[0].length;j++)
					s+=tab[i][j]+" ";
				s+="\n";
			}
			System.out.println(s);*/
		}
		catch(Exception e){
			System.out.println("Fichier non trouvé, vérifiez l'adresse "+csvFile);
		}
		return tab;
	}
}

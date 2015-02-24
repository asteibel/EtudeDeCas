package src.ec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class ReadCSV {

	int n;
	int m;
	
	String path;
	
	public ReadCSV(int n, int m){
		this.m=m;
		this.n=n;
	}
	
	public ReadCSV(String path){
		this.path=path;
	}
	
	public int[][] run(){
		String csvFile ="C:/Users/Audric/Desktop/data.csv";
		BufferedReader br = null;
		String line ="";
		String split=";";
		int[][] tab;
		try{
			System.out.println("1");
			br = new BufferedReader(new FileReader(path));
			System.out.println("2");
			int compteur = 1;
			String[] personne = br.readLine().split(split);
			while((line = br.readLine())!=null)
				compteur++;				
			tab = new int[compteur][personne.length];
			System.out.println(compteur+";"+personne.length);
			
			
			br = new BufferedReader(new FileReader(path));
			compteur = 0;
			while((line = br.readLine())!=null){
				personne = line.split(split);
				for(int i =0;i<tab[0].length;i++){
					if(Integer.parseInt(personne[i])==1)
						tab[compteur][i]=1;
					else
						tab[compteur][i]=0;
				}
				compteur++;				
			}
		}
		catch(Exception e){
			tab=new int[0][0];
			System.out.println("Erreur de chargement du fichier ");
		}
		return tab;
	}
}

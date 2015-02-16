package src.ec;

public class Tableau {

	/**
	 * Nombre d'individus
	 */
	int n;
	
	/**
	 * Nombre d'évènements
	 */
	int m;
	
	/**
	 * indique si la personne i été présente à l'ev i 
	 */
	Boolean[][] presence;
	
	String[] listeIndividus;
	String[] listeEvenements;
	
	public String[] getListeIndividus() {
		return listeIndividus;
	}

	public void setListeIndividus(String[] listeIndividus) {
		this.listeIndividus = listeIndividus;
	}

	public String[] getListeEvenements() {
		return listeEvenements;
	}

	public void setListeEvenements(String[] listeEvenements) {
		this.listeEvenements = listeEvenements;
	}

	public void Tableau(){
		
	}
	
	public void Tableau(int n, int m){
		this.n=n;
		this.m=m;
		presence=new Boolean[n][m];
		for (int i =0;i<n;i++)
			for(int j=0;j<m;j++)
				presence[i][j]=false;
	}

	public void initialisation(int n, int m){
		this.n=n;
		this.m=m;
		presence=new Boolean[n][m];
		for (int i =0;i<n;i++)
			for(int j=0;j<m;j++)
				presence[i][j]=false;
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

	public Boolean[][] getPresence() {
		return presence;
	}

	public void setPresence(Boolean[][] presence) {
		this.presence = presence;
	}
	
	
	public void chargerWE(){
		initialisation(18, 14);
		presence[0][0]=true;
		presence[0][1]=true;
		presence[0][2]=true;
		presence[0][3]=true;
		presence[0][4]=true;
		presence[0][5]=true;
		presence[0][7]=true;
		presence[0][8]=true;
		
		presence[1][0]=true;
		presence[1][1]=true;
		presence[1][2]=true;
		presence[1][4]=true;
		presence[1][5]=true;
		presence[1][6]=true;
		presence[1][7]=true;
		
		presence[2][1]=true;
		presence[2][2]=true;
		presence[2][3]=true;
		presence[2][4]=true;
		presence[2][5]=true;
		presence[2][6]=true;
		presence[2][7]=true;
		presence[2][8]=true;
		
		presence[3][0]=true;
		presence[3][2]=true;
		presence[3][3]=true;
		presence[3][4]=true;
		presence[3][5]=true;
		presence[3][6]=true;
		presence[3][7]=true;
		
		presence[4][2]=true;
		presence[4][3]=true;
		presence[4][4]=true;
		presence[4][6]=true;
		
		presence[5][2]=true;
		presence[5][4]=true;
		presence[5][5]=true;
		presence[5][7]=true;
		
		presence[6][4]=true;
		presence[6][5]=true;
		presence[6][6]=true;
		presence[6][7]=true;
		
		presence[7][5]=true;
		presence[7][7]=true;
		presence[7][8]=true;
		
		presence[8][4]=true;
		presence[8][6]=true;
		presence[8][7]=true;
		presence[8][8]=true;
		
		presence[9][6]=true;
		presence[9][7]=true;
		presence[9][8]=true;
		presence[9][11]=true;
		
		presence[10][7]=true;
		presence[10][8]=true;
		presence[10][9]=true;
		presence[10][11]=true;
		
		presence[11][7]=true;
		presence[11][8]=true;
		presence[11][9]=true;
		presence[11][11]=true;
		presence[11][12]=true;
		presence[11][13]=true;
		
		presence[12][6]=true;
		presence[12][7]=true;
		presence[12][8]=true;
		presence[12][9]=true;
		presence[12][11]=true;
		presence[12][12]=true;
		presence[12][13]=true;
		
		presence[13][5]=true;
		presence[13][6]=true;
		presence[13][8]=true;
		presence[13][9]=true;
		presence[13][10]=true;
		presence[13][11]=true;
		presence[13][12]=true;
		presence[13][13]=true;
		
		presence[14][6]=true;
		presence[14][7]=true;
		presence[14][9]=true;
		presence[14][10]=true;
		presence[14][11]=true;
		
		presence[15][7]=true;
		presence[15][8]=true;
		
		presence[16][8]=true;
		presence[16][10]=true;
		
		presence[17][8]=true;
		presence[17][10]=true;
		
		String[] listeEvenements={"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
		String[] listeIndividus={"EJ","LM","TA","BR","CM","FA","EN","PO","RD","VS","ML","KR","SA","NF","HL","DM","OC","FP"};
		this.listeEvenements=listeEvenements;
		this.listeIndividus=listeIndividus;
		
	}
}

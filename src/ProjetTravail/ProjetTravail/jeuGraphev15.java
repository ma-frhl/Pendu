import java.io.*;

class Tdonnee //faisable en liste car que des int mais plus lisible avec une structure
{
	int res;
	int foc;
	int nbrpa;
	int nbrpr;
	int nbrfu;
	int nbrere;
	int ress;
	int focs;
	int nbrpas;
	int nbrprs;
	int nbrfus;
	int nbreres;
}


public class jeuGraphev15
{
	static int jr, nbrTour,jactif, iactif, factif;
	static boolean Partie;
	static BufferedReader flux = new BufferedReader(new InputStreamReader(System.in)) ;
	

	
	
//=======================================================================================================
	
	private static String [][][] matInit() throws IOException  // fonction compile 
	{
		int i,j,w, m;
		String[][][]M=new String [3][6][6];
		for (m=0; m<3;m++)
		{
			for (i=1;i<5;i++)
			{
				
				for (j=1;j<5;j++)
				{
					M[m][i][j] = "0"; 
				}
			
			}
			for (w=0; w<6;w++)
			{
				M[m][0][w]="m";
				M[m][5][w]="m";
				M[m][w][0]="m";
				M[m][w][5]="m";
			}
			M[m][1][1]="B"+ m;
			M[m][4][4]="N"+m;
		}  
		return (M);
	}
	
//=======================================================================================================
	
	private static void initialisationTDonnees (Tdonnee[] donnees, int r0 , int r1) throws IOException //fonction compile 
	{
		int i;
		donnees[0].res=r0;
		donnees[1].res=r1;
		donnees[0].foc=0;
		donnees[1].foc=2;
		for (i=0;i<2;i+=1)
		{
			donnees[i].nbrpa=1;
			donnees[i].nbrpr=1;
			donnees[i].nbrfu=1;
			donnees[i].nbrere=3;
		}
	}
	
//=======================================================================================================
	
	private static void copieListMat (String[][][] LMmodel,String[][][] LMcopie) throws IOException  // fonction compile
	{
		int m,i,j;
		for (m=0;m<3;m+=1)
		{
			for (i=0;i<6;i+=1)
			{
				for (j=0;j<6;j+=1)
				{
					LMcopie[m][i][j]=LMmodel[m][i][j];
				}
			}
		}
	}
	
//=======================================================================================================
	
	private static void sauvTour(Tdonnee[] donnees, String[][][]Mj,String[][][]Ms)  throws IOException   //fct compile
	{
		int i;
		copieListMat(Mj,Ms);
		for (i=0;i<=1;i+=1)
		{
			donnees[i].ress=donnees[i].res;
			donnees[i].focs=donnees[i].foc;
			donnees[i].nbrpas=donnees[i].nbrpa;
			donnees[i].nbrprs=donnees[i].nbrpr;
			donnees[i].nbrfus=donnees[i].nbrfu;
			donnees[i].nbreres=donnees[i].nbrere;
		}
	}
	
//=======================================================================================================
	
	private static int nbrDansEre(int joueur , int ere, Tdonnee[] donnees)  throws IOException // fct compile
	{
		int nbr;
		nbr = donnees[joueur].nbrpa; //ere=0
		if (ere==1)
		{
			nbr=donnees[joueur].nbrpr;
		}
		else
		{
			if (ere==2)
			{
				nbr=donnees[joueur].nbrfu;
			}
		}
		return (nbr);
	}

//=======================================================================================================	
	
	private static void choixFocus(int f, Tdonnee[] donnees) throws IOException //fct compiler
	{
		int choix;
		boolean cond1,cond2;
		if (f==0) 
		{
			cond1=(donnees[jr].nbrpr != 0);
			cond2=(donnees[jr].nbrfu != 0);
			if ((cond1) && (cond2))
			{
				System.out.println("choisir l'ere ou placer le pion focus");
				System.out.println("1 pour se focus sur le present" );
				System.out.println("2 pour se focus sur le futur" );
				choix=Integer.valueOf(flux.readLine()).intValue();
				switch(choix)
				{
					case 1:
					{
						donnees[jr].foc=1;
						System.out.println("le focus a ete place dans le present");
					}
					break;
					case 2:
					{
						donnees[jr].foc=2;
						System.out.println("le focus a ete place dans le futur");
					}
					break;
					default: System.out.println("saisie erronee choisir 1 pour se focus sur le present et 2 sur le futur");
					choixFocus(f,donnees);
				}
			}
			else
			{
				if (cond1)
				{
					donnees[jr].foc=1;
					System.out.println("le focus a ete place dans le present");
				}
				else
				{
					donnees[jr].foc=2;
					System.out.println("le focus a ete place dans le futur");
				}
			}
		}
		else
		{
			if (f==1) 
			{
				cond1=(donnees[jr].nbrpa != 0);
				cond2=(donnees[jr].nbrfu != 0);
				if ((cond1) && (cond2))
				{
					System.out.println("choisir l'ere ou placer le pion focus");
					System.out.println("1 pour se focus sur le passe" );
					System.out.println("2 pour se focus sur le futur" );
					choix=Integer.valueOf(flux.readLine()).intValue();
					switch(choix)
					{
						case 1:
						{
							donnees[jr].foc=0;
							System.out.println("le focus a ete place dans le passe");
						}
						break;
						case 2:
						{
							donnees[jr].foc=2;
							System.out.println("le focus a ete place dans le futur");
						}
						break;
						default: System.out.println("saisie erronee choisir 1 pour se focus sur le passe et 2 sur le futur");
						choixFocus(f,donnees);
					}
				}
				else
				{
					if (cond1)
					{
						donnees[jr].foc=0;
						System.out.println("le focus a ete place dans le passe");
					}
					else
					{
						donnees[jr].foc=2;
						System.out.println("le focus a ete place dans le futur");
					}
				}
			}
			else //f=2
			{
				cond1=(donnees[jr].nbrpa != 0);
				cond2=(donnees[jr].nbrpr != 0);
				if ((cond1) && (cond2))
				{
					System.out.println("choisir l'ere ou placer le pion focus");
					System.out.println("1 pour se focus sur le passe" );
					System.out.println("2 pour se focus sur le present" );
					choix=Integer.valueOf(flux.readLine()).intValue();
					switch(choix)
					{
						case 1:
						{
							donnees[jr].foc=0;
							System.out.println("le focus a ete place dans le passe");
						}
						break;
						case 2:
						{
							donnees[jr].foc=1;
							System.out.println("le focus a ete place dans le present");
						}
						break;
						default: System.out.println("saisie erronee choisir 1 pour se focus sur le passe et 2 sur le present");
						choixFocus(f,donnees);
					}
				}
				else
				{
					if (cond1)
					{
						donnees[jr].foc=0;
						System.out.println("le focus a ete place dans le passe");
					}
					else
					{
						donnees[jr].foc=1;
						System.out.println("le focus a ete place dans le present");
					}
				}
			}
		}
	}
	
//=======================================================================================================
	
	private static void confForfait(int[][]LP,String [][][] Mj, String[][][]Ms,Tdonnee[] donnees,String[] JR)  throws IOException // fct compile
	{
		int conf;
		conf=Integer.valueOf(flux.readLine()).intValue();
		switch(conf)
		{
			case 0:
			{
				Partie=false;
				System.out.println(JR[jr] + " a declarer forfait au " + nbrTour +"ieme tour");
			}
			break;
			case 1:
			{
				finTour(LP,Mj,Ms,donnees,JR);
			}
			break;
			default: System.out.println("saisie erronee taper 0 pour confirmer le forfait ou 1 pour revenir en arriere "); //fct confirmer 
			confForfait(LP,Mj,Ms,donnees,JR);
		}
	}
	
//=======================================================================================================
	
	private static void confEgalite(int[][]LP,String [][][] Mj, String[][][]Ms,Tdonnee[] donnees,String[] JR)  throws IOException // fct compile
	{
		int conf;
		conf=Integer.valueOf(flux.readLine()).intValue();
		switch(conf)
		{
			case 0:
			{
				Partie=false;
				System.out.println(JR[jr] + " a declarer une egalite au "+ nbrTour +"ieme tour");
			}
			break;
			case 1:
			{
				finTour(LP,Mj,Ms,donnees,JR);
			}
			break;
			default: System.out.println("saisie erronee taper 0 pour confirmer l'egalite ou 1 pour revenir en arriere ");
			confEgalite(LP,Mj,Ms,donnees,JR);
		}
	}
	
//=======================================================================================================
	
	private static void finTour( int[][] LP,String [][][] Mj, String[][][]Ms,Tdonnee[] donnees,String[] JR) throws IOException   //  fct compiler sauvegarde de partie ; et a tester 
	{
		int choix , k;
		String sauvegarde , nom;
		System.out.println("Tapez");
		System.out.println("0 pour mettre fin a votre tour" );
		System.out.println("1 pour annuler votre tour de jeu" );
		System.out.println("2 pour declarer forfait" );
		System.out.println("3 pour declarer une egalite" );
		System.out.println("4 pour sauvegarder la partie" );
		choix=Integer.valueOf(flux.readLine()).intValue();
		switch(choix)
		{
			case 0:
			{
				nbrTour += 1*jr;
				jr=1-jr;
			}
			break;
			case 1:
			{
				Partie=true;
				copieListMat(Ms,Mj);
				for (k=0;k<=1;k+=1)
				{
					donnees[k].res=donnees[k].ress;
					donnees[k].foc=donnees[k].focs;
					donnees[k].nbrpa=donnees[k].nbrpas;
					donnees[k].nbrpr=donnees[k].nbrprs;
					donnees[k].nbrfu=donnees[k].nbrfus;
					donnees[k].nbrere=donnees[k].nbreres;
				}
			}
			break;
			case 2:
			{
				System.out.println("taper 0 pour confirmer le forfait ou 1 pour revenir en arriere" );
				confForfait(LP,Mj,Ms,donnees,JR);
			}
			break;
			case 3:
			{
				System.out.println("taper 0 pour confirmer l'egalite ou 1 pour revenir en arriere" );//entree et retour arriere 
				confEgalite(LP,Mj,Ms,donnees,JR);
			}
			break;
			case 4:
			{
				System.out.println("Sur quelle sauvegarde enregistrer la partie" );
				sauvExiste(LP,JR,Mj,Ms,donnees);
			}
			break;
			default: System.out.println("saisie erronee tapez: ");
			finTour(LP,Mj,Ms,donnees,JR);
		}
	}
//=======================================================================================================			
	
	private static void sauvExiste (int[][] LP, String[] JR, String [][][] Mj,String [][][] Ms, Tdonnee[] donnees) throws IOException
	{
		String sauvegarde,nom;
		sauvegarde=choixSauvegarde(1,LP,JR,Mj,Ms,donnees);
		nom=lireLigne(sauvegarde,1);
		if (nom=="vide")
		{
			while ((nom=="vide") || (nom.length()>50))
			{
				System.out.println("donner un nom a la sauvegarde (moins de 50 caractere)" );
				nom=flux.readLine();
			}
			save(sauvegarde,Mj,nom,donnees);
			Partie=false;
		}
		else 
		{
			System.out.println("la sauvegarde n'est pas vide" );
			confSauv( sauvegarde,nom,LP,JR,Mj,Ms,donnees);
		}
	}
				
//=======================================================================================================			
	
	private static void confSauv (String sauvegarde, String nom,int LP[][],String[ ] JR,String [][][] Mj,String[][][]Ms,Tdonnee[] donnees) throws IOException
	{
		int choix;
		System.out.println("taper 1 pour ecraser nom" );
		System.out.println("taper 2 pour annuler" );
		choix=Integer.valueOf(flux.readLine()).intValue();
		switch(choix)
		{
			case 1:
			{
				nom="vide";
				while ((nom=="vide") || (nom.length()>50))
				{
					System.out.println("donner un nom a la sauvegarde (moins de 50 caractere)" );
					nom=flux.readLine();
				}
				save(sauvegarde,Mj,nom,donnees);
				Partie=false;
			}
			break;
			case 2:
			{
				sauvExiste(LP,JR,Mj,Ms,donnees);
			}
			break;
			default: System.out.println("saisie erronee tapez: ");
			confSauv(sauvegarde,nom,LP,JR,Mj,Ms,donnees);
		}
		
	}
	
	
//=======================================================================================================			
	
	private static int choixPion (int n,int m,int LP[][],String[] JR, String [][][] Mj,  String[][][]Ms) throws IOException //fct compiler  m c est f  
	{
		int increment,i,j,pactif,k;
		String lettre, choix;
		lettre="azertyui";
		increment=0;
		for (i=1;i<5;i+=1)  //peut etre opti avec un while lie au nombre dans ere et increment et en variant i j de +1 par boucle 
		{
			for (j=1;j<5;j+=1)
			{
				
				if (Mj[m][i][j]!="0") 
				{
					if (Mj[m][i][j].substring(0,1).equals(JR[jr].substring(0,1)))
					{
						LP[increment][0]=i;
						LP[increment][1]=j;
						increment+=1;
					}
				}
			}
		}
		pactif=0;
		if (n!=1)
		{
			System.out.println("choisir le pion actif en tapant:");
			for (k=0;k<n;k+=1)
			{
				i=LP[k][0];
				j=LP[k][1];
				System.out.println( lettre.substring(k,k+1) + " pour le pion " + Mj[m][i][j] );
			}
			if (n==2)
			{
				choix=flux.readLine();
				switch(choix)
				{
					case ("a"):
					{
						pactif=0;
					}
					break;
					case ("z"):
					{
						pactif=1;
					}
					break;
					default: System.out.println("saisie erronee ");
					choixPion (n,m,LP,JR,Mj,Ms);
				}
			}
			else
			{
				if (n==3)
				{
					choix=flux.readLine();
					switch(choix)
					{
						case ("a"):
						{
							pactif=0;
						}
						break;
						case ("z"):
						{
							pactif=1;
						}
						break;
						case ("e"):
						{
							pactif=2;
						}
						break;
						default: System.out.println("saisie erronee");
						choixPion (n,m,LP,JR,Mj,Ms);
					}
				}
				else
				{
					if (n==4)
					{
						choix=flux.readLine();
						switch(choix)
						{
							case ("a"):
							{
								pactif=0;
							}
							break;
							case ("z"):
							{
								pactif=1;
							}
							break;
							case ("e"):
							{
								pactif=2;
							}
							break;
							case ("r"):
							{
								pactif=3;
							}
							break;
							default: System.out.println("saisie erronee ");
							choixPion (n,m,LP,JR,Mj,Ms);
						}
					}
					else
					{
						if (n==5)
						{
							choix=flux.readLine();
							switch(choix)
							{
								case ("a"):
								{
									pactif=0;
								}
								break;
								case ("z"):
								{
									pactif=1;
								}
								break;
								case ("e"):
								{
									pactif=2;
								}
								break;
								case ("r"):
								{
									pactif=3;
								}
								break;
								case ("t"):
								{
									pactif=4;
								}
								break;
								default: System.out.println("saisie erronee");
								choixPion (n,m,LP,JR,Mj,Ms);
							}
						}
						else
						{
							if (n==6)
							{
								choix=flux.readLine();
								switch(choix)
								{
									case ("a"):
									{
										pactif=0;
									}
									break;
									case ("z"):
									{
										pactif=1;
									}
									break;
									case ("e"):
									{
										pactif=2;
									}
									break;
									case ("r"):
									{
										pactif=3;
									}
									break;
									case ("t"):
									{
										pactif=4;
									}
									break;
									case ("y"):
									{
										pactif=5;
									}
									break;
									default: System.out.println("saisie erronee");
									choixPion (n,m,LP,JR,Mj,Ms);
								}
							}
							else 
							{
								if (n==7)
								{
									choix=flux.readLine();
									switch(choix)
									{
										case ("a"):
										{
											pactif=0;
										}
										break;
										case ("z"):
										{
											pactif=1;
										}
										break;
										case ("e"):
										{
											pactif=2;
										}
										break;
										case ("r"):
										{
											pactif=3;
										}
										break;
										case ("t"):
										{
											pactif=4;
										}
										break;
										case ("y"):
										{
											pactif=5;
										}
										break;
										case ("u"):
										{
											pactif=6;
										}
										break;
										default: System.out.println("saisie erronee");
										choixPion (n,m,LP,JR,Mj,Ms);
									}
								}
								else 
								{
									choix=flux.readLine();
									switch(choix)
									{
										case ("a"):
										{
											pactif=0;
										}
										break;
										case ("z"):
										{
											pactif=1;
										}
										break;
										case ("e"):
										{
											pactif=2;
										}
										break;
										case ("r"):
										{
											pactif=3;
										}
										break;
										case ("t"):
										{
											pactif=4;
										}
										break;
										case ("y"):
										{
											pactif=5;
										}
										break;
										case ("u"):
										{
											pactif=6;
										}
										break;
										case ("i"):
										{
											pactif=7;
										}
										break;
										default: System.out.println("saisie erronee");
										choixPion (n,m,LP,JR,Mj,Ms);
										}
									}
								}
							}
						}
					}
				}
			}
		return (pactif);
	}



//=======================================================================================================				
	
	private static void choixAction(int m, int i, int j,String [] JR,String[][][]M,Tdonnee[] donnees,boolean cond1,boolean cond2) throws IOException// fct compile
	{
		int choix;
		choix=Integer.valueOf(flux.readLine()).intValue(); 
		switch(choix)
		{
			case 1 : //mvmt
			{
				choixDeplacement(JR,M,donnees);
			}
			break;
			case 2 : //TP
			{
				choixTp(m,i,j,JR,M,donnees,cond1,cond2);
			}
			break;
			default: System.out.println("saisie erronee tapez 1 pour un deplacement et 2 pour un voyage temporel");
			choixAction(m,i,j,JR,M,donnees,cond1,cond2);
		}		
	}
				
	
		
//=======================================================================================================				

	private static void choixDeplacement(String[]JR,String[][][]Mj,Tdonnee[] donnees) throws IOException //fct compiler 
	{
		int dir;
		if (iactif!=1)
		{
			if (iactif!=4)
			{
				if (jactif!=1)
				{
					if (jactif!=4)
					{
						System.out.println("choisir le deplacement en tapant:" );
						System.out.println("5  pour aller en haut" );
						System.out.println("2  pour aller en bas" );
						System.out.println("3  pour aller a droite" );
						System.out.println("1  pour aller en gauche" );
						dir=Integer.valueOf(flux.readLine()).intValue();
						switch(dir)
						{
							case 5 : 
							{
								deplacementHaut(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							case 2:
							{
								deplacementBas(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							case 3:
							{
								deplacementDroite(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							case 1:
							{
								deplacementGauche(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							default: System.out.println("saisie erronee ");
							choixDeplacement(JR,Mj,donnees);
						}
					}
					else //j=4
					{
						System.out.println("choisir le deplacement en tapant:" );
						System.out.println("5  pour aller en haut" );
						System.out.println("2  pour aller en bas" );
						System.out.println("1  pour aller en gauche" );
						dir=Integer.valueOf(flux.readLine()).intValue();
						switch(dir)
						{
							case 5 : 
							{
								deplacementHaut(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							case 2:
							{
								deplacementBas(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							case 1:
							{
								deplacementGauche(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							default: System.out.println("saisie erronee ");
							choixDeplacement(JR,Mj,donnees);
						}
					}
				}
				else // j=1
				{
					System.out.println("choisir le deplacement en tapant:" );
					System.out.println("5  pour aller en haut" );
					System.out.println("2  pour aller en bas" );
					System.out.println("3  pour aller a droite" );
					dir=Integer.valueOf(flux.readLine()).intValue();
					switch(dir)
					{
						case 5 : 
						{
							deplacementHaut(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						case 2:
						{
							deplacementBas(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						case 3:
						{
							deplacementDroite(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						default: System.out.println("saisie erronee ");
						choixDeplacement(JR,Mj,donnees);
					}
				}
			}
			else //i=4
			{
				if (jactif!=1)
				{
					if (jactif!=4)
					{
						System.out.println("choisir le deplacement en tapant:" );
						System.out.println("5  pour aller en haut" );
						System.out.println("3  pour aller a droite" );
						System.out.println("1  pour aller en gauche" );
						dir=Integer.valueOf(flux.readLine()).intValue();
						switch(dir)
						{
							case 5 : 
							{
								deplacementHaut(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							case 3:
							{
								deplacementDroite(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							case 1:
							{
								deplacementGauche(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							default: System.out.println("saisie erronee ");
							choixDeplacement(JR,Mj,donnees);
						}
					}
					else //j=4
					{
						System.out.println("choisir le deplacement en tapant:" );
						System.out.println("5  pour aller en haut" );
						System.out.println("1  pour aller en gauche" );
						dir=Integer.valueOf(flux.readLine()).intValue();
						switch(dir)
						{
							case 5 : 
							{
								deplacementHaut(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							case 1:
							{
								deplacementGauche(iactif,jactif,factif,JR,Mj,donnees);
							}
							break;
							default: System.out.println("saisie erronee ");
							choixDeplacement(JR,Mj,donnees);
						}
					}
				} 
				else //j=1
				{
					System.out.println("choisir le deplacement en tapant:" );
					System.out.println("5  pour aller en haut" );
					System.out.println("3  pour aller a droite" );
					dir=Integer.valueOf(flux.readLine()).intValue();
					switch(dir)
					{
						case 5 : 
						{
							deplacementHaut(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						case 3:
						{
							deplacementDroite(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						default: System.out.println("saisie erronee ");
						choixDeplacement(JR,Mj,donnees);
					}
				}
			}
		}
		else //i=1
		{
			if (jactif!=1)
			{
				if (jactif!=4)
				{
					System.out.println("choisir le deplacement en tapant:" );
					System.out.println("2  pour aller en bas" );
					System.out.println("3  pour aller a droite" );
					System.out.println("1  pour aller en gauche" );
					dir=Integer.valueOf(flux.readLine()).intValue();
					switch(dir)
					{
						case 2:
						{
							deplacementBas(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						case 3:
						{
							deplacementDroite(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						case 1:
						{
							deplacementGauche(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						default: System.out.println("saisie erronee ");
						choixDeplacement(JR,Mj,donnees);
					}
				}
				else //j=4
				{
					System.out.println("choisir le deplacement en tapant:" );
					System.out.println("2  pour aller en bas" );
					System.out.println("1  pour aller en gauche" );
					dir=Integer.valueOf(flux.readLine()).intValue();
					switch(dir)
					{
						case 2:
						{
							deplacementBas(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						case 1:
						{
							deplacementGauche(iactif,jactif,factif,JR,Mj,donnees);
						}
						break;
						default: System.out.println("saisie erronee ");
						choixDeplacement(JR,Mj,donnees);
					}
				}
			}
			else //j=1
			{
				System.out.println("choisir le deplacement en tapant:" );
				System.out.println("2  pour aller en bas" );
				System.out.println("3  pour aller a droite" );
				dir=Integer.valueOf(flux.readLine()).intValue();
				switch(dir)
				{
					case 2:
					{
						deplacementBas(iactif,jactif,factif,JR,Mj,donnees);
					}
					break;
					case 3:
					{
						deplacementDroite(iactif,jactif,factif,JR,Mj,donnees);
					}
					break;
					default: System.out.println("saisie erronee ");
					choixDeplacement(JR,Mj,donnees);
				}
			}
		}
	}

	
//=======================================================================================================				

	private static void disparitionEre (int joueur,String [] JR, Tdonnee[] donnees ) throws IOException // fct compiler
	{
		donnees[joueur].nbrere +=  -1;
		if (donnees[joueur].nbrere<=1)
		{
			Partie=false;
			System.out.println(JR[1-joueur] + " a gagne au " + nbrTour + "ieme tour");
		}
	}

//=======================================================================================================				

	private static void elim (int i,int j, int m, String []JR,String [][][]M,Tdonnee[] donnees) throws IOException // fct compiler
	{
		int jrPion;
		jrPion=0;
		if (M[m][i][j].substring(0,1).equals("N"))
		{
			jrPion=1;
		}
		M[m][i][j]="0";
		if (m==0)
		{
			donnees[jrPion].nbrpa += -1;
			if (donnees[jrPion].nbrpa ==0)
			{
				disparitionEre(jrPion,JR,donnees);
			}
		}
		else
		{
			if (m==1)
			{
				donnees[jrPion].nbrpr += -1;
				if (donnees[jrPion].nbrpr ==0)
				{
					disparitionEre(jrPion,JR,donnees);
				}
			}
			else
			{
				donnees[jrPion].nbrfu += -1;
				if (donnees[jrPion].nbrfu ==0)
				{
					disparitionEre(jrPion,JR,donnees);
				}
			}
		}
	}
	
	
//=======================================================================================================
	
	private static void deplacementHaut(int i, int j,int m,String [] JR,String[][][]M,Tdonnee[] donnees) throws IOException //fct compiler 
	{
		String c;
		if (M[m][i-1][j].equals("0"))
		{
			c=M[m][i-1][j];
			M[m][i-1][j]=M[m][i][j];
			M[m][i][j] =c;
			if (i==iactif)
			{
				iactif+=-1;
			}
		}
		else
		{
			collisionHaut(i,j,m,JR,M,donnees);
		}
	}
	
//=======================================================================================================
	
	private static void collisionHaut(int i, int j,int m,String [] JR, String [][][]M,Tdonnee[] donnees) throws IOException //fct compiler 
	{
		if (M[m][i-1][j].equals("m"))
		{
			elim (i,j,m,JR,M,donnees);
		}
		else
		{
			if ((M[m][i-1][j].substring(0,1)).equals(M[m][i][j].substring(0,1)))
			{
				System.out.println(" tu as cree un paradoxe");
				elim (i-1,j,m,JR,M,donnees);
				elim (i,j,m,JR,M,donnees);
			}
			else
			{
				deplacementHaut(i-1,j,m,JR,M,donnees );
				deplacementHaut(i,j,m,JR,M,donnees);
			}
		}
	}
	
//=======================================================================================================
	
	private static void deplacementBas(int i, int j,int m,String [] JR,String[][][]M,Tdonnee[] donnees) throws IOException //fct compiler 
	{
		String c;
		if (M[m][i+1][j].equals("0"))
		{
			c=M[m][i+1][j];
			M[m][i+1][j]=M[m][i][j];
			M[m][i][j] =c;
			if (i==iactif)
			{
				iactif+=1;
			}
		}
		else
		{
			collisionBas(i,j,m,JR,M,donnees);	
		}
	}
	
//=======================================================================================================
	
	private static void collisionBas(int i, int j,int m,String [] JR,String[][][]M,Tdonnee[] donnees ) throws IOException //fct compiler
	{
		if (M[m][i+1][j].equals("m"))
		{
			elim (i,j,m,JR,M,donnees);
		}
		else
		{
			if ((M[m][i+1][j].substring(0,1)).equals(M[m][i][j].substring(0,1)))
			{
				System.out.println(" tu as cree un paradoxe");
				elim (i+1,j,m,JR,M,donnees);
				elim (i,j,m,JR,M,donnees);
			}
			else
			{
				deplacementBas(i+1,j,m,JR,M,donnees);
				deplacementBas(i,j,m,JR,M,donnees);
			}
		}
	}
	
//=======================================================================================================
	
	private static void deplacementGauche(int i, int j,int m,String [] JR, String[][][]M,Tdonnee[] donnees) throws IOException //fct compiler
	{
		String c;
		if (M[m][i][j-1].equals("0"))
		{
			c=M[m][i][j-1];
			M[m][i][j-1]=M[m][i][j];
			M[m][i][j] =c;
			if (j==jactif)
			{
				jactif+=-1;
			}
		}
		else
		{
			collisionGauche(i,j,m,JR,M,donnees);
		}
	}
	
//=======================================================================================================
	
	private static void collisionGauche(int i, int j,int m,String [] JR, String[][][]M,Tdonnee[] donnees ) throws IOException //fct compiler
	{
		if (M[m][i][j-1].equals("m"))
		{
			elim (i,j,m,JR,M,donnees);
		}
		else
		{
			if ((M[m][i][j-1].substring(0,1)).equals(M[m][i][j].substring(0,1)))
			{
				System.out.println(" tu as cree un paradoxe");
				elim (i,j-1,m,JR,M,donnees);
				elim (i,j,m,JR,M,donnees);
			}
			else
			{
				deplacementGauche(i,j-1,m,JR,M,donnees);
				deplacementGauche(i,j,m,JR,M,donnees);
			}
		}
	}

//=======================================================================================================
	
	private static void deplacementDroite(int i, int j,int m,String [] JR, String[][][]M,Tdonnee[] donnees) throws IOException //fct compiler
	{
		String c;
		if (M[m][i][j+1].equals("0"))
		{
			c=M[m][i][j+1];
			M[m][i][j+1]=M[m][i][j];
			M[m][i][j] =c;
			if (j==jactif)
			{
				jactif+=1;
			}
		}
		else
		{
			collisionDroite(i,j,m,JR,M,donnees);
		}
	}
	
//=======================================================================================================
	
	private static void collisionDroite(int i, int j,int m,String [] JR, String[][][]M,Tdonnee[] donnees) throws IOException //fct compiler
	{
		if (M[m][i][j+1].equals("m"))
		{
			elim (i,j,m,JR,M,donnees);
		}
		else
		{
			if ((M[m][i][j+1].substring(0,1)).equals(M[m][i][j].substring(0,1)))
			{
				System.out.println(" tu as cree un paradoxe");
				elim (i,j+1,m,JR,M,donnees);
				elim (i,j,m,JR,M,donnees);
			}
			else
			{
				deplacementDroite(i,j+1,m,JR,M,donnees);
				deplacementDroite(i,j,m,JR,M,donnees);
			}
		}
	}

//=======================================================================================================				

	private static void choixTp(int m, int i, int j,String [] JR,String[][][]M,Tdonnee[] donnees,boolean cond1,boolean cond2) throws IOException// fct compile
	{
		int dir;
		if ((cond1) && (cond2))
		{
			System.out.println("choisir vers quelle ere voyager");
			System.out.println("1 pour aller dans l'ere precedante");
			System.out.println("2 pour aller dans l'ere suivante");
			dir=Integer.valueOf(flux.readLine()).intValue();
			switch(dir)
			{
				case 1 :
				{
					TP(m,i,j,-1,JR,M,donnees);
				}
				break;
				case 2 :
				{
					TP(m,i,j,1,JR,M,donnees);
				}
				break;
				default: System.out.println("saisie erronee ");
				choixTp(m,i,j,JR,M,donnees,cond1,cond2);
			}
		}
		else
		{
			if (cond1)
			{
				TP(m,i,j,1,JR,M,donnees);
			}
			else
			{
				TP(m,i,j,-1,JR,M,donnees);
			}
		}
	}
				
//=======================================================================================================				
	
	
	private static void TP(int m, int i, int j, int sens,String [] JR, String[][][]M,Tdonnee[] donnees) throws IOException //fct compile
	{
		M[m+sens][i][j]=M[m][i][j];
		if (sens==-1)
		{
			M[m][i][j]=M[m][i][j].substring(0,1) + (7-donnees[jr].res);
			donnees[jr].res+= -1;
			if (m==1)
			{
				if (donnees[jr].nbrpa==0)
				{
					donnees[jr].nbrere+=1;
				}
				donnees[jr].nbrpa+=1;
			}
			else //m==2
			{
				if (donnees[jr].nbrpr==0)
				{
					donnees[jr].nbrere+=1;
				}
				donnees[jr].nbrpr+=1;
			}
		}
		else
		{
			M[m][i][j]="0";
			if (m==1)
			{
				if (donnees[jr].nbrfu==0)
				{
					donnees[jr].nbrere+=1;
				}
				donnees[jr].nbrpr+=-1;
				donnees[jr].nbrfu+=1;
				if (donnees[jr].nbrpr==0)
				{
					disparitionEre(jr,JR,donnees);
				}
			}
			else //m==0
			{
				if (donnees[jr].nbrpr==0)
				{
					donnees[jr].nbrere+=1;
				}
				donnees[jr].nbrpa+=-1;
				donnees[jr].nbrpr+=1;
				if (donnees[jr].nbrpa==0)
				{
					disparitionEre(jr,JR,donnees);
				}
			}
		}
		factif += sens;
	}

//=======================================================================================================				

	
	private static void affichageMatrice(String[][][]M) throws IOException //fct compile 
	{
		int m,i,j;
		for (m=0; m<3;m++)
		{
			for (i=0; i<6; i++)
			{
				for (j=0; j<6; j++)
				{
					System.out.printf("%-5s|", M[m][i][j]); 
				}
				System.out.println();
				System.out.println("-----------------------------------------");

			
			}
			System.out.println( "\n ===============================\n");
		}
	}
	
//=======================================================================================================				

	
	private static void affichage( String[] JR, String [][][] Mj, Tdonnee[] donnees) throws IOException
	{
		int k;
		System.out.println( "\n ===============================\n===============================\n");
		String[]Couleur=new String [2];
		for (k=0;k<2;k+=1)
		{
			System.out.println("DONNEES "+JR[k]+" : reserve: "+ donnees[k].res + "    focus: " + donnees[k].foc );
		}
		System.out.println("Tour de jeu numero "+nbrTour);
		affichageMatrice(Mj);
	}
	
//=======================================================================================================	

	private static void tourDeJeu (int[][] LP, String[] JR, String [][][] Mj,String [][][] Ms, Tdonnee[] donnees) throws IOException 
	{	
		int action,f,z,i,j,k,nDansEre,pactif;
		boolean cond1,cond2;
		//affichage(JR,Mj,donnees);
		traceJeu(Mj,donnees);
		sauvTour(donnees,Mj,Ms);
		f=donnees[jr].foc;
		nDansEre=nbrDansEre(jr,f,donnees);
		if (nDansEre>0)
		{
			factif=donnees[jr].foc;
			pactif=choixPion(nDansEre,f,LP,JR,Mj,Ms); 
			iactif=LP[pactif][0];
			jactif=LP[pactif][1];
			System.out.println( "Votre pion "+ JR[jr]+" numero "+Mj[factif][iactif][jactif].substring(1,2) + " est defini comme votre pion actif " );
			action=2; //action restante
			while ((action>0) && (Mj[factif][iactif][jactif]!="0")&&(Partie))  
			{
				z=3-action;//numero de l action en cours
				if (z==2)
				{
					System.out.println("Le pion actif est le pion " + JR[jr]+" numero "+Mj[factif][iactif][jactif].substring(1,2));
				}
				
				if (factif!=2)
				{
					cond1=((Mj[factif+1][iactif][jactif]=="0")); //Tp dans ere suivante possible
				}
				else
				{
					cond1=false;
				}
				if (factif!=0)
				{
					cond2=((Mj[factif-1][iactif][jactif] =="0")&& (donnees[jr].res>0) );//Tp ds ere precedante possible 
				}
				else
				{
					cond2=false;
				}
				if ((cond1) || (cond2))
				{
					System.out.println("choisir l'action"+ z);
					System.out.println("1 pour realiser un mouvement" );  
					System.out.println("2 pour realiser un tp" );
					choixAction(factif,iactif,jactif,JR,Mj,donnees,cond1,cond2);  
				}
				else
				{
					choixDeplacement(JR,Mj,donnees);
				}
				action=action-1;
				//affichage(JR,Mj,donnees);
				traceJeu(Mj,donnees);
			}
		}
		else
		{
			System.out.println("vous ne possedez aucun pion dans cette ere donc");
		}
		if (Partie)
		{
			choixFocus(donnees[jr].foc,donnees);
			//affichage(JR,Mj,donnees);
			traceJeu(Mj,donnees);
			finTour(LP,Mj,Ms,donnees,JR);
		}
	}
	
	
//=======================================================================================================	
	
	private static void ajouterLigne (String sauvegarde, String Ligne) throws IOException
	{
		
		PrintWriter fichier = new PrintWriter(new FileWriter(sauvegarde,true)); //Association fichier et direct.dat
		
		fichier.println(Ligne); // System.out.println
		fichier.close();
		
		
	}
//=======================================================================================================
	
	private static String lireLigne (String fichierPhysic, int ligne) throws IOException
	{
		String ligneLue;
		int i;
		RandomAccessFile fichier = new RandomAccessFile(fichierPhysic,"r");
		fichier.seek(0);
		ligneLue = fichier.readLine();
		i=1;
		while ((ligneLue != null)&&(i<ligne))
		{
			ligneLue = fichier.readLine();
			i=i+1;
		}
		if (ligneLue==null)
		{
			ligneLue="mauvais numero";
		}
		fichier.close();
		return(ligneLue);
	}
	
//=======================================================================================================
	
	private static String[][] lireMatrice(String fichier, String epoque )throws IOException
	{
		String[][]M = new String[6][6];
		File file = new File(fichier);
		FileReader fileReader = new FileReader(file ); // permet de lire le fichier
		char[] Stock = new char[1024];
		int i,n,j; // n stock le nombre de caractere qui sera lu a chaque iterations
		String ligne;
		ligne =""; //stock les lignes lues a partir du fichier
		i=0; // indice pour remplir la matrice, ajouter chaque ligne
		
		while ((n = fileReader.read(Stock))!=-1)// je lis les donnes du fichier  lorsqu'il n y a plus de caractere a lire dans le fichier read() retourne -1
		{
			for (j=0;j<n;j++)// j ajoute les caractere mu dans Stock, pour former la ligne
			{
				ligne+=Stock[j];
				if (Stock[j]=='\n') //si je saute une ligne alors il n y a plus de caractere a lire
				{
					if ( ligne.contains(epoque))// je verifie l epoque que tu veux
					{
						i=0; // je reinitialise i pour ensuite remplir la matrice a aprtir de cette ligne
					}
					if (i<6 && !ligne.startsWith("Etat de la matrice")&&!ligne.contains("---")) // ca permet de prendre juste les chiffres
					{
						M[i] = ligne.split("\\s+"); // je rempli la matrice que je vais renvoyer, split ca permet de diviser la ligne lu en espaces
						i+=1;
					}
					ligne =""; // permet de lire la ligne suivante
				}
			}
		}
		fileReader.close();
		return (M );
	}
	
//=======================================================================================================
	
	private static void recupDonnees(String Sauvegarde, Tdonnee[] donnees) throws IOException
	{
		FileReader fileReader = new FileReader(new File( Sauvegarde));
		char [] Stock = new char[1024];
		String ligne;
		ligne ="";
		int n,j, cjoueur,reserve, focus, pionPasse, pionPresent, pionFutur, ere;
		
		cjoueur =-1;
		
		
		while ((n = fileReader.read(Stock))!=-1)
		{
			for (j=0; j<n;j++)
			{
				ligne+=Stock[j];
				if ( Stock[j] =='\n')
				{
					if (ligne.contains("joueur 0"))
					{
						cjoueur = 0;
					}
					else
					{
						if ( ligne.contains("joueur 1"))
						{
							cjoueur = 1;
						}
						else
						{
							if (ligne.contains("Reserve"))
							{
								if (cjoueur!=-1)
								{
									reserve = Integer.parseInt(ligne.split(":")[1].trim());// en gros ca permet de prendre les chiffres qui sont apres : et de les convertir en int plus precisement le 1 indique que l on prend les caracteres apres :
									if (cjoueur ==0)
									{
										donnees[0].res=reserve;
									}
									else
									{
										donnees[1].res = reserve;
									}
								}
							}
							else
							{
								if (ligne.contains("Focus"))
								{
									if (cjoueur!=-1)
									{
										focus = Integer.parseInt(ligne.split(":")[1].trim());
										if (cjoueur==0)
										{
											donnees[0].foc = focus;
										}
										else
										{
											donnees[1].foc = focus;
										}
									}
								}
								else
								{
									if (ligne.contains("Nombre de pions dans le passe"))
									{
										if (cjoueur !=-1)
										{
											pionPasse = Integer.parseInt(ligne.split(":")[1].trim());
											if (cjoueur==0)
											{
												donnees[0].nbrpas = pionPasse;
											}
											else
											{
												donnees[1].nbrpas = pionPasse;
											}
										}
									}
									else
									{
										if (ligne.contains("Nombre de pions dans le present"))
										{
											if (cjoueur !=-1)
											{
												pionPresent = Integer.parseInt(ligne.split(":")[1].trim());
												if (cjoueur==0)
												{
													donnees[0].nbrprs = pionPresent;
												}
												else
												{
													donnees[1].nbrprs = pionPresent;
												}
											}
										}
										else
										{
											if (ligne.contains("Nombre de pions dans le futur"))
											{
												if (cjoueur !=-1)
												{
													pionFutur = Integer.parseInt(ligne.split(":")[1].trim());
													if (cjoueur==0)
													{
														donnees[0].nbrfus = pionFutur;
													}
													else
													{
														donnees[1].nbrfus = pionFutur;
													}
												}
											}
											else
											{
												if (ligne.contains("Nombre d ere possedes"))
												{
													if (cjoueur !=-1)
													{
														ere = Integer.parseInt(ligne.split(":")[1].trim());
														if (cjoueur==0)
														{
															donnees[0].nbrere = ere;
														}
														else
														{
															donnees[1].nbrere = ere;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					ligne ="";
				}
				
			}
			
		}
		
		fileReader.close();
	}
	
//=======================================================================================================
	
	private static void save (String Sauvegarde,String [][][]M, String NomInterieur, Tdonnee[]donnees) throws IOException
	{
		String caractere, ress,focs, nbrpas, nbrprs, nbrfus, nbreres,ligne;
		int m,i,k,j;
		
		File saveFile =new File(Sauvegarde);
		if (saveFile.exists())
		{								//on transforme le string en fichier
			saveFile.delete();
			ajouterLigne( Sauvegarde, NomInterieur);
			for (m=0;m<3;m++)
			{
				ajouterLigne(Sauvegarde, "Etat de la matrice"+(m==0 ? "Passe":m==1?"Present":"Futur")+" :");
				ajouterLigne(Sauvegarde,"----------------------------------------------");
				for (i=0;i<6;i++)
				{
					ligne = " " ;
					for(j=0;j<6;j++)
					{
						ligne += String.format("%-5s",M[m][i][j]); // j ai vu ca sur un site ca permet l'indentation 
						;
					}
					ajouterLigne(Sauvegarde, ligne.trim());
				}
				ajouterLigne(Sauvegarde,"----------------------------------------------");
				
			}  
			ajouterLigne (Sauvegarde, " le joueur 0 correspond au joueur Blanc");
			ajouterLigne (Sauvegarde, " le joueur 0 correspond au joueur Noir");
			for ( k = 0; k<2; k++)
			{
				
				
				ress = String.valueOf(donnees[k].ress);
				ajouterLigne(Sauvegarde, "Reserve "+k+" : "+ress);
				
				focs = String.valueOf(donnees[k].focs);
				ajouterLigne(Sauvegarde, "Focus  "+k+" : "+focs);
				
				nbrpas = String.valueOf(donnees[k].nbrpas);
				ajouterLigne(Sauvegarde, "Nombre de pions dans le passe "+k+" : "+nbrpas);
				
				nbrprs = String.valueOf(donnees[k].nbrprs);
				ajouterLigne(Sauvegarde, "Nombre de pions dans le present "+k+" : "+nbrprs);
				
				nbrfus = String.valueOf(donnees[k].nbrfus);
				ajouterLigne(Sauvegarde, "Nombre de pions dans le futur "+k+" : "+nbrfus);
				
				nbreres = String.valueOf(donnees[k].nbreres);
				ajouterLigne(Sauvegarde,"Nombre d ere possedes "+k+" : "  +nbreres);
				ajouterLigne(Sauvegarde,"");
			}
			
			
		}
		else
		{
			ajouterLigne( Sauvegarde, NomInterieur);
			for (m=0;m<3;m++)
			{
				ajouterLigne(Sauvegarde, "Etat de la matrice "+(m==0 ? "Passe":m==1?"Present":"Futur")+" :");
				ajouterLigne(Sauvegarde,"----------------------------------------------");
				for (i=0;i<6;i++)
				{
					ligne = " " ;
					for(j=0;j<6;j++)
					{
						ligne += String.format("%-5s",M[m][i][j]); // j ai vu ca sur un site ca permet l'indentation 
						;
					}
					ajouterLigne(Sauvegarde, ligne.trim());
				}
				ajouterLigne(Sauvegarde,"----------------------------------------------");
				
			}  
			
			ajouterLigne (Sauvegarde, " le joueur 0 correspond au joueur Blanc");
			ajouterLigne (Sauvegarde, " le joueur 0 correspond au joueur Noir");
			for ( k = 0; k<2; k++)
			{
				
				ress = String.valueOf(donnees[k].ress);
				ajouterLigne(Sauvegarde, "Reserve "+k+" : "+ress);
				
				focs = String.valueOf(donnees[k].focs);
				ajouterLigne(Sauvegarde, "Focus " +k+" : " +focs);
				
				nbrpas = String.valueOf(donnees[k].nbrpas);
				ajouterLigne(Sauvegarde, "Nombre de pions dans le passe "+k+" : "+nbrpas);
				
				nbrprs = String.valueOf(donnees[k].nbrprs);
				ajouterLigne(Sauvegarde, "Nombre de pions dans le present "+k+" : "+nbrprs);
				
				nbrfus = String.valueOf(donnees[k].nbrfus);
				ajouterLigne(Sauvegarde, "Nombre de pions dans le futur "+k+" : "+nbrfus);
				
				nbreres = String.valueOf(donnees[k].nbreres);
				ajouterLigne(Sauvegarde, "Nombre d eres possedes "+k+" : "+nbreres);
				ajouterLigne(Sauvegarde,"");
				
			}
		
		}
		
	}
//=======================================================================================================	

	private static void tracePion(String[][][] M,int m, int i, int j)throws IOException  // fonction compile 
	{
		boolean couleur;
		int x,y,dx,dy,lm,lc,esp;
		dx=25;
		dy=70;
		lm=25;
		lc=70;
		esp=430;
		String pion;
		pion=M[m][i][j];
		couleur=(pion.substring(0,1).equals("B"));
		if (couleur)
		{
			EcranGraphique.setColor(255,255,255);
		}
		else
		{
			EcranGraphique.setColor(0,0,0);
		}
		x=dx+lm-lc/2+j*lc+m*esp;
		y=dy+lm-lc/2+i*lc;
		EcranGraphique.fillCircle(x,y,(lc-10)/2);
		if (!couleur)
		{
			EcranGraphique.setColor(255,255,255);
		}
		else
		{
			EcranGraphique.setColor(0,0,0);
		}
		EcranGraphique.drawText(x-5,y+5,1,pion.substring(1,2));
	}

//=======================================================================================================	

	
	private static void traceJeu(String[][][] M,Tdonnee[] donnees)throws IOException
	{
		int k,p,decPlat,x,y,m,i,j;
		EcranGraphique.clear();
		int[][] lava=EcranGraphique.loadPNGFile("lave330.png");
		EcranGraphique.drawImage(25,70,lava);
		int[][] mur=EcranGraphique.loadPNGFile("mur330.png");
		EcranGraphique.drawImage(455,70,mur);
		int[][] elec=EcranGraphique.loadPNGFile("elec330.png");
		EcranGraphique.drawImage(885,70,elec);
		int[][] passe=EcranGraphique.loadPNGFile("passe110.png");
		EcranGraphique.drawImage(125,20,passe);
		int[][] present=EcranGraphique.loadPNGFile("present130.png");
		EcranGraphique.drawImage(555,20,present);
		int[][] futur=EcranGraphique.loadPNGFile("futur110.png");
		EcranGraphique.drawImage(985,20,futur);
		int[][] texte=EcranGraphique.loadPNGFile("texte.png");
		EcranGraphique.drawImage(1225,80,texte);
		EcranGraphique.drawText(1340,130,1,nbrTour);
		for (k=0;k<2;k+=1)
		{
			for (p=0;p<donnees[k].res;p+=1)
			{
				EcranGraphique.fillCircle(1435+20*p,304+k*54,8);
			}
		}
		if (jr==0)
		{
			int[][] blanc=EcranGraphique.loadPNGFile("Blanc.png");
			EcranGraphique.drawImage(1380,168,blanc);
		}
		else
		{
			int[][] noir=EcranGraphique.loadPNGFile("Noir.png");
			EcranGraphique.drawImage(1380,166,noir);
		}
		EcranGraphique.flush();
		EcranGraphique.setColor(127,127,127);
		EcranGraphique.fillRect(50,95,280,280);
		EcranGraphique.fillRect(480,95,280,280);
		EcranGraphique.fillRect(910,95,280,280);
		EcranGraphique.fillRect(25,415,1190,40);
		EcranGraphique.setColor(242,242,242);
		int[][] focusB=EcranGraphique.loadPNGFile("focusBlanc.png");
		EcranGraphique.drawImage(138+donnees[0].foc*430,418,focusB);
		int[][] focusN=EcranGraphique.loadPNGFile("focusNoir.png");
		EcranGraphique.drawImage(208+donnees[1].foc*430,418,focusN);
		for (k=1;k<4;k+=1)
		{
			for (p=0;p<3;p+=1)
			{
				decPlat=p*430;
				x=50+70*k+decPlat;
				EcranGraphique.drawLine(x,95,x,375);
				y=95+70*k;
				EcranGraphique.drawLine(50+decPlat,y,330+decPlat,y);
			}
		}
		EcranGraphique.flush();
		for (m=0;m<3;m+=1)
		{
			for (i=1;i<5;i+=1)
			{
				for (j=1;j<5;j+=1)
				{
					if (!(M[m][i][j].equals("0")))
					{
						tracePion(M,m,i,j) ;
					}
				}
			}
		}
		EcranGraphique.flush();
	}	
	
//=======================================================================================================
	
	private static void menu(int[][] LP, String[] JR, String [][][] Mj,String [][][] Ms, Tdonnee[] donnees)throws IOException
	{
		int choix ;
		String fichier;
		affichageMenu();
		System.out.println("taper 1 pour demarrer une nouvelle partie");
		System.out.println("2 pour charger une ancienne partie");
		System.out.println("3 pour reinitialiser les donnees");
		choix=Integer.valueOf(flux.readLine()).intValue();
		switch(choix)
		{
			case 1 :
			{
				Mj = matInit();
				jr=0;
				nbrTour=1;
				initialisationTDonnees(donnees,4,4);
				jeu(LP,JR,Mj,Ms,donnees);
			}
			break;
			case 2 :
			{
				fichier=choixSauvegarde(0,LP,JR,Mj,Ms,donnees);
				System.out.println("test de fichier"+lireLigne(fichier,1)+"!");
				while (lireLigne(fichier,1)=="vide");
				{
					System.out.println("la partie est vide");
					fichier=choixSauvegarde(0,LP,JR,Mj,Ms,donnees);
				}
				recupDonnees(fichier,donnees);
				jeu(LP,JR,Mj,Ms,donnees);
			}
			case 3 :
			{
				reinitialisation(LP,JR,Mj,Ms,donnees);
			}
			break;
			default: System.out.println("saisie erronee");
			menu(LP,JR,Mj,Ms,donnees);
		}
	}
		
//=======================================================================================================
	
	private static void affichageMenu()throws IOException
	{
		EcranGraphique.clear();
		int[][] titre=EcranGraphique.loadPNGFile("titre1000x136.png");
		EcranGraphique.drawImage(250,10,titre);
		int[][] boite=EcranGraphique.loadPNGFile("boite200x256.png");
		EcranGraphique.drawImage(125,150,boite);
		EcranGraphique.setColor(200,200,200);
		int[][] iello=EcranGraphique.loadPNGFile("iello150x97.png");
		EcranGraphique.drawImage(1255,230,iello);
		EcranGraphique.drawString(1250,370,EcranGraphique.COLABA8x13,"Projet d'algorithmique ");
		EcranGraphique.drawString(1250,387,EcranGraphique.COLABA8x13,"ISIFC");
		EcranGraphique.drawString(1250,404,EcranGraphique.COLABA8x13,"d'apres le jeu de");
		EcranGraphique.drawString(1250,421,EcranGraphique.COLABA8x13,"PETER C. HAYWARD ");
		EcranGraphique.drawString(1250,438,EcranGraphique.COLABA8x13,"par Castell Lenny");
		EcranGraphique.drawText(1250,455,EcranGraphique.COLABA8x13,"et Blaison Matheo");
		EcranGraphique.fillRect(425,160,700,50);
		EcranGraphique.fillRect(425,265,700,50);
		EcranGraphique.fillRect(425,370,700,50);
		EcranGraphique.fillCircle(425,185,24);
		EcranGraphique.fillCircle(425,290,24);
		EcranGraphique.fillCircle(425,395,24);
		EcranGraphique.fillCircle(1125,185,24);
		EcranGraphique.fillCircle(1125,290,24);
		EcranGraphique.fillCircle(1125,395,24);
		int[][] nouv=EcranGraphique.loadPNGFile("new343x48.png");
		EcranGraphique.drawImage(450,161,nouv);
		int[][] sauv=EcranGraphique.loadPNGFile("save635x48.png");
		EcranGraphique.drawImage(450,266,sauv);
		int[][] reset=EcranGraphique.loadPNGFile("reset322x48.png");
		EcranGraphique.drawImage(450,371,reset);
		EcranGraphique.flush();
	}

//=======================================================================================================
	
	private static void affichageMenuSauv()throws IOException
	{
		int k,y;
		EcranGraphique.clear();
		int[][] titre=EcranGraphique.loadPNGFile("titre1000x136.png");
		EcranGraphique.drawImage(250,10,titre);
		int[][] boite=EcranGraphique.loadPNGFile("boite200x256.png");
		EcranGraphique.drawImage(125,150,boite);
		EcranGraphique.setColor(200,200,200);
		int[][] iello=EcranGraphique.loadPNGFile("iello150x97.png");
		EcranGraphique.drawImage(1255,230,iello);
		EcranGraphique.drawString(1250,370,EcranGraphique.COLABA8x13,"Projet d'algorithmique ");
		EcranGraphique.drawString(1250,387,EcranGraphique.COLABA8x13,"ISIFC");
		EcranGraphique.drawString(1250,404,EcranGraphique.COLABA8x13,"d'apres le jeu de");
		EcranGraphique.drawString(1250,421,EcranGraphique.COLABA8x13,"PETER C. HAYWARD ");
		EcranGraphique.drawString(1250,438,EcranGraphique.COLABA8x13,"par Castell Lenny");
		EcranGraphique.drawText(1250,455,EcranGraphique.COLABA8x13,"et Blaison Matheo");
		EcranGraphique.fillRect(425,150,700,50);
		EcranGraphique.fillRect(425,230,700,50);
		EcranGraphique.fillRect(425,310,700,50);
		EcranGraphique.fillRect(425,390,700,50);
		EcranGraphique.fillCircle(425,175,24);
		EcranGraphique.fillCircle(425,255,24);
		EcranGraphique.fillCircle(425,335,24);
		EcranGraphique.fillCircle(425,415,24);
		EcranGraphique.fillCircle(1125,175,24);
		EcranGraphique.fillCircle(1125,255,24);
		EcranGraphique.fillCircle(1125,335,24);
		EcranGraphique.fillCircle(1125,415,24);
		EcranGraphique.setColor(0,0,0);
		y=181;
		for (k=1;k<4;k+=1)
		{
			//nom= recup nom de fichier interne("save"+k)
			EcranGraphique.drawString(450,y,EcranGraphique.COLABA8x13,k+".  "+lireLigne("save"+k +".dat",1));
			y+=80;
		}
		EcranGraphique.drawString(450,y,EcranGraphique.COLABA8x13,"4.  annuler");
		EcranGraphique.flush();
	}
	
//=======================================================================================================

	private static String choixSauvegarde(int fct,int[][] LP, String[] JR, String [][][] Mj,String [][][] Ms, Tdonnee[] donnees)throws IOException
	{
		int choix ;
		String sauvegarde;
		sauvegarde="save1.dat";
		affichageMenuSauv();
		System.out.println("taper 1 pour " + lireLigne("save1.dat",1));
		System.out.println("2 pour "+ lireLigne("save2.dat",1));
		System.out.println("3 pour "+ lireLigne("save3.dat",1));
		System.out.println("4 pour annuler");
		choix=Integer.valueOf(flux.readLine()).intValue();
		switch(choix)
		{
			case 1 :
			{
			}
			break;
			case 2 :
			{
				sauvegarde="save2.dat";
			}
			break;
			case 3 :
			{
				sauvegarde="save3.dat";
			}
			break;
			case 4:
			{
				if (fct==0)
				{
					menu(LP,JR,Mj,Ms,donnees);
				}
				else
				{
					traceJeu(Mj,donnees);
					finTour(LP,Mj,Ms,donnees,JR);
				}
			}
			break;
			default: System.out.println("saisie erronee");
			choixSauvegarde(fct,LP,JR,Mj,Ms,donnees);
		}
		return (sauvegarde);
	}
		
//=======================================================================================================

	private static void jeu(int[][] LP, String[] JR, String [][][] Mj,String [][][] Ms, Tdonnee[] donnees)throws IOException
	{
		Partie = true;
		while (Partie)
		{
			tourDeJeu(LP,JR,Mj,Ms,donnees);
		}
		menu(LP,JR,Mj,Ms,donnees);
	}
	
//=======================================================================================================

	private static void reinitialisation(int[][] LP, String[] JR, String [][][] Mj,String [][][] Ms, Tdonnee[] donnees)throws IOException
	{
		int choix;
		affichageReinitialisation();
		System.out.println("attention vous allez reinitialiser le jeu et toutes vos donnees sauvegardees seront supprimees");
		System.out.println("tapez 1 pour confirmer");
		System.out.println("tapez 2 pour annuler");
		choix=Integer.valueOf(flux.readLine()).intValue();
		switch(choix)
		{
			case 1 :
			{
				Mj = matInit();
				jr=0;
				nbrTour=1;
				initialisationTDonnees(donnees,4,4);
				save("save1.dat",Mj, "vide",donnees);
				save("save2.dat",Mj, "vide",donnees);
				save("save3.dat",Mj, "vide",donnees);
				menu(LP,JR,Mj,Ms,donnees);
			}
			break;
			case 2 :
			{
				menu(LP,JR,Mj,Ms,donnees);
			}
			break;
			default: System.out.println("saisie erronee");
			reinitialisation(LP,JR,Mj,Ms,donnees);
		}
	}
	
//=======================================================================================================

	private static void affichageReinitialisation()throws IOException
	{
		EcranGraphique.clear();
		int[][] titre=EcranGraphique.loadPNGFile("titre1000x136.png");
		EcranGraphique.drawImage(250,10,titre);
		int[][] boite=EcranGraphique.loadPNGFile("boite200x256.png");
		EcranGraphique.drawImage(125,150,boite);
		EcranGraphique.setColor(200,200,200);
		int[][] iello=EcranGraphique.loadPNGFile("iello150x97.png");
		EcranGraphique.drawImage(1255,230,iello);
		EcranGraphique.drawString(1250,370,EcranGraphique.COLABA8x13,"Projet d'algorithmique ");
		EcranGraphique.drawString(1250,387,EcranGraphique.COLABA8x13,"ISIFC");
		EcranGraphique.drawString(1250,404,EcranGraphique.COLABA8x13,"d'apres le jeu de");
		EcranGraphique.drawString(1250,421,EcranGraphique.COLABA8x13,"PETER C. HAYWARD ");
		EcranGraphique.drawString(1250,438,EcranGraphique.COLABA8x13,"par Castell Lenny");
		EcranGraphique.drawText(1250,455,EcranGraphique.COLABA8x13,"et Blaison Matheo");
		EcranGraphique.fillRect(425,175,700,50);
		EcranGraphique.fillRect(425,300,700,50);
		EcranGraphique.fillCircle(425,200,24);
		EcranGraphique.fillCircle(425,325,24);
		EcranGraphique.fillCircle(1125,200,24);
		EcranGraphique.fillCircle(1125,325,24);
		int[][] confReset=EcranGraphique.loadPNGFile("confreset569x48.png");
		EcranGraphique.drawImage(450,176,confReset);
		int[][] annReset=EcranGraphique.loadPNGFile("annReset541x48.png");
		EcranGraphique.drawImage(450,301,annReset);
		EcranGraphique.flush();
	}
	

//=======================================================================================================			
//=======================================================================================================			
//=======================================================================================================			
//=======================================================================================================			

	public static void main (String[] arg) throws IOException
	{
		int k;
		EcranGraphique.init(0,0,1550,550,1520,470,"Demain tu m'as tue");
		String[][][]Mj=new String [3][6][6];
		String[][][]Ms=new String [3][6][6];
		int [][] LP = new int [8][2];
		String [] JR = new String [2]; 
		JR[0]="Blanc";
		JR[1]="Noir";
		Tdonnee donnees[]=new Tdonnee[12];
		for (k=0;k<12;k+=1)
		{
			donnees[k]=new Tdonnee();
		}
		menu(LP,JR,Mj,Ms,donnees);
	}
}

import java.io.*;

public class brouillon_choix_mot  
{
	static BufferedReader flux = new BufferedReader(new InputStreamReader(System.in));
	

	private static char[] Choixmot(int niveau, String fichierPhysic) throws IOException
	{
		int i, x;
		String ligne;
		char[] mot_devine = new char[ligne.length];
		
		
		// creer fichier facile, moyen, difficile
		x = (int)(Math.random()*30); // on tire au hasard un nombre entre 0 et 30, se sera la ligne ou on ira chercher le mot dans le fichier
		
		if (niveau == 1)
		{
			fichierPhysic = 'facile.dat'; //si le joueur choisit le niveau 1, alors on utilise le fichier facile
		}
		if (niveau == 2)
		{
			fichierPhysic ='moyen.dat';
		}
		else
		{
			fichierPhysic = 'difficile.dat';
		}
		
		i = 0; 
		
		
		while (i<x) //tant qu'on est pas � la bonne ligne tiree au sort, on descend dans le fichier jusqu'a la bonne ligne dont on garde le mot
		{
			ligne = fichierPhysic.readLine();
		}
		
		
		mot_devine = ligne.toCharArray(); //on transforme le mot en tableau de char, on utilise le fichier jeu.2 pour nous aider 
		
		return(mot_devine)
	}
	
	
	private static void afficherContenu (String fichierPhysic) throws IOException
	{
		String ligneLue;
		int i;
		
		//tmp pour tester l'existence
		File temp = new File(fichierPhysic);
		
		if (temp.length()==0) //fichier n'existe pas
		{
			System.out.println("vide");
		}
		else
		{
			RandomAccessFile fichier =  new RandomAccessFile(fichierPhysic,"r"); // association fichier direct.dat en lecture
			
			
			fichier.seek(0);// place en debut de fichier
			
			i=1;
			ligneLue = fichier.readLine(); //flux.readLine()
			while (ligneLue != null)
			{
				System.out.println("ligne "+i+" : "+ligneLue);
				//interprete(ligneLue);
				// c'est ici que vous ferez la fonction qui recupere sur votre
				//ligne en String les differents champs
				ligneLue = fichier.readLine();
				i=i+1;
			}
			fichier.close();
		}
	}
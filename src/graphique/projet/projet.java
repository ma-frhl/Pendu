import java.io.*;

public class projet

{
	static BufferedReader flux=new BufferedReader(new InputStreamReader(System.in));	
	public final static int N = 11;     //creation de la constante (nombre d'essai avant de perdre)
	public static final char[] alph = {'a','b','c','d','e','f','g','h','i', 'j', 'k', 'l', 'm', 'n', 'o', 'p','q','r','s','t','u','v','w','x','y','z'};
	
		
	private static char[] ChoixMot(int niveau, String fichierPhysic) throws IOException
	{
		int i, x;
		String ligne;
		char[] mot_devine = new char[ligne.length]
		
		
		// creer fichier facile, moyen, difficile
		x = (int)(Math.random()*30); // on tire au hasard un nombre entre 0 et 30, se sera la ligne ou on ira chercher le mot dans le fichier
		
		if (niveau == 1)
		{
			fichierPhysic = 'facile.dat';
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
		
		
		while (i<x)
		{
			ligne = fichierPhysic.readLine();
		}
		
		
		mot_devine = ligne.toCharArray();
		
		return(mot_devine)
	}
	
	
	
	
	
		
	private static int selectionLettres()  // renvoie x = le nombre d'erreur (nbre de lettres donnees qui ne sont pas dans le mot) ou -1 si 0 erreur  
	{
		int n_err, n_m, i, n, j, indice;
		char l; 
		boolean x;   // creer pour la condition du premier while
		
		x = true;
		indice = 0;
		
		char[] mot = new char [ChoixMot().length];// helpppp comment creer un tableau sans preciser la taille
		char[] joueur = new char [ChoixMot().length];		//tableau que voit le joueur pendant sa partie, les lettres justes sont affichees, des 0 la ou la lettre n'est pas connu, de la taille du mot
		boolean[] erreur = new boolean [alph.length];   //tableau de boolean qui est de meme taille de alph, ex: sa case 4 vaut  True si lettre deja mise/proposee et False si non
		
		mot = ChoixMot();	// recupere le mot de la fonction ChoixMot (qui est mis sous forme de tableau de caractere)
		n_m = mot.length;			// aussi egale a la taille du tableau joueur
		n_err = erreur.length;	
		
		
		
		n = 11;   //nombre d'erreurs max
		
		for (i=0; i<n_err; i+=1)
		{
			erreur[i] = false;
		}
		
		
		
		
		for (i=0; i<n_m; i+=1)  // met des 0 dans toutes les cases du tableau joueur
		{
			joueur[i] = '0';
		}
		
		
		
		
		while ((n>0)||(Arrays.equals(mot, joueur)==x))
		{
			System.out.println("Saisir une lettre");   //penser a essayer en majuscule et minuscule pour voir si les deux marchent
			l = flux.readLine().charAt(0);
			
			for (j=0; j<n_m; j+=1)     // si la lettre n'a pas deja ete donnee, il cherche dans le mot si elle y est, et si elle y est il la met dans le tableau joueur, sinon il compte une erreur
			{
				
				if (erreur[j] == false)
				{
					if (mot[j] == l)
					{
						indice = j;
						joueur[j] = l;
					}
					else
					{
					n -= 1;
					}
				}
			}
					
			
			for(i=0; i<n_err; i+=1)    // permet de dire a deja ete mise ou non, true si deja mise, false sinon, et on met a true en rentrant la lettre si elle n'avait pas deja ete rentree avant
			{
				if (alph[i]==l)
				{
					if (erreur[i] == true)
					{
						System.out.println("Fartasse tu l'as deja rentree celle la de lettre");
					}
					else
					{
						erreur[i] = true;
					}
				}
			}
		}	
					
			
			
		
	return (n);
	}
}
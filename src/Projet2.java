import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;

public class Projet2

{
	static BufferedReader flux = new BufferedReader(new InputStreamReader(System.in));
	public final static int N = 11; // creation de la constante (nombre d'essai avant de perdre)
	public static final char[] alph = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	// Get the project path for .dat files referencement
	public static String getProjectPath = Paths.get("").toAbsolutePath().toString();
	public static final String PATH = getProjectPath + "\\src\\";

	private static char[] ChoixMot(int niveau) throws IOException // pourquoi on avait mis fichierPhysic en entree??? (corriger prog principal si il y est vraiment)
	{
		int i, x;
		String fichierPhysic;
		String ligne = "";
		char[] mot_devine = new char[ligne.length()];
		
		if (niveau == 1)
		{
			fichierPhysic = "facile.dat";
		}
		else if (niveau == 2)
		{
			fichierPhysic ="moyen.dat";
		}
		else if (niveau == 3)
		{
			fichierPhysic ="difficile.dat";
		}
		else
		{
			fichierPhysic = null;
			System.out.println("Veuillez choisir une option valide !");
		}

		BufferedReader reader = new BufferedReader(new FileReader(PATH+fichierPhysic)); //permet d'ouvrir le fichier et le de lire comme un string

		x = (int)(Math.random()*30); // on tire au hasard un nombre entre 0 et 30, se sera la ligne ou on ira chercher le mot dans le fichier
		i = 0;
		
		
		while (i<x)
		{
			ligne = reader.readLine();
			i++;
		}
		
		
		mot_devine = ligne.toCharArray();
		
		return(mot_devine);
	}

	private static int selectionLettres(int niveau) throws IOException// renvoie x = le nombre d'erreur (nbre de lettres donnees qui ne sont pas dans le mot) ou -1 si 0 erreur
	{
		int n_err, n_m, i, n, j, indice;
		char l;
		boolean x; // creer pour la condition du premier while

		x = true;
		indice = 0;

		char[] mot = new char[ChoixMot(niveau).length];// helpppp comment creer un tableau sans preciser la taille
		char[] joueur = new char[mot.length]; // tableau que voit le joueur pendant sa partie, les lettres justes sont affichees, des 0 la ou la lettre n'est pas connu, de la taille du mot
		boolean[] erreur = new boolean[alph.length]; // tableau de boolean qui est de meme taille de alph, ex: sa case 4 vaut True si lettre deja mise/proposee et False si non

		mot = ChoixMot(niveau); // recupere le mot de la fonction ChoixMot (qui est mis sous forme de tableau de caractere)
		n_m = mot.length; // aussi egale a la taille du tableau joueur
		n_err = erreur.length;

		n = 11; // nombre d'erreurs max

		for (i = 0; i < n_err; i += 1) 
		{
			erreur[i] = false;
		}

		for (i = 0; i < n_m; i += 1) // met des 0 dans toutes les cases du tableau joueur
		{
			joueur[i] = '0';
		}

		while ((n > 0) || (Arrays.equals(mot, joueur) == x)) { //tant que ttes les erreurs sont pas faites ou que les deux tableaux sont pas egaux, on continue
			System.out.println("Saisir une lettre"); // penser a essayer en majuscule et minuscule pour voir si les deux marchent
			l = flux.readLine().charAt(0);
			
			for (i = 0; i < n_err; i += 1) // permet de dire a deja ete mise ou non, true si deja mise, false sinon, et on met a true en rentrant la lettre si elle n'avait pas deja ete rentree avant
			{
				if (alph[i] == l) 
					{
					if (erreur[i] == true) 
						{
						System.out.println("Fartasse, tu l'as deja rentree celle la de lettre");
						} 
					else 
						{
						erreur[i] = true;
						}
					}
				}
			}  // elle y etait pas cette acolade jsp pourquoi

			for (j = 0; j < n_m; j += 1) // si la lettre n'a pas deja ete donnee, il cherche dans le mot si elle y est, et si elle y est il la met dans le tableau joueur, sinon il compte une erreur
			{

				if (erreur[j] == false) 
				{
					if (mot[j] == l) 
					{
						indice = j;
						joueur[indice] = l;
					} else 
					{
						n -= 1;
					}
				}
			}

			System.out.print(joueur);// affiche l'avancee du joueur (les lettres justes qu'il a deja donne)
		}

		System.out.println("Jack le pendu est mort...."); // fin de la boucle while = il a depasse son nombre d'essai possible (soit 11), donc message de fin

		return (n);
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
	
	private static void ajouterLignes (String fichierPhysic, String ligne) throws IOException
	{
		String ligneEcrite;
		
		PrintWriter fichier = new PrintWriter(new FileWriter(fichierPhysic,true)); //Association fichier et direct.dat
		
		fichier.println(ligne);
		
		fichier.close();

	}

	public static void main(String[] arg) throws IOException  
	{
		int niveau, erreurs;
		String nom, resultat, fichierPhysic, ligne;
		
		System.out.println(" Il etait une fois, un homme repondant au nom de Jack injustement condamne a la pendaison pour vol... Sa pendaison a lieu aujourd hui, mais vous pouvez le sauver ! Pour cela, vous devez trouver le mot secret... Mais attention, vous n avez droit qu'a 11 erreurs ou il finira pendu."); // mettre
			// le
			// blabla
			// du
			// debut
		System.out.println("Veuillez saisir votre nom");
		nom = flux.readLine();
		System.out.println("Choisissez un niveau : 1 pour facile, 2 pour moyen ou 3 pour difficile");
		niveau = Integer.valueOf(flux.readLine()).intValue(); // saisie du niveau
		System.out.println(niveau);
		if ((niveau != 1) & (niveau != 2) & (niveau != 3)) // si la personne n'a ni mit 1, 2 ou 3 il faut qu'elle
		// recommence
		{
			System.out.println("Il faut mettre 1 pour le niveau facile, 2 pour un niveau moyen ou 3 pour le niveau difficile. Et rien d'autre fartasse");
			niveau = Integer.valueOf(flux.readLine()).intValue();
		}
				
		char[] mot = new char[ChoixMot(niveau).length];

		mot = ChoixMot(niveau);

		erreurs = selectionLettres(niveau);// j'ai rajoute le erreurs = pour pouvoir choper le nbr d'erreurs, puis on recupere le nombre d'erreurs faites et mtn il faut les mettre dans le fichier pour faire le podium jsp comment

		if (erreurs == 11)// ici on indique si gagne ou perdu en fonction du nbr d'erreurs dcp
		{
			System.out.println("Jack est mort, pendu... Fatche vous n'etes pas tres fort avec les mots vous");
			ligne = (nom + " " + "a perdu");// on cree la ligne pr rentrer dans le fichier
		} else {
			System.out.println("Jack est vivant grace a vous ! Felicitation, c'etait votre bonne action de la journee");
			ligne = (nom + " " + "a gagne en faisant" + " " + erreurs + " " + "erreurs");// on cree la ligne pr rentrer dans le fichier
		}

		fichierPhysic = "podium.dat";// faut creer le fichier jsplus faire loulou

		ajouterLignes(fichierPhysic, ligne); // j'appelle ma fonction ajouteLigne qui recquiert un dossier et la ligne a
												// ajt dcp//
		afficherContenu(fichierPhysic); // j'affiche le contenu de mon fichier donc les lignes qui le composent/
		
		
		//Affichage
		
		
		EcranGraphique.init(0,0,500,420,480,360,"pendu");
		EcranGraphique.setClearColor(0,0,0);  //code la couleur du fond
		//enregistrer image dans meme fichier format png en modifiant la taille (sur paint)
		// EcranGraphique.drawImage(coordonnees x , coordonnees y, EcranGraphique.loadPNGFile("nomimage.png"));
		/* Ordre d'affichage d'un segment de droite         */
		/* Extremite initiale en position (40,70)           */
		/* Extremite finale en position (400,300)           */
		EcranGraphique.drawLine(40,70,400,300);
		// faire un while true pour que l'ecrangraphique se reinitialise pour afficher au fur et a mesure
	}

}




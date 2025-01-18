import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;

public class Projet2 {
    static BufferedReader flux = new BufferedReader(new InputStreamReader(System.in));
    public final static int N = 11;
    public static final char[] alph = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String getProjectPath = Paths.get("").toAbsolutePath().toString();
    public static final String PATH = getProjectPath + "\\";

    private static char[] ChoixMot(int niveau) throws IOException {
        String fichierPhysic;
        if (niveau == 1) {
            fichierPhysic = "facile.dat";
        } else if (niveau == 2) {
            fichierPhysic = "moyen.dat";
        } else if (niveau == 3) {
            fichierPhysic = "difficile.dat";
        } else {
            System.out.println("Veuillez choisir une option valide !");
            return new char[0];
        }
		System.out.println("Chemin du fichier : " + PATH + fichierPhysic);

        BufferedReader reader = new BufferedReader(new FileReader(PATH + fichierPhysic));
        int x = (int) (Math.random() * 30);
        String ligne = "";

        for (int i = 0; i <= x; i++) {
            ligne = reader.readLine();
        }

        return ligne.toCharArray();
    }

    private static int selectionLettres(int niveau) throws IOException {
        int n_err, n_m, n = 11;
        char l;
        char[] mot = ChoixMot(niveau);
        if (mot.length == 0) return -1;
        char[] joueur = new char[mot.length];
        boolean[] erreur = new boolean[alph.length];

        Arrays.fill(joueur, '0');

        while (n > 0 && !Arrays.equals(mot, joueur)) {
            System.out.println("Saisir une lettre");
            l = flux.readLine().charAt(0);

            boolean dejaProposee = false;
            for (int i = 0; i < alph.length; i++) {
                if (alph[i] == l) {
                    if (erreur[i]) {
                        System.out.println("Fartasse, tu l'as deja rentree celle la de lettre");
                        dejaProposee = true;
                    } else {
                        erreur[i] = true;
                    }
                    break;
                }
            }

            if (!dejaProposee) {
                boolean lettreTrouvee = false;
                for (int j = 0; j < mot.length; j++) {
                    if (mot[j] == l) {
                        joueur[j] = l;
                        lettreTrouvee = true;
                    }
                }

                if (!lettreTrouvee) {
                    n--;
                }
            }

            System.out.println(joueur);
        }

        if (n == 0) {
            System.out.println("Jack le pendu est mort...");
        }

        return n;
    }

    private static void afficherContenu(String fichierPhysic) throws IOException {
        File temp = new File(fichierPhysic);
        if (temp.length() == 0) {
            System.out.println("vide");
        } else {
            RandomAccessFile fichier = new RandomAccessFile(fichierPhysic, "r");
            String ligneLue;
            int i = 1;

            while ((ligneLue = fichier.readLine()) != null) {
                System.out.println("ligne " + i + " : " + ligneLue);
                i++;
            }
            fichier.close();
        }
    }

    private static void ajouterLignes(String fichierPhysic, String ligne) throws IOException {
        PrintWriter fichier = new PrintWriter(new FileWriter(fichierPhysic, true));
        fichier.println(ligne);
        fichier.close();
    }

    public static void main(String[] arg) throws IOException {
        System.out.println("Il etait une fois, un homme repondant au nom de Jack injustement condamne a la pendaison pour vol... Sa pendaison a lieu aujourd hui, mais vous pouvez le sauver ! Pour cela, vous devez trouver le mot secret... Mais attention, vous n avez droit qu'a 11 erreurs ou il finira pendu.");
        System.out.println("Veuillez saisir votre nom");
        String nom = flux.readLine();
        System.out.println("Choisissez un niveau : 1 pour facile, 2 pour moyen ou 3 pour difficile");
        int niveau = Integer.parseInt(flux.readLine());

        while (niveau < 1 || niveau > 3) {
            System.out.println("Il faut mettre 1 pour le niveau facile, 2 pour un niveau moyen ou 3 pour le niveau difficile. Et rien d'autre fartasse");
            niveau = Integer.parseInt(flux.readLine());
        }

        int erreurs = selectionLettres(niveau);
        String ligne;

        if (erreurs == 11) {
            System.out.println("Jack est mort, pendu... Fatche vous n'etes pas tres fort avec les mots vous");
            ligne = nom + " a perdu";
        } else {
            System.out.println("Jack est vivant grace a vous ! Felicitation, c'etait votre bonne action de la journee");
            ligne = nom + " a gagne en faisant " + erreurs + " erreurs";
        }

        String fichierPhysic = "podium.dat";
        ajouterLignes(fichierPhysic, ligne);
        afficherContenu(fichierPhysic);
    }
}

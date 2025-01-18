import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class PenduV2 {

    public static final int N = 11; // Nombre d'erreurs autorisées -> 11 pour le dessin complet
    static BufferedReader flux = new BufferedReader(new InputStreamReader(System.in));
    public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String getProjectPath = Paths.get("").toAbsolutePath().toString();
    public static final String PATH = getProjectPath + "\\src\\data\\";

    public static void main(String[] args) throws IOException {
        System.out.println(" Il était une fois, un homme répondant au nom de Jack, injustement condamné à la pendaison pour vol...");
        System.out.println(" Sa pendaison a lieu aujourd'hui, mais vous pouvez le sauver ! Pour cela, vous devez trouver le mot secret...");
        System.out.println(" Mais attention, vous n'avez droit qu'à 11 erreurs, ou il finira pendu. \n");

        System.out.print("Veuillez saisir votre nom : ");
        String nom = flux.readLine();

        int niveau = choisirNiveau();
        String mot = choisirMot(niveau);

        jouerPartie(nom, mot);
    }

    public static int choisirNiveau() throws IOException {
        System.out.println("Choisissez un niveau :");
        System.out.println("1. Facile");
        System.out.println("2. Moyen");
        System.out.println("3. Difficile");

        int niveau;
        while (true) {
            System.out.print("Votre choix : ");
            try {
                niveau = Integer.parseInt(flux.readLine());
                if (niveau >= 1 && niveau <= 3) {
                    break;
                } else {
                    System.out.println("Il faut mettre 1 pour le niveau facile, 2 pour un niveau moyen ou 3 pour le niveau difficile.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un chiffre valide !");
            }
        }
        return niveau;
    }

    public static String choisirMot(int niveau) throws IOException {
        String fichierPhysic;

        switch (niveau) {
            case 1:
                fichierPhysic = "facile.dat";
                break;
            case 2:
                fichierPhysic = "moyen.dat";
                break;
            case 3:
                fichierPhysic = "difficile.dat";
                break;
            default:
                fichierPhysic = "facile.dat";
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH + fichierPhysic));
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : Le fichier " + fichierPhysic + " est introuvable dans le répertoire " + PATH);
            System.exit(1); // Quitter le programme en cas d'erreur
        }

        List<String> mots = new ArrayList<>();
        String ligne;

        while ((ligne = reader.readLine()) != null) {
            mots.add(ligne.trim());
        }
        reader.close();

        if (mots.isEmpty()) {
            System.out.println("Le fichier " + fichierPhysic + " est vide !");
            System.exit(1);
        }

        return mots.get(new Random().nextInt(mots.size()));
    }

    public static void jouerPartie(String nom, String mot) throws IOException {
        int erreurs = 0;
        Set<Character> lettresEssayees = new HashSet<>();
        char[] progression = new char[mot.length()];
        Arrays.fill(progression, '_');

        System.out.println("\nJack est prêt à être pendu... Saurez-vous le sauver ?");
        while (erreurs < N && !String.valueOf(progression).equals(mot)) {
            afficherEtat(progression, erreurs, lettresEssayees);

            System.out.print("Saisissez une lettre : ");
            char lettre;
            try {
                lettre = flux.readLine().toLowerCase().charAt(0);
            } catch (Exception e) {
                System.out.println("Veuillez entrer une lettre valide.");
                continue;
            }

            if (lettresEssayees.contains(lettre)) {
                System.out.println("Vous avez déjà essayé cette lettre !");
                continue;
            }

            lettresEssayees.add(lettre);

            if (mot.indexOf(lettre) != -1) {
                for (int i = 0; i < mot.length(); i++) {
                    if (mot.charAt(i) == lettre) {
                        progression[i] = lettre;
                    }
                }
                System.out.println("Bien joué ! La lettre " + lettre + " est dans le mot.");
            } else {
                erreurs++;
                System.out.println("Dommage ! La lettre " + lettre + " n'est pas dans le mot.");
            }
        }

        afficherResultat(nom, mot, erreurs, progression);
    }

    public static void afficherEtat(char[] progression, int erreurs, Set<Character> lettresEssayees) {
        System.out.println("\nMot : " + String.valueOf(progression));
        System.out.println("Erreurs : " + erreurs + "/" + N);
        System.out.println("Lettres essayées : " + lettresEssayees);
        afficherDessin(erreurs);
    }

    public static void afficherDessin(int erreurs) {
        String[] dessin = {
                "\n\n\n\n\n\n",
                "\n\n\n\n\n\n========",
                "\n\n\n\n\n |\n========",
                "\n\n\n\n |\n |\n========",
                "\n\n\n |\n |\n |\n========",
                "\n\n O\n |\n |\n |\n========",
                "\n\n O\n/|\n |\n |\n========",
                "\n\n O\n/|\\\n |\n |\n========",
                "\n\n O\n/|\\\n |\n/ \n========",
                "\n\n O\n/|\\\n |\n/ \\\n========",
                "\n  ____\n |    |\n O    |\n/|\\   |\n/ \\   |\n========"
        };
        System.out.println(dessin[erreurs]);
    }

    public static void afficherResultat(String nom, String mot, int erreurs, char[] progression) throws IOException {
        if (String.valueOf(progression).equals(mot)) {
            System.out.println("\nJack est vivant grâce à vous ! Félicitations, c'était votre bonne action de la journée !");
            System.out.println("Le mot était : " + mot);
            enregistrerResultat(nom, erreurs, "a gagné", mot);
        } else {
            System.out.println("\nJack est mort, pendu... Vous n'avez pas réussi à le sauver.");
            System.out.println("Le mot était : " + mot);
            enregistrerResultat(nom, erreurs, "a perdu", mot);
        }

    }

    public static void enregistrerResultat(String nom, int erreurs, String resultat, String mot) throws IOException {
        String fichierPhysic = "podium.dat";
        String ligne = nom + " " + resultat + " avec " + erreurs + " erreurs. | mot : " + mot;

        PrintWriter fichier = new PrintWriter(new FileWriter(PATH + fichierPhysic, true));
        fichier.println(ligne);
        fichier.close();

        System.out.println("\nRésultats enregistrés dans le fichier " + fichierPhysic + " !");
        afficherContenu(fichierPhysic);
    }

    public static void afficherContenu(String fichierPhysic) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH + fichierPhysic));
        System.out.println("\n=== Contenu du fichier " + fichierPhysic + " ===");
        String ligne;
        while ((ligne = reader.readLine()) != null) {
            System.out.println(ligne);
        }
        reader.close();
    }
}
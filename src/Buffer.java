import java.io.*; 
import java.nio.file.Paths;
import java.util.*;

/**
 * Jeu du Pendu : le joueur doit deviner un mot secret pour sauver Jack de la pendaison.
 */
public class Buffer {

    /**
     * Nombre maximal d'erreurs autorisées.
     */
    public static final int N = 11;

    /**
     * Flux pour lire les entrées utilisateur.
     */
    static BufferedReader flux = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Alphabet utilisé dans le jeu.
     */
    public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * Chemin du répertoire de données du projet.
     */
    public static String getProjectPath = Paths.get("").toAbsolutePath().toString();
    public static final String PATH = getProjectPath + "\\data\\";

    /**
     * Point d'entrée principal du programme.
     *
     * @param args Arguments du programme (non utilisés).
     * @throws IOException En cas d'erreur de lecture.
     */
    public static void main(String[] args) throws IOException {
        System.out.println(" Il était une fois, un homme répondant au nom de Jack, injustement condamné à la pendaison pour vol...");
        System.out.println(" Sa pendaison a lieu aujourd'hui, mais vous pouvez le sauver ! Pour cela, vous devez trouver le mot secret...");
        System.out.println(" Mais attention, vous n'avez droit qu'à 11 erreurs, ou il finira pendu. \n");

        String nom = "";
        boolean nomValide = false;
        while (!nomValide) {
            System.out.print("Veuillez saisir votre nom : ");
            nom = flux.readLine();
            if (nom.matches("[a-zA-Z]+")) {
                nomValide = true;
            } else {
                System.out.println("Le nom ne doit pas être vide et ne peut contenir que des lettres. Essayez à nouveau.");
            }
        }

        int niveau = choisirNiveau();
        String mot = choisirMot(niveau);

        jouerPartie(nom, mot);
    }

    /**
     * Permet au joueur de choisir un niveau de difficulté.
     *
     * @return Le niveau choisi (1 : Facile, 2 : Moyen, 3 : Difficile).
     * @throws IOException En cas d'erreur de saisie.
     */
    public static int choisirNiveau() throws IOException {
        System.out.println("Choisissez un niveau :");
        System.out.println("1. Facile");
        System.out.println("2. Moyen");
        System.out.println("3. Difficile");

        int niveau = 0;
        boolean niveauValide = false;

        while (!niveauValide) {
            System.out.print("Votre choix : ");
            try {
                niveau = Integer.parseInt(flux.readLine());
                if (niveau >= 1 && niveau <= 3) {
                    niveauValide = true;
                } else {
                    System.out.println("Il faut mettre 1 pour le niveau facile, 2 pour un niveau moyen ou 3 pour le niveau difficile.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un chiffre valide !");
            }
        }
        return niveau;
    }

    /**
     * Choisit un mot aléatoire à deviner selon le niveau.
     *
     * @param niveau Le niveau choisi.
     * @return Le mot sélectionné.
     * @throws IOException En cas de problème de lecture ou d'absence de fichier.
     */
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
            System.exit(1);
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

    /**
     * Joue une partie complète avec un mot et un joueur donné.
     *
     * @param nom Le nom du joueur.
     * @param mot Le mot à deviner.
     * @throws IOException En cas d'erreur de saisie.
     */
    public static void jouerPartie(String nom, String mot) throws IOException {
        int erreurs = 0;
        Set<Character> lettresEssayées = new HashSet<>();
        char[] progression = new char[mot.length()];
        Arrays.fill(progression, '_');

        System.out.println("\nJack est prêt à être pendu... Saurez-vous le sauver ?");
        boolean motTrouvé = false;

        while (erreurs < N && !motTrouvé) {
            afficherEtat(progression, erreurs, lettresEssayées);

            System.out.print("Saisissez une lettre : ");
            char lettre = ' ';
            boolean lettreValide = false;

            while (!lettreValide) {
                try {
                    String entrée = flux.readLine().toLowerCase();
                    if (entrée.length() == 1 && Character.isLetter(entrée.charAt(0))) {
                        lettre = entrée.charAt(0);
                        lettreValide = true;
                    } else {
                        System.out.println("Veuillez entrer une seule lettre de l'alphabet.");
                    }
                } catch (Exception e) {
                    System.out.println("Erreur : entrée invalide. Réessayez.");
                }
            }

            if (lettresEssayées.contains(lettre)) {
                System.out.println("Vous avez déjà essayé cette lettre !");
            } else {
                lettresEssayées.add(lettre);

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

            motTrouvé = String.valueOf(progression).equals(mot);
        }

        afficherResultat(nom, mot, erreurs, progression);
    }

    /**
     * Affiche l'état actuel de la partie.
     *
     * @param progression Progression dans le mot.
     * @param erreurs Nombre d'erreurs commises.
     * @param lettresEssayées Lettres déjà essayées.
     */
    public static void afficherEtat(char[] progression, int erreurs, Set<Character> lettresEssayées) {
        System.out.println("\nMot : " + String.valueOf(progression));
        System.out.println("Erreurs : " + erreurs + "/" + N);
        System.out.println("Lettres essayées : " + lettresEssayées);
        afficherDessin(erreurs);
    }

    /**
     * Affiche le dessin de la potence selon les erreurs.
     *
     * @param erreurs Nombre d'erreurs commises.
     */
    public static void afficherDessin(int erreurs) {
        String[] dessin = {
            "  \n  \n  \n  \n  \n========",
            "  |\n  |\n  |\n  |\n  |\n========",
            "  ____\n  |\n  |\n  |\n  |\n========",
            "  ____\n |    | \n  | \n  | \n  | \n========",
            "  ____\n |    |\n O    |\n  |\n  |\n========",
            "  ____\n |    |\n O    |\n |    |\n  |\n========",
            "  ____\n |    |\n O    |\n/|    |\n  |\n========",
            "  ____\n |    |\n O    |\n/|\\   |\n  |\n========",
            "  ____\n |    |\n O    |\n/|\\   |\n/     |\n========",
            "  ____\n |    |\n O    |\n/|\\   |\n/ \\   |\n========"
        };
        System.out.println(dessin[erreurs]);
    }

    /**
     * Affiche le résultat de la partie.
     *
     * @param nom Nom du joueur.
     * @param mot Le mot à deviner.
     * @param erreurs Nombre d'erreurs commises.
     * @param progression État final de la progression.
     */
    public static void afficherResultat(String nom, String mot, int erreurs, char[] progression) {
        if (String.valueOf(progression).equals(mot)) {
            System.out.println("\nFélicitations " + nom + " ! Vous avez sauvé Jack !");
            System.out.println("Le mot était bien : " + mot);
        } else {
            System.out.println("\nDommage " + nom + ", vous n'avez pas réussi à sauver Jack...");
            System.out.println("Le mot à deviner était : " + mot);
        }
        System.out.println("Merci d'avoir joué !");
    }
}

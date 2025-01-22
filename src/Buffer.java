import java.io.*;
import java.util.*;

/**
 * Jeu du pendu avec une interface graphique.
 * Chaque méthode correspond à une étape ou une action du jeu.
 */
public class PenduV3 {

    // Chemin des ressources (images et données)
    private static final String IMAGES_PATH = "./images/";
    private static final String DATA_PATH = "./data/";
    private static final int MAX_ERRORS = 10; // Nombre maximum d'erreurs autorisées
    private static String joueur = ""; // Nom du joueur
    private static String message = ""; // Message d'état ou d'erreur

    /**
     * Point d'entrée du programme. Initialise l'écran graphique et lance la page d'accueil.
     */
    public static void main(String[] args) throws IOException {
        EcranGraphique.init(100, 100, 1000, 800, 1000, 800, "Jeu du Pendu");
        EcranGraphique.clear();
        afficherPageLancement();
    }

    /**
     * Affiche la page de lancement où le joueur peut entrer son nom.
     */
    public static void afficherPageLancement() throws IOException {
        EcranGraphique.clear();

        // Affichage du fond et du message d'accueil
        EcranGraphique.setColor(0, 0, 128);
        EcranGraphique.fillRect(0, 0, 1000, 800);
        EcranGraphique.setColor(255, 255, 255);
        EcranGraphique.drawString(10, 50, EcranGraphique.COLABA8x13, "Bievenue jeune aventurier, vous voulez jouer ? Commencez par vous presenter !");
        EcranGraphique.setColor(102, 102, 255);
        EcranGraphique.drawString(20, 100, EcranGraphique.COLABA8x13, ">> Entrez votre nom (finir par Entree) :");

        // Saisie du nom du joueur
        boolean nomEntre = false;
        joueur = "";
        while (!nomEntre) {
            char c = EcranGraphique.getKey();
            if (c == '\n') {
                nomEntre = true;
            } else if (c != 0x00) {
                joueur += c;
                EcranGraphique.setColor(0, 0, 128);
                EcranGraphique.fillRect(400, 270, 400, 20);
                EcranGraphique.setColor(255, 255, 255);
                String haha = ">> " + joueur;
                EcranGraphique.drawString(30, 130, EcranGraphique.COLABA8x13, haha);
            }
            EcranGraphique.flush();
        }

        introduction();
    }

    /**
     * Affiche une introduction au jeu et au personnage de Jack.
     */
    public static void introduction() throws IOException {
        EcranGraphique.clear();
        EcranGraphique.setColor(0, 0, 0);
        EcranGraphique.setColor(255, 255, 255);
        EcranGraphique.drawString(10, 50, EcranGraphique.COLABA8x13, "Bienvenue " + joueur + ", laissez-moi vous raconter une histoire...");
        EcranGraphique.setColor(255, 255, 255);
        EcranGraphique.drawString(10, 150, EcranGraphique.COLABA8x13, "Il etait une fois, un homme repondant au nom de Jack, injustement condamne a la pendaison pour vol...");

        // Bouton "En savoir davantage"
        EcranGraphique.setColor(0, 0, 200);
        EcranGraphique.fillRect(380, 400, 260, 50);
        EcranGraphique.setColor(255, 255, 255);
        EcranGraphique.drawString(400, 430, EcranGraphique.COLABA8x13, "En savoir davantage (O_O)");
        EcranGraphique.flush();

        boolean boutonClique = false;
        while (!boutonClique) {
            int mouseState = EcranGraphique.getMouseState();
            int x = EcranGraphique.getMouseX();
            int y = EcranGraphique.getMouseY();
            if (mouseState == 2 && x >= 450 && x <= 600 && y >= 400 && y <= 450) {
                boutonClique = true;
            }
            EcranGraphique.flush();
        }

        bienvenue();
    }

    /**
     * Explique les règles du jeu et introduit le défi au joueur.
     */
    public static void bienvenue() throws IOException {
        EcranGraphique.clear();
        EcranGraphique.setColor(0, 0, 0);
        EcranGraphique.fillRect(0, 0, 1000, 800);
        EcranGraphique.setColor(128, 128, 128);
        EcranGraphique.drawString(10, 50, EcranGraphique.COLABA8x13, "Heureux de vous savoir curieux a son encontre " + joueur + " !");
        EcranGraphique.setColor(255, 0, 0);
        EcranGraphique.drawString(10, 100, EcranGraphique.COLABA8x13, "VOUS NE DEVRIEZ PAS !");
        EcranGraphique.setColor(96, 96, 96);
        EcranGraphique.drawString(10, 150, EcranGraphique.COLABA8x13, "Sa pendaison a lieu aujourd'hui, mais vous pouvez quand meme le sauver !");
        EcranGraphique.drawString(10, 200, EcranGraphique.COLABA8x13, "Pour cela, vous devez trouver le mot secret...");
        EcranGraphique.setColor(255, 0, 0);
        String hehe = "Mais attention, vous n'avez droit qu'a " + MAX_ERRORS + " erreurs, ou il finira pendu.";
        EcranGraphique.drawString(40, 300, EcranGraphique.COLABA8x13, hehe);
        EcranGraphique.setColor(0, 0, 200);
        EcranGraphique.fillRect(420, 400, 180, 50);
        EcranGraphique.setColor(255, 255, 255);
        EcranGraphique.drawString(440, 430, EcranGraphique.COLABA8x13, "TENTER MA CHANCE");
        EcranGraphique.flush();

        boolean boutonClique = false;
        while (!boutonClique) {
            int mouseState = EcranGraphique.getMouseState();
            int x = EcranGraphique.getMouseX();
            int y = EcranGraphique.getMouseY();
            if (mouseState == 2 && x >= 450 && x <= 600 && y >= 400 && y <= 450) {
                boutonClique = true;
            }
            EcranGraphique.flush();
        }

        int niveau = choisirNiveau();
        startGame(niveau);
    }

    /**
     * Permet au joueur de choisir un niveau de difficulté.
     * @return Le niveau choisi (1, 2 ou 3).
     */
    public static int choisirNiveau() throws IOException {
        EcranGraphique.clear();
        EcranGraphique.setColor(0, 0, 0);
        EcranGraphique.fillRect(0, 0, 1000, 800);

        EcranGraphique.setColor(255, 0, 0);
        EcranGraphique.drawString(300, 200, EcranGraphique.COLABA8x13, "A quel point voulez-vous jouer avec sa vie ?");
        EcranGraphique.drawString(300, 250, EcranGraphique.COLABA8x13, "1 : Un peu");
        EcranGraphique.drawString(300, 300, EcranGraphique.COLABA8x13, "2 : Beaucoup");
        EcranGraphique.drawString(300, 350, EcranGraphique.COLABA8x13, "3 : J\'adore jouer avec sa vie !");

        int niveau = 0;
        while (niveau < 1 || niveau > 3) {
            char c = EcranGraphique.getKey();
            if (c >= '1' && c <= '3') {
                niveau = c - '0';
            }
            EcranGraphique.flush();
        }
        return niveau;
    }

    /**
     * Lance la partie principale en fonction du niveau choisi.
     * @param niveau Niveau de difficulté choisi par le joueur.
     */
    public static void startGame(int niveau) throws IOException {
        String mot = choisirMot(niveau);
        char[] motCache = new char[mot.length()];
        Arrays.fill(motCache, '_');

        ArrayList<Character> lettresEssayees = new ArrayList<>();
        int erreurs = 0;
        boolean gagne = false;

        while (erreurs < MAX_ERRORS && !gagne) {
            afficherEtat(motCache, lettresEssayees, erreurs);
            char lettre = obtenirLettreUtilisateur(lettresEssayees);

            if (lettresEssayees.contains(lettre)) {
                message = "La lettre '" + lettre + "' a deja ete essayee.";
            } else if (mot.indexOf(lettre) >= 0) {
                for (int i = 0; i < mot.length(); i++) {
                    if (mot.charAt(i) == lettre) {
                        motCache[i] = lettre;
                    }
                }
                message = "Bravo ! La lettre '" + lettre + "' est correcte.";
                lettresEssayees.add(lettre);
            } else {
                erreurs++;
                message = "Dommage ! La lettre '" + lettre + "' n'est pas dans le mot.";
                lettresEssayees.add(lettre);
            }

            gagne = String.valueOf(motCache).equals(mot);
        }

        enregistrerResultat(gagne, mot, erreurs);
        afficherFinPartie(gagne, mot);
    }

    /**
     * Choisit un mot aléatoire à deviner en fonction du niveau choisi.
     * @param niveau Niveau de difficulté (1, 2 ou 3).
     * @return Le mot à deviner.
     * @throws IOException Si le fichier contenant les mots est introuvable ou vide.
     */
    public static String choisirMot(int niveau) throws IOException {
        String fichierMot;
        switch (niveau) {
            case 1:
                fichierMot = DATA_PATH + "facile.dat";
                break;
            case 2:
                fichierMot = DATA_PATH + "moyen.dat";
                break;
            case 3:
                fichierMot = DATA_PATH + "difficile.dat";
                break;
            default:
                fichierMot = DATA_PATH + "facile.dat";
        }

        List<String> mots = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichierMot))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                mots.add(ligne.trim());
            }
        }

        if (mots.isEmpty()) {
            throw new IOException("Le fichier de mots est vide.");
        }

        return mots.get(new Random().nextInt(mots.size()));
    }

    /**
     * Enregistre le résultat de la partie (victoire ou défaite) dans un fichier podium.dat.
     * Le fichier contient le joueur, le mot, et le nombre d'erreurs.
     * @param gagne Indique si le joueur a gagné (true) ou perdu (false).
     * @param mot Le mot qui devait être deviné.
     * @param nb_erreurs Le nombre d'erreurs commises pendant la partie.
     */
    public static void enregistrerResultat(boolean gagne, String mot, int nb_erreurs) {
        String resultat = (gagne ? "Victoire" : "Defaite") + " - Joueur : " + joueur + " - Mot : " + mot + " - Erreurs : " + nb_erreurs;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_PATH + "podium.dat", true))) {
            bw.write(resultat);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement des resultats : " + e.getMessage());
        }
    }


    /**
     * Permet au joueur de saisir une lettre. Valide la saisie pour s'assurer qu'il s'agit bien d'une lettre.
     * @param lettresEssayees Liste des lettres déjà essayées pour éviter les doublons.
     * @return La lettre saisie par le joueur.
     */
    public static char obtenirLettreUtilisateur(ArrayList<Character> lettresEssayees) {
        char lettre = '\0';
        boolean lettreValide = false;

        while (!lettreValide) {
            char c = EcranGraphique.getKey();
            if (c != 0x00 && Character.isLetter(c)) {
                lettre = Character.toLowerCase(c);
                lettreValide = true;
            }
        }

        return lettre;
    }


        /**
     * Affiche l'état actuel du jeu, y compris l'image correspondant au nombre d'erreurs,
     * le mot partiellement découvert, les lettres essayées, et un message d'état.
     * @param motCache Tableau contenant les lettres découvertes du mot (ou '_' pour celles non découvertes).
     * @param lettresEssayees Liste des lettres déjà essayées par le joueur.
     * @param erreurs Nombre actuel d'erreurs commises.
     */
    public static void afficherEtat(char[] motCache, ArrayList<Character> lettresEssayees, int erreurs) {
        EcranGraphique.clear();
        String imagePath = IMAGES_PATH + erreurs + ".png";
        int[][] image = EcranGraphique.loadPNGFile(imagePath);
        EcranGraphique.drawImage(50, 50, image);

        EcranGraphique.setColor(255, 255, 255);
        EcranGraphique.drawString(200, 500, EcranGraphique.COLABA8x13, "Mot : " + new String(motCache));
        EcranGraphique.drawString(200, 520, EcranGraphique.COLABA8x13, "Lettres utilisees : " + lettresEssayees);
        EcranGraphique.drawString(200, 540, EcranGraphique.COLABA8x13, message);
        if (erreurs <= 7) {
            EcranGraphique.drawString(200, 560, EcranGraphique.COLABA8x13, "Erreurs : " + erreurs + " / " + MAX_ERRORS);
        }
        if (erreurs > 7) {
            EcranGraphique.setColor(255, 0, 0);
            if (erreurs == 8) {
                EcranGraphique.drawString(200, 560, EcranGraphique.COLABA8x13, "!!! Jack va bientot etre pendu !!!!!    :( <--- c\'est jack en ce moment");
            } 
            if (erreurs == 9) {
                EcranGraphique.drawString(200, 560, EcranGraphique.COLABA8x13, "!!! Jack est presque pendu !!!!!    x_x <--- jack bientot vu comment t\'es mauvais");
            }
            EcranGraphique.drawString(200, 580, EcranGraphique.COLABA8x13, "Essais restants : " + (MAX_ERRORS - erreurs));
        }
        EcranGraphique.flush();
    }


        /**
     * Affiche la fin de la partie avec un message indiquant si le joueur a gagné ou perdu,
     * ainsi que le mot à deviner. Propose également un bouton pour rejouer.
     * @param gagne Indique si le joueur a gagné (true) ou perdu (false).
     * @param mot Le mot qui devait être deviné.
     * @throws IOException Si une erreur d'affichage se produit.
     */
    public static void afficherFinPartie(boolean gagne, String mot) throws IOException {
        EcranGraphique.clear();
        if (gagne) {
            EcranGraphique.setColor(0, 200, 0);
            EcranGraphique.drawString(300, 300, EcranGraphique.COLABA8x13, "FELICITATIONS " + joueur + " ! Vous avez gagne !");
        } else {
            EcranGraphique.setColor(200, 0, 0);
            EcranGraphique.drawString(300, 300, EcranGraphique.COLABA8x13, "PERDU... " + joueur + ", le mot etait : " + mot);
        }

        EcranGraphique.setColor(0, 0, 200);
        EcranGraphique.fillRect(450, 400, 150, 50);
        EcranGraphique.setColor(255, 255, 255);
        EcranGraphique.drawString(470, 430, EcranGraphique.COLABA8x13, "REJOUER");
        EcranGraphique.flush();

        boolean boutonClique = false;
        while (!boutonClique) {
            int mouseState = EcranGraphique.getMouseState();
            int x = EcranGraphique.getMouseX();
            int y = EcranGraphique.getMouseY();
            if (mouseState == 2 && x >= 450 && x <= 600 && y >= 400 && y <= 450) {
                boutonClique = true;
            }
            EcranGraphique.flush();
        }

        afficherPageLancement();
    }
}

// Pistes d'amlélioration :
// - Ajouter des pages de fin sur le même modèle que la page intro donc avec du texte de type oh nan jack est mort ou ce que vous voulez
// Si la personne clique sur rejouter, relancer... mais pas vraiment genre refaire une page clone de celle de base mais avec un petit détail qui change genre hmmm... vous n'avez pas assez joué avec la vie de jack comme ça ?
// Changer le nom en cas de rejouer (faire une var nom qui recup un nom dans un fichier txt âr exemple sur le même modèle que les mots) et changer jack dans les strings par la var 
// 
// Si vous avez des questions ou quoi que ce soit demandez et amusez-vous bien vous avez déjà une belle base pour essayer de voir comment implémenter vos idées
// Amusez-vous bien !
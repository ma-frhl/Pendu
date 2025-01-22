import java.io.*;
import java.util.*;

public class PenduV3 {

    private static final String IMAGES_PATH = "./images/";
    private static final String DATA_PATH = "./data/";
    private static final int MAX_ERRORS = 10;
    private static String joueur = "";
    private static String message = "";

    
    public static void main(String[] args) throws IOException {
        // Initialisation graphique
        EcranGraphique.init(100, 100, 1000, 800, 1000, 800, "Jeu du Pendu");
        EcranGraphique.clear();
        afficherPageLancement();
    }

    public static void afficherPageLancement() throws IOException {
        EcranGraphique.clear();

        // Affichage du fond et instructions
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

    public static void introduction() throws IOException {
        EcranGraphique.clear();
        EcranGraphique.setColor(0, 0, 0);
        EcranGraphique.setColor(255, 255, 255);
        EcranGraphique.drawString(10, 50, EcranGraphique.COLABA8x13, "Bienvenue " + joueur + ", laissez-moi vous raconter une histoire...");
        EcranGraphique.setColor(255, 255, 255);
        EcranGraphique.drawString(10, 150, EcranGraphique.COLABA8x13, "Il etait une fois, un homme repondant au nom de Jack, injustement condamne a la pendaison pour vol...");

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

        // Lancer le jeu
        startGame(niveau);
    }

    public static int choisirNiveau() throws IOException {
        EcranGraphique.clear();
        EcranGraphique.setColor(0, 0, 0);
        EcranGraphique.fillRect(0, 0, 1000, 800);

        EcranGraphique.setColor(255, 0, 0);
        EcranGraphique.drawString(300, 200, EcranGraphique.COLABA8x13, "A quel point voulez-vous jouer avec sa vie ?");
        EcranGraphique.drawString(300, 250, EcranGraphique.COLABA8x13, "1 : Un peu");
        EcranGraphique.drawString(300, 300, EcranGraphique.COLABA8x13, "2 : Beaucoup");
        EcranGraphique.drawString(300, 350, EcranGraphique.COLABA8x13, "3 : Meurs en fait Jack");

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

    public static void enregistrerResultat(boolean gagne, String mot, int nb_erreurs) {
        String resultat = (gagne ? "Victoire" : "Defaite") + " - Joueur : " + joueur + " - Mot : " + mot + " - Erreurs : " + nb_erreurs;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_PATH + "podium.dat", true))) {
            bw.write(resultat);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement des resultats : " + e.getMessage());
        }
    }

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

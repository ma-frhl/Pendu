/* Application exemple d'utilisation des methodes   */
/* contenues dans EcranGraphique.class              */

public class TestEcranGraphique {
  
  public static void croix(int x,int y) {
    EcranGraphique.drawLine(x-10,y,x+10,y);
    EcranGraphique.drawLine(x,y-10,x,y+10);
  }
  
  public static void main(String [] args) {
    int val = -1;
  /* Initialisation de la fenetre d'affichage       */
    EcranGraphique.init(50,50,480,360,640,480,"Test affichage graphique");
  /* Boucle de gestion de l'application             */
    while ( val != 0 ) {
  /* Menu                                           */
      Ecran.afficherln(" 1 - Afficher 3 pixels");      
      Ecran.afficherln(" 2 - Afficher un segment de droite");      
      Ecran.afficherln(" 3 - Animer un segment de droite");
      Ecran.afficherln(" 4 - Afficher un rectangle et remplir un rectangle");
      Ecran.afficherln("     Afficher un cercle et remplir un cercle");
      Ecran.afficherln(" 5 - Afficher une chaine de caracteres");      
      Ecran.afficherln(" 6 - Tester l'alpha-blending");
      Ecran.afficherln(" 7 - Effacer l'ecran");
      Ecran.afficherln(" 8 - Lire un caractere au clavier");
      Ecran.afficherln(" 9 - Deplacer une croix a la souris");
      Ecran.afficherln("10 - Lire une image PNG et l'afficher");
      Ecran.afficherln("11 - Lire une image PNG sur le web et l'afficher");
      Ecran.afficherln("12 - Tester getMouseState()");
      Ecran.afficherln("13 - Tester getMouseButton()");
      Ecran.afficherln(" 0 - Quitter");
      Ecran.afficher("Choix ? ");
      val = Clavier.saisirInt();
  /* Action en fonction de la valeur saisie         */
      switch (val) {
        case 1 : {
  /* Affichage de trois pixels de couleurs differentes */
          EcranGraphique.setColor(200,0,0);
          EcranGraphique.drawPixel(100,200);
          EcranGraphique.setColor(0,200,0);
          EcranGraphique.drawPixel(200,100);
          EcranGraphique.setColor(0,0,200);
          EcranGraphique.drawPixel(300,200);
          EcranGraphique.flush(); }
          break;
        case 2 : {
  /* Affichage d'un segment de droite               */
          EcranGraphique.setColor(200,0,0);
          EcranGraphique.drawLine(-100,100,100,100);
          EcranGraphique.flush(); }
          break;
        case 3 : {
  /* Animation d'un segment de droite               */
          long ti = System.nanoTime();
          for ( int i = 0 ; i <= 768 ; i++ ) {
            EcranGraphique.clear();
            EcranGraphique.setColor(i/3,i%256,(i+128)%256);
            EcranGraphique.drawLine((int) (320+200*Math.cos(i*Math.PI*2.0/768.0)+0.5),
                                    (int) (240+200*Math.sin(i*Math.PI*2.0/768.0)+0.5),
                                    (int) (320-200*Math.cos(i*Math.PI*2.0/768.0)+0.5),
                                    (int) (240-200*Math.sin(i*Math.PI*2.0/768.0)+0.5));
            EcranGraphique.flush(); }
          long tf = System.nanoTime();
          System.out.println((tf-ti)/1000000); }
          break;
        case 4 : {
  /* Affichage d'un rectangle                       */
          EcranGraphique.setColor(200,0,0);
          EcranGraphique.drawRect(-100,100,370,500);
  /* Affichage d'un cercle                          */
          EcranGraphique.setColor(0,200,0);
          EcranGraphique.drawCircle(120,250,200);
  /* Remplissage d'un cercle                        */
          EcranGraphique.setColor(0,200,200);
          EcranGraphique.fillCircle(500,320,240);
  /* Remplissage d'un rectangle                     */
          EcranGraphique.setColor(200,200,0);
          EcranGraphique.fillRect(500,300,700,100);
          EcranGraphique.flush(); }
          break;
        case 5 : {
  /* Affichage d'une chaine de caracteres */
          EcranGraphique.setColor(255,255,255);
          EcranGraphique.drawString(10,200,EcranGraphique.SYMBOL8x13,"ABCDEFGHIJKLMNOPQRSTUVWXYZ-abcdefghijklmnopqrstuvwxyz-0123456789");
          EcranGraphique.drawString(10,220,EcranGraphique.COLABA8x13,"ABCDEFGHIJKLMNOPQRSTUVWXYZ-abcdefghijklmnopqrstuvwxyz-0123456789");
          EcranGraphique.flush(); }
          break;
        case 6 : {
  /* Affichage d'un rectangle                       */
          EcranGraphique.setColor(200,0,0);
          EcranGraphique.drawRect(-100,100,400,500);
  /* Remplissage d'un rectangle                     */
          EcranGraphique.setColor(200,200,0);
          EcranGraphique.fillRect(500,300,700,100);
          EcranGraphique.setAlphaBlendingMode(true);
          EcranGraphique.setAlpha(0.5);
  /* Affichage d'un cercle                          */
          EcranGraphique.setColor(0,200,0);
          EcranGraphique.drawCircle(120,250,200);
  /* Remplissage d'un cercle                        */
          EcranGraphique.setColor(0,200,200);
          EcranGraphique.fillCircle(500,320,240);
          EcranGraphique.setAlphaBlendingMode(false);
          EcranGraphique.flush(); }
          break;
        case 7 : {
  /* Effacement de la fenetre de dessin             */
          EcranGraphique.clear();
          EcranGraphique.flush(); }
          break;
        case 8 : {
  /* Lecture d'un caractere au clavier              */
          char c;
          EcranGraphique.flushKey();
          while ( (c = EcranGraphique.getKey()) == 0x00 );
          System.out.println(c);
          System.out.println((int) c); }
          break;
        case 9 : {
  /* Deplacement d'une croix a la souris            */
          EcranGraphique.setColor(255,255,255);
          while ( EcranGraphique.getMouseState() == 0 ) {
            EcranGraphique.wait(10); }
          EcranGraphique.setXorMode(true);
          int xOld = EcranGraphique.getMouseX();
          int yOld = EcranGraphique.getMouseY();
          croix(xOld,yOld);
          while ( EcranGraphique.getMouseState() == 1 ) {
            int x = EcranGraphique.getMouseX();
            int y = EcranGraphique.getMouseY();
            if ( ( x != xOld ) || ( y != yOld ) ) {
              croix(xOld,yOld);
              xOld = x;
              yOld = y;
              croix(x,y);
              EcranGraphique.flush(); }
              else {
              EcranGraphique.wait(10); } }
          croix(xOld,yOld);
          EcranGraphique.flush();
          EcranGraphique.setXorMode(false); }
          break;
        case 10 : {
  /* Lecture et affichage d'une image PNG           */
          Ecran.afficher("Quel fichier? ");
          String file = Clavier.saisirString();
          int [][] img = EcranGraphique.loadPNGFile(file);
          if ( img != null ) {
            EcranGraphique.drawImage(0,0,img);
            EcranGraphique.flush(); } }
          break;
        case 11 : {
  /* Lecture et affichage d'une image PNG           */
          Ecran.afficher("Quelle url? ");
          String url = Clavier.saisirString();
          int [][] img = EcranGraphique.downloadPNGFile(url);
          if ( img != null ) {
            EcranGraphique.drawImage(0,0,img);
            EcranGraphique.flush(); } }
          break;
  /* Test d'un "clic" souris                        */
        case 12 : {
          boolean go = true;
          while (go) {
            EcranGraphique.wait(10);
            int v = EcranGraphique.getMouseState();
            go = ( v != 2 );
            Ecran.afficherln(v); }
          Ecran.afficherln("Par le bouton ",EcranGraphique.getMouseButton()); }
          break;
  /* Test des boutons de la souris                  */
        case 13 : {
          boolean go = true;
          while (go) {
            EcranGraphique.wait(10);
            int v = EcranGraphique.getMouseButton();
            go = ( v != 2 );
            Ecran.afficherln(v); } }
          break;
  /* Passage de la fenetre en non resizable         */
  /* et de taille 600 x 400                         */
        case 14 : {
          EcranGraphique.setWindowResizable(false);
          EcranGraphique.setWindowSize(600,400); }
          break;
  /* Passage de la fenetre en resizable             */
        case 15 : {
          EcranGraphique.setWindowResizable(true); }
          break;
  /* Fin du programme                               */
        case 0 : {
          Ecran.afficherln("A bientot!!!");
          break; } } }
  /* Fermeture de la fenetre                        */
  /* et interruption de l'application               */
    EcranGraphique.exit();
  }   
}
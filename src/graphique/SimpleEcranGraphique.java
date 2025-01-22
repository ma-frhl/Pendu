/* Application exemple d'utilisation des methodes    */
/* contenues dans EcranGraphique.class               */

public class SimpleEcranGraphique {
  
  public static void main(String [] args) {
  /* Initialisation de la fenetre d'affichage         */
  /* Coin superieur gauche en position ecran (50,50)  */
  /* Fenetre de taille 500x420 pixels                 */
  /* Zone d'affichage de taille 480x360 pixels        */
  /* Titre "Test affichage graphique"                 */
    EcranGraphique.init(50,50,655,525,640,480,"Test affichage graphique");
  /* Choix d'un vert fonce comme couleur d'effacement */
  /* de la zone d'affichage                           */
    EcranGraphique.setClearColor(0,40,0);
  /* Ordre d'effacement de la zone d'affichage        */
    EcranGraphique.clear();
  /* Choix d'un rouge comme couleur de trace          */
    EcranGraphique.setColor(200,0,0);
  /* Ordre d'affichage d'un segment de droite         */
  /* Extremite initiale en position (40,70)           */
  /* Extremite finale en position (400,300)           */
    EcranGraphique.drawLine(40,70,400,300);
  /* Choix d'un bleu comme couleur de trace           */
    EcranGraphique.setColor(0,0,200);
  /* Ordre d'affichage d'un cercle                    */
  /* Centre en position (310,170)                     */
  /* Rayon de 90 pixels                               */    
    EcranGraphique.drawCircle(310,170,90);
  /* Envoi de la zone d'affichage dans la fenetre     */
    EcranGraphique.flush();
  /* Temporisation de 10000 milli-secondes            */
    EcranGraphique.wait(10000);
  /* Fermeture de la fenetre                          */
  /* et interruption de l'application                 */
    EcranGraphique.exit();
  }   
}
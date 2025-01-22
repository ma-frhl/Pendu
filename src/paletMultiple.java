import java.io.*;
// Import EcranGraphique venant de ./graphique/EcranGraphique.java

public class paletMultiple 
{
	static BufferedReader flux = new BufferedReader(new InputStreamReader(System.in)) ;
	
	public static void tracejeu () throws IOException
	{
		EcranGraphique.drawRect(30,30,1000,500);
		EcranGraphique.drawLine(530,30,530,530);
	}
	
	
	  public static void main(String [] args) throws IOException
	{
		int n,i,j,dist;
		System.out.println("saisir nbr palet");
		n=Integer.valueOf(flux.readLine()).intValue();
		int [][]P=new int [5][n];
		P[0][0]=60;
		for (i=0;i<n;i+=1)
		{
			P[4][i]=(int)(Math.random()*10)+4;
			P[2][i]=(int)(Math.random()*10)+1;
			P[3][i]=(int)(Math.random()*10)+1;
			P[1][i]=60;
			if (i>0)
			{
				P[0][i]=P[0][i-1]+P[4][i-1]+50+P[4][i];
			}
		}

		EcranGraphique.init(0,0,1200,700,1060,560,"Test affichage graphique");
		EcranGraphique.setColor(239,239,239);
		tracejeu();
	  /* Envoi de la zone d'affichage dans la fenetre     */
	   
		   while(true)
			  {
				  for (i=0;i<n;i+=1)
				  {
					if ((P[0][i]<=30+P[4][i]) || (P[0][i]>=1030-P[4][i]))
					  {
						  P[2][i]=-P[2][i];
					  }
					if ((P[1][i]<=30+P[4][i]) || (P[1][i]>=530-P[4][i]))
					  {
						  P[3][i]=-P[3][i];
					  }
					  for (j=0;j<n+1/2;j+=1)
					  {
						  if (i!=j)
						  {
							  dist=(int)(Math.sqrt(((P[0][i]-P[0][j])*(P[0][i]-P[0][j])+(P[1][i]-P[1][j])*(P[1][i]-P[1][j]))));
							  if (dist<P[4][i]+P[4][j])
							  {
								  P[2][i]=-P[2][i];
								  P[3][i]=-P[3][i];
								  P[2][j]=-P[2][j];
								  P[3][j]=-P[3][j];
							  }
						  }
					  }
					  P[0][i]+=P[2][i];
					  P[1][i]+=P[3][i];
					
					EcranGraphique.fillCircle(P[0][i],P[1][i],P[4][i]);
				  }
				  EcranGraphique.flush();
				  EcranGraphique.wait(30);
				  EcranGraphique.clear();
				  tracejeu();
			  }
		  /* Fermeture de la fenetre                          */
		  /* et interruption de l'application                 */
		    
	  }
// interet du flush ? car dans test affiche meme si pas la 
  // Temporisation de 10000 milli-secondes   juste pour affich ou tous prog?
}// difference private et public 
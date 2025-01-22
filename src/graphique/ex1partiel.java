import java.io.*;

public class ex1partiel

{
	static BufferedReader flux=new BufferedReader(new InputStreamReader(System.in));	
	private static boolean estBissextile(int annee)
	{
		boolean y;
		if (annee%4==0)
		{
			y=true;
		}
		else
		{
			if((annee%100==0)&&(annee%400==0))
			{
				y=true;
			}
			else
			{
				y=false;
			}
		}
	return (y);
	}
	
	private static int nbJours (int mois, int a)
	{
		int jours;
		if ((mois==1)||(mois==3)||(mois==5)||(mois==7)||(mois==8)||(mois==10)||(mois==12))
		{
			jours=31;
		}
		else
		{
			if(mois==2)
			{
				if(estBissextile(a))
				{
					jours=29;
				}
				else
				{
					jours=28;
				}
			}
			else
			{
				jours=30;
			}
		}
	return (jours);
	}
	
	public static void main(String [] args) throws IOException
	
	{
		int year, month, days;
		System.out.println("saisir une annee");
		year=Integer.valueOf(flux.readLine()).intValue();
		System.out.println("saisir un mois");
		month=Integer.valueOf(flux.readLine()).intValue();
		days=nbJours(month,year);
		System.out.println("il y a "+days+" jours pour le mois "+month+" de "+year);
	}
}
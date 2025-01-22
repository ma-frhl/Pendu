import java.io.*;

public class palet 
{
	
	public static void main(String [] args) throws IOException
	{
		int x,y,xinit,yinit,dx,dy,R;
		EcranGraphique.init(50,50,655,525,640,480,"Test palet");
		EcranGraphique.setClearColor(0,0,0);
		EcranGraphique.flush();
		
		xinit=170;
		yinit=170;

		R=40;
		
		EcranGraphique.fillCircle(xinit,yinit,R);
		EcranGraphique.setColor(0,154,0);
		EcranGraphique.flush();
		EcranGraphique.wait(80);
		EcranGraphique.clear();
		
		dx=10;
		dy=10;
		x=xinit;
		y=yinit;
		
		while (true)
		{
			x=x+dx; //j'avance petit a petit//
			y=y+dy;
			
			EcranGraphique.fillCircle(x,y,R);
			EcranGraphique.flush();
			EcranGraphique.wait(80);
			EcranGraphique.clear();
			
			if((x>=(640-R))||(x<=(0+R)))
			{
				dx=-dx;
			}
			if((y>=(480-R))||(y<=(0+R)))
			{
				dy=-dy;
			}
		}
		
	}
}
	
  
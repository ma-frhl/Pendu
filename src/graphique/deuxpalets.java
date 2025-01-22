import java.io.*;
public class deuxpalets
{
	
	public static void main(String [] args) throws IOException
	{
		int x, x2,y,y2,xinit, xinit2,yinit, yinit2,dx,dy,dx2,dy2,R,R2;
		EcranGraphique.init(50,50,655,525,640,480,"Test palet");
		EcranGraphique.setClearColor(0,0,0);
		EcranGraphique.flush();
		
		xinit=170;
		yinit=170;
		R=30;
		
		EcranGraphique.fillCircle(xinit,yinit,R);
		EcranGraphique.setColor(161,113,136);
		EcranGraphique.flush();
		
		
		xinit2=50;
		yinit2=50;
		R2=30;
		
		EcranGraphique.fillCircle(xinit2,yinit2,R2);
		EcranGraphique.setColor(173,216,230);
		EcranGraphique.flush();
		
		
		dx=10;
		dy=10;
		dx2=20;
		dy2=20;
		x=xinit;
		y=yinit;
		x2=xinit2;
		y2=yinit2;
		
		while (true)
		{
			x=x+dx; //j'avance petit a petit//
			y=y+dy;
			
			EcranGraphique.fillCircle(x,y,R);
			EcranGraphique.setColor(161,113,136);
			EcranGraphique.flush();
			
			
			x2=x2+dx2;
			y2=y2+dy2;
			
			EcranGraphique.fillCircle(x2,y2,R2);
			EcranGraphique.setColor(173,216,230);
			EcranGraphique.flush();
			
			
			if((x>=(640-R))||(x<=(0+R)))
			{
				dx=-dx;
			}
			if((y>=(480-R))||(y<=(0+R)))
			{
				dy=-dy;
			}
			if ((x2>=(640-R2))||(x2<=(0+R2)))
			{
				dx2=-dx2;
			}
			if ((y2>=(480-R2))||(y2<=(0+R2)))
			{
				dy2=-dy2;
			}
			
			EcranGraphique.wait(80);
			EcranGraphique.clear();
		}
		
	}
}
	
  
import java.math.*;


public class Program
{
	public static void drawSquare()
	{
		Turtle square = new Turtle();
		square.hide();
		
		double x = square.getX();
		double y = square.getY();
		
		int distance = 100;
		for(int i=0;i<4;i++)
		{
			square.forward(distance);
			square.left(90);
		}
	}
	
	public static void drawStar(Turtle bob)
	{
		double x = bob.getX();
		double y = bob.getY();
		int angle = 144;
		int distance = 80;
		bob.left(110);
		
		for(int i=0;i<5;i++)
		{
			bob.left(angle);
			bob.forward(distance);

		}
		
		
	}
	
	public static void drawCircle(Turtle bob, double radius)
	{
		double x = bob.getX();
		double y = bob.getY();
		
		int sides = 360;
		double sumOfAngles = (sides-2)*180;
		double angle = sumOfAngles/sides;
		double sideLength = (Math.PI*radius)/(sides/2);
		
		bob.up();
		bob.forward(radius);
		bob.left(90);
		bob.down();
		
		bob.speed(0);
		for(int i=0;i<sides;i++)
		{
			bob.forward(sideLength);
			bob.left(180-angle);
		}
		
		bob.up();
		bob.setPosition(x,y, 0);
		
	}
	
	public static double getDirection(Turtle bob, Turtle bob2)
	{
		double x = bob2.getX();
		double y = bob2.getY();
		
		return bob.towards(x, y);
	}
	
	public static int posOrNeg()
	{
		double random = Math.random();
		if(random>0.50) return 1;
		else return -1;
	}
	public static void main(String[] args)
	{
		Turtle bob = new Turtle();
		Turtle bob2 = new Turtle();
		
		
		//~ //bob.hide();
		//~ //bob2.hide();
		
		bob.speed(0);
		bob2.speed(0);
		
		drawSquare();
		
		for(int i=0;i<50;i++)
		{
			int x = 0;
			int y = 0;
			
			bob.up();
			bob.setPosition(x,y);
			bob.down();
			
			drawStar(bob);
		}
		bob.home();
		bob.up();
		bob.forward(250);
		bob.down();
		
		for(int i=0;i<50;i++)
		{
			drawCircle(bob, 100);
			bob.forward(40+(i*5));
			drawCircle(bob, 100);
		}
		
		bob2.left(180);
		bob2.up();
		bob2.forward(250);
		bob.down();
		
		for(int i=0;i<50;i++)
		{
			drawCircle(bob2, 100);
			bob2.forward(40+(i*5));
			drawCircle(bob2, 100);
		}
		
		bob.forward(250);
		bob2.forward(250);
		
		bob.speed(200);
		bob2.speed(210);
		bob.up();
		bob2.up();
		
		
		bob.shape("pacman.png");
		bob.shapeSize(30, 30);
		bob2.shape("ghost.png");
		bob2.shapeSize(30, 30);
		
		bob.show();
		bob2.show();
		
		int lives = 0;
		bob.setPosition(0,20);
		bob2.setPosition(0,0);
		while(true)
		{
			int x = (int)(Math.random()*100*posOrNeg());
			int y = (int)(Math.random()*100*posOrNeg());
			
			bob.face(x,y);
			bob.forward(50);
			
			bob2.setDirection(getDirection(bob2, bob));
			bob2.forward(20);
			
			int x1 = (int)(bob.getX());
			int y1 = (int)(bob.getY());
			
			int x2 = (int)(bob2.getX());
			int y2 = (int)(bob2.getY());
			
			double distance = Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
			

			if(distance<20)	lives++;
			if(lives==3)
			{
				bob.exit();
				break;
			 }
		}
			
		System.out.println("AND THEN PACMAN DIED!!!!");
	}
}

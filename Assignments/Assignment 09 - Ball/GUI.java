import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GUI extends JPanel implements ActionListener, KeyListener
{

	Timer tm;
	int velX;
	int velY;
	
    Ball b;
    double printPressure;
	boolean dropDone;
	boolean reboundDone;
	boolean isBounced;
	boolean isInflated; 
	boolean isDeflated;
	boolean isStabbed;
	boolean wasStabbed;
	boolean isPatched;
	boolean ballReset;
	boolean guiReset;
    
    public GUI(Ball ball)
    {
		velX = 2;
		velY = 1;

		//ball stuff
		b = ball;
		printPressure = 0;
		dropDone = false;
		reboundDone = false;
		isBounced = false; 
		isInflated = false;
		isDeflated = false;
		isStabbed = false;
		wasStabbed = false;
		isPatched = false;
		ballReset = false;
		guiReset = false;

		
		//input stuff
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		
		//timer stuff
		tm = new Timer(15, this);
		tm.start();
	}
 
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.RED);
        
        g.fillOval(b.ballCoords[0], b.ballCoords[1], 20, 20);
    }
    
	public void actionPerformed(ActionEvent e)
	{
		if(isBounced == true)
		{
			if(dropDone == false) initialDrop();
			if(reboundDone == false && dropDone == true) rebound();
			repaint();
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE) guiReset = true;
		if(key == KeyEvent.VK_E) ballReset = true;
		if(key == KeyEvent.VK_SPACE) isBounced = true;
		if(key == KeyEvent.VK_W) isInflated = true;
		if(key == KeyEvent.VK_S) isDeflated = true;
		if(key == KeyEvent.VK_F)
		{
			 wasStabbed = true;
			 isStabbed = true;
		 }

		if(key == KeyEvent.VK_G)
		{
			if(wasStabbed == false) isPatched = false;
			else isPatched = true;
		}
		if(key == KeyEvent.VK_Q) printPressure = b.checkPressure();
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e)
	{
		if(isBounced) b.bounce();

		if(isInflated)
		{
			b.inflate();
			System.out.println("WHOOOSH");
			isInflated = false; 
		}

		if(isDeflated)
		{
			b.deflate();
			System.out.println("pffftttt");
			isDeflated = false;
		}
		
		if(isPatched)
		{
			b.patch();
			System.out.println("*Slaps flex tape on that bad boy*");
			wasStabbed= false;
			isPatched = false;
		}

		if(isStabbed)
		{
			b.stab();
			System.out.println("**Stab noises**");
			isStabbed = false;
		}
		
		if(printPressure != 0)
		{
			System.out.println("\nThe pressure is "+printPressure+" psi");
			printPressure = 0;
		}
		
		if(guiReset)
		{
			reset();
			guiReset = false;
		}
		
		if(ballReset)
		{
			b.reset();
			ballReset = false;
		}

	}
	
	public void initialDrop()
    {
        if(b.ballCoords[1] == 430) dropDone = true;
        else b.ballCoords[1] += velY;
    }

    public void rebound()
    {
		if(b.newBallCoords[1] == 501)
		{
			isBounced = false;
			System.out.println("KAPOOW -- ball busted"); 
		}
		else if(b.newBallCoords[1] == 500)
		{	
			isBounced = false;
			System.out.println("THUD -- ball didn't bounce.");
		}
		else if(b.ballCoords[1] == b.newBallCoords[1]) isBounced = false;
		else b.ballCoords[1] -= velY;
	}
	
	public void reset()
	{
		b.ballCoords = new int[]{250, 250}; //cm
		dropDone = false;
		reboundDone = false;
		isBounced = false; 
		isInflated = false;
		tm = new Timer(25, this);
		repaint();
	}

}

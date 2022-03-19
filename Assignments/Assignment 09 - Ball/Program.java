import java.awt.*;
import javax.swing.*;
 
public class Program extends JPanel
{
	public static void playBall()
	{
		System.out.println("ESC - Reset Ball's position"+
		"\nE - Reset Ball's parameters \nSPACE - Bounce Ball"+
		"\nW - Inflates Ball \nS - Deflates Ball \nF - Stabs the Ball"+
		"\nG - Patches Ball");
		
		System.out.println("Patch will only do a thing if you've stabbed it.");
		
		Ball ball = new Ball();
		GUI g = new GUI(ball);
		JFrame frame = new JFrame();
		
		frame.setTitle("Ball");
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(g);
	}
	
	public static void main(String[] args)
    {
		playBall();
	}
}

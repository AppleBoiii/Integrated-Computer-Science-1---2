import java.awt.*;
import javax.swing.*;
 
public class JoshGay extends JPanel
{
	public static void playBall()
	{
		JFrame frame = new JFrame();
		JoshGay1 panel=new JoshGay1();
		frame.getContentPane().add(panel);
		frame.setTitle("Ball");
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args)
    {
		playBall();
	}
}




class JoshGay1 extends JPanel
{
	public void paint(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(10));
		g2.drawString(isHeGay(),20,20);
		g2.drawString(willHeEverNotBeGay(),20,40);
	}
	
	
	public String isHeGay()
	{
		return "yes";
	}
	
	public String willHeEverNotBeGay()
	{
		return "no";
	}
}



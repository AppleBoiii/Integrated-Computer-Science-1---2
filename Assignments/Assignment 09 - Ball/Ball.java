
import javax.swing.*;

class Ball extends JPanel
{
    public int[] ballCoords;
    public int[] newBallCoords;
    private double pressure; 
    private boolean hasHole;
    //pressure (in psi)
        //domain for pressure (4.5, 9)
        //too high = burst, too low = no bounce
    //isHole   (true/false)
        //if true continuosly lose pressure

    public Ball()
    {   
        ballCoords = new int[]{250, 250}; //cm
        newBallCoords = new int[]{250, 250};
        pressure = 8;
        hasHole = false;
    }

    public Ball(double pressure)
    {
        ballCoords = new int[]{250, 250};
        newBallCoords = new int[]{250, 250};
		this.pressure = pressure;
		hasHole = false;
	}

    public void bounce()
    {
        if(hasHole) deflate();

		int newY;
		if(pressure > 9) newY = -1; //MAKE THIS BURST
		else if(pressure < 4.5) newY = 0; //THUDs
		else
		{
			double y = ((17.22+pressure)/17.209)*100;
			newY = (int)(Math.round(y));
		}
        
        int newX = (int)(Math.random()*(10 - (-11)+1) + -11); //ball "tilt" as it comes back up

        newBallCoords[0] = newX;
        newBallCoords[1] = 500-newY;
    }
    
    public void inflate()
    {
		if(hasHole == false)
		{
			if(pressure == 9.5);
			else pressure += 0.5;
		}
    }
    
    public void deflate()
    {
        if(pressure == 4);
        else pressure -= 0.5;
    }

    public void stab()
    {
        hasHole = true;
    }

    public void patch()
    {
        hasHole = false;
    }

    public double checkPressure()
    {
        return pressure;
    }

    public void reset()
    {
        //ballCoords = new int[]{250, 250}; //cm
        newBallCoords = new int[]{250, 250};
        pressure = 8;
        hasHole = false;
    }



}


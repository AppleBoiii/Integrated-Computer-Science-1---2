import java.awt.*; //frames
import java.awt.event.*; //listener

import javax.swing.*; //jframes

public class Program
{
    public static void main(String[] args)
    {
        //EVENT DRIVEN PROGRAMMING
        JFrame window = new JFrame("Example"); //essentially makes a new window: argument is the titlebar 
        CustomPanel panel = new CustomPanel();
        panel.setPreferredSize(new Dimension(640, 640));
        panel.setFocusable(true);

        panel.addMouseMotionListener(panel);
        panel.addKeyListener(panel); //FOCUS: easy fix, change panel.add to window.add otherwise requestFocus();
        panel.addMouseWheelListener(panel);
        panel.addMouseListener(panel);
        Timer t = new Timer(33, panel); //tells the timer the panel will have the action listener
        t.start();

        window.add(panel);
        window.pack();
        window.setVisible(true);
        panel.requestFocus();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class CustomPanel extends JPanel implements MouseMotionListener, KeyListener, MouseWheelListener, ActionListener, MouseListener
{
    double x = 0;
    double y = 0;
    double vx = 0;
    double vy = 0;
    double r = 50;

    //lights out game
    int[][] lightsMap;
    static final int OFF = 0;
    static final int ON = 1;
    int mouseX;
    int mouseY;

    Color highlightColor = Color.BLUE;

    // double mouseX = 0;
    // double mouseY = 0;
    Color c = Color.BLACK;

    public CustomPanel()
    {
        reset(3);
    }

    //@override
    public void paint(Graphics g)
    {
        //to do own stuff and old stuff
        //add super.
        // super.paint(g);

        // g.setColor(Color.WHITE);
        // g.fillRect(0, 0, getWidth(), getHeight());
        // g.setColor(c);
        // g.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
        // g.fillOval(0, 0, getWidth()/3, 100);

        
        boolean winner = hasWon();

        Graphics2D g2 = (Graphics2D)g;  //adds anti-aliasing
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        int cellSize = getWidth()/lightsMap.length;
        int currentRow = mouseY / cellSize;
        int currentCol = mouseX / cellSize;
        
        g.setColor(Color.BLACK);
        for(int row=0;row<lightsMap.length;row++)
        {
            for(int col=0;col<lightsMap[0].length;col++)
            {   
                if(!winner & Math.abs(col-currentCol)+Math.abs(row-currentRow) <= 1)
                {
                    g.setColor(highlightColor);
                    g.fillRect(col*cellSize, row*cellSize, cellSize, cellSize);
                }

                g.setColor(lightsMap[row][col] == ON? Color.YELLOW : Color.BLACK); //if statement
                g.fillOval(col*cellSize, row*cellSize, cellSize, cellSize);
            }
        }

        if(winner)
        {
            g.setColor(new Color(255, 0, 255, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
            Font myFont = new Font("Ubuntu", Font.BOLD, 48);
            g.setFont(myFont);
            String winString = "YOU WIN!!";
            int textWidth = g.getFontMetrics().stringWidth(winString);
            int textHeight = g.getFontMetrics().getHeight();
            g.drawString(winString, (getWidth()/2)-textWidth/2, getHeight()/2 + textHeight/2);
        }

    }

    public boolean hasWon()
    {
        for(int row=0;row<lightsMap.length;row++)
            for(int col=0;col<lightsMap[0].length;col++)
                if(lightsMap[row][col] == ON) return false;

        return true;
    }

    public void reset(int newSize)
    {
        lightsMap = new int[newSize][newSize];

        for(int i=0;i<newSize*newSize;i++)
        {
            int row = (int)(Math.random()*newSize-1);
            int col = (int)(Math.random()*newSize-1);
            toggleCross(row, col);
        }

        repaint();
    }

    public void toggle(int row, int col)
    {
        if(row < 0 || row > lightsMap.length || col < 0 || col > lightsMap[0].length) return;
        lightsMap[row][col] = 1-lightsMap[row][col];
    }

    public void toggleCross(int row, int col)
    {
        toggle(row, col);
        toggle(row+1, col);
        toggle(row-1, col);
        toggle(row, col-1);
        toggle(row, col+1);
    }

    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        // repaint();
    }

    public void mouseDragged(MouseEvent e)
    {

    }

    public void keyReleased(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent e)
    {   
        System.out.println(e);
        if(e.getKeyCode()==KeyEvent.VK_R) reset(5);
        // if(e.getKeyCode()==KeyEvent.VK_B) c=Color.BLUE;
        // if(e.getKeyCode()==KeyEvent.VK_G) c=Color.GREEN;

        repaint();
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void mouseWheelMoved(MouseWheelEvent e)
    {   
        r = r*Math.pow(0.95, e.getWheelRotation());

        repaint();
    }

    public void actionPerformed(ActionEvent event)
    {   
        double x = System.nanoTime() / 1e9;
        double period = 1.5;
        double v = Math.sin(x/period*2*Math.PI);
        highlightColor = new Color((int)(128+100*v), (int)(128-100*v),  (int)(128+100*v));

        repaint();

    }

    public void mouseExited(MouseEvent e) //when mouse exits a componet
    {

    }

    public void mouseEvent(MouseEvent e)  
    {

    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {
        if(!hasWon())
        {
            int cellSize = getWidth()/lightsMap.length;
            int x = e.getX();
            int y = e.getY();

            int row = y/cellSize;
            int col = x/cellSize;
            toggleCross(row, col);
        }

        else
        {
            reset(lightsMap.length+1);
        }
        // repaint();
    }

    public void mouseClicked(MouseEvent e)
    {

    }

}

//ASSIGNMENT
//*keyboard events
//*mouse events
//*point / goal

//idea
//

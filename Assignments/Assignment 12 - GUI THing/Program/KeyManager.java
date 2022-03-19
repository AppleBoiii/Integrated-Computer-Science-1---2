import java.awt.event.*;
import javax.swing.event.MouseInputListener;


public class KeyManager implements KeyListener, MouseInputListener
{

    private boolean[] keys;
    public boolean up, down, left, right, acornTaken, proceed;
    public boolean clicked;
    protected Handler handler;

    public KeyManager(Handler handler)
    {
        keys = new boolean[256];
        this.handler = handler;
    }

    public void tick()
    {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        acornTaken = keys[KeyEvent.VK_F];
        proceed = keys[KeyEvent.VK_SPACE];

        // if(handler.getGame().hasWon && keys[KeyEvent.VK_SPACE] == true)
        // {
        //     System.out.println("Checking..");
        //     handler.getGame().hasWon = false;
        //     handler.getGame().stop();
        //     handler.getGame().start();
        // }
    }

    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
        // System.out.println("Pressed.");
    }

    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }


    public void keyTyped(KeyEvent e)
    {
        // keys[e.getKeyCode()] = false;
    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mouseClicked(MouseEvent e)
    {
        clicked = true;
    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {
        clicked = false;
        System.out.println("Unlicked.");
    }

    public void mouseMoved(MouseEvent e)
    {

    }

    public void mouseDragged(MouseEvent e)
    {

    }

}

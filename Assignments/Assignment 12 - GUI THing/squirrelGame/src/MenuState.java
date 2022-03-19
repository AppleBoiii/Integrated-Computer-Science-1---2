import java.awt.*;

public class MenuState extends State
{
    public MenuState(Handler handler)
    {
        super(handler);
    }

    public void tick()
    {

        if(handler.getGame().getKeyManager().clicked) State.setState(handler.getGame().gameState);
    }

    public void render(Graphics g)
    {
        g.setColor(new Color(255, 204, 51, 25));
        g.fillRect(0, 0, handler.getGame().width, handler.getGame().height);
        g.setColor(Color.BLACK);

        String text = "Gather all 4 acorns, then escape through the golden exit.";
        String textTwo = "F to gather acorns, WASD to move. Click to continue";

        int width = g.getFontMetrics().stringWidth(text);
        int height = g.getFontMetrics().getHeight();
        int widthTwo = g.getFontMetrics().stringWidth(textTwo);

        g.drawString(text, (int)(handler.getGame().width/2) - width/2, (int)(handler.getGame().height/2) ); 
        g.drawString(textTwo, (int)(handler.getGame().width/2) - widthTwo/2, (int)(handler.getGame().height/2) + height/2 + 10 );
    }
}

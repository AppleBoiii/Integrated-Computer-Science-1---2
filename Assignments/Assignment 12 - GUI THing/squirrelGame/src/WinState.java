import java.awt.*;

public class WinState extends State
{
    public WinState(Handler handler)
    {
        super(handler);
    }

    public void tick()
    {
        if(handler.getGame().getKeyManager().proceed) handler.getGame().reset(); //State.setState(new GameState(handler));
    }

    public void render(Graphics g)
    {
        g.setColor(new Color(255, 204, 51, 25));
        g.fillRect(0, 0, handler.getGame().width, handler.getGame().height);
        g.setColor(Color.BLACK);

        String text = "You Win!";
        String textTwo = "Press Space to Play Again";
        int width = g.getFontMetrics().stringWidth(text);
        int height = g.getFontMetrics().getHeight();
        int widthTwo = g.getFontMetrics().stringWidth(textTwo);

        g.drawString(text, (int)(handler.getGame().width/2) - width/2, (int)(handler.getGame().height/2) ); 
        g.drawString(textTwo, (int)(handler.getGame().width/2) - widthTwo/2, (int)(handler.getGame().height/2) + height/2 + 5 );
    }
}

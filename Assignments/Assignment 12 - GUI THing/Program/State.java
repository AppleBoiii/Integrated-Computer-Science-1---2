import java.awt.*;

public abstract class State 
{
    protected Handler handler;
    
    public State(Handler handler)
    {
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);



    private static State currentState = null;
    public static void setState(State state)
    {
        currentState = state;
    }

    public static State state()
    {
        return currentState;
    }

}

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game implements Runnable
{
    private Display display;
    int width, height;
    String title;
    public boolean hasWon = false;

    private Thread thread;
    private Boolean running;

    private BufferStrategy bs; //organizes stuff drawn on screen; a buffer is memory in computer that holds data that goes on screen
    private Graphics g;

    public State gameState;
    public State winState;
    public State menuState;

    private Camera camera;
    private Handler handler;
    private KeyManager km;
    private KeyManager kmTwo;

    public Game(String title, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        running = false;

        System.out.println("Game created!");
        // display = new Display(title, width, height); 

        
    }

    private void init()
    {
        handler = new Handler(this);
        km = new KeyManager(handler);
        kmTwo = new KeyManager(handler);

        display = new Display(title, width, height); 
        display.getJFrame().addKeyListener(km);
        display.getJFrame().addMouseListener(km);
        display.getCanvas().addMouseListener(km);

        camera = new Camera(this, 0, 0);
        AssetLoader.init();;

        menuState = new MenuState(handler);
        gameState = new GameState(handler);
        winState = new WinState(handler);

        // if(hasWon) State.setState(winState);
        // else State.setState(gameState);

        State.setState(menuState);

    }

    private void tick()
    {
        km.tick();
        if(State.state() != null) State.state().tick();
        
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height); //clears the screen everytime game renders

        // g.setColor(Color.GREEN);
        // g.fillRect(0, 0, width, height);
        // g.drawImage(AssetLoader.rightSquirrel, x, height/2, null);
        // g.drawImage(AssetLoader.leftSquirrel, width/2+32, height/2, null);
        // g.drawImage(AssetLoader.backSquirrel, width/2, height/2+32, null);
        // g.drawImage(AssetLoader.frontSquirrel, width/2, height/2-32, null);
        
        if(State.state() != null) State.state().render(g); //calls static func state() in the State.java class
        //which renders whatever state is active. 


        bs.show();
        g.dispose();
    }
    public void run()
    {
        init();

        int fps = 60;
        int ticks = 0;
        double timePerTick = 1e9 / fps; //time in nano seconds to wait before calling game loop
        double dTime = 0;
        long currentTime;
        long lastTime = System.nanoTime();
        long timer = 0;

        while(running) //this is the game loop, it has to update all the vars (player pos, enemies, environ, etc)
        {
            currentTime = System.nanoTime();
            dTime += (currentTime - lastTime) / timePerTick; //all this means: divides the change in time by 
            //number of nano seconds we want to pass before rendering again
            timer += currentTime - lastTime;

            lastTime = currentTime;
            
            if(dTime >= 1) //if dTime >=1 that means that much time has passed, so its time to render again
            {
                tick();
                render();
                ticks++;
                dTime--;
            }

            // if(timer > 1e9)
            // {
            //     System.out.println("FPS: "+ticks);
            //     ticks = 0;
            //     timer = 0;
            // }
        }

        stop();
    }

    public synchronized void start() //synchronized makes it so only one thread can access this at a time
    {
        if(running) return;
        running = true;
        thread = new Thread(this); //tells the thread to run THIS class, e.g. the game class, so makes a new thread for Game obj
        thread.start(); //basically calls run method
    }

    public synchronized void stop()
    {
        display.close();
        if(!running) return;    
        running = false;
        try { thread.join(); }
        catch(InterruptedException e) { e.printStackTrace(); }
    }

    public void reset()
    {
        Tile.reset();
        gameState = new GameState(handler);
        State.setState(gameState);
    }

    public KeyManager getKeyManager()
    {
        return km;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public State getGameState()
    {
        return gameState;
    }

}

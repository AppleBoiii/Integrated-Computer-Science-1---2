import java.awt.*;

public class GameState extends State
{   
    private Player player;
    private World world;

    public GameState(Handler handler)
    {   
        super(handler); //calls the constructor of the State class
        world = new World(handler, "world2.txt");
        handler.setWorld(world);
        player = new Player(handler, world.spawnX, world.spawnY);

    }

    public void tick()
    {
        if(handler.getWorld().acornCount == 0 && player.isOnExit() ) State.setState(handler.getGame().winState);
        world.tick();
        player.tick();
    }

    public void render(Graphics g)
    {
        // Tile.tiles[0].render(g, 0, 0);
        world.render(g);
        player.render(g);
    }
}

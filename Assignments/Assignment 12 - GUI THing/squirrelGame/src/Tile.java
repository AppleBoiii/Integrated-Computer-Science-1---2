import java.awt.image.*;
import java.awt.*;

public class Tile
{
    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
    public static Tile[] tiles = new Tile[256];
    public static Tile grass = new GrassTile(0);
    public static Tile wall = new WallTile(1);
    public static Tile border = new BorderTile(2);
    public static Tile acornOne = new AcornTile(3);
    public static Tile acornTwo = new AcornTile(4);
    public static Tile acornThree = new AcornTile(5);
    public static Tile acornFour = new AcornTile(6);
    public static Tile gate = new GateTile(7);

    public static void reset()
    {
        tiles = new Tile[256];
        grass = new GrassTile(0);
        wall = new WallTile(1);
        border = new BorderTile(2);
        acornOne = new AcornTile(3);
        acornTwo = new AcornTile(4);
        acornThree = new AcornTile(5);
        acornFour = new AcornTile(6);
        gate = new GateTile(7);
    }



    //CLASS
    public BufferedImage texture;
    protected final int id;
    public boolean taken = true;
    public boolean open = false;

    public Tile(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }


    public void tick()
    {

    }

    public void render(Graphics g, int x, int y)
    {   

        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid()
    {
        return false;
    }

    public int getId()
    {
        return id;
    }

    public boolean isAcorn()
    {
        return false;
    }

    public boolean isTaken()
    {
        return taken;
    }

    public boolean isExit()
    {
        return false;
    }
}

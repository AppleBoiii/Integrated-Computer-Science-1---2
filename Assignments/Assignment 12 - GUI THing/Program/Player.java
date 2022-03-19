import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature
{

    private BufferedImage texture;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        bounds.x = 12;
        bounds. y = 14;
        bounds.width = 12;
        bounds.height = 14;

        texture = AssetLoader.rightSquirrel;

    }

    public void tick()
    {
        getInput();
        move();
        handler.getCamera().centerOnEntity(this);
    }

    public boolean isOnExit()
    {
        int q = (int)(x / Tile.TILE_WIDTH);
        int w = (int)(y / Tile.TILE_HEIGHT);

        return handler.getWorld().getTile(q, w).isExit() && !handler.getWorld().getTile(q, w).isSolid();
    }

    private void getInput()
    {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up) 
        {
            yMove = -velocity;
            texture = AssetLoader.backSquirrel;
        }
        if(handler.getKeyManager().down)
        {
            yMove = velocity;
            texture = AssetLoader.frontSquirrel;
        }
        if(handler.getKeyManager().right)
        {
            xMove = velocity;
            texture = AssetLoader.rightSquirrel;
        }
        if(handler.getKeyManager().left)
        {
            xMove = -velocity;
            texture = AssetLoader.leftSquirrel;
        }
        if(handler.getKeyManager().acornTaken)
        {   
            int q = (int)(x / Tile.TILE_WIDTH) + 1;
            int w = (int)(y / Tile.TILE_HEIGHT) + 1;
            if(handler.getWorld().getTile((int) q, (int) w).isAcorn() && !handler.getWorld().getTile((int) q, (int) w).isTaken() )
            {
                handler.getWorld().getTile((int) q, (int) w).taken = true;
                handler.getWorld().acornCount--;
            }

            texture = AssetLoader.pickup;
            // handler.getKeyManager().acornTaken = false;
            // System.out.println("Acorns Left: "+handler.getWorld().acornCount);
        }
    }

    public void render(Graphics g)
    {
        g.setColor(new Color(255, 255, 255, 0));
        g.fillRect((int)(x + bounds.x - handler.getCamera().getXOffset()), (int)(y+bounds.y - handler.getCamera().getYOffset()), bounds.width, bounds.height);
        g.drawImage(texture, (int)(x - handler.getCamera().getXOffset()), 
        (int)(y - handler.getCamera().getYOffset()), w, h, null);

        // g.drawImage(AssetLoader.rightSquirrel, (int)(x), 
        // (int)(y), w, h, null);
    }
}

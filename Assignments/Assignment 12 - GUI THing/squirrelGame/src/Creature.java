import java.awt.*;

public abstract class Creature extends Entity
{   
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_VELOCITY = 2.5f;
    public static final int DEFAULT_WIDTH = 32,
                        DEFAULT_HEIGHT = 32;

    protected int health;
    protected float velocity;
    protected float xMove;
    protected float yMove;

    public Creature(Handler handler, float x, float y, int w, int h)
    {
        super(handler, x, y, w, h); //SUPERS to  constructing via Entity class
        health = DEFAULT_HEALTH;
        velocity = DEFAULT_VELOCITY;
        xMove = 0;
        yMove = 0;
    }

    public void move()
    {
        moveX();
        moveY();
    }

    public void moveX()
    {
        if(xMove > 0) // moving right
        {
            // System.out.println("Going right..");
            int z = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if( !isCollision(z, (int)(y+bounds.y) / Tile.TILE_HEIGHT) && !isCollision(z, (int)(y + bounds.y + bounds.height) / Tile.TILE_HEIGHT) ) x += xMove;
        }

        else if(xMove < 0) //moving left
        {
            int z = (int) (x+xMove+bounds.x) / Tile.TILE_WIDTH;
            if( !isCollision(z, (int)(y+bounds.y) / Tile.TILE_HEIGHT)  && !isCollision(z, (int)(y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) x += xMove;
        }
    }

    public void moveY()
    {
        if(yMove < 0) //going down
        {
            int z = (int) (y+yMove+bounds.y) / Tile.TILE_HEIGHT;
            if(!isCollision( (int) (x+bounds.x) / Tile.TILE_WIDTH, z)  && !isCollision( (int)(x+bounds.x + bounds.width) / Tile.TILE_WIDTH, z)) y += yMove;
        }

        else if(yMove > 0) //going up
        {
            int z = (int) (y+yMove+bounds.y+bounds.height) / Tile.TILE_HEIGHT;
            if( !isCollision( (int) (x+bounds.x / Tile.TILE_WIDTH ), z) && !isCollision( (int) (x+bounds.x + bounds.width) / Tile.TILE_WIDTH, z)) y += yMove;
        }
    }

    protected boolean isCollision(int x, int y)
    {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //these are set / get funcs
    public float getXMove() {
        return this.xMove;
    }

    public void setXMove(float xMove) {
        this.xMove = xMove;
    }

    public float getYMove() {
        return this.yMove;
    }

    public void setYMove(float yMove) {
        this.yMove = yMove;
    }

}

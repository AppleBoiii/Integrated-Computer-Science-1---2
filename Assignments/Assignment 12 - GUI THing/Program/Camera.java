public class Camera 
{   

    private Game game;
    private float xOffset;
    private float yOffset;

    public Camera(Game game, float xOffset, float yOffset)
    {
        this.game = game;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnEntity(Entity e)
    {
        xOffset = e.getX() - game.width / 2;
        yOffset = e.getY() - game.width / 2;
    }

    public void move(float x, float y)
    {
        xOffset += x;
        yOffset += y;
    }

    public float getXOffset() 
    {
        return this.xOffset;
    }

    public void setXOffset(float xOffset) 
    {
        this.xOffset = xOffset;
    }

    public float getYOffset() 
    {
        return this.yOffset;
    }

    public void setYOffset(float yOffset) 
    {
        this.yOffset = yOffset;
    }
}

import java.awt.*;

public abstract class Entity 
{
    protected Handler handler;
    protected float x;
    protected float y; // apparently floats are better for smoother movements (internet told me)
    protected int w;
    protected int h;

    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int w, int h)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        bounds = new Rectangle(0, 0, w, h);
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

}

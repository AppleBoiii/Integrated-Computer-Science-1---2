import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AssetLoader //used to load images into the game
{   
    private static final int w = 32, h = 32; //this is the height and width of the grides in the squirrel sheet i stole

    public static BufferedImage rightSquirrel, leftSquirrel, frontSquirrel, backSquirrel, pickup, grass, wall, border, open_gate, closed_gate, acorn;

    public static void init()
    {
        SpriteLoader sl = new SpriteLoader("/squirrels.png");
        // SpriteLoader grass = new SpriteLoader("/grass.png");
        
        rightSquirrel = sl.crop(0, 0, w, h);
        leftSquirrel = sl.crop(0, w, w, h);
        frontSquirrel = sl.crop(0, w*2, w, h);
        backSquirrel = sl.crop(0, w*3, w, h);
        pickup = sl.crop(w*2, h*7, w, h);
        try 
        { 
            grass = ImageIO.read(AssetLoader.class.getResource("/grass.png.")); 
            wall = ImageIO.read(AssetLoader.class.getResource("/wall.png."));
            border = ImageIO.read(AssetLoader.class.getResource("/border.png"));
            open_gate = ImageIO.read(AssetLoader.class.getResource("/gate.png"));
            closed_gate = ImageIO.read(AssetLoader.class.getResource("/wall.png."));
            acorn = ImageIO.read(AssetLoader.class.getResource("/acorn.png."));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.exit(1); //exits if no image
        }
    }

    public static void reset()
    {
        
    }
}

class SpriteLoader
{ 
    private BufferedImage bi; //BufferedImage = how Java handle image

    public SpriteLoader(String path)
    {   
        try { bi = ImageIO.read(SpriteLoader.class.getResource(path)); } //opens image at path)
        catch(IOException e)
        {
            e.printStackTrace();
            System.exit(1); //exits if no image
        }
    }

    public BufferedImage crop(int x, int y, int w, int h)
    {
        return bi.getSubimage(x, y, w, h);
    }

}

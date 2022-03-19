import java.awt.*;

public class World 
{
    private Handler handler;
    private int w, h;
    public int spawnX, spawnY;
    private int[][] map;
    // private int[][] acornMap;
    public int acornCount;

    // public int acornCount;

    public World(Handler handler, String path)
    {
        this.handler = handler;
        acornCount = 4;
        loadWorld(path);
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {


           //this only renders the tiles that the player 'can see'
           int xStart = (int) Math.max(0, handler.getCamera().getXOffset() / Tile.TILE_WIDTH);
           int xEnd = (int) Math.min(w, (handler.getCamera().getXOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
           int yStart = (int) Math.max(0, handler.getCamera().getYOffset() / Tile.TILE_HEIGHT);
           int yEnd = (int) Math.min(h, (handler.getCamera().getYOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
   
        for(int i=yStart;i<yEnd;i++)
        {
            for(int j=xStart;j<xEnd;j++)
            {
                if(acornCount == 0 && getTile(j, i).isExit()) getTile(j, i).open = true;

                if(getTile(j, i).isExit() && !getTile(j, i).isSolid() ) getTile(j, i).texture = AssetLoader.open_gate; //renders the open gate texture if gate open

                if(getTile(j, i).isAcorn() && getTile(j, i).isTaken())
                {
                    getTile(j, i).texture = AssetLoader.grass; //make it so if taken acorn texture -> grass texture
                    // getTile(j, i).taken = false;
                }

                getTile(j, i).render(g, (int)(j*Tile.TILE_WIDTH - handler.getCamera().getXOffset()), 
                                        (int)(i*Tile.TILE_HEIGHT - handler.getCamera().getYOffset()));
                // getTile(j, i).render(g, (int)(j*Tile.TILE_WIDTH), 
                // (int)(i*Tile.TILE_HEIGHT));
            }
        }
    }

    public Tile getTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= w || y >= h) return Tile.grass;

        Tile t = Tile.tiles[map[x][y]];
        if(t == null) return Tile.grass;

        return t;
    }

    private void loadWorld(String path)
    {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+"); //splits file on any whitespace
        w = Utils.parseInt(tokens[0]);
        h = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        map = new int[h][w];
        for(int i=0;i<h;i++)
        {
            for(int j=0;j<w;j++) map[i][j] = Utils.parseInt(tokens[(i+j*w)+4]);
        
        }
    }
}

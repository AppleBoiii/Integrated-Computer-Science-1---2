public class Handler 
{
    private Game game;
    private World world;

    public Handler(Game game)
    {
        this.game = game;
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public Camera getCamera()
    {
        return game.getCamera();
    }

    public int getWidth()
    {
        return game.width;
    }

    public int getHeight()
    {
        return game.height;
    }
    
    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

}

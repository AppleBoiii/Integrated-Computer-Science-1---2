public class GateTile extends Tile
{

    public GateTile(int id)
    {
        super(AssetLoader.closed_gate, id);
        open = false;
    }

    public boolean isSolid()
    {
        return !open;
    }

    public boolean isExit()
    {
        return true;
    }


}

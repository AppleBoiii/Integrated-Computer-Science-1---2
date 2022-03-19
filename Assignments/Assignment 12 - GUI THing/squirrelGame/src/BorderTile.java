public class BorderTile extends Tile
{
    public BorderTile(int id)
    {
        super(AssetLoader.border, id);
    }

    public boolean isSolid()
    {
        return true;
    }
    
}

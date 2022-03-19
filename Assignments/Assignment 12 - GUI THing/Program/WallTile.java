public class WallTile extends Tile
{
    public WallTile(int id)
    {
        super(AssetLoader.wall, id);

    }

    public boolean isSolid()
    {
        return true;
    }

}

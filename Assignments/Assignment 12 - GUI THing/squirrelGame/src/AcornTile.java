public class AcornTile extends Tile
{


    public AcornTile(int id)
    {   
        super(AssetLoader.acorn, id);
        this.taken = false;
    }

    public boolean isAcorn()
    {
        return true;
    }

    public boolean isTaken()
    {
        return taken;
    }

}


/*
dont count acorns until one acorn is taken


*/
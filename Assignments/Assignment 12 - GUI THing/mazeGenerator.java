import java.util.*;

public class mazeGenerator
{   


    public mazeGenerator(int n)
    {

    }

    private void init()
    {

    }

    public void mazeify()
    {
    
    }
    
    public void rbtMaze()
    {
        
    }

    public String toString()
    {
        String s = "";


        return s;
    }
    public static void main(String[] args)
    {


    }
}

class Cell
{
    boolean status;
    int row;
    int col;

}

/*

Choose initial cell. [0,0] on grid.

can cell visit neighbor?
    yes: 
        pick random neighbor
        add neighbor to <stack> list
        dig path to neighbor
        mark visited
    no:
        mark current cell as visited
        pop cell off of stack

*/
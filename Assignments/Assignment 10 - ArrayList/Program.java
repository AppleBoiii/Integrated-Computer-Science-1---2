import java.util.Arrays;

public class Program
{

    public static void times(ArrayList nums)
    {
        int i=0;
        long start = System.nanoTime();
        while(true)
        {
            nums.add(Math.pow(2, i));
            i++;
        }
        long end = System.nanoTime();

        
    }
    public static void main(String[] args)
    {
        ArrayList mine = new ArrayList();
        
    }
}
public interface ICSList //the functions in this abstract class required to make a thing that implements it run
{
    public void add(int value); //these are called function signtaures 
    public void add(int value, int index);
    public int get(int index);
    public void clear();
    public boolean contains(int value);
    public int indexOf(int value);
    public boolean isEmpty(); 
    public int delete(int index);
    public boolean remove(int value);
    public int set(int index, int value);
    public int size();
    public ICSList subList(int fromIndex, int toIndex);
    public int[] toArray();
    
}
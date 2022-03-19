import java.util.Arrays;

public class ArrayList implements ICSList
{
    private int[] list;
    public int size;

    public ArrayList()
    {
        list = new int[100];
        size = 0;
    }

    /**
     * Adds a value to the array.
     * 
     * @param input: The int to add to the aray. 
     */
    public void add(int input)
    {
        if(size>=list.length) upsize();
        list[size] = input;
        size++;
    }

    private void upsize()
    {
        int[] newList = new int[list.length*2];
        for(int i=0;i<list.length;i++) newList[i] = list[i];
        list = newList;
    }

    /**
     * Get value from specified index.
     * 
     * @param index: The index of the value to get.
     * @return The value at the index
     * @throws IndexOutOfBoundsException if index < 0 or > size. 
     */
    public int get(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Either index is greater than array size or less than zero.");
        return list[index];
    }

    /**
     * Adds a value at specified index, and shifts any elements in the way to the right.
     * 
     * @param index: Index of value to get
     * @param value: Value to put there
     * @throws IndexOutOfBoundsException if index index < 0 or > size. 
     */
    public void add(int index, int value)
    {
        if(index < 0 || index > size) throw new IndexOutOfBoundsException("Either index is greater than array size or less than zero.");
        if(size>=list.length) upsize();

        for(int i=size;i>index;i--) list[i] = list[i-1];
        list[index] = value;
        size++;
        
        //figure out way to do this with single for-loop
        // int[] tempNums = new int[size];
        // for(int i=index;i<size;i++) tempNums[i] = list[i]; //create a copy of list
        // for(int i=index+1;i<size+1;i++) list[i] = tempNums[i-1]; //shift elems to right
        // list[index] = value;
        // size++;
        // throw new UnsupportedOperationException();
    }

    /**
     * Sets size variable to zero
     */
    public void clear()
    {
        size = 0;
    }
    
    /**
     * Checks to see if the ArrayList contains specified value.
     * 
     * @param value: The value to search for
     * @return True/False if value is in the array.
     */
    public boolean contains(int value)
    {
        /*
        converts list to an IntStream and returns whether or not it contains the value
        side note: streams are slightly slower than for-loops from what i tell but i just figured them out & think
        they are cool
        */
        return Arrays.stream(list).anyMatch(x -> x == value);
    }
    
    /**
     * Finds the first index of the specified value and returns it. Returns -1 if value not found.
     * 
     * @param value: The value to find the index of. 
     * @return The earliest occurence of the value, or -1 if it is not found. 
     * 
     */
    //Side Note: I tried experimenting with something that I thought would be faster with a for-loop but this was not the case, it was slower.
    public int indexOf(int value)
    {
        // /**
        //  * Splits the list into thirds, and creates 3 threads to comb through each section
        //  * The threads then return a value that is put in arrayToStoreIndexes, the values will either be
        //  * -1 if the value was not found, or the index where it was found. 
        //  * Then the minimum of those is found (-1 is ignored unless it is only -1s). 
        //  */
        // final int numOfThreads = 3;
        // // if(size > 10000) numOfThreads = 5000;
        // // else numOfThreads = size/2;

        // int rangeToSearch = size / numOfThreads;
        // int[] arrayToStoreIndexes = new int[numOfThreads];
        // int indexToReturn = -1;

        // for (int i = 0; i <= numOfThreads-1; i++)
        // {
        //     ThreadSearcher searcher;
        //     if(i < numOfThreads-1) searcher = new ThreadSearcher(value, i*rangeToSearch, i*rangeToSearch+(rangeToSearch-1), list);
        //     else searcher = new ThreadSearcher(value, i*rangeToSearch, size-1, list);

        //     searcher.start();
        //     try
        //     {
        //         searcher.join();
        //     }
        //     catch(InterruptedException e)
        //     {
        //         e.printStackTrace();
        //     }

        //     arrayToStoreIndexes[i] = searcher.index();
        // }

        // indexToReturn = getMin(arrayToStoreIndexes);

        // return indexToReturn;

        int index = -1;
        for(int i=0;i<size;i++) if(list[i] == value) return i;

        return index;

    }

    /**
     * gets the minimum of an array of nums. I used this for the thread-search I tried in indexOf but commented it out. 
     * @param nums
     * @return
     */
    private int getMin(int[] nums)
    {
        int min = nums[0];

        for(int obj : nums)
        {
            if(obj > min && min < 0) min = obj;
            else if(min > obj && obj >= 0) min = obj;
        }

        return min;
    }

    /**
     * Checks to see if the user has put any elements in the arry.
     * 
     * @return whether or not the size of the array is 0
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /** 
     * Deletes element at the specified index
     * Shifts all other elements over.
     * Returns deleted element
     * 
     * @param index: Index of element to delete. 
     * @return The value of the deleted index.
     * @throws IndexOutofBoundsException if index is >= size and < 0. 
    */
    public int delete(int index)
    {
        if(index >= size || index < 0) throw new IndexOutOfBoundsException("Either index is greater than array size or less than zero.");

        int deleted = list[index];
        for(int i=index;i<size-1;i++)
        {
            list[i] = list[i+1];
        }

        size--; 
        return deleted;
    }

    /**
     * Removes the first occurence of the input value.
     * 
     * @param value: the number to remove from the list. 
     * @return True if the number is in the array and first occurence removed, false if the value is not in the array.
     */
    public boolean remove(int value)
    {
        int index = indexOf(value);
        if(index >= 0)
        {
            delete(index);
            return true;
        }

        return false;
    }

    /**
     * Sets the specificed index to the specified value & returns what the original value was. 
     * 
     * @param index: The index to set to a new value.
     * @param value: The value to put at the specified index.
     * @return the int that was originally there. 
     * @throws IndexOutOfBoundsException when the input index is either greater than list.size or less than zero.
     */
    public int set(int index, int value)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Either index is greater than array size or less than zero.");
        int originalNum = list[index];
        list[index] = value;
        
        return originalNum;
    }

    /**
     * Returns the size of the list.
     * 
     * @return the size instance variable.
     */
    public int size()
    {
        return size;
    }

    /**
     * Creates and returns a new ICSList from another ArrayList. 
     * 
     * @param fromIndex: the (inclusive) index to start copying at.
     * @param toIndex: the (exclusive) index to stop copying at. 
     * @return the new ICS list withh values between the earlier specified indices. 
     * @throws IndexOutOfBoundsException when either index is greater than array size or less than zero.
     * @throws IllegalArgumentException when the from index is larger than to index.
     */
    public ICSList subList(int fromIndex, int toIndex)
    {   
        if(toIndex > size || fromIndex < 0) throw new IndexOutOfBoundsException("Either index is greater than array size or less than zero.");
        if(fromIndex > toIndex) throw new IllegalArgumentException("From index is larger than to index.");

        // ICSList newList = new ArrayList();
        // for(int i=fromIndex;i<toIndex;i++) newList.add(list[i]);
        // return newList;

        ArrayList output = new ArrayList();
        output.list = new int[toIndex-fromIndex];
        output.size = toIndex-fromIndex;
        System.arraycopy(list, fromIndex, output.list, 0, output.size);
        return output;
    }

    /**
     * Turns the ArrayList into a normal Array.
     * 
     * @return a copy of list[] instead of returning list[] so no one messes with the original list.
     */
    public int[] toArray()
    {
        return Arrays.copyOf(list, size);
    }

    /**
     * Returns the string version of the array.
     * 
     * @return a string version of a COPY of the array in ArrayList.
     */
    public String toString()
    {
        return Arrays.toString(Arrays.copyOf(list, size));
    }
}

class ThreadSearcher extends Thread
{
    private int valueToFind;
    private int start;
    private int end;
    private int[] listToLookIn;
    private int indexToReturn;

    public ThreadSearcher(int valueToFind, int start, int end, int[] listToLookIn)
    {
        this.valueToFind = valueToFind;
        this.start = start;
        this.end = end;
        this.listToLookIn = listToLookIn;
        indexToReturn = -1;
    }

    public void run()
    {   
        for(int i=start;i<=end;i++) 
        {
            if(listToLookIn[i] == valueToFind)
            {
                indexToReturn = i;
                // System.out.println("I was called.");
                // System.out.println(indexToReturn);
            }
        }    
    }

    public int index()
    {
        // System.out.println(indexToReturn);
        return indexToReturn;
    }


}
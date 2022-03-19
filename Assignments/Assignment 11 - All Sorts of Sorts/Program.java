import java.util.*;

//assignment for this:
        //implement 4 sorts. 3 will be Bubble, Selection, and Insertion.
        //4 needs to be asymptotically better than those (better than n^2)
        //heapsort, quicksort, mergesort, radixsort
        //Benchmark; time to sort, compares, swaps

public class Program
{
    public static int swapCount = 0;
    public static int compareCount = 0;

    public static boolean isSorted(int[] nums)
    {
        for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i] > nums[i+1]) return false;
        }

        return true;
    }
    public static void insertionSort(int[] nums)
    {   
        /**
         * to do an insertionSort:
         * iterate from nums[1] to nums[len]
         * compare current elem to prev
         * if current elem < prev, swap. compare to previous elem again. if <, swap. continue
         */
        for(int k=0;k<nums.length;k++)
        {
            for(int i=1;i<nums.length;i++)
            {
                while(nums[i-1] > nums[i])
                {
                    int j = i;
                    swap(nums, j, j-1);
                    j--;
                    compareCount++;
                }
            }
        }
    }
    /**
     * this deceivingly looks like o(n)
     * but minIndex also has o(n) 
     * o(n(o(n)) so it is REALLY
     *  o(n^2)
     */
    public static void selectionSort(int[] nums)
    {
        for(int i=0;i<nums.length;i++) swap(nums, i, minIndex(nums, i, nums.length));
    }
    public static void bubbleSort(int[] nums)
    {
        /**NOTE TO SELF FO LATER
         * okay essentially here's what happens:
         * each time you pass the inner for-loop, the first index gets pushed up to where it belongs.
         * e.g. the 9 would get pushed until it gets next to 10.
         * 
         * then this starts over and happens again with the next number that is before a number smaller than it.  
         */
        for(int j=0;j<nums.length-1;j++)
        {
            for(int i=0;i<nums.length-1-j;i++)
            {
                if(nums[i] > nums[i+1])
                {
                    swap(nums, i, i+1);
                    compareCount++;
                }
            }
            // System.out.println(Arrays.toString(nums));
        }
    }
    public static void radixSort(int[] nums)
    {

        ArrayList<Integer>[] buckets = new ArrayList[10]; //an array of ArrayLists
        for(int i=0;i<10;i++) buckets[i] = new ArrayList<Integer>();
        for(int num: nums) buckets[0].add(num);

        int f = 1;
        while(true)
        {
            ArrayList<Integer>[] newBuckets = new ArrayList[10];
            for(int i=0;i<10;i++) newBuckets[i] = new ArrayList<Integer>();
            for(int i=0;i<buckets.length;i++)
            {
                for(int j=0;j<buckets[i].size();j++)
                {
                    int num = buckets[i].get(j);
                    newBuckets[num/f%10].add(num);
                }
            }
            buckets = newBuckets;
            f *= 10;
            if(buckets[0].size() == nums.length) break;
        }

        for(int i=0;i<nums.length;i++) nums[i] = buckets[0].get(i);

    }
    public static void bogoSort(int[] nums) //O(n) = n*n or n!
    {
        while(!isSorted(nums))
        {
            // swap(nums, (int)(Math.random().nums.length), (int)(Math.random().nums.length));
        }
    }
    public static void heapSort(int[] nums)
    {
        for(int i=nums.length;i>0;i--)
        {
            heapify(nums, 0, i);
            // System.out.println("Heap{"+i+"}: "+Arrays.toString(nums));
            swap(nums, 0, i-1);
            // System.out.println("Heap{"+i+"}: "+Arrays.toString(nums));
        }
    }
    public static void heapify(int[] nums, int start, int stop)
    {
        //left index = 2*i+1
        //right index = 2*i+2
        int subArrayLength = stop-start;
        int middleOfArray = (int)(Math.floor((subArrayLength/2))-1);
        while(middleOfArray >= 0) //something is happening here
        {
            siftDown(nums, middleOfArray, stop);
            middleOfArray--;
        }
    }
    public static void siftDown(int nums[], int i, int stopIndex) //int stopIndex)
    {
        int leftIndex = 2*i+1;
        int rightIndex = 2*i+2;
        // int stopIndex = nums.length;
        int swapIndex = i;

        if(leftIndex < stopIndex && nums[leftIndex] > nums[swapIndex])
        {
            swapIndex = leftIndex;
            compareCount++;
        }
        if(rightIndex < stopIndex && nums[rightIndex] > nums[swapIndex])
        {
            swapIndex = rightIndex;
            compareCount++;
        } 

        if(swapIndex != i)
        {
            swap(nums, i, swapIndex);
            siftDown(nums, swapIndex, stopIndex);
        }
         
    }

    //utility funcs
    /**
     * Finds the index of the min value of a sublist
     * @param nums List to use.
     * @param start Place to start in the list. 
     * @param stop  Place to stop in the list. 
     * @return  The index of the minimum value.
     */
    public static int minIndex(int[] nums, int start, int stop)
    {
        int minIndex = start;
        for(int i=start;i<stop;i++)
        {
            if(nums[i] < nums[minIndex])
            {
				minIndex = i;
				compareCount++;
			}
        }

        return minIndex;
    }
    public static int min(int[] nums)
    {
        int min = nums[0];
        for(int num: nums)
        {
            if(num < min) min = num;
        }

        return min;
    }
    public static int max(int[] nums)
    {
        int max = nums[0];
        for(int num : nums)
        {
            if(num > max) max=num;
        }

        return max;
    }    
    public static void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        swapCount++;
    }
    public static int[] getList(int n)
    {
        int[] list = new int[n];
        for(int i=0;i<n;i++)list[i] = i;
        for(int i=0;i<n-1;i++)
        {
            int j = (int)(Math.random()*(n-i))+i;
            swap(list, i, j);  
        }

        return list;
    }
    public static void timeSort(int n)
    {
        int[] nums = getList(n);
        long start = System.nanoTime();
        bubbleSort(nums);
        long end = System.nanoTime();

        double dTimeInSeconds = (end-start)/1e9;
        System.out.println(n+","+dTimeInSeconds);
    }
    public static void main(String[] args)
    {
        // int[] nums = getList(10);
        //insertionSort(nums);
        //bubble
        //selection
        //heap

        int[] arr = {4, 10, 3, 5, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

        // int i = 1;
        // while(true)
        // {
        //     int n = (int)(Math.pow(2, i));
        //     int[] nums = getList(n);

        //     long start = System.nanoTime();

        //     if(args[0].equals("insertion")) insertionSort(nums);
        //     if(args[0].equals("bubble")) bubbleSort(nums);
        //     if(args[0].equals("selection")) selectionSort(nums);
        //     if(args[0].equals("heap")) heapSort(nums);

        //     long end = System.nanoTime();
        //     double timeInSeconds = (end-start)/1e9;
        //     System.out.println(compareCount+","+n);
        //     if(timeInSeconds > 60*5.0) break;
        //     i++;
        // }
        
        // System.exit(0);
    }
}

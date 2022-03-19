import java.util.*;

public class Program
{

    public static boolean isSorted(int[] nums)
    {
        for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i] > nums[i+1]) return false;
        }

        return true;
    }

    public static void selectionSort(int[] nums)
    {
        /**
         * [2, 1, 3, 7, 10, 6, 9, 8]
         * 
         * 
         */
    }
    public static void bubbleSort(int[] nums)
    {
        /**
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
                }
            }
            System.out.println(Arrays.toString(nums));
        }
    }

    public static void heapSort(int[] nums)
    {
        
    }
    public static void bogoSort(int[] nums) //O(n) = n*n or n!
    {
        while(!isSorted(nums))
        {
            // swap(nums, (int)(Math.random().nums.length), (int)(Math.random().nums.length));
        }
    }

    public static void heapify(int[] nums)
    {
        int middleOfArray = (int)(nums.length/2)+1;
        while(middleOfArray >= 0)
        {
            siftDown(nums, middleOfArray);
            middleOfArray--;
        }
    }

    public static void siftDown(int nums[], int i) //int stopIndex)
    {
        int leftIndex = 2*i+1;
        int rightIndex = 2*i+2;
        int stopIndex = nums.length;
        int swapIndex = i;

        if(leftIndex < stopIndex && nums[leftIndex] > nums[swapIndex]) swapIndex = leftIndex;
        if(rightIndex < stopIndex && nums[rightIndex] > nums[swapIndex]) swapIndex = rightIndex;

        if(swapIndex != i)
        {
            swap(nums, i, swapIndex);
            siftDown(nums, i);
        }
         
    }

    public static void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String[] args)
    {
        //assignment for this:
        //implement 4 sorts. 3 will be Bubble, Selection, and Insertion.
        //4 needs to be asymptotically better than those (better than n^2)
        //heapsort, quicksort, mergesort, radixsort
        //Benchmark; time to sort, compares, swaps

        int[] nums = {3, 5, 7, 6, 1, 2, 4, 0};
        heapify(nums);
        System.out.println(Arrays.toString(nums));
        // System.out.println(isSorted(nums));
        // System.out.println(Arrays.toString(nums));

        // System.out.println(Arrays.toString(nums));

    }
}
/**
 * do n-1 passes
 * first pass do n-1 things, n-2, n-3....until 1.
 * 
 * add the ends...so you get n*(n-1)/2 = n^2/2-n/2
 *                         = n*(n-1)/2 ~ n^2/2 as n -> infinity
 *                         = n*(n-1)/2 ~ n^2 = O(n**2) big O notation
 *                         f(n) = n*(n-1)/2
 *                         g(n) = ?
 *                         looking for g(n) where c*g(n) >f(n) for all n greater than n_0
 *                         g(n) = n*n
 */
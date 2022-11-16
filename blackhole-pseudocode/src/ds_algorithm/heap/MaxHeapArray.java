/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.heap;

import com.google.gson.Gson;

/**
 *
 * @author venkateshwarans
 */

/*
0,1,2,3,4,5,6,7,8
1,2,3,4,5,6,7,8,9
         1
      2     3
    4   5  6  7
   8 9

         9
      8     7
    6   5  4  3
   2 1
*/

public class MaxHeapArray {
  public static void maxHeapify (int arr[ ], int i, int n)
  {
    int left = 2*i + 1;
    int right = 2*i + 2;
    int largest = Integer.MIN_VALUE;
    if(left< n && arr[left] > arr[i])
          largest = left;
    else
         largest = i;
    if(right < n && arr[right] > arr[largest])
        largest = right;
    if(largest != i)
    {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;
        maxHeapify (arr, largest, n);
    } 
  }
  
  
  // Time: O(nlog(n)) space: O(n)
  // NOTE: root starts from left
  // calling maxheapify n/2 times to build the heap
  // we could also call it n times from the leaf to root(which is why revese to 0),
  // but anyway leafs dont have children to compare to,
  // so we have to start from the level just above the leaf so n/2 
  // because mre than half nodes lie as the leaf in trees.
  // we heapify bottum up and as it reaches above, the comparison recusrion 
  // will happen upto leaf if a data is swapped to make sure whole tree is follwing heap property
  
  public static int[] buildMaxheap (int arr[ ])
  {
      for(int i = arr.length/2-1;i >= 0;i--)
      {
          maxHeapify(arr, i, arr.length);
      }
      return arr;
  }
  
  public static int[] heapSort(int arr[])
  {
    int heap_size = arr.length;
    buildMaxheap(arr);
    for(int i = arr.length-1;i>=1;i--)
    {
        int temp = arr[i];
        arr[i] = arr[0];
        arr[0] = temp;
        heap_size = heap_size-1;
        maxHeapify(arr, 0, heap_size);
    }
    return arr;
  }
  
  public static void main(String args[]){
    test(heapSort(new int[]{3,2,1,6,5,4,9,8,7,3}), new int[]{1,2,3,3,4,5,6,7,8,9});
    test(heapSort(new int[]{1,2,3,4,5,6,7,8,9}), new int[]{1,2,3,4,5,6,7,8,9});
    test(heapSort(new int[]{1,4,2,5,3,6,8,7,9,1}), new int[]{1,1,2,3,4,5,6,7,8,9});
    
  }
  
  public static void test(int got[], int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
}

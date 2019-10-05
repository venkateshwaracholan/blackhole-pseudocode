/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.heap;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author venkateshwarans
 */
// 1,2,3,4,5,6,7,8,9
// 0,1,2,3,4,5,6,7,8

/*
         1
      2     3
    4   5  6  7
   8 9

         9
      8     7
    6   5  4  3
   2 1
*/

public class MinHeapArray {
  
  public static void minHeapify (int arr[ ], int i, int n)
  {
    int left = 2*i + 1;
    int right = 2*i + 2;
    int min = Integer.MIN_VALUE;
    if(left< n && arr[left] < arr[i])
          min = left;
    else
         min = i;
    if(right < n && arr[right] < arr[min])
        min = right;
    if(min != i)
    {
        int temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
        minHeapify(arr, min, n);
    } 
  }
  
  public static int[] buildMinheap (int arr[])
  {
      for(int i = arr.length/2-1;i >= 0;i--)
      {
          minHeapify(arr, i, arr.length);
      }
      return arr;
  }
  
  public static int[] heapSort(int arr[])
  {
    int heap_size = arr.length;
    buildMinheap(arr);
    for(int i = arr.length-1;i>=1;i--)
    {
        int temp = arr[i];
        arr[i] = arr[0];
        arr[0] = temp;
        heap_size = heap_size-1;
        minHeapify(arr, 0, heap_size);
    }
    return arr;
  }
  
  public static void main(String args[]){
    test(heapSort(new int[]{9,8,7,6,5,4,3,2,1}), new int[]{9,8,7,6,5,4,3,2,1});
    test(heapSort(new int[]{1,4,2,5,3,6,8,7,9,1}), new int[]{9,8,7,6,5,4,3,2,1,1});
    test(heapSort(new int[]{3,2,1,6,5,4,9,8,7,3}), new int[]{9,8,7,6,5,4,3,3,2,1});
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.sort;

import com.google.gson.Gson;
import java.util.Arrays;

/**
 *
 * @author venkateshwarans
 */

// 1,6,9,8,3,5,4,2
public class QuickSort {
  public static int[] quickSort(int[] arr) {
    return sortRec(arr, 0, arr.length-1);
  }
  
  public static int[] sortRec(int[] arr, int l, int r) {
    if(l<r){
      int p = partition(arr, l ,r);
      sortRec(arr, l, p-1); 
			sortRec(arr, p+1, r); 
    }
    return arr;
  }
  
  public static int partition(int[] arr, int l, int r){
    int pivot = arr[r];
    int i = l;
    for(int j=l;j<r;j++){
      if(arr[j]<=pivot){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
        i++;
      }
    }
    int temp = arr[r];
    arr[r] = arr[i];
    arr[i] = temp;
    return i;
  }

  public static void main(String[] args){
    int arr[] = new int[]{12,7,2,4,6,2,4,1,6,3,4,7,8,3,1,15};
    test(quickSort(arr.clone()), arr);
  }

  public static void test(int got[], int exp[]){
    Gson gson = new Gson();
    Arrays.sort(exp);
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
}

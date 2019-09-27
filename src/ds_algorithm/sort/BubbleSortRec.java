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

// 4,5,3,2,1
// 1,2,3,4,5

public class BubbleSortRec {
  public static int[] bubbleSortRec(int[] arr, int n) {
    if(n==0){
      return arr;
    }
    for(int j=0;j<arr.length-1;j++){
      if(arr[j]>arr[j+1]){
        int temp = arr[j];
        arr[j] = arr[j+1];
        arr[j+1] = temp;
      }
    }
    return bubbleSortRec(arr, n-1);
  }
  
  public static int[] bubble(int[] arr){
    return bubbleSortRec(arr, arr.length);
  }

  public static void main(String[] args){
    int arr[] = new int[]{12,7,2,4,6,2,4,1,6,3,4,7,8,3,1,15};
    test(bubble(arr.clone()), arr);
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

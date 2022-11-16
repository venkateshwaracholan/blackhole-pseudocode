/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.sort;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author venkateshwarans
 */
public class SelectionSort {
  public static int[] selectionSort(int[] arr) {
    for(int i=0;i<arr.length;i++){
      int min = i;
      for(int j=i+1;j<arr.length;j++){
        if(arr[j]<arr[min]){
          min = j;
        }
      }
      int temp = arr[i];
      arr[i] = arr[min];
      arr[min] = temp;
    }
    return arr;
  }

  public static void main(String[] args){
    int arr[] = new int[]{12,7,1,15};
    test(selectionSort(arr.clone()), arr);
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

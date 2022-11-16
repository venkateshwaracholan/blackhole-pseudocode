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
// 5,4,3,2,1
// 1,2,3,4,5

/*
Insertion sorts upto i by inserting i th element in the correct position beofre that
insertion is through swapping in reverse order.
*/
public class InsertionSort {
  public static int[] insertionSort(int[] arr) {
    for(int i=0;i<arr.length;i++){
      int j = i;
      for(;j>0 && arr[j-1]>arr[j];j--){
        int temp = arr[j];
        arr[j] = arr[j-1];
        arr[j-1] = temp;
      }
    }
    return arr;
  }

  public static void main(String[] args){
    int arr[] = new int[]{12,7,2,4,6,2,4,1,6,3,4,7,8,3,1,15};
    test(insertionSort(arr.clone()), arr);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.utils;

import com.google.gson.Gson;

/**
 *
 * @author venkateshwarans
 */

// 1 2
public class ArrayUtils {
  public static boolean show = true;
  public static int[] reverse(int arr[]){
    for(int i=0;i<arr.length/2;i++){
      int temp = arr[i];
      arr[i] = arr[arr.length -1 -i];
      arr[arr.length -1 -i] = temp;
    }
    return arr;
  }
  
  public static void main(String[] args){
    test(reverse(new int[]{1,2,3,4,5}), new int[]{5,4,3,2,1});  
    
  }
  
  public static void test(int[] got, int[] exp){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    if(show || !gotStr.equals(expStr)){
      System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
    }
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

import com.google.gson.Gson;
import java.util.Arrays;

/**
 *
 * @author venkateshwarans
 */

// https://leetcode.com/problems/rotate-array/

//0 1 2 3 4 5 6 7
public class RotateArray {
  public static boolean show= false;
  
  //reverse utility mathematical
  public static int[] reverse(int[] arr, int s, int e){
    if(s<0) s = 0;
    if(e<0) return arr;
    if(s>=arr.length) return arr;
    if(e>=arr.length) e = arr.length-1;
    for(int i=s,j=e;i <= s + (e-s)/2 ;i++,j--){
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
    return arr;
  }
  
  //reverse utility more readable
  public static int[] reverseEasy(int[] arr, int s, int e){
    if(s<0) s = 0;
    if(e<0) return arr;
    if(s>=arr.length) return arr;
    if(e>=arr.length) e = arr.length-1;
    while(s<e){
      int temp = arr[s];
      arr[s] = arr[e];
      arr[e] = temp;
      s++;e--;
    }
    return arr;
  }
  
  // Time: O(n) space: O(1)
  public static int[] rotateArrayright(int[] arr, int k){
    if(arr.length == 0) return arr;
    k %= arr.length;
    reverse(arr, 0, arr.length - 1);
    reverse(arr, 0, k - 1);
    reverse(arr, k, arr.length - 1);
    return arr;
  }
  
  // Time: O(n) space: O(1)
  public static int[] rotateArrayleft(int[] arr, int k){
    if(arr.length == 0) return arr;
    k %= arr.length;
    reverseEasy(arr,0,k-1);
    reverseEasy(arr,k,arr.length-1);
    reverseEasy(arr,0,arr.length-1);
    return arr;
  }
  
  // Time: O(n**2) space: O(1)
  public static int[] bubbleRotateright(int arr[],int k){
    for(int i=0;i<k;i++){
      for(int j = arr.length-1;j>0;j--){
        int temp = arr[j];
        arr[j] = arr[j-1];
        arr[j-1] = temp;
      }
    }
    return arr; 
  }
  
  // Time: O(n**2) space: O(1)
  public static int[] bubbleRotateLeft(int arr[],int k){
    for(int i=0;i<k;i++){
      for(int j = 0;j<arr.length-1;j++){
        int temp = arr[j];
        arr[j] = arr[j+1];
        arr[j+1] = temp;
      }
    }
    return arr; 
  }
  
  // Time: O(n) space: O(n)
  public static int[] lameRotateLeft(int arr[], int k){
    if(arr.length==0) return arr;
    if(k > arr.length)
      k=k%arr.length;
    int i=0;
    int j = k;
    int res[] = new int[arr.length];
    while(j<arr.length){
      res[i] = arr[j];
      j++;i++;
    }
    j = 0;
    while(j<k){
      res[i] = arr[j];
      j++;i++;
    }
    return res;
  }
  
  // Time: O(n) space: O(n)
  public static int[] lameRotateRight(int arr[], int k){
    if(arr.length==0) return arr;
    if(k > arr.length)
      k=k%arr.length;
    int i=0;
    int res[] = new int[arr.length];
    while(i<k){
      res[i] = arr[arr.length-k+i];
      i++;
    }
    int j=0;
    i=k;
    while(i<arr.length){
      res[i] = arr[j];
      i++;j++;
    }
    return res;
  }
  
  public static void main(String[] args){
    
//    test(reverse(new int[]{1,2,3,4,5,6,7},0,6), new int[]{7,6,5,4,3,2,1});
//    test(reverse(new int[]{1,2,3,4,5,6,7},3,6), new int[]{1,2,3,7,6,5,4});
//    test(reverse(new int[]{1,2,3,4,5,6,7},0,3), new int[]{4,3,2,1,5,6,7});
//    test(reverse(new int[]{1,2,3,4,5,6,7},1,5), new int[]{1,6,5,4,3,2,7});
//    test(reverse(new int[]{1},1,5), new int[]{1});
//    test(reverse(new int[]{},1,5), new int[]{});
//    test(reverse(new int[]{1,2,3,4,5,6,7},-1,2), new int[]{3,2,1,4,5,6,7});
//    test(reverse(new int[]{1,2,3,4,5,6,7},1,8), new int[]{1,7,6,5,4,3,2});
//    test(reverse(new int[]{1,2,3,4,5,6,7},1,-1), new int[]{1,2,3,4,5,6,7});
//    test(reverse(new int[]{1,2,3,4,5,6,7},8,3), new int[]{1,2,3,4,5,6,7});
//    
//    test(reverseEasy(new int[]{1,2,3,4,5,6,7},0,6), new int[]{7,6,5,4,3,2,1});
//    test(reverseEasy(new int[]{1,2,3,4,5,6,7},3,6), new int[]{1,2,3,7,6,5,4});
//    test(reverseEasy(new int[]{1,2,3,4,5,6,7},0,3), new int[]{4,3,2,1,5,6,7});
//    test(reverseEasy(new int[]{1,2,3,4,5,6,7},1,5), new int[]{1,6,5,4,3,2,7});
//    test(reverseEasy(new int[]{1},1,5), new int[]{1});
//    test(reverseEasy(new int[]{},1,5), new int[]{});
//    test(reverseEasy(new int[]{1,2,3,4,5,6,7},-1,2), new int[]{3,2,1,4,5,6,7});
//    test(reverseEasy(new int[]{1,2,3,4,5,6,7},1,8), new int[]{1,7,6,5,4,3,2});
//    test(reverseEasy(new int[]{1,2,3,4,5,6,7},1,-1), new int[]{1,2,3,4,5,6,7});
//    test(reverseEasy(new int[]{1,2,3,4,5,6,7},8,3), new int[]{1,2,3,4,5,6,7});
    
    test(lameRotateLeft(new int[]{1,2,3,4,5,6,7},3), new int[]{4,5,6,7,1,2,3});
    test(lameRotateLeft(new int[]{},3), new int[]{});
    test(lameRotateLeft(new int[]{1},3), new int[]{1});
    test(lameRotateLeft(new int[]{1,2},3), new int[]{2,1});
    
    test(lameRotateRight(new int[]{1,2,3,4,5,6,7},3), new int[]{5,6,7,1,2,3,4});
    test(lameRotateRight(new int[]{},3), new int[]{});
    test(lameRotateRight(new int[]{1},3), new int[]{1});
    test(lameRotateRight(new int[]{1,2},3), new int[]{2,1});
    
    test(rotateArrayleft(new int[]{1,2,3,4,5,6,7},3), new int[]{4,5,6,7,1,2,3});
    test(rotateArrayleft(new int[]{},3), new int[]{});
    test(rotateArrayleft(new int[]{1},3), new int[]{1});
    test(rotateArrayleft(new int[]{1,2},3), new int[]{2,1});
    
    test(rotateArrayright(new int[]{1,2,3,4,5,6,7},3), new int[]{5,6,7,1,2,3,4});
    test(rotateArrayright(new int[]{},3), new int[]{});
    test(rotateArrayright(new int[]{1},3), new int[]{1});
    test(rotateArrayright(new int[]{1,2},3), new int[]{2,1});
    
    test(bubbleRotateLeft(new int[]{1,2,3,4,5,6,7},3), new int[]{4,5,6,7,1,2,3});
    test(bubbleRotateLeft(new int[]{},3), new int[]{});
    test(bubbleRotateLeft(new int[]{1},3), new int[]{1});
    test(bubbleRotateLeft(new int[]{1,2},3), new int[]{2,1});
    
    test(bubbleRotateright(new int[]{1,2,3,4,5,6,7},3), new int[]{5,6,7,1,2,3,4});
    test(bubbleRotateright(new int[]{},3), new int[]{});
    test(bubbleRotateright(new int[]{1},3), new int[]{1});
    test(bubbleRotateright(new int[]{1,2},3), new int[]{2,1});
  
  }
  public static void test(int[] got, int exp[]){
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

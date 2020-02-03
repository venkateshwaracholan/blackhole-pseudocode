/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.divide_and_conquer;
import java.util.*;
/**
 *
 * @author venkateshwarans
 */
// 0 1 2 3 4 5 6 7 8
public class BinarySearchRec {
  
  // Time: O(log(n)) space: O(1)
  public static int binarySearchRec(int[] arr, int k){
    return  binarySearch(arr,0,arr.length-1,k);
  }
  
  //this bound is better than the alternate impl
  // Time: O(log(n)) space: O(1)
  public static int binarySearch(int[] arr,int l, int r, int k){
    //System.out.println(l+" : "+r);
    int mid = (l+r)/2;
    if(arr[mid]==k){
      return mid;
    }
    if(l<r){
      if(k<arr[mid]){
        binarySearch(arr, mid+1, r, k);
        return binarySearch(arr, l, mid-1, k);
      }else{
        return binarySearch(arr, mid+1, r, k);
      }
    }
    return -1;
  }
  
  //alternative implementation
  // Time: O(log(n)) space: O(1)
  public static int binarySearchAlt(int[] arr,int l, int r, int k){
    int mid = (l+r)/2;
    System.out.println(l+" : "+mid+" : "+r);
    if(arr[mid]==k){
      return mid;
    }
    if(l==r){
      return -1;
    }
    if(k<arr[mid]){
      return binarySearch(arr, l, mid-1, k);
    }else{
      return binarySearch(arr, mid+1, r, k);
    }
  }
  
  public static void main(String[] args){
    
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15}, 9), 4);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15}, 7), 2);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15}, 1), 0);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15}, 15), 6);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15}, 90), -1);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15}, -9), -1);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15}, 10), -1);
      
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15,25}, 9), 4);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15,25}, 7), 2);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15,25}, 1), 0);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15,25}, 15), 6);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15,25}, 90), -1);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15,25}, -9), -1);
      test(binarySearchRec(new int[]{1,2,7,8,9,11,15,25}, 10), -1);
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}

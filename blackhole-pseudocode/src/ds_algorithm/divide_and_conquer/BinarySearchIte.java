/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.divide_and_conquer;

/**
 *
 * @author venkateshwarans
 */
public class BinarySearchIte {
  
  // Time: O(log(n)) space: O(1)
  public static int binarySearch(int[] arr, int k){
    //System.out.println(l+" : "+r);
    int l = 0,r = arr.length-1;
    
    
    while(l<=r){
        int mid = (l+r)/2;
        if(arr[mid]==k){
          return mid;
        }
        if(k<arr[mid]){
          r = mid-1;
        }else{
          l = mid+1;
        }
    }
    return -1;
  }
  
  public static void main(String[] args){
      test(binarySearch(new int[]{}, 9), -1);
    
      test(binarySearch(new int[]{1,2,7,8,9,11,15}, 9), 4);
      test(binarySearch(new int[]{1,2,7,8,9,11,15}, 7), 2);
      test(binarySearch(new int[]{1,2,7,8,9,11,15}, 1), 0);
      test(binarySearch(new int[]{1,2,7,8,9,11,15}, 15), 6);
      test(binarySearch(new int[]{1,2,7,8,9,11,15}, 90), -1);
      test(binarySearch(new int[]{1,2,7,8,9,11,15}, -9), -1);
      test(binarySearch(new int[]{1,2,7,8,9,11,15}, 10), -1);
      
      test(binarySearch(new int[]{1,2,7,8,9,11,15,25}, 9), 4);
      test(binarySearch(new int[]{1,2,7,8,9,11,15,25}, 7), 2);
      test(binarySearch(new int[]{1,2,7,8,9,11,15,25}, 1), 0);
      test(binarySearch(new int[]{1,2,7,8,9,11,15,25}, 15), 6);
      test(binarySearch(new int[]{1,2,7,8,9,11,15,25}, 90), -1);
      test(binarySearch(new int[]{1,2,7,8,9,11,15,25}, -9), -1);
      test(binarySearch(new int[]{1,2,7,8,9,11,15,25}, 10), -1);
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}

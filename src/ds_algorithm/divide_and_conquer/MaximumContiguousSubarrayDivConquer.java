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

/*
https://leetcode.com/problems/maximum-subarray/

This is an alternate solution to a problem
question and Acual O(n) solution available at ds_algorithm/arrays/MaximumContiguousSubarray.java
*/
public class MaximumContiguousSubarrayDivConquer {
  
  public static boolean show = true;
  
  
  // Time: O(nlog(n)) space: O(1)
  public static int maximumContiguousSubarray(int arr[]){
    
    return maximumContiguousSubarrayRec(arr, 0, arr.length-1);
  }
  
  public static int maximumContiguousSubarrayRec(int arr[], int l, int r){
    int m = (l+r)/2;
    
    if(l==r){
      return arr[l];
    }
    return maxOfThree(maximumCrossingSum(arr,l,m,r), maximumContiguousSubarrayRec(arr,l,m), maximumContiguousSubarrayRec(arr,m+1,r));
  }
  
  public static int maxOfThree(int a,int b,int c){
    return Math.max(Math.max(a, b),c);
  }
  
  public static int maximumCrossingSum(int arr[], int l, int m, int r){
    int left_sum = Integer.MIN_VALUE;
    int right_sum = Integer.MIN_VALUE; 
    for(int i=m,sum=0;i>=l;i--){
      sum+=arr[i];
      if(sum>left_sum){
        left_sum=sum;
      }
    }
    for(int i=m+1,sum=0;i<=r;i++){
      sum+=arr[i];
      if(sum>right_sum){
        right_sum=sum;
      }
    }
    //System.out.println(l+" : "+m+" : "+r);
    //System.out.println(left_sum+right_sum);
    return left_sum+right_sum;
    
  }
  
  public static void main(String[] args){
    test(maximumContiguousSubarray(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
    test(maximumContiguousSubarray(new int[]{-1,-1,-1}), -1);
    test(maximumContiguousSubarray(new int[]{-2,-1}), -1);
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}

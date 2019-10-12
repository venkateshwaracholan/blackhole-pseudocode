/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

/**
 *
 * @author venkateshwarans
 */

/*
https://leetcode.com/problems/maximum-subarray/submissions/

53. Maximum Subarray - Kadane's algorithm

  Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

  Example:

  Input: [-2,1,-3,4,-1,2,1,-5,4],
  Output: 6
  Explanation: [4,-1,2,1] has the largest sum = 6.
  Follow up:

  If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.


  Alternate nlog(n) solution available at ds_algorithm/divide_and_conquer/MaximumContiguousSubarrayDivConquer.java
*/

//-2,1,-3,4,-1,2,1,-5,4
public class MaximumContiguousSubarray {
  public static boolean show = true;
  
  public static int maximumContiguousSubarray(int arr[]){
    int cum_max = 0;
    int max = Integer.MIN_VALUE;
    int temp = 0;
    int start =0;int end = 0;
    if(arr.length==0){
      return 0;
    }
    for(int i=0;i<arr.length;i++){
      cum_max+=arr[i];
      if(arr[i]>cum_max){
        cum_max=arr[i];
        temp = i;
      }
      if(cum_max>max){
        max = cum_max;
        start = temp;
        end = i;
      }
    }
    System.out.println(start+" : "+end);
    return max;
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
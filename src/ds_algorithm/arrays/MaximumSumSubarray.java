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
https://leetcode.com/problems/maximum-subarray/

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
public class MaximumSumSubarray {
  public static boolean show = true;
  
  // Time: O(n) space: O(1)
  // kadane - max andn sofar
  // code idea, compare sofar with num and assign if num is greater
  public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;;
        int sofar = 0;
        for(int i=0;i<nums.length;i++){
            sofar = sofar+ nums[i];
            if(sofar<nums[i])
                sofar = nums[i];
            if(sofar>max)
                max = sofar;
        }
        return max;
    }
  
  // Time: O(n) space: O(1)
  public static int maximumContiguousSubarray(int arr[]){
    int sofar = 0;
    int max = Integer.MIN_VALUE;
    int temp = 0;
    int start =0;int end = 0;
    if(arr.length==0){
      return 0;
    }
    for(int i=0;i<arr.length;i++){
      sofar+=arr[i];
      if(arr[i]>sofar){
        sofar=arr[i];
        temp = i;
      }
      if(sofar>max){
        max = sofar;
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

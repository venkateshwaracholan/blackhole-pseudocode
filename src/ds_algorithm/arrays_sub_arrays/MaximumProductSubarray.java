/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays_sub_arrays;

/**
 *
 * @author vshanmugham
 */

/*

https://leetcode.com/problems/maximum-product-subarray/
*/

public class MaximumProductSubarray {

// Time: O(n) space: O(1) 
// Kadane
// Core Idea: have a min and max
// multiply that with number to get number, min and max
// min = min(number, min , max)
// max = max(number, min , max)
// assign max in maximum if possible.
// array with 1 value is an exception
  
  // -2
  // -2,-3
  //  2,3,-2,4
  //  4,2,-2,2,3,-100
  
//  4,2,-2,2,3,-1
//  0 0
//  0 4
//  0 8
//  -16 0
//  -32 2
//  -96 6
//  -6 96
  
  public static int maxProductKadane(int[] nums) {
    if(nums.length==1) return nums[0];
    int maximum = 0, max = 0, min = 0;
    for(int i=0;i<nums.length;i++){
        min = Math.min(nums[i], Math.min(min*nums[i], max*nums[i])); 
        max = Math.max(nums[i], Math.max(min*nums[i], max*nums[i])); 
        maximum = Math.max(max, maximum);
    }
    return maximum;
  }
  
  
  public static void main(String args[]){
    System.out.println(maxProductKadane(new int[]{2,3,-2,4}));
    System.out.println(maxProductKadane(new int[]{4,-2,-2,2,3,-10}));
  }
  
}

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

// https://leetcode.com/problems/subarray-product-less-than-k/

public class SubarrayProductLessThanK {
//  Time: O(n) sapce O(1)
//  this solution is accumulation of combinations
//  2 pointers left and right
//  right moves forward and if product until right get beyond
//  left is moved in a while until prod is less again by diividing and taking off the left component
//  r-l+1 adds all sub arrays between the indices like 10, 10,5 and 10,5,2 are teh sub arrays
  
//  [10, 5, 2, 6]
  public int numSubarrayProductLessThanK(int[] nums, int k) {
    if(k<=1) return 0;
    int l=0,r=0,c=0, p =1;
    for(;r<nums.length;r++){
        p*= nums[r];
        while(p>=k) p/=nums[l++];
        c += r-l+1;
    }
    return c;
  }
}

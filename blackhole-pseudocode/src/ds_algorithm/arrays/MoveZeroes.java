/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

import ds_algorithm.Test;

/** 
*
 * @author vshanmugham
 */

// https://leetcode.com/problems/move-zeroes/

public class MoveZeroes {
  public static void main(String args[]){
    int nums[] = new int[]{0,1,0,3,12};
    moveZeroesSwap(nums);
    Test.test(nums, new int[]{1,3,12,0,0});
    nums = new int[]{0,1,0,3,12};
    moveZeroesAssign(nums);
    Test.test(nums, new int[]{1,3,12,0,0});
  }
    
  // Time: O(n) space: O(1)
  // core idea, swap non zero to left instead of moving zero to right
  public static void moveZeroesSwap(int[] nums) {
    int k = 0;
    for(int i=0;i<nums.length;i++){
        if(nums[i]!=0){
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k++] = temp;
        }
    }
  }
  
  // Time: O(n) space: O(1)
  // core idea, assign  non zero to left instead of moving zero to right
  // later we can assign 0 in the ends
  public static void moveZeroesAssign(int nums[]){
    int k = 0;
    for(int i=0;i<nums.length;i++){
      if(nums[i]!=0)
        nums[k++] = nums[i];
    }
    for(;k<nums.length;k++)
      nums[k] = 0;
  }
  
}

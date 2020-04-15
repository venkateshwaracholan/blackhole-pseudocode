/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/house-robber-ii/

// Time: O(2n) space: O(1) 
// the core idea still stays same as house robber 1
// but if we ro house 1 we cant rob house n.
// we need to run this twice without 1st house and 2nd run without house n.
public class HouseRobber2 {
  public int rob(int[] nums) {
    if(nums.length==0)
      return 0;
    if(nums.length==1)
      return nums[0];
    int tmp = nums[0];
    nums[0] = 0;
    int n1 = robHelper(nums);
    nums[0] = tmp;
    nums[nums.length-1] = 0;
    int n2 = robHelper(nums);
    return Math.max(n1,n2);
  }

  public int robHelper(int nums[]){
    int a = 0, b = 0;
    for(int i=0;i<nums.length;i++){
      int x = Math.max(a+nums[i], b);
      a = b;
      b = x;
    }
    return b;
  }
}

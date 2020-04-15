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

// https://leetcode.com/problems/house-robber

public class HouseRobber {
  
  
  // Time: O(n) space: O(n)
  // Core idea: we are scaling problem for n using dp array
  // for i<2 we cant find prev and prevprev so check help us to make it 0;
  // to find current max, find max of prevprev + cur or prev, which will result in current max
  // assign that to max that can be robbed so far
  // return the last value in the dp array;
  // 1 3 1  =>  3>1+1
  public int robDp1(int[] nums) {
    if(nums.length==0){
      return 0;
    }
    int dp[] = new int[nums.length];
    for(int i=0;i<nums.length;i++){
      int prev = i>0 ? dp[i-1]: 0;
      int prevprev = i>1 ? dp[i-2] : 0;
      dp[i] = Math.max(prevprev+nums[i], prev);
    }
   return dp[nums.length-1]; 
  }
  
  // Time: O(n) space: O(n)
  // same core idea as above with different coding style
  // interpolating dp array to hold 2 more values
  public int robDp2(int[] nums) {
    int dp[] = new int[nums.length+2];
    for(int i=0;i<nums.length;i++){
      dp[i+2] = Math.max(dp[i]+nums[i], dp[i+1]);
    }
   return dp[dp.length-1]; 
  }
  
  
  // Time: O(n) space: O(1)
  // the core idea still stays same
  // but we only worry about 2 states, the prev and prevprev, which can be stored in constant variables.
  // a = prevprev, b = prev
  // b goes to a, x goes to b.
  public int rob(int[] nums) {
    int a = 0, b = 0;
    for(int i=0;i<nums.length;i++){
      int x = Math.max(a+nums[i], b);
      a = b;
      b = x;
    }
   return b; 
  }
  
  
}
//    1 2 3 
//1 2
//a b
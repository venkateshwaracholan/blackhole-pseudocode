/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.distinct_ways;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/target-sum/

public class TargetSum {
  public static int findTargetSumWays(int[] nums, int S) {
    return 0;
  }
  
  
  public static int findTargetSumWays(int[] nums, int S, int count[], int i, int sum) {
    
    findTargetSumWays(nums, S, count, i+1, sum+(-1*nums[i]));
    findTargetSumWays(nums, S, count, i+1, sum+(1*nums[i]));
    return 0;
  }
}


/*
        +1           -1
     +2    -2      +2    -2
   +3 -3  +3 -3  +3 -3  +3 -3


3      -1     -3


*/

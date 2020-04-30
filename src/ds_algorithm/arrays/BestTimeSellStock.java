/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

public class BestTimeSellStock {
//  approach: iteration, min accumulation and subtraction
//  Time: O(n) space: O(1)
//  update min peice as loop goes and update max by subtracting current price with min, simple
  public static int maxProfit(int[] nums) {
    int low = Integer.MAX_VALUE;
    int max = 0;
    for(int i=0;i<nums.length;i++){
        if(nums[i]<low){
            low = nums[i];
        }
        else if(nums[i]-low>max){
            max = nums[i]-low;
        }
    }
    return max;
  }
    
//  Time: O(n) space: O(1)
//  same as above, different coding style
//  looks like kadane in reverse with negatives
  public static int maxProfitKadane(int[] prices) {
    int min=Integer.MAX_VALUE, result =0;
    for(int i=0;i<prices.length;i++){
        min= Math.min(min,prices[i]);
        result=Math.max(result,prices[i]-min);
    }
    return result;
  }
}

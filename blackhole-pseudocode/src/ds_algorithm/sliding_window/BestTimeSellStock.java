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
    
    //APPROACH 1 brute => 2 loops, j=i+1, get area
    
    //  Time: O(n^2) space: O(1)
    //  brute force
    public int maxProfit(int[] prices) {
        int max = 0;
        for(int i=0;i<prices.length;i++)
            for(int j=i+1;j<prices.length;j++)
                max = Math.max(max,prices[j]-prices[i]);
        return max;
    }
    
    
    //APPROACH 2 => 1 loops, accumulate min and sub with cur
    
    //  approach: iteration, min accumulation and subtraction
    //  Time: O(n) space: O(1)
    //  update min price as loop goes and update max by subtracting current price with min, simple
    public static int maxProfit2(int[] nums) {
      int low = Integer.MAX_VALUE,max = 0;
      for(int i=0;i<nums.length;i++){
          if(nums[i]<low) low = nums[i];
          else if(nums[i]-low>max) max = nums[i]-low;
      }
      return max;
    }
    //  Time: O(n) space: O(1)
    //  same as above, different coding style
    public int maxProfitAlt(int[] prices) {
        int max = 0, min = prices[0];
        for(int i=1;i<prices.length;i++){
            max = Math.max(max,prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
    
    
    // Approach 3, i cam up with this on a blind 75 run
    // use two pointers, instead of low variable, we use i to track index of min;
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        int max = 0;
        for(int i=0,j=i+1;j<len;j++){
            max = Math.max(max, prices[j]-prices[i]);
            if(prices[j]<prices[i]){
                i = j;
            }
        }
        return max;
    }
}

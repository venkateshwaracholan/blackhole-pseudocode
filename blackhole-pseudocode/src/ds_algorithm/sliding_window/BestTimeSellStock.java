/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.sliding_window;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

public class BestTimeSellStock {

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Brute Force: Brute Force: for each elem with every later elem(iterate i in (0, n), iterate j in (i+1, n)), profit = prices[j] - prices[i], keep max → O(n²), O(1).
    * One-Pass Optimized: iterate i in (0, n), track min price so far in low, profit = prices[i] - low, update max → O(n), O(1).
    * Sliding Window: iterate i = 0, j=i+1 to n, store min price index in i(slide i = j if prices[j] < prices[i]), profit = prices[j] - prices[i], update max → O(n), O(1).
    */

    
    /*
    * ONE LINER => Brute Force: for each elem with every later elem(iterate i in (0, n), iterate j in (i+1, n)), profit = prices[j] - prices[i], keep max → O(n²), O(1).
    *
    * Approach 1: Brute-force
    * - Iterate over all pairs of days (i, j) with i < j.
    * - Compute profit if buying on day i and selling on day j: prices[j] - prices[i].
    * - Keep track of the maximum profit encountered.
    *
    * Time Complexity: O(n^2) → two nested loops over n days
    * Space Complexity: O(1)  → only a single variable 'max' used
    *
    * Note: Works correctly but inefficient for large inputs. Optimal solution exists in O(n) time.
    */
    public int maxProfit(int[] prices) {
        int max = 0;
        for(int i=0;i<prices.length;i++){
            for(int j=i+1;j<prices.length;j++){
                max = Math.max(max, prices[j]-prices[i]);
            }
        }
        return max;
    }
    
    
    /*
    * ONE LINER => One-Pass Optimized: iterate i in (0, n), track min price so far in low, profit = prices[i] - low, update max → O(n), O(1).
    *
    * Approach 2: One-pass Optimized
    * - Track the minimum price seen so far (low) as we iterate through the array.
    * - At each day i, compute the potential profit if we sell on that day: prices[i] - low.
    * - Update max profit if this profit is higher than current max.
    * - If prices[i] is lower than the current low, update low.
    *
    * Rationale:
    * - Buying at the lowest price seen so far maximizes potential profit.
    * - Only a single pass is needed, no nested loops.
    *
    * Time Complexity: O(n) → single pass through prices
    * Space Complexity: O(1) → only two variables (low, max) used
    *
    * This is the optimal solution for the "Best Time to Buy and Sell Stock" problem.
    */
    public static int maxProfit2(int[] prices) {
      int max = 0, low = prices[0];
        for(int i=0;i<prices.length;i++){
            // low = Math.min(low, prices[i]); // equivalent to: if(prices[i]<low) low = prices[i];
            if(prices[i]<low){
                low = prices[i];
            }
            
            max = Math.max(max, prices[i]-low);

        }
        return max;
    }

    /*
    * ONE LINER => Sliding Window: iterate i = 0, j=i+1 to n, store min price index in i(slide i = j if prices[j] < prices[i]), profit = prices[j] - prices[i], update max → O(n), O(1).
    *
    * Approach 3: Sliding Window / Index Tracking
    * - Track the index of the minimum price seen so far (i).
    * - Iterate j from i+1 to end:
    *     - Compute profit = prices[j] - prices[i]
    *     - Update max if higher
    *     - If prices[j] < prices[i], move the "window" start: i = j
    *
    * Time Complexity: O(n) → single pass
    * Space Complexity: O(1)
    *
    * Note: This version explicitly demonstrates a sliding window concept.
    *       Conceptually equivalent to Approach 2, but the index makes the moving window intuitive.
    */
    public int maxProfitSlidingWindow(int[] prices) {
        int max = 0;
        for(int i=0,j=i+1;j<prices.length;j++){
            if(prices[j]<prices[i]){
                i = j; // slide window forward
            }
            max = Math.max(max, prices[j]-prices[i]);

        }
        return max;
    }
}

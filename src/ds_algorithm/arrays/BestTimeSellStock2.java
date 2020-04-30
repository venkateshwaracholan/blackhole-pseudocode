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

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

public class BestTimeSellStock2 {
  
//  approach: 2d combination, recursion, accumulation, max
// the brute which is real hard
// Time n(n**n) space O(n) recursion depth
// twoloops to get all combinations and recusion to find the next max and add it current
//  profit = j-1 and send j+1 to recussion and add that to profit
// return max possible in recursion
  public int maxProfit(int[] prices, int s){
    if (s >= prices.length)
      return 0;
    int maximum = 0;
    for(int i=s;i<prices.length;i++){
      for(int j=i+1;j<prices.length;j++){
        if(prices[j]>prices[i]){
          int profit  = maxProfit(prices, j+1) + prices[j] - prices[i];
          if(profit>maximum)
            maximum = profit;
        }
      }
    }
    return maximum;
  }
  
  public int maxProfit(int[] prices) {
    return maxProfit(prices,0);
  }
  
  
//  approach: iteration and accumulation for case
// Time: O(n) space: O(1)
// accumulate if val at i>i-1, simple 
  public int maxProfitOptimal(int[] prices) {
    int maximum = 0;
    for(int i=1;i<prices.length;i++){
        if(prices[i]>prices[i-1]){
            maximum+= prices[i] - prices[i-1];
        }
    }
    return maximum;
  }
  
// approach: peak valley, subtraction
// Time: O(n) space: O(1)
// peak valley approach
// first peak and valley are 0
//  first find i until val at i+1<i
//  assign to valley
//  then find i until val at i<i+1
//  assign to peak
//  add peak - valley to profit, simple
  public int maxProfit2(int[] prices) {
    int i = 0;
    int valley = prices[0];
    int peak = prices[0];
    int maxprofit = 0;
    while (i < prices.length - 1) {
        while (i < prices.length - 1 && prices[i] >= prices[i + 1])
            i++;
        valley = prices[i];
        while (i < prices.length - 1 && prices[i] <= prices[i + 1])
            i++;
        peak = prices[i];
        maxprofit += peak - valley;
    }
    return maxprofit;
  }
  
  
  
  
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.min_max_path_to_reach_target;

/**
 *
 * @author venkateshwarans
 */

/*
322. Coin Change
https://leetcode.com/problems/coin-change/

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.

*/

// [1,2,3] 4
public class CoinChange {
  
  public static boolean show = false;
  public static int coinChangeBrute(int n){
    return 0;
  }
  
  public static int coinChangerecursive(int coins[], int amount){
    return coinChangerec(coins, amount, new int[amount+1]);
  }
  //[2,4] 5
  
  //we can use integre.maxvalue or amount+1 as the initial for min
  public static int coinChangerec(int coins[], int amount, int[] dp){
    if(amount<0)return -1;
    if(amount==0) return 0;
    if(dp[amount]!=0)return dp[amount];
    int min = Integer.MAX_VALUE;
    for(int i=0;i<coins.length;i++){
      int res = coinChangerec(coins,amount-coins[i],dp);
      if(res>=0 && res<min){
        min = res+1;
      }
    }
    dp[amount] = min==Integer.MAX_VALUE ? -1 : min;
    return dp[amount]; 
  }
  
  public static int coinChangeIterative(int coins[], int amount){
    int dp[] = new int[amount +1];
    dp[0] = 0;
    for(int i=1;i<=amount;i++){
      dp[i] = Integer.MAX_VALUE;
      for(int j=0;j<coins.length;j++){
        if(i>=coins[j] && dp[i-coins[j]]!=Integer.MAX_VALUE){
          dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }

  public static void main(String[] args){
    test(coinChangeIterative(new int[]{1,2,3},4), 2);
    test(coinChangeIterative(new int[]{2,4},5), -1);
    
    test(coinChangerecursive(new int[]{1,2,3},4), 2);
    test(coinChangerecursive(new int[]{2,4},5), -1);
    
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}

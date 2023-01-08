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

import java.util.*;

public class CoinChange {
  
  public static boolean show = false;
  
  
  
  
    //  Time; O(s**n) Space: O(n) s - amount, n - coins
    //  Core idea: top down, tree recursion, tail recursion, brute force
    //  reduce capacity and break into smaller sub problems
    //  base case: amount<0 return -1
    //  amount ==0 return 0;
    //  have a local min variable which we can use to store min of all coin combos in stack memory
    //  init min to int max
    //  try all combinations in recursion as we can use any number of coins repeatedly(knapsack with inifite items)
    //  if c>=0 meaning it was possible to dispense and comparing it with min and c<min
    //  assign c to min
    //  if min was never assigned, return -1 or min
  
    public int coinChange(int[] coins, int amount) {
        if(amount<0)return -1;
        if(amount==0) return 0;
        int min=Integer.MAX_VALUE;
        for(int coin:coins){
            int c = coinChange(coins, amount-coin);
            if(c>=0&&c<min) min=c+1;
        }
        return min==Integer.MAX_VALUE ? -1 : min;
    }
  
    // same as above, using a dp memoization table to avoid duplicate work
    // duplicate work is computing for same amount repeatedly
    // if an amout is being assigned to dp , all combinations are already compared and min is found
    public int coinChangeMemo(int[] coins, int amount) {
        return recursion(coins, amount, new int[amount+1]);
    }

    public int recursion(int coins[], int amount, int dp[]){
        if(amount<0) return -1;
        if(amount==0) return 0;
        if(dp[amount]!=0) return dp[amount];
        int min = Integer.MAX_VALUE;
        for(int coin: coins){
            int c = recursion(coins, amount-coin, dp);
            if(c>=0 && c< min){
                min = c+1;
            }
        }
        dp[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return dp[amount];
    }
  
  
    //  Time: O(s*n) space: O(s)
    //  Core Idea: DP, Bottom up, tabulation
    //  we initialize ever amount with amount+1 so that min works well
    //  starting from small amout, we try every coin
    //  coin <= i then compute do min from dp[i] and dp[i-coin]+1
    //  dp[i-coin]+1 because if the same amount was dispensable with a different coin in less number of coins
    //  check dp[amount] has assigned value and then return -1 if not or the value
  
    public int coinChangeBottomUp(int[] coins, int amount) {
        int dp[] = new int[amount+1];
        for(int i=1;i<=amount;i++){
            dp[i] = amount+1;
            for(int coin: coins){
                if(coin<=i){
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }
        return dp[amount]==amount+1 ? -1: dp[amount];
    }
  
    //  Time: O(s*n) space: O(s)  
    //  same as above, but to avoid a check, for every coin , we are starting from coin to amount in inner loop 
  
    public static int coinChangeBottomupAlt(int[] coins, int amount) {
        int dp[] = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int coin:coins){
            for(int i=coin;i<=amount;i++){
                dp[i] = Math.min(dp[i-coin]+1, dp[i]);
            }
        }
        return dp[amount]==amount+1 ? -1 : dp[amount];
    }
    
    
    
    // we are doing a bfs
    //     0
    //    125
    // 125 125 125
    
    // in the above bfs, we are avoiding duplicate x with visited
    // so, the branch reaching amount will be the shortest number of coins, as 1 takes 11 times in its branch
    public int coinChangebfs(int[] coins, int amount) {
        Queue<Integer> q = new LinkedList();
        boolean[] v = new boolean[amount+1];
        q.add(0);
        int count = 0;
        while(!q.isEmpty()){
            for(int siz = q.size();siz>0;siz--){
                int x = q.poll();
                if(x==amount) return count;
                if(x>amount || v[x]) continue;
                v[x]=true;
                for(int coin:coins) q.add(x+coin);
            }
            count++;
        }
        return -1;
    }
    
    // same starting from 11 to 0
    public int coinChangebfs2(int[] coins, int amount) {
        Queue<Integer> q = new LinkedList();
        boolean[] v = new boolean[amount+1];
        q.add(amount);
        int count = 0;
        while(!q.isEmpty()){
            for(int siz = q.size();siz>0;siz--){
                int x = q.poll();
                if(x==0) return count;
                if(x<0 || v[x]) continue;
                v[x]=true;
                for(int coin:coins) q.add(x-coin);
            }
            count++;
        }
        return -1;
    }
  
    public static void main(String[] args){
          //test(coinChangeBottomUp2D(new int[]{1,2,5},11), 3);
    }
  
    public static void test(int got, int exp){
        System.out.println(got == exp);
        if(show || got != exp){
            System.out.println("got     : "+got);
            System.out.println("expected: "+exp);
        }
    }
}


// this is unwanted for this problem as we are wasting memory
/*
  
   0 1 2 3 4 5 6 7 8 9 10 11
1 0 1 2 3 4 
2 0 1 1 
3 0
5 0

   0 1 2 3 4 5 6 7 8 9 10 11
5 0  n n n n n n n n 1 n  n          2
2 0    1    2 1  3    4    2 
1 0 1 1 2 2 1  
  
*/  
  
    //  Time: O(s*n) space: O(s)  
    //  core idea: similar to knapsack but there are differences
    //  in knapsack we always consider prev row for subtracting weight in index, here it is current
    //  dp[i+1][j] = dp[i][j]; is critical because we are not filling teh whole matrix with max value
    //  assign the prev rows value first and then only
    //  check if current row has better and apply min function
    //  we need to check if it max value before adding 1 and skip min becoz pev row is already assigned
/*  
    public static int coinChangeBottomUp2D(int[] coins, int amount) {
        int dp[][]  = new int[coins.length+1][amount+1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;
        for(int i=0;i<coins.length;i++){
            for(int j=1;j<=amount;j++){
                dp[i+1][j] = dp[i][j];
                if(j>=coins[i] && dp[i+1][j-coins[i]]!=Integer.MAX_VALUE){
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i+1][j-coins[i]]+1);
                }
            }
        }
        return dp[coins.length][amount]==Integer.MAX_VALUE ? -1 : dp[coins.length][amount];
    }
*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.array_string_dp;

/**
 *
 * @author vshanmugham
 */
import java.util.*;


// https://leetcode.com/problems/two-city-scheduling

public class TwoCityScheduling {
  
// Time: O(2**(n/2)) space: O(2n) 2n recursion depth
// core idea: top down, recursion, brute, tail recursion
// base condition is if reaches 2n
// x: overall counter, i: A city counter, j:B city counter
// initially, try min of both combinations
//  if i or j reached n, then use only other combination
  public int twoCitySchedCostBruteDfs(int[][] costs) {
      return dfs(costs, 0, 0, 0, costs.length/2);
  }

  public int dfs(int[][] costs,int x, int i, int j, int n){
      if(x==2*n) return 0;
      if(i==n)
          return costs[x][1]+dfs(costs, x+1, i, j+1, n);
      if(j==n)
          return costs[x][0]+dfs(costs, x+1, i+1, j, n);
      return Math.min(costs[x][1]+dfs(costs, x+1, i, j+1, n), costs[x][0]+dfs(costs, x+1, i+1, j, n));
  }
  
  
  
// same as above, using a 2d DP table to avoid duplicate work, memoization
//  Time: O(n*n)) space: O(n*n)
  public int twoCitySchedCostMemoDfs(int[][] costs) {
    int n = costs.length/2;
    Integer dp[][] = new Integer[n][n];
    return dfsDp(costs, 0, 0, 0, n, dp);
  }

  public int dfsDp(int[][] costs,int x, int i, int j, int n, Integer dp[][]){
      if(x==2*n) return 0;
      if(dp[i][j]!=null) return dp[i][j];
      if(i==n)
          return dp[i][j] = costs[x][1]+dfsDp(costs, x+1, i, j+1, n, dp);
      if(j==n)
          return dp[i][j] = costs[x][0]+dfsDp(costs, x+1, i+1, j, n, dp);
      return dp[i][j] = Math.min(costs[x][1]+dfsDp(costs, x+1, i, j+1, n, dp), costs[x][0]+dfsDp(costs, x+1, i+1, j, n, dp));
  }
  
  
// Time: O(n*n) space O(n*n)
//  approach: bottom up, dp tabulation 2d
// populate row and col with respective sums
// bound are upto n for botha nd not 2n
//  min of the following 2
// if we choose left value in dp, add B city at i+j+1, if we choose bottom value in dp ad A city value at i+j+1
//  litttle tricky but easy to understand if u can relate to the above recusion
  public static int twoCitySchedCost2DDp(int[][] costs) {
      int n = costs.length/2;
      int dp[][] = new int[n+1][n+1];
      for(int i=0;i<n;i++) dp[i+1][0] = dp[i][0] + costs[i][0];
      for(int i=0;i<n;i++) dp[0][i+1] = dp[0][i] + costs[i][1];
      for(int i=0;i<n;i++)
          for(int j=0;j<n;j++)
              dp[i+1][j+1] = Math.min(dp[i][j+1]+costs[i+j+1][0],dp[i+1][j]+costs[i+j+1][1]);
      return dp[n][n];
  }
  
// Time: O(n*n) space O(n)
  public static int twoCitySchedCost1DDP(int[][] costs) {
      int n = costs.length/2;
      int prev[] = new int[n+1];
      for(int i=0;i<n;i++) prev[i+1] = prev[i] + costs[i][1];
      for(int i=0;i<n;i++){
        int cur[] = new int[n+1];
        cur[0] = prev[0]+costs[i][0];
          for(int j=0;j<n;j++){
              cur[j+1] = Math.min(prev[j+1]+costs[i+j+1][0],cur[j]+costs[i+j+1][1]);
              print(prev);
              print(cur);
          }
          prev = cur;
      }
      return prev[n];
  }
  
  
  
//  Time: O(nlogn) space: O(1)
//  variants of greedy algorithms
//  core idea: greedy, cost difference, sorting
//  sorting based on difference, B-A
//  and assigning first half frm B and other half from A's
//  because B-A will get more negative when B is smaller
//  and B-A will get more positve as A is smaller
//  so for overall minimum, we can sort this was and choose first half from B and second half from A
  
  
  
  public int twoCitySchedCostAlt(int[][] costs) {
      Arrays.sort(costs, Comparator.comparing((a)->a[1]-a[0]));
      int sum = 0;
      for(int i=0;i<costs.length;i++)
          sum+=i<costs.length/2 ? costs[i][1] : costs[i][0];
      return sum;
  }
  
  public int twoCitySchedCostBetter(int[][] costs) {
      Arrays.sort(costs, Comparator.comparing((a)->a[1]-a[0]));
      int sum = 0;
      for(int i=0;i<costs.length/2;i++)
          sum+=costs[i][1] + costs[costs.length-1-i][0];
      return sum;
  }
  
  public int twoCitySchedCostLong(int[][] costs) {
      Arrays.sort(costs, Comparator.comparing((a)->a[1]-a[0]));
      int sum = 0, neg = 0;
      for(int i=0;i<costs.length;i++){
          sum+=costs[i][0];
          if(i<costs.length/2)
              neg += costs[i][1]-costs[i][0];
      }
      return sum+neg;
  }
  
  
  
  public static void print(int dp[][]){
    for(int i=0;i<dp.length;i++){
      for(int j=0;j<dp[0].length;j++)
        System.out.print(dp[i][j]+" ");
      System.out.println();
    }
    System.out.println();
  }
  
  public static void print(int dp[]){
    for(int i=0;i<dp.length;i++)
      System.out.print(dp[i]+" ");
    System.out.println();
  }
  
  public static void main(String args[]){
//    twoCitySchedCost(new int[][]{{10,20},{30,200},{400,50},{30,20}});
  }
  
}
//          20 200 50 20
//      0   20 220 
// 10   10  50 100
// 30   40  90 110
// 400  
// 30  
// 
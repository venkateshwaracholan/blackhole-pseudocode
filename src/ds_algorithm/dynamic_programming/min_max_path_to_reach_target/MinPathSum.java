/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.min_max_path_to_reach_target;

import java.util.Arrays;

/**
 *
 * @author vshanmugham
 */


// https://leetcode.com/problems/minimum-path-sum

public class MinPathSum {
  
// Brute force solution where we re calculate for every combinations from every cell recursively
// Time: O(2**(m+n)) space: O(m+n)
//  core idea: start from left and for every cell there are two possibilitie, bottom and right,
//  if bottom or right is not present lets take it as integer max value
//  stop recursion when the last cell is reached.
//  add min of bottom and right to self and return.
//  but this with this approach calculated cells are recalculated several times upto 2**(m+n)
  public int calculate(int grid[][],int i, int j, int m, int n){
    if( i> m-1 || j>n-1) return Integer.MAX_VALUE;
    else if(i==m-1 && j==n-1) return grid[i][j];
    return grid[i][j] + Math.min(calculate(grid,i+1,j,m,n),calculate(grid,i,j+1,m,n));
  }

  public int minPathSum(int[][] grid) {
    return calculate(grid,0,0,grid.length, grid[0].length);
  }
  
//  DP on brute
//  Time: O(mn) space: O(mn)
//  core idea: lets store what is already computed and return if present
  public int calculateDp(int grid[][],int i, int j, int m, int n, int dp[][]){
    if( i> m-1 || j>n-1) return Integer.MAX_VALUE;
    else if(i==m-1 && j==n-1) return grid[i][j];
    if(dp[i][j]==-1)
      dp[i][j] = grid[i][j] + Math.min(calculateDp(grid,i+1,j,m,n,dp),calculateDp(grid,i,j+1,m,n,dp));
    return dp[i][j];
  }

  public int minPathSumDp(int[][] grid) {
    int dp[][] = new int[grid.length][grid[0].length];
    for(int i=0;i<grid.length;i++)
      for(int j=0;j<grid[0].length;j++)
        dp[i][j] = -1;
    return calculateDp(grid,0,0,grid.length, grid[0].length, dp);
  }
  
  
// Iterative Dp with 2D mn sapce
// Time: O(mn) space: O(mn)
// core idea : accumulate the solution in a dp array
// 4 cases,   1. 0,0 element, 2. row elem, 3.col element, 4 others
// for case 4 alone we compare and get the min from bottom and right
  public int minPathSum2DDPIterative(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int dp[][] = new int[m][n];
    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){
        if(i==0 && j!=0)
          dp[i][j] = grid[i][j] + dp[i][j-1];
        else if(i!=0 && j==0)
          dp[i][j] = grid[i][j] + dp[i-1][j];
        else if(i!=0 && j!=0)
          dp[i][j] = grid[i][j] + Math.min(dp[i][j-1], dp[i-1][j]);
        else
          dp[i][j] = grid[i][j];
      }
    }
    return dp[m-1][n-1];
  }


// Iterative Dp with 1D n sapce
// Time: O(mn) space: O(n)
// core idea : accumulate the solution in a 1D dp array
// we dont need a 2d array to store in dp, u only need of length n(columns) if we traverse cols in each row
// if we traverse rows in each col, we need m space
// 4 cases, 1. 0,0 element, 2. row elem, 3.col element, 4 others
// for case 4 alone we compare and get the min from bottom and right
  public int minPathSum1DDPIterative(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int dp[] = new int[n];
    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){
        if(i==0 && j!=0)
          dp[j] = grid[i][j] + dp[j-1];
        else if(i!=0 && j==0)
          dp[j] = grid[i][j] + dp[j];
        else if(i!=0 && j!=0)
          dp[j] = grid[i][j] + Math.min(dp[j], dp[j-1]);
        else
          dp[j] = grid[i][j];
      }
    }
    return dp[n-1];
  }  
  
/*
1,3,1
1,5,1
4,2,1
*/
  
//  Time:O(mn) space:O(1)
//  core idea: 
//  leverage the top and left element for every element
//  and then the result will lie in the last element of the matrix
  public int minPathSumDpNoSpace(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){
        int t = i-1>=0 ? grid[i-1][j] : Integer.MAX_VALUE;
        int l = j-1>=0 ? grid[i][j-1] : Integer.MAX_VALUE;
        int x=Math.min(l,t);
        grid[i][j]+= x==Integer.MAX_VALUE ? 0 : x;
      }
    }
    return grid[m-1][n-1];
  }
  
  public int minPathSumAlt(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){
        if(i-1>=0 && j-1>=0)
          grid[i][j]+= Math.min(grid[i-1][j],grid[i][j-1]);
        else if(i-1>=0)
          grid[i][j]+= grid[i-1][j];
        else if(j-1>=0)
          grid[i][j]+= grid[i][j-1];
      }
    }
    return grid[m-1][n-1];
  }
  
  public int minPathSumAlt2(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for(int i=1;i<m;i++)
      grid[i][0]+=grid[i-1][0];
    for(int j=1;j<n;j++)
      grid[0][j]+=grid[0][j-1];
    for(int i=1;i<m;i++)
      for(int j=1;j<n;j++)
        grid[i][j]+= Math.min(grid[i-1][j],grid[i][j-1]);
    return grid[m-1][n-1];
  }
}

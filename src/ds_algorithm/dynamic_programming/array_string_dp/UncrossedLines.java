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

// https://leetcode.com/problems/uncrossed-lines


public class UncrossedLines {
  
//  Brute recursion
//  Time: O(2**(m+n)) space: O(m+n)
//  core idea: recursion
//  if same then move pointers for both, or find max of 
//  calling recusrion with moving 1 poiter forwards for each.
  public int maxUncrossedLinesRecBrute(int[] A, int[] B) {
      return recursion(A, B, 0 , 0);
  }

  public int recursion(int a[], int b[], int i, int j){
      if(a.length==i || b.length==j) return 0;
      if(a[i]==b[j])
          return 1+recursion(a,b,i+1,j+1);
      return Math.max(recursion(a,b,i+1,j), recursion(a,b,i,j+1));
  }
  
//  Brute optimised DP
//  Time: O(mn) space: O(mn)
//  memoizing in 2D array to avoid duplicate work
//  Using Integer to avoid assigning -1 to dp array
//  since both recare in if else case type, its neither top down nor bottom up, its just recursion
  public int maxUncrossedLinesRecDP(int[] A, int[] B) {
      Integer dp[][] = new Integer[A.length][B.length];
      return recursionDp(A, B, 0 , 0, dp);
  }

  public int recursionDp(int a[], int b[], int i, int j, Integer dp[][]){
      if(a.length==i || b.length==j) return 0;
      if(dp[i][j]!=null) return dp[i][j];
      if(a[i]==b[j])
          return dp[i][j] = 1+recursionDp(a,b,i+1,j+1,dp);
      return dp[i][j]=Math.max(recursionDp(a,b,i+1,j,dp), recursionDp(a,b,i,j+1,dp));
  }
  
//  2d DP
//  Time: O(mn) space O(mn)
//  approach: dp
//  we choose m+1,n+1 array for dp, so that prev row and col have zeroes by default
//  iterate m x n
//  if the characters match, then add 1 + previouly matched character number
//  else we have to take max of prev row, prev column
//  previous row signifies, propogating the match to its right as even if another char matches in next col
//  , this will help with the count
//  prev column signifies already matched characters
  
//    a b c d e
//  0 0 0 0 0 0
//a 0 1 1 1 1 1   // prev row contibuting to match propogation
//c 0 1 1 2 2 2   
//e 0 1 1 2 2 3
//b 0 1 2 2 2 2  // previous column contibuted to matching b as we already had a matching 'a' before
  public int maxUncrossedLines2DTopDown(int[] A, int[] B) {
      int dp[][] = new int[A.length+1][B.length+1];
      for(int i=0;i<A.length;i++){
          for(int j=0;j<B.length;j++){
              if(A[i]==B[j])
                  dp[i+1][j+1] = dp[i][j] +1;
              else
                  dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
          }
      }
      return dp[A.length][B.length];
  }
  
// same as above but bottom up, staring from below
  
  public int maxUncrossedLines2DBottomUp(int[] A, int[] B) {
      int dp[][] = new int[A.length+1][B.length+1];
      for(int i=A.length-1;i>=0;i--){
          for(int j=B.length-1;j>=0;j--){
              if(A[i]==B[j])
                  dp[i][j] = dp[i+1][j+1] +1;
              else
                  dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
          }
      }
      return dp[0][0];
  }
  
  
// 1D DP
//  Time: O(mn) space O(min(m,n))
//  approach top down approach
//  in the above dp array, we only used current row and previoud row's info and not anything else;
//  so just 2 arrays of length col are enough, cur and prev
//  and if we want to reduce space, we have to create space for min length string
//  so, B should be minimum length, or swap it
//  since min space should be used, we have to interate max sting first as rows and min as columns
//  matrix should be like a standing rectangle
//  create cur everytime and use prev to update this curr
//  and then prev = cur;
  
  public int maxUncrossedLines1DTopDown(int[] A, int[] B) {
      if(B.length>A.length){
          int[] temp = A;
          A = B;
          B = temp;
      }
      int prev[] = new int[B.length+1];
      for(int i=0; i<A.length;i++){
          int cur[] = new int[B.length+1];
          for(int j=0; j<B.length;j++){
              if(A[i]==B[j])
                  cur[j+1] = prev[j] +1;
              else
                  cur[j+1] = Math.max(prev[j+1], cur[j]);
          }
          prev = cur;
      }
      return prev[B.length];
  }

  public int maxUncrossedLines1DBottomUp(int[] A, int[] B) {
    if(B.length>A.length){
        int[] temp = A;
        A = B;
        B = temp;
    }
    int prev[] = new int[B.length+1];
    for(int i=A.length-1;i>=0;i--){
        int cur[] = new int[B.length+1];
        for(int j=B.length-1;j>=0;j--){
            if(A[i]==B[j])
                cur[j] = prev[j+1] +1;
            else
                cur[j] = Math.max(prev[j], cur[j+1]);
        }
        prev = cur;
    }
    return prev[0];
  }
  
}

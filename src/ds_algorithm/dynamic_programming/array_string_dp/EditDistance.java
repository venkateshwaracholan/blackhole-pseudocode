package ds_algorithm.dynamic_programming.array_string_dp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/edit-distance/

public class EditDistance {

// Time: O(3**(m+n)) space: O(m+n)  
// Cored Idea: String DP
//  we use 2 pointers i and j
// if one of the words reaches beyond boundaries, then return other's length - pointer index
// becoz that many insertions or deletes need to happen
// only 4 cases,
// both characters matching, i+1,j+1, no increment in ans
//  horse  ros
// insert =>  rhorse os   i,   j+1
// delete =>  orse ros    i+1,   j
// replace => orse os     i+1, j+1
  
  public int minDistanceRecBrute(String word1, String word2) {
      return recursion(word1, word2, 0, 0);
  }

  public int recursion(String a, String b, int i, int j){
      if(i==a.length()) return b.length()-j;
      if(j==b.length()) return a.length()-i;
      if(a.charAt(i)==b.charAt(j))
          return recursion(a,b,i+1,j+1);
      else
          return 1 + Math.min(Math.min(recursion(a,b,i,j+1), recursion(a,b,i+1,j)), recursion(a,b,i+1,j+1));
  }
  
  public int minDistanceRecDP(String word1, String word2) {
      Integer dp[][] = new Integer[word1.length()][word2.length()];
      return recursion(word1, word2, 0, 0, dp);
  }

//  Time: O(mn) Space: O(mn)
//  core Idea: memeo onthe brute above
//  using2d array to memo and avoid duplicate work
  public int recursion(String a, String b, int i, int j, Integer dp[][]){
      if(i==a.length()) return b.length()-j;
      if(j==b.length()) return a.length()-i;
      if(dp[i][j]!=null) return dp[i][j];
      if(a.charAt(i)==b.charAt(j))
          return dp[i][j] = recursion(a,b,i+1,j+1,dp);
      else
          return dp[i][j] = 1 + Math.min(Math.min(recursion(a,b,i,j+1,dp), recursion(a,b,i+1,j,dp)), recursion(a,b,i+1,j+1,dp));
  }
  
  
//  Time: O(mn) space: O(mn)
//  we need to populate row and col with incremental values as when i or j as 0
//  for the case where one array ends we neeed to do that many insersion or deletion
//  so we use 2 loops to fill where i and j are 0's in  respective loops,
//  Note make sure to fill, last col as well, i<=m, j<=n bounds
//  two loops, 
//  if match, just copy the values from prev dop diagonal
//  if not a match then 1+ min(prev dp diag, prev, row, prev col)
//  return value from the last index in DP.
  public static int minDistance2DTopDown(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int dp[][] = new int[m+1][n+1];
    for (int i = 0; i <= m; i++) dp[i][0] = i;    	
    for (int j = 0; j <= n; j++) dp[0][j] = j;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            if(word1.charAt(i)==word2.charAt(j))
                dp[i+1][j+1] = dp[i][j];
            else
               dp[i+1][j+1] = 1 + Math.min(Math.min(dp[i][j+1],dp[i+1][j]),dp[i][j]); 
        }
    }
    return dp[m][n];
  }
  
//  Time: O(mn) space: O(min(m,n))  
//  same as ablove, converting to 1D DP
//  since we are only accessing cur and prev,
//  we can use 2 array of size n+1
//  always make sure word2 is smaller to reduce space to O(min(m,n))
//  fill the prev strip with incremental values using a loop with bound j<=n
//  when we create cur, make sure to assing cur[0] to i+1, which populates initial values for i==0 case
//  dont forget ro assing cur to prev
//  return prev[n]
  
  public int minDistance1DTopDown(String word1, String word2) {
      if(word1.length()<word2.length()){
          String temp = word1;
          word1 = word2;
          word2 = temp;
      }
      int m = word1.length(), n = word2.length();
      int prev[] = new int[n+1];    	
    for (int j = 0; j <= n; j++) prev[j] = j;
      for(int i=0;i<m;i++){
          int cur[] = new int[n+1];
          cur[0] = i+1;
          for(int j=0;j<n;j++){
              if(word1.charAt(i)==word2.charAt(j))
                  cur[j+1] = prev[j];
              else
                 cur[j+1] = 1 + Math.min(Math.min(cur[j],prev[j+1]),prev[j]); 
          }
          prev = cur;
      }
      return prev[n];
  }
  
//  Time: O(mn) space: O(mn)
//  we need to populate row and col with decremental values as when i==m and j==n (las row and last col)
//  for the case where one array ends we neeed to do that many insertsion or deletion
//  so we use 2 loops to fill teh above 
//  Note make sure to fill, last col as well, i<=m, j<=n bounds, decremental so m-i, n-j
//  two loops, 
//  if match, just copy the values from prev dop diagonal
//  if not a match then 1+ min(prev dp diag, prev, row, prev col)
//  return value from the first index in DP.
  
  public int minDistance2DBottomUp(String word1, String word2) {
      int m = word1.length(), n = word2.length();
      int dp[][] = new int[m+1][n+1];
    for (int i = 0; i <= m; i++) dp[i][n] = m-i;    	
    for (int j = 0; j <= n; j++) dp[m][j] = n-j;
      for(int i=m-1;i>=0;i--){
          for(int j=n-1;j>=0;j--){
              if(word1.charAt(i)==word2.charAt(j))
                  dp[i][j] = dp[i+1][j+1];
              else
                 dp[i][j] = 1 + Math.min(Math.min(dp[i][j+1],dp[i+1][j]),dp[i+1][j+1]); 
          }
      }
      return dp[0][0];
  }
  
//  Time: O(mn) space: O(min(m,n))  
//  same as ablove, converting to 1D DP
//  since we are only accessing cur and prev,
//  we can use 2 array of size n+1
//  always make sure word2 is smaller to reduce space to O(min(m,n))
//  fill the prev strip with decremental values using a loop with bound j<=n
//  when we create cur, make sure to assing cur[n] to m-i, which populates initial values for i==m case
//  dont forget ro assing cur to prev
//  return prev[0] 
  public static int minDistance1DBottomUp(String word1, String word2) {
      if(word1.length()<word2.length()){
          String temp =word1;
          word1 = word2;
          word2= temp;
      }
      int m = word1.length(), n = word2.length();
      int prev[] = new int[n+1];	
    for (int j = 0; j <= n; j++) prev[j] = n-j;
      for(int i=m-1;i>=0;i--){
          int[] cur = new int[n+1];
          cur[n] = m-i;
          for(int j=n-1;j>=0;j--){
              if(word1.charAt(i)==word2.charAt(j))
                  cur[j] = prev[j+1];
              else
                 cur[j] = 1 + Math.min(Math.min(cur[j+1],prev[j]),prev[j+1]); 
          }
          prev = cur;
      }
      return prev[0];
  }
  
  
  
  
  public static void main(String args[]){
//    minDistance("horse","ros");
  }
}

//    h o r s e
//  0 1 2 3 4 5
//r 1 1 1 1 1 1
//o 2 1
//s 3 1


//    h o r s e
//            3
//r   1 1 1 1 2
//o   1       1
//s 5 4 3 2 1 0
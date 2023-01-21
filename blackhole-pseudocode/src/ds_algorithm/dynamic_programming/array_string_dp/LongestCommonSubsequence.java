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

// https://leetcode.com/problems/longest-common-subsequence

public class LongestCommonSubsequence {

    //APPROACH 1 DFS + i,j => i hits len or j hits len ret 0, if chrs same then ret 1+ rec(i+1,j+1) adding 1 coz chars are same, 
    //  otehrwise we are finding max of moving both sides  max(rec(i+1,j) , rec(i,j+1))
     
    //  Brute recursion
    //  Time: O(2**(m+n)) space: O(m+n)
    //  core idea: top down, tree recursion, tail recursion, brute
    //  if same then move pointers for both, or find max of 
    //  calling recusrion with moving 1 poiter forwards for each.
    public int longestCommonSubsequenceRecBrute(String text1, String text2) {
        return recursion(text1, text2, 0, 0);
    }
    public int recursion(String a, String b, int i, int j){
        if(a.length()==i || b.length()==j) return 0;
        if(a.charAt(i)==b.charAt(j))
            return 1+ recursion(a, b, i+1,j+1);
        return Math.max(recursion(a, b, i,j+1), recursion(a, b, i+1,j));
    }
     //APPROACH 1.2 DFS + Integer[m][n] + i,j => i hits len or j hits len ret 0, if chrs same then ret 1+ rec(i+1,j+1) adding 1 coz chars are same, 
    //  otehrwise we are finding max of moving both sides  max(rec(i+1,j) , rec(i,j+1)) , assign to dp whereevr u see return
     
    // 
    //  Brute optimised DP
    //  Time: O(mn) space: O(mn)
    //  approach: top down, memoization 2D, tree recrusion, tail recursion
    //  memoizing in 2D array to avoid duplicate work
    //  Using Integer to avoid assigning -1 to dp array
    public int longestCommonSubsequenceRecDp(String text1, String text2) {
        Integer dp[][] = new Integer[text1.length()][text2.length()];
        return recursionDP(text1, text2, 0, 0, dp);
    }
    public int recursionDP(String a, String b, int i, int j, Integer[][] dp){
        if(a.length()==i || b.length()==j) return 0;
        if(dp[i][j]!=null) return dp[i][j];
        if(a.charAt(i)==b.charAt(j))
            return dp[i][j] = 1+ recursionDP(a, b, i+1,j+1, dp);
        return dp[i][j] = Math.max(recursionDP(a, b, i,j+1, dp), recursionDP(a, b, i+1,j, dp));
    }
  
    //APPROACH 2 Ite + 2d dp  new int[m+1][n+1] => if both chars equals get from  i,j and add 1, we are using extra 1 space in rows anmd cols
    //                            dp[i+1][j+1] =1+dp[i][j] if not we can use checks for i and j 0 and make it 0. ot interpolate dp[i+1][j+1] like this, 
    //                             else dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]) max of right and bottom of i,j finally ret dp[m][n] coz we added 1 extra space in dp
     
    //  2d DP
    //  Time: O(mn) space O(mn)
    //  approach: bottom up, dp tabulation 2d, forward
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

    //  this is why max(prev row, previous column)
    public int longestCommonSubsequence(String text1, String text2) {
        int m=text1.length(),n=text2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(text1.charAt(i)==text2.charAt(j))
                    dp[i+1][j+1] =1+dp[i][j];
                else 
                    dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]); 
            }
        }
        return dp[m][n];
    }
    // reverse of the samee
    public int longestCommonSubsequenceRev(String text1, String text2) {
        int m=text1.length(),n=text2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(text1.charAt(i)==text2.charAt(j))
                    dp[i][j] =1+dp[i+1][j+1];
                else 
                    dp[i][j] = Math.max(dp[i][j+1],dp[i+1][j]);
            }
        }
        return dp[0][0];
    }

    
    //APPROACH 3 Ite + 2d dp  new int[m+1][n+1] => if both chars equals get from  i,j and add 1, we are using extra 1 space in rows anmd cols
    //                            dp[i+1][j+1] =1+dp[i][j] if not we can use checks for i and j 0 and make it 0. ot interpolate dp[i+1][j+1] like this, 
    //                             else dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]) max of right and bottom of i,j finally ret dp[m][n] coz we added 1 extra space in dp
    //                           at max we are accessing only prev col, so just use 2 1D arrays, also to keep dp size minimal, create dp for lower length string 
    //                            craete new cur[] in first loop, prev = cur at end of first loop
     
    //APPROACH
    // since dp on strings are symmetrical, bottom up can be don ein forward and reverse directions
    // symmetrical dp 
    // 1D DP
    //  Time: O(mn) space O(min(m,n))
    //  approach: bottom up, dp tabulation 2d, forward
    //  i swappedd i and j, so now i is for col and j is for row...
    //  in the above dp array, we only used current row and previoud row's info and not anything else;
    //  so just 2 arrays of length col are enough, cur and prev
    //  and if we want to reduce space, we have to create space for min length string
    //  so, text1 should be minimum length, or swap it
    //  since min space should be used, we have to interate max sting first as rows and min as columns
    //  matrix should be like a standing rectangle
    //  create cur everytime and use prev to update this curr
    //  and then prev = cur;
    public int longestCommonSubsequenceForward(String text1, String text2) {
      if(text1.length()>text2.length()){
          String temp = text1;
          text1 = text2;
          text2 = temp;
      }
      int prev[] = new int[text1.length()+1];
      for(int j=0; j<text2.length();j++){
          int cur[] = new int[text1.length()+1];
          for(int i=0;i<text1.length();i++){
              if(text2.charAt(j)==text1.charAt(i))
                  cur[i+1] = prev[i]+1;
              else
                  cur[i+1] = Math.max(cur[i], prev[i+1]);
          }
          prev = cur;
      }
      return prev[text1.length()];
    }
    //  Time: O(mn) space O(min(m,n))
    //  approach: bottom up, dp tabulation 2d, reverse  
    //  same above 1d dp approach iterating from reverse
    //  approach bottom up approach
      public int longestCommonSubsequenceReverse(String text1, String text2) {
        if(text1.length()>text2.length()){
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        int prev[] = new int[text1.length()+1];
        for(int j=text2.length()-1;j>=0;j--){
            int cur[] = new int[text1.length()+1];
            for(int i=text1.length()-1;i>=0;i--){
                if(text2.charAt(j)==text1.charAt(i))
                    cur[i] = prev[i+1]+1;
                else
                    cur[i] = Math.max(cur[i+1], prev[i]);
            }
            prev = cur;
        }
        return prev[0];
      }
}

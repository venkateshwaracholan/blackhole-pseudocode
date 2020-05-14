/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/longest-common-subsequence

public class LongestCommonSubsequence {

  
//  2d DP
//  Time: O(mn) space O(mn)
//  approach: dp
//  we choose m+1,n+1 array fro dp, so that prev row and col have zeroes by default
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
    int dp[][] = new int[text2.length()+1][text1.length()+1];
    for(int j=0; j<text2.length();j++){
        for(int i=0;i<text1.length();i++){
            if(text2.charAt(j)==text1.charAt(i))
                dp[j+1][i+1] = dp[j][i]+1;
            else
                dp[j+1][i+1] = Math.max(dp[j+1][i], dp[j][i+1]);
        }
    }
    return dp[text2.length()][text1.length()];
  }
  
// 1D DP
//  Time: O(mn) space O(min(m,n))
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
//  same above 1d dp approach iterating from reverse
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

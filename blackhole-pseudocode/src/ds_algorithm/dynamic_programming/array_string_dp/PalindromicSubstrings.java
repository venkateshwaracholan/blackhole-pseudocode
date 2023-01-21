/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.dynamic_programming.array_string_dp;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/palindromic-substrings/description/

public class PalindromicSubstrings {
    
    //APPROACH 1 brute => 2 loops, j=i+1, for every comb, check if string is paindrome and inc c
    // time O(n*n*n) space: O(1) NO TLE
    public int countSubstrings(String s) {
        int c=0;
        for(int i=0;i<s.length();i++)
            for(int j=i;j<s.length();j++)
                if(isPalin(s,i,j)) c++;
        return c;
    }
    public boolean isPalin(String s, int i, int j){
        for(;i<=j;j--,i++)
            if(s.charAt(i)!=s.charAt(j)) return false;
        return true;
    }
    
    //APPROACH 2 Extend => 1 loops, add boths extend(i,i) and extend(i,i+1) to c 
    //                          extend(i,j) while chars equal and bound intact, move i to left ,j to right and inc c
 
    //time O(n*n) space: O(1)
    public int countSubstrings2(String s) {
        int c=0;
        for(int i=0;i<s.length();i++)
            c+= extend(s,i,i)+extend(s,i,i+1);
        return c;
    }
    public int extend(String s, int i, int j){
        int c=0;
        for(;i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j);j++,i--)
            c++;
        return c;
    }
    
    
    //APPROACH 3 => 2d DP boolean[n][n] => iterate right diagonals and move to top right dir as right diag elements reduce
    //                   j,i+j -> traverse right diagonals for(i=0,n) for(j=0,n-i) 
    //                  dp[j][i+j] = s[i]==s[i+j](same chars if i is 0) && (i<2||dp[j+1][i+j-1]) means true for checking same char and adj chars, then depend on dp
    //                  if(dp[j][i+j]) inc c, 
    //
    //
    public int countSubstrings3(String s) {
        int n=s.length(), c=0;
        boolean[][] dp = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i;j++){
                dp[j][i+j] = s.charAt(j)==s.charAt(i+j) && (i<2 || dp[j+1][i+j-1]);
                if(dp[j][i+j]) c++;
            }
        }
        return c;
    }
    //APPROACH 3.2 => 2d DP boolean[n][n] => iterate right diagonals and move to top right dir as right diag elements reduce
    //                   j,i+j -> traverse right diagonals for(i=0,n) for(j=0,n-i) 
    //                  dp[j][i+j] = s[i]==s[i+j](same chars if i is 0) && (i<2||dp[j+1][i+j-1]) means true for checking same char and adj chars, then depend on dp
    //                  if(dp[j][i+j]) inc c,  dp[j][i+j] can access only dp[j+1][i+j-1], its prev col, so making col size 2 and swapping col everytime saves space
    public int countSubstrings4(String s) {
        int n=s.length(), c=0;
        boolean[][] dp = new boolean[n][2];
        int col= 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i;j++){
                dp[j][col] = s.charAt(j)==s.charAt(i+j) && (i<2 || dp[j+1][col]);
                if(dp[j][col]) c++;
            }
            col^=1;
        }
        return c;
    }
    
    
    
    
}

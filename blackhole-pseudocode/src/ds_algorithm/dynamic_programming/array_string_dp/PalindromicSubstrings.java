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
    
    //APPROACH
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
    
    //APPROACH
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
    
    
    //APPROACH
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

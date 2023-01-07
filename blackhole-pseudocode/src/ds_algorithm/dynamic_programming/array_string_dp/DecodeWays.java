/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.dynamic_programming.array_string_dp;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/decode-ways/description/

public class DecodeWays {
    
    
    //
    public int numDecodings(String s) {
        return numDecodings(s,0);
    }

    public int numDecodings(String s,int i) {
        if(i>=s.length()) return 1;
        if(s.charAt(i)=='0') return 0;
        int x =  numDecodings(s,i+1);
        if(i<s.length()-1 && (s.charAt(i)=='1'||s.charAt(i)=='2'&&s.charAt(i+1)<'7'))
            x+=numDecodings(s,i+2);
        return x; 
    }
    
    
    //
    public int numDecodings2(String s) {
        return numDecodings2(s,0, new int[s.length()]);
    }

    public int numDecodings2(String s,int i, int[] dp) {
        if(i>=s.length()) return 1;
        if(s.charAt(i)=='0') return 0;
        if(dp[i]>0) return dp[i];
        int x =  numDecodings2(s,i+1,dp);
        if(i<s.length()-1 && (s.charAt(i)=='1'||s.charAt(i)=='2'&&s.charAt(i+1)<'7'))
            x+=numDecodings2(s,i+2,dp);
        dp[i] = x;
        return x; 
    }
    
    //
    public int numDecodings3(String s) {
        Set<String> sym = new HashSet<String>();
        for (int i=1; i<=26; i++) sym.add(String.valueOf(i));
        return numDecodings3(s,0, new int[s.length()],sym);
    }

    public int numDecodings3(String s,int i, int[] dp, Set<String> sym) {
        if(i>=s.length()) return 1;
        if(!sym.contains(s.substring(i,i+1))) return 0;
        if(dp[i]>0) return dp[i];
        int x =  numDecodings3(s,i+1,dp,sym);
        if(i<s.length()-1 && sym.contains(s.substring(i,i+2)))
            x+=numDecodings3(s,i+2,dp,sym);
        dp[i] = x;
        return x; 
    }
}

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
    
    //APPROACH
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
    public int numDecodingsMemo(String s) {
        return numDecodings(s,0, new Integer[s.length()]);
    }
    public int numDecodings(String s, int i, Integer[] dp) {
        if(i>=s.length()) return 1;
        if(s.charAt(i)=='0')return 0;
        if(dp[i]!=null) return dp[i];
        int x = numDecodings(s,i+1,dp);
        if(i<s.length()-1&&(s.charAt(i)=='1'||s.charAt(i)=='2'&&s.charAt(i+1)<'7'))
            x+=numDecodings(s,i+2,dp);
        return dp[i]=x;
    }
    

    
     //APPROACH
    // 1121
    // 0123
    public int numDecodings5(String s) {
        // if (s.charAt(0)=='0') return 0; 
        int[] dp = new int[s.length()+1];
        dp[0]=1;
        dp[1]=s.charAt(0) == '0' ? 0 : 1;
        for(int i=2;i<=s.length();i++){
            int one= Integer.parseInt(s.substring(i-1,i));
            int two= Integer.parseInt(s.substring(i-2,i)); 
            if(one!=0) dp[i]+=dp[i-1];
            if(two>=10&&two<=26) dp[i]+=dp[i-2];
        }
        return dp[s.length()];
    }
    //
    public int numDecodings6(String s) {
        int prevprev=1;
        int prev=s.charAt(0) == '0' ? 0 : 1;
        for(int i=2;i<=s.length();i++){
            int one= Integer.parseInt(s.substring(i-1,i));
            int two= Integer.parseInt(s.substring(i-2,i));
            int cur = 0;
            if(one!=0) cur+=prev;
            if(two>=10&&two<=26) cur+=prevprev;
            prevprev=prev;
            prev=cur;
        }
        return prev;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    // alternate approaches unwanted
        //using set to remove the above check
    // this makes code slower
    public int numDecodingsx(String s) {
        Set<String> set = new HashSet();
        for(int i=1;i<=26;i++) set.add(String.valueOf(i));
        return numDecodings(s,0, new Integer[s.length()], set);
    }
    public int numDecodings(String s, int i, Integer[] dp, Set<String> set) {
        if(i>=s.length()) return 1;
        if(s.charAt(i)=='0')return 0;
        if(dp[i]!=null) return dp[i];
        int x = numDecodings(s,i+1,dp,set);
        if(i<s.length()-1&&set.contains(s.substring(i,i+2)))
            x+=numDecodings(s,i+2,dp,set);
        return dp[i]=x;
    }
}

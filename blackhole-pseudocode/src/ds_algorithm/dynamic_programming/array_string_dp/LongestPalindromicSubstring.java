/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.dynamic_programming.array_string_dp;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/longest-palindromic-substring/description/

public class LongestPalindromicSubstring {
    
    //
    public String longestPalindrome(String s) {
        int max = 1,maxi=0;
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                if(isPalindrome(s,i,j)&&j-i+1>max){
                    max = j-i+1;
                    maxi = i;
                }
            }
        }
        return s.substring(maxi,maxi+max);
    }

    public boolean isPalindrome(String s, int i, int j){
        for(;i<=j;i++,j--)
            if(s.charAt(i)!=s.charAt(j)) return false;
        return true;
    }
    
    
    //
    public String longestPalindrome2(String s) {
        String maxStr="";
        for(int i=0;i<s.length();i++){
            String s1 = extend(s,i,i);
            String s2 = extend(s,i,i+1);
            String x = s1.length()>s2.length() ?s1:s2;
            if(maxStr.length()<x.length()) maxStr=x;
        }
        return maxStr;
    }

    public String extend(String s, int x,int y){
        int i=x,j=y;
        String maxStr="";
        for(;i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j);i--,j++)
            if(maxStr.length()<j-i+1) maxStr = s.substring(i,j+1);
        return maxStr;
    }
    
    
    //
    public String longestPalindrome3(String s) {
        int[] max=new int[2];
        for(int i=0;i<s.length();i++){
            int[] max1 = extend3(s,i,i);
            int[] max2 = extend3(s,i,i+1);
            int[] x = get(max1)>get(max2) ?max1:max2;
            if(get(max)<get(x)) max=x;
        }
        return s.substring(max[0],max[1]);
    }

    public int get(int[] max){
        return max[1]-max[0];
    }

    public int[] extend3(String s, int x,int y){
        int i=x,j=y;
        int[] max=new int[2];
        for(;i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j);i--,j++)
            if(get(max)<j-i+1) max=new int[]{i,j+1};
        return max;
    }
    
    //
    /*  babab
    0 1 2 3 4
    0 0 0 0 0
      0 0 0 0
        0 0 0
          0 0
            0
    */
    
    public String longestPalindrome4(String s) {
        int start=0,end=0, n=s.length();
        boolean[][] dp =new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i;j++){
                dp[j][i+j] = s.charAt(j)==s.charAt(i+j) && (i<2 || dp[j+1][i+j-1]);
                if(dp[j][i+j]&&i>end-start){
                    start= j;
                    end = i+j;
                }
            }
        }
        return s.substring(start,end+1);
    }
    
    
    /*
    0 0
    0 0
    0 0
    0 0
    0 0
        
    */

    /*  babab
    0 1 2 3 4
    0 0 0 0 0
      0 0 0 0
        0 0 0
          0 0
            0
    */
    //
    public String longestPalindrome5(String s) {
        int start=0,end=0, n=s.length();
        boolean[][] dp =new boolean[n][2];
        int col=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i;j++){
                dp[j][col] = s.charAt(j)==s.charAt(i+j) && (i<2 || dp[j+1][col]);
                if(dp[j][col]&&i>end-start){
                    start= j;
                    end = i+j;
                }
            }
            col^=1;
        }
        return s.substring(start,end+1);
    }
    
    
    
    // manchers algorithm
    // getting value from mirror if its a char from palindrome
    // 0123456789  
    // @#b#a#b#a#d#$
    //     c im
    public String longestPalindrome6(String s) {
        char[] ch = new char[2*s.length()+3];
        int x=0;
        ch[x++]  ='@';
        ch[x++]  ='#';
        for(char c: s.toCharArray()){
            ch[x++] = c;
            ch[x++] = '#';
        }
        ch[x++]='$';
        int start=0, maxright=0,maxlength=0,center=0;
        int p[] = new int[ch.length];
        for(int i=1;i<ch.length-1;i++){
            if(i<maxright)
                p[i] = Math.min(maxright-i, p[2*center-i]);
            while(ch[i+p[i]+1] == ch[i-p[i]-1]){
                p[i]++;
            }
            if(i+p[i]>maxright){
                maxright = i+p[i];
                center = i;
            }
            if(p[i]>maxlength){
                start = (i-p[i]-1)/2;
                maxlength =p[i];
            }
        }
        return s.substring(start,start+maxlength);
    }
    
}

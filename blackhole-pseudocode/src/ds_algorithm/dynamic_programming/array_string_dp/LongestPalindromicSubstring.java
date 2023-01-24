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
    
    //APPROACH 1 brute => 2 loops, j=i+1, for every comb, check if string is paindrome and update max and l, ret s.substring(l,l+max)
    
    // brute n*n*n
    public String longestPalindrome(String s) {
        int max = 1,l=0;
        for(int i=0;i<s.length();i++)
            for(int j=i+1;j<s.length();j++)
                if(isPalindrome(s,i,j)&&j-i+1>max){
                    max = j-i+1;
                    l = i;
                }
        return s.substring(l,l+max);
    }
    public boolean isPalindrome(String s, int i, int j){
        for(;i<=j;i++,j--)
            if(s.charAt(i)!=s.charAt(j)) return false;
        return true;
    }
    
    
    
    
    //APPROACH 2 Extend => 1 loops, extend(i,i) and extend(i,i+1) find whichever is longer as x , update maxstr if x is longer than that
    //                          extend(i,j) while chars equal and bound intact, move i to left ,j to right and udpate maxstr
 
    // n*n extending both odd and even
    public String longestPalindrome2(String s) {
        String maxStr="";
        for(int i=0;i<s.length();i++){
            String s1 = extend(s,i,i);
            String s2 = extend(s,i,i+1);
            String x = s1.length()>s2.length()?s1:s2;
            if(x.length()>maxStr.length()) maxStr=x;
        }
        return maxStr;
    }
    public String extend(String s, int i, int j) {
        String maxStr="";
        for(;i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j);i--,j++)
            maxStr = s.substring(i,j+1);
        return maxStr;
    }
    //APPROACH 2.2 Extend + max[] to avoidsubstring calls => 1 loops, extend(i,i) and extend(i,i+1) find whichever is longer as x , update max[] if x is longer than that
    //                          extend(i,j) while chars equal and bound intact, move i to left ,j to right and udpate max[] 
    //                        
    public String longestPalindrome3(String s) {
        int[] max = new int[2];
        for(int i=0;i<s.length();i++){
            int[] m1 = extend2(s,i,i);
            int[] m2 = extend2(s,i,i+1);
            int[] x = get(m1)>get(m2)?m1:m2;
            if(get(x)>get(max)) max=x;
        }
        return s.substring(max[0],max[1]);
    }
    public int get(int[] max){
        return max[1]-max[0];
    }
    public int[] extend(String s, int i, int j) {
        int[] max = new int[2];
        for(;i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j);i--,j++)
           max=new int[]{i,j+1};
        return max;
    }
    
    
    //APPROACH 3 => 2d DP boolean[n][n] => iterate right diagonals and move to top right dir as right diag elements reduce
    //                   j,i+j -> traverse right diagonals for(i=0,n) for(j=0,n-i) 
    //                  dp[j][i+j] = s[i]==s[i+j](same chars if i is 0) && (i<2||dp[j+1][i+j-1]) means true for checking same char and adj chars, then depend on dp
    //                  if(dp[j][i+j]  and i>end-start) update stant and end, i is the max as i inc, j,i+j has a diff of i, chars at these pos are compared
    //
    /*  babab
    //  i==0  matching        b a b a b
    //  i=1  matching        ba ab ba ab etc
    //  i=2  matching         bb aa bb etc
    0 1 2 3 4
  0 1 0 1 0 1
  1   1 0 1 0
  2     1 0 1
  3       1 0
  4         1
    */
    // dp[j][i+j] traverses through the diagonals only, [1,1,1,1,1], then [0,0,0,0], [1,1,1] etc
    // dp[j][j] always traverses right diag, so, dp[j][i+j] moves diagonal by i times, so j<n=i,as diag elements reduce as we move right
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
    //APPROACH 3.2 => 2d DP boolean[n][n] => iterate right diagonals and move to top right dir as right diag elements reduce
    //                   j,i+j -> traverse right diagonals for(i=0,n) for(j=0,n-i) 
    //                  dp[j][i+j] = s[i]==s[i+j](same chars if i is 0) && (i<2||dp[j+1][i+j-1]) means true for checking same char and adj chars, then depend on dp
    //                  if(dp[j][i+j]  and i>end-start) update stant and end, i is the max as i inc, j,i+j has a diff of i, chars at these pos are compared
    //                  dp[j][i+j] can access only dp[j+1][i+j-1], its prev col, so making col size 2 and swapping col everytime saves space
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
    
    
    //APPROACH 4 - Manchers alogirthm + char[2*len+3] ch, abc => @#a#b#c#$, 4 vars=>start,maxlen, maxright,center + c[] for storing max extentions for i,
    //                     for(i=1,ch.len) -> if(i<maxr) p[i] = min(maxr-i, p[2*cen-i]) as maxr-i is max p[i] so far and p[2*cen-i] is the mirror character
    //                     while(ch[i+p[i]+1] == ch[i-p[i]-1]) p[i]++ means, check if p[i] can extend both sides and increase its value i chars are equal
    //                      if(i+p[i]>maxr) update maxr=i+p[i] and center=i, p[i]>maxlen, update new max holder, maxlen=p[i], 
    //                      start = (i-p[i]-1)/2 means i-1/2 gives its pos, whereas i-p[i]-1 givess position of letter upto which char at i was able to extend
    // dont learn this algo 
    // manchers algorithm
    // getting value from mirror if its a char from palindrome
    // 0123456789  
    // @#b#a#b#a#d#$
    //     c im
    // 0123456789
    // @#b#a#b#a#d#$
    // @#c#b#b#d#$
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
            while(ch[i+p[i]+1] == ch[i-p[i]-1]) p[i]++;
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
    
    
    
    
    
    public String longestPalindromeTest(String s) {
        int start=0,end=0, n=s.length();
        boolean[] dp =new boolean[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                dp[j] = s.charAt(i)==s.charAt(j) && (i-j<2 || dp[j+1]);
                if(dp[j]&&i-j>end-start){
                    start= j;
                    end = i;
                }
            }
        }
        return s.substring(start,end+1);
    }
    
}

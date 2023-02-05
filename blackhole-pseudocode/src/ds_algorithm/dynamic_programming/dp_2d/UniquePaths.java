/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.dynamic_programming.dp_2d;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/unique-paths/

public class UniquePaths {
    
    //APPROACH 1 DFS + reverse => forwars is trivial we nee i,j, so doing it rev, thats y m==1&&n==1 ret true, as we are starting m,n, m=n=0 are like out of bounds
    
    public int uniquePaths(int m, int n) {
        if(m==0||n==0) return 0;
        if(m==1&&n==1)return 1;
        return uniquePaths(m-1,n) + uniquePaths(m,n-1);   
    }
    //APPROACH 1.2 DFS + reverse + Integer[m+1][n+1] DP => staring from m-1, n-1 in rev, thats y m==0&&n==0 ret true, as we are starting m,n, m=n=-1 are like out of bounds
    
    public int uniquePaths2(int m, int n) {
        return  uniquePaths(m-1,n-1,new Integer[m+1][n+1]);
    }
    public int uniquePaths(int m, int n, Integer[][] dp) {
        if(m==-1||n==-1) return 0;
        if(m==0&n==0)return 1;
        if(dp[m][n]!=null) return dp[m][n];
        return dp[m][n] =  uniquePaths(m-1,n,dp) + uniquePaths(m,n-1,dp);   
    }
    
    
    //APPROACH 2 Ite + 2D int[m][n] -> dp[0][0] =1, so skip processing 0,0, for(i=0,m) for(j=0,n) skip 0,0 
    //                          ,get dp[i-1][j] i could be 0, s check and get, same for j, add both return dp[m-1][n-1] 
    
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0]=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0&&j==0) continue;
                int x = i==0 ? 0 : dp[i-1][j];
                int y = j==0 ? 0 : dp[i][j-1];
                dp[i][j] = x+y;
            }
        }
        return dp[m-1][n-1];
    }
    
    //APPROACH Ite + 1D int[n] , while getting dp[j-1] we have to check if j is 0 , dp[j] = dp[j]+ dp[j-1];
    
    public int uniquePaths3(int m, int n) {
        int[] dp = new int[n];
        dp[0]=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int y = j==0 ? 0 : dp[j-1];
                dp[j] = dp[j]+y;
            }
        }
        return dp[n-1];
    }
    
    //APPROACH
    /*
    formula for below
        (m+n-2)!/(m-1)!(n-1)!
        m=5,n=4
        m+n=9
        ans = 7!/4!3! => 7654321/4321*321 => 765/321
        m<n and m>n possible
        
    */
    
    // m=5,n=4
    // 7654321/4321*321 => 567/123
    // m=4,n=5
    // 7654321/321*4321 => 4567/1234
    // iterate n times
    public int uniquePaths6(int m, int n) {
        long ans=1;
        for(int i=m,j=1;j<n;i++,j++){
            ans = (ans*i)/(j);
        }
        return (int)ans;
    }
    // using min to optimize
    public int uniquePaths7(int m, int n) {
        long ans=1;
        for(int i=1;i<Math.min(m, n);i++)
            ans = (ans*(m+n-1-i))/(i);
        return (int)ans;
    }
}

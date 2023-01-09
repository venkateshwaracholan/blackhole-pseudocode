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
    
    //
    public int uniquePaths(int m, int n) {
        return uniquePaths(m,n,0,0);
    }

    public int uniquePaths(int m, int n, int i, int j) {
        if(i==m-1&&j==n-1) return 1;
        if(i==m||j==n) return 0;
        return uniquePaths(m,n,i+1,j) + uniquePaths(m,n,i,j+1); 
    }
    
    
    //
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        return uniquePaths2(m,n,0,0, dp);
    }

    public int uniquePaths2(int m, int n, int i, int j, int[][] dp) {
        if(i==m-1&&j==n-1) return 1;
        if(i==m||j==n) return 0;
        if(dp[i][j]!=0) return dp[i][j];
        return dp[i][j] = uniquePaths2(m,n,i+1,j,dp) + uniquePaths2(m,n,i,j+1,dp); 
    }
    
    //
    public int uniquePaths3(int m, int n) {
        return uniquePaths(m-1,n-1,new int[m][n]);
    }

    public int uniquePaths3(int m, int n, int[][] dp) {
        if(m==0&&n==0) return 1;
        if(m==-1||n==-1) return 0;
        if(dp[m][n]!=0) return dp[m][n];
        return dp[m][n] = uniquePaths3(m-1,n,dp) + uniquePaths3(m,n-1,dp); 
    }
    
    
    //
    public int uniquePaths4(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        dp[1][1]=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0&&j==0)continue;
                dp[i+1][j+1] = dp[i+1][j]+dp[i][j+1];
            }
        }
        return dp[m][n];
    }
    
    //
    public int uniquePaths5(int m, int n) {
        int[] dp = new int[n+1];
        dp[1]=1;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                dp[j+1] = dp[j]+dp[j+1];
        return dp[n];
    }
    
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

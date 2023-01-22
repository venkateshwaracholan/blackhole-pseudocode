/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.arrays_2d;

/**
 *
 * @author venka
 */

import java.util.*;


// https://leetcode.com/problems/spiral-matrix/

public class SpiralMatrix {
    
    //APPROACH 1 top=0,left=0,bottom=m-1,right=n-1 + ans.size()<m*n + m*n checks for all loops + <= >= for the 4s => 
    //                  //  left->right, top++(coz 1st row comp), top->bot, right--, right->letf, bottom--, bottom->top left++;
    
    // TimeO(mn) spae: O(1)
    // we are using ans size and m*n to limit the loop
    // make sure we limit the loop everywhere, in all 4 fors too
    // first move left to right while adding to ans, top the lower bound for i ,j=i, tats y matrix[top][i], inc top after loop
    // move from top to bottom, right is j, i moves from top to bottom matrix[i][right], dec right
    // move from right to left, matrix[bottom][i] dec bottom
    // move from bottom to top, matrix[i][left] inc left
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = n-1, top = 0, bottom = m-1;
        List<Integer> ans = new ArrayList();
        while(ans.size()<m*n){
            for(int i=left;i<=right&&ans.size()<m*n;i++) ans.add(matrix[top][i]);
            top++;
            for(int i=top;i<=bottom&&ans.size()<m*n;i++) ans.add(matrix[i][right]);
            right--;
            for(int i=right;i>=left&&ans.size()<m*n;i--) ans.add(matrix[bottom][i]);
            bottom--;
            for(int i=bottom;i>=top&&ans.size()<m*n;i--) ans.add(matrix[i][left]);
            left++;
        } 
        return ans;
    }
    
    //APPROACH 1.2 top=0,left=0,bottom=m-1,right=n-1 + while top<=bottom && left<=right + <= >= for the 4s + top>bottom || left>right for end in middle=> 
    //                  //  left->right, top++(coz 1st row comp), top->bot, right--, right->letf, bottom--, bottom->top left++;
    
    // same as above, we changed the bounds of outer loop
    // run loop while top<=bottom and left<=right
    // after updating top and bottom, check if baounds still make sense or return
    public List<Integer> spiralOrder3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = n-1, top = 0, bottom = m-1;
        List<Integer> ans = new ArrayList();
        while(top<=bottom && left<=right){
            for(int i=left;i<=right;i++) ans.add(matrix[top][i]);
            top++;
            for(int i=top;i<=bottom;i++) ans.add(matrix[i][right]);
            right--;
            if(top>bottom || left>right) return ans;
            for(int i=right;i>=left;i--) ans.add(matrix[bottom][i]);
            bottom--;
            for(int i=bottom;i>=top;i--) ans.add(matrix[i][left]);
            left++;
        } 
        return ans;
    }
    
    
    
    //APPROACH 2 DFS4 + boolean[][] dp +  boolean up + List<Integer> ans => if i,j out of bounds or vis return ans
    //                          ans.add(matrix[i][j]),  dp[i][j]=true mark vis, for up, we have to just call up again and again coz right of up is unvisited
    //                          Then call the DFS4, first right(j+1), bottom(i+1), left(j-1), then up(i-1)
    
    // dfs
    // using visisted matrix
    // make bounds clear, if x or y goes out of range or visited return
    // add to ans, and add to visited
    // first move right from x -> 0, y-> 0 to n 
    // then move bottom from x -> 0 to n, y-> n recursion takes care of these
    // then move left from x -> n, y-> n to 0
    // then move top from x -> n to 0, y-> n
    // but while moving top, it can go to right through recursion so, avoid that by using the up booealn
    // and call code that moves up again
    // everytime rec is called it goes out of range and then comes back,
    // right top to bottom everytime checks its right, due to 1st rec
    // similrly 3rd rec, call 2 rec in front  which simply hits bounds
    public List<Integer> spiralOrder2(int[][] matrix) {
        return dfs(matrix,0,0,new boolean[matrix.length][matrix[0].length], new ArrayList(),false);
    }
    public List<Integer> dfs(int[][] matrix, int i, int j, boolean[][] dp, List<Integer> ans, boolean up) {
        if(i==-1||i==matrix.length||j==-1||j==matrix[0].length||dp[i][j]) return ans;
        ans.add(matrix[i][j]);
        dp[i][j]=true;
        if(up) dfs(matrix,i-1,j,dp,ans,true);
        dfs(matrix,i,j+1,dp,ans,false);
        dfs(matrix,i+1,j,dp,ans,false);
        dfs(matrix,i,j-1,dp,ans,false);
        dfs(matrix,i-1,j,dp,ans,true);
        return ans;
    }
}

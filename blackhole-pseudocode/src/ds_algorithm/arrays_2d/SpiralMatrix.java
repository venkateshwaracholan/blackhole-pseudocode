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
        int m = matrix.length, n = matrix[0].length;
        List<Integer> ans = new ArrayList();
        Boolean visited[][] = new Boolean[m][n];
        dfs(matrix, 0, 0, ans, visited, false);
        return ans;
    }

    public void dfs(int[][] matrix, int x, int y, List<Integer> ans, Boolean[][] visited, boolean up){
        if(x<0|| x>=matrix.length||y<0 || y>=matrix[0].length||visited[x][y]!=null) return;
        ans.add(matrix[x][y]);
        visited[x][y]=true;
        if(up){
            dfs(matrix, x-1, y, ans, visited, true);
        }
        dfs(matrix, x, y+1, ans, visited, false);
        dfs(matrix, x+1, y, ans, visited, false);
        dfs(matrix, x, y-1, ans, visited, false);
        dfs(matrix, x-1, y, ans, visited, true);
    }
    
    
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
    
    // same as above
    // moving bounds inside for loops
    // for 3rd loop we have to check top<=bottom
    // for 4th loop we have to check left<=right
    //to avoid thios confusion use the above solutionm skip this
    public List<Integer> spiralOrder4(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = n-1, top = 0, bottom = m-1;
        List<Integer> ans = new ArrayList();
        while(top<=bottom && left<=right){
            for(int i=left;i<=right;i++) ans.add(matrix[top][i]);
            top++;
            for(int i=top;i<=bottom;i++) ans.add(matrix[i][right]);
            right--;
            for(int i=right;i>=left&&top<=bottom;i--) ans.add(matrix[bottom][i]);
            bottom--;
            for(int i=bottom;i>=top&&left<=right;i--) ans.add(matrix[i][left]);
            left++;
        } 
        return ans;
    }
    
    
}

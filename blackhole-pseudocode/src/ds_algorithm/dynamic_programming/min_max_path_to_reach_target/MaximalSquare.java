/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.min_max_path_to_reach_target;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/maximal-square

public class MaximalSquare {
  
//  Time O(mn) space (1)
//  Approach: Dp, memo
//  gets a little tricky becoz it is characters, but we can always subtract '1' with '0' (ascii)
//  to get numeric
//  core Idea: if current is 1 and all 3  - prev diag, top and left used to get minimum of them and add it to self so, only if all are 1 we will get 1, if all are 2 then it means all of them have their prevs as one and this accumulates.
// the min function work becoz of the ascii, acsii of '2' > '1'
// dont over write ascii with numbers we will end  up in cloud of confusion
// only convert them to number when we are comparing to max;
// the start bounds can be 1,1 but then we have to handle several cases separately
// so let the starts be 0,0 and only do all of these if i>0, j>0 and a[i][j] is 1
//  single val, single row and single cols are handled by keeping max comparison outside the if
  
  
  public int maximalSquare(char[][] matrix) {
    int max = 0;
    if(matrix.length==0 || matrix[0].length==0) return 0;
    int m = matrix.length, n = matrix[0].length;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            if(i>0 && j>0 && matrix[i][j]=='1'){
                //System.out.println(matrix[i][j]);
                matrix[i][j] += Math.min(matrix[i-1][j-1], Math.min(matrix[i][j-1],matrix[i-1][j])) - '0';  
            }
            max = Math.max(max, matrix[i][j]-'0');
        }
    }
    return max*max;
  }
  
//  Time: O(mn) space: O(mn)
//  approach: Dp, memo
//  create dp of size m+1,n+1
//  start populating from i+1, j+1 in dp table to avoid checks
//  if cur is 1 make use of min of prev diag, prev row and prev col +1
//  have a global max variable
  public int maximalSquare2DDP(char[][] matrix) {
        int m = matrix.length, n = m>0?matrix[0].length:0, max= 0;
        int dp[][] = new int[m+1][n+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]=='1'){
                    dp[i+1][j+1] = Math.min(dp[i][j],Math.min(dp[i+1][j], dp[i][j+1]))+1;
                    max = Math.max(max,dp[i+1][j+1]);
                }
            }
        }
        return max*max;
    }
  
  
  
//  Time: O(mn) space: O(n)
//  approach: dp, 1 memo  
//  at each time we only require cur row and prev row of the dp table as we traverse row wise
//  so only create n space an use prev and cur.
//  CAUTION: assign prev to cur outside the inner for loop
  public static int maximalSquare1DDP(char[][] matrix) {
        int m = matrix.length, n = m>0?matrix[0].length:0, max= 0;
        int prev[] = new int[n+1];
        for(int i=0;i<m;i++){
            int cur[] = new int[n+1];
            for(int j=0;j<n;j++){
                if(matrix[i][j]=='1'){
                    cur[j+1] = Math.min(prev[j],Math.min(cur[j], prev[j+1]))+1;
                    max = Math.max(max,cur[j+1]);
                }
            }
            prev = cur;
        }
        return max*max;
    }
  
  public static void main(String args[]){
    
    maximalSquare1DDP(new char[][]{{'1','1'},{'1','1'}});
  }
  
}

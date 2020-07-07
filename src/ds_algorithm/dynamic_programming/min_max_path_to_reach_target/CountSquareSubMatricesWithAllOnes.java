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

// https://leetcode.com/problems/count-square-submatrices-with-all-ones/


public class CountSquareSubMatricesWithAllOnes {
  
// Time: O(mn) space: O(1)
// using the 3 previous elements to accumulate dp
// and add them to the ans  
  public int countSquares(int[][] matrix) {
      int m = matrix.length, n = m==0 ? 0 : matrix[0].length;
      int ans = 0;
      for(int i=0;i<m;i++){
          for(int j=0;j<n;j++){
              if(i>0 && j>0 && matrix[i][j]==1)
                  matrix[i][j] = Math.min(matrix[i-1][j-1], Math.min(matrix[i][j-1],matrix[i-1][j]))+1;
              ans+=matrix[i][j];
          }
      }
      return ans;
  }
}

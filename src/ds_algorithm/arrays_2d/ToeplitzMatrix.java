/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays_2d;

/**
 *
 * @author vshanmugham
 */
// https://leetcode.com/problems/toeplitz-matrix

public class ToeplitzMatrix {
  
  
//   Most basic way to solve the problem
//  Time: O(mn) space O(1)
  public boolean isToeplitzMatrix(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    for(int i=0;i<n;i++){
      int x = 0;
      int y = i; 
      for(int k=1;k<m;k++){
        if(x+k<m && y+k<n && matrix[x+k][y+k]!=matrix[x][y])
          return false;
      }
    }
    for(int i=1;i<m;i++){
      int x = i;
      int y = 0; 
      for(int k=1;k<n;k++){
        if(x+k<m && y+k<n && matrix[x+k][y+k]!=matrix[x][y])
          return false;
      }
    }
    return true;
  }
  
//  Time: O(mn) space O(1)
//  core idea: compare every element with its previous diagonal element
  
  public boolean isToeplitzMatrixBetter(int[][] matrix) {
      int m = matrix.length;
      int n = matrix[0].length;
      for(int i=1;i<m;i++){
          for(int j=1;j<n;j++){
              if(matrix[i][j]!=matrix[i-1][j-1])
                  return false;
          }
      }
      return true;
  }
  
// writter before above solution and has unnecessary checks and wrong bounds  
  public static boolean isToeplitzMatrixIntutive(int[][] matrix) {
      int m = matrix.length;
      int n = matrix[0].length;
      for(int i=0;i<m;i++){
          for(int j=0;j<n;j++){
              if(i-1>=0 && j-1>=0 && matrix[i][j]!=matrix[i-1][j-1])
                  return false;
          }
      }
      return true;
  }
}

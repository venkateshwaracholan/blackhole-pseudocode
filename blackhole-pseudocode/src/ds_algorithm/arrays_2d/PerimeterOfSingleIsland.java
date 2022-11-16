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

// https://leetcode.com/problems/island-perimeter

public class PerimeterOfSingleIsland {
  //  Time: O(mn) space: O(mn)
//  approach: DFS
//  return 1 if 4 border elem is out of border or 0
//  increment taht in count
//  when processing change 1 to 2 priorly before dfs to recompute already computed nodes
//  call dfs for all elements in the matrix
//  slower becoz island rows are recomputed with dfs again and again 
  public static int islandPerimeter(int[][] grid) {
      int m = grid.length, n = grid.length==0 ? 0 : grid[0].length;
      int max = 0;
      for(int i=0;i<m;i++){
          for(int j=0;j<n;j++){
              max = Math.max(max, dfs(grid, i, j, m, n)); 
          }
      }
      return max;
  }

  public static int dfs(int[][] grid, int i, int j, int m, int n){
      int count = 0;
      if(i<0 || i>=m || j<0 || j>=n || grid[i][j]== 0) return 1;
      if(grid[i][j]==1){
          grid[i][j] = 2;
          count+= dfs(grid, i-1, j, m, n);
          count+= dfs(grid, i+1, j, m, n);
          count+= dfs(grid, i, j-1, m, n);
          count+= dfs(grid, i, j+1, m, n);  
      }
      return count;
  }
 
//  Time: O(mn) space: O(1)
//  simple math calc
//  traverse and check 4 border elements if tehy are out or 0, increment
//  tahts all we need to do becoz it si graranteed tohave only one island
  public int islandPerimeterIte(int[][] grid) {
      int m = grid.length, n = grid.length==0 ? 0 : grid[0].length;
      int max = 0;
      int d[][] = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
      for(int i=0;i<m;i++){
          for(int j=0;j<n;j++){
             if(grid[i][j]==1){
                  for(int k=0;k<d.length;k++){
                     int r = i+d[k][0], c = j+d[k][1];
                     if(r<0 || r>=m || c<0 || c>=n || grid[r][c]==0)
                         max++;
                  }
              }
          }
      }
      return max;
  }
  
//  Time: O(mn) space: O(1)
//  simple math calc, same as ablove
//  different coding style
  public int islandPerimeterIteAlt(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int x = grid.length;
        int y = grid[0].length;

        int perimeter = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) perimeter++;
                    if (i == x - 1 || grid[i + 1][j] == 0) perimeter++;
                    if (j == 0 || grid[i][j - 1] == 0) perimeter++;
                    if (j == y - 1 || grid[i][j + 1] == 0) perimeter++;
                }
            }
        }

        return perimeter;
    }
}

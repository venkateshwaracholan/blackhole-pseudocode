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
import java.util.*;

// https://leetcode.com/problems/number-of-islands

public class NumberOfIslands {
  
//  Time: O(mn) space:(min(m,n))
//  code idea BFS,
//  for each entry in the matrix, we expaand as bfs to asjacent and add them to queue
//  until q becomes enpty keep expanding and thats one island
//  important thing is to make the lands to 0 so that that doesnt get counted as another island
//  
//  why space:(min(m,n)) is a marvel
//  worst case is full 1s and if we check the solution with space  
//  it can maximum hold number od right diagonal elements and
//  number of diagonal elements in mxn matrix in min(m,n) 
//  most space efficient solution
  public int numIslandsBFS(char[][] grid) {
    if(grid.length==0)
      return 0;
    int dir[][] = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
    int m = grid.length, n = grid[0].length, count = 0;
    Queue<Integer> q = new LinkedList();
    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){
        if(grid[i][j]=='1'){
          count++;
          q.add(i*n+j);
          while(!q.isEmpty()){
            int rc = q.poll();
            int r = rc/n, c = rc%n;  
            for(int k=0;k<dir.length;k++){
              int nr = r+dir[k][0], nc = c+dir[k][1];
              if(nr>=0 && nr< m && nc>=0 && nc<n && grid[nr][nc]=='1'){
                q.add(nr*n+nc);
                grid[nr][nc]=0;
              }
            }
          }
        }
      }
    }
    return count;
  }
  
  
  public void rec(char grid[][], int[][]dir, int r, int c, int m, int n){
    grid[r][c]='0';
    for(int k=0;k<dir.length;k++){
      int nr = r+dir[k][0], nc = c+dir[k][1];
      if(nr>=0 && nr< m && nc>=0 && nc<n && grid[nr][nc]=='1')
        rec(grid,dir,nr,nc,m,n);
    }
  }

//  Time(mn) space(mn)recursion space
//  core idea DFS, same approach as above, but traverse the depth first,
//  we can also write the same solution in a stack iterative DFS.
  public int numIslandsDFS(char[][] grid) {
    if(grid.length==0)
      return 0;
    int dir[][] = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
    int m = grid.length, n = grid[0].length, count = 0;
    for(int i=0;i<m;i++){
      for(int j=0;j<n;j++){
        if(grid[i][j]=='1'){
          count++;
          rec(grid, dir, i, j, m,n);
        }
      }
    }
    return count;
  }
}

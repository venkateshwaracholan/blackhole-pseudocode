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
//  until q becomes empty keep expanding and thats one island
//  important thing is to make the lands to 0 so that that doesnt get counted as another island
//  
//  why space:(min(m,n)) is a marvel
//  worst case is full 1s and if we check the solution with space  
//  it can maximum hold number of right diagonal(i am correct) elements and
//  number of diagonal elements in mxn matrix in min(m,n) 
//  most space efficient solution
  
  /*
     1111x
     111x1
     11x11   right diagonal is capped by min(m,n)
     1x111
  
  */
  
//  Note: we should have made 0 the lands when adding to queue in first place, 
//  but its handled when its treated as adjacent's adjacent, haha
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
          // grid[i][j] = 0, necessary, not necessary? check note
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
  
//  same as above, but using Queue<int[]>
  public int numIslandsBfsIntArray(char[][] grid) {
      int m = grid.length, n = m==0 ? 0 :grid[0].length;
      int dir[][] = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
      Queue<int[]> q = new LinkedList();
      int ans = 0;
      for(int i=0;i<m;i++){
          for(int j=0;j<n;j++){
              if(grid[i][j]=='1'){
                  ans++;
                  q.add(new int[]{i,j});
              }
              while(!q.isEmpty()){
                  int x[] = q.poll();
                  for(int k=0;k<dir.length;k++){
                      int r = x[0] + dir[k][0], c = x[1]+dir[k][1];
                      if(r>=0 && r<m&& c>=0 && c<n && grid[r][c]=='1'){
                          grid[r][c]=0;
                          q.add(new int[]{r,c});
                      }
                  }
              }
          } 
      }
      return ans;
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
//  Time(mn) space(mn)recursion space
//  same as above, but, in the recusrsion i am using exclusion instead of inclution in the above code
//  exclude with this i<0 || i>=m || j<0 || j>=n
//  and if any has '1' dfs in 4 directions
//  Note: chaotic case, i made it 0 and checked if its '1' in next line, avoid these mistakes
  
  public int numIslandsDfsAlt(char[][] grid) {
      int m = grid.length, n = m==0 ? 0 :grid[0].length;
      int ans = 0;
      for(int i=0;i<m;i++){
          for(int j=0;j<n;j++){
              if(grid[i][j]=='1'){
                  ans++;
                  dfs(i, j, m, n, grid);
              }
          }
      }
      return ans;
  }

  public void dfs(int i, int j, int m, int n, char[][] grid){
      if(i<0 || i>=m || j<0 || j>=n) return;
      //grid[i][j] = 0; i put this piece here once and it went chaotic
      if(grid[i][j]=='1'){
          grid[i][j] = 0;          dfs(i+1,j, m, n, grid);
          dfs(i-1,j, m, n, grid);
          dfs(i,j+1, m, n, grid);
          dfs(i,j-1, m, n, grid);
      }
  }
  
}

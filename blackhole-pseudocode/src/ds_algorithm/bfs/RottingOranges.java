/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.bfs;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/rotting-oranges/

// Amazon | OA 2019 | 🧟‍♀️ Zombie in Matrix is alos the same question
// https://leetcode.com/discuss/interview-question/411357/


import java.util.*;


public class RottingOranges {
  
//  Time: O(mn) space: O(1)
//  core idea: BFS - level order 2 loops, index compression, 
//  first add all rotten oranges in queue
//   the  do a bfs level order traversal using two loops
//  inside that get adjacent  index using dir array
//  if its in bound and not rotten,
//  rot the oranges and add them in queue
//  Note: pre rotting is necessary to avoid adding them again as adjacent from another rotten orange
//  increment count if q is not empty, this avoids a counting increment at last when everything is processed
//  finaly iterate again to see if any fresh oranges left so that we can return -1;
  public int orangesRotting(int[][] grid) {
      int m = grid.length, n = grid.length==0 ? 0 : grid[0].length;
      int dir[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
      Queue<Integer> q = new LinkedList();
      int count = 0;
      for(int i=0;i<m;i++)
          for(int j=0;j<n;j++)
              if(grid[i][j]==2) q.add(i*n+j);
      while(!q.isEmpty()){
          for(int siz = q.size();siz>0;siz--){
              int x = q.poll();int r = x/n, c = x%n;
              for(int k=0;k<dir.length;k++){
                  int rd = r + dir[k][0], cd = c + dir[k][1];
                  if(rd>=0 && rd<m && cd>=0 && cd<n && grid[rd][cd]==1){
                      grid[rd][cd] = 2;
                      q.add(rd*n+cd);
                  }
              }
          }
          if(!q.isEmpty())count++;
      }
      for(int i=0;i<m;i++)
          for(int j=0;j<n;j++)
              if(grid[i][j]==1)
                  count = -1;
      return count;
  }
  
  
//  Time: O(mn) space: O(1)
//  i also removed index compression 
//  core idea: BFS - level order 2 loops
//  we can count fresh oranges
//  if fresh==0 return 0;
//  use level order bfs like above, get adjacent index, check bound, prerot
//  also reduce freshcount
//  we dont need to iterate array again to find if fresh present, instead use the fresh count
//  if fresh is 0 then count-1(-1 to reduce last increment) and -1 otherwise
  
  public int orangesRottingfreshCount(int[][] grid) {
        int m = grid.length, n = grid.length==0 ? 0 : grid[0].length;
        int dir[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        Queue<int[]> q = new LinkedList();
        int count = 0, fresh = 0;
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(grid[i][j]==2) q.add(new int[]{i,j});
                else if(grid[i][j]==1) fresh++; 
        if(fresh==0)return 0;
        while(!q.isEmpty()){
            for(int siz = q.size();siz>0;siz--){
                int x[] = q.poll();
                for(int k=0;k<dir.length;k++){
                    int rd = x[0] + dir[k][0], cd = x[1] + dir[k][1];
                    if(rd>=0 && rd<m && cd>=0 && cd<n && grid[rd][cd]==1){
                        grid[rd][cd] = 2;
                        fresh--;
                        q.add(new int[]{rd,cd});
                    }
                }
            }
            count++;
        }
        return fresh==0 ? count-1 : -1;
    }
  
  
//  Time: O(mn) space: O(mn)
//  core Idea: Bfs (level order not necessary), depth map, index compression
//  depth map wudnt work without index compression, or else we have to overrride equals of int[] instead we can create a custom class
//  using depth map we can compute the time
//  assign ans as the depth we got from processing the last fresh orange
  public int orangesRottingExtraSpace(int[][] grid) {
      int m = grid.length, n = grid.length==0 ? 0 : grid[0].length;
      int dir[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
      Queue<Integer> q = new LinkedList();
      Map<Integer, Integer> depth = new HashMap();
      int ans = 0;
      for(int i=0;i<m;i++)
          for(int j=0;j<n;j++)
              if(grid[i][j]==2){ 
                depth.put(i*n+j,0);
                q.add(i*n+j);
              }
      while(!q.isEmpty()){
          int x = q.poll();int r = x/n, c = x%n;
          for(int k=0;k<dir.length;k++){
              int rd = r + dir[k][0], cd = c + dir[k][1];
              if(rd>=0 && rd<m && cd>=0 && cd<n && grid[rd][cd]==1){
                  grid[rd][cd] = 2;
                  depth.put(rd*n+cd,depth.get(r*n+c)+1);
                  ans = depth.get(rd*n+cd);
                  q.add(rd*n+cd);
              }
          }
      }
      for(int i=0;i<m;i++)
          for(int j=0;j<n;j++)
              if(grid[i][j]==1)
                  ans = -1;
      return ans;
  }
  
  
  public static void main(String args[]){
    // 
    Map<int[], Integer> map = new HashMap();
    int x[] = new int[]{1,1};
    map.put(x,100);
    System.out.println(map.get(x));
  }
}

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




//[
//1 1 1 1 1
//1 1 1 0 0
//1 0 0 0 1
//1 0 1 1 1
//1 1 0 1 1
//]

public class NumberOfIslands {
  
    //APPROACH 1 ITE + BFS Queue<Integer> rowcol + dir4 => r*c loop every row col->  g[i][j]=='1' c++ put rc in queue
    //              BFS do bfs -> for all 4 dirs , find nr,nc check if i,j in bound and g[i][j]=='1', put in quue and mark it '0'
    
    //  Time: O(mn) space:(min(m,n))
    //  code idea BFS,
    //  for each entry in the matrix, we expaand as bfs to asjacent and add them to queue
    //  until q becomes empty keep expanding and thats one island
    //  important thing is to make the lands to 0 so that that doesnt get counted as another island
    //  why space:(min(m,n)) is a marvel
    //  worst case is full 1s and if we check the solution with space  
    //  it can maximum hold number of right diagonal(i am correct) elements and
    //  number of diagonal elements in mxn matrix in min(m,n) 
    //  most space efficient solution
    //  Note: we should make grid[nr][nc]=0 while adding to q so that it avoid duplicates in the queue
    //  otherwise 2 paths will add common nodes again and again in queue
    public int numIslands(char[][] grid) {
        int c=0, m= grid.length,n=grid[0].length;
        int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        Queue<Integer> q = new LinkedList();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    c++;
                    q.add(i*n+j);
                }
                while(!q.isEmpty()){
                    int x = q.poll();
                    for(int k=0;k<dir.length;k++){
                        int nr = (x/n)+dir[k][0], nc = (x%n)+dir[k][1];
                        if(nr>=0&&nr<m&&nc>=0&&nc<n&&grid[nr][nc]=='1'){
                            grid[nr][nc]=0;
                            q.add(nr*n+nc);
                        }
                    }
                }
            }
        }
        return c;
    }
       /*
         1111x
         111x1
         11x11   right diagonal is capped by min(m,n)
         1x111
      */
    
    //APPROACH 1.2 ITE + BFS Queue<Integer> rowcol + dir4 => r*c loop every row col->  g[i][j]=='1' c++ put rc in queue
    //              BFS do bfs -> check if i,j not in bound and g[i][j]=='0' continue, mark it '0',  for all 4 dirs , find nr,nc , add in queue
    
    //  same as above, but using Queue<int[]> also modified the place whre we check and update
    public int numIslands2(char[][] grid) {
        int c=0, m= grid.length,n=grid[0].length;
        int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        Queue<int[]> q = new LinkedList();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    c++;
                    q.add(new int[]{i,j});
                }
                while(!q.isEmpty()){
                    int x[] = q.poll();
                    int nr = x[0], nc= x[1];
                    if(nr<0||nr==m||nc<0||nc==n||grid[nr][nc]!='1') continue;
                    grid[nr][nc]=0;
                    for(int k=0;k<dir.length;k++)
                        q.add(new int[]{nr+dir[k][0],nc+dir[k][1]});
                }
            }
        }
        return c;
    }
  
    //APPROACH 2 ITE + DFS +dir => r*c loop every row col-> if g[i][j]=='1' c++, call dfs and make this island 0
    //              DFS dfs(grid,i,j,dir)-> i,j not in bound or g[i][j]=='0'(visited) ret false, c=b[i][j], g[i][j]='0'(mark vis)
    //              go all  4 dirs
    
  
    //APPROACH
    //  Time(mn) space(mn)recursion space
    //  core idea DFS, same approach as above, but traverse the depth first,
    //  we can also write the same solution in a stack iterative DFS.
    public int numIslands3(char[][] grid) {
        int c=0;
        int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1') c++;
                numIslands3(grid,i,j, dir);
            }
        }
        return c;
    }
    public void numIslands3(char[][] grid, int i, int j, int[][] dir) {
        if(i==-1||i==grid.length||j==-1||j==grid[0].length||grid[i][j]=='0') return;
        grid[i][j] = 0;
        for(int k=0;k<dir.length;k++)
            numIslands3(grid,i+dir[k][0],j+dir[k][1],dir);
    }
   
    //APPROACH 2.2 ITE + DFS4 => r*c loop every row col-> if g[i][j]=='1' c++, call dfs and make this island 0
    //              DFS dfs(grid,i,j,dir)-> i,j not in bound or g[i][j]=='0'(visited) ret false, c=b[i][j], g[i][j]='0'(mark vis)
    //              go all  4 dirs rec(i-1,j)||rec(i+1,j)||rec(i,j-1)||rec(i,j+1)
    
  
    //  Time(mn) space(mn)recursion space
    //  same as above, but, in the recusrsion i am using exclusion instead of inclution in the above code
    //  exclude with this i<0 || i>=m || j<0 || j>=n
    //  and if any has '1' dfs in 4 directions
    //  Note: chaotic case, i made it 0 and checked if its '1' in next line, avoid these mistakes
  
    public int numIslands4(char[][] grid) {
        int c=0;
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++)
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    c++;
                }
        return c;
    }
    public void dfs(char[][] grid, int i, int j) {
        if(i==-1||i==grid.length||j==-1||j==grid[0].length||grid[i][j]=='0') return;
        grid[i][j]='0';
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }
  
}

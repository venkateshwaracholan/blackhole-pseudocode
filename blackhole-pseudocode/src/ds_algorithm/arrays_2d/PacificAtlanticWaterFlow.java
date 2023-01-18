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

// https://leetcode.com/problems/pacific-atlantic-water-flow/description/

public class PacificAtlanticWaterFlow {
    
    //
    //    Complexity (Approach 1)
    //Time Complexity: O(mn^2)
    //Space Complexity: O(mn^2)
    // IN APPROACH 1 WE HAVE TO CHECK IF BFS CAN COVER BOTH atl and pac using flags[]
    // https://leetcode.com/problems/pacific-atlantic-water-flow/solutions/1885773/java-2-different-approaches-brute-force-optimal-beats-99-66-dfs/
    // interesting to thinks but its a worst time and space
    
    
    // APPROACH 2
    //    Time Complexity: O(mn)
    //    Space Complexity: O(mn) for two visited arrays for each Ocean.
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length,n =heights[0].length;
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        for(int i=0;i<m;i++){
            pacificAtlantic(heights,i,0,heights[i][0],pac);
            pacificAtlantic(heights,i,n-1,heights[i][n-1],atl);
        }
        for(int j=0;j<n;j++){
            pacificAtlantic(heights,0,j,heights[0][j],pac);
            pacificAtlantic(heights,m-1,j,heights[m-1][j],atl);
        }
        List<List<Integer>> ans = new ArrayList();
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(pac[i][j]&&atl[i][j]) ans.add(Arrays.asList(i,j));
        return ans;
    }

    public void pacificAtlantic(int[][] heights, int i, int j, int prev, boolean[][] dp) {
        if(i==-1||i==heights.length||j==-1||j==heights[0].length||dp[i][j]||heights[i][j]<prev) return;
        dp[i][j] = true;
        pacificAtlantic(heights,i-1,j,heights[i][j],dp);
        pacificAtlantic(heights,i+1,j,heights[i][j],dp);
        pacificAtlantic(heights,i,j-1,heights[i][j],dp);
        pacificAtlantic(heights,i,j+1,heights[i][j],dp);
    }

    
    
    //BFS
    
    public List<List<Integer>> pacificAtlantic3(int[][] heights) {
        int m = heights.length,n =heights[0].length;
        int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        Queue<int[]> pq = new LinkedList();
        Queue<int[]> aq = new LinkedList();
        for(int i=0;i<m;i++){
            pq.add(new int[]{i,0});
            aq.add(new int[]{i,n-1});
        }
        for(int j=0;j<n;j++){
            pq.add(new int[]{0,j});
            aq.add(new int[]{m-1,j});
        }
        boolean[][] pac = bfs(heights,m,n,pq,new boolean[m][n],dir);
        boolean[][] atl = bfs(heights,m,n,aq,new boolean[m][n],dir);
        List<List<Integer>> ans = new ArrayList();
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                if(pac[i][j]&&atl[i][j]) ans.add(Arrays.asList(i,j));
        return ans;
    }

    public boolean[][] bfs(int[][] heights, int m, int n,Queue<int[]> q, boolean[][] dp, int[][] dir) {
        while(!q.isEmpty()){
            int[] x = q.poll();
            int i=x[0],j=x[1];
            dp[i][j]=true;
            for(int k=0;k<dir.length;k++){
                int nr=i+dir[k][0], nc=j+dir[k][1];
                if(nr>=0&&nr<m&&nc>=0&&nc<n&&!dp[nr][nc]&&heights[nr][nc]>=heights[i][j]){
                    q.add(new int[]{nr,nc});
                    dp[nr][nc]=true;
                }
            }
        }
        return dp;
    }
    
    public boolean[][] bfs2(int[][] heights, int m, int n,Queue<int[]> q, boolean[][] dp, int[][] dir) {
        while(!q.isEmpty()){
            int[] x = q.poll();
            dp[x[0]][x[1]]=true;
            for(int k=0;k<dir.length;k++){
                int nr=x[0]+dir[k][0], nc=x[1]+dir[k][1];
                if(nr==-1||nr==m||nc==-1||nc==n||dp[nr][nc]||heights[nr][nc]<heights[x[0]][x[1]]) continue;
                q.add(new int[]{nr,nc});
                dp[nr][nc]=true;
            }
        }
        return dp;
    }
}

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

// https://leetcode.com/problems/flood-fill

import java.util.*;

public class Floodfill {
  
//  Time O(n) space O(n)
//  approach: bfs, rowcol number
//  Note: if new color and old color are same dont proceed
//  change color when node is processed in queue to not match it again
//  store color in variable if neighbours match that color add them to queue 
  
  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
      if(image.length==0) return image;
      int m = image.length, n = image.length==0? 0 : image[0].length;
      int dir[][] = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
      int color = image[sr][sc];
      Queue<Integer> q = new LinkedList();
      q.add(n*sr + sc);
      while(color!= newColor && !q.isEmpty()){
          int rc = q.poll();
          int r = rc/n, c = rc%n;
          image[r][c] = newColor;
          for(int k=0;k<dir.length;k++){
              int nr = r+dir[k][0], nc = c+ dir[k][1];
              if(nr>=0 && nr<m && nc>=0 && nc< n && image[nr][nc]==color)
                  q.add(n*nr + nc);
          }
      }
      return image;
  }
  
//  Time O(n) space O(n)
//  approach: recursive dfs, rowcol number
//  Note: if new color and old color are same dont proceed
//  process: recolor and pass neighbours if color matches
//  dfs to pass neighbour node, and process them if their color matches
   
  public int[][] floodFillDfs(int[][] image, int sr, int sc, int newColor) {
      int color = image[sr][sc];
      if(color!=newColor) dfs(image, sr,sc, color,newColor);
      return image;
  }

  public void dfs(int[][] image, int sr, int sc, int color, int newColor){
      if(image[sr][sc]==color){
          image[sr][sc] = newColor;
          if(sr>0) dfs(image,sr-1,sc,color,newColor);
          if(sc>0) dfs(image,sr,sc-1,color,newColor);
          if(sr<image.length-1) dfs(image,sr+1,sc,color,newColor);
          if(sc<image[0].length-1) dfs(image,sr,sc+1,color,newColor);
      }
  }
}

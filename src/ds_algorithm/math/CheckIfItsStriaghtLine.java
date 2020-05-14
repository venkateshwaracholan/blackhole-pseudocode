/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.math;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/check-if-it-is-a-straight-line

public class CheckIfItsStriaghtLine {
  
//  Time O(n) sapce O(1)
//  calculate scope inside iteration
//  return true if less we have upto 2 points
//  assign if not assigned, else check and return if not equal
//  find dy,dx and if wither are zero, slope is zero(to avoid division by zero)
//  make sure u use float fro everything to capture precision
  public static boolean checkStraightLine(int[][] coordinates) {
      if(coordinates.length<3) return true;
      float slope = -1,ns=-1;
      for(int i=1;i<coordinates.length;i++){
          float dy = coordinates[i][1] - coordinates[i-1][1], dx = coordinates[i][0] - coordinates[i-1][0];
          if(dx==0 || dy==0) ns=0;
          else ns = dy/dx;
          if(slope==-1) slope = ns;
          else if(slope!=ns) return false;    
      }
      return true;
  }
  
//  Time O(n) sapce O(1)
//  instead of dividing we cross mutiply and check
//  dy0/dx0 = dyi/dxi threrfore dy0*dxi = dyi*dx0
//  since we return true if less we have upto 2 points
//  we can acces first 2 values to compute slope coz coordinates will have atleast 3
  public static boolean checkStraightLineInt(int[][] cr) {
      if(cr.length<3) return true;
      int dy0 = cr[1][1] - cr[0][1], dx0 = cr[1][0] - cr[0][0];
      for(int i=2;i<cr.length;i++){
          int dyi = cr[i][1] - cr[i-1][1], dxi = cr[i][0] - cr[i-1][0];
          if(dy0*dxi != dx0*dyi) return false;
      }
      return true;
  }
  
//  Time O(n) sapce O(1)
//  since we return true if less we have upto 2 points
//  we can acces first 2 values to compute slope coz coordinates will have atleast 3
//  slope remains same even if any point is compared with first point
  public static boolean checkStraightLineCompareWithFirst(int[][] cr) {
    if(cr.length<3) return true;
    int y0 = cr[0][1], y1 = cr[1][1], x0 = cr[0][0], x1 = cr[1][0];
    for(int i=2;i<cr.length;i++){
        int yi = cr[i][1], xi = cr[i][0];
        if((y1-y0)*(xi-x0) != (x1-x0)*(yi-y0)) return false;
    }
    return true;
  }
  
  public static void main(String args[]){
    checkStraightLineInt(new int[][]{{-4,-3},{1,0},{3,-1},{0,-1},{-5,2}});
  }
}

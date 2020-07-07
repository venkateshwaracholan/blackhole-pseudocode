/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/interval-list-intersections/

import java.util.*;

public class IntervalListIntersections {
  
//  Time: O(m+n) sapce: O(m+n)
// core idea, use two pointers, find lo and hi
//  lo is max of left points and hi is min of right points
//  if lo<=hi then add it yo ans
//  ans has to be array list and not a plain array because, we dono the size upfront until we compute
//  so there will be trailing [0,0] if size if short of m+n
//  so use array list adn tehn convert it to array
//  keep the interval with largest ending point and move the other one forward
  public int[][] intervalIntersection(int[][] A, int[][] B) {
      int i=0,j=0,k=0, m = A.length, n = B.length; 
      List<int[]> ans = new ArrayList();
      while(i<A.length && j<B.length){
          int lo = Math.max(A[i][0], B[j][0]);
          int hi = Math.min(A[i][1], B[j][1]);
          if(lo<=hi)
              ans.add(new int[]{lo,hi});
          if(A[i][1]<B[j][1]) i++;
          else j++;
      }
      return ans.toArray(new int[ans.size()][]);
  }
  
//  Time: O(m+n) sapce: O(m+n)
//  core idea, use two pointers, find 4 cases r may be 5
//  for 2 non over lapping cases just move pointers
//  and the only compare right pointers becoz we depend on that to decide which one to move froward
//  (comparing left pointer soultion wudnt wrk for this reason only)
//  and add iterval accordingly
  public int[][] intervalIntersectionAlt(int[][] A, int[][] B) {
    int i=0,j=0,k=0, m = A.length, n = B.length; 
    List<int[]> ans = new ArrayList();
    while(i<A.length && j<B.length){
      if(A[i][1]<B[j][0])
        i++;
      else if(B[j][1]<A[i][0])
        j++;
      else if(A[i][1]<B[j][1]){
          ans.add(new int[]{Math.max(A[i][0], B[j][0]), A[i][1]});
          i++;
      }
      else{
          ans.add(new int[]{Math.max(A[i][0], B[j][0]), B[j][1]});
          j++;
      }
    }
    return ans.toArray(new int[ans.size()][]);
    }
}

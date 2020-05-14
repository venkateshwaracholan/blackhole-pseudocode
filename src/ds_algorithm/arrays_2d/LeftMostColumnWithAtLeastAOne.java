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
// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/530/week-3/3306/
class BinaryMatrix {
      public int get(int row, int col) {
        return 0;
      }
      public List<Integer> dimensions() {
        return new ArrayList();
      }
}


public class LeftMostColumnWithAtLeastAOne {
  
// Time: O(m+n) space: O(1)
// Core idea: since it is sorter row wise as well as column wise(based on number of 1s)
// if we encounter 0 then move down, if we encounter 1 move left and update min
  public int leftMostColumnWithOneEfficient(BinaryMatrix binaryMatrix) {
    int m = binaryMatrix.dimensions().get(0), n = binaryMatrix.dimensions().get(1);
    int i=0, j=n-1, min = -1;
    while(i<m && j>=0){
        if(binaryMatrix.get(i,j)==0)
            i++;
        else{
            min = j;
            j--;
        }
    }
    return min;
  }
  
//  Time: (mlogn) space O(1)
//  
  public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
    int m = binaryMatrix.dimensions().get(0), n = binaryMatrix.dimensions().get(1);
    System.out.println(m+" "+n);
    int min = Integer.MAX_VALUE;
    for(int i=0;i<m;i++)
        min = Math.min(min,binSearch(binaryMatrix,i,0,n-1));
    return min==Integer.MAX_VALUE ? -1 : min;
  }

  public int binSearch(BinaryMatrix binaryMatrix, int i , int l, int r){
    while(l<r){
        int mid = l + (r-l)/2;
        if(binaryMatrix.get(i, mid)==0) l=mid+1;
        else r = mid;
    }
    if(binaryMatrix.get(i, l)==1) return l;
    return Integer.MAX_VALUE;
  }
  
  
  
}

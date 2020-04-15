/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.divide_and_conquer;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/sqrtx/

public class SquareRoot {
  public static int mySqrt(int x) {
    if(x == 0)  return 0;  
    int l = 1;
    int r = x;
    int mid = (l+r)/2;
    while(l<=r){
      mid = (l+r)/2;
      if(mid==x/mid){
        return mid;
      }else if(mid>x/mid){
        r = mid-1;  
      }else{
        l = mid+1;   
      }

    }
    return r;
  }
  
  
}

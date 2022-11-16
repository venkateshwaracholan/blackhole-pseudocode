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


// https://leetcode.com/problems/valid-perfect-square

public class ValidPerfectSquare {
  
//  Time: O(logn) space O(1)
//  use long variables as squaring is troublesome
//  if sq alone is mad long, we need to explicity cast while  sq =(long)mid*mid;  other wise sq will hold negative value.
//  if num<2 return true, 0 and 1 are exceptions
//  bounds, start =2 , end = num/2 coz solution lies withing 2 to num/2
//  if sq matches num return true
//  if sq greater than num, move r left side, else love l right side
  
  
  public boolean isPerfectSquare(int num) {
        if(num<2) return true;
        long l = 2, r = num/2, mid, sq;
        while(l<=r){
            mid = l +(r-l)/2;
            sq =mid*mid; 
            if(sq==num) return true;
            if(sq>num) r = mid-1;
            else l= mid+1;
        }
        return false;
    }
}

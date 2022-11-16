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

// https://leetcode.com/problems/first-bad-version/
// # defective bulb

public class FirstBadVersion {
  
  boolean isBadVersion(int version){
    return false;
  }
  
//  Time: O(logn) space O(1)
//  core idea: binary search, binary binary search
//  weshould never try to locate it and return
//  instead make l point to firt bad version and return l
//  why l = mid+1 and r = mid(not mid-1)
//  there is a fair chance l and mid are equal, so r =mid-1 might make r and l equal and we cant get out of the loop if we put l<=r becoz if we put l<= then we have to return somehw insed loop which is not possible now.
//  l = mid+1 make l move forward if mid is eual to l and avoid loop getting stuck
//  start with l=1 and r = n;
  public int firstBadVersion(int n) {
    int l = 1,r = n;
    while(l<r){
        int mid = l + (r - l) / 2;
        if(isBadVersion(mid)) r = mid;
        else l = mid+1;
    }
    return l;
  }
  
  
  public static void main(String args[]){
    System.out.println(1);
  }
}

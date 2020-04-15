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

// https://leetcode.com/problems/container-with-most-water/

public class ContainerWithMostWater {
  
  // Time: O(n) space: O(1)
  // core idea: start two pointers with both ends
  // its is of no use to move longer line inwards, so do the opposite
  // stor in max and return
  public int maxArea(int[] height) {
    int i=0, j=height.length-1;
    int max = 0;
    while(i<j){
      max = Math.max(max, area(height,i,j));
      if(height[i]<height[j])
        i++;
      else 
        j--;
    }
    return max;
  }

  public int area(int a[], int i, int j){
    return (j-i)*Math.min(a[i],a[j]);
  }
}

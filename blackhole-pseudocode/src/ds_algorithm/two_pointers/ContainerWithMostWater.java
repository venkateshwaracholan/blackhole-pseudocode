/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.two_pointers;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/container-with-most-water/

public class ContainerWithMostWater {
  
    // Time: O(n^2) space: O(1)
    // brute force
    // area => min of heights * j-i
    public int maxAreaBrute(int[] height) {
        int max = Integer.MIN_VALUE;
        for(int i=0;i<height.length-1;i++)
            for(int j=i+1;j<height.length;j++)
                max = Math.max(max, (j-i)*Math.min(height[i],height[j]));
        return max;
    }


    // approach: two pointers, max
    // Time: O(n) space: O(1)
    // core idea: start two pointers with both ends
    // its is of no use to move longer line inwards, so do the opposite
    // stor in max and return
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        for(int i=0,j=height.length-1;i<j;){
            max = Math.max(max, (j-i)*Math.min(height[i],height[j]));
            if(height[i]<height[j])i++;
            else j--;
        }
        return max;
    }
}

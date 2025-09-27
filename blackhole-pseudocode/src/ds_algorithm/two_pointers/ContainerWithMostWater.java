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
  
    /*
    * Approach 1: Brute-force (check all pairs)
    * - Iterate over all pairs of lines (i, j) in the array.
    * - Compute the area formed by height[i] and height[j]:
    *     - height = min(height[i], height[j])
    *     - width  = j - i
    *     - area   = height * width
    * - Keep track of the maximum area encountered.
    *
    * Time Complexity: O(n^2) → two nested loops over n elements
    * Space Complexity: O(1)  → only a single variable 'max' used
    *
    * Note: Simple and easy to implement but not efficient for large inputs.
    */
    public int maxAreaBrute(int[] height) {
        int max = 0;
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                int h = Math.min(height[i],height[j]);
                int w = j-i;
                max = Math.max(max, w*h);
            }
        }
        return max;
    }

    /*
    * Approach 2: Two-Pointer Optimized
    * - Initialize two pointers: i = 0 (left), j = height.length - 1 (right).
    * - At each step, compute the area formed by height[i] and height[j]:
    *     - height = min(height[i], height[j])
    *     - width  = j - i
    *     - area   = height * width
    * - Update max if the current area is larger.
    * - Move the pointer pointing to the smaller height inward:
    *     - If height[i] < height[j], increment i
    *     - Else, decrement j
    * - Repeat until i >= j.
    *
    * Rationale:
    * - Moving the smaller height can potentially increase area because width decreases otherwise.
    * - Skipping the taller line won't help increase area, so we always move the smaller one.
    *
    * Time Complexity: O(n) → single pass from both ends
    * Space Complexity: O(1) → only a few variables used
    *
    * This is the optimal solution for the "Container With Most Water" problem.
    */
    public int maxArea(int[] height) {
        int max = 0;
        for(int i=0,j=height.length-1;i<j;){
            int h = Math.min(height[i],height[j]);
            int l = j-i;
            max = Math.max(max, l*h);
            if(height[i]<height[j]){
                i++;
            }else{
                j--;
            }
        }
        return max;
    }
}

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

/*


*/

public class MaximumProductSubarray {
  public static void main(String args[]){
    System.out.println(maxProduct(new int[]{2,3,-2,4}));
    System.out.println(maxProduct(new int[]{4,-2,-2,2,3,-10}));
  }
//  public static int maxProduct(int[] nums) {
//    if(nums.length == 1)
//      return nums[0];
//    int min = 0;
//    int max = 0;
//    int totalMax = 0;
//
//    for(int num : nums){
//      int prevMin = min*num;
//      int prevMax = max*num;
//      min = Math.min(num, Math.min(prevMin, prevMax));
//      max = Math.max(num, Math.max(prevMin, prevMax));
//      totalMax = Math.max(totalMax, max);
//    }
//    return totalMax;
//  }
  
  // Time: O(n) space: O(1) 
  // Kadane
  // Core Idea: 
  // compute final min and max by multiplying it with number and comparing number itself.
  // min of min*num, max*, num
  // max of min*num, max*num, num
  // assign that in maximum.
  // array with 1 value is an exception
  
  // -2
  // -2,-3
  //  2,3,-2,4
  //  4,2,-2,2,3,-100 
  public static int maxProduct(int[] nums) {
        if(nums.length==1)
            return nums[0];
        int maximum = 0;
        int max = 0;
        int min = 0;
        for(int i=0;i<nums.length;i++){
            min = Math.min(nums[i], Math.min(min*nums[i], max*nums[i])); 
            max = Math.max(nums[i], Math.max(min*nums[i], max*nums[i])); 
            maximum = Math.max(max, maximum);
        }
        return maximum;
    }
  
}

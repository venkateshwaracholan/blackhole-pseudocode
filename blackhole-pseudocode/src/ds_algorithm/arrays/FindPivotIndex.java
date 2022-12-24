/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.arrays;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/find-pivot-index/description/
// https://leetcode.com/problems/find-the-middle-index-in-array/description/

public class FindPivotIndex {
    
    
    // Time O(n) space: O(1)
    // approach: sum, and acc sofar sum
    // using both to subtract and find the right index
    // sofar == sum - nums[i] - sofar => excludes sofar and cur value
    public int pivotIndex(int[] nums) {
        int sum = 0, sofar = 0;
        for(int n: nums) sum+=n;
        for(int i=0;i<nums.length;i++){
            if(sofar == sum-nums[i]-sofar) return i;
            sofar+=nums[i];
        }
        return -1;
    }
}

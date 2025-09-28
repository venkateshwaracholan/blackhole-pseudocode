/*
  TWO SUM
*/


package ds_algorithm.hash;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//https://leetcode.com/problems/two-sum/


public class TwoSum {

    /*
    * TWO SUM — Quick Reference:
    *
    * Brute Force Approach   : Check every pair of numbers(iterate i in (0, n), iterate j in (i+1, n) to avoid checking self) until one sums to target (O(n²) time, O(1) space).
    * HashMap Lookup         : Use a HashMap to store numbers and their positions, and check if complement (target - number) exists, add in map after checking.
    */

    
    /*
    * ONE LINER => Check every pair of numbers(iterate i in (0, n), iterate j in (i+1, n) to avoid checking self) until one sums to target (O(n²) time, O(1) space).
    *
    * Approach 1: Brute Force (Nested Loops)
    * - Outer loop picks nums[i].
    * - Inner loop starts at nums[i+1] (so we only check later elements, not repeat pairs).
    * - If nums[i] + nums[j] == target → return indices {i,j}.
    * - If no pair found → return {0,0} (though problem guarantees a solution).
    *
    * Time Complexity: O(n²) — double loop over nums.
    * Space Complexity: O(1) — no extra data structures.
    *
    * Rationale:
    * - Very straightforward, easy to reason about.
    * - Inefficient for large arrays, but works correctly for all inputs.
    *
    * Example:
    *   nums = [2,7,11,15], target = 9 → (2+7) = 9 → returns [0,1].
    */
    public int[] twoSumBrute(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target)
                return new int[]{i,j};
            }
        }
        return new int[]{0,0};
    }
    
    /*
    * ONE LINER => Use a HashMap to store numbers and their positions, and check if complement (target - number) exists, add in map after checking.
    *
    * Approach 2: HashMap Lookup
    * - Create a HashMap to store value → index as we iterate.
    * - For each nums[i]:
    *     - Compute complement c = target - nums[i].
    *     - If c exists in map → we found the pair → return {i, map.get(c)} 
    *       (map.get(c) gives the index of the previously stored complement number).
    *     - Else store nums[i] → i in map so it can be used as a complement for future numbers.
    * - If no solution found (the problem guarantees one), return {0,0}.
    *
    * Time Complexity: O(n) — one pass through nums, O(1) average lookup/insert.
    * Space Complexity: O(n) — storing up to all elements and their positions in map.
    *
    * Rationale:
    * - HashMap stores each value along with its position so complements can be found in constant time.
    * - Eliminates need for nested loops → optimal O(n) solution.
    * - Order matters: we store current value after checking so we don’t mistakenly match with itself.
    *
    * Example:
    *   nums = [2,7,11,15], target = 9
    *   Iteration:
    *     i=0 → nums[0]=2, c=7 → not in map, store {2:0}.
    *     i=1 → nums[1]=7, c=2 → found in map → return [1,0] (map.get(7) gives index 0).
    */
    public int[] twoSum(int[] nums, int target) {
        var map = new HashMap<Integer, Integer>();
        for(int i=0;i<nums.length;i++){
            int c = target-nums[i];
            if(map.containsKey(c)){
                return new int[]{i, map.get(c)};
            }
            map.put(nums[i],i);
        }
        return new int[]{0,0};
    }
    

}
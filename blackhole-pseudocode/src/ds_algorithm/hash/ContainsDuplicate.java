/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.hash;

/**
 *
 * @author venka
 */
import java.util.*;
import java.util.stream.IntStream;

//https://leetcode.com/problems/contains-duplicate/description/


public class ContainsDuplicate {

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Brute Force        : Compare each elem with every later elem(iterate i in (0, n), iterate j in (i+1, n)) to avoid checking self) to detect duplicates in O(n²).
    * HashSet Approach   : iterate i in (0, n), iterate j in (i+1, n), Use a HashSet to track seen elements(add after checking) and detect duplicates in O(n).
    * Sorting Approach   : Sort array and check adjacent elements for duplicates(iterate i in (1, n) to use i-1 withour error) in O(n log n).
    * Stream Approach    : Use Java Streams to compare distinct count with original length to detect duplicates in O(n) on average.
    */



    
    /*
    * ONE LINER => Compare each elem with every later elem(iterate i in (0, n), iterate j in (i+1, n)) to avoid checking self) to detect duplicates in O(n²).
    *
    * Approach: Brute Force Pair Comparison
    * - Compare every element with all elements that come after it.
    * - If any pair matches → return true.
    * - If no duplicates found after all comparisons → return false.
    *
    * Time Complexity: O(n²) — two nested loops over nums.
    * Space Complexity: O(1) — no extra space used.
    *
    * Rationale:
    * - Simple direct approach; checks all possible pairs.
    * - Inefficient for large arrays but easy to implement and reason about.
    *
    * Example:
    *   nums = [1,2,3,1] → returns true.
    *   nums = [1,2,3,4] → returns false.
    */
    public boolean containsDuplicateBrute(int[] nums) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]){
                     return true;
                }
            }
        }
        return false;
    }
    
    /*
    * ONE LINER => iterate i in (0, n), iterate j in (i+1, n), Use a HashSet to track seen elements(add after checking) and detect duplicates in O(n).
    *
    * Approach: HashSet Tracking
    * - Create an empty HashSet.
    * - Iterate through nums:
    *     - If element already exists in set → return true (duplicate found).
    *     - Else add element to set.
    * - If iteration completes without finding duplicates → return false.
    *
    * Time Complexity: O(n) — each element processed once, constant time HashSet operations.
    * Space Complexity: O(n) — up to n elements stored in the set.
    *
    * Rationale:
    * - Efficiently detects duplicates without comparing all pairs.
    * - HashSet lookup is O(1) on average, making this approach linear time.
    * - Uses extra space but trades it for speed.
    *
    * Example:
    *   nums = [1,2,3,1] → returns true.
    *   nums = [1,2,3,4] → returns false.
    */
    public boolean containsDuplicate(int[] nums) {
        var set = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
    
    
    /*
    * ONE LINER => Sort array and check adjacent elements for duplicates(iterate i in (1, n) to use i-1 withour error) in O(n log n).
    *
    * Approach: Sorting + Adjacent Check
    * - Sort the array.
    * - Iterate through the array starting from index 1 (ensuring nums[i-1] is valid):
    *     - Compare nums[i] with nums[i-1] to check for duplicates.
    *     - If nums[i] equals nums[i-1] → return true (duplicate found).
    * - If no adjacent duplicates → return false.
    *
    * Time Complexity: O(n log n) — dominated by the sort operation.
    * Space Complexity: O(1) — in-place sort (unless the language uses extra space for sorting).
    *
    * Rationale:
    * - Sorting brings duplicates next to each other, enabling an easy linear scan.
    * - Less extra space than HashSet approach but slower due to sorting.
    *
    * Example:
    *   nums = [3,1,4,2,3] → returns true (duplicates found after sorting).
    *   nums = [1,2,3,4]   → returns false.
 */
    public boolean containsDuplicateSort(int[] nums) {
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++)
            if(nums[i-1]==nums[i]) return true;
        return false;
    }
    
    /*
    * ONE LINER => Use Java streams to check duplicates by comparing distinct count with original length.
    *
    * Approach: Stream Distinct Count
    * - Convert nums array to an IntStream.
    * - Use distinct() to filter unique elements.
    * - Compare the count of distinct elements with original array length:
    *     - If counts differ → duplicates exist → return true.
    *     - Else → return false.
    *
    * Bounds Note:
    * - No index bounds needed as stream processes all elements internally.
    *
    * Time Complexity: O(n) on average — distinct() uses a set internally.
    * Space Complexity: O(n) — stores distinct elements internally in a set.
    *
    * Rationale:
    * - Very concise and readable using Java Streams.
    * - Trades manual iteration for expressive stream operations.
    *
    * Example:
    *   nums = [1,2,3,1] → distinct count = 3 vs length = 4 → returns true.
    *   nums = [1,2,3,4] → distinct count = length = 4 → returns false.
    */
    public boolean containsDuplicateStream(int[] nums) {
        return nums.length != IntStream.of(nums).distinct().count();
    }
    
    
    
}

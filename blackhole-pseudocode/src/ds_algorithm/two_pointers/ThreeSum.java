/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.two_pointers;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/3sum/description/

public class ThreeSum {

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Brute Force: Brute Force: Sort array, three nested loops i(0 to n-1), j(i+1 to n-1), k(j+1 to n-1), skip first element duplicates(optional as set covers), store triplets in HashSet → O(n³) time, O(n) space.
    * Complement Lookup: Sort array, for i(0 to n-1), for j(i+1 to n-1) compute complement=0-i-j in inner loop(like two sum), check HashSet for existence, if present store triplets in HashSet  → O(n²) time, O(n) space.
    * Two-Pointer + HashSet: Sort array, for every i, inner loop j=i+1,k=len-1, move j->right on sum<0, k->left sum>0, add sum=0 to HashSet → O(n²) time, O(n) space.  
    * Two-Pointer Inline Dupes skip + Arraylist: Sort array, for every i, skip i duplicates(must coz no set), inner loop j=i+1,k=len-1, move j->right on sum<0, k->left sum>0, on sum=0 add to arraylist, j/k duplicates inline with while and bound j<k, → O(n²) time, O(1) extra space.
    */

    
    /*
    * ONE LINER => Brute Force: Sort array, three nested loops i(0 to n-1), j(i+1 to n-1), k(j+1 to n-1), skip first element duplicates(optional as set covers), store triplets in HashSet → O(n³) time, O(n) space.
    *
    * Approach 1 (Brute-force with minor optimizations):
    * - Sort the array to simplify duplicate handling.
    * - Use three nested loops to check all triplets.
    * - Skip duplicate values for the first element to reduce unnecessary work.
    * - Store triplets in a HashSet to ensure uniqueness.
    *
    * Time: O(n^3) worst-case
    * Space: O(n) for storing unique triplets
    */
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        var set = new HashSet<List<Integer>>();
        Arrays.sort(nums);
        for(int i=0;i<len;i++){
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates(optional)
            for(int j=i+1;j<len;j++){
                for(int k=j+1;k<len;k++){
                    if(nums[i]+nums[j]+nums[k]==0){
                        set.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
    
    /*
    * ONE LINER => Complement Lookup: Sort array, for i(0 to n-1), for j(i+1 to n-1) compute complement=0-i-j in inner loop(like two sum), check HashSet for existence, if present store triplets in HashSet  → O(n²) time, O(n) space.
    *
    * Approach 2 (Brute-ish with HashSet for complement):
    * - Sort the array to make triplets predictable (optional for set uniqueness).
    * - Iterate over each pair (nums[i], nums[j]).
    * - Compute the required third element as target = 0 - nums[i] - nums[j].
    * - Use a HashSet to check if the target exists among previously seen numbers.
    * - Store triplets in a HashSet to avoid duplicates.
    *
    * Time: O(n^2) → two nested loops + O(1) set lookup
    * Space: O(n) → HashSet for complements + HashSet for unique triplets
    *
    * Note: This is an improvement over pure O(n^3) brute because we avoid the innermost loop.
    *       Still uses extra space for uniqueness but easy to understand in interviews.
    */
    public List<List<Integer>> threeSum2(int[] nums) {
        int len = nums.length;
        var res = new HashSet<List<Integer>>();
        var set = new HashSet<Integer>();
        Arrays.sort(nums);
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                int target = 0-nums[i]-nums[j];
                if(set.contains(target)){
                res.add(Arrays.asList(nums[i],nums[j],target));
                }
            }
            set.add(nums[i]);
        }
        return new ArrayList<>(res);
    }
    
    /*
    * ONE LINER => Two-Pointer + HashSet: Sort array, for every i, inner loop j=i+1,k=len-1, move j->right on sum<0, k->right sum>0, add sum=0 to HashSet → O(n²) time, O(n) space.
    *
    * Approach 3 (Two-Pointer 3Sum):
    * - Sort the array first to enable two-pointer traversal.
    * - Fix the first element nums[i], then use two pointers j=i+1 and k=len-1 for the remaining array.
    * - Compute sum = nums[i] + nums[j] + nums[k]:
    *     - If sum > 0 → decrement k to reduce sum.
    *     - If sum < 0 → increment j to increase sum.
    *     - If sum == 0 → add triplet to result set (to avoid duplicates), then move both pointers.
    * - Using a HashSet ensures only unique triplets are stored.
    * - i only goes up to len-2 because we need at least two elements (j and k) after i to form a triplet.
    *
    * Time:  O(n^2) → outer loop n times, inner while loop runs at most n times total per i.
    * Space: O(n) → HashSet for unique triplets + result ArrayList
    *
    * Note: Further optimization can skip duplicate nums[i] and duplicate nums[j]/nums[k]
    *       to remove the need for a HashSet.
    */
    public List<List<Integer>> threeSumTwoPointers(int[] nums) {
        int len = nums.length;
        var res = new HashSet<List<Integer>>();
        Arrays.sort(nums);
        for(int i=0;i<len-2;i++){
            for(int j=i+1,k=len-1;j<k;){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum>0){
                    k--;
                }else if(sum<0){
                    j++;
                }
                else{
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                }
            }
        }
        return new ArrayList<>(res);
    }
    
    
    /*
    * ONE LINER => Two-Pointer Inline Dupes skip + Arraylist: Sort array, for every i, skip i duplicates(must coz no set), inner loop j=i+1,k=len-1, move j->right on sum<0, k->left sum>0, on sum=0 add to arraylist, j/k duplicates inline with while and bound j<k, → O(n²) time, O(1) extra space.
    *
    * Approach: Two-Pointer 3Sum (skip duplicates inline)
    * - Sort the array first to enable two-pointer traversal.
    * - Iterate i from 0 to len-2:
    *     - len-2 ensures at least two elements remain for j and k to form a triplet.
    *     - Skip duplicate nums[i] to avoid repeated triplets.
    * - Use two pointers j = i+1 and k = len-1:
    *     - Compute sum = nums[i] + nums[j] + nums[k].
    *     - If sum < 0 → increment j to increase sum.
    *     - If sum > 0 → decrement k to reduce sum.
    *     - If sum == 0 → add triplet to result and skip duplicates:
    *         - Move j past all duplicates of nums[j].
    *         - Move k past all duplicates of nums[k].
    *         - Increment j and decrement k to continue search.
    * - No HashSet needed because duplicates are skipped inline.
    *
    * Time Complexity: O(n^2) → outer loop n times, inner loop moves j and k at most n times total.
    * Space Complexity: O(1) extra → only the result list is used.
    */
    public List<List<Integer>> threeSum3(int[] nums) {
        int len = nums.length;
        var res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i=0;i<len-2;i++){
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1,k=len-1;j<k;){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum>0){
                    k--;
                }else if(sum<0){
                    j++;
                }
                else{
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    while(j<k&&nums[j]==nums[j+1]){
                        j++;
                    }
                    while(j<k&&nums[k]==nums[k-1]){
                        k--;
                    }
                    j++;
                    k--;
                }
            }
        }
        return res;
    }
}

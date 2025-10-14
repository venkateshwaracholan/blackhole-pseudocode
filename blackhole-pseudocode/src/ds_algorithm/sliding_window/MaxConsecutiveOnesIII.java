package ds_algorithm.sliding_window;

public class MaxConsecutiveOnesIII {
    
    /*
    * longestOnesBrute (NestedLoops for all windows + WindowZeroCount)
    * ONE LINER: int max = 0: (initialize maximum consecutive ones length to 0); for(int i=0;i<nums.length;i++): (iterate over all possible starting indices of subarrays); for(int j=i;j<nums.length;j++): (iterate over all possible ending indices of subarrays starting from i); int zeroCount = 0: (initialize zero counter for current subarray); for(int z=i;z<=j;z++): (iterate over each element in the subarray from i to j); if(nums[z]==0) zeroCount++: (count zeros in the current subarray); if(zeroCount <= k) max = Math.max(max, j-i+1): (update maximum length if current subarray has at most k zeros); return max: (return the length of the longest valid subarray) → O(n^3), O(1) space (absolute: n*n*n)
    *
    * Code(Reason):
    * - int max = 0: initialize maximum consecutive ones length to 0.
    * - for(int i=0;i<nums.length;i++): iterate over all possible starting indices of subarrays.
    * - for(int j=i;j<nums.length;j++): iterate over all possible ending indices of subarrays starting from i.
    * - int zeroCount = 0: initialize zero counter for current subarray.
    * - for(int z=i;z<=j;z++): iterate over each element in the subarray from i to j.
    *     - if(nums[z]==0) zeroCount++: count zeros in the current subarray.
    * - if(zeroCount <= k) max = Math.max(max, j-i+1): update maximum length if current subarray has at most k zeros.
    * - return max: return the length of the longest valid subarray.
    *
    * Rationale: This brute-force approach checks every possible contiguous subarray and counts the number of zeros. If the count of zeros is less than or equal to k, it updates the maximum length. Although simple, it performs redundant counting for overlapping subarrays, leading to high computational cost.
    *
    * Time Complexity: O(n^3) — three nested loops over the array to count zeros in each subarray (absolute: n*n*n).
    * Space Complexity: O(1) — only constant extra space is used for counters and max variable.
    *
    * Examples:
    * Example 1:
    * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
    * Trace: iterate i=0→10, for each i iterate j=i→10, for each subarray count zeros; update max if zeros ≤ 2. 
    * Output: 6 (subarray [1,1,1,0,0,1] or [0,1,1,1,1,1]).
    *
    * Example 2:
    * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
    * Trace: iterate all subarrays, count zeros, update max if ≤ k; longest subarray satisfying condition has length 10.
    * Output: 10 (subarray [1,1,1,0,1,1,0,0,0,1,1,1,1] with 3 zeros flipped).
    */

    public int longestOnesBrute(int[] nums, int k) {
        int max = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
                int zeroCount = 0;
                for(int z=i;z<=j;z++){
                    if(nums[z]==0){
                        zeroCount++;
                    }
                }
                if(zeroCount <= k){
                    max = Math.max(max, j-i+1);
                }
            }
        }
        return max;
    }


    /*
    * longestOnesZeroCountOnJ (NestedLoops + Sliding j Window EarlyBreak)
    * ONE LINER: int max = 0: (initialize maximum consecutive ones length to 0); for(int i=0;i<nums.length;i++): (iterate over all possible starting indices of subarrays); int zeroCount = 0: (initialize zero counter for current subarray starting at i); for(int j=i;j<nums.length;j++): (iterate over all possible ending indices of subarrays starting from i); if(nums[j]==0) zeroCount++: (increment zero counter if current element is 0); if(zeroCount > k) break: (stop expanding subarray if zeros exceed k, optimization over counting full subarray); max = Math.max(max, j-i+1): (update maximum length if current subarray has at most k zeros); return max: (return the length of the longest valid subarray) → O(n^2), O(1) space (absolute: n*n)
    *
    * Code(Reason):
    * - int max = 0: initialize maximum consecutive ones length to 0.
    * - for(int i=0;i<nums.length;i++): iterate over all possible starting indices of subarrays.
    * - int zeroCount = 0: initialize zero counter for current subarray starting at i.
    * - for(int j=i;j<nums.length;j++): iterate over all possible ending indices of subarrays starting from i.
    *     - if(nums[j]==0) zeroCount++: increment zero counter if current element is 0.
    *     - if(zeroCount > k) break: stop expanding subarray if zeros exceed k (early termination optimization).
    *     - max = Math.max(max, j-i+1): update maximum length if current subarray has at most k zeros.
    * - return max: return the length of the longest valid subarray.
    *
    * Rationale: This approach checks all subarrays starting at each index i and expands to the right. It counts zeros in the subarray and breaks early if the count exceeds k, reducing unnecessary counting compared to a full triple-nested loop. The maximum length of valid subarrays is tracked in `max`.
    *
    * Time Complexity: O(n^2) — two nested loops; early break improves average case but worst-case remains quadratic (absolute: n*n).
    * Space Complexity: O(1) — only a few integer variables used.
    *
    * Examples:
    * Example 1:
    * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
    * Trace: i=0, zeroCount counts 0s until j=5 (zeroCount=3 > k) → break; max updated to 6 from subarray [1,1,1,0,0,1]; continue i=1... 
    * Output: 6 (longest subarray with at most 2 zeros flipped).
    *
    * Example 2:
    * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
    * Trace: i=0, expand j until zeroCount > 3 → break; track max for each i; subarray [1,1,1,0,1,1,0,0,0,1,1,1,1] gives max=10.
    * Output: 10 (longest subarray with at most 3 zeros flipped).
    */

    public int longestOnesZeroCountOnJ(int[] nums, int k) {
        int max = 0;
        for(int i=0;i<nums.length;i++){
            int zeroCount = 0;
            for(int j=i;j<nums.length;j++){
                if(nums[j]==0){
                    zeroCount++;
                }
                if(zeroCount > k){
                    break;
                }
                max = Math.max(max, j-i+1);
            }
        }
        return max;
    }

    /*
    * longestOnesSlidingWindow (TwoPointers + SlidingWindow)
    * ONE LINER: int max = 0, zeroCount = 0: (initialize maximum length and zero counter); for(int i=0, j=0;j<nums.length;j++): (iterate end pointer j through array while maintaining start pointer i); if(nums[j]==0) zeroCount++: (increment zero counter when encountering a 0); if(zeroCount > k): (check if zeros exceed allowed flips); if(nums[i]==0) zeroCount--: (decrement zero counter if start pointer i was 0, as window will slide); i++: (move start pointer to shrink window); max = Math.max(max, j-i+1): (update maximum length of valid window); return max: (return maximum length of subarray with at most k zeros flipped) → O(n), O(1) space (absolute: 2n)
    *
    * Code(Reason):
    * - int max = 0, zeroCount = 0: initialize variables to track maximum consecutive ones and current zero count in the sliding window.
    * - for(int i=0, j=0;j<nums.length;j++): initialize two pointers i (window start) and j (window end), iterate end pointer j over the array.
    *     - if(nums[j]==0) zeroCount++: increment zero count when current element is 0.
    *     - if(zeroCount > k): check if the current window exceeds allowed zero flips.
    *         - if(nums[i]==0) zeroCount--: if start element is 0, decrement zero count as it will be removed from window.
    *         - i++: move start pointer forward to shrink window until zeroCount <= k.
    *     - max = Math.max(max, j-i+1): update maximum window length if current window is valid.
    * - return max: return the length of the longest subarray with at most k zeros flipped.
    *
    * Rationale: This optimized sliding window approach maintains a window [i, j] where the number of zeros does not exceed k. When zeroCount exceeds k, the start pointer i is moved forward, shrinking the window while updating zeroCount. The maximum window size during the iteration gives the longest subarray of ones with at most k flips.
    *
    * Time Complexity: O(n) — each element is visited at most twice by i and j pointers (absolute: 2n).
    * Space Complexity: O(1) — only integer variables used to track state.
    *
    * Examples:
    * Example 1:
    * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
    * Trace: j=0..10, zeroCount increments on 0s, i slides forward when zeroCount>k; max updated to 6 for subarray [0,0,1,1,1,1] with 2 zeros flipped.
    * Output: 6 (longest subarray with at most 2 zeros flipped).
    *
    * Example 2:
    * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
    * Trace: maintain sliding window with zeroCount<=3, i moves forward as needed; max reaches 10 for subarray [1,1,1,0,1,1,0,0,0,1] with 3 zeros flipped.
    * Output: 10 (longest subarray with at most 3 zeros flipped).
    */

    public int longestOnesSLidingWindow(int[] nums, int k) {
        int max = 0, zeroCount = 0;
        for(int i=0, j=0;j<nums.length;j++){
            if(nums[j]==0){
                zeroCount++;
            }
            if(zeroCount > k){
                if(nums[i]==0){
                    zeroCount--;
                }
                i++;
            }
            max = Math.max(max, j-i+1);
        }
        return max;
    }
}

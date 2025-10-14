/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.greedy;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/can-place-flowers/description/

public class CanPlaceFlowers {
    
    /*
    * canPlaceFlowers (Array + Greedy)
    * ONE LINER: int len = flowerbed.length: (store length of flowerbed array); for(int i=0;i<len;i++): (iterate over each plot in flowerbed); if(flowerbed[i]==0): (check if current plot is empty); boolean canPlant = true: (assume we can plant at current position); if(i-1>=0 && flowerbed[i-1]==1) canPlant=false: (cannot plant if previous plot has a flower); if(i+1<=len-1 && flowerbed[i+1]==1) canPlant=false: (cannot plant if next plot has a flower); if(canPlant) flowerbed[i]=1: (plant a flower at current plot), n--: (decrease remaining flowers to plant); if(n==0) return true: (early exit if all flowers planted); return n<=0: (return true if all flowers were planted, false otherwise) → O(n), O(1) space (absolute: n)
    *
    * Code(Reason):
    * - int len = flowerbed.length: store the length of the flowerbed array to avoid repeated length lookups.
    * - for(int i=0;i<len;i++): iterate over each plot to check if a flower can be planted.
    *     - if(flowerbed[i]==0): only consider empty plots for planting.
    *         - boolean canPlant = true: initialize a flag assuming the current plot can be used for planting.
    *         - if(i-1>=0 && flowerbed[i-1]==1) canPlant=false: check left neighbor; cannot plant if left plot has a flower.
    *         - if(i+1<=len-1 && flowerbed[i+1]==1) canPlant=false: check right neighbor; cannot plant if right plot has a flower.
    *         - if(canPlant) flowerbed[i]=1: plant a flower at current plot; n--: decrement the remaining number of flowers to plant.
    *         - if(n==0) return true: early return if all required flowers are planted.
    * - return n<=0: final check to return true if all flowers were planted, false otherwise.
    *
    * Rationale: Iterate through each plot and greedily plant a flower in empty plots that have no adjacent flowers. Early exit is possible when all required flowers are planted. The approach ensures maximal placement without violating the no-adjacent-flowers rule.
    *
    * Time Complexity: O(n) — iterate through the flowerbed array once (absolute: n).
    * Space Complexity: O(1) — constant extra space used for counters and flags.
    *
    * Examples:
    * Example 1:
    * Input: flowerbed = [1,0,0,0,1], n = 1
    * Trace: i=0: skip (occupied); i=1: cannot plant (right occupied); i=2: canPlant true, plant flower, n=0 → return true.
    * Output: true
    *
    * Example 2:
    * Input: flowerbed = [1,0,0,0,1], n = 2
    * Trace: i=0: skip; i=1: cannot plant; i=2: plant flower, n=1; i=3: cannot plant; i=4: skip; end of loop, n=1 > 0 → return false.
    * Output: false
    */

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        for(int i=0;i<len;i++){
            if(flowerbed[i]==0){
                boolean canPlant = true;
                if(i-1>=0 && flowerbed[i-1]==1){
                    canPlant=false;
                }
                if(i+1<=len-1 && flowerbed[i+1]==1){
                    canPlant=false;
                }
                if(canPlant){
                    flowerbed[i]=1;
                    n--;
                }
                if(n==0){
                    return true;
                }
            }
        }
        return n<=0;
    }

    /*
    * canPlaceFlowersAlt (Array + Greedy)
    * ONE LINER: int len = flowerbed.length: (store length of flowerbed array); for(int i=0;i<len;i++): (iterate over each plot); if(flowerbed[i]==0): (check if plot is empty); int left = i==0 ? 0 : flowerbed[i-1]: (get left neighbor value, assume 0 if at start); int right = i==len-1 ? 0 : flowerbed[i+1]: (get right neighbor value, assume 0 if at end); if(left==0 && right==0): (can plant only if both neighbors empty); flowerbed[i]=1: (plant flower); n--: (decrement flowers left to plant); if(n==0) return true: (early exit if all flowers planted); return n<=0: (final check if all flowers planted) → O(n), O(1) space (absolute: n)
    *
    * Code(Reason):
    * - int len = flowerbed.length: store length of the flowerbed array for iteration and boundary checks.
    * - for(int i=0;i<len;i++): iterate over each plot to attempt planting.
    *     - if(flowerbed[i]==0): only consider empty plots for planting.
    *         - int left = i==0 ? 0 : flowerbed[i-1]: determine left neighbor value, treating out-of-bounds as 0 to simplify edge cases.
    *         - int right = i==len-1 ? 0 : flowerbed[i+1]: determine right neighbor value, treating out-of-bounds as 0 for last element.
    *         - if(left==0 && right==0): check if both neighbors are empty to satisfy no-adjacent-flowers rule.
    *             - flowerbed[i]=1: plant flower at current plot.
    *             - n--: decrement remaining number of flowers to plant.
    *         - if(n==0) return true: early exit if all required flowers are planted.
    * - return n<=0: final check to return true if all flowers were planted, false otherwise.
    *
    * Rationale: Iterate through each plot and greedily plant a flower if both adjacent plots are empty (or at edges). By tracking remaining flowers and early returning when n reaches zero, the method ensures correct placement without violating adjacency rules.
    *
    * Time Complexity: O(n) — iterate through the flowerbed array once (absolute: n).
    * Space Complexity: O(1) — only variables for counters and neighbor checks are used.
    *
    * Examples:
    * Example 1:
    * Input: flowerbed = [1,0,0,0,1], n = 1
    * Trace: i=0: skip (occupied); i=1: left=1, right=0 → cannot plant; i=2: left=0, right=0 → plant flower, n=0 → return true.
    * Output: true
    *
    * Example 2:
    * Input: flowerbed = [1,0,0,0,1], n = 2
    * Trace: i=0: skip; i=1: cannot plant; i=2: plant flower, n=1; i=3: left=1, right=1 → cannot plant; i=4: skip; end of loop, n=1 > 0 → return false.
    * Output: false
    */

    public boolean canPlaceFlowersAlt(int[] flowerbed, int n) {
        int len = flowerbed.length;
        for(int i=0;i<len;i++){
            if(flowerbed[i]==0){
                int left = i==0 ?  0 : flowerbed[i-1];
                int right = i==len-1 ?  0 : flowerbed[i+1];
                if(left==0 && right==0){
                    flowerbed[i]=1;
                    n--;
                }
                if(n==0){
                    return true;
                }
            }
        }
        return n<=0;
    }
}

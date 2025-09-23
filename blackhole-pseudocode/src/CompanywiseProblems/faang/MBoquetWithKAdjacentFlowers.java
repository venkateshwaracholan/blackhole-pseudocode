/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CompanywiseProblems.faang;

/**
 *
 * @author user
 */

//https://leetcode.com/discuss/post/7138419/faang-interview-dsa-problem-need-help-by-igt4/

//Q1. You are given an array of integers, bakeTime, along with two integers, m and k.
//
//Your task is to determine the minimum number of minutes required to bake m batches of cakes.
//
//Each batch must be made using exactly k cakes that finish baking at the same time and are continuous.
//
//The oven contains n cakes, where the ith cake is ready after bakeTime[i] days and can be used in only one batch.
//
//Return the shortest time needed to prepare m batches. If it is not possible to make m batches, return -1.
//
//Input: bakeTime = [1, 10, 3, 10, 2], m = 3, k = 1
//Output: 3
//
//Explanation:
//Let's see what happened in the first three days.

//https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/ 





public class MBoquetWithKAdjacentFlowers {
    
    // brute-force
    // Complexity Analysis
    // Time: O(n * maxDay) → slow if maxDay is large
    // n = length of bloomDay
    // maxDay = maximum value in bloomDay array
    // Space: O(1)
    // Key Points / FAANG Notes
    // Very similar logic to optimized solution but no binary search.
    // Useful for small inputs or understanding the greedy check.
    // Good for documenting the evolution from brute to optimized.

    public int minDaysBrute(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) return -1;

        int maxDay = 0;
        for (int day : bloomDay) maxDay = Math.max(maxDay, day);

        // Brute force: check each day from 1 to maxDay
        for (int day = 1; day <= maxDay; day++) {
            if (isValid(bloomDay, m, k, day)) return day;
        }

        return -1;
    }


    
    // optimized
    //    Complexity Analysis
    //Time: O(n * log(maxDay))
    //n = length of bloomDay
    //log(maxDay) = binary search iterations over day range
    //
    //Space: O(1)
    //Key Points / FAANG Notes
    //Binary search is on the answer space (days), not array indices.
    //Greedy check counts consecutive flowers ≤ mid.
    //Edge case handled: (long)m * k > bloomDay.length → return -1.
    
    public int minDays(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) return -1;

        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;

        // Find the min and max bloom days
        for (int day : bloomDay) {
            l = Math.min(day, l);
            r = Math.max(day, r);
        }

        // Binary search on days
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(bloomDay, m, k, mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    
    // Brute-force key logic explanation
    // isValid(bloomDay, m, k, day):
    // - Checks if we can make at least 'm' bouquets by a given day.
    // - Counts consecutive flowers that have bloomed ≤ day.
    // - Once 'k' consecutive flowers are found, forms a bouquet and resets count.
    // - If total bouquets ≥ m, returns true.
    // - Resets consecutive count when a flower is not ready (bloomDay[i] > day).
    // Importance:
    // - This is the same check reused in optimized solution with binary search.
    // - In brute-force, we try all days from 1 to maxDay using isValid.
    // - In optimized solution, binary search narrows down the minimum day while still using isValid.

    // Check if we can make m bouquets by 'mid' day
    static boolean isValid(int[] bloomDay, int m, int k, int mid) {
        int bouquets = 0, flowers = 0;

        for (int day : bloomDay) {
            if (day <= mid) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                    if (bouquets >= m) return true;
                }
            } else {
                flowers = 0;
            }
        }

        return false;
    }
}

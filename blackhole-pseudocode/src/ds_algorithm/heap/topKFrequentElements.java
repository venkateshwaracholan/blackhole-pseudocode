/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.heap;

/**
 *
 * @author vshanmugham
 */
import java.util.*;
// https://leetcode.com/problems/top-k-frequent-elements/

public class TopKFrequentElements {

    /**
     * Brute Force:
     * - Count frequencies using a HashMap
     * - Sort map entries by frequency with comparator(n entries)
     * - Take top K
     * 
     * Time: O(N log N)
     * Space: O(N)
     */
    public int[] topKFrequentBrute(int[] nums, int k) {
        var freq = new HashMap<Integer, Integer>();
        int[] ans = new int[k];
        for(int n: nums){
            // freq.compute(n, (key,v) -> v==null ? 1 : v+1);
            freq.merge(n,1,Integer::sum);
        }
        var sorted = new ArrayList<>(freq.keySet());
        sorted.sort((a,b) -> freq.get(b)-freq.get(a));
        
        for(int i=0;i<k;i++){
            ans[i] = sorted.get(i);
        }
        return ans;
    }
    
   /**
     * Approach 1: Frequency Map + Min Heap
     * - Count element frequencies using a HashMap
     * - Maintain a min-heap of size k+1 to track top K frequent elements
     * - If heap size exceeds K, remove element with smallest frequency
     * - Extract elements from heap for final result (order optional)
     * 
     * Time: O(N log K)
     * Space: O(N + K)
     * 
     * Note: if using maxHeap then we ll have to insert n records coz removing min is not possible 
     */
    public int[] topKFrequentOptimal(int[] nums, int k) {
        var freq = new HashMap<Integer, Integer>();
        int[] ans = new int[k];
        for(int n: nums){
            freq.merge(n,1,Integer::sum);
        }
        var minHeap = new PriorityQueue<Integer>((a,b)-> freq.get(a)-freq.get(b));
        for(int n: freq.keySet()){
            minHeap.add(n);
            if(minHeap.size()>k){
                minHeap.poll();
            }
        }
        for(int i=k-1;i>=0;i--){
            ans[i] = minHeap.poll();
        }
        return ans;
    }


    /**
     * Approach 1.2: Frequency Map + Min Heap of size K
     * 
     * - Count frequency of each number using HashMap (`merge` for clarity).
     * - Maintain a min-heap (PriorityQueue) of size k storing [number, frequency] pairs.
     *   - Only push a new pair if heap size < k, or frequency > min frequency in heap.
     *   - This differs from the "k+1 then poll" approach:
     *       - Here, we **never exceed size k**, simplifying heap logic.
     *       - Previous approach added every element and polled when size > k, achieving same effect.
     * - Using Integer[] allows the comparator to directly access frequency for ordering.
     * - Pop from heap in reverse to fill answer array in descending order of frequency.
     *
     * Time Complexity: O(N log K)  
     *   - Counting frequencies: O(N)  
     *   - Heap operations: O(N log K)  
     * Space Complexity: O(N + K)  
     *   - HashMap stores N elements  
     *   - Heap stores up to K elements
     */
    public int[] topKFrequentMoreOptimal(int[] nums, int k) {
        var freq = new HashMap<Integer, Integer>();
        for (int n : nums) {
            freq.merge(n, 1, Integer::sum); // increment frequency by 1
        }
        var minHeap = new PriorityQueue<Integer[]>((a, b) -> a[1] - b[1]);
        for (int n : freq.keySet()) {
            int f = freq.get(n);
            if (minHeap.size() < k) {
                minHeap.add(new Integer[]{n, f}); // add new pair if heap not full
            } else if (f > minHeap.peek()[1]) {
                minHeap.poll();                   // remove smallest frequency
                minHeap.add(new Integer[]{n, f}); // add current
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = minHeap.poll()[0];
        }

        return ans;
    }

  
    /**
     * Approach: Bucket Sort for Top K Frequent Elements
     *
     * - Idea: Map numbers → frequencies, then invert map to frequency → numbers.
     * - Steps:
     *   1. Count frequencies using a HashMap.
     *   2. Create an array of lists (bucket) where index = frequency.
     *      - bucket[f] contains all numbers appearing f times.
     *      - Using array allows direct access by frequency; ArrayList<List<Integer>> would need pre-filling list to avoid IndexOutOfBoundsException.
     *   3. Traverse bucket in reverse (high → low frequency) and collect first k elements.
     *
     * - Advantages:
     *   - Linear time O(n) since max frequency ≤ n.
     *   - Simple, avoids heap or tree structures.
     *
     * - Caveats:
     *   - Null checks needed since not all frequencies may exist.
     *   - Only practical when frequencies are bounded by array size (≤ n).
     *
     * Time: O(n)
     * Space: O(n)
     */
    public int[] topKFrequentBucket(int[] nums, int k) {
        var freq = new HashMap<Integer, Integer>();
        List<Integer> bucket[] = new List[nums.length+1];
        int[] ans = new int[k];
        for(int n: nums){
            freq.merge(n,1,Integer::sum);
        }
        for(int n: freq.keySet()){
            int f = freq.get(n);
            if(bucket[f]==null){
                bucket[f] = new ArrayList<>();
            }
            bucket[f].add(n);
        }

        for(int i=nums.length, x = 0; i>=0 && x<k; i--){
            var b = bucket[i];
            if(b!=null){
                for(int z=0;z<b.size();z++,x++){
                    ans[x] = b.get(z);
                }
            }
        }
        return ans;
    }
    

    /**
     * Approach: TreeSet / TreeMap / Heap for Top K Frequent Elements
     *
     * - TreeSet: 
     *   - Maintains elements in sorted order automatically based on comparator (here frequency, then value for uniqueness).
     *   - Correct comparator example (min-order, like min-heap behavior): 
     *         (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]
     *       - a[1] == b[1] → frequencies equal, compare numbers to prevent duplicates.
     *       - Otherwise, sort by frequency ascending (smallest frequency first → min-heap analogy).
     *   - Max-order comparator example (like max-heap behavior):
     *         (a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]
     *       - Largest frequency first.
     *   - Can efficiently maintain a sliding “top k” by adding new elements and removing the smallest when size > k.
     *   - Caveats:
     *       - TreeSet does not allow duplicate keys (comparator returning 0 is treated as duplicate), so combine frequency + value to ensure uniqueness.
     *       - Min-order TreeSet + size=k → O(n log k) time, Max-order TreeSet requires storing all n elements → O(n log n) time.
     *
     * - TreeMap:
     *   - Maps frequency → list of numbers with that frequency, maintaining frequencies in sorted order.
     *   - Min-order comparator (like min-heap behavior, ascending frequencies):
     *         (a, b) -> a - b
     *   - Max-order comparator (like max-heap behavior, descending frequencies):
     *         (a, b) -> b - a
     *   - Allows reverse traversal to pick top-k elements efficiently.
     *   - Time: O(n log n) due to maintaining sorted map of all frequencies, Space: O(n + k).
     *   - Caveats:
     *       - Overkill for simple top-k problems because heap gives O(n log k) with smaller constants.
     *       - List<Integer> as value handles multiple numbers sharing same frequency.
     *       - Min-order TreeMap + size=k → O(n log k), Max-order → add all n elements → O(n log n).
     *
     * - Heap (PriorityQueue):
     *   - Min-heap:
     *       - Maintain size=k → remove smallest frequency → O(n log k) time, O(k) space.
     *   - Max-heap:
     *       - Add all n elements → poll k times → O(n + k log n) time, O(n) space.
     *
     * Summary / Distinction:
     * - TreeSet<int[]> uses int[] key to combine number and frequency for uniqueness.
     * - TreeMap<Integer, List<Integer>> uses frequency as key, list as value to handle multiple numbers.
     * - Heap is simpler and faster for Top K Frequent Elements.
     * - Trees are educational here, helpful for problems requiring ordering, range queries, or unique constraints.
     */


    
  
  // need to try quick select when i am free, large code
  // https://leetcode.com/problems/top-k-frequent-elements/solutions/646157/top-k-frequent-elements/
}

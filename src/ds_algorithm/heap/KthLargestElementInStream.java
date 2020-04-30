/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.heap;


import java.util.*;
/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/kth-largest-element-in-a-stream 

public class KthLargestElementInStream {
  
}

// Time O(nlogk) space O(K) n - number of element initailly, k - for kth largest
// core idea: min heap of size k and first element is Kth largest element.
// optimisation with if checks to avoid adiing unnecessarily if not required
class KthLargest {

    PriorityQueue<Integer> heap;
    int k;
    public KthLargest(int k, int[] nums) {
        heap = new PriorityQueue(k);
        this.k = k;
        for(int i : nums) {
            add(i);
        }
    }
    
    public int add(int val) {
        if(heap.size() < k) {
            heap.offer(val);
        }
        else if(val > heap.peek()) {
            heap.poll();
            heap.offer(val);
        }
        return heap.peek();
    }
}

// Time O(nlogk) space O(K+1) n - number of element initailly, k - for kth largest
class KthLargestAlt {

    Queue<Integer> heap = new PriorityQueue<>();   
    int k;
    public KthLargestAlt(int k, int[] nums) {
        this.k = k;
        for(int i=0;i<nums.length;i++)
            add(nums[i]);
    }
    
    public int add(int val) {
        heap.add(val);
        if(heap.size()>k) heap.poll();
        return heap.peek();
    }
}
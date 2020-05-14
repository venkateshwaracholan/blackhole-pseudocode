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

public class KtopFrequentElements {
  
// Time O(nlogk) space O(k) n - number of element initailly, k - for kth largest
//  core idea: min heap and reverse assignment 
// using maps inside comparators what an idea, without passing, it looks like javascript closure
//  i use min heap and add answer in reverse
  public int[] topKFrequentFast(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap();
    for(int n:nums)
      map.put(n, map.getOrDefault(n,0)+1);
    Queue<Integer> heap = new PriorityQueue<>((a,b)->map.get(a)-map.get(b));
    for(int n: map.keySet()){
       if(heap.size()<k) heap.add(n);
       else if(map.get(n)>map.get(heap.peek())){
           heap.poll();
           heap.add(n);
       }
    }
    int ans[] = new int[k];
    while(k>0)
      ans[k---1] = heap.poll();
    return ans;
  }
  
  
// Time O(nlogk) space O(K) n - number of element initailly, k - for kth largest
  public int[] topKFrequnetSlow(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap();
    for(int n:nums)
      map.put(n, map.getOrDefault(n,0)+1);
    Queue<Integer> heap = new PriorityQueue<>((a,b)->map.get(a)-map.get(b));
    for(int n: map.keySet()){    
      heap.add(n);
      if(heap.size()>k) heap.poll();
    }
    int ans[] = new int[k];
    while(k>0)
      ans[k---1] = heap.poll();
    return ans;
  }
  
  
// Time O(nlogk) space O(K) n - number of element initailly, k - for kth largest
//  using int[] for comparators and speed
  public int[] topKFrequentArrayFast(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for(int n:nums)
            map.put(n, map.getOrDefault(n,0)+1);
        Queue<Integer[]> heap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(int n: map.keySet()){
            if(heap.size()<k)
                heap.add(new Integer[]{n,map.get(n)});
            else if(map.get(n)>heap.peek()[1]){
                heap.poll();
                heap.add(new Integer[]{n,map.get(n)});
            } 
        }
        int ans[] = new int[k];
        while(k>0)
            ans[k---1] = heap.poll()[0];
        return ans;
    }
  
  
// Time O(nlogk) space O(K) n - number of element initailly, k - for kth largest
  public int[] topKFrequentArraySlow(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap();
    for(int n:nums)
        map.put(n, map.getOrDefault(n,0)+1);
    Queue<Integer[]> heap = new PriorityQueue<>((a,b)->a[1]-b[1]);
    for(int n: map.keySet()){
        heap.add(new Integer[]{n,map.get(n)});
        if(heap.size()>k) heap.poll();
    }
    int ans[] = new int[k];
    while(k>0)
        ans[k---1] = heap.poll()[0];
    return ans;
  }
}

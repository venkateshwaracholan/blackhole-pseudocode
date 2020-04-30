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

public class KthLargestDistinctElement {
  
//  Time:O(nlogk) space O(k)
//  core idea: set + min heap
// avoid addig if it is already present in set
//  just like how we  remove from heap, remove from set also
  
  public int thirdMax(int[] nums) {
    Queue<Integer> heap = new PriorityQueue<>((a,b)-> a-b);
    Set<Integer> set = new HashSet();
    for(int n:nums){
      if(set.contains(n))
        continue;
      if(heap.size()<3){
        heap.add(n);
        set.add(n);
      } 
      else if(n>heap.peek()){
        set.remove(heap.peek());
        heap.poll();
        heap.add(n);
        set.add(n);
      }
      //System.out.println(heap.size()+" "+heap.peek());
    }
    return heap.peek();
  }
  
  
//  there is a treeset solution
//  need write that
}

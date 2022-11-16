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
// https://leetcode.com/problems/third-maximum-number/

public class ThirdLargestNumber {
//  Time: O(n) space O(1)
//  core idea: 3 variables, k variables for kth largest(o(k) space)
//  if else if ladder provids easy way to assign to specific variables and reassign others
//  if max is changing second and thrid max has to adjust acordingly
//  we use Long.MIN_VALUE because testcase contained Integer.MIN_VALUE, cheating done right
  public int thirdMax(int[] nums) {
    long max = Long.MIN_VALUE,smax = Long.MIN_VALUE, tmax  =Long.MIN_VALUE;
    for(int n:nums){
      if(n>max){
        tmax = smax;
        smax = max;
        max = n;
      }else if(n<max && n>smax){
        tmax = smax;
        smax = n;
      }else if(n<smax && n>tmax)
        tmax = n;
    }
    if(tmax==Long.MIN_VALUE) return (int)max;
    else return (int)tmax;
  }
  
//  Time: O(nlogk) space O(k)  k=3 so constant
//  i use a set and a heap of size k to find kth distinct largest number 
  public int thirdMaxGeneric(int[] nums) {
    Queue<Long> heap = new PriorityQueue<>((a,b)-> Long.compare(a,b));
    int max = Integer.MIN_VALUE;
    Set<Integer> set = new HashSet();
    for(int n:nums){
      if(set.contains(n))
        continue;
      if(heap.size()<3){
        heap.add(Long.valueOf(n));
        set.add(n);
      } 
      else if(n>heap.peek()){
        set.remove(heap.peek());
        heap.poll();
        heap.add(Long.valueOf(n));
        set.add(n);
      }
      //System.out.println(heap.size()+" "+heap.peek());
      max = Math.max(max,n);
    }
    return heap.size()<3 ? max : heap.peek().intValue();
  }

//  Time: O(nk) space O(k)  k=3 so constant
//  atmost have k elements in the set and remove if necessary
//  use sets alone and iterate in sets to find min
  public int thirdMaxSetsAlone(int[] nums) {
    int max = Integer.MIN_VALUE;
    Set<Integer> set = new HashSet();
    int min = Integer.MAX_VALUE;
    for(int n:nums){
      if(set.contains(n)) continue;
      min = Integer.MAX_VALUE;
      for(int x: set) min = Math.min(min,x);
      if(set.size()<3) set.add(n);
      else if (n>min){
          set.remove(min);
          set.add(n);
      }
      max = Math.max(max,n);
    }
    min = Integer.MAX_VALUE;
    for(int n: set) min = Math.min(min,n);
    return set.size()<3 ? max : min;
  }
  
//  
  public int thirdMaxSetMethods(int[] nums) {       
    Set<Integer> maximums = new HashSet<Integer>();
    for (int num : nums) {
      maximums.add(num);
      if (maximums.size() > 3) {
        maximums.remove(Collections.min(maximums));
      }
    }
    if (maximums.size() == 3) {
      return Collections.min(maximums);
    }
    return Collections.max(maximums);
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.queue;

/**
 *
 * @author vshanmugham
 */
import java.util.*;

// https://www.youtube.com/watch?v=x_m69OeOHN8


// Map + queue
// have a freq map and add to queue
// when getting if first pos doesnt have freq 1, poll and continue unitl queue is empty
class FirstUniqueInteger {
    Map<Integer, Integer> freq = new HashMap<>();
    Queue<Integer> q = new LinkedList<>();
    public FirstUniqueInteger(int[] nums) {
        for (int x : nums)
            add(x);
    }
    public int showFirstUnique() {
        while (!q.isEmpty() && freq.get(q.peek()) > 1)
            q.poll();
        return q.isEmpty() ? -1 : q.peek();
    }
    public void add(int value) {
        freq.put(value, freq.getOrDefault(value, 0) + 1);
        q.offer(value);
    }
    
    
    public static void main(String args[]){
      FirstUniqueInteger f = new FirstUniqueInteger(new int []{6,3,6});
      System.out.println(f.showFirstUnique());
      f.add(3);
      System.out.println(f.showFirstUnique());
    }
}

// Map + queue
// Time  add O(1) showFirstUnique O(1) amortized
// optimization done, lets not add to queue if ist already present in map
// and use a boolean map instead of freq map
class FirstUniqueIntegerOpt {
    Map<Integer, Boolean> uniq = new HashMap<>();
    Queue<Integer> q = new LinkedList<>();
    public FirstUniqueIntegerOpt(int[] nums) {
        for (int x : nums)
            add(x);
    }
    public int showFirstUnique() {
        while (!q.isEmpty() && !uniq.get(q.peek()))
            q.poll();
        return q.isEmpty() ? -1 : q.peek();
    }
    public void add(int value) {
        if(uniq.containsKey(value)) uniq.put(value, false);
        else {
          uniq.put(value, true);
          q.add(value);
        }
        
    }
    
    public static void main(String args[]){

      FirstUniqueIntegerOpt f = new FirstUniqueIntegerOpt(new int []{6,3,6});
      System.out.println(f.showFirstUnique());
      f.add(3);
      System.out.println(f.showFirstUnique());
    }
}


// map + heap
// Time  add O(log n) showFirstUnique O(1) amortized
class FirstUniqueIntegerHeap {
    Map<Integer, Integer> map = new HashMap<>();
    Queue<Integer> heap = new PriorityQueue<>((a,b)-> map.get(a)-map.get(b));
    public FirstUniqueIntegerHeap(int[] nums) {
        for (int x : nums)
            add(x);
    }
    public int showFirstUnique() {
        return (heap.isEmpty() || map.get(heap.peek())!=1) ? -1 : heap.peek();
    }
    public void add(int value) {
      if(!map.containsKey(value)){
        map.put(value,1);
        heap.add(value);
      }else{
        heap.remove(value);
        map.put(value, map.get(value)+1);
        heap.add(value);
      }
    }
    
    public static void main(String args[]){

      FirstUniqueIntegerHeap f = new FirstUniqueIntegerHeap(new int []{6,3,6});
      System.out.println(f.showFirstUnique());
      f.add(3);
      System.out.println(f.showFirstUnique());
    }
}


// set + linkedHashSet
// Time  add O(1) showFirstUnique O(1) amortized
//  loks like the best solution i wrote
class FirstUniqueIntegerLinkedHashSet{
    Set<Integer> visted = new HashSet();
    Set<Integer> single  = new LinkedHashSet();
    public FirstUniqueIntegerLinkedHashSet(int[] nums) {
        for (int x : nums) add(x);
    }
    public int showFirstUnique() {
      for(int x :single) return x;
      return -1;
    }
    public void add(int value) {
      if(!visted.contains(value)){
        visted.add(value);
        single.add(value);
      }else{
        single.remove(value);
      }
    }
    
    public static void main(String args[]){
      FirstUniqueIntegerLinkedHashSet f = new FirstUniqueIntegerLinkedHashSet(new int []{6,3,6});
      System.out.println(f.showFirstUnique());
      f.add(3);
      System.out.println(f.showFirstUnique());
    }
}
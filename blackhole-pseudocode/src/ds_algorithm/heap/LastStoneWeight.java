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

// https://leetcode.com/problems/last-stone-weight
import java.util.*;

public class LastStoneWeight {
  


//  Time: O(nlogn) space: O(n)
//  Core Idea:use max heap
//  poll 2 destroy and push back if not 0
//  until heap becomes 1 and return
  public int lastStoneWeightHeap(int[] stones) {
    Queue<Integer> q = new PriorityQueue<>((a,b)->b-a);
    for(int i=0;i<stones.length;i++)
        q.add(stones[i]);
    while(q.size()>1){
      int x = q.poll()-q.poll();
      if(x!=0)
        q.add(x);
    }
    return q.size()==0? 0: q.poll();
  }
  
  public int lastStoneWeightHeapAlt(int[] stones) {
      Queue<Integer> q = new PriorityQueue<>((a,b)->b-a);
      for(int i=0;i<stones.length;i++)
          q.add(stones[i]);
      while(q.size()>1){
        int x = q.poll()-q.poll();
        q.add(x);
      }
      return q.poll();
  }
    
  
  
  
//  Time: O(n*nlogn) Space:O(1) worst solution
//  core idea sort everytime inside the loop when we smash the largest stones
//  reverse traversal as int array cant be sorted in desc 
  public int lastStoneWeight(int[] stones) {
    for(int i=stones.length-2;i>=0;i--){
        Arrays.sort(stones);
        stones[i] = Math.abs(stones[i+1]-stones[i]);
    }
    return stones[0];
  }
  
// fastest solution
// becoz of equal case optimization
  
  public int lastStoneWeightFastest(int[] stones) {
        int i = stones.length - 1;
        while(i > 0) {
            Arrays.sort(stones);
            if (stones[i] == stones[i-1]) {
                i = i - 2; // destroy both
            } else {
                stones[i-1] = Math.abs(stones[i-1] - stones[i]);
                i--;
            }
        }
        if (i < 0) return 0;
        return stones[0];
    }
}



/*
Crashing stones
Each day a quarry-worker is given a pile of stones and told to reduce the larger stones into smaller ones. The worker must smash the stones together to reduce them, and is told to always pick up the largest two stones and smash them together. If the stones are of equal weight, they both disintegrate entirely. If one is larger, the smaller one is disintegrated and the larger one is reduced by the weight of the smaller one. Eventually, there is either one stone left that cannot be broken, or all of the stones have been smashed. Determine the weight of the last stone, or return 0 if there is none.

 

Example

weights = [1,2,3,6,7,7].

 

The worker always starts with the two largest stones. In this case, the two largest stones have equal weights of 7 so they both disintegrate when smashed. Next the worker smashes weights 3 and 6. The smaller one is destroyed and the larger weighs 6 - 3 = 3 units. Then, weights 3 and 2 are smashed together, which leaves a stone of weight 1. This is smashed with the last remaining stone of weight 1. There are no stones left, so the remaining stone weight is 0.


*/
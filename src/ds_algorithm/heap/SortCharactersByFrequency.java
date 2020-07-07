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

// https://leetcode.com/problems/sort-characters-by-frequency/

import java.util.*;

public class SortCharactersByFrequency {
  
//  Time : O(n) space: O(n)
//  O(n) because i am using maxHeap.addAll(freq.keySet()); instead of populating one by one
  public String frequencySort(String s) {
      Map<Character, Integer> freq = new HashMap();

      for(char c: s.toCharArray())
          freq.put(c, freq.getOrDefault(c,0)+1);
      Queue<Character> maxHeap = new PriorityQueue<>((a,b) ->freq.get(a)==freq.get(b)? a.compareTo(b) : freq.get(b)-freq.get(a));
      StringBuilder ans = new StringBuilder();
      maxHeap.addAll(freq.keySet());
      while(!maxHeap.isEmpty()){
          char c = maxHeap.poll();
          for(int i=0;i<freq.get(c);i++)
              ans.append(c);
      }
      return ans.toString();

  }
}

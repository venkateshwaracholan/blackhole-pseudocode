/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.hash;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/first-unique-character-in-a-string
import java.util.*;

public class FirstUniqueCharacterInString {
  
//  Time O(2n) space O(1) - 26 char space
//  iterate twice
  public int firstUniqChar(String s) {
      int map[] = new int[26];
      char arr[] = s.toCharArray();
      for(char c: arr) map[c-'a']+=1;
      for(int i=0;i< arr.length;i++) if(map[arr[i]-'a']==1) return i;
      return -1;
  }
  
//  Time O(2n) space O(1) - 26 char space
//  single iteration using linkedhashmap for storing what comes only once and remove whats twice
  public int firstUniqCharIntuitive(String s) {
      int map[] = new int[26];
      Map<Character,Integer> lmap = new LinkedHashMap();
      char arr[] = s.toCharArray();
      for(int i=0;i<arr.length;i++) {
          if(map[arr[i]-'a']>0){
              lmap.remove(arr[i]);
          }else{
              map[arr[i]-'a']=1;
              lmap.put(arr[i],i);
          }

      }
      for(Character x: lmap.keySet())
          return lmap.get(x);
      return -1;
  }
  
//  Time O(26n) space O(1) - 26 char space
//  26 iterations, indexOf and lastIndexOf operation runs on O(n) complexity
//  unsure why still this is the fastest of all three
  public int firstUniqCharVeraLevel(String s) {
      int output = Integer.MAX_VALUE;
      for (char c='a'; c <= 'z'; c++) {
          int pos = s.indexOf(c);
          if (pos != -1 && pos == s.lastIndexOf(c)) {
              output = Math.min(pos, output);
          }
      }
      return output == Integer.MAX_VALUE ? -1: output;
  }
  
}

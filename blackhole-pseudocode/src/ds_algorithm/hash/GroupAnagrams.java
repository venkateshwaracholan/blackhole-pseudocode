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
import java.util.*;

// https://leetcode.com/problems/group-anagrams/


public class GroupAnagrams {
  
//  Time: O(nklogk) space: O(nk)  n- number of strings k-max length of strings
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap();
    for(int i=0;i<strs.length;i++){
      char[] ch = strs[i].toCharArray();
      Arrays.sort(ch);
      String key = String.valueOf(ch);
      if(!map.containsKey(key)){
        map.put(key,new ArrayList());
      }
      map.get(key).add(strs[i]);
    }
    return new ArrayList(map.values());
  }
  
//  Time: O(nk) space: O(nk)  n- number of strings k-max length of strings
//  we use 26 characters counts(a long sting of # and count) as the key to avoid sorting
//  #1#2#3
  public List<List<String>> groupAnagramsEfficient(String[] strs) {
    Map<String, List<String>> map = new HashMap();
    for(int i=0;i<strs.length;i++){
      int cmap[] = new int[26];
      String str = strs[i] ;
      for(int k=0;k<str.length(); k++){
        cmap[str.charAt(k)-'a']++;
      }
      StringBuilder sb = new StringBuilder();
      for(int k=0;k<26; k++){
        sb.append('#');
        sb.append(cmap[k]);
      }
      String key = sb.toString();
      if(!map.containsKey(key))
        map.put(key,new ArrayList());
      map.get(key).add(strs[i]);
    }
    return new ArrayList(map.values());
  }
  
  public static void main(String args[]){

  }
}

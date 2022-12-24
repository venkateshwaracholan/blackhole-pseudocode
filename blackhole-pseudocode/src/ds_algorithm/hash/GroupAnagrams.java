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
    // approach: sorting and hashing,iterate strings with lop and covert to char array for sorting and String.valueOf for getting key
    // if map does not contain key, put a new arraylist
    // add the strs[i] is map contains the key, return new arraylist of map.values as we dont care abt the order of result
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
    //approach: hashing and int map of character freq as there are only 26 chars
    //  we use 26 characters counts(a long sting of # and count) as the key to avoid sorting
    //  #1#2#3
    public List<List<String>> groupAnagramsCMap(String[] strs) {
        Map<String, List<String>> map = new HashMap();
        for(int i=0;i<strs.length;i++){
            int[] cmap = new int[26];
            for(int k=0;k<strs[i].length();k++)
                cmap[strs[i].charAt(k)-'a']++;
            StringBuilder s = new StringBuilder();
            for(int k=0;k<26;k++)
                s.append('#').append(cmap[k]);
            String key = s.toString();
            if(!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(strs[i]);
        }
        return new ArrayList(map.values());
    }
  
    public static void main(String args[]){

    }
}

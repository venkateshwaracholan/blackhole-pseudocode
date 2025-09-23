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
    
    //APPROACH 1 hashMap Map<String, List<String>>, sorted keys, mapvalues
    //  Time: O(nklogk) space: O(nk)  n- number of strings k-max length of strings
    // approach: sorting and hashing,iterate strings with lop and covert to char array for sorting and String.valueOf for getting key
    // if map does not contain key, put a new arraylist
    // add the strs[i] is map contains the key, return new arraylist of map.values as we dont care abt the order of result
    // abbccc
    public List<List<String>> groupAnagrams(String[] strs) {
        var map = new HashMap<String, List<String>>(strs.length);
        for(String s: strs){
            var chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, (k)-> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
  
    //APPROACH 2 hashMap Map<String, List<String>> + cmap[26], build custom key, mapvalues
    //  Time: O(nk) space: O(nk)  n- number of strings k-max length of strings
    //approach: hashing and int map of character freq as there are only 26 chars
    //  we use 26 characters counts(a long sting of # and count) as the key to avoid sorting
    //  #1#2#3
    public List<List<String>> groupAnagramsCMap(String[] strs) {
        var map = new HashMap<String, List<String>>(strs.length);
        for(String s: strs){
            int[] freq = new int[26];
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<s.length();i++){
                freq[s.charAt(i)-'a']++;
            }
            for(int k=0;k<26;k++){
                sb.append('#').append(freq[k]);
            }
            String key = sb.toString();
            map.computeIfAbsent(key, (k)-> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
  
    public static void main(String args[]){

    }
}

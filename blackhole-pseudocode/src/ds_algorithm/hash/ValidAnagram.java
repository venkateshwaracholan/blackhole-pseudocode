/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/valid-anagram/description/
public class ValidAnagram {
    
    
    //APPROACH 1 hashmap compare freq
    //Time: O(n) space: O(1) 26 eng chars
    //approach: Hashmap, find fre map for both, compare freq maps
    //initially check length and return false if not equal, they are not anagrams
    //Note: smap.getOrDefault(c,0).equals(tmap.getOrDefault(c,0) equals here coz of Object Integer( not int)
    public boolean isAnagramHashMap(String s, String t) {
        if(s.length()!=t.length()) return false;
        Map<Character,Integer> smap = new HashMap();
        Map<Character,Integer> tmap = new HashMap();
        for(int i=0;i<s.length();i++){
            smap.put(s.charAt(i),smap.getOrDefault(s.charAt(i),0)+1);
            tmap.put(t.charAt(i),tmap.getOrDefault(t.charAt(i),0)+1);
        }
        for(char c: smap.keySet()){
            if(!smap.getOrDefault(c,0).equals(tmap.getOrDefault(c,0))) return false;
        }
        return true;
    }
    
    //APPROACH 2 increment frq by s and dec freq by tin 1 loop and check for nonzero for false
    //Time: O(n) space: O(1)
    //approach: Hash, int map, add for one string chars and sub for another, so they cancel each other
    // check map if non zero not an anagram
    // initially check length and return false if not equal, they are not anagrams
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        int[] map = new int[26];
        for(int i=0;i<s.length();i++){
            map[s.charAt(i)-'a']++;
            map[t.charAt(i)-'a']--;
        }
        for(int i: map) if(i!=0) return false;
        return true;
    }
    
    //APPROACH 3 sorting char[] and equals check
    // Sorting solution
    // Time O(nlogn)avg O(n^2)worst space(logn) quick sort
    public boolean isAnagramSort(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        return Arrays.equals(sc,tc);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.sliding_window;

/**
 *
 * @author vshanmugham
 */
import java.util.*;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

public class LongestSubstringWithoutRepeatingChar {
    
    //APPROACH 1 brute => 3 loops 1for checkrepetition, j=i, if no repetition put in max
    
    // Time O(n^3) space: O(n)
    // brute force
    // getting every string combination with 2 loops
    // and checking if it dos not have repeating char an setting to max
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for(int i=0;i<s.length();i++)
            for(int j=i;j<s.length();j++)
                if(!checkRep(s,i,j)) 
                    max = Math.max(max,j-i+1);
        return max;
    }
    public boolean checkRep(String s, int i, int j){
        Set<Character> set = new HashSet();
        for(;i<=j;i++){
            if(set.contains(s.charAt(i))) return true;
            set.add(s.charAt(i));
        }
        return false;
    }
    
    //APPROACH 2 => twopointers +set i=0,j=0, move j, while jchar in set, move i and remove ichar from set
    
    // Time  O(2n) space O(n)
    // approach: 2 pointers, using set
    // while right char is present remove all chars until its nor present in set, 
    // meaning moving i to right until j char is not present
    // axybcba
    // when 2nd b hits, remove axyb using while, thats y 2n
    public int lengthOfLongestSubstring2(String s) {
        int max = 0;
        Set<Character> set = new HashSet();
        for(int i=0,j=0;j<s.length();j++){
            while(set.contains(s.charAt(j)))
                set.remove(s.charAt(i++));
            set.add(s.charAt(j));
            max = Math.max(max, j-i+1);
        }
        return max;
    }
    
    
    //APPROACH 3 => twopointers +map<val,index> i=0,j=0, move j, if jchar in set, move i to max of i,idx of jchar from map
    
    // Time complexity : O(n). Index j will iterate n times.
    // Space complexity : O(min(m,n)). Same as the previous approach.
    // approach: put next index of char into map
    // if map contains key, move i to right max of i or index presnt in map
    //abcba
    // when b hits, bs idx in map is 2,  i=max(0,2)=2
    // when a hits as idx in map is 1    i=max(2,1)=2
    public int lengthOfLongestSubstring3(String s) {
        Map<Character, Integer> map = new HashMap();
        int max = 0;
        for(int i=0,j=0;j<s.length();j++){
            char c = s.charAt(j);
            if (map.containsKey(c))
                i = Math.max(map.get(c),i);
            max = Math.max(max, j-i+1);
            map.put(c, j+1);
        }
        return max;
    }
    // approach: same as above, instead of map using Integer[128]
    // using Integer so that we can use null checks as contains
    public int lengthOfLongestSubstring4(String s) {
        int max = 0;
        Integer[] map = new Integer[128];
        for(int i=0,j=0;j<s.length();j++){
            char c = s.charAt(j);
            if(map[c]!=null)
                i = Math.max(i,map[c]);
            map[c]=j+1;
            max = Math.max(max, j-i+1);
        }
        return max;
    }
  
}

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
    
    
    // Time O(n^3) space: O(n)
    // brute force
    // getting every string combination with 2 loops
    // and checking if it dos not have repeating char an setting to max
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                if(!checkRep(s,i,j)) 
                    max = Math.max(max,j-i+1);
            }
        }
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
    
    
    
    // Time  O(2n) space O(n)
    // approach: 2 pointers, freq map, increase and decrease freq based on incoming char
    // while right char is present remove all chars until its freq becomes 1, 
    // meaning moving i to right until freq of right char becomes 1
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap();
        int max = 0;
        for(int i=0,j=0;j<s.length();j++){
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j),0)+1);
            while(map.get(s.charAt(j))>1){
                map.put(s.charAt(i),map.get(s.charAt(i))-1);
                i++;
            }
            max = Math.max(max, j-i+1);
        }
        return max;
    }
    
    // Time complexity : O(n). Index j will iterate n times.
    // Space complexity : O(min(m,n)). Same as the previous approach.
    // approach: put index of char into map
    // if map contains key, move i to right max of i or index presnt in map
    public int lengthOfLongestSubstring3(String s) {
        Map<Character, Integer> map = new HashMap();
        int max = 0;
        for(int i=0,j=0;j<s.length();j++){
            if (map.containsKey(s.charAt(j)))
                i = Math.max(map.get(s.charAt(j)),i);
            max = Math.max(max, j-i+1);
            map.put(s.charAt(j), j+1);
        }
        return max;
    }
    
    // Time complexity : O(n). Index j will iterate n times.
    // Space complexity : O(1). as we use constant space.
    // approach: same as above, instead of map using Integer[128]
    // using Integer so that we can use null checks as contains
    // if char present, move i to right if is less that new index
    public int lengthOfLongestSubstring4(String s) {
        Integer map[] = new Integer[128];
        int max = 0;
        for(int i=0,j=0;j<s.length();j++){
            if(map[s.charAt(j)]!=null && i<map[s.charAt(j)])
                i=map[s.charAt(j)];
            max = Math.max(max,j-i+1);
            map[s.charAt(j)] = j+1;
        }
        return max;
    }
  
}




/*

 abcdeefg => abcde
 aabcefdaag => abcefd
 
 Only smaller case character 'a' <= char[i] <= 'z'
 
 aabcefdaag
 0123456789
 i
 j
 
 abcdabcd
 
 abcdecgkl
 0123456789
 i
      j
 
 max_start = 1
 max_end = 6

 
b
c
d
e
a 5

abcdecgkl

longest_unique_subsequence(String str){

  int max_start = 0, max_end = 0;
  int map[] = new int[26];
  for(int i=0;i<26;i++){
     map[i] = 0;
  }
  int j = 0;
  for(int j=0;j<str.length();j++){
      char c = str.charAt(j);
      if(map[c-'97']==0){
          map[c-'97'] = 1;
          if((max_end-max_start)<(j-i)){
              max_start = i;
              max_end = j;
          }
      }else{
          i++;
          
          j--;
      }
  }
  
}



*/
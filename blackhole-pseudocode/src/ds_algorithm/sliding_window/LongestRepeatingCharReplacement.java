/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.sliding_window;

/**
 *
 * @author venka
 */

import java.util.*;

// https://leetcode.com/problems/longest-repeating-character-replacement/description/

public class LongestRepeatingCharReplacement {
    
    //APPROACH 1 => 2loops inner loop twopointers i=0,j=0, move j, while jchar==ichar inc count, move i when j-i+1>c+k, window>allowed
    // TLE
    // Time complexity: O(n*⁡n) space: O(1) 26 chars
    public int characterReplacement(String s, int k) {
        int max = 0;
        for(int x=0;x<s.length();x++){
            int c = 0;
            for(int i=0,j=0;j<s.length();j++){
                if(s.charAt(x)==s.charAt(j)) c++;
                if(j-i+1>c+k)
                    if(s.charAt(x)==s.charAt(i++)) c--;
                max = Math.max(max,j-i+1);
            }
        }
        return max;
    }
    
    //APPROACH 2 => ouerloop running for only max 26 chars,2loops inner loop twopointers i=0,j=0, move j, while jchar==ichar inc count, move i when j-i+1>c+k, window>allowed
    // Time complexity: O(nm)
    // Space complexity: O(m) m is 26
    // approach: iterate each character and slide a valid window
    // valid window is j-i+1=window, valid wndow = window - count <=k
    // if beyond valid window size, reduce window size by increasing i
    // and removing char at i if its the letter
    public int characterReplacement2(String s, int k) {
        Set<Character> letters = new HashSet();
        int longest = 0;
        for(int i=0;i<s.length();i++) letters.add(s.charAt(i));
        for(char l: letters){
            int c = 0;
            for(int i=0,j=0;j<s.length();j++){
                if(l==s.charAt(j))c++;
                if((j-i+1)>c+k)
                    if(l==s.charAt(i++)) c--;
                longest = Math.max(j-i+1, longest);
            }
        }
        return longest;
    }
 
    //APPROACH 3 =>  int[] freq map + 1 twopointer loop i=0,j=0, move j and put in map, get maxf, move i when j-i+1>maxf+k, window>allowed
    
    // Time complexity: O(n)
    // Space complexity: O(m) m is 26
    // approach: sliding window, freq map
    // build freq map, acc max freq
    // valid window is j-i+1=window, valid wndow = window - maxf <=k
    // if window becomes invalid, reduce window size and corresponding cahr from map, i++
    // accumulate longest
    public int characterReplacement4(String s, int k) {
        int max = 0;
        int[] map = new int[26];
        int maxf = 0;
        for(int i=0,j=0;j<s.length();j++){
            map[s.charAt(j)-'A']++;
            maxf = Math.max(maxf,map[s.charAt(j)-'A']);
            if(j-i+1>maxf+k)
                map[s.charAt(i++)-'A']--;
            max = Math.max(max,j-i+1);
        }
        return max;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //unwanated
    //Time complexity: O(nlog⁡n) space: O(1) 26 chars
    // approach: binary search use mid to dictate widow size
    // then slide window to see if substring can be made, using freq int map, 
    // accumulate max freq, and check if enuf freq present for windowsize with k replacements
    // if true, then we can move lo right so that we can check if hgher window is possible
    // else move r to mid to decrease window size
    // binary bounds => lo=0, hi = s.length()+1 (required to make window size go upto n length)
    // while(lo+1<hi) lo<hi will make infinite loop
    //
    public int characterReplacement3(String s, int k) {
        int l=0,r=s.length()+1;
        while(l+1<r){
            int mid = l+(r-l)/2;
            if(characterReplacement(s,mid,k))l=mid;
            else r=mid;
        }
        return l;
    }
    
    public boolean characterReplacement(String s, int w, int k) {
        int[] map = new int[26];
        int maxf = 0;
        for(int i=0,j=0;j<s.length();j++){
            map[s.charAt(j)-'A']++;
            maxf = Math.max(maxf,map[s.charAt(j)-'A']);
            if(j-i+1>maxf+k)
                map[s.charAt(i++)-'A']--;
            if(w<=maxf+k) return true;
        }
        return false;
    }
    
}

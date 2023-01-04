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
    //Time complexity: O(nlog⁡n) space: O(1) 26 chars
    // approach: binary search use mid to dictate widow size
    // then slide window to see if substring can be made, using freq int map, 
    // accumulate max freq, and check if enuf freq present for windowsize with k replacements
    // if true, then we can move lo right so that we can check if hgher window is possible
    // else move r to mid to decrease window size
    // binary bounds => lo=0, hi = s.length()+1 (required to make window size go upto n length)
    // while(lo+1<hi) lo<hi will make infinite loop
    //
    public int characterReplacement(String s, int k) {
        int lo = 0, hi = s.length()+1;
        while(lo+1<hi){
            int mid = lo + (hi-lo)/2;
            if(canMakeSubstring(s,mid,k)) lo=mid;
            else hi = mid;
        }
        return lo;
    }

    public boolean canMakeSubstring(String s, int windowSize, int k) {
        int freq[] = new int[26], maxf = 0;
        for(int i=0,j=0;j<s.length();j++){
            freq[s.charAt(j)-'A']++;
            if(j-i>=windowSize){
                freq[s.charAt(i)-'A']--;
                i++;
            }
            maxf = Math.max(maxf, freq[s.charAt(j)-'A']);
            if(windowSize-maxf <= k) return true;
        }
        return false;
    }
    
    
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
        for(char c: letters){
            int freq[] = new int[26], count = 0;
            for(int i=0,j=0;j<s.length();j++){
                if(c==s.charAt(j))count++;
                if((j-i+1-count)>k){
                    if(c==s.charAt(i)) count--;
                    i++;
                }
                longest = Math.max(j-i+1, longest);
            }
        }
        return longest;
    }
    
    // Time complexity: O(n)
    // Space complexity: O(m) m is 26
    // approach: sliding window, freq map
    // build freq map, acc max freq
    // valid window is j-i+1=window, valid wndow = window - maxf <=k
    // if window becomes invalid, reduce window size and corresponding cahr from map, i++
    // accumulate longest
    public int characterReplacement3(String s, int k) {
        int longest = 0,maxf = 0;
        int freq[] = new int[26];
        for(int i=0,j=0;j<s.length();j++){
            freq[s.charAt(j)-'A']++;
            maxf = Math.max(maxf, freq[s.charAt(j)-'A']);
            if((j-i+1-maxf)>k){
                freq[s.charAt(i)-'A']--;
                i++;
            }
            longest = Math.max(j-i+1, longest);
        }
        return longest;
    }
    
}

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
import ds_algorithm.Pair;

// https://leetcode.com/problems/minimum-window-substring/description/

public class MinimumWindowSubstring {

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Brute Force: build tCount[128] with count, for every substring (iterate i=0,j=i→n-1), inner loop expand j updating sCount[jchar]++, valid window = sCount has at least tCount chars (checked by iterating all 128 chars in contains method), update min if j-i+1<min → O(n²*128), O(128).
    * Sliding Window (HashMap): build tMap with count, iterate i=0,j=0→n-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tMap[jchar], while (valid window = formed==tMap.size()) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tMap[ichar], update min if j-i+1<min → O(n), O(|S|+|T|).
    * Filtered Sliding Window (HashMap): build tMap with count, filter s into record(ch,idx) containing only t chars, iterate i=0,j=0→filtered.size()-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tMap[jchar], while (valid window = formed==tMap.size()) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tMap[ichar], update min if filtered[j].idx-filtered[i].idx+1<min → O(n), O(|S|+|T|).
    * Sliding Window (int[]): build tCount[128] with count, track uniqT, iterate i=0,j=0→n-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tCount[jchar], while (valid window = formed==uniqT) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tCount[ichar], update min if j-i+1<min → O(n), O(128).
    * Filtered Sliding Window (int[]): build tCount[128] with count, track uniqT, filter s into record(ch,idx) containing only t chars, iterate i=0,j=0→idxLen-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tCount[jchar], while (valid window = formed==uniqT) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tCount[ichar], update min if filtered[j].idx-filtered[i].idx+1<min → O(n), O(128).
    */


    /*
    * ONE LINER => Brute Force: build tCount[128] with count, for every substring (iterate i=0,j=i→n-1), inner loop expand j updating sCount[jchar]++, valid window = sCount has at least tCount chars (checked by iterating all 128 chars in contains method), update min if j-i+1<min → O(n²*128), O(128).
    *
    * Approach: Brute Force with frequency arrays
    * - Count frequency of each character in t → tCount[128].
    * - For every possible start index i in s:
    *     - Expand end index j, building sCount[128] for substring s[i..j].
    *     - After each expansion, check if sCount covers tCount (contains()).
    *     - If valid and window is smaller, update answer.
    * - contains(): compares two frequency arrays, returns true if sCount has at least as many of every char as tCount.
    *
    * Time Complexity: O(n^2 * 128) 
    *   - Outer loop over i, inner loop over j, each validation is O(128).
    * Space Complexity: O(128) per window count + O(128) for tCount.
    *
    * Rationale: Simple brute force – generate all substrings, validate via frequency counts.
    * Inefficient for large input, but correctness is straightforward.
    */
    public String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        int[] tCount = new int[128];
        String ans = "";
        for(char c: t.toCharArray()){
            tCount[c]++;
        }
        for(int i=0;i<s.length();i++){
            int[] sCount = new int[128];
            for(int j=i;j<s.length();j++){
                sCount[s.charAt(j)]++;
                if(contains(sCount, tCount) && j-i+1<min){
                    min = j-i+1;
                    ans = s.substring(i,j+1);
                }
            }
        }
        return ans;
    }
    public boolean contains(int[] s, int[] t){
        for(int i=0;i<128;i++){
            if(t[i]>s[i]){
                return false;
            }
        }
        return true;
    }

    
    
    /*
    * ONE LINER => Sliding Window (HashMap): build tMap with count, iterate i=0,j=0→n-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tMap[jchar], while (valid window = formed==tMap.size()) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tMap[ichar], update min if j-i+1<min → O(n), O(|S|+|T|).
    *
    * Approach 1: Optimized Sliding Window with Hash Maps
    * - Build frequency map tMap for characters in t.
    * - Use two pointers (i, j) to maintain a sliding window in s.
    * - Expand window by moving j and update sMap with character counts.
    * - Maintain a formed count: number of characters in window matching required frequency in tMap.
    * - When formed == tMap.size(), window contains all required characters:
    *     - Shrink window from left (i) to minimize size while still containing all characters.
    *     - Update min window length and start position when a smaller valid window is found.
    * - Continue until j reaches end of s.
    * - Build result substring once at the end (s.substring(start, start+min)) to avoid
    *   costly substring creation inside loop.
    * Time & Space: O(n) — each char visited at most twice, O(|S|+|T|) — for frequency maps.
    * Rationale: Efficiently finds smallest substring containing all chars of t
    * using sliding window and frequency tracking.
    */
    public String minWindowTwoPointers(String s, String t) {
        int min = Integer.MAX_VALUE, start=0;
        var tMap = new HashMap<Character, Integer>();
        var sMap = new HashMap<Character, Integer>();
        // String ans = "";
        for(char c: t.toCharArray()){
            tMap.merge(c,1,Integer::sum);
        }
        for(int i=0,j=0, formed=0;j<s.length();j++){
            char jc = s.charAt(j);
            sMap.merge(jc, 1, Integer::sum);
            if(tMap.containsKey(jc) && sMap.get(jc).equals(tMap.get(jc))){
                formed++;
            }
            while(formed==tMap.size()){
                if(j-i+1<min){
                    min = j-i+1;
                    start = i;
                    // ans = s.substring(i,j+1);
                }
                char ic = s.charAt(i);
                sMap.merge(ic,-1,Integer::sum);
                if(tMap.containsKey(ic) && sMap.get(ic).intValue()<tMap.get(ic).intValue()){
                    formed--;
                }
                i++;
            }
        }
        if(min>s.length()){
            return "";
        }
        return s.substring(start, start+min);
    }
    
    /*
    * ONE LINER => Filtered Sliding Window (HashMap): build tMap with count, filter s into record(ch,idx) containing only t chars, iterate i=0,j=0→filtered.size()-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tMap[jchar], while (valid window = formed==tMap.size()) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tMap[ichar], update min if filtered[j].idx-filtered[i].idx+1<min → O(n), O(|S|+|T|).
    *
    * Approach 2: Filtered Sliding Window with IndexedChar record
    * - Build frequency map tMap for characters in t.
    * - Preprocess s into a filtered list containing only characters present in t,
    *   storing both the character and its original index using IndexedChar record.
    * - Use two pointers (i, j) to maintain a sliding window over the filtered list.
    * - Expand window by moving j and updating sMap with character counts.
    * - Maintain a formed count: number of characters in window matching required frequency in tMap.
    * - When formed == tMap.size():
    *     - Shrink window from left (i) to minimize size while still containing all characters.
    *     - Update min length and starting index when a smaller valid window is found.
    * - Construct result substring once at the end using s.substring(start, start+min) for efficiency.
    *
    * Time & Space: O(n) — filtering and sliding window each iterate s at most once;
    *                 O(|S| + |T|) — for filtered list and frequency maps.
    *
    * Trade‑off: Filtering reduces iterations over irrelevant characters,
    *            but adds preprocessing and extra memory usage;
    *            actual runtime improvement depends on input density.
    *
    * Rationale: Efficiently finds the smallest substring containing all characters of t
    * using a filtered sliding window to skip irrelevant characters.
    */
    public String minWindowFiltered(String s, String t) {
        int min = Integer.MAX_VALUE, start=0;
        var tMap = new HashMap<Character, Integer>();
        var sMap = new HashMap<Character, Integer>();
        record IndexedChar(char ch, int idx){}
        var filtered = new ArrayList<IndexedChar>();
        for(char c: t.toCharArray()){
            tMap.merge(c,1,Integer::sum);
        }
        for(int i=0;i<s.length();i++){
            if(tMap.containsKey(s.charAt(i))){
                filtered.add(new IndexedChar(s.charAt(i), i));
            }
        }
        for(int i=0,j=0, formed=0;j<filtered.size();j++){
            char jc = filtered.get(j).ch();
            sMap.merge(jc, 1, Integer::sum);
            if(tMap.containsKey(jc) && sMap.get(jc).equals(tMap.get(jc))){
                formed++;
            }
            while(formed==tMap.size()){
                int l = filtered.get(i).idx();
                int r = filtered.get(j).idx();
                if(r-l+1<min){
                    min = r-l+1;
                    start = l;
                }
                char ic = filtered.get(i).ch();
                sMap.merge(ic,-1,Integer::sum);
                if(tMap.containsKey(ic) && sMap.get(ic).intValue()<tMap.get(ic).intValue()){
                    formed--;
                }
                i++;
            }
        }
        if(min>s.length()){
            return "";
        }
        return s.substring(start, start+min);
    }
    
    
    
    
    
    
    
    
    
    
  
    
    
    // we can use int[] instead of map with above 2 approaches
    //same as above usng int[] intead of map

    /* ONE LINER => Sliding Window (int[]): build tCount[128] with count, track uniqT, iterate i=0,j=0→n-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tCount[jchar], while (valid window = formed==uniqT) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tCount[ichar], update min if j-i+1<min → O(n), O(128).
    */
    public String minWindowTwoPointersArrayMap(String s, String t) {
        int min = Integer.MAX_VALUE;
        int start = 0, uniqT = 0;
        int[] tMap = new int[128];
        int[] sMap = new int[128];
        for(char c: t.toCharArray()){
            if(tMap[c]==0){
                uniqT++;
            }
            tMap[c]++;
        }
        for(int i=0, j=0, formed = 0;j<s.length();j++){
            char jc = s.charAt(j);
            sMap[jc]++;
            if(tMap[jc]!=0 && sMap[jc]==tMap[jc]){
                formed++;
            }
            while(formed == uniqT){
                if(j-i+1<min){
                    min = j-i+1;
                    start = i;
                }
                char ic = s.charAt(i);
                sMap[ic]--;
                if(tMap[ic]!=0 && sMap[ic]<tMap[ic]){
                    formed--;
                }
                i++;
            }
        }
        if(min>s.length()){
            return "";
        }
        return s.substring(start, start+min);
    }
    /* ONE LINER => Filtered Sliding Window (int[]): build tCount[128] with count, track uniqT, filter s into record(ch,idx) containing only t chars, iterate i=0,j=0→idxLen-1, expand j updating sMap[jchar]++ and formed++ if sMap[jchar]==tCount[jchar], while (valid window = formed==uniqT) shrink i reducing sMap[ichar]-- and formed-- if sMap[ichar]<tCount[ichar], update min if filtered[j].idx-filtered[i].idx+1<min → O(n), O(128).
    */
    
    public String minWindowFilteredArrayMap(String s, String t) {
        int min = Integer.MAX_VALUE;
        int start = 0, uniqT = 0;
        int[] tMap = new int[128];
        int[] sMap = new int[128];
        record Index(char ch, int idx){}
        Index[] filtered = new Index[s.length()];
        for(char c: t.toCharArray()){
            if(tMap[c]==0){
                uniqT++;
            }
            tMap[c]++;
        }
        int idxLen = 0;
        for(int i=0;i<s.length();i++){
            if(tMap[s.charAt(i)]!=0){
                filtered[idxLen] = new Index(s.charAt(i), i);
                idxLen++;
            }
        }
        for(int i=0, j=0, formed = 0;j<idxLen;j++){
            char jc = filtered[j].ch();
            sMap[jc]++;
            if(tMap[jc]!=0 && sMap[jc]==tMap[jc]){
                formed++;
            }
            while(formed == uniqT){
                int l = filtered[i].idx();
                int r = filtered[j].idx();
                if(r-l+1<min){
                    min = r-l+1;
                    start = l;
                }
                char ic = filtered[i].ch();
                sMap[ic]--;
                if(tMap[ic]!=0 && sMap[ic]<tMap[ic]){
                    formed--;
                }
                i++;
            }
        }
        if(min>s.length()){
            return "";
        }
        return s.substring(start, start+min);
    }
}

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

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Brute Nested Match : For each char in s, linearly search t (as char[]) and mark match → O(n²).
    * Sorting Approach   : Sort both strings and compare arrays → O(n log n).
    * HashMap Count      : Build frequency maps for s and t, compare counts → O(n).
    * Array Count (Opt)  : Use int[26], increment for s and decrement for t, all zeros ⇒ anagram → O(n).
    */

    
    /*
    * ONE LINER => For each char in s, find and mark a matching char in t using O(n²) nested loops.
    *
    * Approach BRUTE: Nested Matching with Marker
    * - If lengths differ → return false.
    * - Convert string t into a mutable char array (so we can "mark" used characters).
    * - For each character in s:
    *     - Linearly scan through array t to find an unused matching character.
    *     - If found, mark it as used (e.g., replace with sentinel like '#').
    *     - Use break to stop after first match (avoids consuming multiple matches for one char).
    *     - If no match found → return false.
    * - If all characters in s find a match → return true.
    *
    * Time Complexity: O(n²) — nested search for each character.
    * Space Complexity: O(n) — char array for t.
    *
    * Rationale:
    * - Very brute force, no sorting or hashing, just direct matching.
    * - Works but extremely slow for long strings.
    * - char[] is necessary since Java Strings are immutable (cannot mark characters directly).
    * - break is necessary to ensure only one occurrence of a matching character is used per iteration.
    *
    * Example:
    *   s = "ab", t = "ba" → match 'a' with t[1], mark it, then match 'b' with t[0] → returns true.
    *   s = "ab", t = "cd" → no matches found → returns false.
    */
    public boolean isAnagramBrute(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        char[] c = t.toCharArray();
        for(int i=0;i<s.length();i++){
            boolean found = false;
            for(int j = 0;j<t.length();j++){
                if(s.charAt(i)==c[j]){
                    found = true;
                    c[j]='#';
                    break;
                }
            }
            if(!found){
                return false;
            }
        }
        return true;
    }
    
    /*
    * ONE LINER => Sort both strings and compare equality to check if they are anagrams.
    *
    * Approach BRUTE: Sorting + Comparison
    * - If lengths differ → immediately return false (anagrams must have equal length).
    * - Convert both strings to character arrays.
    * - Sort both arrays.
    * - Compare the sorted arrays:
    *     - If identical → strings are anagrams → return true.
    *     - Else → return false.
    *
    * Time Complexity: O(n log n) — dominated by sorting both strings.
    * Space Complexity: O(n) — for storing character arrays (extra space for sort implementation as well).
    *
    * Rationale:
    * - Brute force way to check anagrams by normalizing order of characters.
    * - Not optimal but conceptually simple and works reliably.
    *
    * Example:
    *   s = "anagram", t = "nagaram" → after sorting both = "aaagmnr" → returns true.
    *   s = "rat", t = "car" → "art" vs "acr" → returns false.
    */  
    public boolean isAnagramSort(String s, String t) {
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        return Arrays.equals(sc,tc);
    }
    
    
    /*
    * ONE LINER => Build frequency maps for s and t using HashMaps and compare counts for all characters.
    *
    * Approach: HashMap Frequency Count
    * - If lengths differ → return false immediately (quick fail).
    * - Create two HashMaps:
    *     - freqS → character frequencies in s
    *     - freqT → character frequencies in t
    * - Traverse both strings in one pass:
    *     - For each character c in s and t:
    *         - Use `Map.compute(key, (k,v) -> ...)` to increment frequency:
    *             - If v == null → first occurrence, set to 1.
    *             - Else → increment existing count.
    * - After building both maps:
    *     - Iterate over keys in freqS.
    *     - If freqT doesn't have the same count for a character → return false.
    * - If all match → return true.
    *
    * Why `compute`?
    * - Compact way to handle "if absent, put 1, else increment".
    * - Avoids boilerplate like:
    *     map.put(c, map.getOrDefault(c, 0) + 1);
    *
    * Time Complexity: O(n) — single pass to build maps + pass to compare.
    * Space Complexity: O(n) — frequency storage (at most O(26) for lowercase letters, but O(n) in general case).
    *
    * Example:
    *   s = "anagram", t = "nagaram"
    *   freqS = {a=3, n=1, g=1, r=1, m=1}
    *   freqT = {n=1, a=3, g=1, r=1, m=1}
    *   → All counts match → return true.
    *
    *   s = "rat", t = "car"
    *   freqS = {r=1, a=1, t=1}
    *   freqT = {c=1, a=1, r=1}
    *   → 't' not in freqT → return false.
    */
    public boolean isAnagramHashMap(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        Map<Character,Integer> freqS= new HashMap<>(), freqT = new HashMap<>();
        for(int i=0;i<s.length();i++){
            freqS.compute(s.charAt(i),(k,v)-> v==null ? 1 : v+1);
            freqT.compute(t.charAt(i),(k,v)-> v==null ? 1 : v+1);
        }
        for(char k: freqS.keySet()){
            if(!freqS.get(k).equals(freqT.get(k))){
                return false;
            }
        }
        return true;
    }
    
    /*
    * ONE LINER => Use a fixed-size int[26], increment for s and decrement for t, all zeros ⇒ anagram → O(n).
    *
    * Approach: Fixed-Array Frequency Count (Optimized)
    * - If lengths differ → return false immediately.
    * - Create an int[26] array `map` for letter frequencies ('a' → index 0, ..., 'z' → index 25).
    * - Traverse both strings in one pass:
    *     - For each i:
    *         - Increment map[s.charAt(i) - 'a'] (for s’s character).
    *         - Decrement map[t.charAt(i) - 'a'] (for t’s character).
    * - After the loop:
    *     - If s and t are anagrams, every increment will be canceled by a decrement → all entries == 0.
    *     - If any entry ≠ 0 → mismatch → return false.
    *
    * Why is this optimal?
    * - Uses **constant space** (array of length 26, independent of input size).
    * - Performs **one traversal** of both strings (O(n)), no sorting or HashMaps needed.
    * - Leverages the constraint that inputs only contain lowercase English letters.
    *
    * Time Complexity: O(n) — single scan over strings + final 26 check.
    * Space Complexity: O(1) — fixed 26-element array.
    *
    * Example:
    *   s = "anagram", t = "nagaram"
    *   map updates cancel out → all zeros → return true.
    *
    *   s = "rat", t = "car"
    *   mismatch at 't' and 'c' → map has non-zero values → return false.
    */
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int[] map = new int[26];
        for(int i=0;i<s.length();i++){
            map[s.charAt(i)-'a']++;
            map[t.charAt(i)-'a']--;    
        }
        for(int i: map){
            if(i!=0){
                return false;
            }
        }
        return true;
    }
    
    
}

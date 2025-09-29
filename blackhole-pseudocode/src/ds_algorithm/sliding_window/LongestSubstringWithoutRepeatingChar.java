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

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Brute Force: for each substring check repeats (iterate i in (0, n-1), iterate j in (i, n-1)), check repetition in s[i..j] using set, update max if uniq and j-i+1>max → O(n³), O(n).
    * Sliding Window + HashSet: iterate i = 0, j = i to n-1, shrink window while duplicate char at j by moving i and removing s[i] from set, add s[j] to set, update max → keeps unique chars in window → O(2n), O(min(n, charset)).
    * Sliding Window + HashMap(val,index): iterate i = 0, j = i to n-1, if s[j] seen in lastIndex map after i pos, skip i to that pos skipping processing all other characters, store last seen index of s[j] in map, update max if j-i+1>max→ O(n), O(min(n, charset)).
    * Sliding Window + Integer[256](asciiVal, pos): iterate i = 0, j = i to n-1, if s[j] seen in lastIndex array after i pos, skip i to that pos skipping processing all other characters, store last seen index of s[j], update max if j-i+1>max→ faster for ASCII → O(n), O(256).  
    */

    
    /*
    * ONE LINER => Brute Force: for each substring check repeats (iterate i in (0, n-1), iterate j in (i, n-1)), check repetition in s[i..j] using set, update max if uniq and j-i+1>max → O(n³), O(n).
    *
    * Approach 1: Brute-force
    * - Check all substrings s[i..j] of the input string.
    * - Use a HashSet to check if the substring contains duplicate characters.
    * - If no duplicates, update max length.
    *
    * Time Complexity: O(n^3)
    *   - Outer loop over i: O(n)
    *   - Inner loop over j: O(n)
    *   - rep() checks substring uniqueness: O(n)
    * Space Complexity: O(n) for the HashSet in rep()
    *
    * Note: Works correctly but inefficient for large inputs.
    */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (!rep(s, i, j)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // Helper to check if substring s[i..j] contains any repeating characters
    public boolean rep(String s, int i, int j) {
        var set = new HashSet<Character>();
        for (; i <= j; i++) {
            if (set.contains(s.charAt(i))) {
                return true;
            }
            set.add(s.charAt(i));
        }
        return false;
    }
    
    /*
    * ONE LINER => Sliding Window + HashSet: iterate i = 0, j = i to n-1, shrink window while duplicate char at j by moving i and removing s[i] from set, add s[j] to set, update max → keeps unique chars in window → O(2n), O(min(n, charset)).
    *
    * Sliding Window + HashSet:
    * - Two pointers i (window start) and j (window end) track the current substring with unique chars.
    * - Both i and j start at 0 to correctly handle substrings of length 1.
    * - HashSet stores characters in the current window.
    * - While s[j] is already in the set, shrink the window from the left:
    *     - Remove s[i] from the set and increment i.
    *     - Repeat until s[j] is no longer in the set.
    *     - This handles cases where multiple duplicates exist behind j and ensures
    *       the window always contains only unique characters.
    * - After the while loop, add s[j] to the set and update max length: max = Math.max(max, j-i+1)
    *
    * Time: O(2n) → each character is added once (when j moves) and removed once (when i moves),
    * Space: O(min(n, charset)) → window stores unique chars
    */
    public int lengthOfLongestSubstringTwoPointerSet(String s) {
        int max = 0;
        var set = new HashSet<Character>();
        for(int i=0,j=i;j<s.length();j++){
            while(set.contains(s.charAt(j))){
                set.remove(s.charAt(i));
                i++;
            }
            max = Math.max(max, j-i+1);
            set.add(s.charAt(j));
        }
        return max;
    }
    
    
    /*
    * ONE LINER => Sliding Window + HashMap(val,index): iterate i = 0, j = i to n-1, if s[j] seen in lastIndex map after i pos, skip i to that pos skipping processing all other characters, store last seen index of s[j] in map, update max if j-i+1>max→ O(n), O(min(n, charset)).
    *
    * Sliding Window + HashMap (Optimized O(n)):
    * - lastIndex stores last seen index of each character.
    * - i = start of window, j = end pointer.
    * - For s[j]:
    *     - If s[j] seen in current window (lastIndex[c] >= i), jump i to lastIndex[c]+1.
    *     - Update lastIndex[c] = j.
    *     - Update max = Math.max(max, j-i+1).
    * - Rationale: jump i directly past duplicates → true O(n), window always unique.
    * - Works for all chars (Unicode), unlike int[] for ASCII.
    *
    * Time: O(n) → each character is processed at most once for j and i jumps exactly once per duplicate.
    * Space: O(min(n, charset)) → store last seen index of unique characters.
    */
    public int lengthOfLongestSubstringOptimizedMap(String s) {
        int max = 0;
        var lastIndex = new HashMap<Character,Integer>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= i) {
                i = lastIndex.get(c) + 1;
            }
            lastIndex.put(c, j);
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
    /*
    * ONE LINER => Sliding Window + Integer[256](asciiVal, pos): iterate i = 0, j = i to n-1, if s[j] seen in lastIndex array after i pos, skip i to that pos skipping processing all other characters, store last seen index of s[j], update max if j-i+1>max→ faster for ASCII → O(n), O(256).  
    *
    * Approach: Sliding Window using Integer[128] (like map for ASCII)
    * - lastIndex[c] stores last seen index of character c.
    * - null indicates character not seen yet (replaces containsKey).
    * - int[] wouldn't work because default 0 can't distinguish unseen vs seen at index 0.
    * - i = start of window, j = end pointer.
    * - For s[j]:
    *     - If lastIndex[c] != null and >= i → duplicate, jump i = lastIndex[c]+1.
    *     - Update lastIndex[c] = j.
    *     - Update max = Math.max(max, j-i+1).
    * - Time: O(n), Space: O(128) fixed for ASCII.
    */
    public int lengthOfLongestSubstring4(String s) {
        int max = 0;
        var lastIndex = new Integer[256];
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (lastIndex[c]!=null && lastIndex[c] >= i) {
                i = lastIndex[c] + 1;
            }
            lastIndex[c] = j;
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
  
}

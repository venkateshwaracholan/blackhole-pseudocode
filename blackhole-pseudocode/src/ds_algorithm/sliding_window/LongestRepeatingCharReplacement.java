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


    /*
    * ONE-LINERS — Quick Reference:
    *
    * Brute Force: for each substring s[i..j] (iterate i in (0, n-1), iterate j in (i, n-1)), iterate all chars to find max frequency, if j-i+1(window length) ≤ maxFreq+k(valid window = count+mistakes) update max → O(n³) for constant charset, O(n⁴) if counting all substring chars, O(1) space.
    * Brute Sliding Window by char: for each character at x (iterate x in (0, n-1)), iterate i = 0, j = 0 to n-1, track count of matches s[x]==s[j], shrink window if j-i+1(window length)>c+k (valid window = count+mistakes) by moving i and decrementing c if s[i]==s[x], update max if j-i+1>max → check all chars → O(n²), O(1).
    * Sliding Window by unique char: collect letters set, for each l in letters set, iterate i = 0, j = i to n-1, track count of matches letter==s[j], shrink window if j-i+1(window length)>c+k (valid window = count+mistakes) by moving i and decrementing c if s[i]==l, update max if j-i+1>max → avoids redundant checks → O(n×U), O(U).
    * Optimized Sliding Window with array and maxf: iterate i = 0, j = i to n-1, increment map[c] for s[j], track maxf = max frequency in window, shrink window if j-i+1(window length)>maxf+k (valid window = max frequency+mistakes) by moving i and decrementing map[s[i]], update max if j-i+1>max → track most frequent char dynamically → O(n), O(26).
    */


    /*
    * ONE LINER => Brute Force: for each substring s[i..j] (iterate i in (0, n-1), iterate j in (i, n-1)), iterate all chars to find max frequency, if j-i+1(window length) ≤ maxFreq+k(valid window = count+mistakes) update max → O(n³) for constant charset, O(n⁴) if counting all substring chars, O(1) space.
    *
    * Approach: Pure Brute Force without frequency map
    * - For every substring s[i..j] (iterate i in (0, n-1), iterate j in (i, n-1)):
    *     - For each possible character c ('A' to 'Z'):
    *         - Count occurrences of c in the substring s[i..j].
    *         - Track maxFreq = maximum frequency among all characters in this substring.
    *     - A valid window = window length (j-i+1) ≤ maxFreq + k (count+mistakes).
    *     - If valid, update max = Math.max(max, j-i+1).
    *
    * Time Complexity: O(n³) when character set size is constant (like 26 for uppercase letters).
    *                  → Outer loops i,j: O(n²), inner character count: O(n×|charset|).
    *                  → If not iterating only distinct charset and instead scanning all substring chars,
    *                    complexity can grow up to O(n⁴).
    * Space Complexity: O(1) extra space (no frequency map or array used).
    *
    * This is the most naive approach. Works correctly but is far less efficient than sliding window optimizations.
    */

    public int characterReplacementBrute(String s, int k) {
        int n = s.length();
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int maxFreq = 0;

                for (char c = 'A'; c <= 'Z'; c++) { // iterate possible chars
                    int count = 0;
                    for (int p = i; p <= j; p++) {
                        if (s.charAt(p) == c) count++;
                    }
                    maxFreq = Math.max(maxFreq, count);
                }

                int windowLength = j - i + 1;
                if (windowLength <= k+maxFreq) {
                    max = Math.max(max, windowLength);
                }
            }
        }

        return max;
    }

    
    /*
    *  ONE LINER => Brute Sliding Window by char: for each character at x (iterate x in (0, n-1)), iterate i = 0, j = 0 to n-1, track count of matches s[x]==s[j], shrink window if j-i+1(window length)>c+k (valid window = count+mistakes) by moving i and decrementing c if s[i]==s[x], update max if j-i+1>max → check all chars → O(n²), O(1).
    *
    * Approach: Brute Sliding Window by character index
    * - For each character s[x] in string:
    *     - Use a sliding window [i, j] to track substring where 
    *       up to k replacements turn all characters to s[x].
    *     - A valid window = window size (j-i+1) ≤ c+k (non-matching chars ≤ k).
    *     - c = count of matching characters in current window.
    *     - If window size (j-i+1) > c+k → window invalid, shrink from left (i++).
    * - Keep track of maximum valid window size found.
    *
    * Time Complexity: O(n^2) in worst case (checking all positions for all characters).
    * Space Complexity: O(1) extra space.
    *
    * This works but is slower than the optimal single-pass sliding window approach.
    */
    public int characterReplacement(String s, int k) {
        int max = 1;
        for(int x=0;x<s.length();x++){
            int c = 0;
            for(int i=0,j=0;j<s.length();j++){
                if(s.charAt(x)==s.charAt(j)){
                    c++;
                }
                if(j-i+1>c+k){
                    if(s.charAt(x)==s.charAt(i)){
                        c--;
                    }
                    i++;
                }
                max = Math.max(max, j-i+1);
            }
        }
        return max;
    }
    
    /*
    *  ONE LINER => Sliding Window by unique char: collect letters set, for each l in letters set, iterate i = 0, j = i to n-1, track count of matches letter==s[j], shrink window if j-i+1(window length)>c+k (valid window = count+mistakes) by moving i and decrementing c if s[i]==l, update max if j-i+1>max → avoids redundant checks → O(n×U), O(U).
    *
    * Approach: Brute Sliding Window by Unique Character
    * - First, collect all unique characters in the string (letters set).
    * - For each unique character 'l':
    *     - Use a sliding window [i, j] to track substrings where up to k replacements 
    *       make all characters equal to 'l'.
    *     - c = count of matching characters in the current window.
    *     - A valid window satisfies: window size (j-i+1) ≤ c+k.
    *     - If window invalid (j-i+1 > c+k), shrink window from the left (i++).
    *     - If removed char matches 'l', decrement c.
    * - Keep track of the maximum valid window size.
    *
    * Time Complexity: O(n + U × n) where U = number of unique characters in s (≤ 26 for letters).
    *                  Effectively O(n) since U is bounded by a constant.
    * Space Complexity: O(U) for storing unique characters.
    *
    * Note: This reduces redundant checks compared to the pure brute-force version,
    *       making it more efficient for strings with limited unique characters.
    */
    public int characterReplacement2(String s, int k) {
        int max = 1;
        var letters = new HashSet<Character>();
        for(int i=0;i<s.length();i++){
            letters.add(s.charAt(i));
        }
        for(char l: letters){
            int c = 0;
            for(int i=0,j=0;j<s.length();j++){
                if(l==s.charAt(j)){
                    c++;
                }
                if(j-i+1>c+k){
                    if(l==s.charAt(i)){
                        c--;
                    }
                    i++;
                }
                max = Math.max(max, j-i+1);
            }
        }
        return max;
    }
 
    /*
    *  ONE LINER => Optimized Sliding Window with array and maxf: iterate i = 0, j = i to n-1, increment map[c] for s[j], track maxf = max frequency in window, shrink window if j-i+1(window length)>maxf+k (valid window = max frequency+mistakes) by moving i and decrementing map[s[i]], update max if j-i+1>max → track most frequent char dynamically → O(n), O(26).
    *
    * Approach: Optimized Sliding Window
    * - Use a sliding window [i, j] to find longest substring where
    *   at most k replacements make all chars the same.
    * - map[c] tracks frequency of each char in current window.
    * - After adding s[j], compute maxf = max frequency of any char in window.
    *   This ensures maxf always represents the current window’s most frequent char,
    *   which determines how many replacements are needed.
    * - If (window size) > maxf + k → too many replacements needed → shrink window from left.
    * - Time: O(n), Space: O(26) for map (constant).
    * - Rationale: Tracking maxf dynamically eliminates the need for outer loops
    *   over unique characters, achieving true linear time.
    */
    public int characterReplacement3(String s, int k) {
        int max = 1, maxf=0;
        int[] map = new int[26]; 
        for(int i=0,j=0;j<s.length();j++){
            map[s.charAt(j)-'A']++;
            maxf = Math.max(maxf, map[s.charAt(j)-'A']);
            if(j-i+1>maxf+k){
                map[s.charAt(i)-'A']--;
                i++;
            }
            
            max = Math.max(max, j-i+1);
        }
        return max;
    }
    
    
}

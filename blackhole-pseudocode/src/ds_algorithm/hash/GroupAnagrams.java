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

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Brute Force Pair Comparison: Compare every string with every other string and group anagrams manually using isAnagram() and a used[] array → O(n²·k) time, O(nk) space.
    * Sorting + HashMap: Sort each string and group by sorted string as key using a HashMap → O(n·k log k) time, O(nk) space.
    * Fixed-size Freq Array(int[26]) + HashMap: Count letter freq into a fixed array and use as a key(Ex: "bat" → "#1#1#0...#1") to group anagrams → O(n·k) time, O(nk) space. 
    */



    /*
    * ONE LINER => Compare every string with every other and group anagrams manually using isAnagram() and a used[] array → O(n²·k) time, O(nk) space.
    *
    * Approach 1: Brute Force Pair Comparison
    * - Maintain a boolean[] `used` to track which strings have already been grouped.
    *   (`used[i] == true` means strs[i] is already placed into a group.)
    * - Iterate over each string `strs[i]`:
    *     - If not already grouped (`used[i] == false`), create a new group and add strs[i].
    *     - Compare it with all subsequent strings (`j > i`) using an isAnagram check:
    *         - If they match → add strs[j] to the group and mark used[j] = true.
    * - Continue until all strings are processed.
    *
    * Time Complexity: O(n²·k) 
    *     - n = number of strings in the input.
    *     - k = maximum length of a string.
    *     - We compare up to n² pairs, and each comparison takes O(k) time.
    *
    * Space Complexity: O(nk) — storing all groups of strings.
    *
    * Rationale:
    * - Most direct approach without sorting or hashing.
    * - Simple but extremely slow for large datasets due to nested comparisons.
    * - `used[]` is essential to ensure each string is processed only once and avoid duplicate grouping.
    *
    * Example:
    *   strs = ["bat","tab","tap","pat"]
    *   → groups = [["bat","tab"],["tap","pat"]].
    */
    public List<List<String>> groupAnagramsBrute(String[] strs) {
        List<List<String>> groups = new ArrayList<>();
        boolean[] used = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {
            if (used[i]) continue;
            List<String> group = new ArrayList<>();
            group.add(strs[i]);
            used[i] = true;

            for (int j = i + 1; j < strs.length; j++) {
                if (!used[j] && isAnagram(strs[i], strs[j])) {
                    group.add(strs[j]);
                    used[j] = true;
                }
            }
            groups.add(group);
        }
        return groups;
    }

    private boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }
    
    /*
     * Sorting + HashMap: Sort each string and group by sorted string as key using a HashMap → O(n·k log k) time, O(nk) space.
     *
     * Approach 1: Sorting + HashMap
     * - Initialize a HashMap<String, List<String>> to group anagrams.
     * - Iterate over each string s in strs:
     *     - Convert to char array and sort → normalized form.
     *     - Use sorted string as key.
     *     - If key doesn’t exist in map → create new list.
     *     - Add original string s to the list for that key.
     * - Return a new ArrayList containing all grouped anagrams.
     *
     * Time Complexity: O(n k log k) — sorting each string of length k for n strings.
     * Space Complexity: O(n k) — storing grouped anagrams and sorted keys.
     *
     * Rationale:
     * - Sorting makes all anagrams identical in normalized form.
     * - HashMap efficiently groups strings sharing the same sorted form.
     *
     * Example:
     *   strs = ["bat","tab","eat","tea"]
     *   sorted keys: ["abt","abt","aet","aet"]
     *   result: [["bat","tab"],["eat","tea"]]
     */
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
  
    /*
    * ONE LINER => Fixed-size Freq Array int[26] + HashMap: Count letter freq into a fixed array and use as a key to group anagrams 
    * (e.g., "bat" → "#1#1#0...#1").
    *
    * Approach 3: Character Count Map + HashMap
    * - Initialize a HashMap<String, List<String>> to group anagrams.
    * - For each string s in strs:
    *     - Create an int[26] array to count character frequencies.
    *     - Build a key string from counts in format "#count#count#...".
    *     - If key doesn’t exist in map → create new list.
    *     - Add original string s to the list for that key.
    * - Return a new ArrayList containing grouped anagrams.
    *
    * Time Complexity: O(n·k) — each string processed once, counting characters in O(k).
    * Space Complexity: O(n·k) — storing grouped anagrams and keys.
    *
    * Rationale:
    * - Avoids sorting, using fixed-size frequency arrays for constant time per character.
    * - Guarantees unique key per anagram group based on character frequency.
 */

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

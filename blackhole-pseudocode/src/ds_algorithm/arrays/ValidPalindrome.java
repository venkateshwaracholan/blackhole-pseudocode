
package ds_algorithm.arrays;

/**
 *
 * @author vshanmugham
 */


// https://leetcode.com/problems/valid-palindrome/

public class ValidPalindrome {

    /*
    * ONE-LINERS — Quick Reference:
    *
    * Brute Force: Filter and create lowercase alphanumerics, reverse, compare → O(n) time, O(n) space.
    * Two-Pointer: Skip non-alphanumerics with respective pointers, compare lowercase, move both inward if equal or exit false → O(n) time, O(1) space.
    * Two-Pointer with Map: Precompute alphanumeric map for fast lookup, skip non-alphanumerics, compare lowercase, , move both inward if equal or exit false → O(n) time, O(1) space.
    */


    /*
    * ONE LINER => Brute Force: Filter and create lowercase alphanumerics, reverse, compare → O(n) time, O(n) space.
    *
    * Approach 1 (Brute Force):
    * - Build a new string containing only lowercase alphanumeric characters.
    * - Reverse the string.
    * - Compare the filtered string with its reverse to check for palindrome.
    * 
    * Time: O(n) → filtering O(n) + reversing O(n) + comparison O(n)
    * Space: O(n) → extra string for filtered and reversed characters
    */
    public boolean isPalindromeBrute(String s) {
        var filtered = new StringBuilder();
        for(char c: s.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                filtered.append(Character.toLowerCase(c));
            }
        }
        String forward = filtered.toString();
        String reverse = filtered.reverse().toString();
        return forward.equals(reverse);
    }
    
    /*
    * ONE LINER => Two-Pointer: Skip non-alphanumerics with respective pointers, compare lowercase, move both inward if equal or exit false → O(n) time, O(1) space.
    *
    * Approach 2 (Two-Pointer In-Place with for loop):
    * - Use two pointers starting from the beginning and end of the string.
    * - Skip non-alphanumeric characters.
    * - Compare lowercase versions of valid characters.
    * - Move pointers inward until they meet; return false if mismatch occurs.
    *
    * Time:  O(n)
    * Space: O(1)
    */
    public boolean isPalindrome(String s) {
        for(int i=0,j=s.length()-1;i<j;){
            char f = s.charAt(i);
            char r = s.charAt(j);
            if(!Character.isLetterOrDigit(f)){
                i++;
            }
            else if(!Character.isLetterOrDigit(r)){
                j--;
            }
            else if(Character.toLowerCase(f)==Character.toLowerCase(r)){
                i++;
                j--;
            }
            else{
                return false;
            }
        }
        return true;
    }

  
    /*
    * ONE LINER => Two-Pointer with Map: Precompute alphanumeric map for fast lookup, skip non-alphanumerics, compare lowercase, , move both inward if equal or exit false → O(n) time, O(1) space.
    *
    * Approach 3 (Precomputed Character Map):
    * - Use an int[256] array as a lookup table to mark all alphanumeric characters
    *   (letters 'A'-'Z', 'a'-'z' and digits '0'-'9').
    * - Two pointers start from beginning and end of string.
    * - Skip characters not in the map.
    * - Compare lowercase versions of characters at pointers, move inward.
    * - Slightly more space than the previous two-pointer method, but still O(1) space.
    *
    * Educational: This manual map approach essentially replicates what 
    *   Character.isLetterOrDigit() does internally (a fast lookup table for letters/digits),
    *   but restricted to ASCII. Useful for understanding how the method works under the hood.
    *
    * Time:  O(n) → O(1) to build map + O(n) to iterate with two pointers.
    * Space: O(1) → fixed 256-int array.
    *
    * Unique: Avoids multiple Character.isLetterOrDigit() calls, slightly faster for long ASCII strings.
    */
    public boolean isPalindrome4(String s) {
        int[] map = new int[256];
        for(int i=0;i<10;i++){
            map[i+'0']=1;
        }
        for(int i=0;i<26;i++){
            map[i+'a']=1;
            map[i+'A']=1;
        }
        for(int i=0,j=s.length()-1;i<j;){
            char f = s.charAt(i);
            char r = s.charAt(j);
            if(map[f]==0){
                i++;
            }
            else if(map[r]==0){
                j--;
            }
            else if(Character.toLowerCase(f)==Character.toLowerCase(r)){
                i++;
                j--;
            }
            else{
                return false;
            }
        }
        return true;
    }
}

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
// https://leetcode.com/problems/permutation-in-string/
// same as FindAllAnagramsInStrings, 



// Time: O(s + p) space: O(1) space of 26 char is constant
// time to compare 26 char array is taken as O(1)
// if p>s return []
// build s1 freq map in array
// build s2 freq map and if i>=s1 length remove i-s1 from array to create a sliding window
// compare 2 arrays and if euqal return true
// else false

public class PermutationInString {
  public boolean checkInclusion(String s1, String s2) {
    int l1 = s1.length(), l2 = s2.length();
    if(l1>l2) return false;
    int m1[] = new int[26], m2[] = new int[26];
    for(char c: s1.toCharArray())
        m1[c-'a']+=1;
    for(int i=0;i<l2;i++){
        m2[s2.charAt(i)-'a']+=1;
        if(i>=l1)
            m2[s2.charAt(i-l1)-'a']-=1;
        if(Arrays.equals(m1,m2))
            return true;
    }
    return false;
  }
}

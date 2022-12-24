/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.strings;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/longest-common-prefix/description/

public class LongestCommonPrefix {
    
    
    //Time: O(mn) space O(1) n - number of strings, m - length of strings 
    // approach: horizontal scanning, take first string as prefix, iterate other strings
    // check if prefix is contained in string at 0, and reduce prefix length from behind until it is either contained in strings or empty
    
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0)return "";
        String prefix = strs[0];
        for(int i=1;i<strs.length;i++){
            while(strs[i].indexOf(prefix)!=0){
                prefix = prefix.substring(0,prefix.length()-1);
                if(prefix.length()==0)return prefix;
            }
        }
        return prefix;
    }
    
    //Time: O(mn) space O(1) n - number of strings, m - length of strings
    // approach: vertical scanning, take first string and have a loop for that,  
    // have a nested loops to iterate the rest of the strings and check if rest of the strings ended or any char not matching with resp to first string at i
    // return str[0] or str[j] substring from 0 to i;
    // return strs[0] just in case all strings are equal
    public String longestCommonPrefixVertical(String[] strs) {
        if(strs.length==0)return "";
        for(int i=0;i<strs[0].length();i++){
            char c = strs[0].charAt(i);
            for(int j=1;j<strs.length;j++){
                if(strs[j].length()==i || strs[j].charAt(i)!=c)
                    return strs[j].substring(0,i);
            }
        }
        return strs[0];
    }
    
    
    // Time O(min(m)⋅n) Space: O(mlogn) mspace for strings logn for binary search over n
    // min(m)is shortest string btwn left n right 
    //approach: binary search recursive, split strings and find common prefix from 2 strings at a time
    // implement a common prfix method for 2 strings, iterate upto min of 2 strings and return prefix
    public String longestCommonPrefixBinarySearch(String[] strs) {
        if(strs.length==0)return "";
        return longestCommonPrefix(strs, 0 ,strs.length-1);
    }

    public String longestCommonPrefix(String[] strs, int l, int r) {
        if(l==r) return strs[l];
        else{
            int mid = (l+r)/2;
            String left = longestCommonPrefix(strs, l, mid);
            String right = longestCommonPrefix(strs, mid+1, r);
            return commonPrefix(left, right);
        }        
    }

    public String commonPrefix(String l, String r){
        int min = Math.min(l.length(),r.length());
        for(int i=0;i<min;i++)
            if(l.charAt(i)!=r.charAt(i))
                return l.substring(0,i);
        return l.substring(0,min);
    }
    
    
    // Time O(mn⋅logm) Space: O(1)
    //  n - number of strings, m - length of strings
    // approach: binary search iterative
    // find min length of all strings and choose l=0, high = min
    //implement iscommonPrefix, that checks if substring of 0,len of first string is present in rest of the strings
    // while low<= high , if 0,mi is found then move low to right of mid else move high to left of mid
    public String longestCommonPrefixBinaryIte(String[] strs) {
        if(strs.length==0)return "";
        int min = Integer.MAX_VALUE;
        for(String s: strs) min = Math.min(min, s.length());
        int low = 0, high = min;
        while(low<=high){
            int mid = (low+high)/2;
            if(isCommonPrefix(strs, mid))
                low = mid +1;
            else
                high = mid-1;
        }
        return strs[0].substring(0, (low+high)/2);
    }

    public boolean isCommonPrefix(String[] strs, int len){
        String x = strs[0].substring(0,len);
        for(int j=1;j<strs.length;j++)
            if(!strs[j].startsWith(x))
                return false;
        return true;
    }
}

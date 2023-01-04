/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.strings;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/word-pattern/description/

public class WordPattern {
    
    //Time O(n) space: (n)
    //approach: Hashing 
    // if map contains key value has to match the value in split array
    // else is incoming value already present return false
    // not first if needs a {} as else if will get confused with nested if
    public boolean wordPattern(String pattern, String s) {
        Map<Character,String> map = new HashMap();
        String[] spl = s.split(" "); 
        if(pattern.length()!=spl.length) return false;
        for(int i=0;i<pattern.length();i++){
            if(map.containsKey(pattern.charAt(i))) {
                if(!spl[i].equals(map.get(pattern.charAt(i)))) return false;
            }
            else if(map.containsValue(spl[i])) return false;
            map.put(pattern.charAt(i), spl[i]);
        }
        return true;
    }
    
    
    //Time O(n) space: (n)
    // approach: using return of put to check if they are matching the same index
    // Note: using Object,Integer object for diff between char and String in key
    // also, i has to be Integer or it wont work
    public boolean wordPattern2(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map<Object,Integer> index = new HashMap();
        for (Integer i=0; i<words.length; ++i){
            if (index.put(pattern.charAt(i), i)!=index.put(words[i], i))
                return false;
        }
        return true;
    }
    
    //Time O(n) space: (n)
    // approach: using indexOf from string and list
    //
    public boolean wordPattern3(String pattern, String str) {
        List<String> words = Arrays.asList(str.split(" "));
        if (words.size() != pattern.length()) return false;
        for (int i=0; i<words.size(); ++i){
            if (pattern.indexOf(pattern.charAt(i))!=words.indexOf(words.get(i)))
                return false;
        }
        return true;
    }
    
    
    // Solution inspired from isomorphic strings
    // approach: transform pattern and words to first occurance index
    // apple 0#1#1#2#3 but changing pattern to String[] was hard
    public boolean wordPattern4(String pattern, String str) {
        String[] pat = new String[pattern.length()], words = str.split(" ");
        if (words.length != pattern.length()) return false;
        for(int i=0;i<pattern.length();i++) pat[i] = String.valueOf(pattern.charAt(i));
        return transform(pat).equals(transform(words));
    }

    public String transform(String[] arr){
        Map<Object,Integer> map = new HashMap(0); 
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.length;i++){
            if(!map.containsKey(arr[i])) map.put(arr[i], i);
            sb.append(map.get(arr[i])).append("#");
        }
        return sb.toString();
    }
    
}

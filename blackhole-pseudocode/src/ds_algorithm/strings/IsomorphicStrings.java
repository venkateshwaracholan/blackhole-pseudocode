/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.strings;
import java.util.*;

/**
 *
 * @author venkateshwarans
 */
public class IsomorphicStrings {
    public static boolean show = false;
    
    //Time O(n) space: O(1) as chars are limited to ascii
    // approach: map first occ of chracters in s to chars in t
    // and check if they are not equal to current char
    //do the check both ways, s,t and t,s
    public boolean isIsomorphic(String s, String t) {
        return check(s,t)&&check(t,s);
    }

    public boolean check(String s, String t) {
        Map<Character, Character> map = new HashMap();
        for(int i=0;i<s.length();i++){
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if(!map.containsKey(sc)) map.put(sc,tc);
            if(map.get(sc)!=tc) return false;
        }
        return true;
    }
    
    //Time O(n) space: O(1) as chars are limited to ascii
    // approach: map first occ of chracters in s to chars in t in 2 int maps for both strings 
    // check if char at s,t maps as well as t,s maps if not return false 
    public boolean isIsomorphicIntMaps(String s, String t) {
        int[] smap = new int[256];
        int[] tmap = new int[256];
        Arrays.fill(smap,-1);
        Arrays.fill(tmap,-1);
        for(int i=0;i<s.length();i++){
            char sc = s.charAt(i);
            char tc = t.charAt(i); 
            if(smap[sc]==-1)smap[sc] = tc;
            if(tmap[tc]==-1)tmap[tc] = sc;
            if(smap[sc]!=tc || tmap[tc]!=sc) return false;
        }
        return true;
    }
    
    
    // Time O(n) space: O(1) as chars are limited to ascii
    // approach: transform string to its first occurance index values
    // apple 0#1#1#2#3
    //trnasform both string and check if they are equal
    public String transform(String s) {
        Map<Character,Integer> map = new HashMap();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char sc = s.charAt(i);
            if(!map.containsKey(sc))  map.put(sc,i);
            sb.append(map.get(sc)).append("#");
        }
        return sb.toString();
    }

    public boolean isIsomorphicTransform(String s, String t) {
        return transform(s).equals(transform(t));
    }
    
    public static void main(String[] args){
      IsomorphicStrings s = new IsomorphicStrings();
      test(s.isIsomorphic("egg","add"), true);
      test(s.isIsomorphic("foo","bar"), false);
      test(s.isIsomorphic("paper","title"), true);
      test(s.isIsomorphic("aa","ab"), false);
      test(s.isIsomorphic("ab","aa"), false);
      test(s.isIsomorphic("aa","aa"), true);
      test(s.isIsomorphic("aa","aaa"), false);

    }
    
    public static void test(boolean got, boolean exp){
    System.out.println(got == exp);
    if(show || !(got == exp)){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}


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
// https://leetcode.com/problems/find-all-anagrams-in-a-string/

// Time: O(s + p) space: O(1) space of 26 char is constant
// time to compare 26 char array is taken as O(1)
// if p>s return []
// build p freq map in array
// build s freq map and if i>=p's length remove i-pl from array to create a sliding window
// compare 2 arrays and if same add i-pl+1 in ans
public class FindAllAnagramsInStrings {
  public List<Integer> findAnagrams(String s, String p) {
      if(p.length()>s.length()) return new ArrayList();
      int sCount[] = new int[26], pCount[] = new int[26];

      for(char c: p.toCharArray())
          pCount[c-'a']+=1;
      List<Integer> ans = new ArrayList();
      for(int i=0;i<s.length();i++){
          sCount[s.charAt(i)-'a']+=1;
          if(i>=p.length())
              sCount[s.charAt(i-p.length())-'a']-=1;
          boolean f = true;
          for(int k=0;k<26;k++){
              if(sCount[k]!=pCount[k]){
                  f = false;
                  break;
              }

          }
          if(f) ans.add(i-p.length()+1);
      }
      return ans;
  }
  
  public List<Integer> findAnagramsAlt(String s, String p) {
      if(p.length()>s.length()) return new ArrayList();
      int sCount[] = new int[26], pCount[] = new int[26], sl = s.length(), pl = p.length();

      for(char c: p.toCharArray())
          pCount[c-'a']+=1;
      List<Integer> ans = new ArrayList();
      for(int i=0;i<sl;i++){
          sCount[s.charAt(i)-'a']+=1;
          if(i>=pl)
              sCount[s.charAt(i-pl)-'a']-=1;
          if(Arrays.equals(sCount,pCount)) ans.add(i-pl+1);
      }
      return ans;
  }
  
// Time: O(s + p) space: O(1) space of 26 char hashmap is constant
// time to compare 26 char hashmap is taken as O(1)
// using hashmap instead of array, same logic
// we need to remove key from hashmap if size is 1 instead of setting key to zero, remove the key
// we are usig hashmap's internal .equals method and not comparing by iteration
  public List<Integer> findAnagramsHashMap(String s, String p) {
      if(p.length()>s.length()) return new ArrayList();
      Map<Character, Integer> sCount = new HashMap(), pCount = new HashMap();
      int sl = s.length(), pl = p.length();

      for(char c: p.toCharArray())
          pCount.put(c, pCount.getOrDefault(c,0)+1);
      List<Integer> ans = new ArrayList();
      for(int i=0;i<sl;i++){
          sCount.put(s.charAt(i), sCount.getOrDefault(s.charAt(i),0)+1);
          if(i>=pl){
              char ch = s.charAt(i - pl);
              if (sCount.get(ch) == 1)
                sCount.remove(ch);
              else
                sCount.put(ch, sCount.get(ch) - 1);
              //sCount.put(s.charAt(i-pl), sCount.get(s.charAt(i-pl))-1); //this wudnt work here
          } 
          if(sCount.equals(pCount)) ans.add(i-pl+1);
      }
      return ans;
  }
  
//  a hack to make the line work
//  i am initialising smap and p map with all char to 0 just for the line to work
//  slower as a result
  public List<Integer> findAnagramsHashMapAlt(String s, String p) {
      if(p.length()>s.length()) return new ArrayList();
      Map<Character, Integer> sCount = new HashMap(), pCount = new HashMap();
      int sl = s.length(), pl = p.length();
      for(int i=0;i<26;i++) {
          char c = (char)('a' + i);
          sCount.put(c,0);
          pCount.put(c,0);
      }
      for(char c: p.toCharArray())
          pCount.put(c, pCount.getOrDefault(c,0)+1);
      List<Integer> ans = new ArrayList();
      for(int i=0;i<sl;i++){
          sCount.put(s.charAt(i), sCount.getOrDefault(s.charAt(i),0)+1);
          if(i>=pl)
              sCount.put(s.charAt(i-pl), sCount.get(s.charAt(i-pl))-1);
          if(sCount.equals(pCount)) ans.add(i-pl+1);
      }
      return ans;
  }
}

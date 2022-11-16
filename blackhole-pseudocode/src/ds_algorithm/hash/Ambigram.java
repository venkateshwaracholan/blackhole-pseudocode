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

public class Ambigram {
  
/* #google
  Sloppy solution:
  
  public boolean anbigram(String s, Map<Character, Character> map){
    char arr = s.toCharArray();int n = arr.length;
    for(int i=0;i<=arr.length/2;i++){
      if(map.containsKey())
        arr[i] = map.get(arr[i]);
      else return false;
      if(arr[i]!=arr[n-1-i]) return false;
    }
    return true;
  }
  
  // mistakes:
  1. 2 variables in one line
  2. bad naming
  3. assigning or modifying char array, dont use char[]
  4. written more code than required, last if should be inside contains
  5. Not using helper method is taken as negative
  6. not reading the question properly, and thus u missed that input is a list of words
  7. 
  8. always use objects if possible
  9. changes in code/approach should be notified.
  
  // positives:
  1. tested written code
  2. used proper data structures
  3. finished fast
  4. used string builder
  
*/
  
  
//  Q1. Write a function that outputs all the words in the list that look the same when turned upside down.
//  eg. axe, dip, dollop, mow

//  a: e, e:a d:p, o:o, m:w,  
//  k has no mapping
//  axe => exa
  
  
//  Time: O(m*n/2) m list of words, max length of a word 
//  space: 
  public static List<String> ambigramWords(List<String> words, Map<Character,Character> mapping){
    List<String> ans = new ArrayList();
    for(String s: words)
      if(isAmbigramCharArrayPrecise(s, mapping))
        ans.add(s);
    return ans;
  }
  
// Time: O(n/2) space:O(1)
// core idea: get transform of char at i until less than equal to half and check from reverse
// <= is important in boudnd because middle element has to be included
// if mapping does not contain return false as there is no possible solution  
//  return true, if no fasle case occur
  
  public static boolean isAmbigram(String s, Map<Character, Character> mapping){
    int n = s.length();
    for(int i=0;i<=n/2;i++){
      if(mapping.containsKey(s.charAt(i))){
        if(mapping.get(s.charAt(i)) != s.charAt(n-1-i))
          return false;
      }
      else{
        return false;
      }
    }
    return true;
  }
  
//  same as above, the checks are reduced to a single line
//  and iterated over char array instead of string
  
  public static boolean isAmbigramCharArrayPrecise(String s, Map<Character, Character> mapping){
    int n = s.length();
    for(int i=0;i<=n/2;i++)
      if(!mapping.containsKey(s.charAt(i)) || mapping.get(s.charAt(i)) != s.charAt(n-1-i))
          return false;
    return true;
  }
  
  
//  Q2. Write a function that outputs all the words that rotate to give a DIFFERENT word that is also in the lis
//  eg. am (we), did (pip), held (play), flays (shelf), loom (wool), ...
  
  
//  Time: O(mn) Space: O(mn) m => list size, n => max length of string 
//  create a set out of list
//  write a getInvetedString util
//  if invertedString isnt null &&  set contains inverted word
//  add to ans
  public static List<String> ambigramWordsQ2(List<String> words, Map<Character,Character> mapping){
    List<String> ans = new ArrayList();
    Set<String> wordSet = new HashSet(words);
    for(String s: words){
      String invertedWord = getInvertedWord(s, mapping);
      if(invertedWord!=null && wordSet.contains(invertedWord))
        ans.add(s);
    }
    return ans;
  }
  
//  Time:O(n) space: O(n)
//  building inverted String using string builder
//  since inverted string is a reverse of transformed characters
//  weiterate in reverse and append the transformed character and return
//  mapping does not contain achar then return null, meaning 
//  generating inverted string is not possible
  public static String getInvertedWord(String s, Map<Character,Character> mapping){
    StringBuilder invertedWord =  new StringBuilder();
    char letters[] = s.toCharArray();
    int n = letters.length;
    for(int i=n-1;i>=0;i--){
      if(mapping.containsKey(letters[i]))
        invertedWord.append(mapping.get(letters[i]));
      else
        return null;
    }
    return invertedWord.toString();
  }
  
  public static void main(String args[]){
    Map<Character, Character> mapping = new HashMap();
    mapping.put('a','e');
    mapping.put('e','a');
    mapping.put('x','x');
    mapping.put('d','p');
    mapping.put('p','d');
    mapping.put('i','i');
    mapping.put('o','o');
    mapping.put('l','l');
    mapping.put('m','w');
    mapping.put('w','m');
    System.out.println(ambigramWords(new ArrayList(Arrays.asList("kite","axe", "dip", "dollop", "mow")), mapping));
    
    System.out.println(ambigramWordsQ2(new ArrayList(Arrays.asList("we","am","axe")), mapping));
  }
  
}

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

/*
 Anagram Difference
An anagram is a word whose characters can be rearranged to create another word. Given two strings, determine the minimum number of characters in either string that must be modified to make the two strings anagrams . If it is not possible to make the two strings anagrams, return -1.

 

Example:

a = ['tea', 'tea', 'act']

b = ['ate', 'toe', 'acts']

 

a[0] = tea and b[0] = ate are anagrams, so 0 characters need to be modified.
a[1] = tea and b[1] = toe are not anagrams.  Modify 1 character in either string (o → a or a → o) to make them anagrams.
a[2] = act and b[2] = acts are not anagrams and cannot be converted to anagrams because they contain different numbers of characters. 
The return array is [0, 1, -1]
 

Function Description 

Complete the function getMinimumDifference in the editor below.

 

getMinimumDifference has the following parameter(s):

    string a[n]:  an array of strings

    string b[n]:  an array of strings

Return

    int[n]:  an array of integers which denote the minimum number of characters in either string that need to be modified to make the two strings anagrams. If it is not possible, return -1.

 

Constraints

Each string consists of lowercase characters [a-z].
1 ≤ n ≤ 100
0 ≤ |a[i]|, |b[i]| ≤ 104
1 ≤ |a[i]| + |b[i]| ≤ 104
 

Input Format for Custom Testing
Sample Case 0
 

Sample Input For Custom Testing

 

STDIN    Function
-----    --------
5    →   a[] size n = 5
a    →   a = ['a', 'jk', 'abb', 'mn', 'abc']
jk
abb
mn
abc
5    →   b[] size n = 5
bb   →   b = ['bb', 'kj', 'bbc', 'op', 'def']
kj
bbc
op
def
Sample Output

-1
0
1
2
3
 

Explanation

Perform the following n = 5 calculations:

Index 0: a and bb cannot be anagrams because they contain different numbers of characters.
Index 1: jk and kj are already anagrams because they both contain the same characters at the same frequencies.
Index 2: abb and bbc differ by one character.
Index 3: mn and op differ by two characters.
Index 4: abc and def differ by three characters.
After checking each pair of strings, return the array [-1, 0, 1, 2, 3] as the answer.


*/


import java.util.*;

public class AnagramDifference {

  public static List<Integer> getMinimumDifference(List<String> a, List<String> b) {
  // Write your code here
      List<Integer> ans = new ArrayList();

      for(int i=0;i<a.size();i++){
          Map<Character, Integer> freq = new HashMap();
          String s1 = a.get(i);
          String s2 = b.get(i);
          if(s1.length()!=s2.length()){
              ans.add(-1);
              continue;
          }
          for(int j=0;j<s1.length();j++){
              char c = s1.charAt(j);
              freq.put(c, freq.getOrDefault(c,0)+1);
          }
          for(int j=0;j<s2.length();j++){
              char c = s2.charAt(j);
              if(freq.containsKey(c)){
                  if(freq.get(c)==1){
                      freq.remove(c);
                  }else{
                      freq.put(c, freq.get(c)-1);
                  }
              }
          }
          int count = 0;
          if(freq.size()==0){
              ans.add(0);
          }else{
              for(char c: freq.keySet()){
                  count+= freq.get(c);
              }
              ans.add(count);
          }
      }
      return ans;

  }
  
}



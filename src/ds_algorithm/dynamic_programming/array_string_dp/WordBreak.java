/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.dynamic_programming.array_string_dp;

/**
 *
 * @author vshanmugham
 */

import java.util.*;

// https://leetcode.com/problems/word-break/

public class WordBreak {
  
  
//  Time: O(n*(2**n)) space(n)
//  core idea: Dfs DP brute
//  convert word dict to set
//  call recursion with start 0
//  rec base: start == length return true
//  end = start+1 and end<=length becoz we are using substring which is exclusive of the last index
//  if substing(start,end) matches, then call recursion starting from end as start
//  dict contians substring and rec return true then return true
//  or false
//  Example:
/*
"leetcode" ["leet", "code"]
first leet matches and then rec starts from c and then code matches and rec start at s = s.length()
  which return true and stops initial loop in recursion abruptly as aswer is found
  
 "leetcod" ["leet", "code", "leetcod"] 
first leet matches and then rec starts from c and then there is nothing to match and end occured
  returns false and the first initial rec procees with end  = c up untilend  = d
  where substring becomes leetcod and matches, so rec start at s = s.length()
  which return true, and thus true
  
*/
  public boolean wordBreakRecBrute(String s, List<String> wordDict) {
      Set<Integer> set = new HashSet(wordDict);
      return recursion(s, set, 0);
  }

  public boolean recursion(String s, Set<Integer> set , int start){
      if(start==s.length()) return true;
      for(int end = start+1; end<=s.length();end++)
          if(set.contains(s.substring(start,end)) && recursion(s, set, end))
              return true;
      return false;
  }
  
  
//  Time: O(n*n*n) space: O(n)  substring: O(n)
//  same as above, but memoizing using a dp array
//  Note: we are only memoizing start indices of recursion for which answer is not possible
//  start index meaning, a word was found before it and end was passed to recursion as start
// 
  /*
   "leetcod" ["leet", "code", "leetcod"] 
   "T   F  "  
   "paypalcode" ["pay", "paypal", "code"]
   "T  F  T   "
  */
  public boolean wordBreakRecBruteDp(String s, List<String> wordDict) {
      Set<Integer> set = new HashSet(wordDict);
      Boolean[] dp = new Boolean[s.length()+1];
      return recursion(s, set, 0, dp);
  }

  public boolean recursion(String s, Set<Integer> set , int start, Boolean[] dp){
      if(start==s.length()) return true;
      if(dp[start]!=null) return dp[start];
      for(int end = start+1; end<=s.length();end++)
          if(set.contains(s.substring(start,end)) && recursion(s, set, end, dp))
               return dp[start] = true; 
      return dp[start] =  false;
  }
  
//  Time: O(n*n*n) space: O(n)  substring: O(n)
//  Core Idea: bfs, visited set
//  initially add 0 in queue
//  while q not empty, and not visited
//  end = start+1 and end<=s.length(we are using substring which excludes end) end++ 
//  if(set contains substring) then add end to q, q.add(end)
//   inside the if if(end reached end) then return true;
//  or else make start visited
//  return fals eat the end;
  /*
  
  "paypalcode" ["pay", "paypal", "pal", "code"]
   0123456789
  
  q: 0, 
  q: 3,6
  q: 6,6  -> to avoid recomputing this we use the visited  
  */
  
  
  public boolean wordBreak(String s, List<String> wordDict) {
      Set<Integer> set = new HashSet(wordDict);
      Queue<Integer> q = new LinkedList();
      boolean visited[] = new boolean[s.length()];
      q.add(0);
      while(!q.isEmpty()){
          int start =q.poll();
          if(visited[start]==false){
              for(int end = start+1;end<=s.length();end++){
                  if(set.contains(s.substring(start, end))){
                      q.add(end);
                      if(end==s.length()) return true;
                  }
              }
              visited[start] = true;   
          }

      }
      return false;
  }
  
  
//  Time: O(n*n*n) space: O(n)  substring: O(n)
//  core idea: iterative dfs, memo, to to mark index with valid words from start where dp[0] is true
//  create wordDict set
//  create dp[] boolean
//  init dp[0] to true; this is critically important
//  bounds outer: end: 0 to end<=s.length (due to substring end exclusion)
//  inner: start: 0 to start<end (going beyond is useless)
//  if(dp[start] is true and substring is present is dict) 
//  then dp[end] is also a valid start point, so dp[end] = true
//  since the core idea is just to mark
//  Note: substring si not omputed unles dp[start] == true
  
/*
   "paypalcode" ["pay", "paypal", "code"]
    0123456789
   "T  T  T   T" 
  
    pay make dp[3] true
    when end is 6 start is 0
    substrings:
    paypal, aypal, ypal, pal, al, l 
  
   "paypalcode" ["pay", "pal", "code"]
    0123456789
   "T  T  T   T" 
  
    here pal matches from the combination when end is 6 start is 0   
*/

  public boolean wordBreakIteDP(String s, List<String> wordDict) {
      Set<Integer> set = new HashSet(wordDict);
      boolean dp[] = new boolean[s.length()+1];
      dp[0] = true;
      for(int end=0;end<=s.length();end++){
          for(int start=0; start<end;start++){
              if(dp[start] && set.contains(s.substring(start,end))){
                  dp[end] = true;
                  break;
              }
          }
      }
      return dp[s.length()];
  }
  
  
  
  public static void main(String args[]){
    
//    System.out.println("abcdef".substring(4,2)); // this doesnt run and throws exceptions
  }
}

// "applepenapple", wordDict = ["apple", "pen"]
//  tfffftfftfffft
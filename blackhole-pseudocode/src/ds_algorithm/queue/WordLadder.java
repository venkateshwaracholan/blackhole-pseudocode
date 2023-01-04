/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.queue;

import java.util.*;
import ds_algorithm.Pair;

/**
 *
 * @author venkateshwarans
 */
public class WordLadder {
  public static int ladderLength(String start, String end, List<String> wordList) {
    Set<String> wordSet = new HashSet(wordList);
    Set<String> visited = new HashSet();
    if(!wordSet.contains(end)){
      return 0;
    }
    Queue<String> wordQ = new LinkedList();
    Queue<Integer> levelQ = new LinkedList();
    int L = start.length();
    wordQ.add(start);
    levelQ.add(1);
    while(!wordQ.isEmpty()){
      String s = wordQ.poll();
      int l = levelQ.poll();
      for(int i=0;i<L;i++){
        char c[] = s.toCharArray();
        for(char j='a'; j<'z';j++){
          c[i] = j;
          String newWord = new String(c);
          if(wordSet.contains(newWord) && newWord.equals(end)){
            return l+1;
          }
          if(wordSet.contains(newWord) && !visited.contains(newWord)){
            //System.out.println(newWord);
            wordQ.add(newWord);
            visited.add(newWord);
            levelQ.add(l+1);
          }
          
        }
      }
    }
    return 0;
  }
  
  public static int ladderLengthPair(String start, String end, List<String> wordList) {
    Set<String> wordSet = new HashSet(wordList);
    Set<String> visited = new HashSet();
    if(!wordSet.contains(end)){
      return 0;
    }
    Queue<Pair<String, Integer>> wordQ = new LinkedList();
    int L = start.length();
    wordQ.add(new Pair(start,1));
    while(!wordQ.isEmpty()){
      Pair p = wordQ.poll();
      String s = (String)p.getKey();
      int l = (int)p.getValue();;
      for(int i=0;i<L;i++){
        char c[] = s.toCharArray();
        for(char j='a'; j<'z';j++){
          c[i] = j;
          String newWord = new String(c);
          if(wordSet.contains(newWord) && newWord.equals(end)){
            return l+1;
          }
          if(wordSet.contains(newWord) && !visited.contains(newWord)){
            //System.out.println(newWord);
            wordQ.add(new Pair(newWord,l+1));
            visited.add(newWord);
          }
          
        }
      }
    }
    return 0;
  }
  
  public static void main(String[] args){
      test(ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"))), 5);
      test(ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log"))), 0);
      test(ladderLength("a", "c", new ArrayList<>(Arrays.asList("a","b","c"))), 2);
      
      test(ladderLengthPair("hit", "cog", new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"))), 5);
      test(ladderLengthPair("hit", "cog", new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log"))), 0);
      test(ladderLengthPair("a", "c", new ArrayList<>(Arrays.asList("a","b","c"))), 2);
      
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}
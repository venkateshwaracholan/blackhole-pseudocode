package ds_algorithm.sort;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/reorder-data-in-log-files/

import java.util.*;

public class ReorderDataInLogFiles {
  
  
//  Time: O(nlogn) sapce: O(1)
//  Core Idea: merge sort, comparator
//  Internally merge sort preserves the order is 2  entires are considered equal
//  sowe pass a custom comparaor to sort
//  since id and log are guaranteed to be present and not empty
//  we find if its a digit or text using the first character of the log text
//  4 cases
//  both digit - do nothing
//  either one digit - swap is digit is in the log1(returning 1 swaps)
//  both texts and are equal, then compare using id wichi is in token[0]
//  else cmpare using texts which is at token[1]
   public String[] reorderLogFiles(String[] logs) {
      Arrays.sort(logs, (log1, log2)-> {
          String tokens1[] = log1.split(" ", 2);
          String tokens2[] = log2.split(" ", 2);
          if(Character.isDigit(tokens1[1].charAt(0)) && Character.isDigit(tokens2[1].charAt(0))){
              return 0;
          }
          if(Character.isDigit(tokens1[1].charAt(0)) || Character.isDigit(tokens2[1].charAt(0))){
              return Character.isDigit(tokens1[1].charAt(0)) ? 1 :-1; 
          }
          if(tokens1[1].equals(tokens2[1])){
              return tokens1[0].compareTo(tokens2[0]);
          }
          return tokens1[1].compareTo(tokens2[1]);
      }); 
      // Arrays.sort(logs, (a,b)-> a.length()-b.length()); 
      return logs;
  }
  
   
//  Time: O(nlogn) space: O(n)
//   CoreIdea: minHeap, process digit and text logs separately
//   based on the first character of log we split them into heap and an arraylist
//   arraylist preserves order, heap sorts based on the comparator
//   finally first add whats on heap to res and the add from list
//   I use i as counter for heap
//   and i use the same i as offset for res and j as the counter, this res[i+j] = digits.get(j)
  public String[] reorderLogFilesAlt(String logs[]){
      Queue<String> minHeap = new PriorityQueue<>((log1,log2)->{
          String token1[] = log1.split(" ",2);
          String token2[] = log2.split(" ",2);
          if(token1[1].equals(token2[1])){
              return token1[0].compareTo(token2[0]);
          }
          return token1[1].compareTo(token2[1]);
      });
      List<String> digits = new ArrayList();

      for(String log: logs){
          String token[] = log.split(" ",2);
          if(Character.isDigit(token[1].charAt(0))){
              digits.add(log);
          }else{
              minHeap.add(log);
          }
      }
      String ans[] = new String[digits.size()+ minHeap.size()];
      int i = 0;
      while(!minHeap.isEmpty()){
          ans[i] = minHeap.poll();
          i++;
      }
      for(int j = 0;j<digits.size();j++){
          ans[i+j] = digits.get(j);
      }
      return ans;
  }
}

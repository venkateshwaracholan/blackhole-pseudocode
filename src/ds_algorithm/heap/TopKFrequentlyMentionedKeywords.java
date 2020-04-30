/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.heap;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/discuss/interview-question/542597/
/*
k = 2
keywords = ["anacell", "cetracular", "betacellular"]
reviews = [
  "Anacell provides the best services in the city",
  "betacellular has awesome services",
  "Best services provided by anacell, everyone should use anacell",
]

*/
import java.util.*;

public class TopKFrequentlyMentionedKeywords {
  
// "\\w" regex - will match all cahracters except[a-zA-z_0-9] refer regex.java
// use min heap
// we use an added set to avoid counting multiple occurances of eywords in same review text
// and the check word.length()>0 is important becase .. can be taken as empty word and could count it
// we add ans in reverse order using ans.add(0,val), every time we add its add before
//  alternatively we can also accumulate words in string builder if a non character occurs instead of using a regex
//  and process them, but we always need a terminating non char symbol to process last word.
  public static List<String> topKFrequentlyMentionedKeywords(String keywords[], String reviews[], int k){
    List<String> ans = new ArrayList();
    Set<String> keywordSet = new HashSet();
    Map<String, Integer> map = new HashMap();
    Queue<String> heap = new PriorityQueue<>((a,b)->map.get(a)==map.get(b) ? b.compareTo(a) : map.get(a)-map.get(b));
    for(String s: keywords)
      keywordSet.add(s);
    for(String review: reviews){
      Set<String> added = new HashSet();
      for(String word: review.split("\\W")){
        word = word.toLowerCase();
        if(word.length()>0 && keywordSet.contains(word) && !added.contains(word)){
          map.put(word,map.getOrDefault(word,0)+1);
          added.add(word);
        }
      }
    }
    for(String s: map.keySet()){
      heap.add(s);
      if(heap.size()>k){
        heap.poll();
      }
    }
    while(heap.size()>0)
      ans.add(0,heap.poll());
    return ans;
  }
  
  
  public static void main(String args[]){
    int k1 = 2;
    String[] keywords1 = { "anacell", "cetracular", "betacellular" };
    String[] reviews1 = 
    { "Anacell provides the best services in the city", 
      "betacellular has awesome services",
      "Best services provided by anacell, everyone should use anacell", };
    int k2 = 2;
    String[] keywords2 = { "anacell", "betacellular", "cetracular", "deltacellular", "eurocell" };
    String[] reviews2 = 
    { "I love anacell Best services; Best services provided by anacell",
      "betacellular has great services", "deltacellular provides much better services than betacellular",
      "cetracular is worse than anacell", "Betacellular is better than deltacellular.", };
    System.out.println(topKFrequentlyMentionedKeywords(keywords1, reviews1,k1));
    System.out.println(topKFrequentlyMentionedKeywords(keywords2, reviews2,k2));

  }
}

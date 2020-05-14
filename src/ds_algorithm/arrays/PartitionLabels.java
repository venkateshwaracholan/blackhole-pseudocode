package ds_algorithm.arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */
// https://leetcode.com/problems/partition-labels/
import java.util.*;


public class PartitionLabels {
  
// Time: O(n) space: O(n)
// approach: greedy, two pointers
// we use a map to store last index last index, by overwriting index as we go right
// two pointers i and j
// j will store the max that any of the chacaters index could reach until i comes there
// if i==j, we have k to keep tack of the groups start
// add i-k+1 to ans and reassing k to i-1;
  public List<Integer> partitionLabels(String S) {
    char arr[] = S.toCharArray();
    Map<Character,Integer> lastIndex = new HashMap();
    for(int i=0;i<arr.length;i++) lastIndex.put(arr[i],i);
    List<Integer> ans = new ArrayList();
    int k = 0;
    for(int i=0, j=0;i<arr.length;i++){
        j = Math.max(j, lastIndex.get(arr[i]));
        if(i==j){
            ans.add(i-k+1);
            k=i+1;
        }
    }
    return ans;
  }
  
  
// Time: O(n) space: O(n)
// approach: greedy, two pointers
// alternatively we use array of 26 length as a map,becoz problme only contains 26 char  
  public List<Integer> partitionLabelsArray(String S) {
    char arr[] = S.toCharArray();
    int lastIndex[] = new int[26];
    for(int i=0;i<arr.length;i++) lastIndex[arr[i]-'a']=i;
    List<Integer> ans = new ArrayList();
    int k = 0;
    for(int i=0, j=0;i<arr.length;i++){
        j = Math.max(j, lastIndex[arr[i]-'a']);
        if(i==j){
            ans.add(i-k+1);
            k=i+1;
        }
    }
    return ans;
  }
}

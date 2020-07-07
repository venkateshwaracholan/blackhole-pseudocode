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
import java.util.*;
// https://leetcode.com/problems/top-k-frequent-words/


class Node{
    public String s;
    public int c;
    Node(String s, int c){
        this.s = s;
        this.c = c;
    }
    public int compareTo(Node b){
        return this.c==b.c ? b.s.compareTo(this.s) : this.c-b.c;
    }
}

public class TopKFrequentWords {
  
//  Time: O(nlogk) space O(n)
//  
  public List<String> topKFrequent(String[] words, int k) {
    Map<String, Integer> map = new HashMap();
    for(String s: words)
      map.put(s,map.getOrDefault(s,0)+1);
    Queue<String> heap = new PriorityQueue<>((a,b)-> map.get(a).equals(map.get(b)) ? b.compareTo(a) : map.get(a)-map.get(b));
    for(String s: map.keySet()){
      heap.add(s);
      if(heap.size()>k)
        heap.poll();
    }
    ArrayList<String> ans = new ArrayList<String>();
    while(!heap.isEmpty())
      ans.add(heap.poll());
    Collections.reverse(ans);
    return ans;
  }
  
  public List<String> topKFrequentClass(String[] words, int k) {
    Map<String, Integer> map = new HashMap();
    for(String s: words)
        map.put(s,map.getOrDefault(s,0)+1);
    Queue<Node> heap = new PriorityQueue<>((a,b)-> a.c==b.c ? b.s.compareTo(a.s) : a.c-b.c);
    for(String s: map.keySet()){
        heap.add(new Node(s,map.get(s)));
        if(heap.size()>k)
            heap.poll();
    }
    ArrayList<String> ans = new ArrayList<String>();
    while(!heap.isEmpty())
        ans.add(heap.poll().s);
    Collections.reverse(ans);
    return ans;
  }
  
  public static List<String> topKFrequentSiftDown(String[] words, int k) {
      Map<String, Integer> map = new HashMap();
      for(String s: words)
          map.put(s,map.getOrDefault(s,0)+1);
      Node arr[] = new Node[map.keySet().size()];
      int i=0, n = arr.length;
      for(String s: map.keySet())
          arr[i++] = new Node(s,map.get(s));
      for(i=arr.length/2;i>=0;i--){
          siftDown(arr, i, arr.length);
      }
      ArrayList<String> ans = new ArrayList<String>();
      for(i=0;k>0;i++,k--){
          ans.add(arr[0].s);
          arr[0] = arr[n-i-1];
          siftDown(arr, 0, n-i);
      }
      return ans;
  }

  public static void siftDown(Node arr[], int i, int n){
      int left = 2*i+1, right = 2*i+2;
      int largest=0;
      if(left<n && arr[left].compareTo(arr[i])>0)
          largest = left;
      else
          largest = i;
      if(right<n && arr[right].compareTo(arr[largest])>0)
          largest = right;
      if(largest!=i){
          Node temp = arr[i];
          arr[i] = arr[largest];            
          arr[largest] = temp;
          siftDown(arr, largest, n);
      }
  }
  
  public static void main(String args[]){
    topKFrequentSiftDown(new String[]{"plpaboutit","jnoqzdute","sfvkdqf","mjc","nkpllqzjzp","foqqenbey","ssnanizsav","nkpllqzjzp","sfvkdqf","isnjmy","pnqsz","hhqpvvt","fvvdtpnzx","jkqonvenhx","cyxwlef","hhqpvvt","fvvdtpnzx","plpaboutit","sfvkdqf","mjc","fvvdtpnzx","bwumsj","foqqenbey","isnjmy","nkpllqzjzp","hhqpvvt","foqqenbey","fvvdtpnzx","bwumsj","hhqpvvt","fvvdtpnzx","jkqonvenhx","jnoqzdute","foqqenbey","jnoqzdute","foqqenbey","hhqpvvt","ssnanizsav","mjc","foqqenbey","bwumsj","ssnanizsav","fvvdtpnzx","nkpllqzjzp","jkqonvenhx","hhqpvvt","mjc","isnjmy","bwumsj","pnqsz","hhqpvvt","nkpllqzjzp","jnoqzdute","pnqsz","nkpllqzjzp","jnoqzdute","foqqenbey","nkpllqzjzp","hhqpvvt","fvvdtpnzx","plpaboutit","jnoqzdute","sfvkdqf","fvvdtpnzx","jkqonvenhx","jnoqzdute","nkpllqzjzp","jnoqzdute","fvvdtpnzx","jkqonvenhx","hhqpvvt","isnjmy","jkqonvenhx","ssnanizsav","jnoqzdute","jkqonvenhx","fvvdtpnzx","hhqpvvt","bwumsj","nkpllqzjzp","bwumsj","jkqonvenhx","jnoqzdute","pnqsz","foqqenbey","sfvkdqf","sfvkdqf"},1);
    //topKFrequentSiftDown(new String[]{"a","aa","aaa"},3);
    //topKFrequentSiftDown(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"},4);
    //topKFrequentSiftDown(new String[]{"i", "love", "leetcode", "i", "love", "coding"},2);
    Map<Integer,Integer> map = new HashMap();
    map.put(1, 1);
    map.put(3, 2);
    map.put(2, 3);
    Queue<Integer> heap = new PriorityQueue<>( map.keySet()); 
    System.out.print("otha");
    
  }
}

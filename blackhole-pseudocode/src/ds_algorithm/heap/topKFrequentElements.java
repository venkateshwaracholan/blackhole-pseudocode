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
// https://leetcode.com/problems/top-k-frequent-elements/

public class TopKFrequentElements {
  
    // Time O(nlogk) space O(n+k) n - number of element initailly, k - for kth largest
    //  core idea: min heap and reverse assignment 
    // using maps inside comparators what an idea, without passing, it looks like javascript closure
    //  i use min heap and add answer in reverse
    public int[] topKFrequentFast(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for(int n:nums)
          map.put(n, map.getOrDefault(n,0)+1);
        Queue<Integer> heap = new PriorityQueue<>((a,b)->map.get(a)-map.get(b));
        for(int n: map.keySet()){
            if(heap.size()<k) heap.add(n);
            else if(map.get(n)>map.get(heap.peek())){
                heap.poll();
                heap.add(n);
            }
        }
        int ans[] = new int[k];
        while(k>0)
            ans[k---1] = heap.poll();
        return ans;
    }
  
  
    // Time O(nlogk) space O(n+K) n - number of element initailly, k - for kth largest
      public int[] topKFrequnet2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for(int n:nums)
            map.put(n, map.getOrDefault(n,0)+1);
        Queue<Integer> heap = new PriorityQueue<>((a,b)->map.get(a)-map.get(b));
        for(int n: map.keySet()){    
            heap.add(n);
            if(heap.size()>k) heap.poll();
        }
        int ans[] = new int[k];
        while(k>0)
            ans[k---1] = heap.poll();
        return ans;
      }
  
  
    // Time O(nlogk) space O(n+K) n - number of element initailly, k - for kth largest
    //  using int[] for comparators and speed
    public int[] topKFrequentArray3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for(int n:nums)
            map.put(n, map.getOrDefault(n,0)+1);
        Queue<Integer[]> heap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(int n: map.keySet()){
            if(heap.size()<k)
                heap.add(new Integer[]{n,map.get(n)});
            else if(map.get(n)>heap.peek()[1]){
                heap.poll();
                heap.add(new Integer[]{n,map.get(n)});
            } 
        }
        int ans[] = new int[k];
        while(k>0)
            ans[k---1] = heap.poll()[0];
        return ans;
    }
  
  
    // Time O(nlogk) space O(n+K) n - number of element initailly, k - for kth largest
    public int[] topKFrequentArray4(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for(int n:nums)
            map.put(n, map.getOrDefault(n,0)+1);
        Queue<Integer[]> heap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(int n: map.keySet()){
            heap.add(new Integer[]{n,map.get(n)});
            if(heap.size()>k) heap.poll();
        }
        int ans[] = new int[k];
        while(k>0)
            ans[k---1] = heap.poll()[0];
        return ans;
    }
    
    //
    // same idea as above, using treeset instead
    // be careful with the comparator, treeset is set, no duplicates
    // so we are using num,freq as uniq
    // if freq equal then check num val
    public int[] topKFrequent5(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        for(int n:nums) map.put(n, map.getOrDefault(n,0)+1);
        TreeSet<int[]> treeset = new TreeSet<>((a,b)->a[1]==b[1]?a[0]-b[0]:a[1]-b[1]);
        for(int n:map.keySet()) {
            treeset.add(new int[]{n,map.get(n)});
            if(treeset.size()>k) treeset.pollFirst();
        }
        int[] ans = new int[k];
        while(k-->0)
            ans[k]=treeset.pollFirst()[0];
        return ans;
    }
  
  
    //MAX Heap;
    //Time O(nlogk) space O(n) n - number of element initailly, k - for kth largest
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for(int n:nums) map.put(n, map.getOrDefault(n,0)+1);
        Queue<Integer> heap = new PriorityQueue((a,b)->map.get(b)-map.get(a));
        for(int n:map.keySet())heap.add(n);
        int ans[] = new int[k];
        for(int x = 0;x<k;x++)
            ans[x] = heap.poll();
        return ans;
    }
  
    // Time: O(n) space:O(n)
    // approach: bucket sorting
    // create a bucket of freq as positions - size 0 to n+1 -> single elem case handling
    // put values as list as more than one number can have same freq
    // get first k elements from the bucket traversing in reverse
    public int[] topKFrequentBucket(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for(int n:nums) map.put(n, map.getOrDefault(n,0)+1);
        List<Integer> bucket[] = new List[nums.length+1];
        for(int key:map.keySet()){
            if(bucket[map.get(key)]==null) bucket[map.get(key)] = new ArrayList();
                bucket[map.get(key)].add(key);
        }
        int[] ans = new int[k];
        for(int x = nums.length,r=0;x>=0&&r<k;x--)
            if(bucket[x]!=null)
                for(int z = 0;z<bucket[x].size();z++)
                    ans[r++] = bucket[x].get(z);
                
        return ans;
    }
    
    // Time O(nlogk) space O(n+k) n - number of element initailly, k - for kth largest
    // approach: using treemaps which sorte keys in sorted order
    // get map of elem to freq
    // store freq to elems in treemap in reverse order
    // k get values from treemap
    public int[] topKFrequentTreeMap(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        for(int n:nums) map.put(n, map.getOrDefault(n,0)+1);
        TreeMap<Integer,List<Integer>> tmap = new TreeMap<>((a,b)->b-a);
        for(int n:map.keySet()) {
            if(!tmap.containsKey(map.get(n)))
                tmap.put(map.get(n),new ArrayList());
            tmap.get(map.get(n)).add(n);
        }
        int[] ans = new int[k];
        int a=0;
        for(List<Integer> n: tmap.values())
            for(int x=0;a<k&&x<n.size();x++)
                ans[a++]=n.get(x);
        return ans;
    }
    
  
  // need to try quick select when i am free, large code
  // https://leetcode.com/problems/top-k-frequent-elements/solutions/646157/top-k-frequent-elements/
}

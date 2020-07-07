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

public class KclosestpointsToOrigin {
  
//  Time: O(nlogk) Space: O(n)
//  core idea: maxheap of k size for min k
//  computing an array of distances for points and using it as labdda closures in priotity queue
//  adding index in heap for less than k size, 
//  if incoming is less than peek, we need to poll and add 
//  fill the points in reverse order using heap.size or K--
  
  public int[][] kClosest(int[][] points, int K) {
      int[] dist = new int[points.length];
      for(int i=0;i<points.length;i++)
          dist[i] =  points[i][0]*points[i][0]+ points[i][1]*points[i][1];
      Queue<Integer> heap = new PriorityQueue<>((a,b) -> dist[b]-dist[a]);
      for(int i=0;i<points.length;i++){
          // heap.add(i);
          // if(heap.size()>K) heap.poll();
          if(heap.size()<K)heap.add(i);
          else if(dist[i]<dist[heap.peek()]){
              heap.poll();
              heap.add(i);
          }
      }
      int ans[][] = new int[K][2];
      while(!heap.isEmpty())
          ans[heap.size()-1] = points[heap.poll()];
      return ans;
  }
  
//  Time: O(nlogk) Space: O(k)
//  same as above, even better,
//  eliminating siat space O(n) to only heap space O(k)
//  computaions off loaded to inside teh heap comparator
//  add to heap
//  and if heap size > k poll() (removes larger leemnet letting behind k closest points in reverse order)
//  construct and in reverse order(optional in this quetion)
  public int[][] kClosestBetter(int[][] points, int K) {
      Queue<int[]> heap = new PriorityQueue<>((a,b) -> (b[0]*b[0]+b[1]*b[1])-(a[0]*a[0]+a[1]*a[1]));
      for(int[] p: points){
          heap.add(p);
          if(heap.size()>K) heap.poll();
      }
      int ans[][] = new int[K][2];
      while(!heap.isEmpty())
          ans[K---1] = heap.poll();
      return ans;
  }
  

  
//  Time: O(nlogn) Space: O(k) solution space
//  Sorting comparator solution
  
  public int[][] kClosestSorting(int[][] points, int K) {
      Arrays.sort(points, new PointComparator());
      int ans[][] = new int[K][2];
      for(int i=0;i<K;i++) ans[i] = points[i];
      return ans;
  }

  class PointComparator implements Comparator<int[]>{
      public int compare(int a[], int b[]){
          return (a[0]*a[0]+a[1]*a[1]) - (b[0]*b[0]+b[1]*b[1]);
      }
  }
  
// same as above even shorter,
// comapring takes one element ins array ans input and uses that for morphing in ascensing order  
  public int[][] kClosestComparing(int[][] points, int K) {
      Arrays.sort(points, Comparator.comparing(p -> p[0]*p[0]+p[1]*p[1]));
      return Arrays.copyOfRange(points, 0, K);
  }
}

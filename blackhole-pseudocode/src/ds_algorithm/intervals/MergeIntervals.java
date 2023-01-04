package ds_algorithm.intervals;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/merge-intervals/

import java.util.*;

public class MergeIntervals {
  
    //  Time: O(nlogn) space:O(n)
    //  fastest so far
    //  core idea: split the interval into start and end, sort and use them to compare
    //  if last index or end[i]<start[i+1], add inteval to list start[j],end[i]
    //  j = i+1 so that i and j becomes equal once again

    // [[1,3],[2,6],[8,10],[15,18]]
    //  1 2  8 15
    //  3 6 10 18

    // even for such interval it works like a charm
    // [[1,3],[1,1],[8,10],[15,18]]
    //  1 1  8 15
    //  1 3 10 18
    public int[][] mergeFastest(int[][] intervals) {
        List<int[]> list = new ArrayList();
        int n = intervals.length;
        int start[] = new int[n], end[] = new int[n];
        for(int i=0;i<intervals.length;i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        for(int i=0,j=0;i<n;i++){
            if(i==n-1 || end[i]<start[i+1]){
                list.add(new int[]{start[j], end[i]});
                j = i+1;
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
    
    
    // 
    
    
  
    //  Time: O(nlogn) space: O(n)
    // core Idea: sort and merge with last interval
    // add first interval in arraylist
    // arrayList becoz out size is not known until we solve the problem
    // compare current interval with last entry in arraylist and see if we can merge
    //  if we cannot merge, add the new interval
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> ans = new ArrayList();
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        ans.add(intervals[0]);
        for(int i=1;i<n;i++){
            int[] last = ans.get(ans.size()-1);
            if(last[1]>=intervals[i][0])
                last[1] = Math.max(last[1], intervals[i][1]);
            else
                ans.add(intervals[i]);
        }
        return ans.toArray(new int[ans.size()][2]);
    }
  
    //  same above can be implemented using  liskedlist and list.getLast 
    
    //  Time: O(nlogn) space: O(n)
    // core Idea: sort and merge with last interval
    //same as above but i am sorting by end
    // iterating from reverse and merging if last's start <= cur's end
    public int[][] merge2(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals,(a,b)->a[1]-b[1]);
        List<int[]> ans = new ArrayList();
        ans.add(intervals[n-1]);
        for(int i=n-2;i>=0;i--){
            int[] last= ans.get(ans.size()-1);
            if(last[0]<=intervals[i][1]){
                last[0] = Math.min(last[0],intervals[i][0]);
            }
            else
                ans.add(intervals[i]);
        }
        return ans.toArray(new int[ans.size()][2]);
    }
    
  
    // same as above, using a last temp variable  
    //  and another difference is adding interval in the list in after iteration
    //  list.add(last); at the end is necessary like list.add(intervals[0]); in the above solution
    public int[][] mergeAlt2(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
        List<int[]> list = new ArrayList();
        if(intervals.length<=1) return intervals;
        int last[] = intervals[0];
        for(int i=1; i<intervals.length;i++){
            if(intervals[i][0]<=last[1])
                last[1] = Math.max(last[1], intervals[i][1]);
            else{
                list.add(last);
                last = intervals[i];
            }
        }
        list.add(last);
        return list.toArray(new int[list.size()][2]);
    }
    
  
  
  
}

// [[1,3],[2,6],[8,10],[15,18]]
//  1 2  8 15
//  3 6 10 18

// [[1,3],[1,1],[8,10],[15,18]]
//  1 2  8 15
//  1 3 10 18

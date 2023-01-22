/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.intervals;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/non-overlapping-intervals


import java.util.*;

public class NonOverlappingIntervals {
  
    // 1,2 end
    // 1,3 cur  

    //APPROACH 1 sort by end + count merges => sort by start, end = in[0][1], start from 1, if mergeable means last[1]>in[i][0], c++ 
    //              since end is sorted, we will merge with min end by default, if unable to merge , update last to in[i]
      
    //  all the below methods are sort by end, which is easier of the problem

    //  Time: O(nlogn) space; O(1)
    //  Core Idea: Greedy, sorting by end time, compare with previous, Intervals
    //  sort by end time in ascending order
    //  store first end in variable end
    //  start loop from 1
    //  if current's start is less than end then its an overlap, so we can remove that
    //  else we can update end and move on
    //  NOTE: since we are accessing intervals[0][1], have a lengtn 0 check upfront
    public int eraseOverlapIntervals(int[][] in) {
        Arrays.sort(in,(a,b)->a[1]-b[1]);
        int c = 0;
        int[] last = in[0];
        for(int i=1;i<in.length;i++){
            if(last[1]>in[i][0]){
                c++;
            }
            else last = in[i];
        }
        return c;
    }
  
      
    //APPROACH 2 sort by end + count discont. non overlaps and sub from tot =>  count=1 coz first interval skipped,  last <= in then unmergable so count
    //                                                           and update last
     
    // 1,2 end
    // 1,3 cur  
    //  Time: O(nlogn) space; O(1)
    //  core idea: getting count of non overlapping intervals
    //  and returning length-count
    //  count has to be initialized to 1, becoz we are iterating from 1
    //  Note: a little risky for an interview
    public int eraseOverlapIntervalsInverse(int[][] in) {
        Arrays.sort(in,(a,b)->a[1]-b[1]);
        int c = 1;
        int[] last = in[0];
        for(int i=1;i<in.length;i++){
            if(last[1]<=in[i][0]){
                c++;
                last = in[i];
            }
        }
        return in.length-c;
    }
    
    //APPROACH 2.2 sort by end + count discont. non overlaps and sub from tot =>  ite from 0, store so end start from -inf means end=last[1] 
    //                                              last <= in then unmergable so count and update end
    //                                                           
     
    // end => -inf
    // 1,2 cur
    // 1,3
    //  same as above, less riskier
      public int eraseOverlapIntervalsInverseBetter(int[][] intervals) {
          Arrays.sort(intervals, (a,b) -> a[1]-b[1]);
          int count = 0, end = Integer.MIN_VALUE;
          for(int[] i: intervals){
              if(end<=i[0]){
                  end = i[1];
                  count++;
              }
          }
          return intervals.length-count; 
      }
  
  
  
    //APPROACH 3 sort by start  + count merges+ merge with min end => sort by start, end = in[0][1], start from 1, if mergeable means last[1]>in[i][0], merge with min end
    //                          c++, if unable to merge , update last to in[i]
      
    //  all the below methods are sort by start, which is easier of the problem
    //  example
    //  1,3 end
    //  1,2 cur
    //  Time: O(nlogn) space; O(1) 
    //  core idea: Greedy, sort by start time, compare with prev, intervals
    //  sort by start time
    //  start first end in variable, and a count
    //  iterare from 1
    //  if start of cur < end then increment count, 
    //  since we are sorting by start, end = min(cur, end)
    //  else assign from cur
    public int eraseOverlapIntervalsSortByStart(int[][] in) {
        Arrays.sort(in,(a,b)->a[0]-b[0]);
        int c = 0;
        int[] last = in[0];
        for(int i=1;i<in.length;i++){
            if(last[1]>in[i][0]){
                last[1] = Math.min(in[i][1], last[1]);
                c++;
            }
            else last = in[i];
        }
        return c;
    }
}

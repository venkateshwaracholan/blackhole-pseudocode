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

    
//  all the below methods are sort by end, which is easier of the problem
  
//  Time: O(nlogn) space; O(1)
//  Core Idea: Greedy, sorting by end time, compare with previous, Intervals
//  sort by end time in ascending order
//  store first end in variable end
//  start loop from 1
//  if current's start is less than end then its an overlap, so we can remove that
//  else we can update end and move on
//  NOTE: since we are accessing intervals[0][1], have a lengtn 0 check upfront
  public int eraseOverlapIntervals(int[][] intervals) {
      if(intervals.length<1) return 0;
      Arrays.sort(intervals, (a,b)-> a[1]-b[1]);
      int end = intervals[0][1];
      int count = 0;
      for(int i=1;i<intervals.length;i++){
          if(intervals[i][0]<end){
              count++;
          }else{
              end = intervals[i][1];
          }
      }
      return count;
  }
  
//  same a above, but i can use endIndex, instead of accessing from outside the from loop
//  and thereby avoid the lengtn 0 check
  public int eraseOverlapIntervalsIndex(int[][] intervals) {
      Arrays.sort(intervals, (a,b)-> a[1]-b[1]);
      int endIndex = 0, count = 0;
      for(int cur=1;cur<intervals.length;cur++){
          if(intervals[cur][0]<intervals[endIndex][1]){
              count++;
          }else{
              endIndex = cur;
          }
      }
      return count;
  }
  
// 1,2 end
// 1,3 cur  
//  Time: O(nlogn) space; O(1)
//  core idea: getting count of non overlapping intervals
//  and returning length-count
//  count has to be initialized to 1, becoz we are iterating from 1
//  Note: a little risky for an interview
  public int eraseOverlapIntervalsInverse(int[][] intervals) {
      if(intervals.length==0) return 0; 
      Arrays.sort(intervals, (a,b)-> a[1]-b[1]);
      int end = intervals[0][1];
      int count = 1;
      for(int i=1;i<intervals.length;i++){
          if(intervals[i][0]>=end){
              count++;
              end = intervals[i][1];
          }
      }
      return intervals.length-count;
  }
  
// end => -inf
// 1,2 cur
// 1,3
//  same as above, less riskier
  
  public int eraseOverlapIntervalsInverseBetter(int[][] intervals) {
      Arrays.sort(intervals, (a,b) -> a[1]-b[1]);
      int count = 0, end = Integer.MIN_VALUE;
      for(int[] i: intervals){
          if(i[0]>=end){
              end = i[1];
              count++;
          }
      }
      return intervals.length-count; 
  }
  
  
  
  
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
  
  
  public int eraseOverlapIntervalsSortByStart(int[][] intervals) {
      if(intervals.length==0) return 0;
      Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
      int count = 0, end = intervals[0][1];
      for(int i=1;i<intervals.length;i++){
          if(intervals[i][0]<end){
              end = Math.min(intervals[i][1], end);
              count++;
          }
          else{
              end = intervals[i][1];
          }
      }
      return count; 
  }
}

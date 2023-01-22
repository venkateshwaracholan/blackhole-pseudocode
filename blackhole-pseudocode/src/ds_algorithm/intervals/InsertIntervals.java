/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.intervals;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/insert-interval/description/

public class InsertIntervals {
    
    //APPROACH 1 Interval + pos of n,i => if  n>in add in, if n<in add n, n=in, else merge into n, finally add n, and list to array(type) 
    //                                   in<n =>in[3,4] n[5,6]  in[1]<n[0]            in>n => n[1,2] in[3,4] in[0]>n[1]
    
    // Time O(n) space: O(n)
    // iterate intervals
    // if new interval falls right side, add in
    // if ne interval fall left side, add n and put in in n
    // else merge start with min and end with max;
    public int[][] insert(int[][] intervals, int[] n) {
        List<int[]> ans = new ArrayList();
        for(int[] in : intervals){
            if(in[1]<n[0]) ans.add(in);
            else if(in[0]>n[1]){
                ans.add(n);
                n=in;
            }
            else{
                n[0] = Math.min(n[0],in[0]);
                n[1] = Math.max(n[1],in[1]);
            }
        }
        ans.add(n);
        return ans.toArray(new int[ans.size()][2]);
    }
    
    //APPROACH 1.2 Interval + pos of n,i => if  n>in add in, if n<in add n, n=in, else merge into n, finally add n, and list to array(type) 
    //                                   n>in =>in[3,4] n[5,6]  in[1]<n[0]            n<in => n[1,2] in[3,4] in[0]>n[1]
    //               since intervals are sorted we will only enounter in<n first, merges and then in>n, as in grows->
    //               so we can do this in sep loops with same i, add in<n, then merge and add n, then add in>n
    
    
    // same as above, doing them in separate loops
    public int[][] insert2(int[][] in, int[] nu) {
        int i=0,n=in.length;
        List<int[]> ans = new ArrayList();
        for(;i<n && in[i][1]<nu[0];i++) ans.add(in[i]);
        for(;i<n && in[i][0]<=nu[1];i++){
            nu[0] = Math.min(in[i][0],nu[0]);
            nu[1] = Math.max(in[i][1],nu[1]);
        }
        ans.add(nu);
        for(;i<n;i++) ans.add(in[i]);
        return ans.toArray(new int[ans.size()][2]);
    }
    
    
    // unwanted
    
    //APPROACH 2, adding all to arraylist, doing bin search to find mid, then merging and removing 
    
    // Time O(n) space:  O(n)   
    //binary search helps in finding the postion to place
    // butconverting array to arrayList spoils everything
    public int[][] insert3(int[][] in, int[] nu) {
        List<int[]> ans = new ArrayList(Arrays.asList(in));
        int l = 0,r = in.length;
        while(l<r){
            int mid = l+(r-l)/2;
            if(nu[0]<in[mid][0]) r = mid;
            else l = mid+1;
        }
        if (l > 0 && ans.get(l-1)[1] >= nu[0]) l--;
        for(;l<ans.size()&& ans.get(l)[0]<=nu[1] ;){
            nu[0] = Math.min(nu[0],ans.get(l)[0]);
            nu[1] = Math.max(nu[1],ans.get(l)[1]);
            ans.remove(l);
        }
        ans.add(l,nu);
        return ans.toArray(new int[ans.size()][2]);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.combinations;

/**
 *
 * @author venka
 */

import java.util.*;

// https://leetcode.com/problems/combination-sum/description/

public class CombinationSum {
    
    //
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum(candidates,target, 0,new ArrayList(), new ArrayList());
    }

    public List<List<Integer>> combinationSum(int[] c, int target, int k, List<List<Integer>> ans, List<Integer> sub) {
        if(target<0) return ans;
        if(target==0) {
            ans.add(new ArrayList(sub));
            return ans;
        }
        for(int i=k;i<c.length;i++){
            sub.add(c[i]);
            combinationSum(c,target-c[i],i,ans,sub);
            sub.remove(sub.size()-1);
        }
        return ans;
    }
    
    //
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum(candidates,target, 0,new ArrayList(), new ArrayList());
    }

    public List<List<Integer>> combinationSum2(int[] c, int target, int i, List<List<Integer>> ans, ArrayList<Integer> sub) {
        if(target<0 || i==c.length) return ans;
        if(target==0) {
            ans.add(new ArrayList(sub));
            return ans;
        }
        sub.add(c[i]);
        combinationSum2(c,target-c[i],i,ans,sub);
        sub.remove(sub.size()-1);
        combinationSum2(c,target,i+1,ans,sub);
        return ans;
    }
    
    // this works if i change all 3 stacks to queues too
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList();
        Stack<List<Integer>> s = new Stack();
        Stack<Integer> sum = new Stack();
        Stack<Integer> index = new Stack();
        s.add(new ArrayList());
        sum.add(0);
        index.add(0);
        while(!s.isEmpty()){
            List<Integer> x = s.pop();
            int t = sum.pop();
            int i = index.pop();
            if(t>target) continue;
            if(t==target) ans.add(x);
            for(;i<candidates.length;i++){
                List<Integer> y = new ArrayList(x);
                y.add(candidates[i]);
                s.add(y);
                index.add(i);
                sum.add(t+candidates[i]);
            }
        }
        return ans;
    }
    
    //
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList();
        List<List<Integer>> temp = new ArrayList();
        temp.add(new ArrayList());
        for(int i=0;i<candidates.length;i++){
            int x = temp.size();
            for(int j=0;j<x;j++){
                List<Integer> cur = new ArrayList(temp.get(j));
                int sum = 0;
                for(int n: cur) sum+=n;
                while(sum<target){
                    cur.add(candidates[i]);
                    sum+=candidates[i];
                    if(sum<target)temp.add(new ArrayList(cur));
                }
                if(sum==target) ans.add(new ArrayList(cur));
            }
        }
        return ans;
    } 
}

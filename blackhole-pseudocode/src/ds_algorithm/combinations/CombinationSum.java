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
    
    //APPROACH 1 DFS + loop  => combinationSum(c[],t,k,ans,sub) -> t<0 return ans, t==0 ans.add(clone(sub)) and ret, for(k=i,len) sub.add(c[i]) rec(t-c[i],i) sub.remove(sublen-1)
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
    //APPROACH 1.2 DFS + DFS  => combinationSum(c[],t,k,ans,sub) -> t<0 return ans, t==0 ans.add(clone(sub)) and ret, sub.add(c[i]) rec(t-c[i],i) sub.remove(sublen-1) rec(t,i+1)
    // 
    // elimination of for loop and usage of recursion instead of the for loop
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
    
    //APPROACH 2 BFS => queues for sum, idx, sub, bfs with star6ted values, if(t>sum) continue, t==sum ans.add(sub) for(i,len) clone(sub) add context in queue sum, idx, sub
    //
    // this works if i change all 3 stacks to queues too
    class State{
        int sum, i;
        List<Integer> sub;
        State(int sum, int i, List<Integer> sub){
            this.sum = sum; 
            this.i=i;
            this.sub=sub;
        }
    }
    public List<List<Integer>> combinationSum(int[] c, int target) {
        List<List<Integer>> ans  = new ArrayList();
        Queue<State> q= new LinkedList();
        q.add(new State(0,0,new ArrayList()));
        while(!q.isEmpty()){
            State s = q.poll();
            if(s.sum>target || s.i==c.length) continue;
            if(s.sum==target) ans.add(s.sub);
            for(int i=s.i;i<c.length;i++){
                List<Integer> copy = new ArrayList(s.sub);
                copy.add(c[i]);
                q.add(new State(s.sum+c[i],i,copy));
            }
        }
        return ans;
    }
    
    //APPROACH 3 ITE +Ite over ans len => for(i=0,len) for(j=0,tempsize) cur = clone(temp.get(j)) iterate cur find sum, 
    //                  while(sum<target) add c[i] to cur and sum, if(sum<tar)add clone in temp, AFloop if sum==target ans.add(cur)
    // int siz = temp.size(); ismust coz we are modifying tepmp in loop
    
    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList(), temp = new ArrayList();
        temp.add(new ArrayList());
        for(int i=0;i<candidates.length;i++){
            int siz = temp.size();
            for(int j=0;j<siz;j++){
                List<Integer> cur = new ArrayList(temp.get(j));
                int sum = 0;
                for(int n:cur) sum+=n;
                while(sum<target){
                    cur.add(candidates[i]);
                    sum+=candidates[i];
                    if(sum<target) temp.add(new ArrayList(cur));
                }
                if(sum==target) ans.add(cur);
            }
        }
        return ans;
    }
    //APPROACH 3.2 ITE +Ite over ans len => for(i=0,len) for(j=0,tempsize) cur = clone(temp.get(j)) sum is at last pos, 
    //                  while(sum<target) add c[i] to cur and sum, set sum to last pos, if(sum<tar)add clone in temp, AFloop cur.removelast() if sum==target ans.add(cur)
    // same a s above, storing sum in first char, you can also put sum in map
    public List<List<Integer>> combinationSum5(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList(), temp = new ArrayList();
        temp.add(new LinkedList(Arrays.asList(0)));
        for(int i=0;i<candidates.length;i++){
            int siz = temp.size();
            for(int j=0;j<siz;j++){
                LinkedList<Integer> cur = new LinkedList(temp.get(j));
                int sum = cur.getLast();
                while(sum<target){
                    cur.addFirst(candidates[i]);
                    sum+=candidates[i];
                    cur.set(cur.size()-1, sum);
                    if(sum<target) temp.add(new LinkedList(cur));
                }
                cur.removeLast();
                if(sum==target) ans.add(cur);
            }
        }
        return ans;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/find-the-town-judge/

public class FindTheTownJudge {
  
//  Time: (N+E) space: O(N)
//  we store the indegree and the outdegree for each person
//  and we iterate over each people to check if in degree is n-1 and outdegree is 0,
//  if such person exits he is judge, otherwise -1
//  Note: there can only be one twon judge, 2 jusdeg is impossible as they judges dont trust anyone and if everyone doesnt trust he isnt a judge
  public int findJudge(int N, int[][] trust) {
      int incoming[] = new int[N], outgoing[] = new int[N];
      for(int t[]: trust){
          incoming[t[1]-1]++;
          outgoing[t[0]-1]++;
      }
      for(int i=1;i<=N;i++)
          if(incoming[i-1]== N-1 && outgoing[i-1]==0) return i;
      return -1;
  }
  
//  Time: (N+E) space: O(N)
//  if someone trusts me increment and if i trsut someone decrement(becoz in such case i am not judge)
//  compare trust == n-1
  public int findJudgeSingleArray(int N, int[][] trust) {
      int tr[] = new int[N+1];
      for(int i=0;i<trust.length;i++){
          tr[trust[i][1]]+=1;
          tr[trust[i][0]]-=1;
      }
      for(int i=1;i<=N;i++)
          if(tr[i]== N-1) return i;
      return -1;
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.graph;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/critical-connections-in-a-network/

// Amazon | OA 2019 | Critical Connections
// https://leetcode.com/discuss/interview-question/372581


// for undirected Tarjan 
// https://www.youtube.com/watch?v=aZXi1unBdJA

// for directed tarjan, with stack set
// https://www.youtube.com/watch?v=wUgWX0nc4NY&t=474s

// finding bridges in a graph

import java.util.*;

public class CriticalConnectionsInANetwork {
  
  
// Time: O(V+E) space: (V+E)
// core idea: DFS, idMap, lowLinks, dfs algorithm to find bridges, backtracking
// construct undirected graph from connections, server are undirectional
// call dfs on lexographic first node
//  dfs(nde,from,id,grraph,idmap,ans)
//  inside dfs, have a local variable low
//  put low as id, and prepopulate node with id, so that in dfs later it can be found as visited
//  from is useful to avoid confusing visited and preventing parent -> child -> parent cycle
//  so iterate neighbours and check if nei ==  from and skip that
//  if idmap doesnt contain then process
//  else update low if nei has lower
//  process: call dfs on nei, and update low if nei has lower, 
//  and update low on node when coming out of recursion
//  finally, if(node's id < nei id) the it shows that there is no /connection/cycle to make it <= what node has
//  so that is a bridge   1 -> 2 -> 3, [1,2] and [2,3] are bridges as  1<2 and 2< 3
//  Note: since global timer isnt used, id's will repeat, but thats okay, still works
  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
      Map<Integer, List<Integer>> graph = new HashMap();
      Map<Integer, Integer> idMap = new HashMap();
      for(int i=0;i<n;i++) graph.put(i, new ArrayList());
      for(List<Integer> edge: connections){
          graph.get(edge.get(0)).add(edge.get(1));
          graph.get(edge.get(1)).add(edge.get(0));
      }
      List<List<Integer>> ans  = new ArrayList();
      dfs(0,0,0,graph,idMap,ans);
      return ans;
  }

  public int dfs(int node, int from,int id,Map<Integer, List<Integer>> graph, Map<Integer, Integer> idMap, List<List<Integer>> ans){
      idMap.put(node, id);
      int low = id;
      for(int x: graph.get(node)){
          if(x==from) continue;
          if(!idMap.containsKey(x)){
              int temp = dfs(x, node, id+1, graph, idMap, ans);
              low = Math.min(low, temp);
              if(temp>idMap.get(node)){
                  List<Integer> l = new ArrayList();
                  l.add(node);l.add(x);
                  ans.add(l);
              }
          }
          else{
              low = Math.min(low,idMap.get(x));
          }
      }
      idMap.put(node, low);
      return low;
  }
  
//  same as above with no return in dfs
  
  public List<List<Integer>> criticalConnectionsVoid(int n, List<List<Integer>> connections) {
      Map<Integer, List<Integer>> graph = new HashMap();
      Map<Integer, Integer> idMap = new HashMap();
      for(int i=0;i<n;i++) graph.put(i, new ArrayList());
      for(List<Integer> edge: connections){
          graph.get(edge.get(0)).add(edge.get(1));
          graph.get(edge.get(1)).add(edge.get(0));
      }
      List<List<Integer>> ans  = new ArrayList();
      dfsVoid(0,0,0,graph,idMap,ans);
      return ans;
  }

  public void dfsVoid(int node, int from,int id,Map<Integer, List<Integer>> graph, Map<Integer, Integer> idMap, List<List<Integer>> ans){
      idMap.put(node, id);
      int low = id;
      for(int x: graph.get(node)){
          if(x==from) continue;
          if(!idMap.containsKey(x)){
              dfsVoid(x, node, id+1, graph, idMap, ans);
              low = Math.min(low, idMap.get(x));
              if(idMap.get(x)>idMap.get(node))
                  ans.add(Arrays.asList(node,x));
          }
          else
              low = Math.min(low,idMap.get(x));
      }
      idMap.put(node, low);
  }
  
// same as above but used a global timer
//  used graph as array, and hashmap's initialized with initial capacity
  public List<List<Integer>> criticalConnectionsVoidAlt(int n, List<List<Integer>> connections) {
      List<Integer> graph[] = new List[n];
      Map<Integer,Integer> idMap = new HashMap(n); 
      for(int i=0;i<n;i++) graph[i] = new ArrayList();
      for(List<Integer> edge: connections){
          graph[edge.get(0)].add(edge.get(1));
          graph[edge.get(1)].add(edge.get(0));
      }
      List<List<Integer>> ans = new ArrayList();
      dfsVoidAlt(0, 0, new int[]{0}, graph, idMap, ans);
      return ans;
  }

  public void dfsVoidAlt(int node, int from, int timer[], List<Integer> graph[], Map<Integer,Integer> idMap, List<List<Integer>> ans){
      int low = timer[0]++;
      idMap.put(node, low);
      for(int x: graph[node]){
          if(x==from) continue;
          if(!idMap.containsKey(x)) dfsVoidAlt(x, node, timer, graph, idMap, ans);
          if(idMap.get(node)<idMap.get(x)) ans.add(Arrays.asList(node,x));
          low = Math.min(low, idMap.get(x));
      }
      idMap.put(node, low);
  }
  
  
//  same as above, but emoved assignment fro out of recursion
//  while updating node's id value, always get the latest value from map becoz it culd hav been updated to 
//  a lesser value in one of the neighbours
//  and also make sure we are comparing initial id value with low links
//  id < lowlink then its a bridge
//  thats explains why low<idMap.get(x)
  
  public List<List<Integer>> criticalConnectionsALt2(int n, List<List<Integer>> connections) {
      List<Integer> graph[] = new List[n];
      Map<Integer,Integer> idMap = new HashMap(n); 
      for(int i=0;i<n;i++) graph[i] = new ArrayList();
      for(List<Integer> edge: connections){
          graph[edge.get(0)].add(edge.get(1));
          graph[edge.get(1)].add(edge.get(0));
      }
      List<List<Integer>> ans = new ArrayList();
      dfsALt2(0, 0, new int[]{0}, graph, idMap, ans);
      return ans;
  }

  public void dfsALt2(int node, int from, int timer[], List<Integer> graph[], Map<Integer,Integer> idMap, List<List<Integer>> ans){
      int low = timer[0]++;
      idMap.put(node, low);
      for(int x: graph[node]){
          if(x==from) continue;
          if(!idMap.containsKey(x)) dfsALt2(x, node, timer, graph, idMap, ans);
          idMap.put(node, Math.min(idMap.get(node), idMap.get(x)));
          if(low<idMap.get(x)) ans.add(Arrays.asList(node,x));
      } 
  }
  
  
  public static void main(String args[]){
    int[][] connections = new int[][]{{0,1},{1,2},{2,0},{1,3}};
    List<List<Integer>> input = new ArrayList();
    for(int[] edge: connections){
      List<Integer> l = new ArrayList();
      input.add(Arrays.asList(edge[0], edge[1]));
    }
    //criticalConnections(connections.length,input);
  }
}

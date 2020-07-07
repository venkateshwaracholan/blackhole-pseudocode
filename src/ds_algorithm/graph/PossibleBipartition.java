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

// https://leetcode.com/problems/possible-bipartition

import java.util.*;

public class PossibleBipartition {
  
  
// Time: O(N+E) Space: O(N+E) n - number of nodes, E length of dislikes(edges)
// Core idea: graph
//  construct graph by any representation, i use Map<Integer, List<Integer>>
//  for nodes to list of disliked edges
//  populating grah with empty array list for ease
//  iterating disliked to add bidirectional edges, Unidirectional doesnt work for unknown reasons(not found yet)
//  bidirectional makes sense for dislikes
//  and then the dfs
//  iterate nodes and call dfs
//  inide dfs, if color contains node check if it is passed correct color and return comparison color.get(i)==c
//  if not, add node and color, 
//  iterate disliked nodes and pass opposite color 1 ^ c
//  if result is fals ethe return false making sure none of the disliked nodes are in wrong color
//  in the outer loop only call dfs if its not present in color map avoiding passing 0 color to all nodes and eventually fail
//  this is a really nice question, wow, now solve celebrity finding using graph  
  
  public boolean possibleBipartition(int N, int[][] dislikes) {
      Map<Integer, List<Integer>> graph = new HashMap();
      Map<Integer, Integer> color = new HashMap();
      for(int i=1;i<=N;i++)
          graph.put(i, new ArrayList());
      for(int edge[]: dislikes){
          graph.get(edge[0]).add(edge[1]);
          graph.get(edge[1]).add(edge[0]);
      }
      for(int i=1;i<=N;i++)
          if(!color.containsKey(i) && !dfs(i,0,graph,color)) return false;
      return true;
  }

  public boolean dfs(int i, int c, Map<Integer, List<Integer>> graph, Map<Integer, Integer> color){
      if(color.containsKey(i)) return c==color.get(i);
      color.put(i,c);
      for(int x: graph.get(i))
          if(!dfs(x, 1^c, graph, color)) return false;
      return true;
  }
  
}

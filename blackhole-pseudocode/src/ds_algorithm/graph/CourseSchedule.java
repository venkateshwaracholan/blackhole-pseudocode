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
import java.util.*;

// https://leetcode.com/problems/course-schedule/

public class CourseSchedule {
  
//  Time: O(N+E) Space: O(N+E) n - numCourses, E - prerequisites.length
//  create a map of lists of neighbours, unidirectional
//  if we can connect dest to srcin any edge then there is a cycle
//  add egde if not
//  in rec func canconnect,
//  iterate neighbours and check if src == dest  and call rec with neighbours and i ffound return true
//  or return false
  public boolean canFinish(int numCourses, int[][] prerequisites) {
      Map<Integer, List<Integer>> graph = new HashMap();
      for(int i=0;i<numCourses;i++)
          graph.put(i,new ArrayList());
      for(int edge[]: prerequisites){
          if(canConnect(edge[1],edge[0],graph)) return false;
          graph.get(edge[0]).add(edge[1]);
      }
      return true;
  }

  public boolean canConnect(int s, int d, Map<Integer, List<Integer>> graph){
      for(int x: graph.get(s)){
          if(x==d || canConnect(x,d,graph)) return true;
      }
      return false;
  }
  
//  same as above, fasster with primitives
//   used array of list for map<int,list<int>>
  
  public boolean canFinishAlt(int numCourses, int[][] prerequisites) {
      List<Integer> graph[] = new List[numCourses];
      for(int i=0;i<numCourses;i++)
          graph[i] = new ArrayList();
      for(int edge[]: prerequisites){
          if(canConnect(edge[1],edge[0],graph)) return false;
          graph[edge[0]].add(edge[1]);
      }
      return true;
  }

  public boolean canConnect(int s, int d, List<Integer> graph[]){
      for(int x: graph[s]){
          if(x==d || canConnect(x,d,graph)) return true;
      }
      return false;
  }  
  
//  Time: O(N+E) Space: O(N+E)
//  Core idea: graph, visited set
//  construct graph, i use Map of int, list<int>
//  initially filling map with empty array list for each node
//  and then adding unidirectional edge
//  itearet course and call dfs, and if false, return false
//  in dfs(to find cycle), if already visited return false
//  or add in visited
//  iterate prerequisited and call dfs again with if not return false strategy
//  remove from visited as we come out of recursion so that it can be a dependency through other node
// 3 [[0,1],[0,2],[1,2]]
// 2
//    1
//  0 
  
  public boolean canFinishDFSTopSort(int numCourses, int[][] prerequisites) {
      Map<Integer, List<Integer>> graph = new HashMap();
      Set<Integer> visited = new HashSet();
      Set<Integer> global = new HashSet();
      for(int i=0;i<numCourses;i++)
          graph.put(i, new ArrayList());
      for(int edge[]: prerequisites)
          graph.get(edge[0]).add(edge[1]);
      for(int i=0;i<numCourses;i++){
          visited = new HashSet();
         if(!visited.contains(i) && !dfs(i, graph, visited, global)) return false;
      }
      return true;
  }

  public boolean dfs(int i, Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> global){
      if(visited.contains(i)) return false;
      visited.add(i);
      for(int x: graph.get(i))
          if(!global.contains(i) && !dfs(x,graph, visited, global)) return false;
      visited.remove(i);
      global.add(i);
      return true;   
  }
  
  public boolean canFinishDFSTopSortAlt(int numCourses, int[][] prerequisites) {
      List<Integer> graph[] = new List[numCourses];
      for(int i=0;i<numCourses;i++) graph[i] = new ArrayList();
      for(int edge[]:prerequisites) graph[edge[0]].add(edge[1]);
      Set<Integer> global = new HashSet(), visited = new HashSet(); 
      for(int i=0;i<numCourses;i++)
          if(!global.contains(i) && !dfsAlt(i, graph, global, visited)) return false;
      return true;    
  }

  public boolean dfsAlt(int i, List<Integer> graph[], Set<Integer> global, Set<Integer> visited){
      if(visited.contains(i)) return false;
      visited.add(i);
      for(int x: graph[i]) if(!global.contains(i) && !dfsAlt(x,graph,global, visited)) return false;
      visited.remove(i);
      global.add(i);
      return true;
  }
  
  
//  Time: O(N+E) Space: O(N+E)
//  core idea: BDS kash, count matching for processed nodes
//  create indegrees map for end nodes
//  add nodes with 0 indegrees to start with queue
//  foreach neighbours rduce indegrees
//  and if indegrees of neighbour node becomes zero add them to queue
//  increment count while processing nodes in queue
//  match count with number of nodes
  
  public boolean canFinishBFSKahnTopSort(int numCourses, int[][] prerequisites) {
      Map<Integer, List<Integer>> graph = new HashMap();
      Map<Integer, Integer> indegrees = new HashMap();
      for(int i=0;i<numCourses;i++){
        graph.put(i, new ArrayList());
        indegrees.put(i, 0);   
      }
      for(int edge[]: prerequisites){
        graph.get(edge[0]).add(edge[1]);
        indegrees.put(edge[1], indegrees.get(edge[1])+1);
      }
      Queue<Integer> q = new LinkedList();
      int count= 0;
      for(int i=0;i<numCourses;i++)
          if(indegrees.get(i)==0) q.add(i);
      while(!q.isEmpty()){
          for(int x: graph.get(q.poll())){
              indegrees.put(x, indegrees.get(x)-1);
              if(indegrees.get(x)==0) q.add(x);
          }
          count++;
      }
      return count == numCourses;
  }
  
//  same as above, used array of list for map<int,list<int>>
//  used array for indegrees inread of map<int, int>
  
  public boolean canFinishKahnAlt(int numCourses, int[][] prerequisites) {
      List<Integer> graph[] = new List[numCourses];
      int indegrees[] = new int[numCourses];
      for(int i=0;i<numCourses;i++)
        graph[i] = new ArrayList();
      for(int edge[]: prerequisites){
        graph[edge[0]].add(edge[1]);
        indegrees[edge[1]]++;
      }
      Queue<Integer> q = new LinkedList();
      int count= 0;
      for(int i=0;i<numCourses;i++)
          if(indegrees[i]==0) q.add(i);
      while(!q.isEmpty()){
          for(int x: graph[q.poll()]){
              if(--indegrees[x]==0) q.add(x);
          }
          count++;
      }
      return count == numCourses;
  }
  
}

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
  
    // TLE
    //  Time: O((N**N)*E) Space: O(N+E) n - numCourses, E - prerequisites.length
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
            graph.get(edge[0]).add(edge[1]);
            if(canConnect(edge[1],edge[0],graph)) return false;
        }
        return true;
    }

    public boolean canConnect(int s, int d, Map<Integer, List<Integer>> graph){
        for(int x: graph.get(s)){
            if(x==d || canConnect(x,d,graph)) return true;
        }
        return false;
    }
    //  TLE same as above, fasster with primitives
    //   used array of list for map<int,list<int>>
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<Integer> graph[] = new List[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();
        for(int edge[]: prerequisites){
            graph[edge[0]].add(edge[1]);
            if(canConnect(edge[1],edge[0],graph)) return false;  
        }
        return true;
    }

    public boolean canConnect(int s, int d, List<Integer> graph[]){
        for(int x: graph[s]){
            if(x==d || canConnect(x,d,graph)) return true;
        }
        return false;
    }  
    // TLE using vistited set istead of source and dest
    // Time: O((N**N)*N) Space: O(N+E)
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap();
        Set<Integer> visited = new HashSet();
        for(int i=0;i<numCourses;i++)
            graph.put(i,new ArrayList());
        for(int edge[]: prerequisites)
            graph.get(edge[0]).add(edge[1]);
        for(int i=0;i<numCourses;i++)
            if(canConnect(i,visited,graph)) return false;
        return true;
    }
    
    public boolean canConnect(int i, Set<Integer> vis, Map<Integer, List<Integer>> graph){
        if(vis.contains(i)) return true;
        vis.add(i);
        for(int x: graph.get(i)){
            if(canConnect(x,vis,graph)) return true;
        }
        vis.remove(i);
        return false;
    }
    
    
    //proper solutions
    // O(N*N) space: O(N+E)
    public boolean canFinish4(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap();
        Set<Integer> global = new HashSet();
        for(int i=0;i<numCourses;i++)
            graph.put(i,new ArrayList());
        for(int edge[]: prerequisites){
            graph.get(edge[0]).add(edge[1]);
            if(!global.contains(edge[1])&&canConnect(edge[1],edge[0],graph, new HashSet())) return false;
        }
        return true;
    }

    public boolean canConnect(int s, int d, Map<Integer, List<Integer>> graph, Set<Integer> global){
        for(int x: graph.get(s)){
            if(x==d || (!global.contains(s) && canConnect(x,d,graph,global))) return true;
        }
        global.add(s);
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
  
    public boolean canFinish5(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> graph = new HashMap(0);
        Set<Integer> global = new HashSet(), visited= new HashSet();
        for(int i=0;i<numCourses;i++)
            graph.put(i,new ArrayList());
        for(int[] edge:prerequisites)
            graph.get(edge[0]).add(edge[1]);
        for(int i=0;i<numCourses;i++)
            if(cycle(i,graph,global,visited)) return false;
        return true;
    }

    public boolean cycle(int i, Map<Integer,List<Integer>> graph, Set<Integer> global, Set<Integer> visited) {
        if(visited.contains(i)) return true;
        visited.add(i);
        for(int x: graph.get(i))
            if(!global.contains(i)&&cycle(x,graph,global, visited)) return true;
        visited.remove(i);
        global.add(i);
        return false;
    }
    // same as above
    public boolean canFinish6(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        Set<Integer> global = new HashSet(), visited= new HashSet();
        for(int i=0;i<numCourses;i++)
            graph[i]=new ArrayList();
        for(int[] edge:prerequisites)
            graph[edge[0]].add(edge[1]);
        for(int i=0;i<numCourses;i++)
            if(cycle(i,graph,global,visited)) return false;
        return true;
    }

    public boolean cycle(int i,  List<Integer>[] graph, Set<Integer> global, Set<Integer> visited) {
        if(visited.contains(i)) return true;
        visited.add(i);
        for(int x: graph[i])
            if(!global.contains(i)&&cycle(x,graph,global, visited)) return true;
        visited.remove(i);
        global.add(i);
        return false;
    }
  
  
    //  Time: O(N+E) Space: O(N+E)
    //  core idea: BDS kash, count matching for processed nodes
    //  create indegrees map for end nodes
    //  add nodes with 0 indegrees to start with queue
    //  foreach neighbours rduce indegrees
    //  and if indegrees of neighbour node becomes zero add them to queue
    //  increment count while processing nodes in queue
    //  match count with number of nodes
  
    public boolean canFinish7(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> graph = new HashMap();
        Map<Integer,Integer> indegrees = new HashMap();
        Queue<Integer> q = new LinkedList();
        int count=0;
        for(int i=0;i<numCourses;i++){
            graph.put(i,new ArrayList());
            indegrees.put(i,0);
        }
        for(int[] edge: prerequisites){
            graph.get(edge[0]).add(edge[1]);
            indegrees.put(edge[1],indegrees.get(edge[1])+1);
        }
        for(int i=0;i<numCourses;i++)
            if(indegrees.get(i)==0) q.add(i);
        while(!q.isEmpty()){
            for(int c: graph.get(q.poll())){
                indegrees.put(c,indegrees.get(c)-1);
                if(indegrees.get(c)==0) q.add(c);
            }
            count++;
        }
        return count==numCourses;
    }
  
    //  same as above, used array of list for map<int,list<int>>
    //  used array for indegrees inread of map<int, int>
  
    public boolean canFinish8(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int[] indegrees = new int[numCourses];
        Queue<Integer> q = new LinkedList();
        int count=0;
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();
        for(int[] edge: prerequisites){
            graph[edge[0]].add(edge[1]);
            indegrees[edge[1]]+=1;
        }
        for(int i=0;i<numCourses;i++)
            if(indegrees[i]==0) q.add(i);
        while(!q.isEmpty()){
            for(int c: graph[q.poll()])
                if(--indegrees[c]==0) q.add(c);
            count++;
        }
        return count==numCourses;
    }
  
}

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

    
    //APPROACH 1 DFS + Map<I, List<I>> graph+ source dest + Set<I> vis  =>  put empty arraylist for all i,  for every edge, add edge and check can connect, 
    //                                                 if connnect, then cycle present, so false, hew hashset for vis to avoid intereference
    //    canConnect(s,d,vis)-> if vis contains then false, as we are traversing dupicate nodes, if(s==d)return true, vis.add(s), for(x:g(s)) if(rec(x,d,vis)) ret true AFloop ret false
    
    //proper solutions
    // O(N*N) space: O(N+E)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap();
        for(int i=0;i<numCourses;i++)
            graph.put(i,new ArrayList());
        for(int edge[]: prerequisites){
            graph.get(edge[0]).add(edge[1]);
            if(canConnect(edge[1],edge[0],graph, new HashSet())) return false;
        }
        return true;
    }
    public boolean canConnect(int s, int d, Map<Integer, List<Integer>> graph, Set<Integer> vis){
        if(vis.contains(s))return false;
        if(s==d) return true;
        vis.add(s);
        for(int x: graph.get(s))
            if(canConnect(x,d,graph,vis)) return true;
        return false;
    }
    //APPROACH 1.2 DFS + List<Integer>[] graph + source dest + Set<I> vis  =>  put empty arraylist for all i,  for every edge, add edge and check can connect, 
    //                                                 if connnect, then cycle present, so false hew hashset for vis to avoid intereference
    //   canConnect(s,d,vis)-> if vis contains then false, as we are traversing dupicate nodes, if(s==d)return true, vis.add(s), for(x:g(s)) if(rec(x,d,vis)) ret true AFloop ret false
    //can implement the same with graph[]
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();
        for(int edge[]: prerequisites){
            graph[edge[0]].add(edge[1]);
            if(canConnect(edge[1],edge[0],graph,new HashSet())) return false;  
        }
        return true;
    }
    public boolean canConnect(int s, int d, List<Integer> graph[], Set<Integer> vis){
        if(vis.contains(s))return false;
        if(s==d) return true;
        vis.add(s);
        for(int x: graph[s])
            if(canConnect(x,d,graph,vis)) return true;
        return false;
    }  
    

    //APPROACH 2 DFS + Map<I, List<I>> graph+ cycle + Set<I> vis + Set<I> comp  => put empty AL for all i,  for every edge, add edge, for every node check cycle, if cycle ret false
    //                     cycle(s,vis,comp) -> vis(s) ret true,comp(s) ret false,vis.add(s)  iterate ch if(cycle(ch,vis,com)) ret true, vis.remove(s), comp.add(s) ret false                   
     
    //  Time: O(N+E) Space: O(N+E)
    //  Core idea: graph, visited set
    //  construct graph, i use Map of int, list<int>
    //  initially filling map with empty array list for each node
    //  and then adding unidirectional edge
    //  itearet course and call dfs, and if false, return false
    //  in dfs(to find cycle), if num in vis then cycle so return true
    //  if numin completed set, then no cycle for that, return false 
    //  remove from visited as we come out of recursion so that it can be a dependency through other node
    //  add to completed when cycle is not found for num 
    //  [[0,1],[0,2],[1,2]]
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> graph = new HashMap();
        for(int i=0;i<numCourses;i++) graph.put(i,new ArrayList());
        for(int[] edge: prerequisites) graph.get(edge[0]).add(edge[1]);
        Set<Integer> vis = new HashSet(), comp=new HashSet();
        for(int i=0;i<numCourses;i++)
            if(cycle(i,graph, vis, comp)) return false;
        return true;
    }
    public boolean cycle(int s, Map<Integer,List<Integer>> graph, Set<Integer> vis, Set<Integer> comp){
        if(vis.contains(s)) return true;
        if(comp.contains(s)) return false;
        vis.add(s);
        for(int ch: graph.get(s))
            if(cycle(ch,graph,vis,comp)) return true;
        vis.remove(s);
        comp.add(s);
        return false;
    }
    //APPROACH 2.2 DFS + List<Integer>[] graph+ cycle + int[] vis  => put empty AL for all i,  for every edge, add edge, for every node check cycle, if cycle ret false
    //                     cycle(s,vis,comp) -> vis[s]==1 ret true,vis[s]==2 ret false, vis[s]=1  iterate ch if(cycle(ch,vis,com)) ret true, vis[s]=2 ret false                   
     
    public boolean canFinish4(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for(int i=0;i<numCourses;i++) graph[i]=new ArrayList();
        for(int[] edge: prerequisites) graph[edge[0]].add(edge[1]);
        int[] vis = new int[numCourses];
        for(int i=0;i<numCourses;i++)
            if(cycle(i,graph, vis)) return false;
        return true;
    }
    public boolean cycle(int s, List<Integer>[] graph, int[] vis){
        if(vis[s]==1) return true;
        if(vis[s]==2) return false;
        vis[s]=1;
        for(int ch: graph[s])
            if(cycle(ch,graph,vis)) return true;
        vis[s]=2;
        return false;
    }
        
    //APPROACH 3 BFS Queue<Integer> + Map<I, List<I>> graph+ Map<I,I> indegrees + count => for all nodes put empty AL in graph and mark indegrees 0,
    //            for every edge, add edge and inc dest node indegree, add nodes iwth 0 indeg to queue
    //            do BFS -> count++, for all ch in graph -> reduce indegress of ch, if(indeg of ch==0) add in queue                                    
    //            ret count==numcourses
    //  Time: O(N+E) Space: O(N+E)
    //  core idea: BFS kahn, count matching for processed nodes
    //  create indegrees map for end nodes
    //  add nodes with 0 indegrees to start with queue
    //  foreach neighbours rduce indegrees
    //  and if indegrees of neighbour node becomes zero add them to queue
    //  increment count while processing nodes in queue
    //  match count with number of nodes
    public boolean canFinish5(int numCourses, int[][] prerequisites) {
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
 //APPROACH 3.2 BFS Queue<Integer> + List<Integer>[] graph+ Map<I,I> indegrees + count => for all nodes put empty AL in graph and mark indegrees 0,
    //            for every edge, add edge and inc dest node indegree, add nodes iwth 0 indeg to queue
    //            do BFS -> count++, for all ch in graph -> reduce indegress of ch, if(indeg of ch==0) add in queue                                    
    //            ret count==numcourses
    //  same as above, used array of list for map<int,list<int>>
    //  used array for indegrees inread of map<int, int>
    public boolean canFinish6(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int[] indegrees = new int[numCourses];
        Queue<Integer> q = new LinkedList();
        int count=0;
        for(int i=0;i<numCourses;i++) graph[i] = new ArrayList();
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

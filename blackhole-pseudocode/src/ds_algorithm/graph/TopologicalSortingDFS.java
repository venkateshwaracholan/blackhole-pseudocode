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

public class TopologicalSortingDFS {
  Map<Integer,GraphNode> nodeMap = new HashMap();
  
  public void addNode(int n){
    if(nodeMap.containsKey(n))
      return;
    nodeMap.put(n,new GraphNode(n));
  }
  
  public void addEdge(int src, int dest){
    if(!(nodeMap.containsKey(src) && nodeMap.containsKey(dest))){
      System.out.println("Node not present");
      return;
    }
    nodeMap.get(src).neighbours.put(nodeMap.get(dest),0);
  }
  
// this is a postorder traversal of a tree
//  
//  toplogical sort doesnt make sense for a directed graph with cycle or an undirected graph
// if a cycle exists we can find that using disjoint set and we cannot resolve for dependency questions
// we need to iterate all nodes and use a visited set to nt process a node again
// thsi piece of code will fail with stackoverflow if a cycle exits
//  this uses
//  global = global viitedset
  public void topologicalSort(){
    Set<GraphNode> global = new HashSet();
    List<GraphNode> list = new ArrayList();
    for(int x: nodeMap.keySet()){
      GraphNode n = nodeMap.get(x);
      if(!global.contains(n))
        topologicalSortUtil(n,global, list);
    }
    for(GraphNode n: list)
      System.out.println(n.val);
  }
  public void topologicalSortUtil(GraphNode n, Set<GraphNode> global, List<GraphNode>  list){
    for(GraphNode x: n.neighbours.keySet())
      if(!global.contains(x))
        topologicalSortUtil(x, global, list);
    global.add(n);
    list.add(n);
  }
  
// topoloicacl sort and abort if cycle is found
// we sue 2 visited, global an local
//  global visited set prevents us from reprocessing already processes nodes
//  visited on teh oetehr hand is added befroe recursion and removed at end of recursion
//  visited is useful to identify cycle is the elements have already visited nodes in rec as neighbours indicatinga cycle.
//  global is checked before calling red function
//  visited is checked in the recursion entrance

  public boolean topologicalSortCycleDetection(){
    Set<GraphNode> global = new HashSet();
    Set<GraphNode> visited = new HashSet();
    List<GraphNode> list = new ArrayList();
    boolean b = true;
    for(int x: nodeMap.keySet()){
      GraphNode n = nodeMap.get(x);
      if(!global.contains(n) && !topologicalSortUtilCycleDetection(n,global, visited, list)){
          b=false;break;
        } 
    }
    if(b)
      for(GraphNode n: list)
        System.out.println(n.val);
    else
        System.out.println("Cycle detected and Not a DAG");
    return b;
  }
  public boolean topologicalSortUtilCycleDetection(GraphNode n, Set<GraphNode> global, Set<GraphNode> visited, List<GraphNode>  list){
    if(visited.contains(n))
        return false;
    visited.add(n);
    for(GraphNode x: n.neighbours.keySet()){
      if(!global.contains(x) && !topologicalSortUtilCycleDetection(x, global, visited, list))
          return false;
    }
    visited.remove(n);
    global.add(n);
    list.add(n);
    return true;
  }
  
  public static void main(String args[]){
    TopologicalSortingDFS g = new TopologicalSortingDFS();
    g.addNode(5);
    g.addNode(4);
    g.addNode(3);
    g.addNode(0);
    g.addNode(2);
    g.addNode(1);
    
    g.addEdge(0,5);
    g.addEdge(0,3);
    g.addEdge(1,5);
    g.addEdge(1,4);
    g.addEdge(3,2);
    g.addEdge(2,4);
    //g.addEdge(4,0);
    
//    g.topologicalSortCycleDetection();
    g.topologicalSort();
    
  }
}
/*

  0      1
    \  /  |
/    5    |
3         |
   \  
     2    |
       \   
         4

*/
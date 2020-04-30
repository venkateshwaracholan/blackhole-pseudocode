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

public class TopologicalSorting {
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
// 
  public void topologicalSort(){
    Set<GraphNode> visited = new HashSet();
    List<GraphNode> list = new ArrayList();
    for(int x: nodeMap.keySet()){
      GraphNode n = nodeMap.get(x);
      if(!visited.contains(n))
        topologicalSortUtil(n,visited, list);
    }
    for(GraphNode n: list)
      System.out.println(n.val);
  }
  public void topologicalSortUtil(GraphNode n, Set<GraphNode> visited, List<GraphNode>  list){
    for(GraphNode x: n.neighbours.keySet())
      if(!visited.contains(x))
        topologicalSortUtil(x, visited, list);
    visited.add(n);
    list.add(n);
  }
  
// topoloicacl sort and abort if cycle is found
  public boolean topologicalSortCycleDetection(){
    Set<GraphNode> visited = new HashSet();
    Set<GraphNode> cycleMarker = new HashSet();
    List<GraphNode> list = new ArrayList();
    boolean b = true;
    for(int x: nodeMap.keySet()){
      GraphNode n = nodeMap.get(x);
      if(!visited.contains(n))
        if(!topologicalSortUtilCycleDetection(n,visited, cycleMarker, list)){
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
  public boolean topologicalSortUtilCycleDetection(GraphNode n, Set<GraphNode> visited, Set<GraphNode> cycleMarker, List<GraphNode>  list){
    if(cycleMarker.contains(n))
        return false;
    cycleMarker.add(n);
    for(GraphNode x: n.neighbours.keySet()){
      if(!visited.contains(x)) 
        if(!topologicalSortUtilCycleDetection(x, visited, cycleMarker, list))
          return false;
    }
    cycleMarker.remove(n);
    visited.add(n);
    list.add(n);
    return true;
  }
  
  public static void main(String args[]){
    TopologicalSorting g = new TopologicalSorting();
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
    //g.addEdge(4,5);
    
    g.topologicalSortCycleDetection();
    
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
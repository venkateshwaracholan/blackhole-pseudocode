/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.graph;

import java.util.*;

/**
 *
 * @author vshanmugham
 */
public class TopologicalSortingBfsKahnsAlgorithm {
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
  
  public void topologicalSort(){
    Set<GraphNode> global = new HashSet();
    List<GraphNode> list = new ArrayList();
    Map<GraphNode, Integer> indegrees = new HashMap();
    for(int x: nodeMap.keySet()){
      GraphNode node = nodeMap.get(x);
      indegrees.put(node, indegrees.getOrDefault(node, 0));
      for(GraphNode n: node.neighbours.keySet()){
        indegrees.put(n, indegrees.getOrDefault(n, 0)+1);
      }
    }
    Queue<GraphNode> q = new LinkedList();
    int count = 0;
    for(int x: nodeMap.keySet()){
      GraphNode n = nodeMap.get(x);
      if(indegrees.get(n)==0) q.add(n);
    }
    while(!q.isEmpty()){
      GraphNode x = q.poll();
      list.add(x);
      for(GraphNode n: x.neighbours.keySet()){
        indegrees.put(n, indegrees.get(n)-1);
        if(indegrees.get(n)==0) q.add(n);
      }
      count++; 
    }
    if(count!=nodeMap.keySet().size())
      System.out.println("Cycle detected and Not a DAG");
    else
      for(GraphNode n: list)
        System.out.println(n.val);
  }
  
  public static void main(String args[]){
    TopologicalSortingBfsKahnsAlgorithm g = new TopologicalSortingBfsKahnsAlgorithm();
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
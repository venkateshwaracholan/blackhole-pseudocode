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

public class DetectCycleDirectedGraph {
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
  
  public boolean detectCycle(){
    Set<GraphNode> visited = new HashSet();
    Set<GraphNode> cycleMarker = new HashSet();
    List<GraphNode> list = new ArrayList();
    Map<GraphNode,GraphNode> parentMap = new HashMap();
    boolean ans = false;
    for(int x: nodeMap.keySet()){
      GraphNode n = nodeMap.get(x);
      if(!visited.contains(n))
        if(detectCycle(n,visited, cycleMarker, parentMap, list)){
          ans=true;break;
        } 
    }
    if(ans){
      System.out.println("Cycle detected and Not a DAG");
      for(GraphNode n: list)
        System.out.println(n.val);
    }
    return ans;
  }
  
  public boolean detectCycle(GraphNode n, Set<GraphNode> visited, Set<GraphNode> cycleMarker, Map<GraphNode,GraphNode>  parentMap, List<GraphNode> list ){
    if(cycleMarker.contains(n)) {
      GraphNode temp = n;
      list.add(temp);
      do{
        temp = parentMap.get(temp);
        list.add(temp);
      }while(!temp.equals(n));
      return true;
    }
    cycleMarker.add(n);
    for(GraphNode x: n.neighbours.keySet()){
      if(!visited.contains(x)) 
        parentMap.put(x, n);
        if(detectCycle(x, visited, cycleMarker, parentMap, list)){
          return true;
        }
    }
    cycleMarker.remove(n);
    visited.add(n);
    return false;
  }
  
  public static void main(String args[]){
    DetectCycleDirectedGraph g = new DetectCycleDirectedGraph();
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
    g.addEdge(4,1);
    
    System.out.println(g.detectCycle());
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
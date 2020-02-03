/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.graph;

import java.util.*;

class GraphNode{
  int val;
  Set<GraphNode> neighbours = new HashSet();
  
  GraphNode(int val){
    this.val = val;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 67 * hash + this.val;
    return hash;
  }
  @Override
  public boolean equals(Object o){
    if(this==o) return true;
    if(!(o instanceof GraphNode)) return false;
    GraphNode g = (GraphNode) o;
    return this.val==g.val;
  }
}
/**
 *
 * @author venkateshwarans
 */
public class GraphUnWeightedAdjacencyList {
  Map<Integer, GraphNode> nodeMap= new HashMap();
  
  public void addNode(int val){
    if(nodeMap.containsKey(val)){
      System.out.println("Node Already present");
      return;
    }
    nodeMap.put(val,new GraphNode(val));
  }
  
  public void addEdge(int src, int dest){
    if(!(nodeMap.containsKey(src) && nodeMap.containsKey(dest))){
      System.out.println("Node not present");
      return;
    }
    GraphNode srcNode = nodeMap.get(src);
    GraphNode destNode = nodeMap.get(dest);
    srcNode.neighbours.add(destNode);
    destNode.neighbours.add(srcNode);
  }
  
  public void breadthFirstSearch(int src){
    if(!nodeMap.containsKey(src)){
      System.out.println("Node not present");
      return;
    }
    breadthFirstSearch(nodeMap.get(src));
  }
  
  public void breadthFirstSearch(GraphNode src){
    Queue<GraphNode> q = new LinkedList();
    Set<GraphNode> visited = new HashSet();
    q.add(src);
    visited.add(src);
    while(!q.isEmpty()){
      GraphNode cur = q.poll();
      
      System.out.println(cur.val);
      for(GraphNode n: cur.neighbours){
        //System.out.println("AAA:"+n.val);
        if(!visited.contains(n)){
          visited.add(n);
          q.add(n);
        }
      }
    }
    
  }
  
  public void depthFirstSearch(int src){
    if(!nodeMap.containsKey(src)){
      System.out.println("Node not present");
      return;
    }
    depthFirstSearch(nodeMap.get(src));
  }
  
  public void depthFirstSearch(GraphNode src){
    Stack<GraphNode> s = new Stack();
    Set<GraphNode> visited = new HashSet();
    s.add(src);
    visited.add(src);
    while(!s.isEmpty()){
      GraphNode cur = s.pop();
      
      System.out.println(cur.val);
      for(GraphNode n: cur.neighbours){
        //System.out.println("AAA:"+n.val);
        if(!visited.contains(n)){
          visited.add(n);
          s.add(n);
        }
      }
    }
  }
  
  public void depthFirstSearchrec(int src){
    if(!nodeMap.containsKey(src)){
      System.out.println("Node not present");
      return;
    }
    depthFirstSearchRec(nodeMap.get(src), new HashSet());
  }
  
  public void depthFirstSearchRec(GraphNode node, Set<GraphNode> visited){
    visited.add(node);
    System.out.println(node.val);
    for(GraphNode n: node.neighbours){
      if(!visited.contains(n)){
        depthFirstSearchRec(n, visited);
      }
    }
  }
  
  public boolean pathExists(int src, int dest){
    if(!nodeMap.containsKey(src) || !nodeMap.containsKey(dest) ){
      return false;
    }
    Set<GraphNode> visited = new HashSet();
    GraphNode srcNode = nodeMap.get(src);
    Queue<GraphNode> q = new LinkedList();
    q.add(srcNode);
    visited.add(srcNode);
    while(!q.isEmpty()){
      GraphNode n = q.poll();
      if(n.val==dest){
        return true;
      }
      for(GraphNode node: n.neighbours){
        if(!visited.contains(node)){
          q.add(node);
          visited.add(node);
        }
      }
      
    }
    return false;
  }
  
  public boolean printShortestpath(int src, int dest){
    if(!nodeMap.containsKey(src) || !nodeMap.containsKey(dest) ){
      return false;
    }
    List<Integer> path = new ArrayList();
    Set<GraphNode> visited = new HashSet();
    GraphNode srcNode = nodeMap.get(src);
    Queue<GraphNode> q = new LinkedList();
    Map<GraphNode, GraphNode> m = new HashMap();
    q.add(srcNode);
    visited.add(srcNode);
    while(!q.isEmpty()){
      GraphNode n = q.poll();
      if(n.val==dest){
        GraphNode p = n;
        while(!p.equals(srcNode)){
          path.add(p.val);
          //System.out.print(p.val+ "->");
          p = m.get(p);
        }
        path.add(p.val);
        Collections.reverse(path);
        //System.out.print(p.val);
        System.out.println(path);
        return true;
      }
      for(GraphNode node: n.neighbours){
        if(!visited.contains(node)){
          m.put(node, n);
          q.add(node);
          visited.add(node);
        }
      }
      
    }
    return false;
  }
  /*
        1 
   2        3
  
     4   5
  
  */
  
  public static void main(String args[]){
    GraphUnWeightedAdjacencyList g = new GraphUnWeightedAdjacencyList();
    g.addNode(1);
    g.addNode(2);
    g.addNode(3);
    g.addNode(4);
    g.addNode(5);
    g.addNode(6);
    g.addNode(7);
    g.addNode(8);
    g.addEdge(1,2);
    g.addEdge(1,3);
    g.addEdge(2,4);
    g.addEdge(3,4);
    
    g.addEdge(1,5);
    g.addEdge(2,5);
    g.addEdge(3,5);
    g.addEdge(4,5);
    g.addEdge(4,7);
    g.addEdge(8,7);
    //g.breadthFirstSearch(1);
    //g.depthFirstSearchrec(1);
    
    System.out.println(g.pathExists(1, 6));
    System.out.println(g.pathExists(5, 1));
    System.out.println(g.pathExists(4, 1));
    
    
    System.out.println(g.printShortestpath(1, 6));
    System.out.println(g.printShortestpath(5, 1));
    System.out.println(g.printShortestpath(8, 1));
    System.out.println(g.printShortestpath(1, 8));
  }
  

}

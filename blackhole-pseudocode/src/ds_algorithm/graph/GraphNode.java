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



public class GraphNode{
  int val;
  int cost;
  Map<GraphNode,Integer> neighbours = new HashMap();
  
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
  
  public int compare(GraphNode n1, GraphNode n2){
    if(n1.cost==n2.cost)
      return 0;
    else if(n1.cost<n2.cost)
      return -1;
    else
      return 1;
              
  }
}
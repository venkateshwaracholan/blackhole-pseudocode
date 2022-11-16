/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.graph;

/**
 *
 * @author venkateshwarans
 */
import java.util.*;


//
public class BuildRoadAndExists {
  Map<String,Integer> nodeLabel = new HashMap();
  Map<Integer,ArrayList<String>> connection = new HashMap();
  int length =0;
  BuildRoadAndExists(){
    
  }
  
//  Time: O(1)
//  add labels to node
//  add self as connection
  public void addNode(String s){
    nodeLabel.put(s,length+1);
    connection.put(length+1, new ArrayList());
    connection.get(length+1).add(s);
    length++;
  }
  
//  Time: O(1)
//  if from and to both has same label, return true
  public boolean roadExists(String from, String to){
    return nodeLabel.get(from) == nodeLabel.get(to);
  }
  
//  Time: O(n)
//  merge labels by updating lower connections labesl to higher conections label 
  public void buildRoad(String from, String to){
    int fromLabel = nodeLabel.get(from), toLabel =  nodeLabel.get(to);
    if(fromLabel==toLabel) return;
    if(connection.get(fromLabel).size()>connection.get(toLabel).size())
      moveLabel(to, from);
    else
      moveLabel(from, to);
  }
  
//  add from connections to to connections
//  update from connection label to to label
//  remove from from connections
  public void moveLabel(String from, String to){
    int fromLabel = nodeLabel.get(from), toLabel =  nodeLabel.get(to);
    for(String s: connection.get(fromLabel)){
      nodeLabel.put(s, toLabel);
      connection.get(toLabel).add(s);
    }
    connection.remove(fromLabel);
  }
  
  public static void main(String args[]){
    BuildRoadAndExistsDisjointSet b = new BuildRoadAndExistsDisjointSet();
    b.addNode("A");
    b.addNode("B");
    b.addNode("C");
    b.addNode("D");
    b.addNode("E");
    b.addNode("F");
    b.addNode("G");
    b.addNode("H");
    b.addNode("I");
    b.addNode("J");
    b.buildRoad("A", "B");
    b.buildRoad("C", "D");
    System.out.println(b.roadExists("A","D"));
    b.buildRoad("D","E");
    b.buildRoad("A","E");
    System.out.println(b.roadExists("B","E"));
    System.out.println(b.roadExists("A","E"));
    
  }
}


class BuildRoadAndExistsDisjointSet {
  Map<String,String> nodeparent = new HashMap();
  Map<String,Integer> rank = new HashMap();
  BuildRoadAndExistsDisjointSet(){
    
  }
  
//  Time O(1)
//  add self as parent
//  add 0 rank to self
  public void addNode(String s){
    nodeparent.put(s,s);
    rank.put(s,0);
  }
  
//  path compress for from and to nodes by updating their parent to the top most(even for intermediate nodes)
//  return true if both have same top most parent;
  public boolean roadExists(String from, String to){
    findParent(from);findParent(to);
    return nodeparent.get(from) == nodeparent.get(to);
  }
  
//  find the top most parent for from and to
//  decide which one to transfer based on the rank
  public void buildRoad(String from, String to){
    String fromP = findParent(from), toP =  findParent(to);
    if(fromP==toP) return;
    if(rank.get(from)>rank.get(to))
      moveLabel(fromP, toP);
    else
      moveLabel(toP, fromP);
  }
  
//  recursive function to get the top most parent and then assign them to nodes out of recursion
//  until the child node called the function
//  this does path compression for nodes till the parent with top most parent
  public String findParent(String s){
    String parent = nodeparent.get(s);
    if(parent==s)
      return s;
    parent = findParent(parent);
    nodeparent.put(s, parent);
    return parent;
  }
  
//  assing top most to's parent as parent to top most from's parent 
//  update rank for to's top most parent
  public void moveLabel(String from, String to){
    nodeparent.put(from, to);
    rank.put(to, rank.get(to)+1);
  }
  
  public static void main(String args[]){
    BuildRoadAndExists b = new BuildRoadAndExists();
    b.addNode("A");
    b.addNode("B");
    b.addNode("C");
    b.addNode("D");
    b.addNode("E");
    b.addNode("F");
    b.addNode("G");
    b.addNode("H");
    b.addNode("I");
    b.addNode("J");
    b.buildRoad("A", "B");
    b.buildRoad("C", "D");
    System.out.println(b.roadExists("A","D"));
    b.buildRoad("D","E");
    b.buildRoad("A","E");
    System.out.println(b.roadExists("B","E"));
    
  }
}
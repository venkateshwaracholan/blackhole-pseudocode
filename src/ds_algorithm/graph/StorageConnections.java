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

/*
an array with storage spaces





*/


public class StorageConnections {
  
// Time: O(nk) space: O(n)
//  n = numer of storage, k = number of incoming connections
//  Label merging algorithm base on connections
//  merge labels, update index to label map and add froms connecton to to's connections
//  remove from's connection
//  remove from's storage and accumulate to to's connection
//  iterate storge map and find count<threshold
  public static int[] storageConnections(int storage[], int connections[][], int threshold){
    Map<Integer, Integer> indexlabel = new HashMap();
    Map<Integer,ArrayList<Integer>> connection = new HashMap();
    Map<Integer, Integer> labelStroage = new HashMap();
    for(int i=0;i<storage.length;i++){
      indexlabel.put(i,i+1);
      connection.put(i+1,new ArrayList());
      connection.get(i+1).add(i);
      labelStroage.put(i+1,storage[i]);
    }
    int ans[] = new int[connections.length];
    for(int i=0;i<connections.length;i++){
      int from = connections[i][0], to = connections[i][1];
      int fl = indexlabel.get(from), tl = indexlabel.get(to);
      if(fl!=tl){
        for(int k:connection.get(fl)){
          connection.get(tl).add(k);
          indexlabel.put(k, tl);
        }
        connection.remove(fl);
        labelStroage.put(tl,labelStroage.get(tl)+labelStroage.get(fl));
        labelStroage.remove(fl);
      }
      int x = 0;
      for(int n: labelStroage.values())
        if(n<=threshold) x++;
      ans[i] = x;
    }
    return ans;
  }
  
  
  public static int[] storageConnectionsDisJoinSet(int storage[], int connections[][], int threshold){
    Map<Integer, Integer> indexParent = new HashMap();
    Map<Integer, Integer> rank = new HashMap();
    for(int i=0;i<storage.length;i++){
      indexParent.put(i,i);
      rank.put(i,storage[i]);
    }
    int ans[] = new int[connections.length];
    for(int i=0;i<connections.length;i++){
      int from = connections[i][0], to = connections[i][1];
      int fp = findParent(from, indexParent), tp = findParent(to, indexParent);
      if(fp!=tp){
        if(rank.get(fp)>rank.get(tp))
          moveLabel(fp, tp, indexParent, rank);
        else
          moveLabel(tp, fp, indexParent, rank);
      }
      int x = 0;
      for(int n: rank.values())
        if(n<=threshold) x++;
      ans[i] = x;
    }
    return ans;
  }
  
  public static void moveLabel(int from, int to, Map<Integer,Integer> indexParent, Map<Integer,Integer> rank){
    indexParent.put(from, to);
    rank.put(to, rank.get(to)+rank.get(from));
    rank.remove(from);
  }
  
  public static int findParent(int i, Map<Integer,Integer> indexParent){
    int parent = indexParent.get(i);
    if(i==parent)
      return i;
    parent = findParent(parent, indexParent); 
    indexParent.put(i, parent);
    return parent;
  }
  
  public static void main(String args[]){
    //int x[] = storageConnectionsDisJoinSet(new int[]{1,2,3,4,5}, new int[][]{{0,1},{1,4},{0,4}}, 4);
    int x[] = storageConnectionsDisJoinSet(new int[]{1,2,3,4,5}, new int[][]{{0,1},{2,3},{1,3}}, 4);
    for(int i: x)
      System.out.println(i);
  }
}

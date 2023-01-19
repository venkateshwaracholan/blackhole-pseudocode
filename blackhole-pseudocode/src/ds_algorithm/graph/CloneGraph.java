/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.graph;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/clone-graph/

public class CloneGraph {
    
    //APPROACH 1 DFS + Map<Integer,Node> cloneGraph(n,map)=> n==null return n, map.contains(n.val) ret map(n.val) (gets us the clone), clone n, put in map(n.val,clone), \
    //                                   for(x:n.neib) clone.neib.add(rec(x,map)) return clone
    
    /** DFS - Recursive
     * Time Complexity: O(V + E)
     * Space Complexity: O(V). Both Recursion Stack and HashMap will take O(V) space
     * V = Number of nodes. E = Number of edges in the graph. */
    public Node cloneGraph(Node node) {
        return cloneGraph(node,new HashMap());
    }
    public Node cloneGraph(Node node, Map<Integer,Node> visited) {
        if(node==null) return node;
        if(visited.containsKey(node.val)) return visited.get(node.val);
        Node clone = new Node(node.val); 
        visited.put(node.val,clone);
        for(Node n: node.neighbors)
            clone.neighbors.add(cloneGraph(n,visited));
        return clone;
    }
    //APPROACH 1.2 DFS + Node[]map cloneGraph(n,map)=> n==null return n, map[n.val]!=null ret map[n.val] (gets us the clone),put in map[n.val]=clone n, 
    //                                   for(x:n.neib) map[n.val].neib.add(rec(x,map)) return map[n.val]
    

    public Node cloneGraph2(Node node) {
        return cloneGraph2(node, new Node[101]);
    }
    public Node cloneGraph2(Node node, Node[] map) {
        if(node==null)return node;
        if(map[node.val]!=null) return map[node.val];
        map[node.val] = new Node(node.val);
        for(Node ch: node.neighbors)
            map[node.val].neighbors.add(cloneGraph2(ch,map));
        return map[node.val];
    }
    
    
    //APPROACH 2 BFS + Map<Integer,Node> cloneGraph(n)=> n==null return n, create clone and put in map, 
    //                              then do bfs -> n, for(x:n.neib) !map.contains(x.val) {put in map(x.val,clone) add in queue} map(n.val).neib.add(map(c.val))
    
    /**
    * BFS - Iterative
    * Time Complexity: O(V + E)
    * Space Complexity: O(V). Both Queue and HashMap will take O(V) space
    * V = Number of nodes. E = Number of edges in the graph.
    */

    public Node cloneGraph3(Node node) {
        if(node==null) return node;
        Queue<Node> q = new LinkedList();
        Map<Integer,Node> map = new HashMap();
        Node clone = new Node(node.val);
        map.put(node.val,clone);
        q.add(node);
        while(!q.isEmpty()){
            Node n = q.poll();
            for(Node c:n.neighbors){
                if(!map.containsKey(c.val)){
                    map.put(c.val, new Node(c.val));
                    q.add(c);
                }
                map.get(n.val).neighbors.add(map.get(c.val));
            }
        }
        return clone;
    }
}



class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
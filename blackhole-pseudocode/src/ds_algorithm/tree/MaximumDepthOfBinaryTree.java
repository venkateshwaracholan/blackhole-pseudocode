/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import java.util.*;

/**
 *
 * @author venkateshwarans
 */

//https://leetcode.com/problems/maximum-depth-of-binary-tree/


public class MaximumDepthOfBinaryTree {
  static boolean show = true;
  
    //APPROACH 1 dfs+max(left,right)+1
    // Time: O(n) space: O(n)
    // simple recursion to return max of left and right
    public static int maximumDepthRec(TreeNode node){
        if(node==null)return 0;
        return Math.max(maximumDepthRec(node.left)+1, maximumDepthRec(node.right)+1);
    }
  
    //APPROACH 2 dfs+max(left(d+1),right(d+1))
    // Time: O(n) space: O(n)
    // simple recursion to return max of left and right by passing depth
    public static int maximumDepthRec2(TreeNode node){
        return maximumDepthRec2(node, 0);
    }
    public static int maximumDepthRec2(TreeNode node, int d){
        if(node==null) return d;
        return Math.max(maximumDepthRec2(node.left, d+1), maximumDepthRec2(node.right, d+1));
    }
  
    //APPROACH 3 bfs level order traversal
    // Time: O(n) space: O(n)
    // BFS Ite
    // double loop bfs for identifying levels
    public static int maximumDepthBFSIte(TreeNode root){
        Queue<TreeNode> q = new LinkedList();
        if(root==null) return 0;
        q.add(root);
        int dep = 0;
        while(!q.isEmpty()){    
            int siz = q.size();
            while(siz-->0){
                TreeNode n = q.poll();
                if(n.left!=null) q.add(n.left);
                if(n.right!=null) q.add(n.right);
            }
            dep++;
        }
        return dep;
    }
  
    //APPROACH 4 bfs + entity with TreeNode and Depth in the queue
    // 
    // Time: O(n) space: O(n)
    // Dfs Ite
    // double stacks to maintain nodes and depths
    
    class Entity{
        public TreeNode n;
        public int d;
        Entity(TreeNode n, int d){
            this.n=n;
            this.d=d;
        }
    }
    public int maxDepth(TreeNode root) {
        Queue<Entity> q = new LinkedList();
        if(root==null) return 0;
        q.add(new Entity(root,1));
        int dep = 0, max=0;
        while(!q.isEmpty()){
            Entity e = q.poll();
            TreeNode n = e.n;
            int d = e.d;
            if(n.left!=null) q.add(new Entity(n.left,d+1));
            if(n.right!=null) q.add(new Entity(n.right,d+1));
            max = Math.max(max, d);
        }
        return max;
    }
  
  
  public  static void main(String[] args){
  }

  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}

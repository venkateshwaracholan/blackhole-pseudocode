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
    if(node==null){
      return d;
    }
    return Math.max(maximumDepthRec2(node.left, d+1), maximumDepthRec2(node.right, d+1));
  }
  
  //APPROACH 3 bfs level order traversal
  // Time: O(n) space: O(n)
  // BFS Ite
  // double loop bfs for identifying levels
  public static int maximumDepthBFSIte(TreeNode node){
    int height = 0;
    Queue<TreeNode> q = new LinkedList();
    if(node!=null)
      q.add(node);
    while(!q.isEmpty()){
      int nodeCount = q.size();
      while(nodeCount>0){
        TreeNode n = q.poll();
        if(n.left!=null)
          q.add(n.left);
        if(n.right!=null)
          q.add(n.right);
        nodeCount--;
      }
      height++;
      
    }
    return height;
  }
  
  //APPROACH 4 dfs + stack for depth, can be done with bfs + queue for depth
  // Time: O(n) space: O(n)
  // Dfs Ite
  // double stacks to maintain nodes and depths
  public static int maximumDepthDFSIte(TreeNode node){
    int maxHeight = 0;
    Stack<TreeNode> s = new Stack();
    Stack<Integer> depths = new Stack();
    if(node!=null){
      s.add(node);
      depths.add(1);
    }
    while(!s.isEmpty()){
      TreeNode n = s.pop();
      int d = depths.pop();
      if(n.right!=null){
        s.add(n.right);
        depths.add(d+1);
      }
      if(n.left!=null){
        s.add(n.left);
        depths.add(d+1);
      }
      maxHeight = Math.max(d, maxHeight);  
    }
    return maxHeight;
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

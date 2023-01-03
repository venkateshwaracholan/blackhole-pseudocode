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
  
  // Time: O(n) space: O(n)
  // DFS rec
  // simple recursion to return max of left and right
  public static int maximumDepthRec(TreeNode node){
    if(node==null)return 0;
    return Math.max(maximumDepthRec(node.left)+1, maximumDepthRec(node.right)+1);
  }
  
  // Time: O(n) space: O(n)
  // DFS rec
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
  

  //approach dfs, recursion, just go deep and get the max as u can
  
  public int maxDepth(TreeNode root) {
        if(root==null)return 0;
        if(root.left==null) return maxDepth(root.right)+1; // redundant lines
        if(root.right==null) return maxDepth(root.left)+1;
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
  // tis will also work
  public int maxDepth2(TreeNode root) {
        if(root==null)return 0;
        return Math.max(maxDepth2(root.left), maxDepth2(root.right))+1;
    }
  
  
  public  static void main(String[] args){
    BinaryTree t1;
      
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, 4, null, null, null, 7));
    test(maximumDepthRec(t1.root), 4);
    t1 = new BinaryTree(Arrays.asList(3,9,20));
    test(maximumDepthRec(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(maximumDepthRec(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,null,9));
    test(maximumDepthRec(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3));
    test(maximumDepthRec(t1.root), 1);
    t1 = new BinaryTree(Arrays.asList());
    test(maximumDepthRec(t1.root), 0);
    
    
    
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, 4, null, null, null, 7));
    test(maximumDepthRec2(t1.root), 4);
    t1 = new BinaryTree(Arrays.asList(3,9,20));
    test(maximumDepthRec2(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(maximumDepthRec2(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,null,9));
    test(maximumDepthRec2(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3));
    test(maximumDepthRec2(t1.root), 1);
    t1 = new BinaryTree(Arrays.asList());
    test(maximumDepthRec2(t1.root), 0);
    
    
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, 4, null, null, null, 7));
    test(maximumDepthBFSIte(t1.root), 4);
    t1 = new BinaryTree(Arrays.asList(3,9,20));
    test(maximumDepthBFSIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(maximumDepthBFSIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,null,9));
    test(maximumDepthBFSIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3));
    test(maximumDepthBFSIte(t1.root), 1);
    t1 = new BinaryTree(Arrays.asList());
    test(maximumDepthBFSIte(t1.root), 0);

    t1 = new BinaryTree(Arrays.asList(2, 1, 3, 4, null, null, null, 7));
    test(maximumDepthDFSIte(t1.root), 4);
    t1 = new BinaryTree(Arrays.asList(3,9,20));
    test(maximumDepthDFSIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(maximumDepthDFSIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,null,9));
    test(maximumDepthDFSIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3));
    test(maximumDepthDFSIte(t1.root), 1);
    t1 = new BinaryTree(Arrays.asList());
    test(maximumDepthDFSIte(t1.root), 0);
    
    
    
//    t1 = new BinaryTree(Arrays.asList(2, 1, 3, 4, null, null, null, 7));
//    test(maxDepth(t1.root), 4);

  }

  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}

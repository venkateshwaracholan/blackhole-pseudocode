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

class StackElem
{
  public StackElem(TreeNode node, boolean hasUnvistedChildren) {
    super();
    this.node = node;
    this.hasUnvistedChildren = hasUnvistedChildren;
  }
  TreeNode node ;
  boolean hasUnvistedChildren;
}


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
  
  
  // Time: O(n) space: O(n)
  // Dfs Ite
  // i dono what purpose it serves, inspired from abhai
  public static int maximumDepthDFSIte2(TreeNode node){
    int maxHeight = 0;
    Stack<TreeNode> s = new Stack();
    Stack<Boolean> vleft = new Stack();
    Stack<Boolean> vright = new Stack();
    if(node!=null){
      s.add(node);
      vleft.add(false);
      vright.add(false);
    }
    while(!s.isEmpty()){
      TreeNode n = s.peek();
      maxHeight = Math.max(s.size(), maxHeight);  
      if(!vleft.peek() && n.left!=null){
        s.add(n.left);
        vleft.pop();
        vleft.add(true);
        vleft.add(false);
        vright.add(false);
      }else if(!vright.peek() && n.right!=null){
        s.add(n.right);
        vright.pop();
        vright.add(true);
        vleft.add(false);
        vright.add(false);
      }else{
        s.pop();
        vleft.pop();
        vright.pop();
      }
    }
    return maxHeight;
  }
  
//       2
//     1   3
//   4
//  7      
// abhais inspiration
//  public static int maxDepth(TreeNode root) {
//    if(root == null)
//    return 0;
//
//    if(root.left==null && root.right==null)
//    return 1;
//
//    List<StackElem> stack = new ArrayList();
//    StackElem currElem = new StackElem(root,true);
//    int height = 0; 
//
//    while(currElem != null)
//    {
//      if(!stack.isEmpty() && stack.get(stack.size()-1) == currElem)
//      {
//        if(currElem.hasUnvistedChildren)
//        {
//          TreeNode nextElem = currElem.node.right ;
//          currElem.hasUnvistedChildren =false;
//          currElem = new StackElem(nextElem,true);
//          currElem.hasUnvistedChildren = nextElem.left!=null || nextElem.right!=null;
//        }
//        else
//        {
//          stack.remove(stack.size()-1);
//          currElem = stack.isEmpty()? null : stack.get(stack.size()-1);
//        }
//      }
//      else
//      {
//        if(currElem.hasUnvistedChildren)
//        {
//          stack.add(currElem);
//          TreeNode nextElem = currElem.node.left == null ? currElem.node.right  : currElem.node.left;
//          currElem.hasUnvistedChildren = (nextElem ==currElem.node.left && currElem.node.right != null);
//          currElem = new StackElem(nextElem,true);
//          currElem.hasUnvistedChildren = nextElem.left!=null || nextElem.right!=null;
//        }
//        else
//        {
//          height = height > stack.size()+1 ? height : stack.size()+1;
//          currElem = stack.isEmpty()? null : stack.get(stack.size()-1);
//        }
//      }
//    }
//    return height;
//  }
  
  
  
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

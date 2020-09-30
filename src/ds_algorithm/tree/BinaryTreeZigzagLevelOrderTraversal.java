package ds_algorithm.tree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal

import java.util.*;

// Time: O(n) space: O(n/2) at maximum qqueue can hold upto n/2 nodes for a balanced tree
// core Idea: BFS, level order traveral, linkedList
// first do a level order bfs over the tree
// have a variable to track depth
// if depth is even, add the values from the level in reverse using linkedList addFirst()
// NOTE: using linked list to avoid unnecessary swapping that happens in case of an arraylist
 
public class BinaryTreeZigzagLevelOrderTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root){
    List<List<Integer>> ans = new ArrayList();
    if(root==null) return ans;
    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    int level = 1;
    while(!q.isEmpty()){
      int nodes = q.size();
      LinkedList<Integer> temp = new LinkedList();
      while(nodes>0){
        TreeNode node = q.poll();
        if(level%2==0){
          temp.addFirst(node.val);
        }
        else{
          temp.add(node.val);
        }
        if(node.left!=null){
          q.add(node.left);
        }
        if(node.right!=null){
          q.add(node.right);
        }
        nodes--;
      }
      ans.add(temp);
      level++;
    }
    return ans;
  }
  
//  same as above, using atemp direction toggle variable instead of the level
  
  public List<List<Integer>> zigzagLevelOrderDiretionVariable(TreeNode root) {
      List<List<Integer>> ans= new ArrayList();
      Deque<TreeNode> q = new ArrayDeque();
      if(root!=null){
          q.add(root);
      }
      int dir = 0;
      while(!q.isEmpty()){
          int l = q.size();
          LinkedList<Integer> temp = new LinkedList();
          while(l>0){
              TreeNode node = q.poll();
              if(dir==0){
                  temp.add(node.val);
              }else{
                  temp.addFirst(node.val);
              }
              if(node.left!=null) {
                  q.add(node.left);
              }
              if(node.right!=null){
                  q.add(node.right);
              }
              l--;
          }
          dir ^= 1;
          ans.add(temp);
      }
      return ans;
  }
  
  
//  Time : O(n) space; O(n) linear tree d==n
//  Core idea: Dfs, using depth a index, LinkedList, depth odd or even
//  as we traverse the dfs, if depth is greater than ans.size, add an empty linkedlist to ans
//  since asn is List<List<Integer>> casting is required to LinkedList<Integer>
//  based on depth we can decide to add last or first(reverse)
  
  
  public List<List<Integer>> zigzagLevelOrderDfs(TreeNode root){
      return zigzagLevelOrder(0, root, new ArrayList());
  }

  public List<List<Integer>> zigzagLevelOrder(int d, TreeNode root, List<List<Integer>> ans){
      if(root==null) return ans;
      if(d>=ans.size()){
          ans.add(new LinkedList());
      }
      LinkedList<Integer> temp = (LinkedList<Integer>)ans.get(d);
      if(d%2==0){
          temp.add(root.val);
      }else{  
          temp.addFirst(root.val);
      }
      zigzagLevelOrder(d+1, root.left, ans);
      zigzagLevelOrder(d+1, root.right, ans);
      return ans;
  }
  
}

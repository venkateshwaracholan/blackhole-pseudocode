/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;

import ds_algorithm.Test;

import static ds_algorithm.binary_tree.SerializeAndDeserializeBinaryTree.deSerializeBfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/path-sum/

public class PathSum {
  
  // Time: O(n) space: O(n)
  // core idea DFS
  // passing sum sofar to nodes and only checking if x equals sum if leadnode
  // return back boolean
  public static boolean hasPathSum(TreeNode root, int sum) {
    return hasPathSum(root,0,sum);
  }

  public static boolean hasPathSum(TreeNode root, int sofar, int sum) {
    if(root==null)
      return false;
    int x = sofar+root.val;
    if(x==sum && root.left==null && root.right==null)
      return true;
    boolean left = hasPathSum(root.left,x,sum);
    if(left)
      return left;
    boolean right = hasPathSum(root.right,x,sum);
    if(right)
      return right;
    return false;
  }
   // better version of above code
    public boolean hasPathSumAlt(TreeNode root, int targetSum) {
        return hasPathSum(root, targetSum, 0);
    }

    public boolean hasPathSumAlt(TreeNode node, int targetSum, int sum) {
        if(node==null)return false;
        if(node.left==null && node.right==null && sum+node.val==targetSum)
            return true;
        return hasPathSum(node.left, targetSum, sum+node.val) ||
        hasPathSum(node.right, targetSum, sum+node.val);
    }
  
  // Time: O(n) space: O(n)
  // core idea DFS
  // subtract sum with root val as u go below the tree
  // ans or left rec or right rec
  public static boolean hasPathSumBetter(TreeNode root, int sum) {
    if(root==null)
      return false;
    return (root.left==null && root.right==null && root.val==sum) || 
            hasPathSumBetter(root.left,sum-root.val) || hasPathSumBetter(root.right,sum-root.val);
  }
  
  // Time: O(n) space: O(n)
  // core idea DFS
  // subtract sum with root val as u go below the tree
  // ans or left rec or right rec
  // avoided root null check and included that in same line
  public static boolean hasPathSumSingleLine(TreeNode root, int sum) {
    return root!=null && ((root.left==null && root.right==null && root.val==sum) || hasPathSumSingleLine(root.left,sum-root.val) || hasPathSumSingleLine(root.right,sum-root.val));
  }
  
  
  // Time: O(n) space: O(n)
  // core idea BFS Ite, using second queue to accumulate sum,check leaft node sum equals target
  public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null)return false;
        Queue<TreeNode> q = new LinkedList();
        Queue<Integer> s = new LinkedList();
        q.add(root);
        s.add(root.val);
        while(!q.isEmpty()){
            TreeNode t = q.poll();
            int v = s.poll();
            if(t.left!=null){
                q.add(t.left);
                s.add(v+t.left.val);
            }
            if(t.right!=null){
                q.add(t.right);
                s.add(v+t.right.val);
            }
            if(t.left==null&&t.right==null&&v==targetSum) return true;
        }
        return false;
    }
  
  
  public static void main(String args[]){
    Test.test(hasPathSum(deSerializeBfs("5,4,8,11,null,13,4,7,2,null,null,null,1"),22), true);
    Test.test(hasPathSum(deSerializeBfs("1,2"),1), false);
    
    Test.test(hasPathSumBetter(deSerializeBfs("5,4,8,11,null,13,4,7,2,null,null,null,1"),22), true);
    Test.test(hasPathSumBetter(deSerializeBfs("1,2"),1), false);
    
    Test.test(hasPathSumSingleLine(deSerializeBfs("5,4,8,11,null,13,4,7,2,null,null,null,1"),22), true);
    Test.test(hasPathSumSingleLine(deSerializeBfs("1,2"),1), false);
  }
}

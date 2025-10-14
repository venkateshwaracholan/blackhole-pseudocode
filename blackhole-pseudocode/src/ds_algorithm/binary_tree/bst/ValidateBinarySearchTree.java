/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.binary_tree.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import ds_algorithm.binary_tree.TreeNode;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/validate-binary-search-tree/description

public class ValidateBinarySearchTree {
    
    
    //APPROACH 1 DFS+range, return false if out of range
    
    // Time O(n) space:O(logn)
    public boolean isValidBSTDFSRecTopDownBottomUpRangeCheck(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long min, long max) {
        if(node==null){
            return true;
        }
        if(node.val<=min || node.val>=max){
            return false;
        }
        boolean left = isValidBST(node.left, min, node.val);
        boolean right = isValidBST(node.right, node.val, max);
        return left && right;
    }
    
    //APPROACH 1.2 BFS+range[], return false if out of range
    
    // Time O(n) space:O(n)
    // BFS, and one more queue for storing limits for each node
    // check if nodes are in rage else false
    // we have to use long becoz of ques range, req some casting too
    
    class Entity2{
        public TreeNode node;
        public long min, max; 
        Entity2(TreeNode node, long min, long max){
            this.node=node;
            this.min=min;
            this.max=max;
        }
    }
    record Entity(TreeNode node, long min, long max){}
    public boolean isValidBSTBFSIteQueueRecordRange(TreeNode root) {
        var queue = new LinkedList<Entity>();
        if(root!=null){
            queue.add(new Entity(root,Long.MIN_VALUE, Long.MAX_VALUE));
        }
        while(!queue.isEmpty()){
            Entity entity = queue.poll();
            TreeNode node = entity.node();
            long min = entity.min();
            long max = entity.max();
            if(node.val<=min || node.val>=max){
                return false;
            }
            if(node.left!=null){
                queue.add(new Entity(node.left, min, node.val));
            }
            if(node.right!=null){
                queue.add(new Entity(node.right, node.val, max));
            }
        }
        return true;
    }
    
    
    //APPROACH 2 Ite inorder traversal + prev    
    
    // Time O(n) space:O(n)
    // approach: inorder traversal iterative
    // using inorder traversal -> left root right
    // use a prev and check if cur val < prev val, then its not a bst
    public boolean isValidBSTInorderTravStackItePrevCheck(TreeNode root) {
        var stack = new Stack<TreeNode>();
        TreeNode node = root, prev = null;
        while(node!=null || !stack.isEmpty()){
            while(node!=null){
                stack.push(node);
                node=node.left;
            }
            node = stack.pop();
            if(prev!=null && node.val<=prev.val){
                return false;
            }
            prev = node;
            node = node.right;
        }
        return true;
    }
    
     //APPROACH 2.2 DFS inorder traversal + prev[], update and check prev in inorder block 
    
    // Time O(n) space:O(n)
    // approach: inorder traversal recursive
    // storing left part and return left && inorder(right)
    // inorder left, root, right, thats y checks are in middle
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, new TreeNode[]{null});
    }

    public boolean isValidBST(TreeNode node, TreeNode[] prev) {
        if(node==null){
            return true;
        }
        boolean left = isValidBST(node.left, prev);
        if(prev[0]!=null && prev[0].val>=node.val){
            return false;
        }
        prev[0] = node;
        boolean right = isValidBST(node.right, prev);
        return left && right;
    }

}

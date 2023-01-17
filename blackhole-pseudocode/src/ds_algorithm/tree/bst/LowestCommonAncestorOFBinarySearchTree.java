/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.tree;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

public class LowestCommonAncestorOFBinarySearchTree {
    
    
    // Time O(logn) space:O(logn)call stack of height of bst,h=logn
    //since its bst, if both p and q are less move left
    // else if both p and q are in greater move right
    // else cur is the ans
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val<root.val&&q.val<root.val)
            return lowestCommonAncestor(root.left,p,q);
        else if(p.val>root.val&&q.val>root.val)
            return lowestCommonAncestor(root.right,p,q);
        else return root;
    }
    
    // Time O(logn) space:O(logn)
    // same above approadh in BFS
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> qu = new LinkedList();
        qu.add(root);
        while(!qu.isEmpty()){
            TreeNode n = qu.poll();
            if(n.val>p.val&&n.val>q.val) qu.add(n.left);
            else if(n.val<p.val&&n.val<q.val) qu.add(n.right);
            else return n;
        }
        return root;
    }
}

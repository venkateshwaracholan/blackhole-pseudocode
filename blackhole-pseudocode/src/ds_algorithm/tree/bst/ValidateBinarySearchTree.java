/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.tree.bst;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/validate-binary-search-tree/description

public class ValidateBinarySearchTree {
    
    
    //APPROACH 1 DFS+range, return false if out of range
    
    // Time O(n) space:O(logn)
    public boolean isValidBST(TreeNode root,long lo, long hi) {
        if(root==null) return true;
        if(root.val<=lo || root.val>=hi) return false;
        return isValidBST(root.left, lo, root.val) && isValidBST(root.right, root.val, hi);
    }
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    //APPROACH 1.2 BFS+range[], return false if out of range
    
    // Time O(n) space:O(n)
    // BFS, and one more queue for storing limits for each node
    // check if nodes are in rage else false
    // we have to use long becoz of ques range, req some casting too
    
    class Entity{
        public TreeNode n;
        public Long[] r;
        Entity(TreeNode n, Long[] r){
            this.n=n;
            this.r=r;
        }
    }
    public boolean isValidBST(TreeNode root) {
        Queue<Entity> q= new LinkedList();
        if(root==null) return true;
        q.add(new Entity(root, new Long[]{Long.MIN_VALUE, Long.MAX_VALUE}));
        while(!q.isEmpty()){
            Entity e = q.poll();
            TreeNode n = e.n;
            Long[] r = e.r;
            if(n.val<=r[0]||n.val>=r[1]) return false;
            if(n.left!=null) q.add(new Entity(n.left, new Long[]{r[0],(long)n.val}));
            if(n.right!=null) q.add(new Entity(n.right, new Long[]{(long)n.val,r[1]}));
        }
        return true;
    }
    
    
    //APPROACH 2 Ite inorder traversal + prev    
    
    // Time O(n) space:O(n)
    // approach: inorder traversal iterative
    // using inorder traversal -> left root right
    // use a prev and check if cur val < prev val, then its not a bst
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> s = new Stack();
        TreeNode n = root,prev=null;
        while(n!=null||!s.isEmpty()){
            while(n!=null){
                s.add(n);
                n=n.left;
            }
            n=s.pop();
            if(prev!=null&&n.val<=prev.val)return false;
            prev=n;
            n=n.right;
        }
        return true;
    }
    
     //APPROACH 2.2 DFS inorder traversal + prev[], update and check prev in inorder block 
    
    // Time O(n) space:O(n)
    // approach: inorder traversal recursive
    // storing left part and return left && inorder(right)
    // inorder left, root, right, thats y checks are in middle
    public boolean isValidBST3(TreeNode root) {
        return inOrder(root, new TreeNode[1]);
    }
    public boolean inOrder(TreeNode root, TreeNode[] prev) {
        if(root==null) return true;
        boolean x = inOrder(root.left,prev);
        if(prev[0]!=null&&prev[0].val>=root.val) return false;
        prev[0] = root;
        return x&&inOrder(root.right,prev);
    }

    
    
    
    
    
    
    //unwanted approach 1.2 use entity class avoid this
    public boolean isValidBST(TreeNode root) {
        Queue<TreeNode> q = new LinkedList();
        Queue<Long[]> lim = new LinkedList();
        q.add(root);
        lim.add(new Long[]{Long.MIN_VALUE,Long.MAX_VALUE});
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            Long[] l = lim.poll();
            if(n.val<=l[0]||n.val>=l[1]) return false;
            if(n.left!=null){
                q.add(n.left);
                lim.add(new Long[]{l[0],(long)n.val});
            }
            if(n.right!=null){
                q.add(n.right);
                lim.add(new Long[]{(long)n.val,l[1]});
            }
        }
        return true;
    }

}

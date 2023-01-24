/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.tree;

/**
 *
 * @author venka
 */

// https://leetcode.com/problems/binary-tree-level-order-traversal/description/

public class BinaryTreeLevelorderTraversal {
    
    //refer https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal for explanations
    
    //APPROACH 1 BFS+level order traversal, create and add in list
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans=new ArrayList();
        Queue<TreeNode> q=new LinkedList();
        if(root!=null) q.add(root);
        while(!q.isEmpty()){
            int siz= q.size();
            List<Integer> sub = new ArrayList();
            while(siz-->0){
                TreeNode n = q.poll();
                sub.add(n.val);
                if(n.left!=null) q.add(n.left);
                if(n.right!=null) q.add(n.right);
            }
            ans.add(sub);
        }
        return ans;
    }
    
    //APPROACH 2 BFS+depth queue, get list from index depth and add in list
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList();
        Queue<Integer> h = new LinkedList();
        List<List<Integer>> ans = new ArrayList();
        if(root!=null) {
            q.add(root);
            h.add(0);
        }
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            int d = h.poll();
            if(ans.size()<=d) ans.add(new ArrayList());
            List<Integer> sub= ans.get(d);
            sub.add(n.val);
            if(n.left!=null) {
                q.add(n.left);
                h.add(d+1);
            }
            if(n.right!=null) {
                q.add(n.right);
                h.add(d+1);
            }
        }   
        return ans;
    }
    
    //APPROACH 3 DFS+depth, get list from index depth and add in list
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        return levelOrder(root, new ArrayList(),0);
    }

    public List<List<Integer>> levelOrder(TreeNode node, List<List<Integer>> ans, int l){
        if(node==null) return ans;
        if(l>=ans.size()) ans.add(new ArrayList());
        ans.get(l).add(node.val);
        levelOrder(node.left,ans,l+1);
        levelOrder(node.right,ans,l+1);
        return ans;
    }
}

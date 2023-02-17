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
    
    class Entity{
        public TreeNode n;
        public int d;
        Entity(TreeNode n, int d){
            this.n=n;
            this.d=d;
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<Entity> q = new LinkedList();
        List<List<Integer>> ans = new ArrayList();
        if(root==null) return ans;
        q.add(new Entity(root,0));
        while(!q.isEmpty()){
            Entity e = q.poll();
            TreeNode n = e.n;
            int d = e.d;
            if(ans.size()<=d) ans.add(new ArrayList());
            ans.get(d).add(n.val);
            if(n.left!=null) q.add(new Entity(n.left,d+1));
            if(n.right!=null) q.add(new Entity(n.right,d+1));
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

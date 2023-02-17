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
//https://leetcode.com/problems/minimum-depth-of-binary-tree/

public class MinimumDepthOfBinaryTree {
    static boolean show = false;
    public static int minimumDepthRec2(TreeNode root){
      if(root==null) return 0;
      if(root.left==null) return minimumDepthRec2(root.right)+1;
      if(root.right==null) return minimumDepthRec2(root.left)+1;
      return Math.min(minimumDepthRec2(root.left),minimumDepthRec2(root.right))+1;
    }
  
    // uniq appoach i created, return valid depth only for leaf nodes and return min of left and right
    // corner case: root null[] if(root==null)return 0;

    public int minDepth2(TreeNode root) {
        if(root==null)return 0;
        return minDepth2(root, 1);
    }
    public int minDepth2(TreeNode node, int d) {
        if(node==null)return Integer.MAX_VALUE;
        if(node.left==null&&node.right==null) return d;
        return Math.min(minDepth2(node.left, d+1),minDepth2(node.right, d+1));
    }
  
    
    // bfs level order
    public static int minimumDepthBfsIte(TreeNode root){
        Queue<TreeNode> q = new LinkedList();
        if(root==null) return 0;
        q.add(root);
        int dep = 0, min=Integer.MAX_VALUE;
        while(!q.isEmpty()){    
            int siz = q.size();
            while(siz-->0){
                TreeNode n = q.poll();
                if(n.left!=null) q.add(n.left);
                if(n.right!=null) q.add(n.right);
                if(n.left==null&&n.right==null)
                    min = Math.min(min,dep+1);
            }
            dep++;
        }
        return min;
    }
  
    // normal bfs
    // O(n)   O(2**h)
    class Entity{
        public TreeNode n;
        public int d;
        Entity(TreeNode n, int d){
            this.n=n;
            this.d=d;
        }
    }
    public int minDepthIte(TreeNode root) {
        Queue<Entity> q = new LinkedList();
        if(root==null) return 0;
        q.add(new Entity(root,1));
        int dep = 0, min=Integer.MAX_VALUE;
        while(!q.isEmpty()){
            Entity e = q.poll();
            TreeNode n = e.n;
            int d = e.d;
            if(n.left!=null) q.add(new Entity(n.left,d+1));
            if(n.right!=null) q.add(new Entity(n.right,d+1));
            if(n.left==null && n.right==null)
                min = Math.min(min, d);
        }
        return min;
    }
      
    
  
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // unwanted
    
      
  // approach:recursion, dfs, send dept inside and acc min depth only if leaf node
  
  public int minDepth(TreeNode root) {
        if(root==null)return 0;
        int[] min = new int[]{Integer.MAX_VALUE};
        minDepth(root, 1, min);
        return min[0];
    }

    public int minDepth(TreeNode node, int d, int[] min) {
        if(node==null)return 0;
        if(node.left==null&&node.right==null)
            min[0] = Math.min(min[0], d);
        minDepth(node.left, d+1, min);
        minDepth(node.right, d+1, min);
        return d;
    }
    
    
    
    
    
    
    
  public  static void main(String[] args){
    BinaryTree t1;

    t1 = new BinaryTree(Arrays.asList(2, 1, 3, 4, null, null, null, 7));
    test(minimumDepthRec(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9,20));
    test(minimumDepthRec(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(minimumDepthRec(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,null,9));
    test(minimumDepthRec(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3));
    test(minimumDepthRec(t1.root), 1);
    t1 = new BinaryTree(Arrays.asList());
    test(minimumDepthRec(t1.root), 0);
    
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, 4, null, null, null, 7));
    test(minimumDepthRec2(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9,20));
    test(minimumDepthRec2(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(minimumDepthRec2(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,null,9));
    test(minimumDepthRec2(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3));
    test(minimumDepthRec2(t1.root), 1);
    t1 = new BinaryTree(Arrays.asList());
    test(minimumDepthRec2(t1.root), 0);
    
    
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, 4, null, null, null, 7));
    test(minimumDepthDfsIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9,20));
    test(minimumDepthDfsIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(minimumDepthDfsIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,null,9));
    test(minimumDepthDfsIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3));
    test(minimumDepthDfsIte(t1.root), 1);
    t1 = new BinaryTree(Arrays.asList());
    test(minimumDepthDfsIte(t1.root), 0);
    

    t1 = new BinaryTree(Arrays.asList(2, 1, 3, 4, null, null, null, 7));
    test(minimumDepthBfsIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9,20));
    test(minimumDepthBfsIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,9));
    test(minimumDepthBfsIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3,null,9));
    test(minimumDepthBfsIte(t1.root), 2);
    t1 = new BinaryTree(Arrays.asList(3));
    test(minimumDepthBfsIte(t1.root), 1);
    t1 = new BinaryTree(Arrays.asList());
    test(minimumDepthBfsIte(t1.root), 0);
    
    
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

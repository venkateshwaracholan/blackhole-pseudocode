/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
import java.util.*;

public class ConstructBTreeFromInorderPreorder {
/*

* 0. preorder[0] is the root of tree
* 1. Iterate 0->len - each element of preorder 
* 2. Find the idx of that element in inorder
* Elements that are left to the idx are left sub-tree and right are right-subtree to that element
* 3. If you now divide the remaining elements in pre-order array into two, 
* the elements immediate to identified idx are part of left sub-tree and at the end it is right tree elements
* 4. recursively add left and right from preorder

*/ 
  

    // Time: O(n) space O(n)
    //Core Idea: recursion and use inorder's index as a map for order
    // skip placing if its index is not in the range
    // use idx[] to keep track of the cur el index in rec
    // readjust range in every recursion just like the preorder bst problem
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> m = new HashMap(0);
        for(int i=0;i<inorder.length;i++) m.put(inorder[i],i);
        return buildTree(preorder,m, 0, preorder.length-1,new int[1]);
    }
    public TreeNode buildTree(int[] p, Map<Integer, Integer> m, int l, int r, int[] i) {
        if(l>r) return null;
        int idx = m.get(p[i[0]]);
        TreeNode n = new TreeNode(p[i[0]++]);
        if(l==r) return n;
        n.left=buildTree(p,m,l,idx-1,i);
        n.right=buildTree(p,m,idx+1,r,i);
        return n;
    }
    // same as above, just checks change
    public static TreeNode recursionBstWay(int p[], Map<Integer, Integer> m, int l, int r, int i[]){
        if(i[0]==p.length) return null;
        int idx = m.get(p[i[0]]);
        if(idx < l || idx>r) return null;  
        TreeNode n = new TreeNode(p[i[0]++];);
        n.left = recursionBstWay(p, m, l, idx-1, i);
        n.right = recursionBstWay(p, m,idx+1, r, i);
        return n;
    }
    public static TreeNode buildTreeBstWay(int[] preorder,int inorder[]){
      Map<Integer, Integer> map = new HashMap();
      for(int i=0;i<inorder.length;i++) map.put(inorder[i], i);
      return recursionBstWay(preorder, map, 0, preorder.length-1, new int[]{0});
    }
    
    

  
    //  first craete node from 1sst el in pr
    // craete val to index map using inroder
    // the iterate rest and call buildtree passing root, val and map
    // if pos of p < pos of n val then check if left is null and place there
    // else pass left to rec with p again to find a suitable place
    // vice versa
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> m = new HashMap(0);
        for(int i=0;i<inorder.length;i++) m.put(inorder[i],i);
        TreeNode n = new TreeNode(preorder[0]);
        for(int i=1;i<preorder.length;i++)
            buildTree(n,preorder[i],m);
        return n;
    }
    public void buildTree(int p, Map<Integer,Integer> m, TreeNode n) {
        if(m.get(p)<m.get(n.val)){
            if(n.left==null) n.left = new TreeNode(p);
            else buildTree(p,m,n.left);
        }else{
            if(n.right==null) n.right = new TreeNode(p);
            else buildTree(p,m,n.right);
        }
    }
  
    
    
    // using inorder traversal -> left root right
    // we use a boundary
    // for left we pass val as boundary
    // for right we pass inf as boundary
    // when left most val matches int[0] return null;
    // increment i in the inroder block
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,inorder, new int[1], new int[1],Integer.MAX_VALUE);
    }
    public TreeNode buildTree(int[] pr, int[] in, int[] p, int[] i, int b) {
        if(p[0]==pr.length || b==in[i[0]]) return null;
        TreeNode n = new TreeNode(pr[p[0]++]);
        n.left = buildTree(pr,in,p,i,n.val);
        i[0]++;
        n.right = buildTree(pr,in,p,i,b);
        return n;
    }
    
    
    // grab first node and create root
    // iterate rest, create a stack
    // in the loop assign prev = null
    // until stack not empty and stack top has inorder[x] pop and asssign to prev and inc x;
    // create a node
    // if prev is null put node to stack peek's left
    // else put to right
    // push node to stack
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Stack<TreeNode> s = new Stack();
        TreeNode n = new TreeNode(preorder[0]);
        s.add(n);
        int x = 0;
        for(int i=1;i<preorder.length;i++){
            TreeNode prev=null;
            while(!s.isEmpty() && s.peek().val==inorder[x]){
                prev = s.pop();
                x++;
            }
            TreeNode t = new TreeNode(preorder[i]);
            if(prev==null){
                prev = s.peek();
                prev.left = t;
            }
            else
                prev.right = t;
            s.push(t);
        }
        return n;
    }
    
    
    
  
  
  public static void main(String args[]){
    buildTreeBstWay(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;
import java.util.Arrays;
import java.util.Stack;


/**
 *
 * @author venkateshwarans
 */
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/


/*
         2
      1     3
    n   4  n  7
   n n 8 9
*/


public class LowestCommonAncestor {
  
  static boolean show = true;
  
  public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b){
    if(root == null || b.val == root.val || a.val == root.val) {
        return root;
    }
    TreeNode x =  lowestCommonAncestor(root.left, a, b);
    TreeNode y =  lowestCommonAncestor(root.right, a, b);
    if(x!=null && y!=null)
      return root;
    else if(x==null)
      return y;
    else
      return x; 
  }
  
  public static TreeNode lowestCommonAncestorIteDFS(TreeNode root, TreeNode a, TreeNode b){
    Stack<TreeNode> stack = new Stack<>();
    TreeNode ancestor = null;
    int ancestorLevel = -1;
    TreeNode node = root;
    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
      root = stack.pop();
      if (stack.size() < ancestorLevel) {
        ancestor = root;
        ancestorLevel = stack.size();
      }
      if (root.val == a.val || root.val == b.val) {
        if (ancestor != null) break;
        ancestor = root;
        ancestorLevel = stack.size();
      }
      node = root.right;
    }
    return ancestor;
  }
  
  public  static void main(String[] args){
    BinaryTree t1;
      
    t1 = new BinaryTree(Arrays.asList(2, 1, 3, null, 4, null, 7, null, null, 8, 9));
//    test(lowestCommonAncestor(t1.root, new TreeNode(1), new TreeNode(3)), 2);
//    test(lowestCommonAncestor(t1.root, new TreeNode(8), new TreeNode(4)), 4);
//    test(lowestCommonAncestor(t1.root, new TreeNode(8), new TreeNode(1)), 1);
//    test(lowestCommonAncestor(t1.root, new TreeNode(9), new TreeNode(7)), 2);
    
    
    
    test(lowestCommonAncestorIteDFS(t1.root, new TreeNode(1), new TreeNode(3)), 2);
    test(lowestCommonAncestorIteDFS(t1.root, new TreeNode(8), new TreeNode(4)), 4);
    test(lowestCommonAncestorIteDFS(t1.root, new TreeNode(8), new TreeNode(1)), 1);
    test(lowestCommonAncestorIteDFS(t1.root, new TreeNode(9), new TreeNode(7)), 2);
    
  }
  
  public static void test(TreeNode n, int exp){
    int got = n.val;
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
  
}

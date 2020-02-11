/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import com.google.gson.Gson;
import java.util.*;

/**
 *
 * @author venkateshwarans
 */
// right left root
// traversal is always relative to the root.

/*
         1
      2     3
    4   5  6  7
   8 9

*/

public class PreOrderTraversal {
  
  // Time: O(n) space: O(n)
  public static ArrayList<Integer> preOrderTraversalRec(TreeNode root){
    return preOrderTraversalRec(root, new ArrayList());
  }
  
  public static ArrayList<Integer> preOrderTraversalRec(TreeNode root, ArrayList<Integer> list){
    if(root==null) return list;
    list.add(root.val);
    preOrderTraversalRec(root.left, list);
    preOrderTraversalRec(root.right, list);
    return list;
  }
  
  // Time: O(n) space: O(n)
  public static ArrayList<Integer> preOrderTraversalIte(TreeNode root){
    ArrayList<Integer> list = new ArrayList();
    Stack<TreeNode> s = new Stack();
    s.add(root);
    while(!s.empty()){
      TreeNode n = s.pop();
      list.add(n.val);
      if(n.right!=null) s.add(n.right);
      if(n.left!=null) s.add(n.left);
    }
    return list;
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    test(preOrderTraversalRec(tree.root), new int[]{1,2,4,8,9,5,3,6,7});
    test(preOrderTraversalIte(tree.root), new int[]{1,2,4,8,9,5,3,6,7});
    
  }
  
  public static void test(ArrayList<Integer> got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
  
}

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
// left root right
// traversal is always relative to the root.


public class InOrderTraversal {
  
  
  // Time: O(n) space: O(n)
  public static ArrayList<Integer> inOrderTraversalRec(TreeNode root){
    return inOrderTraversalRec(root, new ArrayList());
  }
  
  
  public static ArrayList<Integer> inOrderTraversalRec(TreeNode root, ArrayList<Integer> list){
    if(root==null) return list;
    inOrderTraversalRec(root.left, list);
    list.add(root.val);
    inOrderTraversalRec(root.right, list);
    return list;
  }
  
  /*
         1
      2     3
    4   5  6  7
   8 9

*/

  // The ides is to move to left most node upfront putting values in stack 
  // and placing the right node(even if it is null) in the n so that if it has a node will repeatedlytravel left
  // the while which takes us to left is kept before popping from stack deliberately
  // n!=null in first while is to allow the first run.
  // n=n.right is a magic logic line whihc prevent traversing left from an aleready visited node.
  
  // Time: O(n) space: O(n)
  public static ArrayList<Integer> inOrderTraversalIte(TreeNode root){
    ArrayList<Integer> list = new ArrayList();
    Stack<TreeNode> s = new Stack();
    TreeNode n = root;
    while(n!=null || !s.empty()){
      while(n!=null){
        s.add(n);
        n=n.left;
      }
      n = s.pop();
      list.add(n.val);
      n=n.right;
    }
    return list;
  }
  
  
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    test(inOrderTraversalRec(tree.root), new int[]{8,4,9,2,5,1,6,3,7});
    test(inOrderTraversalIte(tree.root), new int[]{8,4,9,2,5,1,6,3,7});
    
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

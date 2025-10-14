/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;

import com.google.gson.Gson;
import java.util.*;

/**
 *
 * @author venkateshwarans
 */
// left right root
// traversal is always relative to the root.

/*
         1
      2     3
    4   5  6  7
   8 9

*/

public class PostOrderTraversal {
  
  // Time: O(n) space: O(n)
  public static ArrayList<Integer> postOrderTraversalRec(TreeNode root){
    return postOrderTraversalRec(root, new ArrayList());
  }
  
  public static ArrayList<Integer> postOrderTraversalRec(TreeNode root, ArrayList<Integer> list){
    if(root==null) return list;
    postOrderTraversalRec(root.left, list);
    postOrderTraversalRec(root.right, list);
    list.add(root.val);
    return list;
  }
  
  /*
           1
        2      3
     4    5   6  7
   8   9
     10 11

  */
  
  // adding nodes twice is the only way to solve this problem.
  // ensure !stack.empty() and peek and compare to choose is it redundant go to right and
  // if not print and make n null so that next thing from the stack is chosen. 

  // Time: O(n) space: O(n)
  public static ArrayList<Integer> postOrderTraversalIte(TreeNode root){
    ArrayList<Integer> list = new ArrayList();
    TreeNode n = root;
    Stack<TreeNode> s = new Stack();
    while(n!=null || !s.empty()){
      while(n!=null){
        s.add(n);
        s.add(n);
        n=n.left;
      }
      n = s.pop();
      if(!s.empty() && s.peek() == n){
        n=n.right;
      }else{
        list.add(n.val);
        n=null;
      }
    }
  
    return list;
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    test(postOrderTraversalRec(tree.root), new int[]{8,9,4,5,2,6,7,3,1});
    test(postOrderTraversalIte(tree.root), new int[]{8,9,4,5,2,6,7,3,1});
    
    tree = new BinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7});
    test(postOrderTraversalIte(tree.root), new int[]{4,5,2,6,7,3,1});
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

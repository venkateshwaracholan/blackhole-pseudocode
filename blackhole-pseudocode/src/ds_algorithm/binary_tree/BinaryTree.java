/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;

import com.google.gson.Gson;

import java.util.*;
import ds_algorithm.Test;
import ds_algorithm.binary_tree.TreeNode;


/**
 *
 * @author venkateshwarans
 */

// 1 2 3 4 5 6 7 8 9 
// 0 1 2 3 4 5 6 7 8 

/*
List to array conversion: return list.stream().mapToInt(i->i).toArray();
*/

/*
         1
      2     3
    4   5  6  7
   8 9

*/

/*
Traversal confusions

preorder and postorder are reverse to each other(reverse and swap left and rights)


    1
  2   3
 4 5 6 7

Inorder - left - root - right

root comes in the middle.
values starts from left most node of the tree


4,2,5,1,6,3,7



preOrder
  root - left - rightx
  
value starts from root value of the tree

1,2,4,5,3,6,7

postorder

left - right - root

root value comes at the last

4,5,2,6,7,3,1

*/

public class BinaryTree {
  public TreeNode root = null;
  
  
  public BinaryTree(){

  }
  
  public BinaryTree(int[] arr){
    if(arr.length == 0){
      return;
    }
    this.root = insertRec(arr, null, 0);
  }
  
  public BinaryTree(List<Integer> arr){
    if(arr.size() == 0){
      return;
    }
    this.root = insertRec(arr, null, 0);
  }
  //2, 1, 3, -1, 4, -1, 7
  
  //BFS insertion of node
  public void insert(int val){
    TreeNode n = new TreeNode(val);
    if(this.root==null){
      this.root = n;
      return;
    }
    Queue<TreeNode> q  =new LinkedList();
    q.add(root);
    while(!q.isEmpty()){
      TreeNode t = q.poll();
      if(t.left==null){
        t.left = n;
        return;
      }else if(t.right == null){
        t.right=n;
        return;
      }
      q.add(t.left);
      q.add(t.right);
    }
  }
  
  public TreeNode insertRec(int[] arr, TreeNode root, int i){
    if(i<arr.length){
      TreeNode n;
      if(arr[i]!=-1){
        n= new TreeNode(arr[i]);
      }else{
        return root;
      }
      root = n;
      root.left = insertRec(arr, root.left, 2*i +1);
      root.right = insertRec(arr, root.right, 2*i +2);
    }
    return root;
  }
  
  public TreeNode insertRec(List<Integer> arr, TreeNode root, int i){
    if(i<arr.size()){
      TreeNode n;
      if(arr.get(i)!=null){
        n = new TreeNode(arr.get(i));
      }else{
        return root;
      }
      root = n;
      root.left = insertRec(arr, root.left, 2*i +1);
      root.right = insertRec(arr, root.right, 2*i +2);
    }
    return root;
  }
  
//  public TreeNode insertIte(int[] arr){
//    int i=0;
//    while(i<arr.length){
//      TreeNode n = new TreeNode(arr[i]);
//      root = n;
//      root.left = insertRec(arr, root.left, 2*i +1);
//      root.right = insertRec(arr, root.right, 2*i +2);
//    }
//    return root;
//  }
  
  public ArrayList<Integer> breadthFirstSearch(){
    return breadthFirstSearch(this.root);
  }
  
  public static ArrayList<Integer> breadthFirstSearch(TreeNode root){
    Queue<TreeNode> q= new LinkedList();
    ArrayList<Integer> list = new ArrayList();
    if(root==null){
      return list;
    }
    q.add(root);
    while(!q.isEmpty()){
      TreeNode n = q.poll();
      list.add(n.val);
      if(n.left!=null){
        q.add(n.left);
      }
      if(n.right!=null){
        q.add(n.right);
      }
    }
    return list;
  }
  
  public ArrayList<Integer> breadthFirstSearchWithNull(){
    
    Queue<TreeNode> q= new LinkedList();
    ArrayList<Integer> list = new ArrayList();
    if(this.root==null) return list;
    q.add(this.root);
    while(!q.isEmpty()){
      int siz = q.size();
      TreeNode n = q.peek();
      Boolean allNull = true;
      while(siz>0){
        n = q.poll();
        list.add(n!=null ? n.val : null);
        if(n!=null) {
          if(n.left!=null) allNull = false;
          if(n.right!=null) allNull = false;
          q.add(n.left);
          q.add(n.right);
        }else{
          q.add(null);
          q.add(null);
        }
        siz--;
      }
      if(allNull) break;
    }
    return list;
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}; 
    ArrayList<Integer> list = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    
    BinaryTree tree = new BinaryTree(arr);
    BinaryTree tree2 = new BinaryTree(list);
    
    Test.test(tree.breadthFirstSearch(), new int[]{1,2,3,4,5,6,7,8,9});
    Test.test(tree2.breadthFirstSearch(), new int[]{1,2,3,4,5,6,7,8,9});
    
    tree.insert(10);
    tree2.insert(10);
    Test.test(tree.breadthFirstSearch(), new int[]{1,2,3,4,5,6,7,8,9,10});
    Test.test(breadthFirstSearch(tree.root), new int[]{1,2,3,4,5,6,7,8,9,10});
    Test.test(tree2.breadthFirstSearch(), new int[]{1,2,3,4,5,6,7,8,9,10});
    
    
    list = new ArrayList(Arrays.asList(1, null, 2, null, null, null, 3));
    tree = new BinaryTree(list);
    Test.test(tree.breadthFirstSearchWithNull(), new ArrayList(Arrays.asList(1,null,2,null,null,null,3)));
    
  }

}

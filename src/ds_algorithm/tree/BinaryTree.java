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

public class BinaryTree {
  TreeNode root = null;
  
  
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
        n= new TreeNode(arr.get(i));
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
    Queue<TreeNode> q= new LinkedList();
    ArrayList<Integer> list = new ArrayList();
    if(this.root==null){
      return list;
    }
    q.add(this.root);
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
    q.add(this.root);
    while(!q.isEmpty()){
      TreeNode n = q.poll();
      if(n!=null){
        list.add(n.val);
//        if(n.left==null && n.right==null){
//          continue;
//        }
        q.add(n.left);
        q.add(n.right);
      }else{
        list.add(null);
      }
      
    }
    return list;
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    
    test(tree.breadthFirstSearch(), new int[]{1,2,3,4,5,6,7,8,9});
    
    tree.insert(10);
    test(tree.breadthFirstSearch(), new int[]{1,2,3,4,5,6,7,8,9,10});
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

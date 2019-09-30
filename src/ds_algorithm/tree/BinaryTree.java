/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import com.google.gson.Gson;
import java.util.*;

class Node{
  int val;
  Node left = null;
  Node right = null;
  
  public Node(int a){
    this.val = a;
  }
}

/**
 *
 * @author venkateshwarans
 */

// 1 2 3 4 5 6 7 8 9 
// 0 1 2 3 4 5 6 7 8 

/*
         1
      2     3
    4   5  6  7
   8 9

*/

public class BinaryTree {
  Node root = null;
  
  
  public BinaryTree(){

  }
  
  public BinaryTree(int[] arr){
    if(arr.length == 0){
      return;
    }
    this.root = insertRec(arr, null, 0);
  }
  
  public Node insertRec(int[] arr, Node root, int i){
    if(i<arr.length){
      Node n = new Node(arr[i]);
      root = n;
      root.left = insertRec(arr, root.left, 2*i +1);
      root.right = insertRec(arr, root.right, 2*i +2);
    }
    return root;
  }
  
//  public Node insertIte(int[] arr){
//    int i=0;
//    while(i<arr.length){
//      Node n = new Node(arr[i]);
//      root = n;
//      root.left = insertRec(arr, root.left, 2*i +1);
//      root.right = insertRec(arr, root.right, 2*i +2);
//    }
//    return root;
//  }
  
  public int[] preOrderTraversalRec(){
    ArrayList<Integer> list = new ArrayList();
    preOrderTraversalRec(this.root, list);
    return list.stream().mapToInt(i->i).toArray();
  }
  
  public void preOrderTraversalRec(Node n, ArrayList<Integer> list){
    if(n!=null){
      list.add(n.val);
      preOrderTraversalRec(n.left, list);
      preOrderTraversalRec(n.right,list);
      
    }
  }
  
  public int[] inOrderTraversalRec(){
    ArrayList<Integer> list = new ArrayList();
    inOrderTraversalRec(this.root, list);
    return list.stream().mapToInt(i->i).toArray();
  }
  
  public void inOrderTraversalRec(Node n, ArrayList<Integer> list){
    if(n!=null){
      inOrderTraversalRec(n.left, list);
      list.add(n.val);
      inOrderTraversalRec(n.right, list);
    }
  }
  
  public int[] postOrderTraversalRec(){
    ArrayList<Integer> list = new ArrayList();
    postOrderTraversalRec(this.root, list);
    return list.stream().mapToInt(i->i).toArray();
  }
  
  public void postOrderTraversalRec(Node n, ArrayList<Integer> list){
    if(n!=null){
      postOrderTraversalRec(n.left, list);
      postOrderTraversalRec(n.right, list);
      list.add(n.val);
    }
  }
  
  public void breadthFirstSearch(){
    Queue<Node> q= new LinkedList();
    q.add(this.root);
    while(!q.isEmpty()){
      Node n = q.poll();
      System.out.println(n.val);
      if(n.left!=null){
        q.add(n.left);
      }
      if(n.right!=null){
        q.add(n.right);
      }
      
    }
  }
  
  public int[] preOrderTraversalIte(){
    ArrayList<Integer> list = new ArrayList();
    Stack<Node> s = new Stack();
    s.add(this.root);
    while(!s.empty()){
      Node n = s.pop();
      list.add(n.val);
      if(n.right!=null){
        s.add(n.right);
      }
      if(n.left!=null){
        s.add(n.left);
      }
    }
    return list.stream().mapToInt(i->i).toArray();
  }
  
/*
         1
      2     3
    4   5  6  7
   8 9

*/
  
  public int[] inOrderTraversalIte(){
    ArrayList<Integer> list = new ArrayList();
    Stack<Node> s = new Stack();
    Node n = this.root;
    while(n!=null || !s.empty()){
      while(n!=null){
        s.add(n);
        n=n.left;
      }
      n = s.pop();
      list.add(n.val);
      n = n.right;
    };
    return list.stream().mapToInt(i->i).toArray();
  }
  
  
  /*
         1
      2     3
    4   5  6  7
   8 9
  */
  public int[] postOrderTraversalIte(){
    ArrayList<Integer> list = new ArrayList();
    Stack<Node> s = new Stack();
    Node n = this.root;
    while(n!=null || !s.empty()){
      while(n!=null){
        s.add(n);
        s.add(n);
        n=n.left;
      }
      n = s.pop();
      if(!s.empty() && s.peek() == n){
        n = n.right;
      }else{
        list.add(n.val);
        n=null;
      }
    };
    return list.stream().mapToInt(i->i).toArray();
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    test(tree.preOrderTraversalRec(), new int[]{1,2,4,8,9,5,3,6,7});
    test(tree.preOrderTraversalIte(), new int[]{1,2,4,8,9,5,3,6,7});
    
    test(tree.inOrderTraversalRec(), new int[]{8,4,9,2,5,1,6,3,7});
    test(tree.inOrderTraversalIte(), new int[]{8,4,9,2,5,1,6,3,7});
    
    test(tree.postOrderTraversalRec(), new int[]{8,9,4,5,2,6,7,3,1});
    test(tree.postOrderTraversalIte(), new int[]{8,9,4,5,2,6,7,3,1});
  }

  public static void test(int got[], int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
}

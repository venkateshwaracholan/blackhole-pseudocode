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




// bfs with adding right node first and therefore adding first val from queue to answer.
// Time: O(n) space: O(n)
// core idea, add right nodes firsts will let them stay in the front when level ends
// in level order traversal with two loops, we have to go level by level
// and print first node

public class RightSideView {
  public static ArrayList<Integer> rightSideViewBfs(TreeNode root){
    ArrayList<Integer> list = new ArrayList();
    if(root==null) return list;
    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    while(!q.isEmpty()){
        int n = q.size();
        list.add(q.peek().val);
        while(n>0){
            TreeNode node = q.poll();
            if(node.right!=null) q.add(node.right);
            if(node.left!=null) q.add(node.left);
            n--;
        }
    }
    return list;
  }
  
  /*
           1
        2      3
     4    5   6  7
   8   9
  */
  
  public static ArrayList<Integer> rightSideViewDfsRecAlt(TreeNode root){
    return rightSideViewDfsRecAlt(root, 0, new ArrayList());
  }
  
  // Time: O(n) space: O(n)
  // Core idea, adding right first and left second
  // if first time depth is equal to list size we add them to list.
  // since list size is increasing there is no way nodes at the same level will match the same condition again
  public static ArrayList<Integer> rightSideViewDfsRecAlt(TreeNode root, int d, ArrayList<Integer> list){
    if(root==null) return list;
    if(d==list.size()){
      list.add(root.val);
    }
    rightSideViewDfsRecAlt(root.right, d+1, list);
    rightSideViewDfsRecAlt(root.left, d+1, list);
    return list;
  }
  
  public static ArrayList<Integer> rightSideViewDfsRec(TreeNode root){
    ArrayList<Integer> list = new ArrayList();
    HashMap<Integer, Integer> map= new HashMap();
    int depth = rightSideViewDfsRec(root, map, 0);
    for(int i=0;i<depth;i++){
      list.add(map.get(i));
    }
    return list;
  }
  
  // Time: O(n) space: O(n)
  // Core idea - over write the depth to node map by traversing from left to right
  // and then we can iterate depth and fetch value node from map which is done in ablove code 
  public static int rightSideViewDfsRec(TreeNode root, HashMap<Integer, Integer> map, int d){
    if(root==null) return d;
    map.put(d,root.val);
    int d1 = rightSideViewDfsRec(root.left, map, d+1);
    int d2 = rightSideViewDfsRec(root.right, map, d+1);
    return Math.max(d1,d2);
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    test(rightSideViewBfs(tree.root), new int[]{1,3,7,9});
    test(rightSideViewDfsRec(tree.root), new int[]{1,3,7,9});
    test(rightSideViewDfsRecAlt(tree.root), new int[]{1,3,7,9});
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

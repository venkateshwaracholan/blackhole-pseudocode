/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import com.google.gson.Gson;
import static ds_algorithm.tree.PreOrderTraversal.preOrderTraversalRec;
import java.util.*;

/**
 *
 * @author venkateshwarans
 */
//https://leetcode.com/problems/serialize-and-deserialize-binary-tree



public class SerializeAndDeserializeBinaryTree {
  
  public static String serializeDfs(TreeNode root){
    
    ArrayList<Integer> list = serializeDfs(root,new ArrayList());
    StringBuilder sb = new StringBuilder();
    for(int i=0;i<list.size();i++){
      Integer l = list.get(i);
      if(l==null)
        sb.append("null");
      else
        sb.append(l);
      if(i!=list.size()-1)
        sb.append(",");
    }
    return sb.toString();
  }
  
  /*
         1
      2     3
    4   5  6  7
   8 9

*/
  
  public static ArrayList<Integer> serializeDfs(TreeNode root, ArrayList<Integer> ans){
    if(root==null){
      ans.add(null);
      return ans;
    }
    ans.add(root.val);
    serializeDfs(root.left, ans);
    serializeDfs(root.right, ans);
    return ans;
  }
  
  public static String serializeDfsString(TreeNode root, String ans){
    if(root==null){
      ans+="null,";
      return ans;
    }
    String left = serializeDfsString(root.left, ans);
    String right =serializeDfsString(root.right, ans);
    return root.val+"," + left+ right;
  }
  
  public static TreeNode deSerializeDfs(String str){
    String arr[] = str.split(",");
    ArrayList<String> list = new ArrayList(Arrays.asList(arr));
    return deSerializeDfs(list);
  }
  
  public static TreeNode deSerializeDfs(ArrayList<String> list){
    if(list.get(0).equals("null")){
      list.remove(0);
      return null;
    }
    TreeNode node = new TreeNode(Integer.valueOf(list.get(0)));
    list.remove(0);
    node.left = deSerializeDfs(list);
    node.right = deSerializeDfs(list);
    return node;
  }
  
  
  /*
         1
      2     3
    4   5  6  7
   8 9

  */
  
  public static String serializeBfs(TreeNode node){
    String ans = "";
    if(node==null){
      return ans;
    }
    Queue<TreeNode> q = new LinkedList();
    q.add(node);
    ans+=node.val+",";
    while(!q.isEmpty()){
      TreeNode n = q.poll();
      ans += (n.left==null ? "null" : n.left.val)+",";
      ans += (n.right==null ? "null" : n.right.val)+",";
      if(n.left!=null) q.add(n.left);
      if(n.right!=null) q.add(n.right);
    }
    return ans;
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    
    test(serializeDfsString(tree.root,""), "1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null,");
    
    test(serializeDfs(tree.root,new ArrayList()), new ArrayList<>(Arrays.asList(1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null)));
    test(serializeDfs(tree.root), "1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null");
    
    test(preOrderTraversalRec(deSerializeDfs("1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null,")), new int[]{1,2,4,8,9,5,3,6,7}); 
    
    tree = new BinaryTree(new int[]{1, 2, 3, 4, -1,-1,7, 8, 9});
    
    test(serializeBfs(tree.root), "1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null,");
  }
  
  public static void test(String gotStr, String expStr){
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gotStr);
    System.out.println("expected: "+expStr);
  }
  
  public static void test(ArrayList<Integer> got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
  
  public static void test(ArrayList<Integer> got, ArrayList<Integer> exp){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
}

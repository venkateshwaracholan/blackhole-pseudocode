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
//https://leetcode.com/problems/serialize-and-deserialize-binary-tree



public class SerializeAndDeserializeBinaryTree {
  
  public static String serialize(TreeNode root){
    
    ArrayList<Integer> list = serializeHelper(root,new ArrayList());
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
  
  public static ArrayList<Integer> serializeHelper(TreeNode root, ArrayList<Integer> ans){
    if(root==null){
      ans.add(null);
      return ans;
    }
    ans.add(root.val);
    serializeHelper(root.left, ans);
    serializeHelper(root.right, ans);
    return ans;
  }
  
  public static String serializeHelper(TreeNode root, String ans){
    if(root==null){
      ans+="null,";
      return ans;
    }
    String left = serializeHelper(root.left, ans);
    String right =serializeHelper(root.right, ans);
    return root.val+"," + left+ right;
  }
  
  /*
         1
      2     3
    4   5  6  7
   8 9

  */
  
  public static TreeNode deSerialize(String str){
    String arr[] = str.split(",");
    for(int i=0;i<str.length();i++){
      String s = arr[i];
      TreeNode t = null;
      if(!s.equals("null")){
        t = new TreeNode(Integer.valueOf(s));
      }
      
    }
    return new TreeNode(1);
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    
    test(serializeHelper(tree.root,""), "1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null");
    
    test(serializeHelper(tree.root,new ArrayList()), new ArrayList<>(Arrays.asList(1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null)));
    test(serialize(tree.root), "1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null");
    
  }
  
  
  public static void test(String gotStr, String expStr){
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gotStr);
    System.out.println("expected: "+expStr);
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

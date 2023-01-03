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
  
    // dfs
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serialize(root, new StringBuilder()).toString();
    }

    public StringBuilder serialize(TreeNode root, StringBuilder sb) {
        if(root==null){
            sb.append("#,");
            return sb;
        }
        sb.append(root.val).append(',');
        serialize(root.left, sb);
        serialize(root.right,sb);
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        return deserialize(nodes, new int[1]);
    }
    public TreeNode deserialize(String[] nodes, int[] i) {
        if(nodes[i[0]].equals("#")) {
            i[0]++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodes[i[0]++]));
        root.left = deserialize(nodes,i);
        root.right = deserialize(nodes,i);
        return root;
    }
    
    
    
    
    //bfs
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> q = new LinkedList();
        StringBuilder sb = new StringBuilder();
        if(root!=null) q.add(root);
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            if(n==null) {
                sb.append("#,");
                continue;
            }
            else sb.append(n.val).append(",");
            q.add(n.left);
            q.add(n.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<TreeNode> q = new LinkedList();
        String[] nodes = data.split(",");
        if(nodes.length==0 || nodes[0].equals(""))  return null;
        int i=1;
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        q.add(root);
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            n.left = create(nodes[i++]);
            n.right = create(nodes[i++]);
            if(n.left!=null)q.add(n.left);
            if(n.right!=null)q.add(n.right);
        }
        return root;
    }

    public TreeNode create(String val){
        if(val.equals("#")) return null;
        else return new TreeNode(Integer.valueOf(val));
    }
    
    
    
    
    //the below solutions are worse
    
    
    
    
    
    
  // Time: O(n) space: O(n)
  // wrapper class to create string using sb
  // can remove trailing null in this impl, please do that
  // once the above step is done, it is space efficient
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
  
  // Time: O(n) space: O(n)
  // dfs serialize inorder traversal
  // the actual recursive list constructor for seriliazation
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
  
  // serialize directly with strings, but removing triling nulls are hedious
  // Time: O(n) space: O(n)
  public static String serializeDfsString(TreeNode root, String ans){
    if(root==null){
      ans+="null,";
      return ans;
    }
    String left = serializeDfsString(root.left, ans);
    String right =serializeDfsString(root.right, ans);
    return root.val+"," + left+ right;
  }
  
  
  // deserialize 
  // Time: O(n) space: O(n)
  // wrapper for splitting and creating list
  public static TreeNode deSerializeDfs(String str){
    String arr[] = str.split(",");
    ArrayList<String> list = new ArrayList(Arrays.asList(arr));
    return deSerializeDfs(list);
  }
  
  // Time: O(n) space: O(n)
  // actual code which uses dfs to recusrsively remove and assign to left and right
  // it works because it was serialized with inorder traversal
  // the check for size to be empty is necessary
  public static TreeNode deSerializeDfs(ArrayList<String> list){
    if(list.size()==0)
      return null;
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
  // Leetcode serialize space efficient
  // Time: O(n) space: O(n)
  // append null for children, remove trailing nulls(avlodhan) and construct string out of it.
  public static String serializeBfs(TreeNode root){
    StringBuilder ans = new StringBuilder();
    if(root==null){
      return "";
    }
    ArrayList<Integer> list = new ArrayList();
    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    list.add(root.val);
    while(!q.isEmpty()){
      TreeNode n = q.poll();
      if(n.left==null)
        list.add(null);
      else{
        list.add(n.left.val);
        q.add(n.left);
      }
      if(n.right==null)
        list.add(null);
      else{
        list.add(n.right.val);
        q.add(n.right);
      }
    }
    for(int i = list.size()-1;i>0;i--){
      if(list.get(i)==null){
        list.remove(i);
      }else{
        break;
      }
    }
    for(int i = 0;i<list.size();i++){
      ans.append(list.get(i));
      ans.append(",");
    }
    
    return ans.toString();
  }
  
  // Leetcode deserialize for above serialized structure
  // Time: O(n) space: O(n)
  // split string to form list
  // use a queue to assign children, remove and assign 2 elements for each node in queue
  // check list size before removing second time.
  public static TreeNode deSerializeBfs(String str){
    if(str.length()==0)
      return null;
    ArrayList<Integer> list = new ArrayList();
    String[] strArr = str.split(",");
    for(int i=0;i<strArr.length;i++){
      if(strArr[i].equals("null")){
        list.add(null);
      }else{
        list.add(Integer.valueOf(strArr[i]));
      }
    }
    if(list.size()==0 || list.get(0)==null)
      return null;
    TreeNode root = new TreeNode(list.remove(0));
    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    while(!q.isEmpty() && list.size()>0){
      TreeNode n = q.poll();
      //System.out.println(n.val);
      if(list.get(0)!=null){
        n.left = new TreeNode(list.remove(0));
        q.add(n.left);
      }else{
        list.remove(0);
      }
      if(list.size()>0){
        if(list.get(0)!=null){
          n.right = new TreeNode(list.remove(0));
          q.add(n.right);
        }else{
          list.remove(0);
        }
      }
    }
    return root;
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    
//    test(serializeDfsString(tree.root,""), "1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null,");
//    
//    test(serializeDfs(tree.root,new ArrayList()), new ArrayList<>(Arrays.asList(1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null)));
//    test(serializeDfs(tree.root), "1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null");
//    
//    test(preOrderTraversalRec(deSerializeDfs("1,2,4,8,null,null,9,null,null,5,null,null,3,6,null,null,7,null,null,")), new int[]{1,2,4,8,9,5,3,6,7}); 
//    

    tree = new BinaryTree(new int[]{1, -1, 2, -1,-1,-1, 3});    
    test(serializeDfs(tree.root), "1,null,2,null,3,");
    tree = new BinaryTree(new int[]{1, 2, 3, -1,-1,-1, 4});
    test(serializeDfs(tree.root), "1,2,null,null,3,null,4,");
    tree = new BinaryTree(new int[]{1, 2});
    test(serializeDfs(tree.root), "1,2,");
    
    tree.root = deSerializeDfs("1,2,null,null,3,null,4,");
    test(serializeDfs(tree.root), "1,2,null,null,3,null,4,");
    
    tree.root = deSerializeDfs("1,2,");
    test(serializeDfs(tree.root), "1,2,");
    

    tree = new BinaryTree(new int[]{1, -1, 2, -1,-1,-1, 3});    
    test(serializeBfs(tree.root), "1,null,2,null,3,");
    tree = new BinaryTree(new int[]{1, 2, 3, -1,-1,-1, 4});
    test(serializeBfs(tree.root), "1,2,3,null,null,null,4,");
    tree = new BinaryTree(new int[]{1, 2});
    test(serializeBfs(tree.root), "1,2,");
    
    tree.root = deSerializeBfs("1,2,3,null,null,null,4,");
    test(serializeBfs(tree.root), "1,2,3,null,null,null,4,");
    
    tree.root = deSerializeBfs("1,2,");
    test(serializeBfs(tree.root), "1,2,");

      
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

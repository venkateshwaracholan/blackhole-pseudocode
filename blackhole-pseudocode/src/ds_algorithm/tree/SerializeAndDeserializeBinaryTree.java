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
  
    //APPROACH 1 serialize DFS preorder + sb, put N, for null nand return, put val, in perorder
    //           deserialize DFS preorder + i[], split by ',', i==n or i=='N' i[0]++ and return null, create Treenode from Integer.valueOf, i[0]++ n.left=rec, n.right=rec, return n
    
    // dfs
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serialize(root,new StringBuilder()).toString();
    }
    public StringBuilder serialize(TreeNode n, StringBuilder sb) {
        if(n==null){
            sb.append("N,");
            return sb;
        }
        sb.append(n.val).append(',');
        serialize(n.left,sb);
        serialize(n.right,sb);
        return sb;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split(",");
        return deserialize(s,new int[1]);
    }
    public TreeNode deserialize(String[] s, int[] i) {
        if(i[0]==s.length||s[i[0]].equals("N")) {
            i[0]++;
            return null;
        }
        TreeNode n = new TreeNode(Integer.valueOf(s[i[0]++]));
        n.left = deserialize(s,i);
        n.right = deserialize(s,i);
        return n;
    }
    
    
    //APPROACH 2 serialize BFS + sb, put N, for null nand continue, put val, and add in q, allow nulls
    //           deserialize BFS + create('N'->null else TreenNode) , len=0 or s[0]='N' ret null else create root node, 
    //           x=1 bfs with root, n.left=create(x++), n.right=create(x++), add left and right in q if not null
    //bfs
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> q=new LinkedList();
        q.add(root);
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            if(n==null){
                sb.append("N,");
                continue;
            }
            sb.append(n.val).append(',');
            q.add(n.left);
            q.add(n.right);
        }
        return sb.toString();
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split(",");
        Queue<TreeNode> q=new LinkedList();
        if(s.length==0 || s[0].equals("N")) return null;
        TreeNode root = create(s[0]);
        q.add(root);
        int x=1;
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            n.left = create(s[x++]);
            n.right = create(s[x++]);
            if(n.left!=null) q.add(n.left);
            if(n.right!=null) q.add(n.right);
        }
        return root;
    }
    public TreeNode create(String s){
        if(s.equals("N")) return null;
        else return new TreeNode(Integer.valueOf(s));
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

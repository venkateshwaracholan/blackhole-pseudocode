/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;

import com.google.gson.Gson;

import static ds_algorithm.binary_tree.PreOrderTraversal.preOrderTraversalRec;

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

    // Decodes your encoded data to tree.
     public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode node, StringBuilder sb) {
        if(node==null){
            sb.append('n').append(',');
            return;
        }
        sb.append(node.val).append(',');
        serialize(node.left, sb);
        serialize(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        return deserialize(vals, new int[]{0});
    }

    public TreeNode deserialize(String[] data, int[] idx) {
        if(idx[0]==data.length || data[idx[0]].equals("n")){
            idx[0]++;
            return null;
        }
        int val = Integer.valueOf(data[idx[0]]);
        var node = new TreeNode(val);
        idx[0]++;
        node.left = deserialize(data, idx);
        node.right = deserialize(data, idx);
        return node;
    }
    
    
    //APPROACH 2 serialize BFS + sb, put N, for null nand continue, put val, and add in q, allow nulls
    //           deserialize BFS + create('N'->null else TreenNode) , len=0 or s[0]='N' ret null else create root node, 
    //           x=1 bfs with root, n.left=create(x++), n.right=create(x++), add left and right in q if not null
    //bfs
    // Encodes a tree to a single string.
    // Encodes a tree to a single string.
    public String serializeBFS(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        var queue = new LinkedList<TreeNode>(); 
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node==null){
                sb.append("n,");
                continue;
            }
            sb.append(node.val).append(",");
            queue.add(node.left);
            queue.add(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeBFS(String data) {
        var queue = new LinkedList<TreeNode>();
        String[] vals = data.split(",");
        if(vals.length==0 || vals[0].equals("n")){
            return null;
        }
        int idx = 1;
        TreeNode root = create(vals[0]);
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode parent = queue.poll();
            parent.left = create(vals[idx]);
            idx++;
            parent.right = create(vals[idx]);
            idx++;
            if(parent.left!=null){
                queue.add(parent.left);
            }
            if(parent.right!=null){
                queue.add(parent.right);
            }
        }
        return root;
    }
    
    public TreeNode create(String val){
        if(val.equals("n")){
            return null;
        }
        else{
            return new TreeNode(Integer.valueOf(val));
        }
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

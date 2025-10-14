/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/binary-tree-paths/
import java.util.*;
import ds_algorithm.Pair;

public class BinaryTreePaths {
  
//  Time: O(n) space: O(n)
//  approach: dfs
//  core idea: pass string to append, if leaf add s to result,
//  else add ->
//  dfs
//  return list
  public List<String> binaryTreePaths(TreeNode root) {
      return binaryTreePaths(root, new ArrayList(), "");
  }

  public List<String> binaryTreePaths(TreeNode root, List<String> list, String s) {
      if(root==null) return new ArrayList();
      s+=root.val;
      if(root.left==null && root.right==null) list.add(s);
      else s+="->";
      binaryTreePaths(root.left, list, s);
      binaryTreePaths(root.right, list, s);
      return list;
  }
  
//  Time: O(n) space: O(n)
//  approach: dfs
//  same abolve solution with string builder, 100% faster
  public List<String> binaryTreePathsStringbuilder(TreeNode root) {
      return binaryTreePathsStringbuilder(root, new ArrayList(), new StringBuilder());
  }

  public List<String> binaryTreePathsStringbuilder(TreeNode root, List<String> list, StringBuilder s)     {
      if(root==null) return list;
      s.append(root.val);
      if(root.left==null && root.right==null) list.add(s.toString());
      else s.append("->");
      binaryTreePathsStringbuilder(root.left, list, new StringBuilder(s));
      binaryTreePathsStringbuilder(root.right, list, new StringBuilder(s));
      return list;
  }
  
  

  
//  Time: O(n) space: O(n)
//  do things on the go
//  pass s+root.val+"->"
//  andd if leaf append root.val also and put in the list
  public List<String> binaryTreePathsAlt(TreeNode root) {
    return binaryTreePathsAlt(root, new ArrayList(), "");
  }

  public List<String> binaryTreePathsAlt(TreeNode root, List<String> list, String s)     {
    if(root==null) return new ArrayList();
    if(root.left==null && root.right==null) list.add(s+root.val);
    binaryTreePathsAlt(root.left, list, s+root.val+"->");
    binaryTreePathsAlt(root.right, list, s+root.val+"->");
    return list;
  }
  
  
//  Time O(n) space O(n)
//  Iterative DFS using stack
//  we use a pair because we need to keep track of path
  public List<String> binaryTreePathsDFSIte(TreeNode root) {
      Stack<Pair<TreeNode,String>> s= new Stack();
      s.add(new Pair(root,""));
      List<String> res = new ArrayList();
      while(!s.isEmpty()){
          Pair<TreeNode,String> x = s.pop();
          TreeNode n = x.getFirst();
          String path = x.getSecond();
          if(n!=null){
              if(n.left==null&&n.right==null)
                  res.add(path+n.val);
              s.add(new Pair(n.left,path+n.val+"->"));
              s.add(new Pair(n.right,path+n.val+"->"));
          } 
      }
      return res;
  }
  // same as above with map instead of a pair
    public List<String> binaryTreePathsMap(TreeNode root) {
      Stack<TreeNode> s = new Stack();
      List<String> res = new ArrayList();
      Map<TreeNode, String> map = new HashMap();
      map.put(root, "");
      s.add(root);
      while(!s.isEmpty()){
            TreeNode t = s.pop();
            String a = map.get(t);
            if(t.left==null && t.right==null)
                res.add(a+t.val);
            if(t.left!=null){
                s.add(t.left);
                map.put(t.left, a+ t.val+"->");
            }
            if(t.right!=null){
                s.add(t.right);
                map.put(t.right, a+ t.val+"->");
            }
      }
      return res;
    }
  
  
//  Time O(n) space O(n)
//  Iterative bfs using queue
//  we use a pair because we need to keep track of path
  public List<String> binaryTreePathsBFS(TreeNode root) {
    Queue<Pair<TreeNode,String>> q= new LinkedList();
    q.add(new Pair(root,""));
    List<String> res = new ArrayList();
    while(!q.isEmpty()){
        Pair<TreeNode,String> x = q.poll();
        TreeNode n = x.getFirst();
        String path = x.getSecond();
        if(n!=null){
            if(n.left==null&&n.right==null)
                res.add(path+n.val);
            q.add(new Pair(n.left,path+n.val+"->"));
            q.add(new Pair(n.right,path+n.val+"->"));
        } 
    }
    return res;
  }
  
  // same as above with map instead of a pair
  public List<String> binaryTreePathsMapIte(TreeNode root) {
        List<String> ans = new ArrayList();
        Queue<TreeNode> q = new LinkedList();
        Map<TreeNode, String> map = new HashMap();
        q.add(root);
        map.put(root, "");
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            String s = map.get(n);
            if(n.left==null && n.right==null)
                ans.add(s+n.val);
            if(n.left!=null){
                q.add(n.left);
                map.put(n.left, s+n.val+"->");
            } 
            if(n.right!=null){
                q.add(n.right);
                map.put(n.right, s+n.val+"->");
            }
        }
        return ans;
    }
  
  
    // using 2 queues instead of a map
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList();
        Queue<TreeNode> q = new LinkedList();
        Queue<String> qs = new LinkedList();
        q.add(root);
        qs.add(root.val+"");
        while(!q.isEmpty()){
            TreeNode t = q.poll();
            String s = qs.poll();
            if(t.left==null&&t.right==null) ans.add(s);
            if(t.left!=null){
                q.add(t.left);
                qs.add(s+"->"+t.left.val);
            }
            if(t.right!=null){
                q.add(t.right);
                qs.add(s+"->"+t.right.val);
            }
        }
        return ans;
    }
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

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
      binaryTreePathsStringbuilder(root.left, list, s);
      binaryTreePathsStringbuilder(root.right, list, s);
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
  
  
}

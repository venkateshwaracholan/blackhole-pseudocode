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
// https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/532/week-5/3315
// https://www.youtube.com/watch?v=Mu4b6dLfCks

public class StringValidRootToLeafPath {
  
//  Time: O(n) space: O(n)
//  Appraoch: tail recursion
//  we use or to traverse into both sides and then come out if its not possible
//  if index becomes last, we check if its a leaf and return
  public boolean dfs(TreeNode root, int [] arr, int pos){
    if(root==null)return false;
    if(root.val!=arr[pos]) return false;
    if(pos == arr.length-1) 
      return root.left==null && root.right==null;
    return dfs(root.left,arr,pos+1) || dfs(root.right,arr,pos+1);
  }
  
  public boolean isValidSequence(TreeNode root, int[] arr) {
    if(root == null)
        return false; 
    return dfs(root,arr,0); 
  }
  
  
//  Time: O(d) space: O(n)
//  Approach: tail recursion
//  since pos can never reach last as if it reaches last we will check if leaf and return
//  so i can always peek pos+1 safely in the below code
//  so i will check if ledt is equal to pos+1 and then go inside, vice versa for right
//  and return false if none matches.
//  so that i only visit D times, but d becomes n if tree is linear
  public boolean dfsAlt(TreeNode root, int [] arr, int pos){
    if(root==null)return false;
    if(root.val!=arr[pos]) return false;
    if(pos == arr.length-1) 
      return root.left==null && root.right==null;
    if(root.left!=null && root.left.val==arr[pos+1]){
      return dfsAlt(root.left,arr,pos+1);
    }
    if(root.right!=null && root.right.val==arr[pos+1])
      return dfsAlt(root.right,arr,pos+1);
    return false;
  }
  
  public boolean isValidSequenceAlt(TreeNode root, int[] arr) {
    if(root == null)
        return false; 
    return dfs(root,arr,0); 
  }
}

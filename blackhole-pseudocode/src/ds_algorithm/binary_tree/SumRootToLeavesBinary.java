/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;

import ds_algorithm.Test;

import static ds_algorithm.binary_tree.SerializeAndDeserializeBinaryTree.deSerializeBfs;

import java.util.*;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers

public class SumRootToLeavesBinary {
  
  
  public static void sumRootToLeaf(TreeNode root,List<Integer> list, int arr[]) {
      if(root==null){
          return;
      }
      list.add(root.val);
      sumRootToLeaf(root.left,list,arr);
      sumRootToLeaf(root.right,list,arr);
      if(root.left==null && root.right==null){
          int sum = 0;
          System.out.println(list.size());
          for(int i=0;i<list.size();i++){
              sum+= list.get(i) * Math.pow(2,(list.size()-1)-i);
          }
          arr[0]+=sum; 
      }
      list.remove(list.size()-1);
  }
  
  public static int sumRootToLeaf(TreeNode root){
      int ans[] =  new int[1];
      sumRootToLeaf(root, new ArrayList(),ans);
      return ans[0];
  }
   
  public static void main(String args[]){
    BinaryTree tree =new BinaryTree();
    Test.test(sumRootToLeaf(deSerializeBfs("1,0,1,0,1,0,1")), 22);
    
  }
}

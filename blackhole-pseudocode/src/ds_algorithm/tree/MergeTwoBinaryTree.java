/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

/**
 *
 * @author venkateshwarans
 */

/*
  https://leetcode.com/problems/merge-two-binary-trees/
  617. Merge Two Binary Trees
  Easy

  Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

  You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

  Example 1:

  Input: 
    Tree 1                     Tree 2                  
            1                         2                             
           / \                       / \                            
          3   2                     1   3                        
         /                           \   \                      
        5                             4   7                  
  Output: 
  Merged tree:
         3
        / \
       4   5
      / \   \ 
     5   4   7

TRY: try to implement eth same without destroying the input binary trees

            1                         1                             
           /                           \                            
          1                             1                        
                                         \                      
                                          1  

*/

import com.google.gson.Gson;
import ds_algorithm.tree.BinaryTree;
import java.util.*;


public class MergeTwoBinaryTree {
  
  // Time: O(n) space: O(n)
  // DFS rec
  // core Idea - merge second binary tree into first
  // if nodes are present add value to t1
  // or return t1 or t2whichever is present so that it can be assigned to parent t1 
  public static TreeNode mergeTreesRec(TreeNode t1, TreeNode t2) {  
    if(t1==null){
      return t2;
    }
    if(t2==null){
      return t1;
    }
    t1.val += t2.val;
    t1.left = mergeTreesRec(t1.left,t2.left);
    t1.right = mergeTreesRec(t1.right,t2.right);
    return t1;
  }
  
  // Time: O(n) space: O(n)
  // DFS Ite
  // core Idea - merge second binary tree into first
  // if t1 is null return t2 is mandatory
  // use stack to do iterative dfs
  // use an array to wrap t1 and t2
  // if either is null continue becoz u cant add t2 to t if any one is null
  // if t1s children are null just assign t2s children
  // if not then add t1 and t2 to stack so that they can traverse further
  public static TreeNode mergeTreesIte(TreeNode t1, TreeNode t2) { 
    if(t1 == null)
      return t2;
    Stack<TreeNode[]> s = new Stack();
    TreeNode arr[] = new TreeNode[]{t1,t2};
    s.add(arr);
    while(!s.empty()){
      TreeNode narr[] = s.pop();
      if(narr[0]==null || narr[1]==null){
        continue;
      }
      narr[0].val += narr[1].val;
      if(narr[0].left == null){
        narr[0].left = narr[1].left;
      }else{
        s.add(new TreeNode[]{narr[0].left, narr[1].left});
      }
      if(narr[0].right == null){
        narr[0].right = narr[1].right;
      }else{
        s.add(new TreeNode[]{narr[0].right, narr[1].right});
      }
    }
      
      
    return t1;
  }
  
  public static void main(String[] args){

//    BinaryTree t1 = new BinaryTree(new int[]{2, 1, 3, -1, 4, -1, 7});
//    BinaryTree t2 = new BinaryTree(new int[]{1,3,2,5});
    BinaryTree t1 = new BinaryTree(Arrays.asList(2, 1, 3, null, 4, null, 7));
    BinaryTree t2 = new BinaryTree(Arrays.asList(1,3,2,5));
    mergeTreesRec(t1.root,t2.root);
    test(t1.breadthFirstSearch(), new int[]{3,4,5,5,4,7});
    
//    BinaryTree t3 = new BinaryTree(Arrays.asList(2, 1, 3, null, 4, null, 7));
//    BinaryTree t4 = new BinaryTree(Arrays.asList(1,3,2,5));
//    mergeTreesIte(t3.root,t4.root);
//    test(t3.breadthFirstSearch(), new int[]{3,4,5,5,4,7});

    BinaryTree t3 = new BinaryTree(Arrays.asList(1,1));
    BinaryTree t4 = new BinaryTree(Arrays.asList(1,null,1,null,null,null,1));
    mergeTreesIte(t3.root,t4.root);
    test(t3.breadthFirstSearch(), new int[]{2,1,1,1});
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

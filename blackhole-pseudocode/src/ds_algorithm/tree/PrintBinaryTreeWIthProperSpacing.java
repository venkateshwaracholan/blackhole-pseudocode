/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree;

import static ds_algorithm.tree.MaximumDepthOfBinaryTree.maximumDepthDFSIte;
import static ds_algorithm.tree.SerializeAndDeserializeBinaryTree.deSerializeDfs;
import java.util.*;

/**
 *
 * @author vshanmugham
 */
public class PrintBinaryTreeWIthProperSpacing {
  
  
  // Time: O(n) space: O(n)
  public static void print_pattern(TreeNode root,int max_h){
    if(root==null)
        return;
    Queue<TreeNode> q = new LinkedList();
    q.add(root);
    int l = 1;
    while(!q.isEmpty()){
      int siz = q.size();
      print_spaces((int)Math.pow(2,(max_h-l))-1);
      while(siz>0){
        TreeNode t = q.poll();
        if(t!=null)
            System.out.print(t.val);
        else
            System.out.print(' ');

        if(siz>1)
            print_spaces((int)Math.pow(2,(max_h-l+1))-1);

        if(t!=null && l<max_h){
            q.add(t.left);
            q.add(t.right);
        }
        siz--;
     }
     System.out.println();
     l++;
    }
  }
  public static void print_spaces(int n){
    for(int i=0;i<n;i++)
        System.out.print(' ');
  }
  
  public static void main(String[] args){
    BinaryTree tree = new BinaryTree();
    tree.root = deSerializeDfs("1,2,null,null,3,null,4,");
    int d = maximumDepthDFSIte(tree.root);
    print_pattern(tree.root, d);
    
  }
  
}

//
//1 -> 2**0 - 1 
//
// 1 
//2 3.  -> 2**1 - 1
//
//   1
// 3   2
//1 2 3 4. -> 2**2 -1
//
//
//// here.   initial spacing = 2**(max_h-level) 
// followup spacng = 2**(max_h-level+1)
//max_h = 4
//
//       1         -> 2**3-1 l=1
//   3       2     -> 2**2-1,// 2**3-1 l=2
// 1   2   3   4   -> 2**1-1,// 2**2-1,2**2-1,2**2-1 
//1 1 1 1 1 1 1 1  -> 2**0-1,// 2**1-1,...
//
//
//
//
//// root
//// first find height
//// and bfs
//// add nulls in queue for spacing if level!= max_h
//// print spaces if null
//
//
//function print(TreeNode root){
//    int max_h = find_max_height(root);
//    print_pattern(TreeNode root,int max_h);
//}
//
//
////here 2 
//       1
//   3       2
//     2       4
//    1         1  -> 2**3-1  // h=4 
//






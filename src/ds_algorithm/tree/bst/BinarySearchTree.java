/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree.bst;

import com.google.gson.Gson;
import ds_algorithm.tree.BinaryTree;
import ds_algorithm.tree.BinaryTree;
import ds_algorithm.tree.InOrderTraversal;
import ds_algorithm.tree.TreeNode;
import ds_algorithm.tree.TreeNode;
import java.util.ArrayList;



/**
 *
 * @author venkateshwarans
 */

/*
    5
   4 6
  3

*/    
        

public class BinarySearchTree extends BinaryTree{
  
  BinarySearchTree(){
    
  }

  BinarySearchTree(int arr[]){
    for(int i: arr){
      insertIte(i);
    }
  }
  
  public void insertIte(int val){
    TreeNode n = new TreeNode(val);
    if(this.root==null){
      this.root = n;
      return;
    }
    TreeNode temp = root;
    while(temp!=null){
      if(val<=temp.val){
        if(temp.left==null){
          temp.left = n;
          return;
        }else
          temp = temp.left;
      }else{
        if(temp.right==null){
          temp.right = n;
          return;
        }else
          temp = temp.right;
      }
    }
  }
  
  public void insertRec(int val){
    this.root = insertRec(this.root, val);
  }

  public TreeNode insertRec(TreeNode node, int val){
    if(node==null){
      return new TreeNode(val);
    }
    if(val<node.val){
      node.left = insertRec(node.left,val);
    }else{
      node.right = insertRec(node.right,val);
    }
    return node;
  }
  
  public TreeNode maxPredecessor(TreeNode node){
    while(node.right!=null){
      node = node.right;
    }
    return node;
  }
  
  public TreeNode minSuccessor(TreeNode node){
    while(node.left!=null){
      node = node.left;
    }
    return node;
  }
  public TreeNode delete(int val){
    TreeNode[] t =  delete(this.root, val);
    this.root = t[0];
    return t[1];
  }
  
  public TreeNode[] delete(TreeNode root, int val){
    TreeNode node = root;
    if(node == null){
      return new TreeNode[]{null,null};
    }
    TreeNode parent = null;
    while(node != null){
      if(node.val == val){
        if(node.left==null && node.right == null){
          if(node==root){
            root=null;
          }else if(parent.left == node){
            parent.left = null;
          }else if(parent.right == node){
            parent.right = null;
          }
        }else if(node.left==null || node.right == null){
          TreeNode child  = node.left!=null ? node.left : node.right;
          if(node==root){
            root = child;
          }else if(parent.left==node)
            parent.left = child;
          else if(parent.right==node)
            parent.right = child;
        }else{
          TreeNode pred = maxPredecessor(node.left);
          delete(node.left, pred.val);
          node.val = pred.val;
          
//          TreeNode succ = minSuccessor(node.right);
//          delete(node, succ.val);
//          node.val = succ.val;
        }
        return new TreeNode[]{root, node};
      }else{
        parent = node;
        if(val < node.val){
          node = node.left;
        }else{
          node = node.right;
        }
      }
    }
    return new TreeNode[]{root, node};
  }
  
  public TreeNode SearchIte(TreeNode node, int val){
    if(node==null){
      return new TreeNode(val);
    }
    if(val<node.val){
      node.left = insertRec(node.left,val);
    }else{
      node.right = insertRec(node.right,val);
    }
    return node;
  }
  
  public TreeNode SearchRec(int val){
    return SearchRec(this.root, val);
  }
  
  public TreeNode SearchRec(TreeNode node, int val){
    if(node==null){
      return null;
    }
    if(val==node.val)
      return node;
    else if(val<node.val)
      return SearchRec(node.left,val);
    else
      return SearchRec(node.right,val);
  }
  
  
  
  public static void main(String[] args){
    BinarySearchTree bst = new BinarySearchTree();
    
    test(bst.breadthFirstSearch(), new int[]{});
    
    bst.insertIte(10);
    test(bst.breadthFirstSearch(), new int[]{10});
    bst.insertIte(5);
    bst.insertIte(15);
    bst.insertIte(6);
    bst.insertIte(13);
    test(InOrderTraversal.inOrderTraversalIte(bst.root), new int[]{5,6,10,13,15});
    
    bst = new BinarySearchTree();
    
    test(bst.breadthFirstSearch(), new int[]{});
    
    bst.insertRec(10);
    test(bst.breadthFirstSearch(), new int[]{10});
    bst.insertRec(5);
    bst.insertRec(15);
    bst.insertRec(6);
    bst.insertRec(13);
    test(InOrderTraversal.inOrderTraversalIte(bst.root), new int[]{5,6,10,13,15});
    
    bst = new BinarySearchTree(new int[]{2,5,7,1,3,5});
    test(InOrderTraversal.inOrderTraversalIte(bst.root), new int[]{1,2,3,5,5,7});
    System.out.println(bst.SearchRec(3).val);
    
    
    bst = new BinarySearchTree();
    bst.insertRec(5);
    bst.insertRec(2);
    bst.insertRec(10);
    bst.insertRec(4);
    bst.insertRec(3);
    test(bst.breadthFirstSearch(), new int[]{5,2,10,4,3});
    bst.delete(5);
    test(bst.breadthFirstSearch(), new int[]{4,2,10, 3});
    
    
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

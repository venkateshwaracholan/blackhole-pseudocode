/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.tree.bst;

/**
 *
 * @author vshanmugham
 */



/*

        5
      3   9
     2 4 6 11
     
     min-4, max-7
     
         5
        4 6
        
        
        5
      3   9
     2 4 6 11
     
     min-6, max-9
        
        9
       6

        5
      3   9
     2 4 6 11
     
     min-2, max-4
        
        3
       2 4
       
       
       5
     3   9
     
     min 9 max 9

TreeNode remove_elements_not_in_range(Treenode Root, int min, int max){
    if(root==null){
       return null;
    }
    
    if(root.val<min){
        TreeNode rightchild = root.right;
        root = null;
        return remove_elements_not_in_range(rightchild, min, max);
    }
    
    if(root.val>max){
        TreeNode leftchild = root.left;
        root = null;
        return remove_elements_not_in_range(leftchild, min, max);
    }
    
    TreeNode left = remove_elements_not_in_range(root.left, min, max);
    TreeNode right = remove_elements_not_in_range(root.rigt, min, max);
    
    
    
    retun root;

}
       


*/
public class removeElementOutOfRangeBST {
  
}

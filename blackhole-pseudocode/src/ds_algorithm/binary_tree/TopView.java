/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.binary_tree;
import java.util.*;
import com.google.gson.Gson;
/**
 *
 * @author venkateshwarans
 */


/*
         1
      2     3
    4   5  6  7
   8 9

*/

public class TopView {
  public static ArrayList<Integer> topView(TreeNode root){
    ArrayList<Integer> list= new ArrayList();
    
    return list;
  }
  
  public static void main(String[] args){
    int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    BinaryTree tree = new BinaryTree(arr);
    test(topView(tree.root), new int[]{8,4,9,2,5,1,6,3,7});
    test(topView(tree.root), new int[]{8,4,9,2,5,1,6,3,7});
    
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

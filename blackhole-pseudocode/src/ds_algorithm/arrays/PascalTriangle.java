/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.arrays;

/**
 *
 * @author venka
 */
import java.util.*;

// https://leetcode.com/problems/pascals-triangle/description/

public class PascalTriangle {
    
    // Time: O(n^2) Space: O(1)
    // approach 2 for loops
    // edge case handler j==0 || j==i add 1
    // easy to add new list and refer it for adding values instead of using temp variables
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList();
        for(int i=0;i<numRows;i++){
            ans.add(new ArrayList());
            for(int j=0;j<=i;j++){
                if(j==0||j==i)
                    ans.get(i).add(1);
                else
                    ans.get(i).add(ans.get(i-1).get(j-1)+ans.get(i-1).get(j));
            }
        }
        return ans;
    }
    
    //Time: O(n^2) space:O(1)
    // approach: intuitive recursion
    // get prev list and add 1 to start and thgen use prev list to fill values and finally add 1 again
    // add it in ans and return
    public List<List<Integer>> generateRec(int numRows) {
        if(numRows==1) return new ArrayList(Arrays.asList(new ArrayList(Arrays.asList(1))));
        List<List<Integer>> ans = generateRec(numRows-1);
        List<Integer> prev = ans.get(ans.size()-1);
        List<Integer> cur = new ArrayList(Arrays.asList(1));
        for(int j=1;j<prev.size();j++)
            cur.add(prev.get(j-1)+prev.get(j));
        cur.add(1);
        ans.add(cur);
        return ans;
    }
}

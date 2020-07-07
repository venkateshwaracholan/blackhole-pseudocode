/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.divide_and_conquer;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/random-pick-with-weight/
//if u dont understand quetion
// https://leetcode.com/problems/random-pick-with-weight/discuss/711246/Easy-Solution-with-Video-Explanation-Java

// question is to pick random probability based on eth weight
// [1 2 3 4] weight
// [0 1 2 3] index

// [0 1 1 2 2 2 3 3 3 3] now the index selection should be based on this probbility
import java.util.*;

public class RandomPickWithWeight {
  int cum[]; 
    Random random = new Random();
    public RandomPickWithWeight(int[] w) {
        cum = new int[w.length];
        cum[0] = w[0];
        for(int i=1;i<w.length;i++)
            cum[i] = cum[i-1] + w[i];
    }
    
    public int pickIndex() {
        int rand = random.nextInt(cum[cum.length-1]);
        int l = 0, r = cum.length-1, index = 0;
        while(l<=r){
            int mid = l+ (r-l)/2;
            if(rand<cum[mid]) r = mid-1;
            else l = mid+1;
        }
        return l;
    }
}

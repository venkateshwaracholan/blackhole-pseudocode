/*
  TWO SUM
*/


package ds_algorithm.hash;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//https://leetcode.com/problems/two-sum/


public class TwoSum {
    
    //APPROACH 1 brute two loops, j=i+1
    //  Time O(n^2) space O(1)
    // approach : brute force, 2 loops, lame
    public int[] twoSumBrute(int[] nums, int target) {
        for(int i=0;i<nums.length;i++)
            for(int j=i+1;j<nums.length;j++)
                if(nums[i]+nums[j]==target) return new int[]{i,j};
        return new int[]{0,0};
    }
    
    //APPROACH 2 hashmap val,index check target-val in map
    //  Time O(n) space O(n)
    // approach : Hashmap, check if target-cur is present in map, store value,index in map
    // return something random at end, prblm told it def has a solution
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map= new HashMap();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i]))
                return new int[]{i, map.get(target-nums[i])};
            map.put(nums[i], i);
        }
        return new int[]{0,0};
    }
    

}
/*
  TWO SUM
*/
package map;

import java.util.*;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int ans[] = {0,0};
        Map<Integer, Integer> map = new HashMap();
        for(int i=0;i<nums.length;i++){ 
            if(map.get(target-nums[i])!=null && map.get(target-nums[i])!=i){
                ans[0] = i;
                ans[1] = map.get(target-nums[i]);
                return ans;
            }
            map.put(nums[i],i);
        }
        return ans;
    }

    public static void main(String[] args){
      int input[] = {2,7,11,15};
      System.out.println(twoSum(input,9));
    }
}
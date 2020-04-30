/*
  TWO SUM
*/
package ds_algorithm.hash;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
      test(twoSum(new int[]{2,7,11,15}, 9), new int[]{0,1});
      test(twoSum(new int[]{2,7,11,15}, 18), new int[]{2,1});
      test(twoSum(new int[]{2,7,11,15}, 13), new int[]{2,0});
    }
    
    public static void test(int got[], int exp[]){
      Gson gson = new Gson();
      Arrays.sort(got);
      Arrays.sort(exp);
      String gotStr = gson.toJson(got);
      String expStr = gson.toJson(exp);
      System.out.println(gotStr.equals(expStr));
      System.out.println("got     : "+gson.toJson(gotStr));
      System.out.println("expected: "+gson.toJson(expStr));
    }
}
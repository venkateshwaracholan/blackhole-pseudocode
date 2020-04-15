/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.sort;

/**
 *
 * @author vshanmugham
 */
public class SoryArrayWithoutDuplicates {
  
}




/*
function sort_dec_without_duplicate(int nums[]){
    int dup = 0;
    for(int i=0;i<nums.length;){
        int max_i = i;
        for(int j=0;j<nums.length;j++){
            if(a[j]>a[max_i])
                max_i = j;             
        }
        if(i>0 && a[i-1]==a[max_i]){
            a[max_i] = Integer.Min_value;
            dup++;
        }else{
            int temp = a[i];
            a[i] = a[max_i];
            a[max_i] = temp;
            i++;
        }  
    }
    int new_len = nums.length-dup;
    int ans = new int[new_len];
    for(int i=0;i<new_len;i++){
        ans[i] = nums[i];
    }
    return ans;
}


*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.sort;

import com.google.gson.Gson;
import java.util.Arrays;

/**
 *
 * @author venkateshwarans
 */

// 1,6,9,8,3,5,4,2,1
public class MergeSort {
  public static int[] mergeSort(int[] arr) {
    return sortRec(arr, 0, arr.length-1);
  }
  
  public static int[] sortRec(int[] arr, int l, int r) {
    int mid = (l+r)/2;
    if(l<r){
      sortRec(arr, l, mid);
			sortRec(arr, mid+1, r);
      merge(arr, l, mid,r);
    }
    return arr;
  }
  
  public static void merge(int[] arr, int l,int mid, int r){
    //System.out.println(l+" : "+mid+" : "+r);
    //System.out.println(arr[l]+" : "+arr[mid]+" : "+arr[r]);
    int n1 = mid-l+1;
    int n2 = r-mid;
    int i,j,k;
    int L[] = new int[n1];
    int R[] = new int[n2];
    
    for(i=0;i<n1;i++){
      L[i] = arr[l+i];
    }
    for(j=0;j<n2;j++){
      R[j] = arr[mid+1+j];
    }
    i=0;
    j=0;
    k=l;
    while(i<n1&&j<n2){
      if(L[i]<R[j]){
        arr[k] = L[i];
        i++;
      }else{
        arr[k] = R[j];
        j++;
      }
      k++;
    }
    while(i<n1){
      arr[k++] = L[i++];
    }
    while(j<n2){
      arr[k++] = R[j++];
    }
  }

  public static void main(String[] args){
    int arr[] = new int[]{12,7,2,4,6,2,4,1,6,3,4,7,8,3,1,15,2};
    test(mergeSort(arr.clone()), arr);
  }

  public static void test(int got[], int exp[]){
    Gson gson = new Gson();
    Arrays.sort(exp);
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
}

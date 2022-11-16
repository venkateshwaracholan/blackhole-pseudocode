/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author vshanmugham
 */
public class Test {
  public static boolean show = true;
  // Integer comparison
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
  
  // String Comparison
  public static void test(String gotStr, String expStr){
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gotStr);
    System.out.println("expected: "+expStr);
  }
  
  // List array comparison
  public static void test(ArrayList<Integer> got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
  
  // List List comparison
  public static void test(ArrayList<Integer> got, ArrayList<Integer> exp){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    System.out.println("got     : "+gson.toJson(gotStr));
    System.out.println("expected: "+gson.toJson(expStr));
  }
  
  // int array array comparison
  public static void test(int[] got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    if(show || !gotStr.equals(expStr)){
      System.out.println("got     : "+gson.toJson(gotStr));
      System.out.println("expected: "+gson.toJson(expStr));
    }
  }
  
  public static void test(boolean got, boolean exp){
    System.out.println(got==exp);
//    System.out.println("got     : "+got);
//    System.out.println("expected: "+exp);
  }
  
  public static void main(String arg[]){
    
  }
}

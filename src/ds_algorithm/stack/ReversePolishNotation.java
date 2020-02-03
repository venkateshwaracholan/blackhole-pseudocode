/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.stack;

import java.util.Stack;

/**
 *
 * @author venkateshwarans
 */
public class ReversePolishNotation {
  public static boolean show = false;
  
  public static int reversePolishNotation(String[] tokens){
    String operations = "+-*/";
    Stack<String> s = new Stack();
    for(int i=0;i<tokens.length;i++){
      String t = tokens[i];
      if(!operations.contains(t)){
        s.push(t);
     }else{
        int b =  Integer.valueOf(s.pop());
        int a =  Integer.valueOf(s.pop());
        int ind = operations.indexOf(t);
        switch(ind){
          case 0: s.push(String.valueOf(a+b));break;
          case 1: s.push(String.valueOf(a-b));break;
          case 2: s.push(String.valueOf(a*b));break;
          case 3: s.push(String.valueOf(a/b));break;
        }
      }
    }
    return Integer.valueOf(s.pop());
  }
  
  public static void main(String[] args){
    test(reversePolishNotation(new String[]{ "2", "1", "+", "3", "*" }), 9);
    test(reversePolishNotation(new String[]{"4", "13", "5", "/", "+"}), 6);
  }
  
  public static void test(int got, int exp){
    System.out.println(got == exp);
    if(show || got != exp){
      System.out.println("got     : "+got);
      System.out.println("expected: "+exp);
    }
  }
}

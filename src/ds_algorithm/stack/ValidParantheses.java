/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.stack;

import java.util.*;

/**
 *
 * @author vshanmugham
 */

// https://leetcode.com/problems/valid-parentheses

public class ValidParantheses {
  
  public boolean isValid(String s) {
    Stack<Character> st = new Stack();
    for(int i=0;i<s.length();i++){
      char c = s.charAt(i);
      if(c == '{' || c == '[' || c == '('){
        st.add(c);
      }else{
        if(st.isEmpty())
          return false;
        char top = st.peek();
        if((c==')' && top!='(') || (c=='}' && top!='{') || (c==']' && top!='[') ){
          return false;
        }
        st.pop();
      }
    }
    return st.isEmpty();
  }
  
  public boolean isValid2(String s) {
        Stack<Character> st = new Stack();
        Map<Character,Character> m = new HashMap<>();
        m.put(')', '(');
        m.put('}', '{');
        m.put(']', '[');
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '{' || c == '[' || c == '('){
                st.add(c);
            }else{
                if(st.isEmpty())
                    return false;
                char top = st.peek();
                if(top!=m.get(c))
                    return false;
                st.pop();
            }
        }
        return st.isEmpty();
    }
  
}

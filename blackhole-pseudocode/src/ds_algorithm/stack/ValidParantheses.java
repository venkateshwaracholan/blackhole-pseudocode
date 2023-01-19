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
    
    //APPROACH 2 stack+checks, 
    // Time O(n) space O(n)
    // approach: stack
    // iterat chars, if else if ladder
    // add resp closing brach char for each open
    // if not opn chars, then check if stack empty or stack top is not equal to retrun false
    //finally return true f stack empty
    public boolean isValid(String s) {
        if(s.length()%2!=0) return false;
        Stack<Character> stack = new Stack();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') stack.push(')');
            else if(s.charAt(i)=='[') stack.push(']');
            else if(s.charAt(i)=='{') stack.push('}');
            else if(stack.isEmpty() || stack.pop()!=s.charAt(i)) return false;
        }
        return stack.isEmpty();
    }
    // Time O(n) space O(n)
    // approach: stack
    // add if one of open braces
    // else if stac k empty ret false
    // pop it which will contain open brace
    // check if incoming close brace corresponds to open brace
    public boolean isValid2(String s) {
        if(s.length()%2!=0)return false;
        Stack<Character> st = new Stack();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(' || c=='[' || c=='{') st.push(c);
            else{
                if(st.isEmpty()) return false;
                char t = st.pop();
                if(c==')'&&t!='(' || c==']'&& t!='[' || c=='}'&&t!='{') return false;
            }
        }
        return st.isEmpty();
    }
    
    //APPROACH 2 stack+map, 
    // 
    public boolean isValid3(String s) {
        if(s.length()%2!=0) return false;
        Map<Character,Character> map = new HashMap();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Stack<Character> st = new Stack();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)) st.add(c);
            else if(st.isEmpty()||st.pop()!=map.get(c)) return false;
        }
        return st.isEmpty();
    }
    // using map to avoid code from above approach
    public boolean isValidMap(String s) {
        if(s.length()%2!=0) return false;
        Map<Character,Character> map = new HashMap();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        Stack<Character> st = new Stack();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(map.containsKey(c)) st.push(c);
            else{
                if(st.isEmpty() || map.get(st.pop())!=c) return false;
            }
        }
        return st.isEmpty();
    }
  
    
  
}

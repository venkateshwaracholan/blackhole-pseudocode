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


    /*
    * Approach: Brute Force by repeated replacement
    * - While the string changes:
    *     - Replace all "()", "[]", "{}" with "".
    * - Continue until no changes occur.
    * - If resulting string is empty → valid parentheses; else invalid.
    *
    * Time Complexity:
    *   Best Case: O(n) — all pairs removed in a single pass, e.g. s = "()[]{}".
    *   Worst Case: O(n^2) — deep nesting removes only one pair per pass, e.g.
    *       s = "(((((((((())))))))))".
    * Space Complexity: O(n) — new strings created each pass.
    *
    * Rationale: Simple and intuitive brute force approach.
    * Avoids explicit stack usage but is inefficient for large inputs.
    * Serves as a baseline before optimizing to a stack‑based O(n) solution.
    */
    public boolean isValidBrute(String s) {
        String prev = "";
        while(!s.equals(prev)){
            prev  = s;
            s = s.replace("()","")
            .replace("[]","")
            .replace("{}","");
        }
        return s.isEmpty();
    }

    /*
    * Approach: Optimized Stack-Based Matching by pushing open braces
    * - If string length is odd → return false (cannot be balanced).
    * - Use a stack to track open brackets '(' '{' '['.
    * - Iterate through string:
    *     - If open bracket '(' '{' '[' → push to stack.
    *     - Else closing bracket ')' '}' ']' → 
    *         - If stack is empty → invalid.
    *         - Else pop directly and check if popped bracket matches expected open bracket.
    *           If mismatch → invalid.
    * - At end, if stack is empty → valid parentheses.
    *
    * Time Complexity: O(n) — single pass through the string, each character processed once.
    * Space Complexity: O(n) — in worst case all characters are open brackets stored in the stack.
    *
    * Rationale: Efficient and standard approach for parentheses matching.
    * Avoids extra peek step by checking bracket match directly on pop.
    *
    * Example:
    *   Best Case: "()[]{}" → O(n), stack never grows large.
    *   Worst Case: "(((((((((())))))))))" → O(n), stack grows to n/2 before unwinding.
    */
    public boolean isValid2(String s) {
        if(s.length()%2==1){
            return false;
        }
        var st = new Stack<Character>(); 
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='('||c=='{'||c=='['){
                st.push(c);
            }
            else{
                if(st.empty()){
                    return false;
                }
                char t = st.pop();
                if(c=='}'&&t!='{' || c==')'&&t!='(' || c==']' &&t!='[' ){
                    return false;
                }
            }
        }
        return st.empty();
    }

    // using map to avoid code from above approach
    public boolean isValidMap(String s) {
        if(s.length()%2==1){
            return false;
        }
        var map = Map.of('(', ')', '{', '}', '[', ']');
        var st = new Stack<Character>(); 
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                st.push(c);
            }
            else if(st.empty() || map.get(st.pop())!=c){
                return false;
            }
        }
        return st.empty();
    }

    
    /*
    * Approach: Optimized Stack-Based Matching by pushing expected closing brackets
    * - If string length is odd → return false (cannot be balanced).
    * - Use a stack to track expected closing brackets.
    * - Iterate through the string:
    *     - If c is an open bracket:
    *         - '(' → push ')'
    *         - '{' → push '}'
    *         - '[' → push ']'
    *     - Else (c is a closing bracket):
    *         - If stack is empty → invalid.
    *         - Else pop from stack and check if popped bracket matches c.
    *           If mismatch → invalid.
    * - At end, if stack is empty → valid parentheses; else invalid.
    *
    * Time Complexity: O(n) — single pass through string, each character processed once.
    * Space Complexity: O(n) — in worst case all characters are open brackets stored in the stack.
    *
    * Rationale: This approach avoids explicit matching logic by pushing the expected
    * closing bracket for each open bracket, enabling direct comparison upon popping.
    * This simplifies the check and improves readability.
    */
    public boolean isValid3(String s) {
        if(s.length()%2==1){
            return false;
        }
        var st = new Stack<Character>(); 
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='('){
                st.push(')');
            }
            else if(c=='{'){
                st.push('}');
            }
            else if(c=='['){
                st.push(']');
            }
            else if(st.empty() || st.pop()!=c){
                return false;
            }
        }
        return st.empty();
    }

    // using map to avoid code from above approach
    public boolean isValid4(String s) {
        if(s.length()%2==1){
            return false;
        }
        var map = Map.of('(', ')', '{', '}', '[', ']');
        var st = new Stack<Character>(); 
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                st.push(map.get(c));
            }
            else if(st.empty() || st.pop()!=c){
                return false;
            }
        }
        return st.empty();
    }
    
  
    
  
}

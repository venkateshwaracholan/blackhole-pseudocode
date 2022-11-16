/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.stack;

/**
 *
 * @author vshanmugham
 */
import java.util.*;
// https://leetcode.com/problems/online-stock-span

public class OnlineStockSpan {
  
}


// Time: O(n) space: O(n)
// using 2 stacks, on to store prices and another for weights
// if new el is less than top just add pice and weiht as 1
// if new el is greater than top, pop until its not and add weights to itself
// finally push new el and the accumulated weight
class StockSpanner {
    Stack<Integer> prices, weights;
    public StockSpanner() {
        prices = new Stack();
        weights = new Stack();
    }
    
    public int next(int price) {
        int w = 1;
        while(!prices.empty() && price >= prices.peek()){
            prices.pop();
            w+=weights.pop();
        }
        prices.push(price);
        weights.push(w);
        return w;
    }
}

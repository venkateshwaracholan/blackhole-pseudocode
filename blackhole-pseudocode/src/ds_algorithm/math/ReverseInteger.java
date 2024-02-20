/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_algorithm.math;

/**
 *
 * @author venka
 */
public class ReverseInteger {
    public int reverse(int x) {
        int prevRev = 0 , rev= 0;
        while( x != 0){
            rev= rev*10 + x % 10;
            System.out.println(rev+" "+x);
            System.out.println(prevRev+" "+ ((rev - x % 10) / 10));
            if((rev - x % 10) / 10 != prevRev){
                return 0;
            }
            prevRev = rev;
            x= x/10;
        }
        return rev;
    }
    //2147483647
    //-2147483648

    // Time Complexity: O(log⁡(x)). There are roughly log⁡10(x) (x) digits in x.
    // Space Complexity: O(1).
    // why this works is becasue rev of 2147483647 => 7463847412 is not int coz > int max, 
    // only 1463847412 and lesser is a valid int which on reverse cannot overflow
    // 1563847412 or 1463847413 overflow and rev of 9 dig becomes > Integer.MAX_VALUE/10, so we can return 0
    
    // 
    public int reverse2(int x) {
        int ans = 0;
        while(x!=0){
            if(ans>Integer.MAX_VALUE/10 || ans<Integer.MIN_VALUE/10)
                return 0;
            ans= ans*10 + x%10;
            x/=10;
        }
        return ans ;
    }
}

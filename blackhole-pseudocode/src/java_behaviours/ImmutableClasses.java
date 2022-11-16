/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_behaviours;

/**
 *
 * @author vshanmugham
 */

/*
The class must be declared as final (So that child classes can’t be created)
Data members in the class must be declared as final (So that we can’t change the value of it after object creation)
A parameterized constructor
Getter method for all the variables in it
No setters(To not have the option to change the value of the instance variable)

Advantages:
mutable objects are thread-safe so you will not have any synchronization issues.
Immutable objects are good Map keys and Set elements, since these typically do not change once created.
Immutability makes it easier to write, use and reason about the code (class invariant is established once and then unchanged)
Immutability makes it easier to parallelize your program as there are no conflicts among objects.
The internal state of your program will be consistent even if you have exceptions.
References to immutable objects can be cached as they are not going to change.

example:
map keys are immutable

final vs immutability
in finan referene cant be changes but states of objects can be,
in immutable class reference can be changes buut not the states.

*/

final class Student 
{ 
    final String name; 
    final int regNo; 
  
    public Student(String name, int regNo) 
    { 
        this.name = name; 
        this.regNo = regNo; 
    } 
    public String getName() 
    { 
        return name; 
    } 
    public int getRegNo() 
    { 
        return regNo; 
    } 
} 
  
// Driver class 
public class ImmutableClasses 
{ 
    public static void main(String args[]) 
    { 
        Student s = new Student("ABC", 101); 
        System.out.println(s.getName()); 
        System.out.println(s.getRegNo()); 
  
        // Uncommenting below line causes error 
        // s.regNo = 102; 
    } 
} 
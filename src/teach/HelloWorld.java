/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teach;

/**
 *
 * @author vshanmugham
 */

/*
int, char
int x = 0;


class and object

static - for class
non static - for objects  or instances

class:
access specifier
variables, function
constructors

*/

class Car{
  //variables identifiers
  private int wheels;
  private String name;
  public static int x = 100;
  
  Car(){
    
  }
  
  
  public boolean compareCars(){
    return false;
  }
  
  //methods functions
  public static void func(){
    System.out.println("Car class method");
  }
  
  public void addWheel(int wheels){
    if(validate(wheels))
      this.wheels = wheels;
  }
  
  private boolean validate(int wheels){
    if(wheels>4){
      System.out.println("otha, car ku 4 wheel dhan da nee " +wheels+ " kudutha enna panradhu");
      return false;
    }
    return true;
  }
  
  public int getWheels(){
    return this.wheels;
  }
}

public class HelloWorld {
  public static void main(String args[]){
    Car car = new Car(); // object or instances
    car.addWheel(4);
    System.out.println(car.getWheels());
    System.out.println(0);
  }
}

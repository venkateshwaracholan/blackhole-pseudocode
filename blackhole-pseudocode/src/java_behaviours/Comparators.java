/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_behaviours;


// https://mkyong.com/java/java-object-sorting-example-comparable-and-comparator/

/**
 *
 * @author vshanmugham
 */


/*


  a   b
  5  10

a-b   asc
  
-1 0 1

b-a
1 0 -1



12345

ab
no swap a-b is negative


Key thing to remember is if comparison returns positive value ( >0) swap happens. Otherwise not during sorting algorithm.

Example:

4(a) 2(b) 6

For ascending order:

a > b (4 > 2) return 1 (swap requires between a and b i.e place 2 4 6)

For descending order:

a > b ( 2 > 4 ) return -1 (swap not needed between a and b i.e place 4 2 6, because it is already in order).

This logic implemented under sorting algorithm, So, just think if a and b are already in order as you expected then return -1 otherwise return 1.


*/
import java.util.*;
import java.util.stream.Collectors;

public class Comparators {
  
  // there is no way to sort primitives like int array
  public static void main(String args[]){
        
        //data
        //ints
        int arr[] = new int[]{7,2,6,3,6,8,6};
        List<Integer> arrlist = Arrays.stream(arr).boxed().collect(Collectors.toList()); //we cant use like strings
        Integer[] inrarr = Arrays.stream(arr).boxed().toArray(Integer[]::new); // int[] to integer[]
        
        //strings
        String[] strArr = {"a", "b", "c", "d", "e"};
        List<String> strlist = Arrays.asList(strArr);   
        
        //objects
        Car[] cars = new Car[] {
            new Car(20000, "Toyota Corolla", 5, 4, true),
            new Car(30000, "Honda Civic", 3, 5, false),
            new Car(25000, "Ford Mustang", 2, 3,true),
            new Car(35000, "Tesla Model S", 1, 5,false),
            new Car(15000, "Chevrolet Spark", 10, 2,false)
        };
        List<Car> carlist = Arrays.asList(cars);   
        
        
        // normal sorting ascending
        Arrays.sort(arr);
        Arrays.sort(arr);
        Collections.sort(arrlist);
        arrlist.sort(Comparator.comparing(x -> x));
        Arrays.sort(cars);
        Collections.sort(carlist);
        carlist.sort(Comparator.comparing(x -> x.getRating())); //using arrow
        carlist.sort(Comparator.comparing(Car::getRating));// referring to class method, key extractor function
        carlist.sort(Comparator.comparing(Car::getRating).thenComparing(Car::getName));// chaining comparators to further sort if equal
        carlist.sort(Car::compareBypriceThenRatingThenName); //using static method
        carlist.stream().sorted(Comparator.comparing(Car::getRating)).collect(Collectors.toList()); // using streams

        //reverse order
        //Comparator.reverseOrder() uses Collections.reverseOrder() internally
        Arrays.sort(inrarr, Collections.reverseOrder()); // int[] cant be sorted reverse order, we can iterrate from rev
        Collections.sort(arrlist, Collections.reverseOrder());
        arrlist.sort(Comparator.comparing(x -> x, Collections.reverseOrder()));
        Comparator<Integer> comp6 = Comparator.comparing(x -> x); // reversed works only on comparator variables
        arrlist.sort(comp6.reversed());
        
        Arrays.sort(cars, Comparator.reverseOrder());
        Collections.sort(carlist, Comparator.reverseOrder());
        carlist.sort(Comparator.comparing(Car::getRating).reversed());// works on class but not primitives unless assigned to comparator variable
        carlist.sort(Comparator.comparing(Car::getRating).thenComparing(Car::getName).reversed());// chaining comparators to further sort if equal
        

        //lambda comparators
        Arrays.sort(strArr, (a,b)-> a.compareTo(b));
        Arrays.sort(strArr, Comparator.comparing(x -> x));
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)-> a.compareTo(b));
        minHeap.add(5);
        minHeap.add(10);
        Collections.sort(carlist, (a,b) -> a.getName().compareTo(b.getName()));
        Comparator<Car> comp7  = (Car a,Car b) -> a.getName().compareTo(b.getName());// mentioning class names
        Comparator<Car> comp8  = (a,b) -> Integer.compare(a.getRating(),b.getRating());//compiler can infer on its own too, also uisng integer compare instead of a-b style
        Comparator<Car> comp9 = comp7.thenComparing(comp8); //chaining comparators created separately
        carlist.stream().sorted((a,b) -> a.getRating() - b.getRating()).collect(Collectors.toList()); // using streams
        carlist.stream().sorted(comp9).collect(Collectors.toList());
    
        
          
        //lambda comparators reversed
        // boths arrays sort and collectionb sort takes extra param (arr, comp, order);
        Arrays.sort(strArr, (a,b)-> b.compareTo(a)); //a and b reversed
        Arrays.sort(strArr, Comparator.comparing(x -> x, Comparator.reverseOrder())); //requires import
        
        Collections.sort(arrlist, (a,b)-> b.compareTo(a)); //a and b reversed
        Collections.sort(arrlist, Comparator.comparing(x -> x, Comparator.reverseOrder())); 
        
        Comparator<String> comp4 = Comparator.comparing(x -> x);// cannnot be reversed here, we can also use compareTo to gen comparator here
        Comparator<String> comp5 = comp4.reversed();//can be reversed after creating a comparator comp4
        

        //creating comparators
          
        //comparator as a class defined below at end
        IntegerComparator com = new IntegerComparator();
        //creating comparator with new and overriding method compare, anonymous class creation with interface comparatorsoverriding method compare(compare two objects)
        // comparator cannot have compareTo(compare self to 1 object)
        Comparator<Integer> com2 = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compare(01, o2);
            }
        };
        
        
        Arrays.sort(inrarr, com);
        Collections.sort(arrlist, com);
        
        //comparator reverse order
        Arrays.sort(inrarr, com.reversed());
        Collections.sort(arrlist, com.reversed());
        for(Integer s: inrarr) System.out.println(s);
        
        
        //boolean comparators
        // show new cars first
        Comparator<Car> comparator = new Comparator<Car>() {
		@Override
		public int compare(Car c1, Car c2) {
                    Boolean b1 =  c1.isNew();
                    Boolean b2 =  c2.isNew();
                    //return b2.compareTo(b1);
                    return Boolean.compare(b2,b1);
		}
	};
        //then show cars price greater than price
        comparator = comparator.thenComparing((c1)  -> {
            return c1.priceGreaterThan(28000);
        },Comparator.reverseOrder());
        Collections.sort(carlist, comparator.thenComparing(Comparator.comparing(Car::getName).reversed()));
        Collections.sort(carlist, comparator.thenComparing(Car::getName, Collections.reverseOrder())); // same as above line
        
        //prints
        for(String s: strArr) System.out.println(s);
        for(String s: strArr) System.out.println(s);
        for(Car c: carlist) System.out.println(c);
        while(!minHeap.isEmpty()){
            System.out.println(minHeap.poll());
        }
        
        //handling nulls in comparators
        carlist.sort((h1, h2) -> {
            if (h1 == null) {
                return h2 == null ? 0 : 1;
            }
            else if (h2 == null) {
                return -1;
            }
            return h1.getName().compareTo(h2.getName());
        });
        carlist.sort(Comparator.nullsLast(Comparator.comparing(Car::getName)));
        carlist.sort(Comparator.nullsFirst(Comparator.comparing(Car::getName)));
      }
  
  
  
}

class Car implements Comparable<Car>{
    private int price;
    private String name;
    private int qty;
    private int rating;
    private boolean isNew;
    Car(int price, String name, int qty, int rating, boolean isNew){
        this.price = price;
        this.name = name;
        this.qty = qty;
        this.rating = rating;
        this.isNew = isNew;
    }
    public int getRating(){
        return this.rating;
    }
    public String getName(){
        return this.name;
    }
    public boolean isNew(){
        return this.isNew;
    }
    public boolean priceGreaterThan(int price){
        return this.price >= price;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("name : ").append(name);
        sb.append("\nprice : ").append(price);
        sb.append("\nqty : ").append(qty);
        sb.append("\nrating : ").append(rating);
        sb.append("\nIs new Car : ").append(isNew);
        return sb.toString();
    }
    
    //name is default comparison
    @Override
    public int compareTo(Car c1) {
        //chaining comparator logic without then comparing
        int i = this.price - c1.price;
        if (i != 0) return i;
        i = ((Integer)this.rating).compareTo(c1.rating);
        if (i != 0) return i;
        return this.name.compareTo(c1.name);
    }
    //alt code style + static methods
    public static int compareBypriceThenRatingThenName(Car c1, Car c2) {
        if (c1.price == c2.price) {
            if (c1.rating == c2.rating)
                return c1.name.compareTo(c2.name);
            else 
                return Integer.compare(c1.rating, c2.rating);
        } else{
            return Integer.compare(c1.price, c2.price);
        }
    }
}

//compparator is an interface with method compare( comparing two objects)
class IntegerComparator implements Comparator<Integer>{ 
    public int compare(Integer a, Integer b){ 
        return a - b;
    } 
} 
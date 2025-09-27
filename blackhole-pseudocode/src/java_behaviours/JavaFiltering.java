/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_behaviours;

import java.util.Arrays;
import java_behaviours.Car;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author venka
 */
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
}

public class JavaFiltering {
    public static void main(String[] args){
        double[] d = {8, 7, -6, 5, -4};
        d = Arrays.stream(d).filter(x -> x > 0).toArray();
        
        String[] a = { "s", "", "1", "", "" };
        a = Arrays.stream(a).filter(s -> !s.isEmpty()).toArray(String[]::new);
        
        //objects
        Car[] cars = new Car[] {
            new Car(20000, "Toyota Corolla", 5, 4, true),
            new Car(30000, "Honda Civic", 3, 5, false),
            new Car(25000, "Ford Mustang", 2, 3,true),
            new Car(35000, "Tesla Model S", 1, 5,false),
            new Car(15000, "Chevrolet Spark", 10, 2,false)
        };
        List<Car> carlist = Arrays.asList(cars);   
        
        cars = Arrays.stream(cars).filter(s -> s.isNew()).toArray(Car[]::new);
        carlist = carlist.stream().filter(s -> s.isNew()).collect(Collectors.toList());
    }
}
